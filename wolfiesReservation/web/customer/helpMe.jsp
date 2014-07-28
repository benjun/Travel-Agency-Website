<%-- 
    Document   : helpMe
    Created on : May 7, 2014, 11:00:51 AM
    Author     : 미소년
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://code.jquery.com/jquery.js"></script>
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/style.css" />
   <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/logincss/textbox.css" />
  <script src="/TravelAgencyWeb/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>
       

<h2>Customer Help Page</h2>
<h3>How do I?</h3>
<p>
  <ul>
    <li><h4>Cancel customer's reservation:</h4><p>
      The <strong><% out.println("<a href='CancelCustomerReservation?accountNo=" + ano  + "&loginId=" + idd + "'>Cancel my reservation</a>");%></strong> 
      page contains the means to search for customer's reservations according to their own account number 
      in the database. After finding the reservations that a customer is looking for, a customer can 
      cancel his/her information. Only customers can be added on this page.
    </p></li>
    <li><h4>Book customer's reservation:</h4><p>
      The <strong><% out.println("<a href='bookMe?accountNo=" + ano  + "&loginId=" + idd + "'>Book Me Now</a>");%> </strong> 
       page will produce reservations that are available according to their account number. This will show all the indirect flights
       and direct flights. After customers book their flights, data will be added to database system.
    </p></li>
    <li><h4>Customer's current reservation:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Current reservation</a> </strong> 
      Produce a list of current reservations by account number.
    </p></li>
    <li><h4>Travel itinerary given reservation:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Current reservation</a> </strong> 
      Produce a list of current reservations by reservation number and customer's account number.
    </p></li>    
    <li><h4>Customer current bid on a given reverse auction:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/auctions.jsp">Current reservation</a> </strong> 
      An auction is identified by (AccountNo, AirlineID, FlightNo, Class). Current bid is the latest bid based on date time.
    </p></li>
    <li><h4>Bid history for a reverse auction:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/auctions.jsp">Current reservation</a> </strong> 
      Produce list of bid history and bid amount you want.
    </p></li>
    <li><h4>Reservation history:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Current reservation</a> </strong> 
      Produce a list of reservation history
    </p></li>
    <li><h4>Best seller list of flight:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Current reservation</a> </strong> 
      Produce a list of best seller flight
    </p></li>
    <li><h4>Personalized flight suggestion list:</h4><p>
      The <strong><a href="/TravelAgencyWeb/customer/reservationInfo.jsp">Current reservation</a> </strong> 
      Produce a list of personalized flight suggestion list
    </p></li>
 
  </ul>
</p>

</body>
</html>