/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
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
// dependency check
if(!ofwat){
	alert("missing import: ofwat.js");
}
if(!ofwat.rest){
	alert("missing import: rest.js");
}
ofwat.editor = {
};

var editing = false;

ofwat.editor.startEdit = function () {
	ofwat.editor.createServerBasket();
  	// make sure the page displays the up to date data
	if(ofwat.reportDisplayPage){
		ofwat.reportDisplayPage.writeEditingCompanyId();
		ofwat.reportDisplayPage.writeEditingDataTagId();
	}
  	location.href = ofwat.editor.groupIndexRewrite();
};

	
ofwat.editor.styleForEditing = function () {
	// get all the editable inputs, toggle whether it's currently editable or not.
	if(dijit.byId("editButton") != null){
		dijit.byId("editButton").setAttribute('disabled', true);
		editing = true;
		dijit.byId("saveButton").setAttribute('disabled', false);
		dijit.byId("cancelEditing").setAttribute('disabled', false);
	}else{
		$("#editButton").attr("disabled", "disabled");
		editing = true;
		$("#saveButton").removeAttr("disabled");
		$("#cancelEditing").removeAttr("disabled");
	}
};
	
ofwat.editor.valueCellConfig = function(dataCell, dto, dictionaryFunc, codeSelects, onchInputFunc, onchCodeListFunc, lastChunkCell) {
	dojo.removeAttr(dataCell.parentNode, "onclick");
	//dojo.attr(dataCell.parentNode, "onclick", dictionaryFunc);
	dojo.attr(dataCell.parentNode, "title", dto.item.code);
	
	var chunkCellClass = "chunkCellLast-" + (typeof lastChunkCell === 'undefined') ? 'false' : x;
	
	var CHUNK_CELL_CLASS = "chunkCellLast";
	var $lastCell = $("." + CHUNK_CELL_CLASS);
	$lastCell.removeClass(CHUNK_CELL_CLASS);
	
	//if (dto.value){
		if (dto.item.codeList) {
			if (editing &&
				true === dto.editable){
				dataCell.innerHTML = codeSelects[dto.item.codeList.id];
				ofwat.editor.dropDownCellSelected(dto.value, dataCell);
				ofwat.editor.cellEditConfig(dto, dataCell, onchCodeListFunc);
			}
			else {
				if (dto.updatedValue) {
					dataCell.innerHTML = "<input type=\"text\" class=\"" + CHUNK_CELL_CLASS + " " + dojo.attr(dataCell.parentNode, "class") + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.updatedValue) + "'></input>";
				}
				else {
					dataCell.innerHTML = "<input type=\"text\" class=\"" + CHUNK_CELL_CLASS + " " + dojo.attr(dataCell.parentNode, "class") + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.value) + "'></input>";
				}
				
				ofwat.editor.cellEditConfig(dto, dataCell, null);
			}
		}
		else {
			if (dojo.hasClass(dataCell.parentNode, "calc")) {
				if (dto.updatedValue) {
					dataCell.innerHTML = ofwat.escapeHTML(dto.updatedValue);
				}
				else {
					dataCell.innerHTML = ofwat.escapeHTML(dto.value);
				}
			}
			else {
				// input
				var textCellClass = "";
				if (dto.item.unit == "Text") {
					textCellClass = " textCell";
				}
				if (dto.updatedValue) {
					dataCell.innerHTML = "<input type=\"text\" class=\"" + CHUNK_CELL_CLASS + " " + dojo.attr(dataCell.parentNode, "class") + textCellClass + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.updatedValue) + "'></input>";
				}
				else {
					dataCell.innerHTML = "<input type=\"text\" class=\"" + CHUNK_CELL_CLASS + " " + dojo.attr(dataCell.parentNode, "class") + textCellClass + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.value) + "'></input>";
				}
				ofwat.editor.cellEditConfig(dto, dataCell, onchInputFunc);
			 }
		 }
//	} 
//	else {
//		ofwat.editor.cellWithNoValue(dataCell);
//	}
		//dojo.attr(dataCell.children[0], "onfocus", dictionaryFunc);
		//dojo.attr(dataCell.children[0], "onblur", ofwat.dictionary.clearPopovers);
		$(dataCell.children[0]).focus({dtoKey:dataCell.id, divId:dataCell.id, node:dataCell, dto:dto}, dictionaryFunc);
		$(dataCell.parentNode).click({dtoKey:dataCell.id, divId:dataCell.id, node:dataCell, dto:dto}, dictionaryFunc);			
		$(dataCell.children[0]).blur(ofwat.dictionary.clearPopovers);
};

ofwat.editor.confidenceGradeCellConfig = function(params) {
	
	var cgCell = params.dataCell;
	var dto = params.dto;
	var dictionaryFunc = params.dictionaryFunc;
	var cgSelect = params.cgSelect;
	var onchCGFunc = params.onchCGFunc;
	
	dojo.removeAttr(cgCell.parentNode, "onclick");
	//dojo.attr(cgCell.parentNode, "onclick", dictionaryFunc);
	dojo.attr(cgCell.parentNode, "title", dto.item.code);
//	if (dto.confidenceGrade) {
		if (editing &&
			true === dto.editable){
				cgCell.innerHTML = cgSelect;
				if (dto.updatedConfidenceGrade) {
					ofwat.editor.dropDownCellSelected(dto.updatedConfidenceGrade, cgCell);
				}
				else {
					ofwat.editor.dropDownCellSelected(dto.confidenceGrade, cgCell);
				}
				ofwat.editor.cellEditConfig(dto, cgCell, onchCGFunc);
		}
		else {
			if (dto.updatedConfidenceGrade) {
				cgCell.innerHTML = "<input type=\"text\" class=\"" + dojo.attr(cgCell.parentNode, "class") + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.updatedConfidenceGrade) + "'></input>";
			}
			else {
				cgCell.innerHTML = "<input type=\"text\" class=\"" + dojo.attr(cgCell.parentNode, "class") + " disabled\" readonly=\"readonly\" value='" + ofwat.escapeHTML(dto.confidenceGrade) + "'></input>";
			}
			ofwat.editor.cellEditConfig(dto, cgCell, null);
		}
//	}
//	else {
//		ofwat.editor.cellWithNoValue(cgCell);
//	}
	//Need to pass the actual div here too!
	
	//dojo.attr(cgCell.children[0], "onfocus", dictionaryFunc);
	$(cgCell.children[0]).focus({dtoKey:dto.identifier, divId:dto.identifier, node:cgCell}, dictionaryFunc);
	$(cgCell.parentNode).click({dtoKey:dto.identifier, divId:dto.identifier, node:cgCell}, dictionaryFunc);		
};


ofwat.editor.cellEditConfig = function(dto, cell, onchFunc) {
	if (true === dto.editable){
		if (true === dto.locked){
			dojo.addClass(cell.firstChild, "locked");
		}
		else {
			dojo.addClass(cell.firstChild, "edit");
			dojo.removeAttr(cell.firstChild, "onchange");
			if (onchFunc) {
				dojo.attr(cell.firstChild, "onchange", onchFunc);
			}

			if (editing) {
				dojo.addClass(cell.firstChild, "editing");
				if (cell.firstChild.nodeName == "INPUT") {
					dojo.removeAttr(cell.firstChild, 'readonly');
				}
				if (cell.firstChild.nodeName == "SELECT") {
					dojo.removeAttr(cell.firstChild, 'disabled');
				}
			}
			else {
				// not editing
				dojo.removeClass(cell.firstChild, "editing");
				dojo.attr(cell.firstChild, 'readonly', 'readonly');
			}
		}
	}
};

ofwat.editor.dropDownCell = function(ddItems) {
	var dropDown = "<select class='input cg disabled' disabled='disabled' >";
	dojo.forEach(ddItems, function(ddItem){
		dropDown = dropDown + "<option value=" + ddItem.id + ">" + ddItem.code + "</option>";
	});
	dropDown = dropDown + "</select>";
	return dropDown; 
};

ofwat.editor.dropDownCellSelected = function(selectedText, cell) {
	for(var i in cell.firstChild.children){
		if (selectedText == cell.firstChild.children[i].innerHTML) {
			cell.firstChild.selectedIndex = i;
		}
	}
};

//ofwat.editor.cellWithNoValue = function(dataCell) {
//	var parent = dataCell.parentNode;
//	if (!dojo.hasClass(parent, "input")) {
//		dojo.removeClass(parent, "calc");
//		dojo.removeClass(parent, "copy");
//		dojo.addClass(parent, "empty");
//	}
//	dataCell.innerHTML = "";
//};

ofwat.editor.showSaveDialog = function() {
	ofwat.editor.getEdits(function(data){
			dojo.empty("editsTableDiv");
			var editsReviewTable = dojo.create("table", {id: "editsReviewTable", classname: "editsReview", innerHTML: "<tr><th class='editsReview' colspan='8'>Edits</th></tr>"}, dojo.byId("editsTableDiv"));
			var editsReviewTableTbody = editsReviewTable;//.childNodes[0];			
			var editsReviewH2 = dojo.create("tr", null, editsReviewTableTbody, "last");
			dojo.create("th", {classname: "editsReview", innerHTML: "Item"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "Interval"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "Group"}, editsReviewH2, "last");
			dojo.create("th", {classname: "editsReview", innerHTML: "Run"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "Company"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "Data Type"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "Previous Value"}, editsReviewH2, "last"); 
			dojo.create("th", {classname: "editsReview", innerHTML: "New Value"}, editsReviewH2, "last"); 
			for(var i in data.edits) {
				if (0 == i%2) {
					var row = dojo.create("tr", {classname: "tableListOdd"}, editsReviewTableTbody, "last");
				}
				else {
					var row = dojo.create("tr", {classname: "tableListEven"}, editsReviewTableTbody, "last");
				}
				if ("add" != i && "contains" != i) {
					var edit = data.edits[i];
					dojo.create("td", {innerHTML: edit.itemDto.code}, row, "last");
					dojo.create("td", {innerHTML: edit.intervalDto.name}, row, "last");
					if ("NON GROUPED ITEM" == edit.groupEntry.description) {
						dojo.create("td", {innerHTML: "Not Grouped"}, row, "last");
					}
					else {
						dojo.create("td", {innerHTML: edit.groupEntry.description}, row, "last");
					}
					
					if(edit.runId != null){
						dojo.create("td", {innerHTML: edit.runName}, row, "last");
					}else{
						dojo.create("td", {innerHTML: "-"}, row, "last");
					}
					
					if(edit.company != null){
						dojo.create("td", {innerHTML: edit.company.code}, row, "last");						
					}else{
						dojo.create("td", {innerHTML: "-"}, row, "last");
					}
					
					if ("VALUE" == edit.editType) {
						dojo.create("td", {innerHTML: "Value"}, row, "last");
					}
					else if ("CONFIDENCE_GRADE" == edit.editType) {
						dojo.create("td", {innerHTML: "CG"}, row, "last");
					}
					else {
						dojo.create("td", {innerHTML: "unknown"}, row, "last");
					}
					dojo.create("td", {innerHTML: edit.original}, row, "last");
					dojo.create("td", {innerHTML: edit.value}, row, "last");
				}
			}	
		}
	);

	dojo.byId('saveDialog').style.display='block';
	dojo.byId('fade').style.display='block';
	dojo.byId('auditComment').focus();
};

ofwat.editor.saveEdits = function () {
	var commentBox = dijit.byId('auditComment');
	var text = dojo.trim(commentBox.getValue());
	if (text!=null && text!=""){
		ofwat.editor.checkoutBasket(text);
	} else {
		alert("Please enter an audit comment");
	}
};

ofwat.editor.returnToEdits = function () {
	dojo.byId('saveDialog').style.display='none';
	dojo.byId('fade').style.display='none';
};

ofwat.editor.cancelEdits = function () {
	if (confirm("Are you sure you wish to cancel your edits?")){
		ofwat.editor.deleteServerBasket();
	}
};

ofwat.editor.finishEdits = function () {
	
	// disable the save button & cancel edits button
	if(dijit.byId("saveButton") != null){
		dijit.byId("saveButton").setAttribute('disabled', true);
		dijit.byId("cancelEditing").setAttribute('disabled', true);
		// enable the edit button
		dijit.byId("editButton").setAttribute('disabled', false);
		editing = false;
	}else{
		$("#saveButton").attr("disabled", "disabled");
		$("#cancelEditing").attr("disabled", "disabled");
		$("#editButton").removeAttr("disabled");
		editing = false;
	}
	
	
	// hide the save dialog
	dojo.byId('saveDialog').style.display='none';
	dojo.byId('fade').style.display='none';
	
  	dojo.byId("response").innerHTML = "";
	
  	// make sure the page displays the up to date data
	if(ofwat.reportDisplayPage){
		ofwat.reportDisplayPage.writeEditingCompanyId();
		ofwat.reportDisplayPage.writeEditingDataTagId();
	}
  	location.href = ofwat.editor.groupIndexRewrite();
};

ofwat.editor.groupIndexRewrite = function() {
	var groupSelectDiv = dojo.byId("groupSelect");
	if (!groupSelectDiv) {
		return location.href;
	}
	var groupSelect = groupSelectDiv.firstChild;
	if (!groupSelectDiv) {
		return location.href;
	}

	var pathParam = "&groupIndex=";
	var uriAppendIndex = location.href.lastIndexOf(pathParam);
	var uri = location.href;
	if (uriAppendIndex > -1) {
		uri = location.href.substring(0, uriAppendIndex) + pathParam + groupSelect.selectedIndex;
	}
	else {
		uri = location.href + pathParam + groupSelect.selectedIndex;
	}
  	return uri;
};

ofwat.editor.postTableUpdate = function (identifier, value, dataType) {
	var original = omp.oDataList[identifier];
	console.log("original data:" + original.value + " new value:"+ value);
	if ("value" == dataType) {
		original.updatedValue = value;
	}
	else if ("cg" == dataType) {
		original.updatedConfidenceGrade = value;
	}
	oed.sendUpdate(original);
};

ofwat.editor.postReportUpdate = function (dtoKey, value, dataType) {
	var original = ord.oReportData.dataList[dtoKey];
	if ("value" == dataType) {
		original.updatedValue = value;
	}
	else if ("cg" == dataType) {
		original.updatedConfidenceGrade = value;
	}
	oed.sendUpdate(original);
};

ofwat.editor.sendUpdate = function (dataDto) {
	var xhrArgs = {
		    url: "/Fountain/rest-services/basket/edits",
	        handleAs: "json",
	        postData: dojo.toJson(dataDto),
	        preventCache: true,
            headers: {"Content-Type": "application/json"},
            sync: true,
	        load: function(data, ioargs){
            	ofwat.showMessage("");
			},
            error: function(error) {
				ofwat.showError(error, "Update failed.");
				if (410 == error.status) {
					alert("Your editing session has already been closed. Please press the edit button to start editing again.");
				  	location.reload();
				}
				else if (503 == error.status) {
					alert(error.responseText);
				  	location.reload();
				}
			}
	    };
	ofwat.showMessage("Update requested... <img border='0' src='/Fountain/images/loader.gif'/>");
	dojo.xhrPost(xhrArgs);
};


ofwat.editor.getBasket = function() {
	var xhrArgs = {
			url: "/Fountain/rest-services/basket",
			handleAs: "json",
			headers: {"accept": "application/json"},
            sync: true,
            failok: true,
			load: function(){
				oed.styleForEditing();
				ofwat.showMessage("");
			}
//			error: function(error) {
//				// This is not a problem, means basket does not exist, so all ok
//				ofwat.showMessage("");
//			}
	};
	ofwat.showMessage("Checking for existing edits... <img border='0' src='/Fountain/images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.editor.getEdits = function(loadFunction) {
	var xhrArgs = {
			url: "/Fountain/rest-services/basket",
			handleAs: "json",
			headers: {"accept": "application/json"},
			failok: true,
			load: function(data){
				loadFunction(data);
				ofwat.showMessage("");
			},
			error: function(error) {
				// This is not a problem, means basket does not exist, so all ok
				ofwat.showMessage("");
			}
	};
	ofwat.showMessage("Checking for existing edits... <img border='0' src='/Fountain/images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.editor.createServerBasket = function(){
	var xhrArgs = {
			url: "/Fountain/rest-services/basket",
			handleAs: "json",
			headers: {"Content-Type": "application/json"},
			sync: true,
			load: function(){
            	ofwat.showMessage("");
			},
			error: function(error) {
				if (409 == error.status) {
					ofwat.showMessage("Your edit basket is already open.");
				}
				else {
					ofwat.showError(error,"Failed to create edit basket");
				}
			}
	};
	ofwat.showMessage("Creating edit basket... <img border='0' src='/Fountain/images/loader.gif'/>");
	var deferred = dojo.xhrPut(xhrArgs);
};

ofwat.editor.checkoutBasket = function(auditComment){
	var xhrArgs = {
            url: "/Fountain/rest-services/checkout",
            postData: dojo.toJson(auditComment),
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function() {
				ofwat.editor.finishEdits();
            	ofwat.showMessage("Save successful");
		    }, 
		    error: function (error){
		    	ofwat.showError(error,"Save failed");
		    }
    };
	ofwat.showMessage("Save requested... <img border='0' src='/Fountain/images/loader.gif'/>");
	dojo.xhrPost(xhrArgs);
};

ofwat.editor.deleteServerBasket = function(){
    var xhrArgs = {
            url: '/Fountain/rest-services/basket',
            handleAs: "json",
            load: function(data) {
            	ofwat.editor.finishEdits();
    			ofwat.showMessage("Cancel completed");
            },
            error: function(error) {
            	ofwat.showError(error,"Problem cancelling edit");
             }
        };
    ofwat.showMessage("Cancelling edits... <img border='0' src='/Fountain/images/loader.gif'/>");
    //Call the asynchronous xhrDelete
    var deferred = dojo.xhrDelete(xhrArgs);
};


ofwat.editor.lockTimer = function() {
	if (oed.oLocks.length > 0) {
		setTimeout("ofwat.editor.refreshLocks()", 15000);
	}
};

ofwat.editor.refreshLocks = function() {
	var userId = oed.oLocks[0].user.id;
	var xhrArgs = {
            url: "/Fountain/rest-services/lock/" + userId,
            putData: dojo.toJson(oed.oLocks),
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function() {
    			ofwat.showMessage("");
		    }, 
		    error: function (error){
		    	ofwat.showError(error,"Refresh locks failed");
		    }
    };
	ofwat.editor.lockTimer();
	ofwat.showMessage("Refreshing locks... <img border='0' src='/Fountain/images/loader.gif'/>");
	dojo.xhrPut(xhrArgs);
};

