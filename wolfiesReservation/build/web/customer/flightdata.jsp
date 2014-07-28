<%-- 
    Document   : FlightData
    Created on : Apr 22, 2014, 6:23:52 PM
    Author     : Minhan Jun
--%>

<%@page import="java.util.List"%>
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
        <h2>${querytype}</h2>
        <form>
        <table border=1>
            <% List<String> columns = (List<String>)request.getAttribute("columns");
              List<String[]> results = (List<String[]>)request.getAttribute("results"); %>
              <tr> 
                
                 <%for(String columnName: columns ){
                 out.println("<td>" + columnName + "</td>"); }%>
                 <td></td>
              </tr>
              <tr>
               
                    <%for(String[] rowData: results){
                 for(String data: rowData){
                    out.println("<td>" + data + "</td>");
                    }
                 out.println("<td><a href='select_flight?AirlineID=" + rowData[0] + "&FlightNo=" + rowData[1] + "&LegNo=" + rowData[2] + "&DepAirportID=" +
                         rowData[3] + "&ArrAirportID=" + rowData[4] + "&ArrTime=" + rowData[5] + "&DepTime=" + rowData[6] 
                         + "&FareType=" + rowData[7] + "&Class=" + rowData[8] + "&Fare=" + rowData[9] + "&AccountNo=" + rowData[10] + "'  >Select</a></td>");
                 out.println("</tr><tr>");
                    }
                    %>
             </tr>
             
        </table>
    </form>
    </body>
</html>