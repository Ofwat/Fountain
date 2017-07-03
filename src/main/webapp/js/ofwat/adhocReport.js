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
if(!ofwat.listUtils){
	alert("missing import: listUtils.js");
}
if(!ofwat.referenceSelection){
	alert("missing import: referenceSelection.js");
}

if(!ofwat.reportPreview){
	alert("missing import: reportPreview.js");
}


ofwat.adhocReport = {
		reportId : null		
};

/**
 * starts a chain of rest calls to populate initial options
 */
ofwat.adhocReport.loadPanels = function(){
	
	ofwat.referenceSelection.loadCompanyTypes();
	ofwat.referenceSelection.loadCompanies();
	ofwat.referenceSelection.loadModels();
	ofwat.referenceSelection.loadIntervals();
	ofwat.referenceSelection.loadTeamsForUser();
    ofwat.referenceSelection.loadAgenda();
	ofwat.adhocReport.personaliseSaveDialog();
};

ofwat.adhocReport.personaliseSaveDialog = function(){
	// Note on use of number of teams. There will always be a Fountain.Users team (if not the team rest 
	// service would give an error). So if there is only one team it must be Fountain.Users/Universal. 
	if (1 == ofwat.referenceSelection.userTeamArray.length) {
		dojo.byId('publicReportDiv').style.display = "none";
		dojo.byId('teamDiv').style.display = "none";
	}
	else if (2 == ofwat.referenceSelection.userTeamArray.length) {
		dojo.byId('teamDiv').style.display = "none";
	}
	else {
		var teamSelect = dojo.byId("teamSelect");
		if (teamSelect) {
			dojo.empty(teamSelect);
			for (var i=0;i<ofwat.referenceSelection.userTeamArray.length;i++) {
				var team=ofwat.referenceSelection.userTeamArray[i];
				if (team.name != "Universal") {
					dojo.place("<option value='"+team.id+ "'>" + ofwat.escapeHTML(team.name) +"</option>", teamSelect);
				}
			}
		}
	}
};


ofwat.adhocReport.generatePreview = function(){
	ofwat.reportPreview.generatePreview();
};

/**
 * ensure that all the steps have been completed
 */
ofwat.adhocReport.validateSelection = function(){

    var layoutTopList = dojo.byId("layoutTopList");
    var layoutLeftList =  dojo.byId("layoutLeftList");
    var tagChosenList =  dojo.byId("tagChosenList");

	if(dojo.byId("itemChosenList").options.length < 1){
		alert("Please select one or more items");
		return;
	}
	if(dojo.byId("intervalChosenList").options.length < 1){
		alert("Please select one or more years");
		return;
	}
	if(dojo.byId("layoutLeftList").options.length < 1){
		alert("No row layout selected");
		return;
	}
	var layoutTopList = dojo.byId("layoutTopList");
	if(layoutTopList.options.length < 1){
		alert("No column layout selected");
		return;
	}

	if (!dojo.byId("desccb").checked && !dojo.byId("unitcb").checked && !dojo.byId("boncb").checked 
			&& !dojo.byId("modelcb").checked){
		alert("No item heading(s) selected");
		return;
	}
	
	if (!dojo.byId("namecb").checked && !dojo.byId("acronymcb").checked){
			alert("No company heading(s) selected");
			return;
	}
	var availableGroups = dojo.byId("availableGroups");
	if(availableGroups.selectedIndex > -1){
		var selectedGroup = availableGroups[availableGroups.selectedIndex].value;
		if(!(selectedGroup == "NONE")){
		// make sure the left
			var topCompany = false;
			for( var i = 0; i < layoutTopList.options.length; i++){
				if(layoutTopList.options[i].text == "Company"){
					topCompany = true;
				}
			}
			if(topCompany){
				alert("Company must be a left hand axis for group reports");
				return;
			}
		}
	}
	ofwat.adhocReport.showSaveDialog();
	
};

ofwat.adhocReport.showSaveDialog = function(){
	dojo.byId('saveDialog').style.display='block';
	dojo.byId('fade').style.display='block';
	dojo.byId('reportName').focus();
};


ofwat.adhocReport.showAvailableGroups = function(){
	var groupSet = []; // will hold a unique property for each group
	var items = dojo.byId("itemChosenList").options;
	dojo.forEach(items, function(entry, i){
		var split =entry.value.split("#");
		groupSet[split[3]] = true;
	});
	
	var groupSelect = dojo.byId("availableGroups");
	dojo.empty(groupSelect);
	for (value in groupSet) {	
		if ('undefined' != value &&
			'NONE' != value ) {
			dojo.place("<option value='"+value+"'>"+value+"</option>", groupSelect);
		}
	}
	dojo.place("<option value='NONE'>NONE</option>", groupSelect);
};

	
ofwat.adhocReport.updateReport = function () {
	var criteria = ofwat.adhocReport.gatherCriteria();
	if (!criteria){
		return;
	}
	var xhrArgs = {
            url: "../../rest-services/report/" + ofwat.adhocReport.reportId + "/edit",
            postData: dojo.toJson(criteria),
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function(data) {
				location.href="reportDisplay.page?reportId=" + data;	
		    }
        };
	ofwat.showMessage("Updating report <img border='0' src='../../images/loader.gif'/>");
	dojo.xhrPost(xhrArgs);
};

function parseRunTagVal(runTagVal){
    var runIdRegExp = /[0-9]+#/.exec(runTagVal);
    var runId = runIdRegExp[0].substring(0, runIdRegExp[0].length - 1);
    var tagIdRegExp = /#[0-9]+/.exec(runTagVal);
    var tagId = tagIdRegExp[0].substring(1);
    return {runId:runId, tagId:tagId};
}

ofwat.adhocReport.saveReport = function(){
	var criteria = ofwat.adhocReport.gatherCriteria();
	if (criteria == false){
		return;
	}

	var xhrArgs = {
            url: "../../rest-services/report",
            postData: dojo.toJson(criteria),
            handleAs: "json",
            headers: {"Content-Type": "application/json"},
			load: function(data) {
				location.href="reportDisplay.page?reportId=" + data;	
		    },
            error: function(error) {
				ofwat.showMessage("Problem saving report "+ error +")");
			}
        };
	var message = ofwat.showMessage("Saving report <img border='0' src='../../images/loader.gif'/>");
	var teamDiv = dojo.byId('teamDiv');
	var messageDiv = dojo.create("div", {
			innerHTML: "<p style='padding-left: 14px;'></p>"
		}, teamDiv, "after");
	var messageP =  dojo.query("p", messageDiv);
	dojo.place(message, messageP[0], "0");
	dojo.xhrPost(xhrArgs);
};

ofwat.adhocReport.gatherCriteria = function() {
	var criteria = {
			items: [],
			intervals: [],
			companies: [],
           	runTagIds: [],
			layoutLeft: [],
			layoutTop: [],
			name: {},
			description: {},
			publicReport: false,
			displayCGs: false,
			displayDesc: true,
			displayUnit: true,
			displayBoncode: false,
			displayModel: false,
			displayCompanyName: true,
			displayCompanyAcronym: false,
            displayRunName: false,
            displayRunDescription: false,
            displayTagName: false,
			readOnly: false,
			group: "",
			teamId: {}
	};
	
	var items = dojo.byId("itemChosenList").options;
	dojo.forEach(items, function(entry, i){
		var modelItemDto = new Object();
		// itemId#modelId#modelPropertiesMapId
		var split =entry.value.split("#");
		modelItemDto.itemId= split[0];
		modelItemDto.modelId = split[1];
		modelItemDto.modelPropertiesMapId = split[2];
		criteria.items.push(modelItemDto);
	});

    var runTags = dojo.byId("tagChosenList").options
    dojo.forEach(runTags, function(entry, i){
        var runTagIdsDto = new Object();
        // itemId#modelId#modelPropertiesMapId
        var split = entry.value.split("#");
        runTagIdsDto.runId= split[0];
        runTagIdsDto.tagId = split[1];
        criteria.runTagIds.push(runTagIdsDto);
    });


	var intervals = dojo.byId("intervalChosenList").options;
	dojo.forEach(intervals, function(entry, i){
		criteria.intervals.push(entry.value);
	});
	
	var companies = dojo.byId("companyChosenList").options;
	dojo.forEach(companies, function(entry, i){
		criteria.companies.push(entry.value);
	});
	
	var layoutLeft = dojo.byId("layoutLeftList").options;
	dojo.forEach(layoutLeft, function(entry, i){
		criteria.layoutLeft.push(entry.text);
	});
	
	var layoutTop = dojo.byId("layoutTopList").options;
	dojo.forEach(layoutTop, function(entry, i){
		criteria.layoutTop.push(entry.text);
	});
	
	criteria.name = dojo.byId("reportName").value;
	if (!criteria.name){
		alert("No report name specified");
		ofwat.showMessage("No name specified");
		return false;
	}
	
	var reportDescription = dojo.byId("reportDescription");
	if (!reportDescription) {
		criteria.description = '';
	} 
	else {
		criteria.description = dojo.byId("reportDescription").value;
		if (!criteria.description){
			criteria.description = '';
			//alert("No report description specified");
			//ofwat.showMessage("No description specified");
			//return false;
		}
	}
	
	
	var availableGroups = dojo.byId("availableGroups");
	if(-1 < availableGroups.selectedIndex){
		criteria.group = availableGroups[availableGroups.selectedIndex].value;
	}
	criteria.publicReport = dojo.byId("publicReport").checked;
	criteria.readOnly = dojo.byId("readOnly").checked;

	var teamSelect = dojo.byId("teamSelect");
	if(-1 < teamSelect.selectedIndex){
		criteria.teamId = teamSelect[teamSelect.selectedIndex].value;
	}
	else {
		// Note on use of number of teams. There will always be a Fountain.Users team (if not the team rest 
		// service would give an error). So if there is only one team it must be Fountain.Users/Universal. 
		if (1 == ofwat.referenceSelection.userTeamArray.length &&
			"Universal" == ofwat.referenceSelection.userTeamArray[0].name) {
			criteria.teamId = ofwat.referenceSelection.userTeamArray[0].id;
		}
		else if (2 == ofwat.referenceSelection.userTeamArray.length) {
			for (var i=0; i < ofwat.referenceSelection.userTeamArray.length; i++) {
				if (ofwat.referenceSelection.userTeamArray[i].name != "Universal") {
					criteria.teamId = ofwat.referenceSelection.userTeamArray[i].id;
				}
			}
		}
	}
	
	criteria.displayCGs = dojo.byId("cgcb").checked;
	criteria.displayDesc = dojo.byId("desccb").checked;
	criteria.displayUnit = dojo.byId("unitcb").checked;
	criteria.displayBoncode = dojo.byId("boncb").checked;
	criteria.displayCompanyName = dojo.byId("namecb").checked;
	criteria.displayCompanyAcronym = dojo.byId("acronymcb").checked;
	criteria.displayModel = dojo.byId("modelcb").checked;
    criteria.displayAgendaName = dojo.byId("agendaNamecb").checked;
    criteria.displayAgendaCode = dojo.byId("agendaCodecb").checked;
    criteria.displayRunName = dojo.byId("runNamecb").checked;
    criteria.displayRunDescription = dojo.byId("runDescriptioncb").checked;
    criteria.displayTagName = dojo.byId("tagNamecb").checked;

	return criteria;
};

ofwat.adhocReport.cancelSave = function() {
	dojo.byId('saveDialog').style.display='none';
	dojo.byId('fade').style.display='none';
};


ofwat.adhocReport.initialise = function(showLoadScreen, hideLoadScreen){
	
	// add event listeners
	//showLoadScreen(dojo.byId('progtabwrapper'));

	ofwat.showMessage("Loading options <img border='0' src='../../images/loader.gif'/>");
	// setup page
	ofwat.adhocReport.loadPanels();
	ofwat.adhocReport.reportId = ofwat.getQueryVariable("reportId");
	
	var pageId = ofwat.getQueryVariable("pageId");
	
	ofwat.showMessage("Loading options <img border='0' src='../../images/loader.gif'/>");
	if (ofwat.adhocReport.reportId) { 
		ofwat.adhocReport.getOptions(ofwat.adhocReport.reportId);
	} else if (pageId){
		ofwat.adhocReport.quickStart(pageId);
	}
	//hideLoadScreen(dojo.byId('progtabwrapper'));

	
};

ofwat.adhocReport.getOptions = function(reportId) {
	// retrieve the values - including whether this report is owned by the current user
	var xhrArgs = {
			url: "../../rest-services/report/"+reportId+"/edit",
			handleAs: "json",
			preventCache: true,
			sync: true,
			headers: {"accept": "application/json"},
			load: function(data){
				ofwat.adhocReport.preselectOptions(data);
				ofwat.showMessage("");
			},
			error: function(error) {
				ofwat.showMessage("Problem loading report criteria "+ error +")");
			}
	};
	ofwat.showMessage("Loading report criteria <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.adhocReport.preselectOptions = function (criteria){
	// we receive a EditReportDto
	// if the report is owned by the current user, allow them to amend the report or Save As
	// otherwise Save As only.
	
	if (criteria.myReport){
		dojo.byId("reportName").value = criteria.name;
		if(criteria.description){
			dojo.byId("reportDescription").value = criteria.description;				
		}
		// save button has to do an update rather than usual save
		dijit.byId('saveButton').attr('onClick', ofwat.adhocReport.updateReport); 
	} else {
		// hide the original save button
		var save = dojo.byId("saveButtonSpan");
		dojo.removeClass(save, "displayButton");
		dojo.addClass(save, "hiddenButton");
	}
	
	// show save as button
	var saveAs = dojo.byId("saveAsButtonSpan");
	dojo.removeClass(saveAs, "hiddenButton");
	dojo.addClass(saveAs, "displayButton");


	//Set the read only chkbox in Save dialog. 
	dojo.byId("readOnly").checked = criteria.readOnly;
	/*
	if(criteria.reportCreator){
		//Allow to mark/unmark readOnly - Do nothing!
		$("#readOnlyMessage").hide();
	}else if(criteria.admin){
		//Allow to mark/unmark readOnly - Do nothing!
		$("#readOnlyMessage").hide();
	}
	else{
	*/
		//Mark it according to the status.
		if(criteria.readOnly && !criteria.admin){
			var save = dojo.byId("saveButtonSpan");
			//dojo.removeClass(save, "displayButton");
			//dojo.addClass(save, "hiddenButton");
			save.disabled = "disabled";
			//Make the readonly checkbox disabled. 
			//dojo.byId("readOnly").disabled = "disabled";
			dojo.byId("readOnly").checked = false; 
			$("#readOnlyMessage").show();
			$("#readOnlyMessage").removeClass('hidden');
		}
		
		if(criteria.readOnly && criteria.admin){
			$("#readOnlyMessageAdmin").show();
			$("#readOnlyMessageAdmin").removeClass('hidden');
		}
		
 	//}
	$.each($(".reportOwner"), function(i, item){
		$(item).html(criteria.reportCreatorName);
	});
	

	// pipe the values into the relevant parts of the page
	var items = criteria.items;
	var itemList = dojo.byId("itemChosenList");
	dojo.empty(itemList);
	// TODO criteria.group only contains the group selected for the report. What we need here is the group that the item belongs to.
	// This is so that the group selection box will contain all groups that the user may want.
	dojo.forEach(items, function(item, i){
		// get group part
		var group="";
		if (criteria.group){
			group = "#" + criteria.group;
		} 
 		dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + group + "' " +
 				" title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode + "   " + item.description) + "'>" +
 				ofwat.escapeHTML(item.itemCode + " [" + item.modelCode + "   " + item.description + "] ") + "</option>", itemList);
	});
	
	var intervalList = dojo.byId("intervalChosenList");
	dojo.empty(intervalList);
	dojo.forEach(criteria.intervals, function(interval, i){
		// find the option on the selector list, then add it to the selected list
		var option = ofwat.referenceSelection.allIntervalsArray[interval]; 
		dojo.place("<option value='"+interval+"'>"+option.name+"</option>", intervalList);
		
	});

	var companyList = dojo.byId("companyChosenList");
	dojo.empty(companyList);
	dojo.forEach(criteria.companies, function(company, i){
		var option = ofwat.referenceSelection.allCompaniesArray[company]; 
		dojo.place("<option value='"+company+ "'>" + option.name +"</option>", companyList);
	});
	
	var layoutLeft = dojo.byId("layoutLeftList");
	dojo.empty(layoutLeft);
	dojo.forEach(criteria.layoutLeft, function(left, i){
		dojo.place("<option value='0'>" + ofwat.escapeHTML(left) + "</option>", layoutLeft);
	});
	
	var layoutTop = dojo.byId("layoutTopList");
	dojo.empty(layoutTop);
	dojo.forEach(criteria.layoutTop, function(top, i){
		dojo.place("<option value='1'>" + ofwat.escapeHTML(top) + "</option>", layoutTop);
	});

    //We will get a list of runTagDtos.
    var runTags = criteria.runTagIds
    var tagList = dojo.byId("tagChosenList");
    dojo.empty(tagList);
    dojo.forEach(runTags, function(runTag, i){
    	if(runTag.runId != 0){
    		dojo.place("<option value='" + runTag.runId + "#"+ runTag.tagId +"' " +
    				" title=''>" +
    				ofwat.escapeHTML(runTag.tagName + " [" + runTag.runName + "]") + "</option>", tagList);
    	}
    });


	dojo.byId("publicReport").checked = criteria.publicReport; 
	
	// set up the display options
	dijit.byId("desccb").setValue(criteria.displayDesc);
	dijit.byId("unitcb").setValue(criteria.displayUnit);
	dijit.byId("boncb").setValue(criteria.displayBoncode);
	dijit.byId("modelcb").setValue(criteria.displayModel);
	dijit.byId("cgcb").setValue(criteria.displayCGs);
	dijit.byId("namecb").setValue(criteria.displayCompanyName);
	dijit.byId("acronymcb").setValue(criteria.displayCompanyAcronym);
	dijit.byId("agendaNamecb").setValue(criteria.displayAgendaName);
	dijit.byId("agendaCodecb").setValue(criteria.displayAgendaCode);
    dijit.byId("runNamecb").setValue(criteria.displayRunName);
    dijit.byId("runDescriptioncb").setValue(criteria.displayRunDescription);
    dijit.byId("tagNamecb").setValue(criteria.displayTagName);

	for (var i=0; i<teamSelect.options.length; i++) {
		if (teamSelect.options[i].value == criteria.teamId) {
			teamSelect.options[i].selected = true;
			break;
		}
	}

	if (criteria.group){
		// TODO FIX !!!! set up the groups - 	<select id="availableGroups">
		ofwat.adhocReport.showAvailableGroups();
		// select the correct group
		var select = dojo.byId("availableGroups");
		for ( var option in select.options) {
			if (option.value == criteria.group) {
				option.selected;
				break;
			}
		}
	}
};

ofwat.adhocReport.quickStart = function(pageId) {
	// get the items on the page, same as if they'd chosen the page on a drop down list, but into the chosen list side
	// years, items, 
	var xhrArgs = {
			url: "../../rest-services/table/"+ pageId+"/structure",
			handleAs: "json",
			headers: {
		"accept": "application/json"
	},
	load: function(data){
		ofwat.showMessage("Loading report criteria <img border='0' src='../../images/loader.gif'/>");
		ofwat.adhocReport.pageItemsSelected(data);
	},
	error: function(error) {
		ofwat.showError("Could not load criteria from table", error);}

	}
	ofwat.showMessage("Loading report criteria <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);

};


ofwat.adhocReport.pageItemsSelected = function(data) {
	var table = data;
	ofwat.referenceSelection.itemArray = table.modelItemDtos;
	var elChosenList = dojo.byId("itemChosenList");
	dojo.empty("itemChosenList");

	dojo.byId("reportName").value = table.name;
	if (table.description) {
		dojo.byId("reportDescription").value = table.description;
	}
	
	var tableName = table.name.split(":")[1];
	tableName = dojo.trim(tableName);
	var previousItem;

	for (var i=0;i<ofwat.referenceSelection.itemArray.length;i++)
    {
		var item = ofwat.referenceSelection.itemArray[i];
		item.itemEquals = ofwat.referenceSelection.itemEquals;
		if (!item.itemEquals(previousItem)) {
			var titleText;
			if (item.lineNumber == 0) {
				titleText = "title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode) + "   Table " + tableName + "   " + ofwat.escapeHTML(item.description) + "'>"; 
				dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + "#" + item.group + "' " +
						titleText +
						ofwat.escapeHTML(item.itemCode + " [ M:" + item.modelCode + " T:" + tableName + " ] " + item.description) + "</option>", elChosenList);
			}
			else {
				titleText = "title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode + "   Table " + tableName + "   Line " + item.lineNumber + "   " + item.description) + "'>";
				dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + "#" + item.group + "' " +
						titleText +
						ofwat.escapeHTML(item.itemCode + " [ M:" + item.modelCode + " T:" + tableName + " L:" + item.lineNumber + " ] " + item.description) + "</option>", elChosenList);
			}
		}
		previousItem = item;
    }
	
	// get the intervals next
	ofwat.showMessage("Loading years <img border='0' src='../../images/loader.gif'/>");
	var intervals = table.intervalDtos;
	var elIntervalList = dojo.byId("intervalChosenList");
	dojo.empty("intervalChosenList");
	
	intervals.sort(function(a, b){
		 return a.index-b.index
		});
	
	for (var i=0; i<intervals.length;i++) {
		var interval = intervals[i];
		dojo.place("<option value=\""+interval.id+"\">"+interval.name+"</option>", elIntervalList);
	}
	
	// swap to company tab
	var tabContainer = dijit.byId('tabContainer');
	var companyTab = dijit.byId('companyTab');
	tabContainer.selectChild(companyTab);
	companyTab.selected = true;
	ofwat.showMessage("");
};


