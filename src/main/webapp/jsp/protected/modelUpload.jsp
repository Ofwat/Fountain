<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page errorPage="../error.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Table Suite</title>
<link type="text/css" rel="stylesheet" href="../../css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>

   	<div id="response"></div>
	<p></p>
	<table>
		<tr>
			<td>Table Suite File to import:</td>
			<td><form id="myForm" method="post" action="../../rest-services/file/model" encoding="multipart/form-data" enctype="multipart/form-data" accept-charset="UTF-8">
			<input name="modelFile" id="modelFile" type="file"></input>
				<span id="submitButton"><input type="submit" name="submit" value="Upload" onclick="ofwat.hideButton(this, 'file', 'modelFile');"></input></span>
			</form></td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td>Bulk  import:</td>
			<td><form id="myForm2" method="post" action="../../rest-services/file/bulkModelUpload" >
			<input type="submit" name="submit" value="Upload" onclick="ofwat.hideButton(this, 'bulk');"></input></form></td>
		</tr>
	</table>

	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>	
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../footer.jsp" />
</body>
</html>
