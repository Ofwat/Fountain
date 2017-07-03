<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Report Help</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1; IE=8"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- CSS Reset sheet, minified version, written by yahoo -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
		<!-- 960.gs grid sheet -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
		<link rel="stylesheet" type="text/css" href="/Fountain/css/layout.css"/>
	    <link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
		<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
		<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap-filterable.css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap-tagsinput.css" media="screen"/>
		<link href="/Fountain/css/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/Fountain/css/font-awesome.css"  type="text/css"  media="all"/>
		<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
		<style type="text/css">
			body .modal {
			    /* new custom width */
			    width: 1066px;
    			height: 700px;
    			margin-left: -503px;
			    /* must be half of the width, minus scrollbar on the left (30px) */
			}		
			body .modal-body {
			    /* new custom width */
			    overflow-y: hidden;
			    max-height: 600px;
			}
		</style>		
</head>
<body>
<div class="bannerContent"></div>

<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../../js/svg-pan-zoom.min.js"></script>
<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
<script type="text/javascript" src="../../js/json2.js"></script>
<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<script type="text/javascript" src="../../js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="../../js/pnotify.custom.min.js"></script>
<script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
<script type="text/javascript" src="../../js/SVG-inject.js"></script>
<script type="text/javascript" src="../../js/moment.js"></script>   
<script type="text/javascript">
	$(document).ready(function(){
		var options = {show:false};
		$('#myModal').modal(options);
		
		$("#container").width = $(window).width();
		$("#container").height = $(window).height();
		
		
		window.showModel = function(id){
			console.log("Showing model with id: " + id);
			//Show a pop up the same as index.jsp but load the appropriate HTML5 firl in the display. 
			$('#screenCastFrame')[0].height = 800;
			$('#screenCastFrame')[0].width = 1000;
			
			var itemId = id.slice(0,id.length-1);
			var src = "/Fountain/screenCasts/"
			switch(itemId) {
				case "g336": //Temp fix for bug in SVG.
					break;
			    case "item":
			    	src = src + "ItemsTab/HTML5/practice.html";
			        break;
			    case "interval":
			    	src = src + "YearsTab/HTML5/practice.html";
			        break;
			    case "company":
			    	src = src + "CompaniesTab/HTML5/practice.html";
			        break;		
			    case "run":
			    	src = src + "RunsTab/HTML5/practice.html";
			        break;	
			    case "data":
			    	src = src + "RunsTab/HTML5/practice.html";
			        break;	
			    case "layout":
			    	src = src + "LayoutTab/HTML5/practice.html";
			        break;				        
			    case "display":
			    	src = src + "DisplayOptions/HTML5/practice.html";
			        break;	
			    case "save":
			    	src = src + "Save/HTML5/practice.html";
			        break;				        
			    default:
			    	console.log("Couldn't find item: " + itemId);
			}
		    $('#myModal').modal('show');
		  	$('#screenCastFrame')[0].src = src;//"/Fountain/screenCasts/CompaniesTab/HTML5/practice.html"
		} 
		
		
	    <ofwat:userRole role="OFWAT\Fountain.Editors">
		isAdmin = true;
		</ofwat:userRole>		
		
		
	    Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="My Reports";
		ofwat.wikiPage();	
		loadSvg();
	      var test = svgPanZoom('#svg3336', {
	          zoomEnabled: true,
	          controlIconsEnabled: true,
	          fit: true,
	          center: true,
	          // viewportSelector: document.getElementById('demo-tiger').querySelector('#g4') // this option will make library to misbehave. Viewport should have no transform attribute
	        });
	      
	      //$("#item1").mouseover(function(){alert("over!!! item 1")});
	});
</script>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"></h3>
  </div>
  <div class="modal-body">
  <iframe id="screenCastFrame"></iframe>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>	
<div id="container" style="border:1px solid black; overflow-x:hidden; overflow-y:hidden;">

</div>	
</body>
</html>