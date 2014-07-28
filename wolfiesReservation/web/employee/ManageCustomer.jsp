<%--
    Document   : manager
    Created on : Apr 11, 2014, 11:10:50 AM
    Author     :
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    $(function() {
    $( "#accordion" ).accordion();
    });
    
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

<!--Add, Edit and Delete information for an employee</br>
an Add page, an edit/delete page, also a view page probably-->
<h2>Customer Management</h2>
<h4>Select what you want to do:</h4>
<div id="accordion">
  <h3>Add Customer</h3>
  <div>
    <p>
      
    </p>
    <form name="AddCustomerForm" action="/TravelAgencyWeb/employee/AddCustomer" method="post">
        <table border="0">
            <tbody>
                <tr><td><font color="red">* field is required.</font></td></tr>
                <tr><td><label>First Name*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonFirstName"/></td>
                </tr>
                <tr><td><label>Last Name*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonLastName"/></td>
                </tr>
                <tr><td><label>Address*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonAddress"/></td>
                </tr>
                <tr><td><label>City*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonCity"/></td>
                </tr>
                <tr><td><label>State*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="PersonState"/></td>
                </tr>
                <tr><td><label>Zip Code*:</label></td>
                    <td><input class="textbox" type="text" maxlength="7" name="PersonZipCode"/></td>
                </tr>
                <tr><td><label>Credit Card Number*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="CustomerCreditCardNo"/></td>
                </tr>
                <tr><td><label>E-mail*:</label></td>
                    <td><input class="textbox" type="text" maxlength="45" name="CustomerEmail"/></td>
                </tr>     
      </tbody>
        </table>      
      <input type="submit" name="submit" value="Add"/>
    </form>
  </div>
  
  <h3>Edit Customer</h3>
  <div>
    <p>
      
    </p>
    <form name="EditCustomerForm" action="/TravelAgencyWeb/employee/EditCustomerList" method="post">
        <table>
       <tr><td><font color="red">* field is required.</font></td></tr>
                <tr><td><label>First Name*:</label></td>
                    <td><input type="text" maxlength="45" name="PersonFirstName"/></td>
                </tr>
                <tr><td><label>Last Name*:</label></td>
                    <td><input type="text" maxlength="45" name="PersonLastName"/></td>
                </tr>
        </table>                
      <button value="dummy" type="submit">Search</button>
    </form>
  </div>
  
  <h3>Delete Customer</h3>
  <div>
    <p>
      
    </p>
    <form name="DeleteCustomerForm" action="/TravelAgencyWeb/employee/DeleteCustomerList" method="post">
        <table>
       <tr><td><font color="red">* field is required.</font></td></tr>
                <tr><td><label>First Name*:</label></td>
                    <td><input type="text" maxlength="45" name="PersonFirstName"/></td>
                </tr>
                <tr><td><label>Last Name*:</label></td>
                    <td><input type="text" maxlength="45" name="PersonLastName"/></td>
                </tr>
        </table>                  
      <button value="dummy" type="submit">Search</button>
    </form>
  </div>
  
 
</div>


</body>
</html>
