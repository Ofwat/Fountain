<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<%@ page
	import="uk.gov.ofwat.fountain.domain.Model, uk.gov.ofwat.fountain.api.ModelService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Fountain: Edit Run</title>
<!-- CSS Reset sheet, minified version, written by yahoo -->
<link rel="stylesheet" type="text/css"
	href="/Fountain/css/reset-fonts-grids.css" />
<!-- 960.gs grid sheet -->
<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css" />
<link rel="stylesheet" type="text/css" href="/Fountain/css/fountainNew.css" />
<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css"	media="screen" />
<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen" />
<link type="text/css" rel="stylesheet"	href="/Fountain/css/bootstrap.css" media="screen" />
<link href="/Fountain/css/jquery.pnotify.default.css" media="all" rel="stylesheet" type="text/css" />
<!-- Include this file if you are using Pines Icons. -->
<link href="/Fountain/css/jquery.pnotify.default.icons.css" media="all" rel="stylesheet" type="text/css" />	
<link href="/Fountain/css/bootstrap-datetimepicker.min.css" media="all" rel="stylesheet" type="text/css" />	
</head>

<body>
<div class="bannerContent"></div>
	<p></p>
	<div class="container_12">
		<div>
			<div id="reports" class="grid_12">
				<!-- 
				<div id="responseOuter" class="">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<div id="response"></div>
				</div>
				-->
				<div id="editReleaseData"></div>
				<script type="text/javascript" src="../../../js/jquery/jquery-1.10.2.js"></script>
				<script type="text/javascript" src="../../../js/jquery.pnotify.min.js"></script>					
				<script type="text/javascript" src="../../../js/json2.js"></script>
				<script type="text/javascript" src="../../../js/handlebars/handlebars.js"></script>
				<script type="text/javascript" src="../../../js/moment.js"></script>
				<script type="text/javascript" src="../../../js/ofwat/ofwat.js"></script>
				<script type="text/javascript" src="../../../js/ofwat/rest.js"></script>
				<script type="text/javascript" src="../../../js/dojo/dojo.js.uncompressed.js"></script>
				<script type="text/javascript" src="../../../js/ofwat/listUtils.js"></script>
				<script type="text/javascript" src="../../../js/ofwat/admin.js"></script>
				<script type="text/javascript" src="../../../js/bootstrap.js"></script>
				<script type="text/javascript" src="../../../js/jquery.pnotify.min.js"></script>				
				<script type="text/javascript" src="../../../js/ofwat/updates.js"></script>
				<script type="text/javascript" src="../../../js/tinymce/tinymce.min.js"></script>
				<script type="text/javascript" src="../../../js/bootstrap-datetimepicker.min.js"></script>
				<script type="text/javascript" src="../../../js/moment.js"></script>
			    <jsp:include page="../../footer.jsp" />
			</div>
		</div>
	</div>

    <script type="text/javascript">
    	Handlebars.registerHelper('date', ofwat.dateHelper);
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<script type="text/javascript">
	var releaseId = <%=request.getParameter("id")%>;
	dojo.addOnLoad(ofwat.viewRelease.onLoad(releaseId));
	$(document).ready(function(){
	    tinymce.init({
	        selector: "#updateDescription",
	        menu: {}
		});
        $('#datetimepicker3').datetimepicker({
          pickTime: false,
          format: 'dd/MM/yyyy hh:mm'
        });
        
        $("#addUpdate").click(function(){
        	ofwat.viewRelease.addupdate();
        });
    	$(".updateButton").click(function(){
    		ofwat.viewRelease.deleteUpdate($(this).data("updateid"));
    	})
    	
    	$("#saveRelease").click(function(){
    		//Save the changes to the release fields.
    		ofwat.viewRelease.updateRelease();
    	});
    	
	});
	</script>

</body>
</html>