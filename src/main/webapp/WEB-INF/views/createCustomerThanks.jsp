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
<title>Thank You</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
</head>
<body>
<header>Customer Profile Created!</header>
<nav><a href="index.jsp">Home</a></nav> 
<p id="description">Thank you ${firstName}! You customer profile has been created.</p>	
<hr/>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>