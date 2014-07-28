/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Customer;

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
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author 미소년
 */
public class FindFlights extends HttpServlet {

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
        String here  = request.getParameter("DepAirportID");
        String there = request.getParameter("ArrAirportID");
        String day   = request.getParameter("day");
        String month = request.getParameter("month");
        String year  = request.getParameter("year");
        String startDay = getStartEndDay(day, month, year, true);
        String endDay   = getStartEndDay(day, month, year, false);
        //connection preliminaries
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, dbUserName,   
                                            dbPassword);
            //Execute Query
            String strQuery = "select * from Leg where DepTime > ? and ArrTime"
                            + " < ? ";
            PreparedStatement stm = conn.prepareStatement(strQuery);
            stm.setString(1, startDay);
            stm.setString(2, endDay);
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
            //Process Query -- Create List of eligible flight paths
            //Forward request
            String queryType = "Possible Travel Paths: ";
            request.setAttribute("query", queryType);
            request.setAttribute("columns", columns);
            request.setAttribute("results", flightTracker(here,there,results));
            RequestDispatcher rd = request.getRequestDispatcher(
                                           "/customer/flightdata2.jsp");
            rd.forward(request, response);
        } catch(Exception e ) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
    }
    
    /**
     *
     */
    private List<List<String[]>> flightTracker(String here, String there, 
                                               List<String[]> legList) {
        List<List<String[]>> results = new ArrayList<List<String[]>>();
        for(String[] leg : legList) {
            //If leg starts from here
            if(leg[3].equals(here)) {
                //make a new flight path, add it to head
                if(leg[4].equals(there)) {
                    //If base case, add completed path to results
                    List<String[]> newPath = new ArrayList<String[]>();
                    newPath.add(leg);
                    results.add(newPath);
                } else {
                    //If not base case, must recurse and find other eligible 
                    //flight paths to add. (NOTE: returned flight paths need
                    //this leg to be appended to head
                    for(List<String[]> recPaths : flightTracker(leg[4],
                                                         there, legList)) {
                        List<String[]> newPath = new ArrayList<String[]>();
                        newPath.add(leg);
                        newPath.addAll(recPaths);
                        results.add(newPath);
                    }
                }
            }
        }
        return results;
    }
    /**
     * Converts day, month, year values into SQL ready values for our query
     */
    private String getStartEndDay(String sday, String smonth, String syear, 
                                  boolean start) {
        int day   = Integer.parseInt(sday);
        int month = Integer.parseInt(smonth);
        int year  = Integer.parseInt(syear);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        if(start) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            //Java calendar is off 1
            cal.set(Calendar.MONTH, month-1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.add(Calendar.DATE, -1);
            return sdf.format(cal.getTime());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            //Java calendar is off 1
            cal.set(Calendar.MONTH, month-1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.add(Calendar.DATE, 1);
            return sdf.format(cal.getTime());
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