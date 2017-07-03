<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Model, uk.gov.ofwat.fountain.api.ModelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Admin</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link href="/Fountain/css/jquery.pnotify.default.css" media="all" rel="stylesheet" type="text/css" />	
	<!-- Include this file if you are using Pines Icons. -->
	<link href="/Fountain/css/jquery.pnotify.default.icons.css" media="all" rel="stylesheet" type="text/css" />		
	
</head>
<body>
<div class="bannerContent"></div>
<p></p>
<div class="container_12">
<h3>Run List</h3>
<div>
	<div id="reports" class="grid_12">
		<!-- insert the template here! Div will get replaced in Handlebars JS call below!-->
		<div id="tableData"></div>
		
		<a class="btn" id="createRun" href="createRun.jsp">Create a new Run</a>
		<a class="btn" id="listRunTemplates" href="listRunTemplates.jsp">List Run Templates</a>
		<!-- <a class="btn" id="createRuntemplate" href="createRunTemplate.jsp">Create A Run Template</a> -->

		<script type="text/javascript" src="../../../js/json2.js"></script>
		<script type="text/javascript" src="../../../js/handlebars/handlebars.js"></script>	
		<script type="text/javascript" src="../../../js/ofwat/ofwat.js"></script>
		<script type="text/javascript" src="../../../js/ofwat/rest.js"></script>
		<script type="text/javascript" src="../../../js/dojo/dojo.js.uncompressed.js" ></script>
		<script type="text/javascript" src="../../../js/ofwat/listUtils.js"></script> 
		<script type="text/javascript" src="../../../js/ofwat/admin.js"></script>
	    <script type="text/javascript" src="../../../js/jquery/jquery-1.10.2.js"></script>
	    <script type="text/javascript" src="../../../js/bootstrap.js"></script>	
		<script type="text/javascript" src="../../../js/ofwat/runs.js"></script>
		<script type="text/javascript" src="../../../js/jquery.pnotify.min.js"></script>    
		<script type="text/javascript" src="../../../js/moment.js"></script>
		<jsp:include page="../../footer.jsp" />	
	</div>
	
</div>
</div>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<script type="text/javascript">
		dojo.addOnLoad(function(){
			Handlebars.registerHelper('checked', ofwat.checkedHelper);
			Handlebars.registerHelper('date', ofwat.dateHelper);
			Handlebars.registerHelper('defaultRun', ofwat.defaultRunHelper);
			ofwat.loadTemplate(dojo.byId("tableData"),ofwat.template.runList);
		});	 
	</script>
	
</body>
</html>