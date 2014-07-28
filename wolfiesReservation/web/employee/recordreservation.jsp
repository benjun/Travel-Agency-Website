<%-- 
    Document   : recordReservation
    Created on : Apr 20, 2014, 5:33:57 PM
    Author     : Garret Leotta
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
  <script>
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

<h2>Record A Reservation:</h2><br>
<p>To retrieve a list of possible travel paths: <br>
Enter the <strong>date of travel</strong> desired.<br>
Enter the <strong>name of the city or the ID of the airport</strong> you are 
leaving from.<br>
Enter the <strong>name of the city or the ID of the airport</strong> you wish 
to go to.</p>
<form name="bookmenow" action="/TravelAgencyWeb/employee/RecordReservation"
      method="post" onsubmit="return checkForm(this);">
  <table>
  <tr>
    <td>
      Date of Travel:
    </td>
    <td>
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
      <select name='day'>
        <option selected value=''>Day</option>
        <%for(int i=1;i<=31;i++) { %>
        <option value='<%=i%>'><%=i%></option>
        <% } %>
      </select>
      <select name='year'>
        <option selected value=''>Year</option>
        <% int max = 6;
           for(int i=0;i<=max;i++) { %>
        <option value='<%= 2011+i%>'><%= 2011+i%></option>
        <% } %>
      </select>
    </td>
  </tr>
  <tr>
    <td>
      Leaving From:
    </td>
    <td>
      <input class="textbox" type="text" name="here" value="">
    </td>
  </tr>
  <tr>
    <td>
      Going To:
    </td>
    <td>
      <input class="textbox" type="text" name="there" value="">
    </td>
  </tr>
  <tr>
    <td>
      <button value="dummy" type="submit">Send</button>
    </td>
  </tr>
  </table>
</form>
</body>
</html>