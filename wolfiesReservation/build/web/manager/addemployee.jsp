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
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/style.css" />
   <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/logincss/textbox.css" />
  <script src="/TravelAgencyWeb/js/bootstrap.min.js"></script>
  <script>
    $(function() {
    $( "#accordion" ).accordion();
    });
    
    function checkForm(oForm) {
      var filled = 0;
      for (var i = 0; i < oForm.elements.length; i++) {
        if (GetElementValue(oForm.elements[i]).length > 0)
          filled++;
      }
      if(filled!=oForm.elements.length) {
        alert("Please Fill Form Completely");
        return false;
      }
    }

    function GetElementValue(element) {
      if ((element.type === "checkbox" || element.type === "radio") && 
           element.checked === false)
        return "";
      return element.value;
    }
  </script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<h2>Add New Employee</h2>
<form name="AddEmployeeForm" action="/TravelAgencyWeb/manager/AddEmployee" method="post">
  <table border="0">
    <tr><td><font color="red">All Fields Required</font></td></tr>
    <tr><td><label>First Name:</label></td>
      <td><input class="textbox" type="text" maxlength="50" name="firstName"/></td>
    </tr>
    <tr><td><label>Last Name:</label></td>
      <td><input class="textbox" type="text" maxlength="50" name="lastName"/></td>
    </tr>
    <tr><td><label>Address:</label></td>
      <td><input class="textbox" type="text" maxlength="100" name="address"/></td>
    </tr>
    <tr><td><label>City:</label></td>
      <td><input class="textbox" type="text" maxlength="50" name="city"/></td>
    </tr>
    <tr><td><label>State:</label></td>
      <td><input class="textbox" type="text" maxlength="50" name="state"/></td>
    </tr>
    <tr><td><label>Zip Code:</label></td>
      <td><input class="textbox" type="text" maxlength="5" name="zipCode"/></td>
    </tr>
    <tr><td><label>SSN</label></td>
      <td><input class="textbox" type="text" maxlength="9" name="ssn"/></td>
    </tr>
    <tr><td><label>Hourly Rate</label></td>
      <td><input class="textbox" type="text" maxlength="10" name="hourlyRate"/></td>
    </tr>
    <tr><td><label>Manager?</label></td>
      <td><input class="textbox" type="text" maxlength="1" name="isManager"/></td>
    </tr>
  </table>
  <input type="submit" name="submit" value="Add"/>
</form>

</body>
</html>
