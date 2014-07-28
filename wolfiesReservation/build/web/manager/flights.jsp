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

<h2>Flight Listings</h2>
<div id="accordion">
  <h3>Comprehensive Listing</h3>
  <div>
    <p>
      Produce a comprehensive listing of all flights
    </p>
    <form name="comp" action="/TravelAgencyWeb/manager/ComprehensiveFlights" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  <h3>Most Active Flights</h3>
  <div>
    <p>
      Produce a list of most active flights
    </p>
    <form name="activeflights" action="/TravelAgencyWeb/manager/ActiveFlights" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  <h3>Customer Listing By Flight</h3>
  <div>
    <p>
      Produce a list of all customers who have seats reserved on a given flight
    </p>
    <form name="custbyflight" action="/TravelAgencyWeb/manager/CustomersByFlight" method="post">
      Flight Number: <input class="textbox" type="text" name="custFlightNumber">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  <h3>Flight Listing By Airport</h3>
  <div>
    <p>
      Produce a list of all flights for a given airport
    </p>
    <form name="flightsbyport" action="/TravelAgencyWeb/manager/FlightsByAirport" method="post">
      Airport Name: <input class="textbox" type="text" name="airportName">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
  <h3>List Flights by Delays</h3>
  <div>
    <p>
      Produce a list of all flights whose arrival and departure times are 
      on-time/delayed
    </p>
    <form name="delayed" action="/TravelAgencyWeb/manager/DelayedFlights" method="post">
      <button value="dummy" type="submit">Query</button>
    </form>
  </div>
</div>

</body>
</html>
