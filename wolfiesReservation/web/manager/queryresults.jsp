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

<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><%=request.getAttribute("query")%></h3>
            </div>
            <div class="panel-body">
<table border=1>
<% List<String> columns = (List<String>)request.getAttribute("columns");
  List<String[]> results = (List<String[]>)request.getAttribute("results"); 
out.println("<tr bgcolor='#E6E6E6'>");
  for(String columnName: columns ){
     out.println("<td>"+columnName+"</td>");
  }
  out.println("</tr>");
 for(int i=0; i<results.size(); i++){
      String[] rowData = results.get(i);
      if(i%2==0)
      {
              out.println("<tr>");
                  for(String data: rowData){
                             out.println("<td>"+data+"</td>");
                                 }
                      out.println("</tr>");
      }
      else
                            {
          out.println("<tr bgcolor = '#F2F2F2'>");
                  for(String data: rowData){
                             out.println("<td>"+data+"</td>");
                                 }
                      out.println("</tr>");
                   
      }
           }
%>
</table>
            </div>
</div>
</body>
</html>
