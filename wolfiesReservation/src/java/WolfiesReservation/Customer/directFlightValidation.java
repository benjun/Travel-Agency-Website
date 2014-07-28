/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 미소년
 */
public class directFlightValidation extends HttpServlet {

public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

            String msg = "";
            String accountNo = request.getParameter("accountNo");
            String id = request.getParameter("id");

            int x =0;
            try{
                request.setAttribute("AccountNo", accountNo);
                request.setAttribute("id", id);
                
                request.getSession(true).setAttribute("AccountNo", accountNo);
                request.getSession(true).setAttribute("id", id);

                RequestDispatcher rd = request.getRequestDispatcher("/customer/bookmenow.jsp");
                rd.forward(request, response);

            }
            catch(Exception e ){
            e.printStackTrace();
            }
        

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(msg);
        
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
