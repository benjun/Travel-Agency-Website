<%-- 
    Document   : existingReservation
    Created on : Apr 24, 2014, 10:28:25 PM
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
    <h2>Your reservation has been completed. Thank you</h2>
    
    <!-- use either session or request to get information -->
    <form action="customer.jsp">
        <input type="submit" value="go back to main menu">
    </form>
</body>
</html>
