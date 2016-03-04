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
	<title>Fountain: Create Run Template</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/fountainNew.css"/>
	<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<style type="text/css">
	
		ul.listy {border:1px solid Black;width:200px;height:200px;display:inline-block;vertical-align:top}
		ul.listy li {background-color:Azure;border-bottom:1px dotted Gray}   
		ul.listy li.selected {background-color:GoldenRod}
	
	</style>
</head>
<body>
<div class="bannerContent"></div>
<p></p>
<div class="container_12">
	<div>
		<div id="reports" class="grid_12">
		<div id="alert"></div>	
		<form class="form-horizontal" id="frmCreateRunTemplate">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Create Run Template</legend>
		
		<!-- Text input-->
		<div class="control-group">
		  <label class="control-label" for="txtName">Name:</label>
		  <div class="controls">
		    <input id="txtName" name="txtName" type="text" placeholder="Run Template Name" class="input-large" required>
		    
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="control-group">
		  <label class="control-label" for="txtDescription">Description:</label>
		  <div class="controls">                     
		    <textarea id="txtDescription" name="txtDescription" required></textarea>
		  </div>
		</div>
		
		<div id="selectExternalModels"></div>
		
		<!-- Button (Double) -->
		<div class="control-group">
		  <label class="control-label" for="btnSave"></label>
		  <div class="controls">
		    <button id="btnSave" name="btnSave" class="btn btn-primary" type="submit">Save</button>
		    <button id="btnCancel" name="btnCancel" class="btn btn-default" type="button">Cancel</button>
		  </div>
		</div>
		
		</fieldset>
		</form>
		</div>
	</div>
	
	<script type="text/javascript" src="../../../js/json2.js"></script>
	<script type="text/javascript" src="../../../js/handlebars/handlebars.js"></script>	
	<script type="text/javascript" src="../../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../../js/dojo/dojo.js.uncompressed.js" ></script>
	<script type="text/javascript" src="../../../js/ofwat/listUtils.js"></script> 
	<script type="text/javascript" src="../../../js/ofwat/admin.js"></script>
    <script type="text/javascript" src="../../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../../js/jquery/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../../js/jqBootstrapValidation.js"></script>
    <script type="text/javascript" src="../../../js/ofwat/runs.js"></script>
    <!-- <script type="text/javascript" src="../../../js/underscore-min.js"></script> -->		
	<script type="text/javascript" src="../../../js/moment.js"></script>   
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>
	
	<jsp:include page="../../footer.jsp" />
</div>
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			$("#btnCancel").click(function(){
				window.location.href = "/Fountain/jsp/protected/runs/listRunTemplates.jsp";
			});
			$("#frmCreateRunTemplate").submit(function(event){
				event.preventDefault();
				var runTemplateName = $("#txtName").val();
				var runTemplateDescription = $("#txtDescription").val(); //TODO Grab from form or passed to func.
				ofwat.runs.createRunTemplate(runTemplateName, runTemplateDescription, function(data){
					//$("#alert").append('<div class="alert alert-success"><button type="button" class="close" data-dismiss="alert">&times;</button>Created run Template Id: ' + data.id + '</div>');
					window.location.href = "/Fountain/jsp/protected/runs/listRunTemplates.jsp";
				})				
			})
		});
	</script>
	
</body>
</html>