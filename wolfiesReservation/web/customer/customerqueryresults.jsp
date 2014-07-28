<%-- 
    Document   : existingReservation
    Created on : Apr 24, 2014, 10:28:25 PM
    Author     : 미소년
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

         
<h2><%=request.getAttribute("querytype")%></h2>
<table border=1>
<% List<String> columns = (List<String>)request.getAttribute("columns");
  List<String[]> results = (List<String[]>)request.getAttribute("results"); 
  out.println("<tr>");
  for(String columnName: columns ){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("</tr>");
  for(String[] rowData: results){
     out.println("<tr>");
     for(String data: rowData){
        out.println("<td>"+data+"</td>");
     }
     out.println("</tr>");
  }
%>
</table>
</body>
</html>
