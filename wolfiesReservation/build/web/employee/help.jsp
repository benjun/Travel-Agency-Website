<%-- 
    Document   : manager
    Created on : Apr 11, 2014, 11:10:50 AM
    Author     : 
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

<h2>Emloyee Help Page</h2>
<h3>How do I?</h3>
<p>
  <ul>
    <li><h4>Add, Edit and Delete information for employees:</h4>
      <strong><a href="/TravelAgencyWeb/employee/ManageCustomer.jsp">Customer Information</a></strong>
    </li>
    <li><h4>Make a reservation for customers:</h4>
       <strong><a href="/TravelAgencyWeb/employee/RecordAReservation.jsp">Record a Reservation</a></strong>
    </li>
    <li><h4>Produce a mailing list of customers:</h4>
      <strong><a href="/TravelAgencyWeb/Eeployee/MailingList">Maling List</a></strong>
    </li>
    <li><h4>Make a flight suggestion to customers: 
    </h4>
      <strong><a href="/TravelAgencyWeb/employee/FlightSuggestionList">Flight Suggestion</a></strong>
    </li>    
  </ul>
</p>

</body>
</html>