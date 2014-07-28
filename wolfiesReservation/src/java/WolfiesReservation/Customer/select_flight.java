/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class select_flight extends HttpServlet {

   
      public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //get all parameters from FlightData.jsp
        String aaID = request.getParameter("AirlineID");
        String flightNo = request.getParameter("FlightNo");
        String legNo = request.getParameter("LegNo");
        String depAirportID = request.getParameter("DepAirportID");
        String arrAirportID = request.getParameter("ArrAirportID");
        String arrTime = request.getParameter("ArrTime");
        String depTime = request.getParameter("DepTime");
        String fareType = request.getParameter("FareType");
        String classType = request.getParameter("Class");
        String fare = request.getParameter("Fare");
        String accountNo = request.getParameter("accountNo");
        float fareFloat = Float.parseFloat(fare);
        
        //String acctNo = request.getParameter("acctountNumber");
        
        // tested value  
//        out.println(aaID +"<br>");
//        out.println(flightNo+ "<br>");
//        out.println(legNo + "<br>");
//        out.println(depAirportID + "<br>");
//        out.println(arrAirportID + "<br>");
//        out.println(arrTime + "<br>");
//        out.println(depTime + "<br>");
//        out.println(fareType + "<br>");
//        out.println(classType + "<br>");
//        out.println(fare );
  
          String msg = "";
        //JDBC connection
            Connection c = null;
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "travelagency";
            String driver = "com.mysql.jdbc.Driver";
            String dbUserName = "root";
            String dbPassword = "root";
        
            try{
            
                Class.forName(driver).newInstance();
                c = DriverManager.getConnection(url + dbName, dbUserName, dbPassword);
                //ResrNo, ResrDate, BookingFee, TotalFare, RepSSN, AccountNo
                String strQuery = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?, ?)";
                //ResrNo, AirlineID, FlightNo, LegNo, Date 
                String strQuery2 = "INSERT INTO includes VALUES (?, ?, ?, ?, ?)";
                //ResrNo, Id, AccountNo, SeatNo, Class, Meal
                String strQuery3 = "INSERT INTO reservationpassenger VALUES (?, ?, ?, ?, ?, ?)";
                
                String strQuery4 = "SELECT (MAX(ResrNo) + 1) AS NextResrNo FROM reservation";
                //String strQuery5 = "SELECT AccountNo, Id FROM customer";
                PreparedStatement stm = c.prepareStatement(strQuery);
                PreparedStatement stm2 = c.prepareStatement(strQuery2);
                PreparedStatement stm3 = c.prepareStatement(strQuery3);
                PreparedStatement stm4 = c.prepareStatement(strQuery4);
                //PreparedStatement stm5 = c.prepareStatement(strQuery5);
                ResultSet rs = stm4.executeQuery();
                //ResultSet rs1 = stm5.executeQuery();
                //I need to fix resrNo randomly selected here. I need to get info from strQuery
                int nextResrNo = 1;
                //int AccountNo = 0;
                int id = 1; // need to get data from person table 1010 ->3  1009 ->2 1008 ->1
                // &&rs1.next()
                if (rs.next() )
                {
                nextResrNo = rs.getInt(1);
                //AccountNo = rs1.getInt(1);
                //id = rs1.getInt(2);
                stm.setInt(1, nextResrNo);
                stm.setTimestamp(2, getCurrentTimeStamp());
                //booking fee is always 200 in mine. 
                stm.setFloat(3, 200);
                stm.setFloat(4, fareFloat + 200);
                stm.setString(5, null);
                //I need to get info from customer account number.
                stm.setString(6, accountNo);
                
                stm2.setInt(1, nextResrNo);
                stm2.setString(2, aaID);
                stm2.setString(3, flightNo);
                stm2.setString(4, legNo);
                stm2.setTimestamp(5, getCurrentTimeStamp());
                
                stm3.setInt(1, nextResrNo);
                stm3.setInt(2, id);
                stm3.setString(3, accountNo);
                //I generated seatnumber by myself
                stm3.setString(4, "50E");
                stm3.setString(5, classType);
                //I generated meal by myself
                stm3.setString(6, "Fish and chips");
                
                stm.executeUpdate();
                stm2.executeUpdate();
                stm3.executeUpdate();
              
                System.out.println("Reserved");
                
                RequestDispatcher rd = request.getRequestDispatcher("/customer/existingreservation.jsp");
                rd.forward(request, response);
                }

               

                
                //ResultSet rs = stm.executeQuery();
                //ResultSetMetaData md = rs.getMetaData();
                
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        
        
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
