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
if(!ofwat.editor){
	alert("missing import: editor.js");
}
if(!ofwat.keyUtils){
	alert("missing import: keyUtils.js");
}
ofwat.modelPage = {
	oTable: null,
	oDataList : null
};

var omp = ofwat.modelPage;
var oed = ofwat.editor; 
var cgSelect = null;
var codeSelects = null;
var companyId = null;
var company = null;

//adding a couple of methods so that Array can implement Set
// element must implement meaningful equals(other) method
Array.prototype.contains = function (element) {
	 for (var i = 0; i < this.length; i++) {
		 if (this[i].equals(element)) {
			 return true;
		 }
	 }
	 return false;
};

Array.prototype.add = function(element){
	if(!this.contains(element)){
		this[this.length] = element;
	}
};

// and create an equals method to use with groupEntries
ofwat.modelPage.groupEquals = function(other){
	return other.id == this.id;
};

ofwat.modelPage.populatePage = function(url){
	ofwat.showMessage("Loading Data... <img border='0' src='../../../../images/loader.gif'/>");
	var xhrArgs = {
			url: url,
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				omp.processData(data);
				ofwat.showMessage("");
			},
			error: function(error) {
				ofwat.showError(error);
			}
	};
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.modelPage.processData = function(data){
	oed.getBasket();
	oed.oLocks = data.locks;
	oed.lockTimer();
	ofwat.modelPage.expandGroupRows(data.groupEntries);
	
	ofwat.modelPage.assignDefaultRunIds(data.defaultRunIdMap);
		
	ofwat.modelPage.replaceValues(data);
	ofwat.modelPage.removeBlankLines();
};


ofwat.modelPage.removeBlankLines = function(){
	var rows = dojo.query('tr.optionalRow');
	
	for(var i = 0; i < rows.length; i++){
		var currentRow = rows[i];
		var hasDataCells = false;
		var hasData = false;
		for (var j=0; j < currentRow.cells.length; j++) {
			var currentCell = currentRow.cells[j];
			var cellClasses = dojo.attr(currentCell, "class");
			if (cellClasses.indexOf("input") != -1 ||
				cellClasses.indexOf("copy") != -1 ) {
				hasDataCells = true;
				if (currentCell.firstChild &&
					currentCell.firstChild.firstChild &&
					currentCell.firstChild.firstChild.value &&
					currentCell.firstChild.firstChild.value.length >0) {
					hasData = true;
				}
			}
		}
		if (hasDataCells && !hasData) {
			if (currentRow) {
				dojo.addClass(currentRow, "hideRow");
			}
		}
	}
};

ofwat.modelPage.buildGroups = function(groupArray){
	
	var selectDiv = dojo.byId("groupSelect");
	var groupIndex = 0;
	
	function compare(a, b) {
		   if (a.ordinal < b.ordinal) {
		      return -1;
		   }
		   if (a.ordinal > b.ordinal) {
		      return 1;
		   }
		   if (a.ordinal == b.ordinal) {
		      return 0;
		   }
		}

	groupArray.sort(compare);
	
	if(selectDiv){
		var txt = "<select onchange='javascript:ofwat.modelPage.swapGroupValues(this.options[this.selectedIndex].value)';>";
		for(var i = 0; i < groupArray.length; i++){
			txt = txt + "<option value=" + groupArray[i].id + ">" + ofwat.escapeHTML(groupArray[i].description) + "</option>";
		}
		txt = txt + "</select>";
		
		selectDiv.innerHTML = txt;
		groupIndex = (ofwat.getQueryVariable('groupIndex')!=null)?ofwat.getQueryVariable('groupIndex'):0;
		selectDiv.firstChild.selectedIndex=groupIndex;
	}

	// fire the initial update
	ofwat.modelPage.swapGroupValues(groupArray[groupIndex].id);
};

/**
 * This routine populates values or pages with group
 * selectors. It looks for cells with ids in the format
 * item-itemproperties-interval-company-groupEntry.
 * 
 * Removes the group id from the dataDTO identifier 
 * 
 * Also replaces the default edit and dictionary pane functions
 * with versions suitable for grouped data
 */ 
ofwat.modelPage.swapGroupValues = function(groupId){
	var cellsToFill = dojo.query("td > div");
	for ( var int = 0; int < cellsToFill.length; int++) {
		var dataCell = cellsToFill[int];
		var identifier = dojo.attr(dataCell, "class");
		identifier = ofwat.keyUtils.getKeyFromString(identifier);

		if (ofwat.keyUtils.isKeyString(identifier)){
			var oDataDto = omp.oDataList[identifier];
			var idWithGroup = null;
			if (!oDataDto) {
				idWithGroup = ofwat.keyUtils.setGroupEntryId(identifier, groupId);
				oDataDto = omp.oDataList[idWithGroup];
			}
		
			if (oDataDto){
				var onchInputFunc = "oed.postTableUpdate('" + oDataDto.identifier +"', this.value, 'value')";
				var onchCodeListFunc = "oed.postTableUpdate('" + oDataDto.identifier +"', this.options[this.selectedIndex].text, 'value')";
				var dictionaryFunc = omp.populateGroupDictionaryPane;
				ofwat.editor.valueCellConfig(dataCell, oDataDto, dictionaryFunc, codeSelects, onchInputFunc, onchCodeListFunc);
			}
			// and the CG
			if (identifier.indexOf("CG",0)!= -1){
				var idWithoutCG = ofwat.keyUtils.removeCG(identifier);
				oDataDto = omp.oDataList[identifier];
				if (!oDataDto){
					var idWithGroup = ofwat.keyUtils.setGroupEntryId(idWithoutCG, groupId);
					oDataDto = omp.oDataList[idWithGroup];
				}
				if (oDataDto){
					var onchCGFunc = "oed.postTableUpdate('" + identifier +"', this.options[this.selectedIndex].text, 'cg')";
					var dictionaryFunc = omp.populateGroupDictionaryPane;
					ofwat.editor.confidenceGradeCellConfig({dataCell:dataCell, dto:oDataDto, dictionaryFunc:dictionaryFunc, cgSelect:cgSelect, onchCGFunc:onchCGFunc});
				} 
			}
		}
	}
	
};


/**
 * Default value update for non-grouped pages.
 * 
 * Grouped pages (expanded or drop down) will be further modified
 * by replace group data or replace expanded group data functions
 */
ofwat.modelPage.replaceValues = function(data){
	
	// Create the CG and code selection drop downs
	if (!cgSelect) {
		cgSelect = ofwat.editor.dropDownCell(data.cgs);
	}
	if (!codeSelects) {
		codeSelects = new Array();
		for(var i in data.codeListsByCodeList){
			codeSelects[i] = ofwat.editor.dropDownCell(data.codeListsByCodeList[i]);
		}
	}
	
	omp.oDataList = data.dataList;
	// build the drop down box if applicable
	if (dojo.byId('groupSelect')){
		omp.buildGroups(data.groupEntries);
	} else {
 		ofwat.modelPage.fillInCells(data.groupEntries);
	}
	
	// scroll the navigation pane to the right place
	var container = dojo.byId("navPane");
	var el = dojo.byId("current");

	// Position container at the top line then scroll el into view
	container.scrollTop = 0;
	el.scrollIntoView(true);
	// Scroll back nothing if element is at bottom of container else do it
	// for half the height of the containers display area
	var scrollBack = (container.scrollHeight - container.scrollTop <= container.clientHeight) ? 0 : container.clientHeight/2;
	container.scrollTop = container.scrollTop - scrollBack;
	
	// display the option to hide/show the navigation pane
	ofwat.toggleRightPane('showFixedDiv', 'fixeddiv', true);

};

/**
 * once the group rows have been expanded, there may be redundant lines
 * as there isn't always data for every item for each group entry.
 * 
 *  If a row has no data for the items derived from the data then remove it.
 *  Calculated cells are removed if there is no corresponding inp
 */
ofwat.modelPage.removeEmptyGroupLines = function(){
	dojo.query(".groupRow").forEach(function(tr){
		var hasData = false;
		var inputsTds = dojo.query("td.input", tr);
		for(var i = 0; i < inputsTds.length; i++){
			// set realRow = true if data exists
			
			var divs =dojo.query("div", inputsTds[i]);
			if(divs){
				if(null !=  divs[0].innerHTML && "" != divs[0].innerHTML){
					realRow = true;
					hasData = true;
				}
			}
		}
		if(!hasData){
			dojo.addClass(tr, "cleanup");
		}
	});
	
	dojo.query("tr.cleanup").orphan();
	; // all the <tr>s
	
	
};


ofwat.modelPage.populateGroupDictionaryPane = function (obj){
	
	var divId = obj.data.divId;
	var dto = obj.data.dto;
	var classId = dto.identifier; //obj.data.dtoKey; //This is the key. 
	// remove highlight class from any div already highlighted
	dojo.query("div").removeClass("highlight");
	dojo.query("td").removeClass("highlight");
	// highlight this div
	
	//Add to the parent element. 
	var cells = dojo.query("."+classId);//.addClass("highlight");
	
	if (cells.length==0) {
		var idWithoutGroup = ofwat.keyUtils.removeGroupId(classId);
		//Add to the parent element.
		cells = dojo.query("."+idWithoutGroup);//.addClass("highlight");
		if (cells.length==0) {
			var idWithUndefinedGroup = ofwat.keyUtils.setGroupEntryId(idWithoutGroup, "undefined");
			//Add to the parent element.
			cells = dojo.query("."+idWithUndefinedGroup);//.addClass("highlight");
		}
	}
	
	for(var i=0;i<cells.length;i++){
		dojo.addClass(cells[i].parentNode, "highlight")
		//console.log(cells[i]);
	}
	
	var oDataDto = omp.oDataList[classId];
	
	ofwat.dictionary.showDictionary({dto:oDataDto, divId:divId, legacy:true});
	// no CG on the ID for retrieving audits
	var idWithoutCG = ofwat.keyUtils.removeCG(classId);
	ofwat.dictionary.showAudits({divId:idWithoutCG, dictionaryHtml:'', callback:null, legacy:true});
	
	var tabContainer = dijit.byId('rightMenu');
	var dictionaryTab = dijit.byId('dictionaryPane');
	tabContainer.selectChild(dictionaryTab);
	dictionaryTab.selected = true;
	
};

ofwat.modelPage.sendToReport = function(pageId) {
	ofwat.showMessage("Creating report");
	location.href="../../adhocReport.jsp?pageId=" + pageId; 
	// send the current page info to the reports page
	// open the report page, with the page info
};



ofwat.modelPage.fillInCells = function (groupEntries) {
			
	var cellsToFill = dojo.query("td > div");
	 
	// Works for non-grouped
	for ( var int = 0; int < cellsToFill.length; int++) {
		var dataCell = cellsToFill[int];
		var identifier = dojo.attr(dataCell, "class");
		identifier = ofwat.keyUtils.getKeyFromString(identifier);
		if (!ofwat.keyUtils.isKeyString(identifier)){
			alert("not a key " + identifier);
		}
		var oDataDto = omp.oDataList[identifier];
		var idWithGroup = null;
		if (!oDataDto) {
			for (var j = 0; j < groupEntries.length; j++) {
				var groupEntry = groupEntries[j];
				if (false == groupEntry.aggregate){
					idWithGroup = ofwat.keyUtils.setGroupEntryId(identifier, groupEntry.id);
					if (!ofwat.keyUtils.isKeyString(idWithGroup)){
						alert("not a key " + idWithGroup);
					}
					oDataDto = omp.oDataList[idWithGroup];
					if (oDataDto){
						break;
					}
				}
			}
		}
		if (oDataDto){
			var onchInputFunc = "oed.postTableUpdate('" + oDataDto.identifier +"', this.value, 'value')";
			var onchCodeListFunc = "oed.postTableUpdate('" + oDataDto.identifier +"', this.options[this.selectedIndex].text, 'value')";
			var dictionaryFunc = omp.populateGroupDictionaryPane;
			ofwat.editor.valueCellConfig(dataCell, oDataDto, dictionaryFunc, codeSelects, onchInputFunc, onchCodeListFunc);
		}
		// and the CG
		if (identifier.indexOf("CG",0)!= -1){
				var idWithoutCG = ofwat.keyUtils.removeCG(identifier);
			oDataDto = omp.oDataList[identifier];
			if (!oDataDto){
				identifier = ofwat.keyUtils.setGroupEntryId(idWithoutCG, groupEntry.id);
				oDataDto = omp.oDataList[identifier];
			}
			if (oDataDto){
				var onchCGFunc = "oed.postTableUpdate('" + identifier +"', this.options[this.selectedIndex].text, 'cg')";
				var dictionaryFunc = omp.populateGroupDictionaryPane;
				ofwat.editor.confidenceGradeCellConfig({dataCell:dataCell, dto:oDataDto, dictionaryFunc:dictionaryFunc, cgSelect:cgSelect, onchCGFunc:onchCGFunc});
			} 
		}
	}
	
	
	// finally clear up the unwanted lines
	ofwat.modelPage.removeEmptyGroupLines();
};

ofwat.modelPage.expandGroupRows = function(groupsEntries){
	var rows = dojo.query('tr.groupRow');
	
	var groupLineNumber = 0;
	for(var i = 0; i < rows.length; i++){
		var currentRow = rows[i];
		for (var j=0; j < groupsEntries.length; j++) {
			var groupEntry = groupsEntries[j];
			if (groupEntry.ordinal!=0){
				if (1 == groupsEntries.length) {
					ofwat.modelPage.addGroupIds(currentRow, groupsEntries, j);
				}	
				else { 
					if (ofwat.modelPage.lastGroupEntry(groupsEntries, j)) {
						ofwat.modelPage.addGroupIds(currentRow, groupsEntries, j);
						ofwat.modelPage.updateLineNum(currentRow, ++groupLineNumber);
					}
					else {
						var cloneRow = dojo.clone(currentRow);
						ofwat.modelPage.addGroupIds(currentRow, groupsEntries, j);
						ofwat.modelPage.updateLineNum(currentRow, ++groupLineNumber);
						dojo.place(cloneRow, currentRow, "after");
						currentRow = cloneRow;
					}
				}
			}
		}
	}
};

ofwat.modelPage.addGroupIds = function(row, companyGroupsEntries, groupEntryIndex){
	var cells = dojo.query("div", row);
	for (var i = 0;  i < cells.length;  i++)  {
		var div = cells[i];
		var key = dojo.attr(div, "class");
		key = ofwat.keyUtils.getKeyFromString(key);
		dojo.removeClass(div, key);
		var groupEntryId = companyGroupsEntries[groupEntryIndex].id; 
		key = ofwat.keyUtils.setGroupEntryId(key, groupEntryId);
		dojo.addClass(div, key);
		dojo.attr(div, "id", key);
	}
};

ofwat.modelPage.firstGroupEntry = function(j) {
	return j == 0;
};

ofwat.modelPage.lastGroupEntry = function(companyGroupsEntries, j) {
	return companyGroupsEntries.length -1 == j;
};

ofwat.modelPage.updateLineNum = function(row, j) {
	dojo.forEach(dojo.query(".lineNumber", row), function(lnr, m){
		lnr.innerHTML += ("." + j);
	});
};

ofwat.modelPage.populatePageWithCompany = function (modelCode) {
	var queryObject = dojo.queryToObject(dojo.doc.location.search.substr((dojo.doc.location.search[0] === "?" ? 1 : 0)));
	if(queryObject.companyId){
		ofwat.createCookie("companyValue", queryObject.companyId, 100);
	}
	companyId = ofwat.readCookie("companyValue"); 
	ofwat.modelPage.loadCompany();
	ofwat.modelPage.setModelHeader(modelCode);
	ofwat.modelPage.putCompanyIdInCellKeys();
	ofwat.modelPage.filterSectionsByCompanyType();
	ofwat.modelPage.filterNavigationTablesByCompanyType();
};

ofwat.modelPage.setModelHeader = function(modelCode){
	var headerDiv = dojo.query(".header")[0];
	headerDiv.innerHTML = ofwat.formattedTimeAndDate() + ": " + modelCode + " for " + company.code + " " + company.name; 
};

ofwat.modelPage.putCompanyIdInCellKeys = function(){
	var cells = dojo.query("td > div");
	for (var i = 0; i < cells.length; i++) {
		var div = cells[i];
		var key = dojo.attr(div, "class");
		key = ofwat.keyUtils.getKeyFromString(key);
		dojo.removeClass(div, key);
		key = ofwat.keyUtils.removeGroupId(ofwat.keyUtils.setCompanyId(key, companyId));
		dojo.addClass(div, key);
	}
};

ofwat.modelPage.showPage = function (pageName) {
	var nextPage=pageName + ".page?companyId=" + companyId;
	location.href = nextPage; 	
};

ofwat.modelPage.exportTableToExcel = function (pageId) {
	var excelPage= "/Fountain/rest-services/table/excel/tables?tableId=" + pageId + "&companyId=" + companyId;
	window.open(excelPage, "_blank");
};

ofwat.modelPage.loadCompany = function() {
	var xhrArgs = {
        url: "/Fountain/rest-services/company/" + companyId,
        handleAs: "json",
		sync: true,
        headers: {"accept": "application/json"},
        load: function(data){
			ofwat.modelPage.parseCompany(data);
			ofwat.showMessage("");
		},
        error: function(error) {
            ofwat.showError("parseCompany errored", error);
		}
    };
	ofwat.showMessage("Loading company... <img border='0' src='/Fountain/images/loader.gif'/>");
    dojo.xhrGet(xhrArgs);
};

ofwat.modelPage.parseCompany = function(data) {
	company = data;
};

ofwat.modelPage.filterSectionsByCompanyType = function(){
	var rows = dojo.query('tr.companyType');
	
	for(var i = 0; i < rows.length; i++){
		var currentRow = rows[i];
		var classes = dojo.attr(currentRow, "class");
		if (classes.indexOf("WASC") != -1) { // wasc section
			if ("WASC" != company.companyType.code) { // hide for none wasc companies
				dojo.addClass(currentRow, "hideRow");
			}
		}
		else if (classes.indexOf("WOC") != -1) { // woc section
			if ("WOC" != company.companyType.code) { // hide for none woc companies
				dojo.addClass(currentRow, "hideRow");
			}
		}
	}
};

ofwat.modelPage.filterNavigationTablesByCompanyType = function(){
	var divs = dojo.query('li.NavPane');
	
	for(var i = 0; i < divs.length; i++){
		var currentDiv = divs[i];
		var classes = dojo.attr(currentDiv, "class");
		if (classes.indexOf("WASC") != -1 ) { // wasc section
			if ("WASC" != company.companyType.code) { // hide for none wasc companies
				dojo.addClass(currentDiv, "invisible");
			}
		}
		else if (classes.indexOf("WOC") != -1) { // woc section
			if ("WOC" != company.companyType.code) { // hide for none woc companies
				dojo.addClass(currentDiv, "invisible");
			}
		}
	}
};

ofwat.modelPage.onLoad = function() {
    Handlebars.registerHelper('inRole', ofwat.checkUserInRole); /*TODO needs to be global i.e everywhere where we use clientside templates.*/
    ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);            
    ofwat.loadTemplate(dojo.query(".footerContent")[0], ofwat.template.footer);
    ofwat.loadTemplate(dojo.query(".editButtons")[0], ofwat.template.reportEditButtons);
    ofwat.loadTemplate(dojo.query("#bannerTheme")[0], ofwat.template.bannerTheme);

    dojo.query("div[rel]").forEach(function(n) {
        var className = dojo.attr(n, "rel");
        // now set it
        dojo.attr(n, "dojoType", className);
    });
    //dojo.parser.parse("progtabwrapper");
    dojo.parser.parse();
    ofwat._initLoader();            

		helpPage="Table Views";
	ofwat.wikiPage();

//	ofwat.modelPage.replaceRunIdInCellKeys();
	
    var allContentDiv = dojo.query("#allContent")[0];
	dojo.removeClass(allContentDiv, "invisible");
};

ofwat.modelPage.assignDefaultRunIds = function(defaultRunIdMap){
	var cells = dojo.query("td > div");
	for (var i = 0; i < cells.length; i++) {
		var div = cells[i];
		var key = dojo.attr(div, "class");
		key = ofwat.keyUtils.getKeyFromString(key);
		var runIdInKey = ofwat.keyUtils.getRunId(key);
        if (runIdInKey in defaultRunIdMap) {
			dojo.removeClass(div, key);
			key = ofwat.keyUtils.setRunId(key, defaultRunIdMap[runIdInKey]);
			dojo.addClass(div, key);
		}
	}
};

