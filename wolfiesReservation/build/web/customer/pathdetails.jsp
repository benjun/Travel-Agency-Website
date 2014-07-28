<%--
    Document   : customer
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
  <script>
    function checkForm(oForm) {
      var filled = 0;
      for (var i = 0; i < oForm.elements.length; i++) {
        if (GetElementValue(oForm.elements[i]).length > 0)
          filled++;
        if(oForm.elements[i].type=='radio') {
          filled++;
        }
      }
      if(filled<oForm.elements.length) {
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

<h2>Path Details</h2>
<p>Select additional options for your Reservation</p>
<form name="pathdetails" action="/TravelAgencyWeb/customer/CustSelectPath"
 method="post" onsubmit="return checkForm(this);">
  <h4>Seat Class:</h4>
    <p>Economy or First Class?</p>
    <label for="economy">Economy</label>
    <input type="radio" name="class" id="economy" value="economy"><br>
    <label for="first">First Class</label>
    <input type="radio" name="class" id="first" value="first">
  
  <h4>Number of Passengers:</h4>
    <p>How many tickets do you need?</p>
    <label for="pass">Passengers</label>
    <select name='passengers' id='pass'>
    <%for(int i=1;i<7;i++) {%>
    <option value='<%=i%>'><%=i%></option>
    <%}%>
    </select>

  
  <h4>Meal:</h4>
    <p>Do you request any special Meal for your flight?</p>
    <input type="text" class="textbox" name="meal" value="">

  <h4>Customer Representative:</h4>
    <p>Did a customer representative help you? If so, enter his/her SSN. If not, enter "no".</p>
    <input type="text" class="textbox" name="repssn" value="no"><br>
    
  <input name="index" hidden value="<%=request.getParameter("index")%>"><br>
  <button value="dummy" type='submit'>Submit</button>
</form>

<!--((List<String[]>)session.getAttribute("results")).get(Integer.parseInt(request.getParameter("index")))-->
<!--
request.getAttribute("msg")%>
<h2>request.getAttribute("query")%></h2>

  List<String> columns = (List<String>)request.getAttribute("columns");
  List<List<String[]>> results = 
                      (List<List<String[]>>)request.getAttribute("results"); 
  for(List<String[]> result : results) {
    out.println("<table border=1>");
    out.println("<tr>");
    for(String columnName : columns) {
      out.println("<td>"+columnName+"</td>");
    }
    out.println("</tr>");
    for(String[] rowData : result) {
      out.println("<tr>");
      for(String data: rowData){
        out.println("<td>"+data+"</td>");
      }
      out.println("</tr>");
    }
    out.println("</table><br>");
  }
-->
</body>
</html>