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

//TODO - move the rest mechanics to ofwat.rest.

// dependency check
if(!ofwat){
	alert("missing import: ofwat.js");
}
if(!ofwat.rest){
	alert("missing import: rest.js");
}
if(!ofwat.scrollpane){
	alert("missing import: scrollPane.js");
}


ofwat.dataEditor = {
	xmlhttpDeleteBasket: null,
	xmlhttpPutBasket: null,	
	xmlhttpGetBasket: null,
	xmlhttpPostCheckout: null,
	xmlhttpGetEdit: null,
	dtoList: null,
	sCurrentTableUri: null,
	sCurrentTableName: null,
	basket: null,
	previousModel: null,
	currentModel: null
};



ofwat.dataEditor.loadInitialData = function() {
	ofwat.dataEditor.displayToolbar();
	ofwat.rest.loadJSON('../../rest-services/company', ofwat.dataEditor.parseCompanies); 
};

ofwat.dataEditor.clearData = function(id){
	var dataTable =  dojo.byId(id);
	if(dataTable){
		dataTable.innerHTML = "";
	}
};

ofwat.dataEditor.getBasket = function() {
	ofwat.dataEditor.xmlhttpGetBasket=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.dataEditor.xmlhttpGetBasket=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.dataEditor.xmlhttpGetBasket=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if (ofwat.dataEditor.xmlhttpGetBasket!=null)
	  {
		ofwat.dataEditor.xmlhttpGetBasket.onreadystatechange=ofwat.dataEditor.parseBasket;
		ofwat.dataEditor.xmlhttpGetBasket.open("GET",'../../rest-services/basket?modelId=' + ofwat.dataEditor.currentModel.id,true);
		ofwat.dataEditor.xmlhttpGetBasket.setRequestHeader("accept", "application/json");
		ofwat.dataEditor.xmlhttpGetBasket.send(null);
	  }
	else
	  {
	  	alert("Your browser does not support XMLHTTP.");
	  }
};

ofwat.dataEditor.parseBasket = function() {
	var txt;
	if(ofwat.dataEditor.xmlhttpGetBasket.readyState!=4) return;
	if( ofwat.dataEditor.xmlhttpGetBasket.status!=200 && ofwat.dataEditor.xmlhttpGetBasket.status!=404) 
	{
	 	alert("Problem getting basket. xmlhttpGetBasket.status = " + ofwat.dataEditor.xmlhttpGetBasket.status);
	  	return;
	}

	if( ofwat.dataEditor.xmlhttpGetBasket.status==200) {
		ofwat.dataEditor.toggleEditMode();
		ofwat.dataEditor.basket = JSON.parse(ofwat.dataEditor.xmlhttpGetBasket.responseText);
	} 
	else {
		ofwat.dataEditor.basket = null;
	}
};

ofwat.dataEditor.getEdit = function(uri) {
	ofwat.dataEditor.xmlhttpGetEdit=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.dataEditor.xmlhttpGetEdit=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.dataEditor.xmlhttpGetEdit=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if (this.xmlhttpGetEdit!=null)
	  {
		ofwat.dataEditor.xmlhttpGetEdit.onreadystatechange=ofwat.dataEditor.parseEdit;
		ofwat.dataEditor.xmlhttpGetEdit.open("GET", uri, true);
		ofwat.dataEditor.xmlhttpGetEdit.setRequestHeader("accept", "application/json");
		ofwat.dataEditor.xmlhttpGetEdit.send(null);
	  }
	else
	  {
	  	alert("Your browser does not support XMLHTTP.");
	  }
};

ofwat.dataEditor.parseEdit = function() {
	var txt;
	if(ofwat.dataEditor.xmlhttpGetEdit.readyState!=4) return;
	if( ofwat.dataEditor.xmlhttpGetEdit.status!=200) 
	{
	 	alert("Problem getting edit. xmlhttpGetEdit.status = " + ofwat.dataEditor.xmlhttpGetEdit.status);
	  	return;
	}

	var edit = JSON.parse(ofwat.dataEditor.xmlhttpGetEdit.responseText);
	// if the item/year/datatype has already been edited then overwrite the existing edit.
	// otherwise just add the new edit.
	var newEdit = true;
	for (var i=0; i < ofwat.dataEditor.basket.edits.length; i++) {
		if (ofwat.dataEditor.basket.edits[i].itemId == edit.itemId &&
			ofwat.dataEditor.basket.edits[i].intervalId == edit.intervalId && 
			ofwat.dataEditor.basket.edits[i].companyId == edit.companyId &&
			ofwat.dataEditor.basket.edits[i].GroupEntryId == edit.GroupEntryId) {
			ofwat.dataEditor.basket.edits[i] = edit;
			newEdit = false;
		}
	}
	if (newEdit) {
		ofwat.dataEditor.basket.edits.push(edit);
	}
};

ofwat.dataEditor.refreshDataPane = function() {
	if(null != ofwat.dataEditor.sCurrentTableUri && null != ofwat.dataEditor.sCurrentTableName){
		ofwat.dataEditor.loadDataTable(ofwat.dataEditor.sCurrentTableName, ofwat.dataEditor.sCurrentTableUri);
	}
};

ofwat.dataEditor.parseModels = function(){
	var txt;
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError(ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }
	
	txt="<table>";
	var modelArray = JSON.parse(ofwat.rest.xmlhttp.responseText);
	
	// Hide the dictionary panel 
	var dictionaryPane = dojo.byId("dictionary");
	dictionaryPane.style.display = "none";	
	
	for (i=0;i<modelArray.length;i++)
	  {
	  	if (i%2){
			txt=txt + "<tr class='tabEven'>";
		} else { 
    		txt=txt + "<tr class='tabOdd'>";
		}
		{
	    try
	      {
	    	txt=txt + "<td><a href='javascript:ofwat.dataEditor.loadTables(\"" + modelArray[i].uri + "\")'/>" + ofwat.escapeHTML(modelArray[i].name) + "</a></td>";
	      }
	    catch (er)
	      {
		      alert(er);
	     	txt=txt + "<td> " + er +"</td>";
	      }
	    }
	  txt=txt + "</tr>";
	  }

	txt=txt + "</table>";
	dojo.byId('models').innerHTML=txt;
	
};

ofwat.dataEditor.parseCompanies = function(){
	var txt;
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError(ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }

	  txt="<table>";
	var companyArray = JSON.parse(ofwat.rest.xmlhttp.responseText);

	txt=txt + "<tr>";
	txt=txt + "<td class='companies'>";
	txt=txt + "<select id='companySelect'>";
	for (i=0;i<companyArray.length;i++)
    {
	    {
	    try
	      {
		    if (0 == i) 
			{
				txt=txt + "<option value='" + companyArray[i].id + "' selected='true'>" + companyArray[i].name + " ["+companyArray[i].code+"]</option>";
	      	}
			else 
			{
				txt=txt + "<option value='" + companyArray[i].id + "'>" + ofwat.escapeHTML(companyArray[i].name + " ["+companyArray[i].code)+"]</option>";
			}
	      }
	    catch (er)
	      {
		      alert(er);
	     	txt=txt + "<td> " + er +"</td>";
	      }
	    }
	}
   txt=txt + "</select>";
	txt=txt + "</td>";
	txt=txt + "</tr>";

	txt=txt + "</table>";
	dojo.byId('companies').innerHTML=txt;

	ofwat.rest.loadJSON('/Fountain/rest-services/model?typeId=1', ofwat.dataEditor.parseModels);
};



ofwat.dataEditor.loadTables = function(uri) {
	var ctlEdit = dojo.byId("editButton");
	if ( typeof ctlEdit === "undefined"   ) {
		ofwat.rest.loadJSON(uri, this.parseTables);
	}
	else if(false == ctlEdit.disabled){
		ofwat.rest.loadJSON(uri, this.parseTables);
	}
	else {
		alert("Please save or cancel your edits before selecting a new model.");
	}
};

ofwat.dataEditor.parseTables = function(){
	// set the current table to null as we've just changed models
	ofwat.dataEditor.sCurrentTableUri = null;
	ofwat.dataEditor.sCurrentTableName = null;
	ofwat.scrollpane.drawScrollPane();
	
	// Hide the dictionary panel 
	var dictionaryPane = dojo.byId("dictionary");
	dictionaryPane.style.display = "none";	
	
	
	var txt;
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200) {
		ofwat.rest.handleError(ofwat.rest.xmlhttp.responseXML);
	 	 return;
	}
	

	var model = JSON.parse(ofwat.rest.xmlhttp.responseText);
	varName=model.name;
	varTableLinks = model.tableLinks;
	varCode=model.code;
	document.title = model.code;
	ofwat.dataEditor.currentModel=model;
	
	varCompanySelect = dojo.byId('companySelect');
	varCompanySelectedIndex = varCompanySelect.options[varCompanySelect.selectedIndex].value;
	
	txt="<table>";
	txt=txt + "<tr>";
	try	{
		txt=txt + "<td>" + ofwat.escapeHTML(varCode) + "</td>";
		txt=txt + "<td>" + ofwat.escapeHTML(varName) + "</td>";
	}
	catch (er) {
		alert(er);
		txt=txt + "<td> " + er +"</td>";
	}
	txt=txt + "</tr>";
	txt=txt + "</table>";
	
	txt=txt + "<div class=\"tables\">";
	txt=txt + "<table>";
    for(j=0; j<varTableLinks.length;j++){
    	if (j%2){
			txt=txt + "<tr class='tabEven'>";
		} else { 
    		txt=txt + "<tr class='tabOdd'>";
		}
    	varTableName = ofwat.escapeHTML(varTableLinks[j].name);
    	varTableUri = varTableLinks[j].uri;
    	try {
    		txt=txt + "<td colspan=2 class='tableName'><a href='javascript:ofwat.dataEditor.loadDataTable(\"" + varTableName + "\",\"" + varTableUri + "\")'>"+varTableName+"</a></td>";
	      	txt=txt + "<td colspan=2><a name='excelLink' title='download "+varTableName+" as spreadsheet' href='" + varTableUri + "/excel?companyId="+varCompanySelectedIndex+"&cg="+ofwat.tableDisplay.getShowCGs()+"' target='_blank'><img border='0' src='../../images/table.png' alt='download as spreadsheet'/></a></td>";
    	}
	    catch (er) {
	    	alert(er);
	    	txt=txt + "<td colspan=2> " + er +"</td>";
	    }
	    txt=txt + "</tr>";
    }

	txt=txt + "</div>";
	txt=txt + "</table>";
	dojo.byId('tables').innerHTML=txt;		
	
	ofwat.dataEditor.getBasket();
};


ofwat.dataEditor.loadDataTable = function(varTableName, varTableUri) {
	ofwat.scrollpane.drawScrollPane();
	// set the current table URI in case of edits
	ofwat.dataEditor.sCurrentTableUri = varTableUri;
	ofwat.dataEditor.sCurrentTableName = varTableName;
	
	varCompanySelect = dojo.byId('companySelect');
	varCompanySelectedIndex = varCompanySelect.options[varCompanySelect.selectedIndex].value;
	dojo.byId('tableName').innerHTML=ofwat.escapeHTML(varTableName);
	var sTitle = document.title;
	var nPos = sTitle.indexOf(":", 0); 
	if(nPos > 0){
		document.title = sTitle.substring(0, nPos + 2) + varTableName;  
	}
	else{
		document.title = sTitle + " : " + varTableName;
	}
	ofwat.rest.loadJSON(varTableUri + "?companyId=" + varCompanySelectedIndex, ofwat.dataEditor.parseDataTable);
};

ofwat.dataEditor.parseDataTable = function(varTableUri){
	var txt;
	ofwat.dataEditor.dtoList = {}; 
	
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError(ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }
	
	// Hide the dictionary panel 
	var dictionaryPane = dojo.byId("dictionary");
	dictionaryPane.style.display = "none";	
	
	ofwat.tableDisplay.displayTable(ofwat.rest.xmlhttp.responseText);
	return false;
};

ofwat.dataEditor.populateDictionaryPane = function(identifier) {
	var dictionaryPane = dojo.byId("dictionary");
	dictionaryPane.style.display = "inline";	
	var oDataDto = this.dtoList[identifier];
	var varItem = oDataDto.item;
	var varItemProps = oDataDto.itemPropertiesDto;
	var txt="<table class='dictionary'>";

	txt=txt+"<tr><td class='dictionaryHeader'colspan=2>"+ofwat.escapeHTML(varItem.code)+"</th></tr>";
	txt=txt+"<tr><td>Line Number</td><td>"+ofwat.escapeHTML(varItem.lineNumber)+"</td></tr>";
	txt=txt+"<tr><td>Interval</td><td>"+ofwat.escapeHTML(oDataDto.intervalDTO.name)+"</td></tr>";
	txt=txt+"<tr><td>Cell Type</td><td>"+ofwat.escapeHTML(varItem.cellType)+"</td></tr>";
	txt=txt+"<tr><td>Formula</td><td>"+ofwat.escapeHTML(oDataDto.formula)+"</td></tr>";

	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Details</td></tr>";
	txt=txt+"<tr><td>Description</td><td>"+ofwat.escapeHTML(varItemProps.description)+"</td></tr>";
	txt=txt+"<tr><td>Purpose</td><td>"+ofwat.escapeHTML(varItem.purpose)+"</td></tr>";
	txt=txt+"<tr><td>Rule Text</td><td>"+ofwat.escapeHTML(varItem.ruleText)+"</td></tr>";
	txt=txt+"<tr><td>Definition</td><td>"+ofwat.escapeHTML(varItem.definition)+"</td></tr>";

	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Format</td></tr>";
	txt=txt+"<tr><td>Unit</td><td>"+ofwat.escapeHTML(varItem.unit)+"</td></tr>";
	txt=txt+"<tr><td>Unit Type</td><td>"+ofwat.escapeHTML(varItem.unitType)+"</td></tr>";
	txt=txt+"<tr><td>Decimal Places</td><td>"+ofwat.escapeHTML(varItem.dp)+"</td></tr>";
	txt=txt+"<tr><td>Inflation Type</td><td>"+ofwat.escapeHTML(varItem.inflationType)+"</td></tr>";
	txt=txt+"<tr><td>Price Base</td><td>"+ofwat.escapeHTML(varItem.priceBase)+"</td></tr>";
	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Audit</td></tr>";
	txt=txt+"<tr><td colspan='2'>"
	txt=txt+  "<div id='dictionary-audit' style='overflow:scroll; width:100%; height:100%'>";
	txt=txt+    "<input type='button' onClick='javascript:ofwat.dataEditor.showAudit(\"" + identifier + "\");' value='Show audit'/>";
	txt=txt+  "</div>";
	txt=txt+"</td></tr>";

	txt=txt+"</table>";
	dojo.byId('dictionary').innerHTML=txt;	
};

ofwat.dataEditor.showAudit = function(identifier) {
	ofwat.rest.xmlhttp=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.rest.xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.rest.xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	
	
	ofwat.rest.xmlhttp.open( "GET", "../../rest-services/data/auditedValues/" + identifier);
	ofwat.rest.xmlhttp.setRequestHeader("Content-Type", "application/json");
	ofwat.rest.xmlhttp.onreadystatechange = ofwat.dataEditor.populateAudit;
	ofwat.rest.xmlhttp.send(null);
};


ofwat.dataEditor.populateAudit = function() {
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError(ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }

	var sRet = ofwat.rest.xmlhttp.responseText;
	try {
		var aoRet = JSON.parse(sRet);
	}
	catch (error) {
		alert("Couldn't parse result: " + error);
	}

	var auditPane = dojo.byId("dictionary-audit");
	
	var txt="<table class='dictionary'>";
	txt=txt+"<tr>";
	txt=txt+  "<td class='dictionaryHeader'>Value</td>";
	txt=txt+  "<td class='dictionaryHeader'>Date</td>";
	txt=txt+  "<td class='dictionaryHeader'>Name</td>";
	txt=txt+  "<td class='dictionaryHeader'>Comment</td>";
	txt=txt+"</tr>";
	
	for (var i=0; i<aoRet.length; i++) {
		txt=txt+"<tr>";
		txt=txt+  "<td>" + ofwat.escapeHTML(aoRet[i].value) + "</td>";
		txt=txt+  "<td>" + ofwat.escapeHTML(aoRet[i].date) + "</td>";
		txt=txt+  "<td>" + ofwat.escapeHTML(aoRet[i].user.name) + "</td>";
		txt=txt+  "<td>" + ofwat.escapeHTML(aoRet[i].comment) + "</td>";
		txt=txt+"</tr>";
	}

	txt=txt+"</table>";
	auditPane.innerHTML=txt;	
};

ofwat.dataEditor.postDataUpdate = function(identifier, value, dataType){
	var original = this.dtoList[identifier];
	
	if ("value" == dataType) {
		original.updatedValue = value;
	}
	else if ("cg" == dataType) {
		original.updatedConfidenceGrade = value;
	}
	
	var strJSON = JSON.stringify(original);
	
	
	//TODO - move this to the rest package?
	ofwat.rest.xmlhttp=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.rest.xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.rest.xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	ofwat.rest.xmlhttp.open( "POST", '../../rest-services/basket/edits?modelId=' + ofwat.dataEditor.currentModel.id);
	ofwat.rest.xmlhttp.setRequestHeader("Content-Type", "application/json");
	ofwat.rest.xmlhttp.onreadystatechange = function(){ofwat.dataEditor.postDataResponse();};
	ofwat.rest.xmlhttp.send(strJSON);
};

ofwat.dataEditor.postDataResponse = function() {
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=201) {
	 	 alert("Problem posting data. xmlhttp.status = " + ofwat.rest.xmlhttp.status);
 	 	 return;
	}
	ofwat.dataEditor.getEdit(ofwat.rest.xmlhttp.getResponseHeader("location"));
};

ofwat.dataEditor.editButtonPressed = function() {
	ofwat.dataEditor.createServerBasket();
};

ofwat.dataEditor.cancelButtonPressed = function() {
	ofwat.dataEditor.deleteServerBasket();
};

ofwat.dataEditor.saveButtonPressed = function() {
	ofwat.dataEditor.showOverlay();
};

ofwat.dataEditor.commitAuditButtonPressed = function() {
	ofwat.dataEditor.basket.audit = dojo.byId("auditTextBox").value;
	ofwat.dataEditor.checkoutBasket();
	ofwat.dataEditor.hideOverlay();
};

ofwat.dataEditor.exportButtonPressed = function(varTableUri){
	elCompanySelect = dojo.byId('companySelect');
	nCompanySelectedIndex = elCompanySelect.options[elCompanySelect.selectedIndex].value;
	dojo.byId('formTools').innerHTML = "<a href='" + varTableUri + "'>excel export</a>";
};

ofwat.dataEditor.toggleEditMode = function(){
	var ctlEdit = dojo.byId("editButton");
	var ctlCancel = dojo.byId("cancelButton");
	var ctlSave = dojo.byId("saveButton");

	if(null == this.basket){
		// start editing
		ctlEdit.disabled = true;
		ctlSave.disabled = false;
		ctlCancel.disabled = false;
		ofwat.dataEditor.basket = this.newBasket();
	}
	else{
		// stop editing
		ctlEdit.disabled = false;
		ctlSave.disabled = true;
		ctlCancel.disabled = true;
		ofwat.dataEditor.basket = null;
	}
};

ofwat.dataEditor.newBasket = function() {
	return JSON.parse("{\"audit\":\"\",\"edits\":[]}");
};

ofwat.dataEditor.createServerBasket = function(){
	ofwat.dataEditor.xmlhttpPutBasket=null;
	var strJSON = JSON.stringify(ofwat.dataEditor.basket);

	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.dataEditor.xmlhttpPutBasket=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.dataEditor.xmlhttpPutBasket=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	ofwat.dataEditor.xmlhttpPutBasket.open( "PUT", '../../rest-services/basket?modelId=' + ofwat.dataEditor.currentModel.id);
	ofwat.dataEditor.xmlhttpPutBasket.setRequestHeader("Content-Type", "application/json");
	ofwat.dataEditor.xmlhttpPutBasket.onreadystatechange = ofwat.dataEditor.createBasketResponse;
	ofwat.dataEditor.xmlhttpPutBasket.send(strJSON);
};

ofwat.dataEditor.createBasketResponse = function() {
	if(ofwat.dataEditor.xmlhttpPutBasket.readyState!=4) return;
	if(ofwat.dataEditor.xmlhttpPutBasket.status!=201) {
	 	 alert("Problem starting edit. xmlhttpPutBasket.status = " + ofwat.dataEditor.xmlhttpPutBasket.status);
 	 	 return;
	}
	ofwat.dataEditor.toggleEditMode();
	ofwat.dataEditor.refreshDataPane();
};

ofwat.dataEditor.checkoutBasket = function(){
	ofwat.dataEditor.xmlhttpPostCheckout=null;
	var strJSON = JSON.stringify(ofwat.dataEditor.basket);

	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.dataEditor.xmlhttpPostCheckout=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.dataEditor.xmlhttpPostCheckout=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	ofwat.dataEditor.xmlhttpPostCheckout.open( "POST", '../../rest-services/checkout?modelId=' + ofwat.dataEditor.currentModel.id);
	ofwat.dataEditor.xmlhttpPostCheckout.setRequestHeader("Content-Type", "application/json");
	ofwat.dataEditor.xmlhttpPostCheckout.onreadystatechange = ofwat.dataEditor.checkoutResponse;
	ofwat.dataEditor.xmlhttpPostCheckout.send(strJSON);
};

ofwat.dataEditor.checkoutResponse = function() {
	if(ofwat.dataEditor.xmlhttpPostCheckout.readyState!=4){
		return;
	}
	if( ofwat.dataEditor.xmlhttpPostCheckout.status!=204 ) {
	 	 alert("Problem commiting edits. xmlhttpPostCheckout.status = " + ofwat.dataEditor.xmlhttpPostCheckout.status);
 	 	 return;
	}
	ofwat.dataEditor.toggleEditMode();
	ofwat.dataEditor.refreshDataPane();
};

ofwat.dataEditor.deleteServerBasket = function(){
	ofwat.dataEditor.xmlhttpDeleteBasket=null;

	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.dataEditor.xmlhttpDeleteBasket=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.dataEditor.xmlhttpDeleteBasket=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	ofwat.dataEditor.xmlhttpDeleteBasket.open( "DELETE", '../../rest-services/basket?modelId=' + ofwat.dataEditor.currentModel.id);
	ofwat.dataEditor.xmlhttpDeleteBasket.setRequestHeader("Content-Type", "application/json");
	ofwat.dataEditor.xmlhttpDeleteBasket.onreadystatechange = ofwat.dataEditor.deleteBasketResponse;
	ofwat.dataEditor.xmlhttpDeleteBasket.send(null);
};

ofwat.dataEditor.deleteBasketResponse = function() {
	if(ofwat.dataEditor.xmlhttpDeleteBasket.readyState!=4) {
		return;
		}
	if(ofwat.dataEditor.xmlhttpDeleteBasket.status!=204) {
	 	 alert("Problem canceling edit. xmlhttpDeleteBasket.status = " + ofwat.dataEditor.xmlhttpDeleteBasket.status);
 	 	 return;
	}
	ofwat.dataEditor.toggleEditMode();
	ofwat.dataEditor.refreshDataPane();
};


ofwat.dataEditor.showOverlay = function() {
	dojo.byId('light').style.display='block';
	dojo.byId('fade').style.display='block';
};

ofwat.dataEditor.hideOverlay = function(){
	dojo.byId('light').style.display='none';
	dojo.byId('fade').style.display='none';
};

ofwat.dataEditor.displayToolbar = function() {
	var toolBar = dojo.byId("bannerToolbar");
	toolBar.style.display = "inline";	
	
//	enable the form control buttons
	txt = "<form id='frmToolbar'>";
	txt=txt+"<div class='editButtonDiv' id='editButtonDiv'><input id='editButton' type=button value='Edit' onclick='javascript:ofwat.dataEditor.editButtonPressed()'></input></div>";
	txt=txt+"<div class='saveButtonDiv' id='saveButtonDiv'><input id='saveButton' type='button' value='Save' disabled onclick='javascript:ofwat.dataEditor.saveButtonPressed()'></input></div>";
	txt=txt+"<div class='cancelButtonDiv' id='cancelButtonDiv'><input id='cancelButton' type=button value='Cancel' disabled onclick='javascript:ofwat.dataEditor.cancelButtonPressed()'></input></div>";
	txt=txt+"<input id='toggleCGsBox' type=checkbox onclick='javascript:ofwat.dataEditor.setCGStatus()'>&nbsp;Show CGs</input>";
	txt=txt+"<div id='auditDiv'></div>";
	txt=txt+"</form>";
	dojo.byId('formTools').innerHTML=txt;	
};

ofwat.dataEditor.hideToolbar = function() {
	var toolBar = dojo.byId("bannerToolbar");
	toolBar.style.display = "none";	
};

ofwat.dataEditor.userEdit = function() {
	var edit = JSON.parse("{\"id\":\"\",\"user\":\"\",\"value\":\"\",\"itemId\":\"\",\"intervalId\":\"\",\"companyId\":\"\"}");
	return edit;
};

ofwat.dataEditor.setCGStatus = function(){
	ofwat.tableDisplay.toggleCGs();
	
	var cgState = ofwat.tableDisplay.getShowCGs();
	var excelLinks = dojo.byId("excelLink");
	for (i = 0; i < excelLinks.length; i++)
	{
		lastQueryIdx = excelLinks[i].href.lastIndexOf("&");
		if(-1 != lastQueryIdx){
			var linkText = new String(excelLinks[i].href);
			excelLinks[i].href = linkText.substr(0, lastQueryIdx);
		}
		excelLinks[i].href = excelLinks[i].href + "&cg=" + cgState;
	}

};

