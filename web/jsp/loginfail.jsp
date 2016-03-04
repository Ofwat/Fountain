<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.io.StringWriter,java.io.PrintWriter"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Fountain: Login Error</title>
 <link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" />
</head>
<body>
    <jsp:include page="banner.jsp" />
    <p class="text">
	Your login failed, please try again with the correct username and password.
	</p>
	<p class="text">You can log back in <a href="/Fountain/jsp/protected/index.jsp">here</a></p>
	<jsp:include page="footer.jsp"/>

</body>
</html>