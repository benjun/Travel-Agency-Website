<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://code.jquery.com/jquery.js"></script>
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/style.css" />
  <script src="/TravelAgencyWeb/js/bootstrap.min.js"></script>
   <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/logincss/textbox.css" />
</head>
<body>

<%@ include file="navbar.jsp" %>

<h2><%=request.getAttribute("querytype")%></h2>
<form name="selectauction" action="/TravelAgencyWeb/customer/BidAuction"
 method="post">
<table border=1>
<% List<String> columns = (List<String>)request.getAttribute("columns");
  List<String[]> results = (List<String[]>)session.getAttribute("results"); 
  out.println("<tr>");
  for(String columnName: columns ){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("<td>Your Bid:</td>");
  out.println("</tr>");
  int i = 0;
  for(String[] rowData : results) {
     out.println("<tr>");
     for(String data: rowData) {
        out.println("<td>"+data+"</td>");
     }
     out.println("<td><input type='text' name='bid"+i+"' value=''></td>");
     out.println("<td><button name='index' value="+i+" type='submit'>Bid</button></td>");
     out.println("</tr>");
     i++;
  }
%>
</table>
</body>
</html>
