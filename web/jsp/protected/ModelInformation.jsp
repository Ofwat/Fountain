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
	<title>Fountain: Home</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	
	
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	    <link rel="stylesheet" type="text/css" href="/Fountain/js/dijit/themes/tundra/tundra.css"/>
		<!-- 960.gs grid sheet -->
			<link rel="stylesheet" type="text/css" href="/Fountain/css/layout.css"/>
	    <link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
				<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>				
</head>
<body>
<div class="bannerContent"></div>
<div id="response"></div>

<div class="container_12"> <!-- main container, currently using 1 x grid_6, 2 x grid_3s -->
	<div class="grid_8 box" id="reservoirXtra"><!-- A set of drop downs to select model, company and page with a Go button -->
		
		
		
			<p class="frontPageSelector">
				<label for="model">Table Suite</label>
				<select id="model" onchange="ofwat.modelInformation.displayModelInfo()">
					<option value='0'>place holder</option>
				</select>
			</p>
		
	</div>
	
	
<div class="clear"></div>
</div> 	

<div class="container_12"> <!-- main container, currently using 1 x grid_12 -->
   	<div class="grid_4 push_8" id="response"></div>
   	<div class='clear'>&nbsp;</div>
	<div id="modelInfoDiv"></div>
	<div class='clear'>&nbsp;</div>
	<p class="right">
		<button class="saveButton" id="saveButton" onclick="return ofwat.modelInformation.saveModel()">Save</button>
	</p>
</div>

	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" >helpPage="Home";ofwat.wikiPage();</script>


	<%-- TODO change our javascript to minified versions for efficiency, for production env only --%>
	<script type="text/javascript" src="../../js/json2.js"></script>

	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script>
	<script type="text/javascript" src="../../js/ofwat/modelInformationPage.js"></script>
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>	
	<script type="text/javascript" src="../../js/moment.js"></script>
    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="Home";
		ofwat.wikiPage();
    </script>

<jsp:include page="../footer.jsp" />

<script>		// set up dojo
	dojo.require("dijit.layout.TabContainer");
	dojo.require("dijit.layout.ContentPane");
	dojo.require("dojo.parser");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.CheckBox");
	dojo.addOnLoad(function() {
	    dojo.query("div[rel]").forEach(function(n) {
	        var className = dojo.attr(n, "rel");
	        // now set it
	        dojo.attr(n, "dojoType", className);
	    });
	   dojo.parser.parse();
	   // When all dojo aspects are loaded, call the page initialisation funtion
	   dojo.addOnLoad(ofwat.modelInformation.loadInitialData);
	});
</script>

</body>
</html>