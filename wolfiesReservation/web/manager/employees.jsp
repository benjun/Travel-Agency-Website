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

<h2>Employee Management</h2>
<h4>Find an Employee By:</h4>
<div id="accordion">
  <h3>Name</h3>
  <div>
    <p>
      
    </p>
    <form name="employbyname" action="EmployeesByName" method="post">
      Full Name: <input class="textbox" type="text" name="employeeName">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  
  <h3>SSN</h3>
  <div>
    <p>
      
    </p>
    <form name="employbyssn" action="EmployeesByNumber" method="post">
      SSN: <input class="textbox" type="text" name="employeeSSN">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  
  <h3>Start Date</h3>
  <div>
    <p>
      
    </p>
    <form name="employbydate" action="EmployeesByStart" method="post" 
          onsubmit="return checkForm(this);">
      <select name='month'>
        <option selected value=''>Month</option>
        <option value='1'>January</option>
        <option value='2'>February</option>
        <option value='3'>March</option>
        <option value='4'>April</option>
        <option value='5'>May</option>
        <option value='6'>June</option>
        <option value='7'>July</option>
        <option value='8'>August</option>
        <option value='9'>September</option>
        <option value='10'>October</option>
        <option value='11'>November</option>
        <option value='12'>December</option>
      </select>
      <select  name='year'>
        <option selected value=''>Year</option>
        <% int max = 15;
           for(int i=0;i<=max;i++) { %>
        <option value='<%= 2000+i%>'><%= 2000+i%></option>
        <% } %>
      </select>
      <button value="dummy" type="submit">Send</button>
    </form>
  </div>
  
  <h3>Comprehensive Listing</h3>
  <div>
    <p>
      
    </p>
    <form name="compemploylist" action="ComprehensiveEmployees" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
</div>
<h4><a href='addemployee.jsp'>Add a New Employee</a></h4>


</body>
</html>
