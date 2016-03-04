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
	<title>Fountain: Edit Model Dependencies</title>
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
			<form class="form-horizontal">
				<fieldset>
					<!-- Form Name -->
					<legend>Edit Model Dependencies</legend>

					<div class="control-group">
						<div id="modelList"></div>					
					</div>					
					
					<!-- Button (Double) -->
					<!-- 
					<div class="control-group">
					  <label class="control-label" for="btnSave"></label>
					  <div class="controls">
					    <button id="btnSave" name="btnSave" class="btn btn-primary">Save</button>
					    <button id="btnCancel" name="btnCancel" class="btn btn-default">Cancel</button>
					  </div>
					</div>
					-->		
				</fieldset>
			</form>		
		</div>
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
    <script type="text/javascript" src="../../../js/ofwat/runs.js"></script>
    <script type="text/javascript" src="../../../js/jquery/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap.js"></script>
    <!-- <script type="text/javascript" src="../../../js/underscore-min.js"></script> -->		
	<script type="text/javascript" src="../../../js/moment.js"></script>   
    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../../footer.jsp" />
	
	<script type="text/javascript">
		
		dojo.addOnLoad(function(){

			//List the models!
			ofwat.loadTemplate(dojo.byId("modelList"), ofwat.template.externalModelList);
			
			var $companyLists = $(".innerModelCompanies");
			$.each($companyLists, function(index, value) {
				$value = $(value);
				$value.on('shown', function() {
					var $div = $(value);
					//need to get parent/parent/prev sib/1st td, 1st button 
					var b = $("#demo_" + $div.data("id")).parent().parent()
							.prev().first()
					b = b.find(":button")[0]
					b.innerHTML = "-";
				});
				$value.on('hide', function() {
					var $div = $(value);
					//need to get parent/parent/prev sib/1st td, 1st button 
					var b = $("#demo_" + $div.data("id")).parent().parent()
							.prev().first()
					b = b.find(":button")[0]
					b.innerHTML = "+";
				})
			});			
			
			
			
			//For each model create one of these!
			
			//Get all divs in page with class: dependencyPicker
			//iterate over them.
			var $models = $(".dependencyPicker");
			$.each($models, function(index, value) {
				var $div = $(value);
				var _this = this;
				var modelId = $div.data("id");
				var ofwatDragSelect = new OfwatDragSelect({
					sourceUrl:"/Fountain/rest-services/model?typeId=5", 
					targetUrl:"/Fountain/rest-services/model/dependency/" + modelId, //?
					targetNode:$div, //Where we insert the drag and drop in the document.
					sourceTitle:"Available Models",
					targetTitle:"Selected Dependencies",
					srcTableId:"srcTable_" + index,
					tgtTableId:"tgtTable_" + index,
					sourceDataIdField:"id",
					targetDataIdField:"id",
					sourceItemValueFunction:null,
					targetItemValueFunction:null,
					extra:{modelId:"" + $div.data("id")},
					onSourceDrop: function(id, extra){
						//alert("source drop");
						//remove from fountain template list for run!
						//removeTemplateModel(templateId, id);
						$.ajax({
							type: "DELETE",
							url: "http://localhost:8080/Fountain/rest-services/model/dependency/" + extra.modelId + "?dependencyId=" + id,
							success: function(data){
								console.log(data);		  
							},
							error:function(jqXHR){
								
							}
						});									
					},
					onTargetDrop: function(id, extra){
						//alert("target drop");
						//add to fountain run template list for run!
						//addTemplateModel(templateId, id);
						//Add a model dependency for this modelId and dependencyId passed!
						$.ajax({
							type: "POST",
							url: "http://localhost:8080/Fountain/rest-services/model/dependency/" + extra.modelId + "?dependencyId=" + id, 
							success:function(data){
								console.log(data);	
							},
							error:function(jqXHR){
								
							}
						});						
					}
				});			
			});

			/*
			function removeTemplateModel(templateId, modelId){
				//Jquery/Dojo DELETE  
				$.ajax({
					type: "DELETE",
					url: "http://localhost:8080/Fountain/rest-services/runs/templates/" + templateId + "/models?modelId=" + modelId,
					success: function(data){
						console.log(data);		  
					},
					error:function(jqXHR){
						
					}
				});				
				
			}
			
			function addTemplateModel(templateId, modelId){
				//Jquery/dojo POST
				$.ajax({
					type: "PUT",
					url:"http://localhost:8080/Fountain/rest-services/runs/templates/" + templateId + "/models?modelId=" + modelId, 
					success:function(data){
						console.log(data);	
					},
					error:function(jqXHR){
						
					}
				});
			}
			*/
		});
	</script>
	
</body>
</html>