<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Create Customer</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
</head>
<body>
<header>Create Customer</header>
<nav><a href="index.jsp">Home</a></nav> 
<p id="description">Please use the form below to create your customer profile.</p>	
<hr/>
<div class="table-contain"> 
<sf:form method="POST" action="#" id="createCustomerForm" commandName="customerModel">
    <fieldset>
	  <label for="cust_title">Title</label><sf:select id="cust_title" name="title" path="title">
		<sf:option value="" label="-- Please Select --"/>
              <sf:options items="${titleList}" itemValue="id" itemLabel="name"/>
	  </sf:select><sf:errors path="title" /><br/>
	  <label for="cust_firstName">First Name</label><sf:input type="text" id="cust_firstName" name="firstName" path="firstName"></sf:input><sf:errors path="firstName" /><br/>
	  <label for="cust_lastName">Last Name</label><sf:input type="text" id="cust_lastName" name="lastName" path="lastName"></sf:input><sf:errors path="lastName" /><br/>
	  <label for="cust_userName">User Name</label><sf:input type="text" id="cust_userName" name="userName" path="userName"></sf:input><sf:errors path="userName" /><br/>
	  <label for="cust_dob">Birth Date</label><sf:input type="text" id="cust_dob" name="dob" path="dob"/><sf:errors path="dob" /><br/>	  
	  <button name="submit" id="cust_submit">Submit</button> 
	</fieldset>
</sf:form>
</div>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>
