/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;
/**
 *
 * @author Garret Leotta
 */
public class EmployeesByStart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void service(HttpServletRequest request, HttpServletResponse 
                        response) throws ServletException, IOException {
        //get form data
        String month = request.getParameter("month");
        String year = request.getParameter("year");
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
            String strQuery = "SELECT DISTINCT E.SSN, P.FirstName, "
                            + "P.LastName, P.Address, P.City, P.State, "
                            + "P.ZipCode, E.StartDate, E.HourlyRate FROM " 
                            + "Employee E,Person P WHERE E.Id = P.Id AND "
                            + "E.IsManager = 0 AND E.StartDate > ? ";
            String startDate = dateTimeConvert(month, year, false);
            PreparedStatement stm = conn.prepareStatement(strQuery);
            stm.setString(1, startDate);
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
            
            String queryType = "Employees or started in or after the month of"
                             + " " + numberToMonth(month) + " " + year + ":";
            request.setAttribute("query", queryType);
            request.setAttribute("columns", columns);
            request.setAttribute("results", results);
            RequestDispatcher rd = request.getRequestDispatcher(
                                           "/manager/employeelisting.jsp");
            rd.forward(request, response);
        } catch(Exception e ) {
            out.println(e);
            e.printStackTrace();
        }
            
        response.setContentType("text/html");
        out.println(msg);
    }
    
    /**
     * returns the start or end date of the given month and year in the format
     * of a SQL datetime: 'YYYY-MM-DD'
     */
    private String dateTimeConvert(String smonth, String syear, boolean start){
        int month = Integer.parseInt(smonth);
        int year  = Integer.parseInt(syear);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(start) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            //Java calendar is off 1
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            return sdf.format(cal.getTime());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            //Java calendar is off 1, so this is the month ahead of ours
            cal.set(Calendar.MONTH, month-1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DATE, -1);
            return sdf.format(cal.getTime());
        }
    }
    
    private String numberToMonth(String smonth) {
        int month = Integer.parseInt(smonth);
        switch (month) {
            case 1:  return "January";
            case 2:  return "February";
            case 3:  return "March";
            case 4:  return "April";
            case 5:  return "May";
            case 6:  return "June";
            case 7:  return "July";
            case 8:  return "August";
            case 9:  return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Invalid month";
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
        return "Customer Login Validation";
    }

}
