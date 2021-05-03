<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    	
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>java-spring-demo: Home</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script>

		$(document).ready(
			function() {
				$( "#example_table tr:even" ).css( "background-color", "#ffffff" );
				$( "#example_table tr:odd" ).css( "background-color", "#eeeeee" );

			});
	
</script>
</head>
<body>
<header>java-spring-demo: Home</header>
<hr/>
<p>The home page for the java-spring-demo project.</p>	
<p>This web application contains the following examples:</p>
<div class="table-contain">
<table id="example_table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Link</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="row-header"><b>Simple AJAX Data Fetch with JQuery UI SelectMenu</b></td>
				<td>A simple example of a JQuery UI SelectMenu component that calls a web service to fetch data.</td>
				<td><a href="states">View States by Country</a></td>
			</tr>
			<tr>
				<td class="row-header"><b>Spring MVC Form Processing</b></td>
				<td>A simple form using Spring MVC to create a new customer. This example does not use JQuery.</td>
				<td><a href="createCustomer">Create a New Customer</a></td>
			</tr>
			<tr>
				<td class="row-header"><b>AJAX Data Fetch and Form Submission</b></td>
				<td>An example using Spring MVC / JQuery UI to display a list of customers and a dialog to create a new customer.</td>
				<td><a href="customers">View Customers / Create a New Customer</a></td>
			</tr>
			<tr>
				<td class="row-header"><b>AJAX Data Fetch and Form Submission</b></td>
				<td>A simple search example to display a search results page with the ability to search by first name, last name or user name.</td>
				<td><a href="customerSearch">Customer Search</a></td>
			</tr>
			<tr>
				<td class="row-header"><b>Content Negotiation</b></td>
				<td>Returns XML or JSON data based on the extension appended to the request URL. The default format is JSON.</td>
				<td><a href="getCustomers">Customers (default)</a><br/><br/><a href="getCustomers.json">Customers (.json)</a><br/><br/><a href="getCustomers.xml">Customers (.xml)</a></td>
			</tr>
			<tr>
				<td class="row-header"><b>Admin</b></td>
				<td>Create or delete test data</td>
				<td><a href="admin">Admin</a></td>
			</tr>
		</tbody>
	</table>
</div>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
<hr/>
<footer>Page Generated: <%= new Date() %></footer>	
</body>
</html>

