<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Company, uk.gov.ofwat.fountain.api.CompanyService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Edit Company Set</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/fountainNew.css"/>
	<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
	<link href="/Fountain/css/jquery.pnotify.default.css" media="all" rel="stylesheet" type="text/css" />	
	<!-- Include this file if you are using Pines Icons. -->
	<link href="/Fountain/css/jquery.pnotify.default.icons.css" media="all" rel="stylesheet" type="text/css" />		
	<style type="text/css">
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
		<legend>Edit Company Set</legend>
		
		<!-- Text input-->
		<div class="control-group">
		  <label class="control-label" for="txtName">Name:</label>
		  <div class="controls">
		    <input id="txtName" name="txtName" type="text" class="input-large" required="" disabled="disabled">
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="control-group">
		  <label class="control-label" for="txtDescription">Description:</label>
		  <div class="controls">                     
		    <textarea id="txtDescription" name="txtDescription" disabled="disabled"></textarea>
		  </div>
		</div>
		
		<div id="selectExternalModels"></div>
					<table class="itemTable">
						<tr>
							<th>Companies:</th><th></th><th>Selected Companies</th><th></th>
						</tr>
						<tr>
							<td id="results">
								<select class="itemChosenListLeft" style="width:350px;" multiple="multiple" id="itemStartList" size="20">
								<!-- Populated from the search results -->
									<option value="0">&nbsp;</option>
								</select>
							</td>
							<td class="centre" style="vertical-align:top;">
								<button id="addItem" class="btn" style="success" type="button" onclick="ofwat.listUtils.move('itemStartList', 'itemChosenList', false);">Add</button>
								<br />
								<button id="removeItem" class="btn" style="btn-warning" type="button" onclick="ofwat.listUtils.move('itemChosenList', 'itemStartList', false);">Remove</button>
							</td>
							<td><select class="itemChosenListRight" style="width:350px;" multiple="multiple" id="itemChosenList" size="20">
								<!-- Populated from the selected search results -->
								</select>
							</td>
							<td style="vertical-align:top;">
								<button href="#" id="btnMoveSelectedUp" class="btn btn-mini" type="button"><i class="icon-chevron-up"></i></button><br/>
								<button href="#" id="btnMoveSelectedDown" class="btn btn-mini" type="button"><i class="icon-chevron-down"></i></button><br/>
							</tr>
					</table>			
		</div>
		<!-- Drag and drop selection! -->
		<div id="dragAndDropTargetNode"/>
		
		<!-- Button (Double) -->
		 
		<div class="control-group">
		  <label class="control-label" for="btnSave"></label>
		  <div class="controls">
		    <button id="btnSave" name="btnSave" class="btn btn-primary" type="button">Save</button>
		    <a href="/Fountain/jsp/protected/runs/listRunCompanyTemplates.jsp" id="btnCancel" name="btnCancel" class="btn btn-default">Cancel</a>
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
    <script type="text/javascript" src="../../../js/ofwat/runs.js"></script>	
    <script type="text/javascript" src="../../../js/bootstrap.js"></script>
	<script type="text/javascript" src="../../../js/jquery.pnotify.min.js"></script>    
	<script type="text/javascript" src="../../../js/moment.js"></script>   
    <!-- <script type="text/javascript" src="../../../js/underscore-min.js"></script> -->		
	
	<script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>
	
	<jsp:include page="../../footer.jsp" />
	
	<script type="text/javascript">
	var params = {};		
	dojo.addOnLoad(function(){
		var templateId = <%= request.getParameter("runId") %>;
		var originalTgts = [];
		
		$("#btnMoveSelectedUp").click(function(){
			ofwat.listUtils.ListItemMove(-1, 'itemChosenList');
		});
		$("#btnMoveSelectedDown").click(function(){
			ofwat.listUtils.ListItemMove(1, 'itemChosenList');
		});
		
		//get the template details from the passed id.		
		var xhr = $.get("/Fountain/rest-services/runs/companyTemplate/" + templateId, function(data){
			console.log(data);
			$("#txtName").val(data.name);
			$("#txtDescription").val(data.description);
		});
		xhr.error(function(error){
			alert("Unable to get the template!");
		});
		
		
		$("#btnSave").click(function(){
			var selectedTgtIds = [];
			$('#itemChosenList option').each(function(index, val) { 
				selectedTgtIds.push({"id":$(this).attr('value'),displayOrder:index});
			});
			console.log(selectedTgtIds); //This is an ordered list of all the new tgtIds. 
			//POST THE new objec to the server to remove all templates and add the ones here!
			$.ajax({
				type: "POST",
				data:JSON.stringify(selectedTgtIds),
				url:"/Fountain/rest-services/runs/companyTemplate/" + templateId + "/company", 
				contentType: "application/json; charset=utf-8",
				success:function(data){
					//ofwatDragSelect.getTargetNode().highlight('green');
					//Show a notification
					$.pnotify({
						title: 'Updated Company Set',
					    text: 'Successfully updated Company Set',
					    type: 'success',
					    opacity: .8,
					    history: false
					});							
					
				},
				error:function(jqXHR){
					//ofwatDragSelect.getTargetNode().highlight('red');
					//Show a notification
					$.pnotify({
						 title: 'Could not update Company Set.',
						 text: 'Could not update Company Set: ' + error.responseText + '',
						 type: 'error',
						 opacity: .8,
						 history: false
					});							
				}
			});				
		});
		//Load all the external models.
		
		this.getData = function(sourceUrl, targetUrl, callback){
			//Get the data for source and target and initialise the View. 
			var promiseSrc = $.ajax({url: sourceUrl, dataType: "json"});
			//Get a ref to the context.
			var promiseTarget = $.ajax({url: targetUrl, dataType: "json"});
			var fullSource = [];
			var target = [];

			$.when(promiseSrc, promiseTarget).done(function(xhrObjSrc, xhrObjTarget){
				//Build the data object (Should be genericised) - part way there.
				var srcData = xhrObjSrc[0];
				//parse the source and target data into a data structure of [{key:X, value{...}},...]
				for(var i=0;i<srcData.length;i++){
					var srcItem = {};
					if(params.sourceDataIdField == null){
						srcItem.key = srcData[i].id;
					}else{
						srcItem.key = srcData[i][params.sourceDataIdField];
					}
					if(params.sourceItemValueFunction == null){
						srcItem.value = {'name':srcData[i].name,'code':srcData[i].code};
					}else{
						srcItem.value = params.sourceItemValueFunction(tgtData[i]);
					}
					fullSource.push(srcItem);
				}
				var tgtData = xhrObjTarget[0];
				for(var i=0;i<tgtData.length;i++){
					var tgtItem = {}
					if(params.targetDataIdField == null){
						tgtItem.key = tgtData[i].id;
					}else{
						tgtItem.key = tgtData[i][params.targetDataIdField];
					}
					if(params.targetItemValueFunction == null){
						tgtItem.value = {'name':tgtData[i].name,'code':tgtData[i].code};
					}else{
						tgtItem.value = params.targetItemValueFunction(tgtData[i]);
					}
					target.push(tgtItem);
				}						
				
				//Call the template.
				//Add a row to each table for src and tgt
				//loop over source and if existing in target then remove from source!
				callback(xhrObjSrc, xhrObjTarget, fullSource, target);
			});								
		}
		
		this.displayData = function(fullSource, target, tableSrcId, tableTgtId){
			var source = [];
			for(var i=0;i<fullSource.length;i++){
				var found = false;
				for(var j=0;j<target.length;j++){
					if(fullSource[i].key == target[j].key){
						found = true;
						break;							
					}
				}
				if(!found){
					source.push(fullSource[i]);
				}
			}
				
			var data = {};
			data.source = source;
			data.target = target;				
			$('#' + tableSrcId).empty();	
			$('#' + tableTgtId).empty();
			for(var i=0;i<source.length;i++){
				$('#' + tableSrcId).append('<option class="ui-state-default" value="' +  source[i].key + '" data-key="' + source[i].key + '">' + source[i].value.name + ' : ' + source[i].value.code + '</option>');	
			}
					
			for(var i=0;i<target.length;i++){
				$('#' + tableTgtId).append('<option class="ui-state-default" value="' +  target[i].key + '" data-key="' + target[i].key + '">' + target[i].value.name + ' : ' + target[i].value.code + '</option>');
			}					
		}
		
		
		//Load all the selected models.
		var handler = function(sourceData, tgtData){
			//Get a list of everything that is in tgt to start with. 
			for(var i=0;i<tgtData.length;i++){
//				originalTgts.push({"id":tgtData[i].key, "displayOrder":tgtData[i].value.displayOrder});	
				originalTgts.push({"id":tgtData[i].key, "displayOrder":i});	
			}
			displayData(sourceData,tgtData,"itemStartList","itemChosenList");
		}
		var test = function(a,b,c,d){console.log(c);console.log(d);handler(c,d)};
		getData("/Fountain/rest-services/company", "/Fountain/rest-services/runs/companyTemplate/" + templateId + "/company", test);			
		$("#itemChosenList").change(function(){
		    var str;
			$( "#itemChosenList option:selected" ).each(function() {
		        str += $( this ).text() + " ";
		      });	
		});
		
		
		var parseOptionToArray = function(optionId){
			var data = []
			//iterate over all table rows (except the first and build the array of ids.)
			//var trs = $("#table-draggable2").find("tr:gt(0)");
			var lis = $("#" + optionId).find("option");	
			lis.each(function(index, value){
				//get the first td
				value = $(value).data("key");
				data.push(value);
			});
			return data;		
		}
		
		var parseListToArray = function(listId){
			var data = []
			//iterate over all table rows (except the first and build the array of ids.)
			//var trs = $("#table-draggable2").find("tr:gt(0)");
			var lis = $("#" + listId).find("li");	
			lis.each(function(index, value){
				//get the first td
				value = $(value).data("key");
				data.push(value);
			});
			return data;		
		}
		
		var parseTableToArray = function(tableId, skipFirstRow){
			var data = []
			//iterate over all table rows (except the first and build the array of ids.)
			//var trs = $("#table-draggable2").find("tr:gt(0)");
			var trs;
			if(skipFirstRow == true){
				trs = $("#" + tableId + " tr:gt(0)");	
			}else{
				trs = $("#" + tableId + " tr");
			}
			
			trs.each(function(index, value){ //need to exclude th
				//get the first td
				$td = $(value).find("td:first-child")
				value = $td.data("key");
				data.push(value);
			});
			return data;
		}			
	});
	</script>
	
</body>
</html>