<%-- 
    Document   : index
    Created on : Apr 10, 2014, 2:17:59 PM
    Author     : index.jsp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang='en'>
<head>
    <meta charset="UTF-8" /> 
    <title>
        Login
    </title>
    
    <link rel="stylesheet" href="logincss/style.css">
  
</head>
<body>
<font color="red">${msg}</font>
<%session.removeAttribute("msg");%>


  <section class="container">
    <div class="login">
      <h1><img src="/TravelAgencyWeb/images/logo.png"> Woflie's Reservation  </h1>
      Login As Customer    
      <form name="customerLogin" action="/TravelAgencyWeb/CustomerLogin" method="post">
          Enter Account Number: <input type="text" name="accountNo" placeholder="ex:1008">
        <button value="dummy" type="submit">Login</button>
      </form>
      <br>
      Login As Employee/Manager
      <form name="employeeLogin" action="/TravelAgencyWeb/EmployeeLogin" method="post" >
        Enter SSN: <input type="text" name="ssn" placeholder="ex:12345678">
        <button value="dummy" type="submit">Login</button>
    </div>

  </section>

</body>
</html>