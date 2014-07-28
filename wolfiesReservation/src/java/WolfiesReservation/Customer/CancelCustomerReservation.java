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
public class CancelCustomerReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String accountNo = request.getParameter("accountNo");
        String id = request.getParameter("loginId");
        //int acn = Integer.parseInt(accountNo);
        
        
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
                
                String strQuery = "SELECT R.ResrNo,R.ResrDate, R.BookingFee,R.TotalFare,R.AccountNo,I.AirlineID,I.FlightNo FROM reservation R, includes I, reservationpassenger P WHERE R.AccountNo = ? AND P.AccountNo =? AND R.ResrNo = I.ResrNo AND I.ResrNo =P.ResrNo AND P.ResrNo= R.ResrNo";
                //String strQuery2 = "SELECT AccountNo FROM customer";
                
                PreparedStatement stm = c.prepareStatement(strQuery);
                //PreparedStatement stm2 = c.prepareStatement(strQuery2);
                //ResultSet rs1 = stm2.executeQuery();
                //int AccountNo = 0;
                
                   // AccountNo = rs1.getInt(1);
                    stm.setString(1, accountNo);
                    stm.setString(2, accountNo);
                
                
                
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
                String queryType = "Choose your reservation results to cancel";  
                request.setAttribute("columns", columns);
                request.setAttribute("results", results);
                request.setAttribute("querytype", queryType); 
                
                //request.getSession(true).setAttribute("loginId", id);
                
                RequestDispatcher rd = request.getRequestDispatcher("/customer/reservationliststocancel.jsp");
                rd.forward(request, response);                
                
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