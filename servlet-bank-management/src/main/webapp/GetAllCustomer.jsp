<%@page import="java.io.PrintWriter"%>
<%@page import="com.jsp.service.PersonService"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.dto.Person"%>

<%@page import="com.jsp.service.ManagerService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" href="GetAllCustomer.css">
</head>
<body>
<%
ManagerService managerService= new ManagerService();
PersonService personService=new PersonService();
	%>
	<%
	List<Person> persons = managerService.viewAllPerson(); %>
	<table  border="2px"  id="table">
		<tr>
			<th>Customer_ID</th>
			<th>Customer_NAME</th>
			<th>Customer_Email</th>
			<th>Customer_Status</th>
			<th>APPROVE</th>
			<th>BLOCK</th>

		</tr>
		<%
		for (Person p : persons) {
		%>

		<tr>
			<td><%=p.getPerson_id()%></td>
			<td><%= p.getName()%></td>
			<td><%=p.getEmail() %></td>
			<td><%=p.getStatus() %></td>
			<td> <a href="Approve.jsp?id=<%= p.getPerson_id()%>">APPROVE</a></td>
			<td> <a href="Block.jsp?id=<%= p.getPerson_id()%>">BLOCK</a></td>

		</tr>
		<%
		}
		%>

	</table>
	<br> <br> 
	<br><br>
	<div>
<%PrintWriter printWriter= response.getWriter();
printWriter.write("<br><br>");
printWriter.write("<a href='BankController.jsp'>Main Menu</a>");
printWriter.write("<br><br>");
printWriter.write("<a href='ManagerController.jsp'>Back</a>"); %>
</div>
</body>
</html>