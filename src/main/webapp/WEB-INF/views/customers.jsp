<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    	
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Customers</title>
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
<script>
	$(function() {
				var dialog, form,
				firstName = $( "#firstName" ),
				lastName = $( "#lastName" ),
				userName = $( "#userName" ),
				dob = $( "#dob" ),
			    allFields = $( [] ).add( firstName ).add( lastName ).add( userName ).add( dob );
				
				function addCustomer() {
				      	var valid = true;
				      	var titleIdVal = 1;
						var firstNameVal = $("#firstName").val();
						var lastNameVal = $("#lastName").val();
						var userNameVal = $("#userName").val();
						var dobVal = $("#dob").val();

						$.ajax({
									method: "POST",
									dataType : "json",
									url : "createCustomerJson",
									data : {
										title : titleIdVal,
										firstName : firstNameVal,
										lastName : lastNameVal,
										userName : userNameVal,
										dob : dobVal
										},
									success: function(result) {		
										if (result.status == "OK") {
											refreshCustomerTable();											
										} else {
							            	for (var i = 0; i < result.fieldErrorList.length; i++) {
							            		alert(result.fieldErrorList[i].fieldName + ": " + result.fieldErrorList[i].errorMsg);
							            	}
										}
									}
								});
						
				      	allFields.removeClass( "ui-state-error" );
				      	dialog.dialog( "close" );
				      	return valid;
				    }				

				function editCustomer(id) {
					if (id === undefined) {
				          id = 0;
				    }					
					alert("Editing " + id);
				}				

				function refreshCustomerTable() {
					$('#customer_table').hide();
					$('#customer_table TBODY tr').remove();

					$.getJSON('getAllCustomers', function(data) {
						var html = '';
						var len = data.length;
						if (len > 0) {
							for (var i = 0; i < len; i++) {
								html = "<tr id='" + data[i].id + "'><td>" + data[i].lastName 
									 + "</td><td>" + data[i].firstName 
									 + "</td><td>" + data[i].userName
									 + "</td><td>" + data[i].birthDate
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

					});
				}

				dialog = $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 450,
			      width: 475,
			      modal: true,
			      buttons: {
			        "Create an account": addCustomer,
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
			      addCustomer();
			    });
			 
			    $( "#create-customer" ).button().on( "click", function() {
			      dialog.dialog( "open" );
			    });
			    
			    $( "#update-customer-table" ).button().on( "click", function() {
				    refreshCustomerTable();
				});

				$( "#dob" ).datepicker({
				      changeMonth: true,
				      changeYear: true,
				      yearRange : '-99:+0'
				    });
				
				refreshCustomerTable();

	});								
</script>
</head>
<body>
<header>Tabular Display with JQuery Dialog</header>
<nav><a href="index.jsp">Home</a></nav>
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
<button class="ui-button" id="create-customer">Create New Customer</button>
</div>
<div class="table-contain">
	<h1>Accounts</h1>
	<p>This section is TBD</p>
</div>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>