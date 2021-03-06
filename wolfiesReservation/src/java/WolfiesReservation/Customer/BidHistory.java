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
public class BidHistory extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //get form data
        String flightNo = request.getParameter("auctFlightNumber");
        String seatClass = request.getParameter("auctClass");
        String airId = flightNo.substring(0, 2);
        String fltNo = flightNo.substring(2, 5);
        //JDBC connection
        Connection c = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        PrintWriter out = response.getWriter();
        try {
            Class.forName(driver).newInstance();
            c = DriverManager.getConnection(url + dbName, dbUserName, dbPassword);
            //Execute Query
            String strQuery = "SELECT * FROM Auctions WHERE AirlineID = ? AND "
                            + "FlightNo = ? AND Class = ? ORDER BY "
                            + "`AuctionDate` DESC";
            PreparedStatement stm = c.prepareStatement(strQuery);
            stm.setString(1, airId);
            stm.setString(2, fltNo);
            stm.setString(3, seatClass);
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
            String queryType = "Bidding History for a given Auction";  
            request.setAttribute("columns", columns);
            request.setAttribute("results", results);
            request.setAttribute("querytype", queryType);  
            
            RequestDispatcher rd = request.getRequestDispatcher("/customer/queryresults.jsp");
            rd.include(request, response);                
            response.setContentType("text/html");
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
    }
    
    
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
