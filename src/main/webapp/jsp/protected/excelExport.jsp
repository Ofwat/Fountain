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
	<title>Fountain: Excel Export</title>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen, print"/>
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>
<div class="container-fluid">
	<div class="row">
		<div class="span8">
	   		<div>Select tables and companies to export as one excel file.</div>
	    	<div id="response"></div>
		</div>
	</div>
 	<div class="row">
		<div class="span12">
			<div class="row">
				<div class="span6">
					<h2>Tables</h2>
					<p class="selector">Tables from <select id='modelSelect' onchange="ofwat.exporting.loadPages();"></select></p>
					<div id="tableAll"></div>
					<div id="tables" class="row"></div> 	
				</div>
				
				<div class="span6">
					<h2>Companies</h2>
					<p class="selector">Company Type <select id='companyTypes' onchange='ofwat.exporting.filterCompanies();'></select></p>
					<div id="companyAll"></div>
					<div id="companies" class="row"></div>
				 </div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="span10">
		</div>
		<div class="span2">
			<button class="goButton" id="goButton" onclick="return ofwat.exporting.exportToExcel()">Go</button>
		</div>
	</div>
</div>

 
  	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" >helpPage="Home";ofwat.wikiPage();</script>

	<script type="text/javascript" src="../../js/json2.js"></script>
	<script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script>
	<script type="text/javascript" src="../../js/ofwat/export.js"></script>
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="My Reports";
		ofwat.wikiPage();
    </script>

 	<script type="text/javascript"> 
     	
//<![CDATA[
           
dojo.require("dijit.form.CheckBox");
dojo.require("dojo.io.iframe");

dojo.addOnLoad(ofwat.exporting.populateOptions);
//]]>
</script>

	<jsp:include page="../footer.jsp" />
</body>
</html>