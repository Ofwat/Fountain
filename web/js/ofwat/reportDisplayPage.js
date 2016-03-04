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
ofwat.reportDisplayPage = {
		companyArray: {},
		dataTagArray: {},
		reportUrl: null,
		reportHasCompany: null,
		editingCompanyTypeId: null,
		editingCompanyId: null,
		editingAgendaId: null,
		editingRunId: null,
		editingDataTagId: null,
		reportHasRun: null,
		chunkSet: null
};


var ord = ofwat.reportDisplayPage;
var oed = ofwat.editor;
var cgSelect = null;
var codeSelects = null;
var groupsByCompanies = null;


ofwat.reportDisplayPage.writeEditingCompanyId = function () {
	if (!reportHasCompany) {
		ofwat.createCookie("reportEditingCompanyType", editingCompanyTypeId, 1);
		ofwat.createCookie("reportEditingCompany", editingCompanyId, 1);
	}
}

ofwat.reportDisplayPage.writeEditingDataTagId = function () {
	if (!reportHasRun) {
		ofwat.createCookie("reportEditingAgenda", editingAgendaId, 1);
		ofwat.createCookie("reportEditingRun", editingRunId, 1);
		ofwat.createCookie("reportEditingDataTag", editingDataTagId, 1);
	}
}

ofwat.reportDisplayPage.populateReport = function(url){
	reportUrl = url;
	ofwat.reportCompanyRunSelection.getReportDetails();
	var reportId = ofwat.reportDisplayPage.getReportId();
	ofwat.reportDisplayPage.selectCompanyDialog(reportId);
	var companyType = null;
	var company = null;
	var agenda = null;
	var run = null;
	var dataTag = null;
	var populate = true;

	if (!reportHasCompany) {
		var companyTypeDiv = dojo.byId('companyTypeDiv');
		dojo.removeClass(companyTypeDiv, "invisible");
		var companyDiv = dojo.byId('companyDiv');
		dojo.removeClass(companyDiv, "invisible");

		var companyType = ofwat.readCookie("reportEditingCompanyType");
		var company = ofwat.readCookie("reportEditingCompany");
		if (null == companyType ||
			null == company) {
			ofwat.reportDisplayPage.setupDiv(reportId);
			populate = false;
		}
	}
	else {
		var companyTypeDiv = dojo.byId('companyTypeDiv');
		dojo.addClass(companyTypeDiv, "invisible");
		var companyDiv = dojo.byId('companyDiv');
		dojo.addClass(companyDiv, "invisible");
	}

	if (!reportHasRun) {
		var agendaDiv = dojo.byId('agendaDiv');
		dojo.removeClass(agendaDiv, "invisible");
		var runDiv = dojo.byId('runDiv');
		dojo.removeClass(runDiv, "invisible");
		var dataTagDiv = dojo.byId('dataTagDiv');
		dojo.removeClass(dataTagDiv, "invisible");

		var agenda = ofwat.readCookie("reportEditingAgenda");
		var run = ofwat.readCookie("reportEditingRun");
		var dataTag = ofwat.readCookie("reportEditingDataTag");
		if (null == agenda ||
			null == run ||
			null == dataTag) {
			if (populate) {
				ofwat.reportDisplayPage.setupDiv(reportId);
				populate = false;
			}
		}
	}
	else {
		var agendaDiv = dojo.byId('agendaDiv');
		dojo.addClass(agendaDiv, "invisible");
		var runDiv = dojo.byId('runDiv');
		dojo.addClass(runDiv, "invisible");
		var dataTagDiv = dojo.byId('dataTagDiv');
		dojo.addClass(dataTagDiv, "invisible");
	}
	
	if (populate) {
		ofwat.reportDisplayPage.populateReportWithData(url, companyType, company, agenda, run, dataTag);
	}
};

ofwat.reportDisplayPage.setupDiv = function(reportId) {
	var selectCompanyDiv = dojo.byId("selectCompanyDiv");
	
	var companyType = ofwat.readCookie("reportCompanyType");
	if (null != companyType) {
		dojo.byId('companyType').value = companyType;
	}
	var company = ofwat.readCookie("reportCompany");
	if (null != company) {
		dojo.byId('company').value = company;
		ofwat.reportCompanyRunSelection.loadDataTags(reportId);
	}

	var agenda = ofwat.readCookie("reportAgenda");
	if (null != agenda) {
		dojo.byId('agenda').value = agenda;
		ofwat.reportCompanyRunSelection.updateRuns();
	}
	var run = ofwat.readCookie("reportRun");
	if (null != run) {
		dojo.byId('run').value = run;
		ofwat.reportCompanyRunSelection.loadDataTags(reportId);
	}
	var dataTag = ofwat.readCookie("reportDataTag");
	if (null != dataTag) {
		dojo.byId('dataTag').value = dataTag;
	}

	dojo.removeClass(selectCompanyDiv, "invisible");
	dojo.byId('selectCompanyDiv').style.display='block';
	dojo.byId('fade').style.display='block';
	dojo.byId('goButton').focus();
	
	var sel = dojo.byId('dataTag');
	if(sel.selectedIndex == -1){
		sel.selectedIndex = 0;	
	}
	
};

ofwat.reportDisplayPage.populateReportWithData = function(url, companyType, company, agenda, run, dataTag) {
	editingCompanyTypeId = companyType;
	editingCompanyId = company;
	ofwat.createCookie("reportCompanyType", companyType, 1);
	ofwat.createCookie("reportCompany", company, 1);
	if (!reportHasCompany && 
		null != company) {
		url = url + "?companyId=" + company;
	}

	if (!reportHasRun) {
		editingAgendaId = agenda;
		editingRunId = run;
		editingDataTagId = dataTag;
		ofwat.createCookie("reportAgenda", agenda, 1);
		ofwat.createCookie("reportRun", run, 1);
		ofwat.createCookie("reportDataTag", dataTag, 1);
	}
	else {
		agenda = null;
		run = null;
		dataTag = null;
	}
	
	if (null != agenda &&
		null != run &&
		null != dataTag) {
		if (url.indexOf("?") > -1) {
			url = url + "&runId=" + run + "&tagId=" + dataTag;
		}
		else {
			url = url + "?runId=" + run + "&tagId=" + dataTag;
		}
	}
	
	var xhrArgs = {
			url: url,
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				ord.processData(data);
				ofwat.showMessage("");
			},
			error: function(error, ioargs) {
				ofwat.editor.removeCompanySelection();
				ofwat.showError(error, "Error: " + ioargs.xhr.response);
				dojo.removeAttr('goButton', 'disabled');
			}
	};
	
	//Disable the clicked button.
	dojo.setAttr('goButton', 'disabled', 'disabled');
	ofwat.showMessage("Loading report... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);

	ofwat.eraseCookie("reportEditingCompanyType");
	ofwat.eraseCookie("reportEditingCompany");
	ofwat.eraseCookie("reportEditingDataTag");
	ofwat.eraseCookie("reportEditingRun");
	ofwat.eraseCookie("reportEditingAgenda");
};


ofwat.reportDisplayPage.storeChunkSet = function(chunkSet){
	ord.chunkSet = chunkSet;
	ord.chunkSet.requestedChunkCount = 0;
	ord.chunkSet.processedChunkCount = 0;
	ord.chunkSet.lastChunk = false;
};

ofwat.reportDisplayPage.getChunk = function(chunkId){
	var url = "/Fountain/rest-services/report/chunk/" + ord.chunkSet.chunkSetId + "/" + chunkId
	ord.chunkSet.requestedChunkCount++;
	ofwat.showMessage("Loading report data chunk... <img border='0' src='../../images/loader.gif'/>");
	var xhrArgs = {
			url: url,
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				//ord.processData(data);
				//Do we update the count here?
				console.log("Getting a chunk...");
				ofwat.reportDisplayPage.processChunks(data);
				ofwat.showMessage("");
			},
			error: function(error, ioargs) {
				//ofwat.editor.removeCompanySelection();
				ofwat.showError(error, "Error: " + ioargs.xhr.response);
				//dojo.removeAttr('goButton', 'disabled');
			}
	};
	ofwat.showMessage("Loading report chunk... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

//CHUNKING populate the appropriate cells with the chunk data and update the class
//for the last populated cell - we will need update the chunk managment so we know what the next chunk is!
ofwat.reportDisplayPage.processChunks = function(chunks){
	//console.log(chunks);
	if(!ord.oReportData.dataList){
		ord.oReportData.dataList = {};	
	}
	for(var i=0;i<chunks.length;i++){
		//console.log("Processing chunk" + chunks[i]);
		//Add to the report. 
		ord.oReportData.dataList[chunks[i].identifier] = chunks[i];	
		//Update the CSS!
		
	}
	//ofwat.reportDisplayPage.getChunk(chunk.fileRef.substring(0,(chunk.fileRef.length-5)));
	ofwat.reportDisplayPage.insertValues();
	ord.chunkSet.processedChunkCount++;
	if(ord.chunkSet.processedChunkCount == ord.chunkSet.chunkCount){
		ord.chunkSet.lastChunk = true;
	}
}

ofwat.reportDisplayPage.getNextChunk = function(){
	var chunkSet = ord.chunkSet;
	//ord.chunkSet.nextChunk = chunkSet.chunks.fileRef.substring(0,(chunkSet.chunks.fileRef.length-5));	
	var ref = chunkSet.chunks[ord.chunkSet.requestedChunkCount].fileRef.substring(0,(chunkSet.chunks[ord.chunkSet.requestedChunkCount].fileRef.length-5));
	console.log("Processing next chunk with id: " + ref);
	if(!ord.chunkSet.lastChunk){
		ofwat.reportDisplayPage.getChunk(ref);
	}
}

ofwat.reportDisplayPage.getRemainingChunks = function(){
	while(ord.chunkSet.requestedChunkCount < ord.chunkSet.chunkCount){
		//This isn't going to block so we'll send thousands of them.... ?
		ofwat.reportDisplayPage.getNextChunk();
	}
}

ofwat.reportDisplayPage.fetchFirstChunk = function(){
	var chunkSet = ord.chunkSet;
	var chunkOrd = 0;
	//ord.chunkSet.nextChunk = chunkSet.chunks.fileRef.substring(0,(chunkSet.chunks.fileRef.length-5));	
	var ref = chunkSet.chunks[chunkOrd].fileRef.substring(0,(chunkSet.chunks[chunkOrd].fileRef.length-5));
	console.log("Processing first chunk with id: " + ref);
	ofwat.reportDisplayPage.getChunk(ref);
}

ofwat.reportDisplayPage.processData = function(data){
	
	//TODO CHUNKING We need to be able to make multiple calls here to get the chunks of data! 

	
	oed.getBasket();
	ord.oReportData = data;
	oed.oLocks = data.locks;
	oed.lockTimer();
	
	if(!reportHasCompany && reportHasRun){
		//Selecting just the company at runtime
		ofwat.reportDisplayPage.addCompanyIds(ord.oReportData.company.id);
		ofwat.reportDisplayPage.addTagIdCompanyFromMap(data.tagMap, ord.oReportData.company.id);
		ofwat.reportDisplayPage.insertCompany();		
	}
	
	if(reportHasCompany && !reportHasRun){
		//Selecting just the tag at runtime
		// ofwat.reportDisplayPage.insertCompany();
        ofwat.reportDisplayPage.addRunIds(ord.oReportData.run.id);
		ofwat.reportDisplayPage.addTagIdsFromMap(data.tagMap, ord.oReportData.run.id); //And add the run id!
		ofwat.reportDisplayPage.insertAgendaRunTag();
	}
	
	if(!reportHasCompany && !reportHasRun){
		//Selecting the run and company at runtime.
		ofwat.reportDisplayPage.addCompanyIds(ord.oReportData.company.id);
		ofwat.reportDisplayPage.insertCompany();
        ofwat.reportDisplayPage.addRunIds(ord.oReportData.run.id);
        ofwat.reportDisplayPage.addTagIds(ord.oReportData.tagId);		
		ofwat.reportDisplayPage.insertAgendaRunTag();
	}
	
	if (ord.oReportData.defaultRunIdMap) {
		ofwat.reportDisplayPage.assignDefaultRunIds(ord.oReportData.defaultRunIdMap);
	}
	
	ofwat.reportDisplayPage.expandRows(ord.oReportData.groupEntriesByCompany);
		
	//CHUNKING This is where we need to hook into the chunking calls i.e 
	//We won't have all the data at this point if the report is chunked.
	if(!data.chunked){
		ofwat.reportDisplayPage.insertValues();
	}else{
		//Get the first chunk and set up the chunk handling. 
		ofwat.reportDisplayPage.storeChunkSet(data.chunkSet);
		ofwat.reportDisplayPage.fetchFirstChunk();
		ofwat.reportDisplayPage.setupChunkDataFetchListener();
	}
	
	ofwat.editor.removeCompanySelection();
	
	//Set up the scrolling headers. 
	ofwat.reportDisplayPage.setupScrollingReportHeader();
	
	
};

ofwat.editor.removeCompanySelection = function () {
	dojo.byId('selectCompanyDiv').style.display='none';
	dojo.byId('fade').style.display='none';
};


/**
 * Overwrite the tagId in the cell with a new tagId looked up from the map that we have returned as part of the 
 * reportData. we will look up the new tag based on the companyId in the individual element.  
 * 
 * CASE 1 in Jira doc - https://ofwatdev.atlassian.net/wiki/pages/viewpage.action?pageId=4653058
 * 
 */
ofwat.reportDisplayPage.addTagIdsFromMap = function(tagMap, runId){
	if (tagMap && 
		"companyTagMap" == tagMap.tagMapType) {
	    var rows = dojo.query('tr.grouped');
	    for(var i = 0; i < rows.length; i++){
	        var row = rows[i];
	        for (var j = 0;  j < row.children.length;  j++)  {
	    		var td = row.children[j];
	    		if (dojo.hasClass(td, "input") ||
	    				dojo.hasClass(td, "calc")) {
	        		//Search the map to find the appropriate tagId to replace it with!
	                var div = td.children[0];
	                var key = div.id;
	        		var companyId = ofwat.keyUtils.getCompanyId(key);
	        		
        			//Look up from the map based on companyId
	        		var newTagId = tagMap.tags[companyId];
	        		if((dojo.hasClass(td, "addRunTagToKey")) && (newTagId != null)){
		                dojo.removeClass(div, key);
		                //update with the new tag.
		                key = ofwat.keyUtils.setTagId(key, newTagId);
		                key = ofwat.keyUtils.setRunId(key, runId);
		                dojo.addClass(div, key);
		                div.id = key;        		
	        		}
	        	}
	        }
        }
    }
}

/**
 * Overwrite the tagId in the cell with a new tagId looked up from the map that we have returned as part of the 
 * reportData but with the companyID as the key!
 * 
 * CASE 2 in Jira doc - https://ofwatdev.atlassian.net/wiki/pages/viewpage.action?pageId=4653058
 */
ofwat.reportDisplayPage.addTagIdCompanyFromMap = function(tagMap, companyId){
	if (tagMap && 
		"tagTagMap" == tagMap.tagMapType) {
		var rows = dojo.query('tr.grouped');
	    for(var i = 0; i < rows.length; i++){
	        var row = rows[i];
	        for (var j = 0;  j < row.children.length;  j++)  {
	    		var td = row.children[j];
	    		if (dojo.hasClass(td, "input") ||
	    				dojo.hasClass(td, "calc")) {
	                var div = td.children[0];
	                var key = div.id;        		
	        		var tagId = ofwat.keyUtils.getTagId(key);
	
	        		//Look up from the map based on tagId
	        		var newTagId = tagMap.tags[tagId];
	        		if(newTagId != null){
		                dojo.removeClass(div, key);
		                //update with the new tag.
		                key = ofwat.keyUtils.setTagId(key, newTagId);
		                //We are also setting the company Id to the passed company here. 
		                key = ofwat.keyUtils.setCompanyId(key, companyId);
		                dojo.addClass(div, key);
		                div.id = key;        		
	        		}        		   		      		
	        	}
	        }
        }
    }
}



/**
 * Add the tag id to the key if we are using a report where we select run/tag at runtime.
 * @param tagId
 */
ofwat.reportDisplayPage.addTagIds = function(tagId){
    var rows = dojo.query('tr.grouped');
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        for (var j = 0;  j < row.children.length;  j++)  {
            var td = row.children[j];
            if (dojo.hasClass(td, "addRunTagToKey")) {
                var div = td.children[0];
                var key = div.id;

                dojo.removeClass(div, key);
                key = ofwat.keyUtils.setTagId(key, tagId);
                dojo.addClass(div, key);
                div.id = key;
            }
        }
    }
}

/**
 * Add the run id to the key if we are using a report where we select run/tag at runtime.
 * @param runId
 */
ofwat.reportDisplayPage.addRunIds = function(runId){
    var rows = dojo.query('tr.grouped');
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        for (var j = 0;  j < row.children.length;  j++)  {
            var td = row.children[j];
            if (dojo.hasClass(td, "addRunTagToKey")) {
                var div = td.children[0];
                var key = div.id;

                dojo.removeClass(div, key);
                key = ofwat.keyUtils.setRunId(key, runId);
                dojo.addClass(div, key);
                div.id = key;
            }
        }
    }
}

ofwat.reportDisplayPage.assignDefaultRunIds = function(defaultRunIdMap){
    var rows = dojo.query('tr.grouped');
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        for (var j = 0;  j < row.children.length;  j++)  {
            var td = row.children[j];
			if (dojo.hasClass(td, "input") ||
				dojo.hasClass(td, "calc")) {
	            var div = td.children[0];
	            var key = div.id;
	            var runId = ofwat.keyUtils.getRunId(key)
	            if (runId in defaultRunIdMap) {
	            	dojo.removeClass(div, key);
	            	key = ofwat.keyUtils.setRunId(key, defaultRunIdMap[runId]);
	            	dojo.addClass(div, key);
	            }
	            div.id = key;
	        }
        }
    }
}

ofwat.reportDisplayPage.addCompanyIds = function(companyId){
	var rows = dojo.query('tr.grouped');
	
	for(var i = 0; i < rows.length; i++){
		var row = rows[i];
		for (var j = 0;  j < row.children.length;  j++)  {
			var td = row.children[j];
			if (dojo.hasClass(td, "input") ||
				dojo.hasClass(td, "calc")) {
				var div = td.children[0]; 
				var key = div.id;

				dojo.removeClass(div, key);
				key = ofwat.keyUtils.setCompanyId(key, companyId);
				dojo.addClass(div, key);
				div.id = key;
			}
		}
	}
};

ofwat.reportDisplayPage.insertCompany = function(){
	var companyNameCells = dojo.query('td.companyNamePlaceHolder');
	for (var i=0; i<companyNameCells.length; i++) {
		companyNameCells[i].innerHTML = ord.oReportData.company.name;
	}
	var companyAcronymCells = dojo.query('td.companyAcronymPlaceHolder');
	for (var i=0; i<companyAcronymCells.length; i++) {
		companyAcronymCells[i].innerHTML = ord.oReportData.company.code;
	}
};

ofwat.reportDisplayPage.insertAgendaRunTag = function(){
	var agendaNameCells = dojo.query('td.agendaNamePlaceHolder');
	for (var i=0; i<agendaNameCells.length; i++) {
		agendaNameCells[i].innerHTML = ord.oReportData.run.agenda.name;
	}
	var agendaCodeCells = dojo.query('td.agendaCodePlaceHolder');
	for (var i=0; i<agendaCodeCells.length; i++) {
		agendaCodeCells[i].innerHTML = ord.oReportData.run.agenda.code;
	}
	var runNameCells = dojo.query('td.runNamePlaceHolder');
	for (var i=0; i<runNameCells.length; i++) {
		runNameCells[i].innerHTML = ord.oReportData.run.name;
	}
	var runDescriptionCells = dojo.query('td.runDescriptionPlaceHolder');
	for (var i=0; i<runDescriptionCells.length; i++) {
		runDescriptionCells[i].innerHTML = ord.oReportData.run.description;
	}
	var tagNameCells = dojo.query('td.tagNamePlaceHolder');
	for (var i=0; i<tagNameCells.length; i++) {
		tagNameCells[i].innerHTML = ord.oReportData.tag.displayName;
	}
};

ofwat.reportDisplayPage.expandRows = function(groupEntriesByCompany){
	var rows = dojo.query('tr.grouped');
	/**
	 * IMPORTANT!!!!
	 * 
	 * If you change anything here please make sure you visit ReportChunkJsonEntityFactory and make sure that changes made here to adding the 
	 * group ids are also reflected there!!!!
	 */	
	for(var i = 0; i < rows.length; i++){
		var currentRow = rows[i];
		var companyId = ofwat.reportDisplayPage.getCompany(currentRow);
		var companyGroupsEntries = groupEntriesByCompany[companyId];
		for (var j=0; j < companyGroupsEntries.length; j++) {
			var groupEntry = companyGroupsEntries[j];
			if (1 == companyGroupsEntries.length) {
				//ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j);
				ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j, groupEntriesByCompany, companyId);
			}	
			else 
			if (ofwat.reportDisplayPage.lastGroupEntry(companyGroupsEntries, j)) {
				ofwat.reportDisplayPage.removePrimaryAxisText(currentRow);
				ofwat.reportDisplayPage.clearUnwantedHeadings(currentRow);
				ofwat.reportDisplayPage.addGroupDescription(currentRow, groupEntry.description);
				//ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j);
				ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j, groupEntriesByCompany, companyId);
			}
			else {
				var cloneRow = dojo.clone(currentRow);
				ofwat.reportDisplayPage.clearUnwantedHeadings(cloneRow);
				if (!ofwat.reportDisplayPage.firstGroupEntry(j)) {
					ofwat.reportDisplayPage.removePrimaryAxisText(currentRow);
				}
				ofwat.reportDisplayPage.addGroupDescription(currentRow, groupEntry.description);
				//ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j);
				ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupsEntries, j, groupEntriesByCompany, companyId);
				
				dojo.place(cloneRow, currentRow, "after");
				currentRow = cloneRow;
			}
		}
	}
};


/**
 * Takes a <tr> row and removes the text from any <td>s with 
 * the class nonRepeat 
 */
ofwat.reportDisplayPage.clearUnwantedHeadings = function(row){
	dojo.query(".nonRepeat", row).forEach(function(tdNode){
		dojo.empty(tdNode);
		dojo.removeClass(tdNode);
		dojo.addClass(tdNode, "empty");		

	});
};

ofwat.reportDisplayPage.firstGroupEntry = function(j) {
	return j == 0;
};

ofwat.reportDisplayPage.lastGroupEntry = function(companyGroupsEntries, j) {
	return companyGroupsEntries.length -1 == j;
};

ofwat.reportDisplayPage.getCompany = function(row){
	var companyId = null;
	for (var i = 0;  i < row.children.length;  i++)  {
		var td = row.children[i];
		if (dojo.hasClass(td, "input") ||
			dojo.hasClass(td, "calc")) {
			var div = td.children[0];
			companyId = ofwat.keyUtils.getCompanyId(div.id);
			break;
		}
	}
	return companyId;
};

ofwat.reportDisplayPage.removePrimaryAxisText = function(row, groupId){
	for (var i = 0;  i < row.children.length;  i++)  {
		var td = row.children[i];
		if (dojo.hasClass(td, "companyPrimary") ||
			dojo.hasClass(td, "itemPropertiesPrimary")) {
			td.innerHTML = "";
			dojo.removeClass(td, "colHeading");
			dojo.addClass(td, "empty");
		}
	}
};

ofwat.reportDisplayPage.addGroupDescription = function(row, groupDescription){
	for (var i = 0;  i < row.children.length;  i++)  {
		var td = row.children[i];
		if (dojo.hasClass(td, "groupRowHeading")) {
			td.innerHTML = groupDescription;
		}
	}
};


ofwat.reportDisplayPage.deleteReport = function (uri, reportName) {

	if (window.confirm("Are you sure you want to delete " + reportName +" report?")) {
		var xhrArgs = {
				url: uri,
				handleAs: "json",
				preventCache: true,
				load: function(data) {
				ofwat.showMessage("Successfully deleted " + reportName);; 
					location.href = "/Fountain/jsp/protected/customReports.jsp";
				},
				error: function(error, ioargs) {
					var message = "";
					switch(ioargs.xhr.status){
						case 401: 
							message = "You are not authorised to delete this report";
							break;
						case 404: 
							message = "The report was not found"; 
							break; 
						case 500: 
							message = "The server reported an error. " + error; 
							break; 
						case 407: 
							message = "You need to authenticate with a proxy."; 
							break; 
						default: 
							message = "Unknown error."; 
						} 
					ofwat.showError(message);
				}
		};
		ofwat.showMessage("Deleting report " + reportName + "<img border='0' src='../../images/loader.gif'/>");

		//Call the asynchronous xhrDelete
		var deferred = dojo.xhrDelete(xhrArgs);
		
	}
};



ofwat.reportDisplayPage.excelExportReport = function (url) {
	
	var company = ofwat.readCookie("reportCompany");
	if (!reportHasCompany && 
		null != company) {
		url = url + "?companyId=" + company;
	}

	if (!reportHasRun) {
		var run = ofwat.readCookie("reportRun");
		var dataTag = ofwat.readCookie("reportDataTag");
		if (null != run &&
			null != dataTag) {
			if (url.indexOf("?") > -1) {
				url = url + "&runId=" + run + "&tagId=" + dataTag;
			}
			else {
				url = url + "?runId=" + run + "&tagId=" + dataTag;
			}
		}
	}
	
	window.open(url);
};

ofwat.reportDisplayPage.reportDesign = function(uri){
	location.href=uri;
};

ofwat.reportDisplayPage.emailReport = function(reportName) {
	var mailto_link = "mailto:?subject=Fountain Report: " + reportName + "&body="+location.href;
	win = window.open(mailto_link,'emailWindow');
	if (win && win.open &&!win.closed) win.close();
};

ofwat.reportDisplayPage.addGroupIds = function(row, companyGroupsEntries, groupEntryIndex, groupEntriesByCompany, companyId){ //Should be the full list!
	/**
	 * IMPORTANT!!!!
	 * 
	 * If you change anything here please make sure you visit ReportChunkJsonEntityFactory and make sure that changes made here to adding the 
	 * group ids are also reflected there!!!!
	 */
		
	//Called with all parameters:
	//ofwat.reportDisplayPage.addGroupIds(currentRow, companyGroupEntries, j, groupEntriesByCompany, companyId);
	
	for (var i = 0;  i < row.children.length;  i++)  {
		//for each child td cell. 
		var td = row.children[i];		
		if (dojo.hasClass(td, "input") ||
			dojo.hasClass(td, "calc")) {
			var div = td.children[0]; 
			var key = div.id;

			//Get the companyId from the key.
			var companyId = ofwat.keyUtils.getCompanyId(key);
			
			if(groupEntriesByCompany[companyId]){
				dojo.removeClass(div, key);
				var groupEntryId = groupEntriesByCompany[companyId][groupEntryIndex].id;
				key = ofwat.keyUtils.setGroupEntryId(key, groupEntryId);
				dojo.addClass(div, key);
				dojo.attr(div, "id", key);
			}
			else
			if (companyGroupsEntries[groupEntryIndex]) {
				// Only add a group entry if we have one for this company.  
				dojo.removeClass(div, key);
				var groupEntryId = companyGroupsEntries[groupEntryIndex].id; 
				key = ofwat.keyUtils.setGroupEntryId(key, groupEntryId);
				dojo.addClass(div, key);
				dojo.attr(div, "id", key);
			} 
		}
	}
};

ofwat.reportDisplayPage.insertValues = function(){
	
	//Mark the last inserted Data cell. 
	var lastCell = false;
	
	if (!cgSelect) {
		cgSelect = ofwat.editor.dropDownCell(ord.oReportData.cgs);
	}
	if (!codeSelects) {
		codeSelects = new Array();
		for(var i in ord.oReportData.codeListsByCodeList){
			codeSelects[i] = ofwat.editor.dropDownCell(ord.oReportData.codeListsByCodeList[i]);
		}
	}
	
	if (!groupsByCompanies) {
		groupsByCompanies = ord.oReportData.groupEntriesByCompany;
	}
	
	var cellsToFill = dojo.query("td > div");
	for ( var int = 0; int < cellsToFill.length; int++) {
		
		if(int+1 == cellsToFill.length){
			lastCell = true;
		}
		
		var dataCell = cellsToFill[int];
		var identifier = dojo.attr(dataCell, "class");
		identifier = ofwat.keyUtils.getKeyFromString(identifier);

		if (ofwat.keyUtils.isKeyString(identifier)){
			var dto = ord.oReportData.dataList[identifier];
			var idWithGroup = null;
			var processed = false;
			if (!dto) {
				var companyId = ofwat.keyUtils.getCompanyId(identifier);
				var groupEntries = groupsByCompanies[companyId];
				for (var j = 0; j < groupEntries.length; j++) {
					var groupEntry = groupEntries[j];
					if (false == groupEntry.aggregate){
						idWithGroup = ofwat.keyUtils.setGroupEntryId(identifier, groupEntry.id);
						if (!ofwat.keyUtils.isKeyString(idWithGroup)){
							alert("not a key " + idWithGroup);
						}
						dto = ord.oReportData.dataList[idWithGroup];
						//console.log("id = " + idWithGroup);
						if (dto){
							break;
						}
					}
				}
			}
			if (dto){
				var dictionaryFunc = ofwat.reportDisplayPage.populateDictionary;
				var onchInputFunc = "ofwat.editor.postReportUpdate('" + dto.identifier + "', this.value, 'value')";
				var onchCodeListFunc = "ofwat.editor.postReportUpdate('" + dto.identifier + "', this.options[this.selectedIndex].text, 'value')";
				ofwat.editor.valueCellConfig(dataCell, dto, dictionaryFunc, codeSelects, onchInputFunc, onchCodeListFunc, lastCell);
				processed = true;
			}

			// and now the CG
			if (identifier.indexOf("CG",0)!= -1){
				var idWithoutCG = ofwat.keyUtils.removeCG(identifier);
				dto = ord.oReportData.dataList[identifier];
				if (!dto){
					var companyId = ofwat.keyUtils.getCompanyId(identifier);
					var groupEntries = groupsByCompanies[companyId];
					for (var j = 0; j < groupEntries.length; j++) {
						var groupEntry = groupEntries[j];
						if (false == groupEntry.aggregate){					
					
							idWithGroup = ofwat.keyUtils.setGroupEntryId(idWithoutCG, groupEntry.id);
							if (!ofwat.keyUtils.isKeyString(idWithGroup)){
								alert("not a key " + idWithGroup);
							}							
							
							dto = ord.oReportData.dataList[idWithGroup];
							if(dto){
								break;
							}
							
						}
					}
					
				}
				if (dto && !processed){
					var onchCGFunc = "ofwat.editor.postReportUpdate('" + dto.identifier + "', this.options[this.selectedIndex].text, 'cg')";
					var dictionaryFunc = ofwat.reportDisplayPage.populateDictionary;
					ofwat.editor.confidenceGradeCellConfig({dto:dto, dataCell:dataCell, dictionaryFunc:dictionaryFunc, cgSelect:cgSelect, onchCGFunc:onchCGFunc }); //dtoKey=dto.identifier
				} 
			}
		}
	}
	
	
	/*
	//Get the existing table header and clone it. 
	var x = $("<table id='floatingHeader' class='pure-table pure-table-no-border' style='position:fixed; top:0px; display:none;'></table>");
	var z = $(".colHeaderRow").clone();
	x.append(z);
	$("body").append(x);
	//WE should also set the widths to absolute here! otherwise they may not be correct see report
	//i.e iterate over all the cells to be cloned - make a note of their width and then set these on the 
	//new cells - I'm not sure if this is supported across all browsers. 
	
	$("#floatingHeader tr").each(function () {
	    $('td', this).each(function () {
	        if($(this).hasClass('empty')){
	        	$(this).remove()
	        }
	     })
	})
	
	
	var leftPos = $(".lastRowHeaderCell").first().offset().left + $(".lastRowHeaderCell").first().outerWidth(true)
	var topPos = 0;
	
	var maxRight = $($(".pure-table")[1]).outerWidth(true) + $($(".pure-table")[1]).offset().left 
	var maxWidth = maxRight - leftPos;
	
	x.css("left", leftPos + "px");
	x.css("max-width", maxWidth + "px");
	x.css("width", maxWidth + "px");
	
	
	var tableOffset = $($(".pure-table")[1]).offset().top;
	var $fixedHeader = $("#floatingHeader")	
	
	var leftPos = $(".lastRowHeaderCell").first().offset().left + $(".lastRowHeaderCell").first().outerWidth(true)
	var maxRight = $($(".pure-table")[1]).outerWidth(true) + $($(".pure-table")[1]).offset().left 
	var maxWidth = maxRight - leftPos;
	
	x.css("left", leftPos + "px");
	x.css("max-width", maxWidth + "px");
	x.css("width", maxWidth + "px");
    
	var offset = $(this).scrollTop();
    if (offset >= tableOffset && $fixedHeader.is(":hidden")) {
        $fixedHeader.show();
    }
    else if (offset < tableOffset) {
        $fixedHeader.hide();
        
    }	
    */
};


//ofwat.reportDisplayPage.populateDictionary = function(dtoKey, divId, node){
ofwat.reportDisplayPage.populateDictionary = function(event){
	event.stopPropagation();
	var dtoKey = event.data.dtoKey;
	var divId = event.data.divId;
	var node = event.data.node;
	// remove highlight class from any div already highlighted
	dojo.query("td").removeClass("highlight");
	
	// highlight this div
	//dojo.query("."+divId).parent().addClass("highlight");
	//dojo.addClass(cells[i].parentNode.parentNode, "highlight");
	
	var dto = ord.oReportData.dataList[dtoKey];

	var dictionaryHtml = ofwat.dictionary.showDictionary({dto:dto, divId:divId, node:node});
	var showPopover = function(text){
		var infoHtml = text;
		ofwat.dictionary.showAllInfo(infoHtml, divId, node);		
		// highlight this div
		//dojo.query("."+divId).parent().addClass("highlight");	
		$(node).parent().addClass("highlight")
	}
	var auditHtml = ofwat.dictionary.showAudits({divId:divId, dictionaryHtml:dictionaryHtml, callback:showPopover, node:node});
	
};

ofwat.reportDisplayPage.onLoad = function() {
	addRequires();
    Handlebars.registerHelper('inRole', ofwat.checkUserInRole); /*TODO needs to be global i.e everywhere where we use clientside templates.*/
	ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
	ofwat.loadTemplate(dojo.query(".editButtons")[0], ofwat.template.reportEditButtons);
	ofwat.loadTemplate(dojo.query(".footerContent")[0], ofwat.template.footer);
	ofwat.loadTemplate(dojo.query("#bannerTheme")[0], ofwat.template.bannerTheme);
	/*dojo.parser.instantiate(dojo.query(".editButtons")[0]);*/
	dojo.parser.parse();
	/*Populate the time and date*/
	var timeAndDate = dojo.query(".timeAndDate")[0];
	timeAndDate.innerHTML = ofwat.formattedTimeAndDate();
	ofwat._initLoader();
	
	helpPage="Report Page";
	ofwat.wikiPage();
	
	var allContentDiv = dojo.query("#allContent")[0];
	dojo.removeClass(allContentDiv, "invisible");
};

ofwat.reportDisplayPage.selectCompanyDialog = function(reportId) {
	
	var selectCompanyDiv = dojo.create("div");
	selectCompanyDiv.id = "selectCompanyDiv";
	dojo.addClass(selectCompanyDiv, "invisible");
	dojo.addClass(selectCompanyDiv, "white_content");
	
	selectCompanyDiv.innerHTML = "" +
		"<div id='response'></div>" +
		"<div id='companyTypeDiv'>" +
		"<p>Please select from the following to run your report:</p>" +
		"<p class='frontPageSelector'>" +
		"	<label for='companyType'>Company Type</label>" + 
		"	<select id='companyType' onchange='ofwat.reportCompanyRunSelection.filterCompanies();'>" +
		"	<option value='0'>place holder</option>" +
		"</select>" +
		"</p>" +
		"</div>" +
		"<div id='companyDiv'>" +
		"<p class='frontPageSelector'>" +
		"	<label for='company'>Company</label>" + 
		"	<select id='company' onchange='ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
		"		<option value='0'>place holder</option>" +
		"	</select>" +
		"</p>" +
		"</div>" +
		"<div id='agendaDiv'>" +
		"<p class='frontPageSelector'>" +
		"	<label for='agenda'>Data Stream</label>" + 
		"	<select id='agenda' onchange='ofwat.reportCompanyRunSelection.updateRuns();ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
		"	<option value='0'>place holder</option>" +
		"</select>" +
		"</p>" +
		"</div>" +
		"<div id='runDiv'>" +
		"<p class='frontPageSelector'>" +
		"	<label for='run'>Run</label>" + 
		"	<select id='run' onchange='ofwat.reportCompanyRunSelection.loadDataTags(" + reportId + ");'>" +
		"	<option value='0'>place holder</option>" +
		"</select>" +
		"</p>" +
		"</div>" +
		"<div id='dataTagDiv'>" +
		"<p class='frontPageSelector'>" +
		"	<label for='dataTag'>Checkpoint</label>" + 
		"	<select id='dataTag'>" +
		"		<option value='0'>place holder</option>" +
		"	</select>" +
		"</p>" +
		"</div>" +
		"<p class='right'>" +
		"	<button class='goButton btn' id='goButton' onclick='ofwat.reportDisplayPage.populateReportWithData(reportUrl, companyType.options[companyType.selectedIndex].value, company.options[company.selectedIndex].value, agenda.options[agenda.selectedIndex].value, (function(){if(run.options[run.selectedIndex]){return(run.options[run.selectedIndex].value);}else{return(null);}})(), (function(){if(dataTag.options[dataTag.selectedIndex]){return(dataTag.options[dataTag.selectedIndex].value);}else{return(null);}})())'>Go</button>" +
		"</p>";
	

	var allContentDiv = dojo.byId("allContent");
	dojo.place(selectCompanyDiv, allContentDiv, "last");

	ofwat.reportCompanyRunSelection.loadCompanyTypes().then(ofwat.reportCompanyRunSelection.loadCompanies).then(ofwat.reportCompanyRunSelection.loadAgenda).then(ofwat.reportCompanyRunSelection.updateRuns).then(ofwat.reportCompanyRunSelection.loadDataTags(reportId)); 
}

dojo.require("dojo.NodeList-traverse");

ofwat.reportDisplayPage.getReportId = function(){
	var reportId = dojo.queryToObject(dojo.doc.location.search.substr((dojo.doc.location.search[0] === "?" ? 1 : 0))).reportId;
	return reportId;
};

//CHUNKING
ofwat.reportDisplayPage.setupChunkDataFetchListener = function(){
	$(window).bind("resize", function() {
		var $chunkCellLast = $(".chunkCellLast");
		var offset = $(this).scrollTop();
		var chunkCellOffset = $chunkCellLast.offset().top;
		if (offset+400 >= chunkCellOffset){
			//console.log("Get more data!");
			ofwat.reportDisplayPage.getNextChunk();
		}
		
		//if the last .chunkCellLast cell is near the top or off the top then load more data...
		//if $endReport is on screen - then iterate and load all the chunks!
		var $endReport = $(".endReport");
		if($endReport.visible()){
			ofwat.reportDisplayPage.getRemainingChunks()
		}		
		
		
	});
	
	$(window).bind("scroll", function() {
		//if the last .chunkCellLast cell is near the top or off the top then load more data...
		//We need to know if the bottom of the report is on screen - then iterate and load all the chunks!
		var $chunkCellLast = $(".chunkCellLast");
		var offset = $(this).scrollTop();
		var chunkCellOffset = $chunkCellLast.offset().top;
		if (offset+400 >= chunkCellOffset){
			//console.log("Get more data!");
			ofwat.reportDisplayPage.getNextChunk();
		}
		
		var $endReport = $(".endReport");
		if($endReport.visible()){
			ofwat.reportDisplayPage.getRemainingChunks()
		}				
		
	});
};

ofwat.reportDisplayPage.setupScrollingReportHeader = function(){
	//Get the existing table header and clone it. 
	var x = $("<table id='floatingHeader' class='pure-table pure-table-no-border' style='position:fixed; top:0px; display:none;'></table>");
	var z = $(".colHeaderRow")
	x.append(z.clone());
	$("body").append(x);
	//WE should also set the widths to absolute here! otherwise they may not be correct see report
	//i.e iterate over all the cells to be cloned - make a note of their width and then set these on the 
	//new cells - I'm not sure if this is supported across all browsers. 
	var colHeadingWidths = [];
	z.each(function(){
		$('td', this).each(function(){
			var col = $(this);
			if(col.hasClass("colHeading")){
				colHeadingWidths.push(col.width());
			}			
		});
	});
	
	var i = 0;
	$("#floatingHeader tr").each(function () {
		var colHeaderSection = false;
		
		$('td', this).each(function () {
	        if($(this).hasClass('colHeading')){
	        	colHeaderSection = true;
	        	$(this).width(colHeadingWidths[i]);
	        	i++;
	        }
	        if(	false === colHeaderSection &&
	        	$(this).hasClass('empty')){
	        	$(this).remove()
	        }
	    });
	})
	
	
	var leftPos = $(".lastRowHeaderCell").first().offset().left + $(".lastRowHeaderCell").first().outerWidth(true)
	var topPos = 0;
	
	var maxRight = $($(".pure-table")[1]).outerWidth(true) + $($(".pure-table")[1]).offset().left 
	var maxWidth = maxRight - leftPos;
	
	x.css("left", leftPos + "px");
	x.css("max-width", maxWidth + "px");
	x.css("width", maxWidth + "px");
	
	
	var tableOffset = $($(".pure-table")[1]).offset().top;
	var $fixedHeader = $("#floatingHeader")

	$(window).bind("scroll", function() {
		//Added the lines below to recalculate for loading data dynamically
		
		//var leftPos = $(".lastRowHeaderCell").first().offset().left + $(".lastRowHeaderCell").first().outerWidth(true)
		var maxRight = $($(".pure-table")[1]).outerWidth(true) + $($(".pure-table")[1]).offset().left; 
		var maxWidth = maxRight - leftPos;
		x.css("left", (leftPos - $(this).scrollLeft()) + "px");
		x.css("max-width", maxWidth + "px");
		x.css("width", maxWidth + "px");		
		//end
		
		console.log($(this).scrollLeft());
	    var offset = $(this).scrollTop();
	    
	    if (offset >= tableOffset && $fixedHeader.is(":hidden")) {
	        $fixedHeader.show();
	    }
	    else if (offset < tableOffset) {
	        $fixedHeader.hide();
	    }
	});
	
	$(window).bind("resize", function() {
   
		var leftPos = $(".lastRowHeaderCell").first().offset().left + $(".lastRowHeaderCell").first().outerWidth(true)
		var maxRight = $($(".pure-table")[1]).outerWidth(true) + $($(".pure-table")[1]).offset().left 
		var maxWidth = maxRight - leftPos;
		
		x.css("left", (leftPos - $(this).scrollLeft()) + "px");
		x.css("max-width", maxWidth + "px");
		x.css("width", maxWidth + "px");
	    
		var offset = $(this).scrollTop();
	    if (offset >= tableOffset && $fixedHeader.is(":hidden")) {
	        $fixedHeader.show();
	    }
	    else if (offset < tableOffset) {
	        $fixedHeader.hide();
	    }		
	});
	
};
