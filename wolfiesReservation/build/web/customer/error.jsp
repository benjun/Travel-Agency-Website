<%--
    Document   : customer
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
<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Customer's current reservation</h3>
            </div>
            <div class="panel-body">
                Your reservation number doesn't exist.      
              <button type="button" class="btn btn-lg btn-default" onclick="window.location.href='reservationInfo.jsp'">Go back</button>              
            </div>
          </div>
</body>
</html>