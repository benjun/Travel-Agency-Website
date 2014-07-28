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
public class DeleteCustomerReservation extends HttpServlet {

           public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String resrNo = request.getParameter("ResrNo");
        
        out.println(resrNo);
        
        
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
                String strQuery2 = "DELETE FROM includes WHERE ResrNo =?";
                String strQuery3 = "DELETE FROM reservationpassenger WHERE ResrNo =?";
                String strQuery = "DELETE FROM reservation WHERE ResrNo = ?";
                
                PreparedStatement stm2 = c.prepareStatement(strQuery2);
                PreparedStatement stm3 = c.prepareStatement(strQuery3);
                PreparedStatement stm = c.prepareStatement(strQuery);

                stm2.setString(1,resrNo);
                stm3.setString(1,resrNo);                
                stm.setString(1, resrNo);

                
//                ResultSet rs = stm.executeQuery();
//                ResultSet rs2 = stm2.executeQuery();
//                ResultSet rs3 = stm3.executeQuery();
//                
//                if(rs.next() && rs2.next() && rs3.next()){
//                
//                stm.executeUpdate();
//                stm2.executeUpdate();
//                stm3.executeUpdate();
//                
//                }
                  stm2.executeUpdate();
                  stm3.executeUpdate();                
                  stm.executeUpdate();

                RequestDispatcher rd = request.getRequestDispatcher("/customer/cancelcomplete.jsp");
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
