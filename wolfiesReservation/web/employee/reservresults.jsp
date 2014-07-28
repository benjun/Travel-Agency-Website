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

<!--<%=request.getAttribute("msg")%>-->
<h2><%=request.getAttribute("query")%></h2>
<form name="selectpath" action="/TravelAgencyWeb/employee/pathdetails.jsp"
 method="post">
<%
  List<String> columns = (List<String>)session.getAttribute("columns");
  List<List<String[]>> results = 
                      (List<List<String[]>>)session.getAttribute("results"); 
  int i = 0;
  for(List<String[]> result : results) {
    out.println("<table border=1>");
    out.println("<tr>");
    for(String columnName : columns) {
      out.println("<td>"+columnName+"</td>");
    }
    out.println("<td>Select Path</td>");
    out.println("</tr>");
    float totalFare = 0;
    int outerindex=0;
    for(String[] rowData : result) {
      int innerindex=0;
      out.println("<tr>");
      for(String data: rowData){
        if(innerindex!=7) {
          out.println("<td>"+data+"</td>");
          innerindex++;
        } else {
          totalFare += Float.parseFloat(data);
        }
      }
      if(outerindex==result.size()-1) {
        out.println("<td>");
        out.println(String.format("%.2f%n", totalFare));
        out.println("</td>");
        out.println("<td>");
        out.println("<button name='index'value="+ i
                   +" type='submit'>Select</button>");
        out.println("</td>");
      }
      out.println("</tr>");
      outerindex++;
    }
    out.println("</table><br>");
    i++;
  }
%>
</form>
</body>
</html>
