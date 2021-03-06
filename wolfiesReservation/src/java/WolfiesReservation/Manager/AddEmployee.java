/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WolfiesReservation.Manager;

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

public class AddEmployee extends HttpServlet {

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
                        
        String firstName = (String)request.getParameter("firstName");
        String lastName = (String)request.getParameter("lastName");
        String address = (String)request.getParameter("address");
        String city = (String)request.getParameter("city");
        String state = (String)request.getParameter("state");
        String zipCode = (String)request.getParameter("zipCode");
        String hourlyRate = (String)request.getParameter("hourlyRate");
        String ssn = (String)request.getParameter("ssn");
        String isManager = (String)request.getParameter("isManager");
        
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        PrintWriter out = response.getWriter();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, dbUserName,   
                                            dbPassword);
            //Execute Query
            String strQuery = "INSERT INTO Person (FirstName, LastName, " 
                            + "Address, City, State, ZipCode) VALUES (?, ?, " + "?, ?, ?, ?)";
            
            PreparedStatement stm = conn.prepareStatement(strQuery);
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, address);
            stm.setString(4, city);
            stm.setString(5, state);
            stm.setString(6, zipCode);
            stm.executeUpdate();
            
            strQuery = "SELECT Id FROM Person WHERE FirstName = ? AND "
                     + "LastName = ? AND Address = ? AND City = ? AND State ="+ " ? AND ZipCode = ?";
            stm = conn.prepareStatement(strQuery);
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, address);
            stm.setString(4, city);
            stm.setString(5, state);
            stm.setString(6, zipCode);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                out.println("got into the if");
                String personId = rs.getString(1);
                strQuery = "INSERT INTO Employee (Id, SSN, HourlyRate, "
                         + "isManager, StartDate) VALUES (?, ?, ?, ?, NOW())";
                stm = conn.prepareStatement(strQuery);
                out.println("should you?");
                stm.setString(1, personId);
                stm.setString(2, ssn);
                stm.setString(3, hourlyRate);
                stm.setString(4, isManager);
                out.println("will you?");
                stm.executeUpdate();
                out.println("did you?");
                RequestDispatcher rd = request.getRequestDispatcher("/manager/editsuccess.jsp");
                rd.forward(request, response);
                out.println("are you?");
            }
            out.println("didnt reach the if");
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
            
        response.setContentType("text/html");
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
