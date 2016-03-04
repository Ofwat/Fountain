<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1; IE=8"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- CSS Reset sheet, minified version, written by yahoo -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	    <!-- <link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/> -->
		<!-- 960.gs grid sheet -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
		<link rel="stylesheet" type="text/css" href="/Fountain/css/layout.css"/>
	    <link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
		<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
		<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap-filterable.css" media="screen"/>
		<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
		<!-- <link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/> -->
        <title>My Reports</title>
    </head>
    
    <body class="">
<div class="bannerContent"></div>
    <p></p>
    <div class="container_12"> <!-- main container, currently using 1 x grid_12 -->
    	<div class="grid_4 push_8" id="response"></div>
	    
    	<div class='clear'>&nbsp;</div>
		<div id="reports" class='grid_12'></div>
		<div class='clear'>&nbsp;</div>

   </div>
   <div id="allContent">
   </div>
   	<div id="fade" class="dark_overlay"></div>
   
   <p/>
   	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" >helpPage="My Reports";ofwat.wikiPage();</script>
   
    <script type="text/javascript" src="../../js/json2.js"></script>
    <script type="text/javascript" src="../../js/ofwat/rest.js"></script>
    <script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script> 
    <script type="text/javascript" src="../../js/ofwat/reportPage.js"></script>
    <script type="text/javascript" src="../../js/ofwat/reportCompanyRunSelection.js"></script>
    <script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.dataTables.js"></script>       
    <script type="text/javascript" src="../../js/jquery.filterable.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-editable.min.js"></script>     
    <script type="text/javascript" src="../../js/moment.js"></script>
<script type="text/javascript">
//<![CDATA[
    //dojo.require("dijit.Tooltip");
//]]>
$(document).ready(function() {
	ofwat.reportPage.loadReports();
});
</script>

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="My Reports";
		ofwat.wikiPage();
    </script>


<jsp:include page="../footer.jsp" />
</body>
</html>