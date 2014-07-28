/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WolfiesReservation.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Youngseo Son
 */
public class EditCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     public void service(HttpServletRequest request, HttpServletResponse 
                        response) throws ServletException, IOException {
        String msg = "";
        //connection preliminaries
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        PrintWriter out = response.getWriter();
        String customerId = (String)request.getParameter("CustomerId");
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, dbUserName,   
                                            dbPassword);
            //Execute Query
            String strQuery = "SELECT P.Id, P.FirstName, P.LastName, C.CreditCardNo, C.Email, P.Address, P.City, P.State, P.ZipCode"
                            +" FROM Customer C, Person P"
                            +" WHERE C.Id = P.Id AND P.Id = ?";
            PreparedStatement stm = conn.prepareStatement(strQuery);
            stm.setString(1, customerId);
            ResultSet rs = stm.executeQuery();
            
            //Validate result set
            //Data structure to hold column names
      
            //Data structure to hold result set
            
            if(rs.next()) {
                request.setAttribute("Id", rs.getString("Id"));
                request.setAttribute("FirstName", rs.getString("FirstName"));
                request.setAttribute("LastName", rs.getString("LastName"));
                request.setAttribute("Email", rs.getString("Email"));
                request.setAttribute("Address", rs.getString("Address"));
                request.setAttribute("City", rs.getString("City"));
                request.setAttribute("State", rs.getString("State"));
                request.setAttribute("ZipCode", rs.getString("ZipCode"));
                request.setAttribute("CreditCardNo", rs.getString("CreditCardNo"));
                
            RequestDispatcher rd = request.getRequestDispatcher(
                                           "/employee/EditCustomer.jsp");
            rd.forward(request, response);
         
            }
            
            
            
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
            
        response.setContentType("text/html");
        out.println(msg);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
