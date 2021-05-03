<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    	
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>java-spring-demo: Admin</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
<script
  src="https://code.jquery.com/jquery-3.1.1.js"
  integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
  crossorigin="anonymous"></script>
<script
  src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
  integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
  crossorigin="anonymous"></script>  
<sec:csrfMetaTags />
<script>
	$(function() {
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");

		function deleteData() {
			var data = {};
			data[csrfParameter] = csrfToken;
			
			$.ajax({
						method: "POST",
						dataType : "json",
						url : "api/admin/deleteCustomerAccountData",
						data: data,
						success: function(result) {		
							if (result.status == "OK") {
								alert("Accounts and Customers deleted");											
							} else {
								alert("An error occurred while deleting accounts and customers");											
							}
						}
				});
	    }				
		function createData() {
			var data = {};
			data[csrfParameter] = csrfToken;

			$.ajax({
						method: "POST",
						dataType : "json",
						url : "api/admin/createCustomerAccountData",
						data: data,
						success: function(result) {		
							if (result.status == "OK") {
								alert("Accounts and Customers created");											
							} else {
								alert("An error occurred while creating accounts and customers");											
							}
						}
				});
	    }				
	    $( "#delete-cust-account-data" ).button().on( "click", function() {
	    	event.preventDefault();
	    	deleteData();
		});
	    $( "#create-cust-account-data" ).button().on( "click", function() {
	    	event.preventDefault();
	    	createData();
		});
			    
			    
	});								
</script>
</head>
<body>
<header>java-spring-demo: Admin</header>
<nav><a href="${pageContext.request.contextPath}/home">Return to Home</a></nav>
<p id="description">This page can create or delete test customers and accounts.</p>	
<hr/>	
<div class="table-contain">
<button class="ui-button" id="delete-cust-account-data">Delete All Accounts and Customers</button>
<br/>
<button class="ui-button" id="create-cust-account-data">Create Customers and Accounts</button>
</div>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>