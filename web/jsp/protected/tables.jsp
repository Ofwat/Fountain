<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Model,uk.gov.ofwat.fountain.api.ModelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="uk.gov.ofwat.fountain.domain.ModelType"%>
<%@page import="java.util.List"%>
<%@page import="uk.gov.ofwat.fountain.api.ModelServiceImpl"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain Tables</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- <link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/> -->
	<link rel="stylesheet" type="text/css" href="/Fountain/js/dbootstrap/theme/dbootstrap/dbootstrap.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	
	<!--  <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
</head>
<body>
<div class="bannerContent"></div>

<div id="response"></div>

<div class="container_12"> <!-- main container, currently using 1 x grid_6, 2 x grid_3s -->
	<div class="grid_8 box" id="reservoirXtra"><!-- A set of drop downs to select model, company and page with a Go button -->
		
		<form class="form-horizontal" id="modelForm" action="#">
				<div class="control-group">
					<label class="control-label" for="companyTypes">Company Type</label> 
					<div class="controls">
						<select id="companyTypes" onchange="ofwat.landingPage.filterCompanies();">
							<option value='0'>place holder</option>
						</select>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="company">Company</label>
					<div class="controls"> 
						<select id="company">
							<option value='0'>place holder</option>
						</select>
					</div>
				</div>	
					
				<div class="control-group" id="modelSelectControl">
					<label class="control-label" for="model">Table Suite</label>
					<div class="controls">
						<select id="model" >
							<option value='0'>place holder</option>
						</select>
					</div>
				</div>
				
				<div class="control-group" id="tableSelectControl">
					<label class="control-label" for="page">Table</label>
					<div class="controls">
						<select id="page">
							<option value='0'>place holder</option>
						</select>
					</div>
				</div>
			
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-primary" id="goButton">Go <i class="icon-share-alt"></i></button>
					</div>
				</div>
		</form>
	</div>
	<div class="grid_4">
		<!--  
		<div class="box">
			<h4 class="indexHead">What's New In Fountain</h4>
			<a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/SitePages/Latest%20Updates.aspx" target="_blank">Latest Updates</a><br/>
		</div>
		-->
	</div>
	<div class="clear"></div>
	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" >helpPage="Home";ofwat.wikiPage();</script>

	<%-- TODO change our javascript to minified versions for efficiency, for production env only --%>
	<script type="text/javascript" src="../../js/json2.js"></script>

	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script>
	<script type="text/javascript" src="../../js/ofwat/landingPage.js"></script>
	
    <script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>		

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="Home";
		ofwat.wikiPage();
		$(document).ready(function(){
			// Read a page's GET URL variables and return them as an associative array.
			function getUrlVars()
			{
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			        hash = hashes[i].split('=');
			        vars.push(hash[0]);
			        vars[hash[0]] = hash[1];
			    }
			    return vars;
			}
			
			
			var modelCode = decodeURIComponent(getUrlVars()["modelcode"]);
			var tableName = decodeURIComponent(getUrlVars()["tablename"]);
			var passParams = false;
			
			if((modelCode != "undefined") && (tableName != "undefined")){
				console.log(modelCode);
				console.log(tableName);
				
				//Set the appropriate checkboxes. 
				var $tableSelect = $('#page');
				var $modelSelect = $('#model');
				
				$("#tableSelectControl").hide();
			 	$("#modelSelectControl").hide();
				
				//$modelSelect.hide();
				//$tableSelect.hide();
				passParams = true;
				
			}
		
			//onclick="return ofwat.landingPage.openModel()"	
			var _this = this;
			$("#goButton").click(function(){
				if(passParams){
					return ofwat.landingPage.openModel(modelCode, tableName);
				}else{
					return ofwat.landingPage.openModel();
				}
			});
			
		});
    </script>

	<jsp:include page="../footer.jsp" />
</div> 	
</body>
</html>