<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Company History</title>
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
		<!--
		
		#pdf {
			width: 500px;
			height: 900px;
			margin: 2em auto;
			border: 10px solid #6699FF;
		}
		
		#pdf p {
		   padding: 1em;
		}
		
		#pdf object {
		   display: block;
		   border: solid 1px #666;
		}
		
		-->
		</style>		
</head>
<body>
<div class="bannerContent"></div>
<div class="container-fluid">
<div class='row-fluid' style="margin-right: auto; margin-left: auto; width: 90em;">
<div class="span9">
<h3 class='indexHead'>Company History</h3>
<ul class="nav nav-tabs" id="myTab">
  <li class="active"><a href="#home">Wocs</a></li>
  <li><a href="#profile">Wascs</a></li>
  <li><a href="#messages">Navs</a></li>
</ul>
 
<div class="tab-content">
  <div class="tab-pane active" id="home">
  <div id="pdfWoCs" style="height:800px">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="../../pdf/CompanyStructureWoCs.pdf">Click here to download the PDF</a></div>
  </div>
  <div class="tab-pane" id="profile">
  <div id="pdfWasCs" style="height:800px">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="../../pdf/CompanyStructureWascs.pdf">Click here to download the PDF</a></div>
  </div>
  <div class="tab-pane" id="messages">
  <div id="pdfNavs" style="height:800px">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="../../pdf/CompanyStructureNavs.pdf">Click here to download the PDF</a></div>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
<script type="text/javascript" src="../../js/json2.js"></script>
<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<script type="text/javascript" src="../../js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="../../js/pnotify.custom.min.js"></script>
<script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
<script type="text/javascript" src="../../js/pdfobject.js"></script>
<script type="text/javascript" src="../../js/moment.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	    <ofwat:userRole role="OFWAT\Fountain.Editors">
			isAdmin = true;
		</ofwat:userRole>		
	    Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="My Reports";
		ofwat.wikiPage();				
		$('#myTab a').click(function (e) {
			  e.preventDefault();
			  $(this).tab('show');
		});
		var pdf1 = new PDFObject({ url: "../../pdf/CompanyStructureWascs.pdf" }).embed("pdfWasCs");
		var pdf2 = new PDFObject({ url: "../../pdf/CompanyStructureWoCs.pdf" }).embed("pdfWoCs");
		var pdf3 = new PDFObject({ url: "../../pdf/CompanyStructureNavs.pdf" }).embed("pdfNavs");
	});
</script>
</body>
</html>