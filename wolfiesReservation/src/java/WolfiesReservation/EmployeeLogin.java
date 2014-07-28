/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Garret Leotta
 */
public class EmployeeLogin extends HttpServlet {

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
        
        //get form data
        String ssn = request.getParameter("ssn");
        String msg = "";
        //connection preliminaries
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
            String strQuery = "select * from Employee where SSN= ? ";
            String strQuery2 = "SELECT P.FirstName, P.Id FROM Person P, "
                             + "Employee E WHERE P.Id = E.Id AND E.SSN = ?";
            PreparedStatement stm = conn.prepareStatement(strQuery);
            PreparedStatement stm2 = conn.prepareStatement(strQuery2);
            stm.setString(1, ssn);
            stm2.setString(1, ssn);
            ResultSet rs = stm.executeQuery();
            ResultSet rs2 = stm2.executeQuery();
            //Validate result set
            if(rs.next()) {
                if(rs.getInt("IsManager")==1) {
                    if(rs2.next()) {
                        request.getSession(true).setAttribute("loginId",
                                                         rs2.getString("Id"));
                        request.getSession(true).setAttribute("firstName",
                                                  rs2.getString("FirstName"));
                        request.getSession(true).setAttribute("loginRank",
                                                              "manager");
                        response.sendRedirect("manager/index.jsp");
                    }
                } else {
                    if(rs2.next()) {
                        request.getSession(true).setAttribute("EmployeeId",
                                                         rs2.getString("Id"));
                        request.getSession(true).setAttribute("EmployeeName",
                                                  rs2.getString("FirstName"));
                        request.getSession(true).setAttribute("loginRank",
                                                              "employee");
                        request.getSession(true).setAttribute("EmployeeSSN",
                                                              ssn);
                        response.sendRedirect("employee/index.jsp");
                    }
                }
            } else {
                msg = "Log-in Failed, invalid SSN";
                request.getSession(true).setAttribute("msg", msg);
                response.sendRedirect("index.jsp");
            }
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
    }
    
    //Edit these
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
        return "Employee Login Validation";
    }

}
