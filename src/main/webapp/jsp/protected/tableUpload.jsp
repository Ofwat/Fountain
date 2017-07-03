<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page errorPage="../error.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Table</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/>
	<link type="text/css" rel="stylesheet" href="../../css/layout.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
</head>
<body class="tundra">

<div class="bannerContent"></div>

   	<div id="response"></div>
	<p></p>
<div class="container_12"> <!-- main container, currently using 1 x grid_6, 2 x grid_3s -->
	<div class="grid_8 box" id="reservoirXtra"><!-- A set of drop downs to select model, company and page with a Go button -->
		<h2>Table Import</h2>
		<form id="modelForm" method="post" action="../../rest-services/file/table" enctype="multipart/form-data" accept-charset="UTF-8" >
			<p class="frontPageSelector">
				<label for="tableFile">Import file</label> 
				<input name="tableFile" id="tableFile" type="file"></input>
			</p>
			<p class="frontPageSelector">
				<label for="company">Company</label> 
				<select id="company" name="company">
					<option value='0'>place holder</option>
				</select>	
			</p>
			<p class="frontPageSelector">
				<label for="model">Model</label>
				<select id="model" name="model">
					<option value='0'>place holder</option>
				</select>
			</p>
			<p class="frontPageSelector">
				<label for="page">Table</label>
				<select id="page" name="page">
					<option value='0'></option>
				</select>
			</p>
			<p class="frontPageSelector">
				<label for="allowHistoricData">Allow Historic Data</label> 
				<input name="allowHistoricData" id="allowHistoricData" type="checkbox"/>
			</p>
			<p class="right">
 				<input id="submitButton" type="submit" name="submit" value="Upload" onclick="ofwat.hideButton(this, 'file', 'tableFile')"></input>
			</p>
		</form>
	</div>
	<div class="clear"></div>
</div> 	
				
	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/rest.js"></script>
	<script type="text/javascript" src="/Fountain/js/ofwat/tableUpload.js"></script>
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