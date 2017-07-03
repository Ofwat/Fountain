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

ofwat.referenceSelection = {
		intervalArray: {},
		companyArray: {},
		itemArray: {},
		teamArray: {},
		userTeamArray: {},
		allIntervalsArray: {},
		allCompaniesArray: {},
        allRunsArray: {},
        allTagsArray: {},
        //runTagArray: {},
        allRunTagsArray: {},
        allAgendaArray: {}
};

ofwat.referenceSelection.clearLists = function(){
	if(dojo.byId("itemStartList")){
		dojo.empty("itemStartList");
	}
	if(dojo.byId("itemChosenList")){
		dojo.empty("itemChosenList");
	}
	if(dojo.byId("companyStartList")){
		dojo.empty("companyStartList");
	}
	if(dojo.byId("companyChosenList")){
		dojo.empty("companyChosenList");
	}
	if(dojo.byId("intervalStartList")){
		dojo.empty("intervalStartList");
	}
	if(dojo.byId("intervalChosenList")){
		dojo.empty("intervalChosenList");
	}
	if(dojo.byId("itemSearchTerm")){
		dojo.empty("itemSearchTerm");
	}
	if(dojo.byId("groupEntryStartList")){
		dojo.empty("groupEntryStartList");
	}
	if(dojo.byId("groupEntryChosenList")){
        	dojo.empty("groupEntryChosenList");
	}
	if(dojo.byId("tagStartList")){
	    	dojo.empty("tagStartList");
	}
};

ofwat.referenceSelection.clearOptions = function(){
	if(dojo.byId("itemChosenList")){
		dojo.empty("itemChosenList");
	}
	if(dojo.byId("companyChosenList")){
		dojo.empty("companyChosenList");
	}
	if(dojo.byId("intervalChosenList")){
		dojo.empty("intervalChosenList");
	}
	if(dojo.byId("itemSearchTerm")){
		dojo.empty("itemSearchTerm");
	}
    if(dojo.byId("tagChosenList")){
        dojo.empty("tagChosenList");
    }
};



ofwat.referenceSelection.disableSearch = function() {
	var node = dojo.byId('searchCriteria');
	if(node){
		node.innerHTML = "";
	}
	node = dojo.byId("itemSearchTerm");
	if(node){
		node.disabled = false;
	}
	node = dojo.byId("itemSearchTerm");
	if(node){
		node.disabled = false;
	}
	node = dojo.byId("search");
	if(node){
		node.disabled = false;
	}
	node = dojo.byId("searchDef");
	if(node){
		node.disabled = false;
	}
	node = dojo.byId("itemStartList");
	if(node){
		dojo.empty(node);
	}
};

ofwat.referenceSelection.enableSearch = function() {
	
	var node = dojo.byId('searchCriteria');
	if(node){
		node.innerHTML = "";
	}
	node = dojo.byId("itemSearchTerm");
	if(node){
		node.value="";
	}
	node = dojo.byId("itemSearchTerm");
	if(node){
		node.disabled = true;
	}
	node = dojo.byId("search");
	if(node){
		node.disabled = true;
	}
	node = dojo.byId("searchDef");
	if(node){
		node.disabled = true;
	}
	node = dojo.byId("itemStartList");
	if(node){
		dojo.empty(node);
	}
};

ofwat.referenceSelection.updateRuns = function(){
    var elSelector = dojo.byId("agendaList");
    var selectedAgendaId = elSelector.options[elSelector.selectedIndex].value;
    ofwat.referenceSelection.loadRuns(selectedAgendaId);
}

/**
 * Updates the tag display showing only tags form the chosen run.
 */
ofwat.referenceSelection.updateTags = function(){
    var elSelector = dojo.byId("runList");
    var selectedRunId = elSelector.options[elSelector.selectedIndex].value;
    ofwat.referenceSelection.loadTags(selectedRunId);
}

/**
 * filters the available companies by selected company type
 */
ofwat.referenceSelection.filterCompanies = function(){
	var elSelector = dojo.byId("companyTypes"); 
	var selectedTypeId = elSelector.options[elSelector.selectedIndex].value;
	var historic = (selectedTypeId == 'exp');
	var elCompanyList = dojo.byId("companyStartList");
	dojo.empty("companyStartList");
	for (i=0;i<ofwat.referenceSelection.companyArray.length;i++)
	{
		var company=ofwat.referenceSelection.companyArray[i];
	
		if((0 == selectedTypeId && !company.expired) || 
		   ((company.companyType.id == selectedTypeId) && 
		    !company.expired) || 
		   (historic && company.expired)){
			dojo.place("<option value='"+company.id+ "'>" + company.name + " ["+ofwat.escapeHTML(company.code)+"]</option>", elCompanyList);
		}
	}
	
};


/**
 * executes an item search based on the value in the itemSearchTerm textbox.
 * filters by the model currently showing in modelSelect
 * 
 * set extended to true for a definition search or false for a code and description only search
 * 
 * populates itemStartList with the return values 
 */
ofwat.referenceSelection.itemSearch = function (extended){
		var searchText = dojo.byId("itemSearchTerm").value;	
		var elTypes = dojo.byId("modelSelect");
		var typeIndex = elTypes[elTypes.selectedIndex].value;		
		var filterOptions="&filters=" + typeIndex;
		var restUrl = "";
		if(extended){
			// definition search
			restUrl = "../../rest-services/item?criteria=" + searchText + "&extended=true"  + filterOptions;
		}
		else{
			//quick search
			restUrl = "../../rest-services/item?criteria=" + searchText +  filterOptions;
		}
		
		 var xhrArgs = {
			        url: restUrl,
			        handleAs: "json",
			        headers: {
			            "accept": "application/json"
			        },
			        load: function(data){
			        	  ofwat.referenceSelection.populateItemSearch(data);
			        	  ofwat.showMessage("");
			    	},
			    	error: function(error) {
			    		ofwat.showError("itemSearch errored", error);
					}
			    	
			    };
		 ofwat.showMessage("Searching... <img border='0' src='../../images/loader.gif'/>");
		 var deferred = dojo.xhrGet(xhrArgs);
		 return deferred;
};


ofwat.referenceSelection.itemEquals = function(otherItem) {
	if (undefined  == otherItem) {
		return false;
	}
	if ((this.itemCode == otherItem.itemCode) &&
		(this.itemId == otherItem.itemId) &&
		(this.modelCode == otherItem.modelCode) &&
		(this.modelId == otherItem.modelId) &&
		(this.modelName ==	otherItem.modelName) &&
		(this.modelPropertiesMapId == otherItem.modelPropertiesMapId)) {
		return true;
	}
	return false;	
};

ofwat.referenceSelection.loadTags = function(runId){
    //alert("Loading Tags");
    //get the run from the selected run.
    var xhrArgs = {
        url: "../../rest-services/tags/run?companyId=0&reportId=0&runId=" + runId,
        handleAs: "json",
        sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function (data) {
            ofwat.referenceSelection.populateTags(data, runId);
            ofwat.showMessage("");
        },
        error: function (error) {
            ofwat.showError("load checkpoints errored", error);
        }

    };
    ofwat.showMessage("Loading checkpoints... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
}

ofwat.referenceSelection.loadAgenda = function() {
    //alert("Loading Agenda");
    var xhrArgs = {
        url: "../../rest-services/agenda",
        handleAs: "json",
        preventCache: true,
        sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function(data){
            ofwat.referenceSelection.populateAgenda(data);
            ofwat.showMessage("");
        },
        error: function(error) {
            ofwat.showError("loadAgenda errored", error);
        }
    };
    ofwat.showMessage("Loading agenda... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
}

ofwat.referenceSelection.populateAgenda = function(data) {
    //alert("populating Agenda");
    var agendaArray = data;
    var elAgendaSelector = dojo.byId("agendaList");
    dojo.empty("agendaList");
    for (i=0;i<agendaArray.length;i++)
    {
        var agenda = agendaArray[i];
        dojo.place("<option value='"+agenda.id+ "'> " + ofwat.escapeHTML(agenda.name) + "</option>", elAgendaSelector);
        ofwat.referenceSelection.allAgendaArray[agenda.id] = agenda;
    }
    var selectedAgendaId = elAgendaSelector.options[elAgendaSelector.selectedIndex].value;
    ofwat.referenceSelection.updateRuns();
}

ofwat.referenceSelection.loadRuns = function(agendaId) {
    //alert("Loading Runs");
    var xhrArgs = {
        url: "../../rest-services/runs?agendaId=" + agendaId,
        handleAs: "json",
        preventCache: true,
        sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function(data){
            ofwat.referenceSelection.populateRuns(data);
            ofwat.showMessage("");
        },
        error: function(error) {
            ofwat.showError("loadRuns errored", error);
        }
    };
    ofwat.showMessage("Loading company types... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
}

ofwat.referenceSelection.populateRuns = function(data) {
    //alert("populating Runs");
    var runsArray = data;
    var elRunSelector = dojo.byId("runList");
    dojo.empty("runList");
    for (i=0;i<runsArray.length;i++)
    {
        var run = runsArray[i];
        dojo.place("<option value='"+run.id+ "'> " + ofwat.escapeHTML(run.name) + "</option>", elRunSelector);
        ofwat.referenceSelection.allRunsArray[run.id] = run;
    }
    var selectedRunId = elRunSelector.options[elRunSelector.selectedIndex].value;
    ofwat.referenceSelection.loadTags(selectedRunId);
}

ofwat.referenceSelection.populateTags = function(data, runId) {
    //alert("populating Tags");
    var tagsArray = data;
    var elTagSelector = dojo.byId("tagStartList");
    dojo.empty("tagStartList");
    
    //dojo.place("<option value='0'>Latest</option>", elTagSelector);
    var latestTag = {id:0, displayName:"Latest"};
    tagsArray.unshift(latestTag);

    var runName = ofwat.listUtils.ListGetNodeText("runList", runId);
    for (i=0;i<tagsArray.length;i++)
    {
        var tag = tagsArray[i];
        var selected = '';
        if(i==0){
            selected = "selected";
        }
        dojo.place("<option value='" + runId + "#"+ tag.id + "' "+ selected +"> " + ofwat.escapeHTML(tag.displayName) + " [" + runName + "]</option>", elTagSelector);
        ofwat.referenceSelection.allTagsArray[tag.id] = tag;
    }
    var selectedTagId = elTagSelector.options[elTagSelector.selectedIndex].value;
}

ofwat.referenceSelection.loadCompanies = function() {

    var xhrArgs = {
        url: "../../rest-services/company/all",
        handleAs: "json",
		sync: true,
        headers: {
            "accept": "application/json"
        },
        load: function(data){
    		ofwat.referenceSelection.populateCompanies(data);
    		ofwat.showMessage("");
    	},
    	error: function(error) {
    		ofwat.showError("loadCompanies errored", error);
		}
    	
    };
	ofwat.showMessage("Loading companies... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
    return deferred;
};


ofwat.referenceSelection.loadCompanyTypes = function() {
	
	var xhrArgs = {
	        url: "../../rest-services/company/types",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.referenceSelection.populateCompanyTypes(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadCompanyTypes errored", error);
			}
	    };
    	ofwat.showMessage("Loading company types... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};



ofwat.referenceSelection.loadIntervals = function(){
	
	var xhrArgs = {
	        url: "../../rest-services/interval",
	        handleAs: "json",
			sync: true,
	        headers: {
	            "accept": "application/json"
	        },
	        load: function(data){
	        	ofwat.referenceSelection.populateAllIntervals(data);
	    		ofwat.referenceSelection.populateIntervalTypes(data);
	    		ofwat.showMessage("");
	    	},
	    	error: function(error) {
	            ofwat.showError("loadIntervals errored", error);
			}
	    	
	    };
		ofwat.showMessage("Loading years... <img border='0' src='../../images/loader.gif'/>");
	    //Call the asynchronous xhrGet
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};

/**
 * Rest call to get models.
 */
ofwat.referenceSelection.loadModels = function() {
	 var xhrArgs = {
		        url: "../../rest-services/model?typeId=0",
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		    		ofwat.referenceSelection.populateModels(data);
		    		ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadModels errored", error);
				}
		    	
		    };
		 	ofwat.showMessage("Loading models... <img border='0' src='../../images/loader.gif'/>");
		    var deferred = dojo.xhrGet(xhrArgs);
		    return deferred;
	
};

/**
 * Rest call to get items for a page.
 */
ofwat.referenceSelection.loadPageItems = function() {
	var elPageSelect = dojo.byId("pageSelect");
	var selectedValue = elPageSelect[elPageSelect.selectedIndex].value;
	if ("Any" == selectedValue) {
		ofwat.referenceSelection.disableSearch();
	}
	else {
		ofwat.referenceSelection.enableSearch();
		 var xhrArgs = {
			        url: selectedValue,
			        handleAs: "json",
			        headers: {
			            "accept": "application/json"
			        },
			        load: function(data){
			        	ofwat.referenceSelection.populatePageItems(data);
			        	ofwat.showMessage("");
			    	},
			    	error: function(error) {
			            ofwat.showError("loadPagesItems errored", error);}
			    	
		 };
		 ofwat.showMessage("Loading items... <img border='0' src='../../images/loader.gif'/>");
		 var deferred = dojo.xhrGet(xhrArgs);
		 return deferred;
	}	
};


/**
 * Rest call to get pages.
 */
ofwat.referenceSelection.loadPages = function() {
	ofwat.referenceSelection.disableSearch();
	dojo.attr(dojo.byId("pageSelect"), "disabled", true);
	var elModelSelect = dojo.byId("modelSelect");
	var modelId = elModelSelect[elModelSelect.selectedIndex].value; 
	 var xhrArgs = {
		        url: "../../rest-services/model/" + modelId + "/table/structure",
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.referenceSelection.populatePages(data);
		        	ofwat.showMessage("");
		        	dojo.attr(dojo.byId("pageSelect"), "disabled", false);
		    	},
		    	error: function(error) {
		            ofwat.showError("loadPages errored", error);
		        	dojo.attr(dojo.byId("pageSelect"), "disabled", false);
				}
		    	
	 };
	 ofwat.showMessage("Loading pages... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
	 return deferred;
};


/**
 * Rest call to get teams.
 */
ofwat.referenceSelection.loadTeams = function() {
	 var xhrArgs = {
		        url: "../../rest-services/team",
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.referenceSelection.populateTeams(data);
		        	ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadTeams", error);
				}
		    	
	 };
	 ofwat.showMessage("Loading teams... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
	 return deferred;
};

/**
 * Rest call to get teams for a user.
 */
ofwat.referenceSelection.loadTeamsForUser = function() {
	 var xhrArgs = {
		        url: "../../rest-services/team/user",
		        handleAs: "json",
	            sync: true,
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.referenceSelection.populateTeamsForUser(data);
		        	ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadTeamsForUser", error);
				}
		    	
	 };
	 ofwat.showMessage("Loading teams... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
	 return deferred;
};

ofwat.referenceSelection.loadGroups = function(){
	 var xhrArgs = {
		        url: "../../rest-services/group",
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.referenceSelection.populateGroups(data);
		        	ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadGroups", error);
				}
		    	
	 };
	 ofwat.showMessage("Loading groups... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
	 return deferred;
};

ofwat.referenceSelection.loadCompanyGroups = function(){
	
	var elCompany = dojo.byId("companyStartList");
	var companyId = elCompany[elCompany.selectedIndex].value;		
	var elGroup = dojo.byId("groupSelect");
	var groupId = elGroup[elGroup.selectedIndex].value;		
	 var xhrArgs = {
		        url: "../../rest-services/group/" + groupId + "/company/" + companyId,
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.referenceSelection.populateGroupEntries(data);
		        	ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadCompanyGroups", error);
				}
		    	
	 };
	 ofwat.showMessage("Loading group entries... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
	 return deferred;
	
};

ofwat.referenceSelection.populateGroups = function(data) {
	
	var elStartList = dojo.byId("groupSelect");
	dojo.empty("groupSelect");
	for (i=0;i<data.length;i++)
	{
		var group=data[i];
		dojo.place("<option value='"+group.id+ "'>" + ofwat.escapeHTML(group.name) + "</option>", elStartList);
	}	
	
	
};

ofwat.referenceSelection.populateGroupEntries = function(data) {
	
	var elStartList = dojo.byId("groupEntryStartList");
	dojo.empty("groupEntryStartList");
	for (i=0;i<data.length;i++)
	{
		var groupEntry=data[i];
		dojo.place("<option value='"+groupEntry.id+ "'>" + ofwat.escapeHTML(groupEntry.description) + "</option>", elStartList);
	}	
	
	
};



/**
* handler function for loadCompanies
*/
ofwat.referenceSelection.populateCompanies = function(data) {
	ofwat.referenceSelection.companyArray = data;
	var elStartList = dojo.byId("companyStartList");
	dojo.empty("companyStartList");
	for (i=0;i<ofwat.referenceSelection.companyArray.length;i++)
	{
		var company=ofwat.referenceSelection.companyArray[i];
		if (!company.expired){
			dojo.place("<option value='"+company.id+ "'>" + ofwat.escapeHTML(company.name) + " ["+ofwat.escapeHTML(company.code)+"]</option>", elStartList);
		}
		// also add to local cache
		ofwat.referenceSelection.allCompaniesArray[company.id] = company;
	}
	
};

/**
 * handler function for loadCompanyTypes
 */
ofwat.referenceSelection.populateCompanyTypes = function(data) {
	var companyTypeArray = data;
	var elCompanyTypeSelector = dojo.byId("companyTypes");
	dojo.empty("companyTypes");
	dojo.place("<option value='0'>All Current</option>", elCompanyTypeSelector);
	
	for (i=0;i<companyTypeArray.length;i++)
	{
		var companyType = companyTypeArray[i];
		dojo.place("<option value='"+companyType.id+ "'>All Current " + ofwat.escapeHTML(companyType.code) + "</option>", elCompanyTypeSelector);
	}	
	dojo.place("<option value='exp'>Historic</option>", elCompanyTypeSelector);
	
//	companyTypeIdx = (ofwat.getQueryVariable('companyTypeIdx')!=null)?ofwat.getQueryVariable('companyTypeIdx'):0;
//	elCompanyTypeSelector.selectedIndex=companyTypeIdx;
};



/**
 * populates the interval selection list based on the selected interval type
 */
ofwat.referenceSelection.populateIntervals = function() {
	var elTypes = dojo.byId("intervalTypes");
	var typeIndex = elTypes[elTypes.selectedIndex].value;
	var intervals = ofwat.referenceSelection.intervalArray[typeIndex].intervalDtos;
	var elIntervals = dojo.byId("intervalStartList");
	// remove current options
	dojo.empty("intervalStartList");
	for (var i=0; i<intervals.length; i++){
		var interval=intervals[i];
		dojo.place("<option value='" +interval.id+ "'>" + ofwat.escapeHTML(interval.name) + "</option>", elIntervals);
	}

};

/**
 * handler function for loadIntervals
 */
ofwat.referenceSelection.populateIntervalTypes = function(data){
	
	ofwat.referenceSelection.intervalArray = data;

	var elTypeSelect = dojo.byId("intervalTypes");
	// populate the interval type selection
	for (var i=0;i<ofwat.referenceSelection.intervalArray.length;i++)
	{
		var intervals = ofwat.referenceSelection.intervalArray[i].intervalDtos;
		dojo.place("<option value='" + i + "'>" + ofwat.escapeHTML(ofwat.referenceSelection.intervalArray[i].name) + "</option>", elTypeSelect);
	}
	ofwat.referenceSelection.populateIntervals();
	
};

/**
 * handler function for loadIntervals to load all intervals into a local variable 
 */
ofwat.referenceSelection.populateAllIntervals = function(data){
	
	for (var i=0;i<data.length;i++)
	{
		var intervals = data[i].intervalDtos;
		for (var j=0; j<intervals.length; j++) {
			var interval = intervals[j];
			ofwat.referenceSelection.allIntervalsArray[interval.id] = interval;
		}
	}
};

/**
 * handler function for the itemSearch function
 */
ofwat.referenceSelection.populateItemSearch = function (data){

	var itemArray = data;
	var elStartList = dojo.byId("itemStartList");
	dojo.empty("itemStartList");
	for (i=0;i<itemArray.length;i++)
    {
		var item=itemArray[i];
		dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + "#" + item.group + "'" +
				" title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode + "   " + item.description) + "'>" +
				ofwat.escapeHTML(item.itemCode + " [" + item.modelCode + "] " + item.description) + "</option>", elStartList);
    }
	
	var searchCriteria="Search results for <b>"+ofwat.escapeHTML(dojo.byId("itemSearchTerm").value)+"</b>";
	dojo.byId('searchCriteria').innerHTML=searchCriteria;
};

/**
 * handler function for loadModels
 */
ofwat.referenceSelection.populateModels = function(data) {
	
	var modelArray = data;
	var elModelSelect = dojo.byId("modelSelect");
	for (var i=0; i<modelArray.length; i++)
	{
		var model = modelArray[i];
		if (model.displayOrder > -1) {
			dojo.place("<option value='" + model.id + "'>" + ofwat.escapeHTML(model.name) + "</option>", elModelSelect);
		}
	}
	ofwat.referenceSelection.loadPages();
};

/**
 * Handler for loadPageItems method. It populates the item list with items from a page.
 */
ofwat.referenceSelection.populatePageItems = function(data) {

	var table = data;
	ofwat.referenceSelection.itemArray = table.modelItemDtos;
	var elStartList = dojo.byId("itemStartList");
	dojo.empty("itemStartList");

	var elPageSelect = dojo.byId("pageSelect");
	var selectedPageValue = elPageSelect[elPageSelect.selectedIndex].innerHTML;

	var previousItem;
	for (i=0;i<ofwat.referenceSelection.itemArray.length;i++)
    {
		var item = ofwat.referenceSelection.itemArray[i];
		item.itemEquals = ofwat.referenceSelection.itemEquals;
		if (!item.itemEquals(previousItem)) {
			var titleText;
			if (item.lineNumber == 0) {
				titleText = "title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode) + "   Table " + selectedPageValue + "   " + ofwat.escapeHTML(item.description) + "'>"; 
				dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + "#" + item.group + "' " +
						titleText +
						ofwat.escapeHTML(item.itemCode + " [ M:" + item.modelCode + " T:" + selectedPageValue + " ] " + item.description) + "</option>", elStartList);
			}
			else {
				titleText = "title='" + ofwat.escapeHTML(item.itemCode + "   " + item.modelCode + "   Table " + selectedPageValue + "   Line " + item.lineNumber + "   " + item.description) + "'>";
				dojo.place("<option value='" + item.itemId+"#"+ item.modelId+"#"+item.modelPropertiesMapId + "#" + item.group + "' " +
						titleText +
						ofwat.escapeHTML(item.itemCode + " [ M:" + item.modelCode + " T:" + selectedPageValue + " L:" + item.lineNumber + " ] " + item.description) + "</option>", elStartList);
			}
		}
		previousItem = item;
    }
};


/**
 * handler function for loadPages
 */
ofwat.referenceSelection.populatePages = function(data) {
	var tableLinks = data;
	dojo.empty("pageSelect");
	var elPageSelect = dojo.byId("pageSelect");
	dojo.place("<option value='Any'>Any</option>", elPageSelect);
	for (var i=0; i<tableLinks.length; i++)
	{
		var link = tableLinks[i];
		dojo.place("<option value='" + link.uri + "'>" + ofwat.escapeHTML(link.name) + "</option>", elPageSelect);
	}
};


/**
 * handler function for loadTeams
 */
ofwat.referenceSelection.populateTeams = function(data) {
	ofwat.referenceSelection.teamArray = data;
};

/**
 * handler function for loadTeamsForUser
 */
ofwat.referenceSelection.populateTeamsForUser = function(data) {
	ofwat.referenceSelection.userTeamArray = data;
};