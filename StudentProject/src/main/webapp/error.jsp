<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR</title>
</head>
<body>
<div align="center">
		<h3>Page generated ERROR</h3>
		<h4>EXCEPTION : </h4>
		<h4><%= exception.getMessage() %></h4>
</div>
</body>
</html>