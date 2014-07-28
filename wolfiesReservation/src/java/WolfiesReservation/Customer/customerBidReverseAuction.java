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
public class customerBidReverseAuction extends HttpServlet {

  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        //Get all the parameters from bookMeNow.jsp file

        //String accountNo = request.getParameter("accountNo");
        
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
                String strQuery = "SELECT * FROM Auctions WHERE AccountNo = ? AND AirlineID = ? AND FlightNo = ? AND Class = ? ORDER BY `Date` DESC LIMIT 0,1";
                String strQuery2 = "SELECT P.Class, I.AirlineID, I.FlightNo FROM reservationpassenger P, includes I WHERE P.AccountNo = ? AND P.ResrNo = I.ResrNo";
                //String strQuery 2 = "select AccountNo from customer where AccountNo=?";
                //PreparedStatement stm2 = c.prepareStatement(strQuery2);
                PreparedStatement stm = c.prepareStatement(strQuery);
                PreparedStatement stm2 = c.prepareStatement(strQuery2);
                
                stm2.setObject(1, request.getSession(true).getAttribute("accountNo") );
                //stm2.setString(1, accountNo);
                
                ResultSet rs2 = stm2.executeQuery();
                if(rs2.next()){                
                    String classType = rs2.getString(1); // Getting First for no reason instead of getting Economy
                    String airlineID = rs2.getString(2); // Getting JA for no reason instead of getting First
                    String flightNo = rs2.getString(3);
//                    out.println(classType);
//                    out.println(airlineID);
//                    out.println(flightNo);
                    String check1 = "AA";
                    String check2 = "Economy";
                    
                    
                    //stm.setString(1, accountNo);
                    stm.setObject(1, request.getSession(true).getAttribute("accountNo") );
                    stm.setString(2, check1);
                    stm.setString(3, flightNo);
                    stm.setString(4, check2);

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
                String queryType = "A customer's current bid";  
                request.setAttribute("columns", columns);
                request.setAttribute("results", results);
                request.setAttribute("querytype", queryType);  
                
                //request.getSession(true).setAttribute("columns", columns);
                //request.getSession(true).setAttribute("results", results);
                //request.getSession(true).setAttribute("querytype", queryType);                
                }
                
                
                RequestDispatcher rd = request.getRequestDispatcher("/customer/customerqueryresults.jsp");
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
