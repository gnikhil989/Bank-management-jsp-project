<%@page import="com.jsp.service.BankService"%>
<%@page import="com.jsp.dto.Bank"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" href="Login.css">
</head>
<body>
<div >
<%BankService bankService=new BankService(); %>
<%Bank bank=bankService.getBankByid(1); %>
<h1 >Welcome To <%=bank.getBank_name()%> </h1>
<div id="form">
<form action="Login" method="post" >
		Login as a Customer :<input type="submit" name="Customer"
			placeholder="Click me if you are a Customer"><br> <br>

		Login as a Manager :<input type="submit" name="Manager"
			placeholder="Click me if you are a Manager"> <br> <br>

	</form>
	</div>
	</div>
</body>
</html>