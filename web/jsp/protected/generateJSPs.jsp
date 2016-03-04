<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page errorPage="../error.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate JSPs</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>

<div class="bannerContent"></div>
   	<div id="response"></div>
	<p></p>
	
 
	<table>
		<tr>
			<td>Model file to generate JSPs from:</td>
			<td><form id="myForm" method="post" action="../../rest-services/file/modelJSPs" enctype="multipart/form-data">
			<input name="dataFile" id="dataFile" type="file"></input>
			<input type="submit" name="submit" value="Upload"></input>
			</form></td>
		</tr>
	</table>

	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>
</html>