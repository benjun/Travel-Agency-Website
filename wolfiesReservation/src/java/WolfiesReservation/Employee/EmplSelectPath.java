/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minchan Jun
 */
public class EmplSelectPath extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //get form data
        
        int    index=0;
        int passengers =1;  
        try {
                 index = Integer.parseInt(request.getParameter("index"));
                 passengers = Integer.parseInt(request.getParameter("passengers"));
        } catch(Exception e) {
            index=0;
            passengers=1;
        }
        List<String[]> path = ((List<List<String[]>>)request.getSession(true).getAttribute("results")).get(index);
        String repssn     = (String)request.getSession(true).getAttribute("EmployeeSSN");
        String meal       = request.getParameter("meal");
        String seatclass  = request.getParameter("class");
        
        String date       = path.get(0)[5].substring(0, 10);
        String accountNo  = (String)request.getParameter("accountNo");
        System.out.println("SEEME"+accountNo);
        float  totalFare  = getFare(path, seatclass, passengers);
        String resrNo     = "";
        int    passId     = 0;
        
        String msg = "";
        //JDBC connection
        Connection c = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        try {
            Class.forName(driver).newInstance();
            c = DriverManager.getConnection(url + dbName, dbUserName, dbPassword);
            
            //Queries
            //ResrDate, BookingFee, TotalFare, RepSSN, AccountNo
            String insertResQuery = "INSERT INTO Reservation (ResrDate, BookingFee, TotalFare, RepSSN, AccountNo) VALUES (?, ?, ?, ?, ?)";
            //ResrNo, AirlineID, FlightNo, LegNo, LegDate 
            String insertIncQuery = "INSERT INTO Includes VALUES (?, ?, ?, ?, "
                                  + "?)";
            //Id, AccountNo
            String passengerQuery = "INSERT INTO Passenger VALUES (?, ?)";
            //ResrNo, Id, AccountNo, SeatNo, Class, Meal
            String insertPasQuery = "INSERT INTO ReservationPassenger "
                                  + "VALUES (?, ?, ?, ?, ?, ?)";
            //Query to get last inserted reservation
            String getResrNo = "SELECT MAX(ResrNo) AS OurResrNo FROM "
                             + "Reservation";
            //Query to get last inserted Passenger
            String getPassId = "SELECT MAX(Id) AS FirPassId FROM "
                             + "Passenger where accountNo = ?";
            
            //Create new reservation
            PreparedStatement insResStm = c.prepareStatement(insertResQuery);
            insResStm.setString(1, date);
            insResStm.setString(2, String.format("%.2f%n", totalFare/10));
            insResStm.setString(3, String.format("%.2f%n", totalFare));
            if(repssn.equals("no")) {
                insResStm.setNull(4, Types.INTEGER);
            } else {
                insResStm.setString(4, repssn);
            }
            accountNo = "1";
            insResStm.setString(5, accountNo);
            insResStm.executeUpdate();
            out.println("Successfully inserted Reservation");
            
            PreparedStatement getResrStm = c.prepareStatement(getResrNo);
            ResultSet rs = getResrStm.executeQuery();
            if(rs.next()) {
                resrNo = rs.getString("OurResrNo");
                if(rs.wasNull()) {
                    resrNo = "1";
                }
            }
            PreparedStatement getPassStm = c.prepareStatement(getPassId);
            getPassStm.setString(1, accountNo);
            ResultSet rs2 = getPassStm.executeQuery();
            if(rs2.next()) {
                String tempVal = rs2.getString("FirPassId");
                if(rs2.wasNull()) {
                    passId = 0;
                } else {
                    passId = Integer.parseInt(tempVal);
                }
            }
            //For each leg on the path, create Includes row
            for(String[] leg : path) {
                PreparedStatement incStm = c.prepareStatement(insertIncQuery);
                incStm.setString(1, resrNo);
                incStm.setString(2, leg[0]);
                incStm.setString(3, leg[1]);
                incStm.setString(4, leg[2]);
                incStm.setString(5, leg[6]);
                
                incStm.executeUpdate();
            }
            out.println("Successfully inserted Includes");
            //For each passenger, create ReservationPassenger row
            for(int i=0;i<passengers;i++) {
                //insert a Passenger row
                PreparedStatement pstm = c.prepareStatement(passengerQuery);
                pstm.setString(1, Integer.toString(passId+i+1));
                pstm.setString(2, accountNo);
                pstm.executeUpdate();
                
                PreparedStatement pasStm = c.prepareStatement(insertPasQuery);
                pasStm.setString(1, resrNo);
                pasStm.setString(2, Integer.toString(passId+i+1));
                pasStm.setString(3, accountNo);
                pasStm.setString(4, "D21C");
                pasStm.setString(5, seatclass);
                pasStm.setString(6, meal);
                pasStm.executeUpdate();
            }
            out.println("Successfully inserted passengers");
            
            //process
            //Create ONE Reservation -- DONE
            //For each leg on path, create Includes -- DONE
            //For each passenger, create a ReservationPassenger -- DONE
            
            RequestDispatcher rd = request.getRequestDispatcher("/employee/index.jsp");
            rd.forward(request, response);
        } catch(Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    }
    
    private float getFare(List<String[]> path, String seatclass, 
                          int passengers) {
        float totalFare = 0;
        //Connection Preliminaries
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, dbUserName,   
                    dbPassword);
            //Execute Query
            for(String[] leg : path) {
                String strQuery = "select fare from Fare where Class = ? and "
                                + "airlineid = ? and flightNo = ? ";
                PreparedStatement stm = conn.prepareStatement(strQuery);
                stm.setString(1, seatclass);
                stm.setString(2, leg[0]);
                stm.setString(3, leg[1]);
                ResultSet rs = stm.executeQuery();
                if(rs.next()) {
                    totalFare += Float.parseFloat(rs.getString("fare"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return totalFare*passengers;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
}

