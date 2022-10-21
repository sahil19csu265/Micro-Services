<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
  .styled-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    margin-left: auto;
    margin-right: auto;
    
  
}

h1 {
  font-size: 70px;
  font-weight: 600;
  background-image: linear-gradient(to left, #553c9a, #b393d3);
  color: transparent;
  background-clip: text;
  -webkit-background-clip: text;
  text-align: center
}

.styled-table thead tr {
    background-color: #009878;
    color: #ffffff;
    text-align: left;
}

.styled-table th,
.styled-table td {
    padding: 12px 15px;
}

.styled-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
}

.styled-table tbody tr.active-row {
    font-weight: bold;
    color: #009879;
}

.btn{
   margin: auto;  
   
}

form{
   
    
    margin:0 auto;
    text-align:center;
}

.container container-fluid {
    background-color:grey;
}



</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Expense</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#deleteExpense").hide();
     $("#updateExpense").hide();
    
    $("#update").click(function(){
        $("#updateExpense").show();
        $("#deleteExpense").hide();
    });
    $("#delete").click(function(){
        $("#deleteExpense").show();
         $("#updateExpense").hide();
    });
});

</script>
</head>
<body class="container container-fluid">
	<div id="add">
		<h1>EXPENSES</h1>
		<form action="ExpenseController" method="GET">
			<table class="styled-table">
				<tr >
					<td> Name:</td>
					<td><input type="text" name="ename"></td>
				</tr>
				<tr >
					<td> Description:</td>
					<td><input type="text" name="edesc"></td>
				</tr>
				<tr >
					<td> Price:</td>
					<td><input type="text" name="eprice"></td>
				</tr>
				

				
			</table>
		<div class="btn">
		<button class="btn btn-primary" type="submit" name="addExpense" value="Add">ADD</button>
		<button class="btn btn-dark" type="submit" formmethod="post" name="showExpense" value="Show">SHOW</button>
		</div>
		</form>
	</div>
    <div class="mt-3">
		<h1>EXPENSES TABLE</h1>
		<br>
		<table class="styled-table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Expense Name</th>
					<th scope="col">Expense Description</th>
					<th scope="col">Expense Price</th>
					<th scope="col">Expense Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="exp" items="${ExpenseList}">
					<tr>
						<th scope="row">${exp.id}</th>
						<td>${exp.ename}</td>
						<td>${exp.edesc}</td>
						<td>${exp.eprice}</td>
						<td>${exp.edate}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<form name="formid" action="ExpenseController" method="POST">
			<br>Select ID of Expense for update or delete:  <select class="browser-default custom-select" name="id" onchange="document.getElementById('butn').click()">
			    <option value="">--Select one--</option>
				<c:forEach items="${ExpenseList}" var="Expense">
     			
     				<option value="${Expense.id}" ${record.id == Expense.id ? 'selected' : ''}>${Expense.id}</option>
     				
				</c:forEach>
			</select> 
			<input class="btn btn-white mt-2" type="submit" hidden="true" id="butn" value="Fill existing field values" name="showInfo" formmethod="get">
			<br>
			<br>
			<div class="d-flex flex-column ">
			<div><input type="radio" id="update" name="choice" onclick="document.getElementById('hiddenUpdate').hidden=!this.checked;" ${empty record ? 'disabled': ''}>   Update</div>
			<div><input type="radio" id="delete" name="choice" 
				onclick="document.getElementById('hiddenUpdate').hidden=this.checked;" ${empty record ? 'disabled': ''}>   Delete<br>
				</div></div>
			<div id="hiddenUpdate" hidden="true" >
			<table class="table mt-3">
				<tr >
					<td>Update Expense Name:</td>
					<td><input type="text" id="enameupdate" name="enameupdate" value="${record.ename}"></td>
				</tr>
				<tr >
					<td>Update Expense Description:</td>
					<td><input type="text" id="edescupdate" name="edescupdate" value="${record.edesc}"></td>
				</tr>
				<tr >
					<td>Update Expense Price:</td>
					<td><input type="text" id="epriceupdate" name="epriceupdate" value="${record.eprice}" ></td>
				</tr>
			</table>
			</div>	
			<button type="submit" class="btn btn-success" id="updateExpense" name="updateExpense">
				UPDATE</button>
			<br> <br>
			<button class="btn btn-danger" type="submit" id="deleteExpense" name="deleteExpense">
				DELETE</button>




		</form>
	</div>
	
</body>
</html>
