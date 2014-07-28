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
public class DeleteCustomerList extends HttpServlet {

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
        String personFirstName = (String)request.getParameter("PersonFirstName");
        String personLastName = (String)request.getParameter("PersonLastName");
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, dbUserName,   
                                            dbPassword);
            //Execute Query
            String strQuery = "SELECT P.Id, P.FirstName, P.LastName, C.Email, P.Address, P.City, P.State, P.ZipCode"
                            +" FROM Customer C, Person P"
                            +" WHERE C.Id = P.Id AND P.FirstName = ? AND P.LastName = ?";
            PreparedStatement stm = conn.prepareStatement(strQuery);
            stm.setString(1, personFirstName);
            stm.setString(2, personLastName);
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
            
            String queryType = "Customer List: ";
            request.setAttribute("query", queryType);
            request.setAttribute("columns", columns);
            request.setAttribute("results", results);
            RequestDispatcher rd = request.getRequestDispatcher(
                                           "/employee/DeleteList.jsp");
            rd.forward(request, response);
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
            
        response.setContentType("text/html");
        out.println(msg);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
        processRequest(request, response);
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
        processRequest(request, response);
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
