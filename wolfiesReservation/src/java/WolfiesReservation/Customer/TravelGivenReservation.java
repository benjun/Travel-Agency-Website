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
 * @author 미소년
 */
public class TravelGivenReservation extends HttpServlet {

public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        //Get all the parameters from bookMeNow.jsp file

        //String accountNo = request.getParameter("accountNo");
        String reservationNo = request.getParameter("reservationNo");
        
        String msg = "";
                response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(msg);
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
                //get flight info
                //Execute Query
                String strQuery = "SELECT DISTINCT I.ResrNo, I.AirlineID, I.FlightNo, DA.Name, AA.Name ,L.DepTime, L.ArrTime "
                        + "FROM Includes I, Leg L, Airport DA, Airport AA WHERE I.AirlineID = L.AirlineID AND I.FlightNo = L.FlightNo "
                        + "AND L.DepAirportID = DA.Id AND L.ArrAirportID = AA.Id AND I.ResrNo = ?";
                String strQuery2 = "SELECT ResrNo FROM reservation WHERE AccountNo = ?";
                //String strQuery 2 = "select AccountNo from customer where AccountNo=?";
                //PreparedStatement stm2 = c.prepareStatement(strQuery2);
                PreparedStatement stm = c.prepareStatement(strQuery);
                PreparedStatement stm2 = c.prepareStatement(strQuery2);
                
                stm2.setObject(1, request.getSession(true).getAttribute("accountNo"));
                //stm2.setString(1, request.getSession(true).getAttribute("accountNo"));
                ResultSet rs1 = stm2.executeQuery();
                //int resrNo = 0;
                if(rs1.next()){
                    if(reservationNo.equals(rs1.getString(1))){
                        
                    
                    //reservationNo = rs1.getString(1);
                    stm.setString(1, reservationNo);
                ResultSet rs = stm.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                
                //Validate result set
                //Data structure to hold column names
                List<String> columns = new ArrayList<String>();
                int rowCount = md.getColumnCount();
                for (int i = 1; i <= rowCount; i++) {
                columns.add(md.getColumnName(i));
                }
                //Data structure to hold result set
                List<String[]> results = new ArrayList<String[]>();
                while(rs.next()) {
                String[] row = new String[rowCount];
                for (int i = 1; i <= rowCount; i++) {
                    row[i-1] = rs.getString(columns.get(i-1));
                }
                results.add(row);
                }
                String queryType = "Travel itinerary for a given reservation";  
                request.setAttribute("columns", columns);
                request.setAttribute("results", results);
                request.setAttribute("querytype", queryType);
                
                //request.getSession(true).setAttribute("querytype", queryType);                
                //request.setAttribute("AccountNo", accountNo);
                System.out.println(queryType);
                System.out.println("columns: " + columns);
                System.out.println("results: " + results);
                                RequestDispatcher rd = request.getRequestDispatcher("/customer/customerqueryresults.jsp");
                rd.forward(request, response);  
                    }
                  
                }
                RequestDispatcher rd = request.getRequestDispatcher("/customer/error.jsp");
                rd.forward(request, response);                
                

                
            }
            catch(Exception e ){
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

}
