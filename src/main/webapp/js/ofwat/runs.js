/*
 *  Copyright (C) 2013 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/*
 * Contains the Java Script code necessary to implement IPL runs, will use the 
 * JQuery library to provide functionality - we are currently using dojo so some of that may be mixed in 
 * where existing functionality is already present. 
 * 
 * This is a transition to replacing Dojo with JQuery.  
 */


//We are going to use JQuery for manipulating the UI as this is where we are going, but existing DOJO
//functionality in ofwat.js will be used as required and to avoid duplication. 

//Views are loaded using Handlebars.ja client side templating these are in the jsp/templates directory.
//The functionality to load the data and template is in ofwat.js the loadTemplate function.



//Create a run 

//Update a run

//Delete a run?

//Create a run template

//Update a run template

//Delete a run template

//Modify the tag on a run?

if(!ofwat){
	alert("missing import: ofwat.js");
}
/*
if(!ofwat.rest){
	alert("missing import: rest.js");
}
if(!ofwat.listUtils){
	alert("missing import: listUtils.js");
}
*/
//if(!ofwat.referenceSelection){
//	alert("missing import: referenceSelection.js");
//}

ofwat.runs = {};

/*
 * Make the REST call to delete a run. 
 */
ofwat.runs.deleteRun = function(){
}

ofwat.runs.setNewDefault = function(runId, agenda){

	var standardClass = agenda + "_STANDARD";
	var defaultClass = agenda + "_DEFAULT";
	var radioName = "defaultRadio_" + agenda;
	
	var confirmation = confirm("Confirmation Needed \n\nYou are changing the default run. Is that OK?");
	if (confirmation == false) {
		var unchangedDefaultRunCheckbox = $("." + defaultClass);
		unchangedDefaultRunCheckbox.prop("checked", true);
		return;
	}

	var xhrArgs = {
            url: "../../../rest-services/runs/default/" + runId,
            postData: "",
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function(data) {
				// change old default to standard
				var oldDefaultRunCheckbox = $("." + defaultClass);
				oldDefaultRunCheckbox.removeClass(defaultClass);
				oldDefaultRunCheckbox.addClass(standardClass);
				// change new default to default
				var justClickedDefaultRunCheckbox = $("input[name=" + radioName + "]:checked");
				justClickedDefaultRunCheckbox.removeClass(standardClass);
				justClickedDefaultRunCheckbox.addClass(defaultClass);
		    },
            error: function(error){
				//Show a notification
				$.pnotify({
					 title: 'Could not set new default.',
					 text: 'Could not set new default: ' + error.responseText + '',
					 type: 'error',
					 opacity: .8,
					 history: false
				});
				var unchangedDefaultRunCheckbox = $("." + defaultClass);
				unchangedDefaultRunCheckbox.prop("checked", true);
			}
        };
	dojo.xhrPost(xhrArgs);
}



ofwat.runs.toggleAdminOnly = function(runId){

	var xhrArgs = {
            url: "../../../rest-services/runs/" + runId + "/toggleAdminOnly",
            postData: "",
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function(data) {
//				alert("Admin only has been changed");
			},
            error: function(error){
				//Show a notification
				$.pnotify({
					 title: 'Could not change admin only.',
					 text: 'Please report the following error and then refresh the page.\nError: ' + error.responseText + '',
					 type: 'error',
					 opacity: .8,
					 history: false
				});
			}
        };
	dojo.xhrPost(xhrArgs);
}


ofwat.runs.tagModel = function(runId, modelId){
	alert(runId + ":" + modelId);	
}


ofwat.runs.getRunTemplates = function(handleData){
	$.get("/Fountain/rest-services/runs/templates", function(data){
		handleData(data);	
	});
}

ofwat.runs.getDataSources = function(handleData){
	$.get("/Fountain/rest-services/runs/datasources", function(data){
		handleData(data);	
	});
}

ofwat.runs.getTags = function(dataSource, handleData){
	$.get("/Fountain/rest-services/runs/templates", function(data){
		handleData(data);	
	});
}

ofwat.runs.showLoadingDialog = function(message){
	var $messageDiv = $('#loading-indicator').find(".modal-body");
	$messageDiv.append("<span class='ajaxMessage'>" + message + "</span>");
	$('#loading-indicator').modal({backdrop: 'static', keyboard:false});
}

ofwat.runs.hideLoadingDialog = function(){
	$('#loading-indicator').modal('hide');
	var $messageDiv = $('#loading-indicator').find(".modal-body").find(".ajaxMessage");
	$messageDiv.remove();	
}

//Add the div for the loading indicator to all pages that use runs.js! 
$(document).ready(function() {
	//Add the timeout div behind the .grid_12
	var $div = $(".grid_12");
	$div.append($('<div id="loading-indicator" class="modal hide"><div class="modal-body"><img src="/Fountain/images/ajax-loader.gif"></img></div></div>'));
	
	$(document).ajaxSend(function(event, request, settings) {
		if(typeof(getLoadingMessage) == "function"){
			ofwat.runs.showLoadingDialog(getLoadingMessage());
		}
	});

	$(document).ajaxComplete(function(event, request, settings) {
		ofwat.runs.hideLoadingDialog();
	});		
	
	$.fn.highlight = function (color) {
		var highlightColor;
		if(color == null){
			highlightColor = "#CCFFCC";
		}else{
			highlightColor = color;
		}
	    $(this).each(function () {
	        var el = $(this);
	        $("<div/>")
	        .width(el.outerWidth())
	        .height(el.outerHeight())
	        .css({
	            "position": "absolute",
	            "left": el.offset().left,
	            "top": el.offset().top,
	            "background-color": highlightColor,//"#ffff99", 
	            "opacity": ".7",
	            "z-index": "9999999"
	        }).appendTo('body').fadeOut(1000).queue(function () { $(this).remove(); });
	    });
	}	
	
	
});

ofwat.runs.createRunTemplate = function(runTemplateName, runTemplateDescription, handleData){
	var runTemplate = {
		name: runTemplateName,
		description: runTemplateDescription
	}
	$.ajax({
		url: '/Fountain/rest-services/runs/templates/',
		type:"PUT",
		data: runTemplate,
		//data: JSON.stringify(runTemplate),
		//contentType: "application/json",
		success: function(data){
			handleData(data);
		}
	});
};


ofwat.runs.createRunCompanyTemplate = function(runCompanyTemplateName, runCompanyTemplateDescription, handleData){
	var runCompanyTemplate = {
		name: runCompanyTemplateName,
		description: runCompanyTemplateDescription
	}
	$.ajax({
		url: '/Fountain/rest-services/runs/companyTemplate/',
		type:"PUT",
		data: runCompanyTemplate,
		//data: JSON.stringify(runTemplate),
		//contentType: "application/json",
		success: function(data){
			handleData(data);
		}
	});
};

function OfwatDragSelect(params){
	
	var _this = this;
	var srcTableId = "table-draggable1";
	var tgtTableId = "table-draggable2";
	var extra;
	var intersectData = function(src, tgt){
		
	}
	
	this.getSourceNode = function(){
		return $("#" + srcTableId);
	}
	
	this.getTargetNode = function(){
		return $("#" + tgtTableId);
	}	
	
	this.init = function(){
		//TODO Check that we have all params and bounce if we don't!
		//Allow us to pass src and tgt table ids!
		if(params.srcTableId != null){
			srcTableId = params.srcTableId;
		}
		if(params.tgtTableId != null){
			tgtTableId = params.tgtTableId;
		}	
		if(params.extra){
			extra = params.extra;
		}
		createNodes(params.targetNode, extra);
		initDragAndDrop(extra);				
		getData(params.sourceUrl, params.targetUrl, function(xhrObjSrc, xhrObjTarget, sourceData, targetData){
			//update src so that it only shows those in srcArray
			_this.displayData(sourceData, targetData, srcTableId, tgtTableId);
			removeDuplicates();
		});

	}
	
	var removeDuplicates = function(){
		var srcIds = parseListToArray(srcTableId, true);
		var tgtIds = parseListToArray(srcTableId, true);
		//remove rows where data-key matches ID in duplicates.
		var trs= $("#" + srcTableId + " li");	
		trs.each(function(index, value){ //need to exclude
			//get the first td
			$td = $(value).find("li:first-child")
			value = $td.data("key");
			var pos = tgtIds.indexOf(value);
			if(pos > -1){
				//remove value from the table
				$(value).remove();
			}
		});
	}

	//private
	var createNodes = function(node, extra){
		//Needs to create this replacing the passed node. :
		var classId = "connectedSortable";
		if((extra != null) && (extra.modelId != null)){
			classId = "connectedSortable_" + extra.modelId 
		}
		/*
		var htmlStr = '<div class="grid_5">' + 
		'<h5>' + params.sourceTitle + '</h5>' +
		'<table id="' + srcTableId +'" class="table table-striped table-bordered">' +
		'<tbody class="'+ classId +'">' +
			'<tr><th>Id</th><th>Name</th><th>Code</th></tr>' +
		'</table>' +
		'</div>' +
		'<div class="grid_1">&gt;&gt;</div>' +
		'<div class="grid_5">' +
		'<h5>' + params.targetTitle + '</h5>' +
		'<table id="' + tgtTableId + '" class="table table-striped table-bordered">' +
		'<tbody class="' + classId + '">' +
			'<tr><th>Id</th><th>Name</th><th>Code</th></tr>' +
			'</tbody>' +
		'</table>' + 
		'</div>';
		*/
		var htmlStr = '<div class="grid_5 ulContainer">' +
			'<ul id="' + srcTableId +'" class="'+ classId +'" style="margin:0px; min-height:30px;">' +
			'</ul>' + 
			'</div>' + 
			'<div class="grid_1">&gt;&gt;</div>' + 
			'<div class="grid_5 ulContainer">' + 
			'<ul id="' + tgtTableId +'" class="'+ classId +'"  style="margin:0px; min-height:30px;">' + 
			'</ul>' + 
			'</div>';
		
		var $node = $(node);
		var $newNode = $.parseHTML(htmlStr);
		$node.replaceWith($newNode);
		/*$('.ulContainer').mouseout(function(){
			//remove the div!
			$('.ulContainer').find(".ui-sortable-placeholder-fountain").remove();
			console.log("mouse out...");
		})*/		
	}			
	
	var  initDragAndDrop = function(extra){
		var classId = "connectedSortable";
		if((extra != null) && (extra.modelId != null)){
			classId = "connectedSortable_" + extra.modelId; 
		}
		
	    $( "ul." + classId)
        .sortable({
            connectWith: "." + classId,
            items: "li",
            //appendTo: $tabs,
            helper:"clone",
            zIndex: 999990,
            placeholder:"ui-sortable-placeholder-fountain",
            forcePlaceholderSize: false,
            start: function(){ },
            stop: function(){
            	//parseTableToArray(tgtTableId, true);
            	removeDuplicates();
            	//make the post call with the added item?
            }
        })
        .droppable({
        	accept: "." + classId + " li",
        	hoverClass: "ui-state-hover",
		    drop: function( event, ui ) {
				//Potentially fire an event to update fountain here.
		    	//parseTableToArray($(this).parent()[0]);
		    	//we can get the TR from the ui[0] or similar, but we also need to check the source and target. 
		    	//on source drop
		    	
		    	//Element data-key value
		    	var id = ui.draggable.data("key")
		    	var result = false;
		    	if(event.target.id == srcTableId){
		    		//it was dropped on the source.
			    	if(params.onSourceDrop != null){
			    		result = params.onSourceDrop(id, extra);
			    		if(result == true){
			    			successHighlight($(event.target));
			    		}else{
			    			errorHighlight($(event.target));
			    		}			    		
			    	}
		    	}else{
		    		//it was dropped on the target.
			    	if(params.onTargetDrop != null){
			    		result = params.onTargetDrop(id, extra);
			    		if(result == true){
			    			successHighlight($(event.target));
			    		}else{
			    			errorHighlight($(event.target));
			    		}
			    	}
		    	}
			}	        	
        }).disableSelection();
        
        var successHighlight = function(element){
        	console.log(element);
        	//element.fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100);
        	$.highlight(element);
	    }
	    
        var errorHighlight = function(element){
        	console.log(element);
        	//element.fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100);
	    }
        
        				
	}
	
	var getData = function(sourceUrl, targetUrl, callback){
		//Get the data for source and target and initialise the View. 
		var promiseSrc = $.get(sourceUrl);
		//Get a ref to the context.
		var promiseTarget = $.get(targetUrl);
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
				
		for(var i=0;i<source.length;i++){
			$('#' + tableSrcId).append('<li class="ui-state-default" data-key="' + source[i].key + '">' + source[i].value.name + ' : ' + source[i].value.code + '</li>');	
		}
				
		for(var i=0;i<target.length;i++){
			$('#' + tableTgtId).append('<li class="ui-state-default" data-key="' + target[i].key + '">' + target[i].value.name + ' : ' + target[i].value.code + '</li>');
		}					
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
	this.init();
}

