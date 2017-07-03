<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.io.StringWriter,java.io.PrintWriter"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Fountain Error</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>
    <jsp:include page="banner.jsp" />
    <p class="text">
    An unexpected error occurred. The name of the exception is:
    </p>
    <p class="exception">
    <%=exception%>
	</p>
    <p class="exception">
    ${error}
	</p>

<%
	out.println("<!--");
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	if (exception!=null) {
		exception.printStackTrace(pw);
	}
	out.print(sw);
	sw.close();
	pw.close();
	out.println("-->");
%>
<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
<jsp:include page="footer.jsp" />
</body>
</html>