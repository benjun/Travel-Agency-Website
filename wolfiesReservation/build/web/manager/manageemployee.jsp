<%--
    Document   : manager
    Created on : Apr 11, 2014, 11:10:50 AM
    Author     :
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/style.css" />
   <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/logincss/textbox.css" />
  <script src="/TravelAgencyWeb/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Employee Details</h3>
  </div>
  <div class="panel-body">
    <form name="EditEmployeeForm" action="EditEmployee" method="post">
      <input type="hidden" name="pid" value="<%=request.getAttribute("pid")%>"/>
      <table border="0">
        <tr><td><label>First Name:</label></td>
          <td><input class="textbox" type="text" maxlength="50" name="firstName" value="<%=request.getAttribute("FirstName")%>"/></td>
        </tr>
        <tr><td><label>Last Name:</label></td>
          <td><input class="textbox" type="text" maxlength="50" name="lastName" value="<%=request.getAttribute("LastName")%>"/></td>
        </tr>
        <tr><td><label>Address:</label></td>
          <td><input class="textbox" type="text" maxlength="100" name="address" value="<%=request.getAttribute("Address")%>"/></td>
        </tr>
        <tr><td><label>City:</label></td>
          <td><input class="textbox" type="text" maxlength="50" name="city" value="<%=request.getAttribute("City")%>"/></td>
        </tr>
        <tr><td><label>State:</label></td>
          <td><input class="textbox" type="text" maxlength="50" name="state" value="<%=request.getAttribute("State")%>"/></td>
        </tr>
        <tr><td><label>Zip Code:</label></td>
          <td><input class="textbox" type="text" maxlength="5" name="zipCode" value="<%=request.getAttribute("ZipCode")%>"/></td>
        </tr>
        <tr><td><label>Hourly Rate</label></td>
          <td><input class="textbox" type="text" maxlength="9" name="hourlyRate" value="<%=request.getAttribute("HourlyRate")%>"/></td>
        </tr>
        <tr><td><label>Manager Status</label></td>
          <td><input class="textbox" type="text" maxlength="4" name="isManager" value="<%=request.getAttribute("IsManager")%>"/></td>
        </tr>
      </table>
    <button type="submit" class="btn btn-lg btn-default">Change</button> 
    </form>
  </div>
</div>

</table>
</body>
</html>
