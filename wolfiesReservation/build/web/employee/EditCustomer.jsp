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
<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Customer Addition</h3>
            </div>
            <div class="panel-body">
                <form name="EditCustomerTransactionForm" action="EditCustomerTransaction" method="post">
                    <input type="hidden" name="CustomerId" value="<%=request.getParameter("CustomerId")%>"/>
              <table border="0">
            <tbody>                
                <tr><td><label>First Name:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonFirstName" value="<%=request.getAttribute("FirstName")%>"/></td>
                </tr>
                <tr><td><label>Last Name:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonLastName" value="<%=request.getAttribute("LastName")%>"/></td>
                </tr>
                <tr><td><label>Address:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonAddress" value="<%=request.getAttribute("Address")%>"/></td>
                </tr>
                <tr><td><label>City:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonCity" value="<%=request.getAttribute("City")%>"/></td>
                </tr>
                <tr><td><label>State:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonState" value="<%=request.getAttribute("State")%>"/></td>
                </tr>
                <tr><td><label>Zip Code:</label></td>
                    <td><input class="textbox" type="text" maxlength="7" name="PersonZipCode" value="<%=request.getAttribute("ZipCode")%>"/></td>
                </tr>
                <tr><td><label>Credit Card Number:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="CustomerCreditCardNo" value="<%=request.getAttribute("CreditCardNo")%>"/></td>
                </tr>
                <tr><td><label>E-mail:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="CustomerEmail" value="<%=request.getAttribute("Email")%>"/></td>
                </tr>     
      </tbody>
        </table>      
              <button type="submit" class="btn btn-lg btn-default">Change</button> 
              </form>
            </div>
          </div>
</body>
</html>