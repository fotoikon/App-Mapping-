<%-- 
    Document   : register
    Created on : Jan 8, 2017, 7:40:30 PM
    Author     : fotini
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display</title>
<style>
table#nat{
	width: 50%;
	background-color: #c48ec5;
}
</style>
</head>
<body>  
<%      String name =  request.getParameter("uname");
	String Addr = request.getParameter("upass");
	String age = request.getParameter("uemail");%>
<table id ="nat">
<tr>
	<td>Full Name</td>
	<td><%= name %></td>
</tr>
<tr>
	<td>pass</td>
	<td><%= Addr %></td>
</tr>
<tr>
	<td>email</td>
	<td><%= age %></td>
</tr>

</table>
</body>
</html>