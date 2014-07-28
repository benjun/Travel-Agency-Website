<%-- 
    Document   : customer navbar
    Created on : Apr 11, 2014, 11:10:50 AM
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/style_1.css" />

    <nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
                <% 
//                    String an =(String)request.getAttribute("AccountNo");
//                    String id = (String)request.getAttribute("id");
                    //request.setAttribute("AccountNo", an);
                    //request.setAttribute("id", id);
                    //String id =(String)session.getAttribute("id");
                    String ano = (String)session.getAttribute("accountNo");
                    String idd = (String)session.getAttribute("loginId");
                    String firstName = (String)session.getAttribute("firstName");
                    String rank = (String)session.getAttribute("loginRank");
                    String queryType = (String)session.getAttribute("querytype");
              %>        
    <img src="/TravelAgencyWeb/images/logo.png" class="navbar-left">
        
    <% out.println("<a class='navbar-brand' href='http://localhost:8089/TravelAgencyWeb/CustomerLogin?accountNo=" + ano  + "&loginId=" + idd +  "'>Wolfie's Reservations");%>:: Hello, <%= session.getAttribute("firstName") %>! </a>
    
  </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <a href="/TravelAgencyWeb/logout.jsp" class="navbar-right"> <img src="/TravelAgencyWeb/images/logout.png"></a>
    <ul class="nav navbar-nav">
      <li class="dropdown">
          
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Customer 
            
        <b class="caret"></b></a>
        <ul class="dropdown-menu">

         <% out.println("<li><a href='CancelCustomerReservation?accountNo=" + ano  + "&loginId=" + idd + "'>Cancel my reservation</a></li>");%>
          <%// out.println("<li><a href='bookMe?accountNo=" + ano  + "&loginId=" + idd + "'>Book Me Now</a></li>");%>
          <li><a href="/TravelAgencyWeb/customer/bookmenow.jsp">Book Me Now</a></li>
          <li><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Reservation Info</a></li>         
          <li><a href="/TravelAgencyWeb/customer/auctions.jsp">Auction</a></li>                   
          <li class="divider"></li>
          <li><a href="/TravelAgencyWeb/customer/helpMe.jsp">Help me</a></li> 
        </ul>
      </li>
    </ul>
  </div>
</nav>
<div id="footer">
  <p>Copyright@ Wolfies' Reservation.  Contact information: <a href="mailto:minchan.jun@stonybrook.edu">
  minchan.jun@stonybrook.edu</a>.</p>
</div>
</html>