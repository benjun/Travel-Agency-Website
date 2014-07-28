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
      $("#accordion").accordion({
        active: parseInt(getAnchor(location.href)),
        heightStyle: "content"
      });
    });
    
    function getAnchor(url)
    {
      var index = url.lastIndexOf('#');
      if (index != -1)
        return url.substring(index+1);
    }
    
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

<h2>Revenue Reports</h2>
<div id="accordion">
  <h3>Monthly Report</h3>
  <div class="content">
    <p>
      Obtain a sales report for a particular month.
    </p>
    <form name="monthlyreport" action="/TravelAgencyWeb/manager/MonthlyReport" method="post" 
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
      <select name='year'>
        <option selected value=''>Year</option>
        <% int max = new java.util.Date().getYear() + 1900 - 2011;
           for(int i=0;i<=max;i++) { %>
        <option value='<%= 2011+i%>'><%= 2011+i%></option>
        <% } %>
      </select>
      <button value="dummy" type="submit">Send</button>
    </form>
  </div>
  
  <h3>Reservation Listing</h3>
  <div class="content">
    <p>
      Produce a list of reservations by flight number or by customer name.
    </p>
    <form name="reservbyflight" action="/TravelAgencyWeb/manager/ReservationsByFlight" method="post">
      Flight Number: <input class="textbox" type="text" name="reservFlightNumber">
      <button value="dummy" type="submit">Query</button>
    </form>
    or
    <form name="reservbycust" action="/TravelAgencyWeb/manager/ReservationsByCustomer" method="post">
      Customer Name: <input class="textbox" type="text" name="reservCustomerName">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  
  <h3>Revenue Summary</h3>
  <div class="content">
    <p>
      Produce a summary listing of revenue generated by a particular flight, 
      destination city, or customer
    </p>
    <form name="summbyflight" action="/TravelAgencyWeb/manager/SummaryByFlight" method="post">
      Flight Number: <input class="textbox" type="text" name="summFlightNumber">
      <button value="dummy" type="submit">Query</button>
    </form>
    or
    <form name="summbycity" action="/TravelAgencyWeb/manager/SummaryByCity" method="post">
      City Name: <input class="textbox" type="text" name="summCityName">
      <button value="dummy" type="submit">Query</button>
    </form>
    or
    <form name="summbycust" action="/TravelAgencyWeb/manager/SummaryByCustomer" method="post">
      Account Number: <input class="textbox" type="text" name="summAccountNo">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  
  <h3>Top Employee</h3>
  <div class="content">
    <p>
      Determine which customer representative generated most total revenue
    </p>
    <form name="eotm" action="/TravelAgencyWeb/manager/Eotm" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  
  <h3>Top Customer</h3>
  <div class="content">
    <p>
      Determine which customer generated most total revenue
    </p>
    <form name="cotm" action="/TravelAgencyWeb/manager/Cotm" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
</div>
</body>
</html>