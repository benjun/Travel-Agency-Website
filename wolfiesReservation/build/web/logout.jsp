<%-- 
    Document   : logout
    Created on : May 7, 2014, 8:44:02 PM
    Author     : 미소년
--%>
<%@page import= "java.util.*;" %>
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
        <% session.invalidate(); %>
        <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Your log-in status</h3>
            </div>
            <div class="panel-body">
                You logged out successfully!      
              <button type="button" class="btn btn-lg btn-default" onclick="window.location.href='/TravelAgencyWeb/index.jsp'">Go back to main menu</button>              
            </div>
          </div>        
    </body>
</html>
    </body>
</html>
