package WolfiesReservation.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Minchan Jun
 */
public class BidAuction extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //get form data
        int index = Integer.parseInt(request.getParameter("index"));
        String[] results = ((List<String[]>)request.getSession().getAttribute("results")).get(index);
        float bid = Float.parseFloat(request.getParameter("bid"+index));
        String airId  = results[1];
        String fltNo  = results[2];
        String sClass = results[3];
        String auDate = results[4];
        String accountNo = (String)request.getSession().getAttribute("accountNo");
        String msg = "";
        //JDBC connection
        Connection c = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "travelagency";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "root";
        try {
            Class.forName(driver).newInstance();
            c = DriverManager.getConnection(url + dbName, dbUserName, dbPassword);
            
            //Queries
            String selectAuctQuery = "SELECT nyop from Auctions where "
                                   + "airlineid = ? and flightno = ? and " 
                                   + "class = ? and auctiondate = ?";
            PreparedStatement selAucStm = c.prepareStatement(selectAuctQuery);
            selAucStm.setString(1, airId);
            selAucStm.setString(2, fltNo);
            selAucStm.setString(3, sClass);
            selAucStm.setString(4, auDate);
            ResultSet rs = selAucStm.executeQuery();
            boolean highest = false;
            if(rs.next()) {
                float highBid = Float.parseFloat(rs.getString("nyop"));
                if(bid > highBid) {
                    highest = true;
                } else {
                    highest = false;
                }
            }
            
            if(highest) {
                String insAucQuery = "INSERT INTO Auctions (AccountNo, "
                                   + "AirlineId, FlightNo, Class, " 
                                   + "AuctionDate, NYOP, Accepted) VALUES (?, "
                                   + "?, ?, ?, NOW(), ?, 0)";
                PreparedStatement insAucStm = c.prepareStatement(insAucQuery);
                insAucStm.setString(1, accountNo);
                insAucStm.setString(2, airId);
                insAucStm.setString(3, fltNo);
                insAucStm.setString(4, sClass);
                insAucStm.setString(5, String.format("%.2f%n", bid));
                insAucStm.executeUpdate();
                RequestDispatcher rd = request.getRequestDispatcher("/customer/auctions.jsp");
                out.println("<font color='red'>Successfully Bid On Auction</font>");
                rd.include(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/customer/auctions.jsp");
                out.println("<font color='red'>Your Bid was Too Low</font>");
                rd.include(request, response);
            }
        } catch(Exception e) {
            out.println(e);
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
        //processRequest(request, response);
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
        //processRequest(request, response);
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

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
}
