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
<title>java-spring-demo: Customer Search</title>
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
				var dialog, form,
				firstName = $( "#firstName" ),
				lastName = $( "#lastName" ),
				userName = $( "#userName" ),
				dob = $( "#dob" ),
			    allFields = $( [] ).add( firstName ).add( lastName ).add( userName ).add( dob );

				var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
				var csrfHeader = $("meta[name='_csrf_header']").attr("content");
				var csrfToken = $("meta[name='_csrf']").attr("content");
				
				function editCustomer(id) {
					if (id === undefined) {
				          id = 0;
				    }					
					alert("Editing " + id);
				}				

				function searchCustomers() {
					var searchVal = $("#searchVal").val();
					var data = {};
					data[csrfParameter] = csrfToken;
					data["searchTerm"] = searchVal;

					$('#customer_table').hide();
					$('#customer_table TBODY tr').remove();

					$.ajax({
						method: "POST",
						dataType : "json",
						url : "api/customer/customerSearch",
						data : data,
						success: function(data) {
							var html = '';
							var len = data.length;
							if (len > 0) {
								for (var i = 0; i < len; i++) {
									html = "<tr id='" + data[i].id + "'><td>" + data[i].lastName 
										 + "</td><td>" + data[i].firstName 
										 + "</td><td>" + data[i].userName
										 + "</td><td>" + new Date(data[i].birthDate).toString()
											+ "</td><td><a data-role='button' class='ui-table-button' id='edit_" + data[i].id + "'>Edit</a></td></tr>";
									$('#customer_table TBODY').append(html);		
								    $("#customer_table TBODY").find("a[id='edit_" + data[i].id + "']").button().on( "click", function() {
										   editCustomer(22);					
								    });
								}			
							    
							} else {
								html="<tr><td colspan=\"5\">No results found.</td></tr>";
								$('#customer_table TBODY').html(html);						
							}
							$( "#customer_table tr:even" ).css( "background-color", "#bbbbff" );
							$( "#customer_table tr:odd" ).css( "background-color", "#ffeeee" );
							$('#customer_table').show();

						}
					});
				}

				dialog = $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 450,
			      width: 475,
			      modal: true,
			      buttons: {
			        Cancel: function() {
			          dialog.dialog( "close" );
			        }
			      },
			      close: function() {
			        form[ 0 ].reset();
			        allFields.removeClass( "ui-state-error" );
			      }
			    });
			 
			    form = dialog.find( "form" ).on( "submit", function( event ) {
			      event.preventDefault();
			      //addCustomer();
			    });
			 
			    $( "#update-customer-table" ).button().on( "click", function() {
			    	event.preventDefault();
			    	searchCustomers();
				});

				$( "#dob" ).datepicker({
				      changeMonth: true,
				      changeYear: true,
				      yearRange : '-99:+0'
				    });

				$( "#search_submit" ).button().on( "click", function() {
			    	event.preventDefault();
			    	searchCustomers();
				});;
				
				searchCustomers();
	});								
</script>
</head>
<body>
<header>java-spring-demo: Customer Search</header>
<nav><a href="${pageContext.request.contextPath}/home">Return to Home</a></nav>
<div id="dialog-form" title="Create new customer">
  <p class="validateTips">All form fields are required.</p>
  <form>
    <fieldset class="customer-fs">
      <label for="firstName">First Name</label>
      <input type="text" name="firstName" id="firstName" class="text ui-widget-content ui-corner-all">
      <label for="lastName">Last Name</label>
      <input type="text" name="lastName" id="lastName" class="text ui-widget-content ui-corner-all">
      <label for="userName">User Name</label>
      <input type="text" name="userName" id="userName" class="text ui-widget-content ui-corner-all">
      <label for="dob">BirthDate</label>
      <input type="text" name="dob" id="dob" class="text ui-widget-content ui-corner-all"> 
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"/>
    </fieldset>
  </form>
</div>
<p id="description">This page displays a list of customers in a table that is populated by an AJAX call to the getCustomers service.</p>	
<hr/>	
<div class="table-contain">
	<h1>Customers</h1>
	<form id="searchCustomerForm" method="POST">
	    <fieldset class="customer-fs">
	      <input type="text" name="searchVal" id="searchVal" class="text ui-widget-content ui-corner-all">
	  	  <button name="search_submit" id="search_submit">Search</button> 
	    </fieldset>
	  </form>

	<table id="customer_table">
		<thead>
			<tr>
				<th>Last&nbsp;Name</th>
				<th>First&nbsp;Name</th>
				<th>User&nbsp;Name</th>
				<th>Birth&nbsp;Date</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
<button class="ui-button" id="update-customer-table">Refresh Table</button>
</div>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>