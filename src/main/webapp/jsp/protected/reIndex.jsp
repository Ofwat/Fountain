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
	
</head>
<body>
<div class="bannerContent"></div>

<p></p>
<div class="container_12">

<h3>Update Search Indexes</h3>
<div>
	<div id="response"></div>

	<div id="reports" class="grid_12">

		<div id = "alert_placeholder"></div>

		<!-- Re-index item form -->
		<form id="reindexItemForm">
		  <fieldset>
		    <legend>Re-Index Items</legend>
		    <input type="text" placeholder="1,2,3,4..." id="itemIds">
		    <span class="help-block">Enter a comma separated list of item ids or leave blank to re-index all items.</span>
		    <button class="btn" type="button" id="indexItems">Index Items</button>
		  </fieldset>
		</form>		
		<!-- Re-index report form -->
		<form id="reindexReportForm">
		  <fieldset>
		    <legend>Re-Index Reports</legend>
		    <input type="text" placeholder="1,2,3,4..." id="reportIds">
		    <span class="help-block">Enter a comma separated list of report ids or leave blank to re-index all reports.</span>
		    <button class="btn" type="button" id="indexReports">Index Reports</button>
		  </fieldset>
		</form>				
		<!-- Re-index table form -->
		<form id="reindexTableForm">
		  <fieldset>
		    <legend>Re-Index Tables</legend>
		    <input type="text" placeholder="1,2,3,4..." id="tableIds">
		    <span class="help-block">Enter a comma separated list of table ids or leave blank to re-index all tables.</span>
		    <button class="btn" type="button" id="indexTables">Index Tables</button>
		  </fieldset>
		</form>			
		
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header" aria-hidden="false">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="false">×</button>
		    <h3 id="myModalLabel" id="modalHeader" id="modalHeader"></h3>
		  </div>
		  <div class="modal-body" id="modalBody" aria-hidden="false">
		  </div>
		  <div class="modal-footer" aria-hidden="false">
		    <button class="btn" data-dismiss="modal" aria-hidden="false">Close</button>
		    <button id="reIndexConfirm" class="btn btn-primary">Ok</button>
		  </div>
		</div>		
		
		
		
	<script type="text/javascript" src="../../js/json2.js"></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../js/dojo/dojo.js.uncompressed.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/listUtils.js"></script> 
	<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="../../js/bootstrap.js"></script>		
	<script type="text/javascript" src="../../js/moment.js"></script>
	<!-- <script type="text/javascript" src="../../js/ofwat/admin.js"></script>  -->

    <script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

		<jsp:include page="../footer.jsp" />	
	</div>
	
</div>

</div>
	<script type="text/javascript">
		//dojo.addOnLoad(function(){

		$().ready(function(){	
			
			bootstrap_alert = function() {}
			bootstrap_alert.warning = function(message) {
				$('#alert_placeholder').html('<div class="alert"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')
			}			
			
			
			$("#indexReports").click(function(){
				var reportIds = $("#reportIds").val();
				if((reportIds == '') || (reportIds == null)){
					$("#modalHeader").html("Re-index All Reports");
					$("#modalBody").html("<p>Are you sure you wish to re-index all reports in Fountain? - this may take a while!</p>");
				}else{
					$("#modalHeader").html("Re-index Selected Reports");
					$("#modalBody").html("The following reports will be re-indexed: " + reportIds);
				}
				
				//This is being added twice...
				$("#reIndexConfirm").unbind();
				$("#reIndexConfirm").click(function(){
					//Ok, make the ajax call here. 
					var xhr = $.ajax({
						  type: "POST",
						  url: "/Fountain/rest-services/search/index/report?reportIds=" + $("#reportIds").val(),
						  success: function(){
							  //Show the success
							  $('#myModal').modal('hide');
							  bootstrap_alert.warning('Indexed reports.');
						  },
						  dataType: "JSON"
						}).always(function() {
							//clear the modal;
							bootstrap_alert.warning("Thanks, we have started the indexing process.");
						}).fail(function() {
						    bootstrap_alert.warning("Sorry we couldn't start the indexing process.");
						});
				});
				$('#myModal').modal();
			});
			
			$("#indexItems").click(function(){
				var itemIds = $("#itemIds").val();
				if((itemIds == '') || (itemIds == null)){
					$("#modalHeader").html("Re-index All Items");
					$("#modalBody").html("<p>Are you sure you wish to re-index all items in Fountain? - this may take a while!</p>");
				}else{
					$("#modalHeader").html("Re-index Selected Items");
					$("#modalBody").html("The following items will be re-indexed: " + reportIds);
				}
				$("#reIndexConfirm").unbind();
				$("#reIndexConfirm").click(function(){
					//Ok, make the ajax call here. 
					var xhr = $.ajax({
						  type: "POST",
						  url: "/Fountain/rest-services/search/index/item?itemIds=" + $("#itemIds").val(),
						  success: function(){
							  //Show the success
							  $('#myModal').modal('hide');
							  bootstrap_alert.warning('Indexed items.');
						  },
						  dataType: "JSON"
						}).always(function() {
							//clear the modal;
							bootstrap_alert.warning("Thanks, we have started the indexing process.");
						}).fail(function() {
						    bootstrap_alert.warning("Sorry we couldn't start the indexing process.");
						});
				});
				$('#myModal').modal();
			});			
			
			$("#indexTables").click(function(){
				var tableIds = $("#tableIds").val();
				if((tableIds == '') || (tableIds == null)){
					$("#modalHeader").html("Re-index All Tables");
					$("#modalBody").html("<p>Are you sure you wish to re-index all tables in Fountain? - this may take a while!</p>");
				}else{
					$("#modalHeader").html("Re-index Selected Tables");
					$("#modalBody").html("The following tables will be re-indexed: " + tableIds);
				}
				$("#reIndexConfirm").unbind();
				$("#reIndexConfirm").click(function(){
					//Ok, make the ajax call here. 
					var xhr = $.ajax({
						  type: "POST",
						  url: "/Fountain/rest-services/search/index/table?tableIds=" + $("#tableIds").val(),
						  success: function(){
							  //Show the success
							  $('#myModal').modal('hide');
							  bootstrap_alert.warning('Indexed tables.');
						  },
						  dataType: "JSON"
						}).always(function() {
							//clear the modal;
							bootstrap_alert.warning("Thanks, we have started the indexing process.");
						}).fail(function() {
						    bootstrap_alert.warning("Sorry we couldn't start the indexing process.");
						});
				});
				$('#myModal').modal();
			});			
			
			
			
		});	 
	</script>
	
</body>
</html>