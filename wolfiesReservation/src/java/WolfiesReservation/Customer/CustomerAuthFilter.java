/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WolfiesReservation.Customer;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author 미소년
 */

public class CustomerAuthFilter implements Filter {

  private String contextPath;

  @Override
  public void init(FilterConfig fc) throws ServletException {
    contextPath = fc.getServletContext().getContextPath();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, 
                       FilterChain fc) throws IOException, ServletException {
   
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;  

    if (req.getSession().getAttribute("loginRank") != "customer") {
        String msg = "You must log in as a customer to view that page!";
        req.getSession(true).setAttribute("msg", msg);
        res.sendRedirect("/TravelAgencyWeb/index.jsp");
    } else {
        fc.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {
  }
}