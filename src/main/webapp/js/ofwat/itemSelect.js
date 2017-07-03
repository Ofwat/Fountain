//***************************************
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
//** Script Library for Item Selection **
//***************************************

// dependency check
if(!ofwat){
	alert("missing import: ofwat.js");
}
if(!ofwat.rest){
	alert("missing import: rest.js");
}
if(!ofwat.listSort){
	alert("missing import: listSort.js");
}

ofwat.itemSelect = {
	intervalArray: null, // List to hold selected intervals (for filtering)
	modelArray: null     // list to hold selected models (for filtering)
};


ofwat.itemSelect.filterList = function(){
	
	var filterText = dojo.byId("itemFilter").value;
	ofwat.rest.loadJSON("../../rest-services/item?criteria=" + filterText , ofwat.itemSelect.populateItemSelection);
};

ofwat.itemSelect.itemSearch = function (){
	// check there is a model selected
	if (!this.modelSelected()){
		alert("Please select a model");
	} else {
		var searchText = dojo.byId("itemSearchTerm").value;
		var filterOptions="";
		filterOptions="&filters=";
		var modelBoxArray = dojo.byId("model");
		for(i=0; i<modelBoxArray.length; i++){
			var box = modelBoxArray[i];
			if (box.checked){
				var split = box.value.split("#");
				if (split[0]!= 'All'){
					filterOptions+=split[0]+";";
				}
			}
		}
		ofwat.rest.loadJSON("../../rest-services/item?criteria=" + searchText + filterOptions , populateItemSearch);
	} 
};

ofwat.itemSelect.itemDefSearch = function (){
	// check a model is selected
	if (!this.modelSelected()){
		alert("Please select a model");
	} else {
		var searchText = dojo.byId("itemSearchTerm").value;
		var filterOptions="";
		filterOptions="&filters=";
		var modelBoxArray = dojo.byId("model");
		for(i=0; i<modelBoxArray.length; i++){
			var box = modelBoxArray[i];
			if (box.checked){
				var split = box.value.split("#");
				filterOptions+=split[0]+";";
			}
		}
		ofwat.rest.loadJSON("../../rest-services/item?criteria=" + searchText +"&extended=true" + filterOptions, populateItemSearch);
	}
};

ofwat.itemSelect.modelSelected = function(){
	var modelBoxArray = dojo.byId("model");
	for(i=0; i<modelBoxArray.length; i++){
		var box = modelBoxArray[i];
		if (box.checked){
			return true;
		}
	}
	return false;
};

ofwat.itemSelect.populateItemSearch = function (){
	
	var txt;
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		handleError( ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }

	txt="<select id=\"resultList\" class=\"resultList\" multiple=\"multiple\">";
	var itemArray = JSON.parse(ofwat.rest.xmlhttp.responseText);
	var options = "";
	for (i=0;i<itemArray.length;i++)
    {
		options = options + "<option value=" + itemArray[i].itemId+"#"+ itemArray[i].modelId+"#"+itemArray[i].modelPropertiesMapId +">"+ofwat.escapeHTML(itemArray[i].itemCode + ": " +itemArray[i].itemName+" ["+itemArray[i].modelCode)+"]</option>";
//		options = options + "<li class=\"list1\" value=\""+itemArray[i].itemId+"#"+ itemArray[i].modelId+"#"+itemArray[i].modelPropertiesMapId+"\">"+itemArray[i].itemCode + ": " +itemArray[i].itemName+" ["+itemArray[i].modelCode+"]</li>";
    }
	txt+=options+"</select>";
	dojo.byId('results').innerHTML=txt;
	
	var searchCriteria="Search results for <b>"+ofwat.escapeHTML(dojo.byId("itemSearchTerm")).value+"</b>";
	dojo.byId('searchCriteria').innerHTML=searchCriteria;
	
	
	// make all the li's dragable and drop into the correct location (different drop zone from the other list (the intervals)
};




//Deletes an item from the picklist
// pass in startlist if you want to pass the deleted item back to the startlist
ofwat.itemSelect.delItem = function (fromList, startList) {

	var pickList = dojo.byId(fromList);
	var pickIndex = pickList.selectedIndex;
	var pickOptions = pickList.options;

	
	var selectList = null;
	var selectOptions = null;
	var selectOLength = null;
	
	if (startList!=null){
		selectList = dojo.byId(startList);
		selectOptions = selectList.options;
		selectOLength = selectOptions.length;
	}
	
	if (pickIndex > -1) {
		if (startList != null){
			selectOptions[selectOLength] = new Option(pickList[pickIndex].text);
			selectOptions[selectOLength].value = pickList[pickIndex].value;
		}

		pickOptions[pickIndex] = null;

		if (startList!=null) {
			// Re-sort the select list
			this.sortOptions(selectList);
		}
	}
};

ofwat.itemSelect.sortNumerically = function (a,b){
	 if ( a.value < b.value )
		 return -1;
	 if ( a.value > b.value )
		 return 1;
	 return 0; // a == b
};

ofwat.itemSelect.sortOptions = function (list) {
	var items = list.options.length;
	// create array and make copies of options in list
	var tmpArray = new Array(items);
	for ( i=0; i<items; i++ )
		tmpArray[i] = new Option(list.options[i].text,list.options[i].value);
	// sort options using given function
	tmpArray.sort(sortNumerically);
	// make copies of sorted options back to list
	for ( i=0; i<items; i++ )
		list.options[i] = new Option(tmpArray[i].text,tmpArray[i].value);

};


ofwat.itemSelect.getFilterModels = function (){
	// retrieve models from server
	// put into a popup box that the user can select or remove them from
	ofwat.rest.loadJSON("../../rest-services/model?typeId=1", ofwat.itemSelect.displayFilterOptions);
};

ofwat.itemSelect.displayFilterOptions = function () {
	var isIE = (navigator.appName == "Microsoft Internet Explorer");
	// put these on the screen, not a pop up anymore...
	var txt="";
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError( ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }
    var modelRespArray = JSON.parse(ofwat.rest.xmlhttp.responseText);

    var modelList = dojo.byId('filterList');
    var form = document.createElement("form");
    modelList.appendChild(form);
    form.setAttribute("name", "modelForm");
    form.setAttribute("id", "modelForm");
//    // if ie
//    if (isIE){
//    	form.outerHTML = form.outerHTML.replace(/>/, " name="+form.name+">");
//    }
    var table = document.createElement("table");
    form.appendChild(table);
    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var tr = document.createElement("tr");
    tbody.appendChild(tr);
    var td = document.createElement("td");
    tr.appendChild(td);
    var label = document.createElement("label");
    td.appendChild(label);
    label.setAttribute("for", "All Models");
    var text = document.createTextNode("All Models");
    label.appendChild(text);
    
    var td1 = document.createElement("td");
    tr.appendChild(td1);
    var input = document.createElement("input");
    input.type='checkbox';
    input.defaultChecked=false;
    td1.appendChild(input);
    input.setAttribute("dojoType", "dijit.form.CheckBox");
    input.setAttribute("value", "All");
    input.setAttribute("name", "model");
    input.setAttribute("id", "AllModels");
    input.setAttribute("onclick", "return ofwat.itemSelect.selectAllClicked(this);");
//	if (isIE){
//		input.outerHTML = input.outerHTML.replace(/>/, " name="+input.name+">");
//	}

    
    
    for (i=0;i<modelRespArray.length;i++)
    {
    	var name = ofwat.escapeHTML(modelRespArray[i].name);
    	var id= modelRespArray[i].id;
    	var tr1 = document.createElement("tr");
    	tbody.appendChild(tr1);
    	tr1.setAttribute("id", "box"+id);
    	var td2 = document.createElement("td");
    	tr1.appendChild(td2);
    	var lab1 = document.createElement("label");
    	td2.appendChild(lab1);
    	lab1.setAttribute("for", name);
    	var text1 = document.createTextNode(name);
    	lab1.appendChild(text1);
    	var td3 = document.createElement("td");
    	tr1.appendChild(td3);
    	td3.setAttribute("class", "unselectedBox");
    	var input2 = document.createElement("input");
    	input2.type='checkbox';
    	input2.defaultChecked=false;
    	td3.appendChild(input2);
    	input2.setAttribute("dojoType", "dijit.form.CheckBox");
    	input2.setAttribute("name", "model");
    	input2.setAttribute("value", id+"#"+name);
    	input2.setAttribute("id", "model"+id);
    	input2.setAttribute("onclick", "ofwat.itemSelect.selectedBox(this)");
//    	if (isIE){
//    		input2.outerHTML = input2.outerHTML.replace(/>/, " name="+input2.name+">");
//    	}
    
    }

};

ofwat.itemSelect.selectedBox = function (box){	
	//var boxRow = dojo.byId(box);
	//split the value
	var ids = box.value.split("#");
	if (box.checked){
		this.getTablesForModel(ids[0]);
	} else {
		this.removeTablesForModel(ids[0]);
	}
};

ofwat.itemSelect.getTablesForModel = function(modelId){
	ofwat.rest.loadJSON("../../rest-services/model/"+modelId, this.displayModelTables);
};



ofwat.itemSelect.displayModelTables = function(){
	var txt="";
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	{
		ofwat,rest.handleError(ofwat.rest.xmlhttp.responseXML);
		return;
	}
	var model = JSON.parse(ofwat.rest.xmlhttp.responseText);
	
	var tableExists = dojo.byId("modelTable"+model.id);
	// model table doesn't exist
	if (!tableExists){

		txt+="<table id=\"modelTable"+model.id+"\">";
		for (i=0;i<model.tableLinks.length;i++) {
			txt +="<tr><td class=\"reportList"+(i%2==0?"Odd":"Even")+"\">";
			txt += "<a href='javascript:ofwat.itemSelect.useTable(\"" + model.tableLinks[i].uri + "/structure\")'/>" + ofwat.escapeHTML(model.tableLinks[i].name) + "</a>";
			txt +="</td></tr>";

		}
		txt+="</table>";
		var tableList = dojo.byId("tableList");
		var tables = tableList.innerHTML;
		// TODO work out where to add this table of tables - shuffle the tables..
		tableList.innerHTML = tables + txt;
	}
};

ofwat.itemSelect.useTable = function (uri) {
	ofwat.rest.loadJSON(uri, ofwat.itemSelect.addToReport);
};

ofwat.itemSelect.addToReport = function() {
	var txt="";
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	{
		ofwat.rest.handleError( ofwat.rest.xmlhttp.responseXML);
		return;
	}
	var reportStructure = JSON.parse(ofwat.rest.xmlhttp.responseText);
	
	// get the modelItems - put in the selected items field
	var modelItems = reportStructure.modelItemDtos;
	var selectedItems = dojo.byId("chosenList");
	
	for (i=0; i<modelItems.length; i++){
		var modelItem = modelItems[i];
		var newValue = modelItem.itemId+"#"+ modelItem.modelId+"#"+modelItem.modelPropertiesMapId;
		// if item not already in list
		var exists=false;
		for (j=0;j<selectedItems.length;j++){
			if (selectedItems.options[j].value==newValue) {
				exists=true;
				break;
			}
		}
		if (!exists){
			var newOption = document.createElement('option');
			newOption.value = newValue;
			newOption.text = ofwat.escapeHTML(modelItem.itemCode + ": " +modelItem.itemName+" ["+modelItem.modelCode+"]"); 
			// add another option to the select list
			try {
				selectedItems.add(newOption, null); // standards compliant; doesn't work in IE
			}
			catch(ex) {
				selectedItems.add(newOption); // IE only
			}
		}
	}
	// get the intervals - put in the selected intervals field
	intervals = reportStructure.intervalIds;
	
	var list1 = dojo.byId('list1');
	list1items= list1.getElementsByTagName("li");
	for (i=0;i<intervals.length;i++){
		// get the interval LI, move it to list2
		// remove it from list1...
		for (j=0;j<list1items.length;j++){
			li = list1items[j];
			if (li.value==intervals[i]){
				list1.removeChild(li);
				// add to list 2
				list2.appendChild(li);
			}
		}
	}
	ofwat.listSort.prepItems();
};


ofwat.itemSelect.removeTablesForModel = function (modelId){
	// find the table for this model
	var mt = dojo.byId("modelTable"+modelId);
	// Remove the table
	if (mt!=null){
		var parent = mt.parentNode;
		parent.removeChild(mt);
	}
};

ofwat.itemSelect.selectAllClicked = function(thisBox) {
	var myForm = document.getElementById("modelForm");
	
	var modelId = null;
	
	for (var i=0;i<myForm.elements.length;i++) {
		var e = myForm.elements[i];
		if ( (e.name == 'model') && (e.type=='checkbox')){
			e.checked = thisBox.checked;
			modelId = e.value.split("#");
			if (modelId[0]!='All'){
				if (thisBox.checked == true){
					this.getTablesForModel(modelId[0]);
				} else {
					this.removeTablesForModel(modelId);
				}
			}
		}
	}
	return true;
};

ofwat.itemSelect.getFilterIntervals = function(){
	// retrieve models from server
	// put into a popup box that the user can select or remove them from
	ofwat.rest.loadJSON("../../rest-services/interval", this.displayIntervalOptions);
};


ofwat.itemSelect.showOverlay = function(dlg) {
	dojo.byId(dlg).style.display='block';
	dojo.byId('fade').style.display='block';
};

ofwat.itemSelect.hideOverlay = function(dlg){
	dojo.byId(dlg).style.display='none';
	dojo.byId('fade').style.display='none';	
};

ofwat.itemSelect.populateIntervalOptions = function(){
	var txt;
	if(ofwat.rest.xmlhttp.readyState!=4) return;
	if(ofwat.rest.xmlhttp.status!=200)
	  {
		ofwat.rest.handleError( ofwat.rest.xmlhttp.responseXML);
	  	return;
	  }
	  var intervalRespArr= JSON.parse(ofwat.rest.xmlhttp.responseText);
	  var options="";
	 
	    for (i=0;i<intervalRespArr.length;i++)
	    {
	    	var name = intervalRespArr[i].name;
	    	var intervals = intervalRespArr[i].intervalDtos;
	    	
	    	for (j=0; j<intervals.length;j++){
	    		var interval=intervals[j];
	    		options = options + "<li class=\"list1\" value=\""+interval.id+"\">"+ofwat.escapeHTML(interval.name) + "</li>";
	    	}
	    }
	   dojo.byId('list1').innerHTML=options;

		
	   // recalculate height of list box
	//   dojo.byId('play').height = intervals.length*20;
};


ofwat.itemSelect.saveReport = function(){
		
	// check name field
	if(null== dojo.byId("reportName").value || "" == dojo.byId("reportName").value){
		alert("the report must have a name");
		return false;
	}
	
	// check there are some items
	if(dojo.byId("list2").childNodes.length < 1){
		alert("You must select at least one interval");
		return false;
	}
	if(dojo.byId("chosenList").length < 1){
		alert("You must select at least one item");
		return false;
	}
	
	alert("report save executed");
	
	// bundle up the report name and the itempropertyIds and intervalIds into a report
	
	// TODO - declare these properly
	reportDto = new Object();
	intervalIds = new Array();
	itemPropIds = new Array();
	modelItems = new Array();
	reportDto.name =  dojo.byId("reportName").value;
	reportDto.intervalIds = intervalIds;
	reportDto.modelItemDtos = modelItems;
	var intervalList = dojo.byId("list2");
	var j=0;
	for (i=0; i<intervalList.childNodes.length; i++){
		if (intervalList.childNodes[i].nodeName=="LI"){
			reportDto.intervalIds[j] = intervalList.childNodes[i].value;
			j++;
		}
	}
	
	// populate the ModelItemDtos
	for(i = 0; i < dojo.byId("chosenList").length; i++){
		var modelItemDto = new Object();
		// itemId#modelId#modelPropertiesMapId
		var split =dojo.byId("chosenList")[i].value.split("#");
		modelItemDto.itemId= split[0];
		modelItemDto.modelId = split[1];
		modelItemDto.modelPropertiesMapId = split[2];
		reportDto.modelItemDtos[i] = modelItemDto;
		
//		private String modelCode;
//		private String modelName;
//		private String itemCode;
//		private String itemName;

	}
	
	var xhrArgs = {
            url: "../../rest-services/report",
            putData: dojo.toJson(reportDto),
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
            load: function(data) {
    			ofwat.showMessage("");
            }
        };
	ofwat.showMessage("Saving report... <img border='0' src='../../images/loader.gif'/>");
	dojo.xhrPut(xhrArgs);
};


ofwat.itemSelect.ItemMove = function(offset){	
	var chosenList = dojo.byId('chosenList');
	idx =chosenList.selectedIndex; 
	if( (-1 == idx) || (idx + offset) < 0 || (idx + offset) > (chosenList.options.length-1)) {
		// either nothing is selected or the item cannot be moved any further in that direction   
		return;
	}
	var selText = chosenList.options[idx].text;
	var selValue = chosenList.options[idx].value;
	chosenList.options[idx].text = chosenList.options[idx + offset].text; 
	chosenList.options[idx].value = chosenList.options[idx + offset].value; 
	chosenList.options[idx + offset].text = selText; 
	chosenList.options[idx + offset].value = selValue; 
	chosenList.selectedIndex = idx + offset;	
};

//Adds a selected item from startList into the chosen item list, resultList

ofwat.itemSelect.addItem = function(startList, endList) {

	var resultList = dojo.byId(startList);
	var resultIndex = resultList.selectedIndex;
	var resultOptions = resultList.options;
	var chosenList = dojo.byId(endList);
	var chosenOptions = chosenList.options;
	var chosenOLength = chosenOptions.length;

	// An item must be selected
	if (resultIndex > -1) {
		chosenOptions[chosenOLength] = new Option(resultList[resultIndex].text);
		chosenOptions[chosenOLength].value = resultList[resultIndex].value;
		// If single selection, remove the item from the select list
		if (singleSelect) {
			resultOptions[resultIndex] = null;
		}
		
		var tempText;
		var tempValue;
			
		while (chosenOLength > 0 && chosenOptions[chosenOLength].value < chosenOptions[chosenOLength-1].value) {
			tempText = chosenOptions[chosenOLength-1].text;
			tempValue = chosenOptions[chosenOLength-1].value;
			chosenOptions[chosenOLength-1].text = chosenOptions[chosenOLength].text;
			chosenOptions[chosenOLength-1].value = chosenOptions[chosenOLength].value;
			chosenOptions[chosenOLength].text = tempText;
			chosenOptions[chosenOLength].value = tempValue;	
			chosenOptions = chosenOLength - 1;
		}
		
	}
};