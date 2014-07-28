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

<h2><%=request.getAttribute("query")%></h2>
<table border=1>
<% List<String> columns = (List<String>)request.getAttribute("columns");
  List<String[]> results = (List<String[]>)request.getAttribute("results"); 
  out.println("<tr>");
  for(String columnName: columns ){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("<td>Select</td>");
  out.println("</tr>");
  for(String[] rowData: results){
     String ssn = rowData[0];
     out.println("<tr>");
     for(String data: rowData){
        out.println("<td>"+data+"</td>");
     }
     out.println("<td><a href='/TravelAgencyWeb/manager/ManageEmployee?eid="+ssn+"'>Select</a></td>");
     out.println("</tr>");
  }
%>
</table>
</body>
</html>
