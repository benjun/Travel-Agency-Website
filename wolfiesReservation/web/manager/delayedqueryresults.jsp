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

<h2><%=((List<String>)request.getAttribute("queries")).get(0)%></h2>
<table border=1>
<% List<List<String>> columns = 
        (List<List<String>>)request.getAttribute("columns");
  List<List<String[]>> results = 
        (List<List<String[]>>)request.getAttribute("results"); 
  out.println("<tr>");
  for(String columnName: columns.get(0)){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("</tr>");
  for(String[] rowData: results.get(0)){
     out.println("<tr>");
     for(String data: rowData){
        out.println("<td>"+data+"</td>");
     }
     out.println("</tr>");
  }
%>
</table>
<h2><%=((List<String>)request.getAttribute("queries")).get(1)%></h2>
<table border=1>
<% columns = (List<List<String>>)request.getAttribute("columns");
   results = (List<List<String[]>>)request.getAttribute("results"); 
  out.println("<tr>");
  for(String columnName: columns.get(1)){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("</tr>");
  for(String[] rowData: results.get(1)){
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
