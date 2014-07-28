<%-- 
    Document   : reservationListstoCancel
    Created on : Apr 25, 2014, 1:34:03 AM
    Author     : 誘몄냼??--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://code.jquery.com/jquery.js"></script>
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/css/style.css" />
   <link rel="stylesheet" type="text/css" href="/TravelAgencyWeb/logincss/textbox.css" />
  <script src="/TravelAgencyWeb/js/bootstrap.min.js"></script>
</head>
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
                 out.println("<td><a href='DeleteCustomerReservation?ResrNo=" + rowData[0]  + "'>Cancel</a></td>");
                 out.println("</tr><tr>");
                    }
                    %>
             </tr>
            
        </table>
    </form>
                           Welcome! your id is:  <%=session.getAttribute("loginId") %><br>
              your account no is:<%=session.getAttribute("accountNo") %><br>
              your first name is: <%=session.getAttribute("firstName") %><br>
              your rank is : <%= session.getAttribute("loginRank") %>
    </body>
</html>