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
ofwat.reportCompanyRunSelection = {
		companyArray: {},
		dataTagArray: {},
		reportUrl: null,
		reportHasCompany: null,
		editingCompanyTypeId: null,
		editingCompanyId: null,
		editingRunId: null,
		editingDataTagId: null,
		reportHasRun: null,
		reportId: null
};


var ord = ofwat.reportCompanyRunSelection;
var cgSelect = null;
var codeSelects = null;
var groupsByCompanies = null;
	
ofwat.reportCompanyRunSelection.removeCompanySelection = function () {
	dojo.byId('selectCompanyDiv').style.display='none';
	dojo.byId('fade').style.display='none';
};

ofwat.reportCompanyRunSelection.excelExportReport = function (url) {

	var company;
	var run;
	var dataTag;
	if (!reportHasCompany) { 
		var companySelector = dojo.byId("company"); 
		company = companySelector.options[companySelector.selectedIndex].value;
	}
	if (!reportHasRun) {
		var runSelector = dojo.byId('run');
		run = runSelector.options[runSelector.selectedIndex].value;
		var dataTagSelector = dojo.byId('dataTag');
		dataTag = dataTagSelector.options[dataTagSelector.selectedIndex].value;
	}

	if (!reportHasCompany && 
		null != company) {
		url = url + "?companyId=" + company;
	}

	if (null != run &&
		null != dataTag) {
		if (url.indexOf("?") > -1) {
			url = url + "&runId=" + run + "&tagId=" + dataTag;
		}
		else {
			url = url + "?runId=" + run + "&tagId=" + dataTag;
		}
	}
	
	window.open(url);
	ofwat.reportCompanyRunSelection.removeCompanySelection();
};

ofwat.reportCompanyRunSelection.onLoad = function() {
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

dojo.require("dojo.NodeList-traverse");

ofwat.reportCompanyRunSelection.loadCompanyTypes = function() {
	
	var xhrArgs = {
	        url: "../../rest-services/company/types",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.reportCompanyRunSelection.populateCompanyTypes(data);
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

/**
 * handler function for loadCompanyTypes
 */
ofwat.reportCompanyRunSelection.populateCompanyTypes = function(data) {
	var companyTypeArray = data;
	var elCompanyTypeSelector = dojo.byId("companyType");
	dojo.empty("companyType");
	dojo.place("<option value='0'>All Current</option>", elCompanyTypeSelector);
	
	for (i=0;i<companyTypeArray.length;i++)
	{
		var companyType = companyTypeArray[i];
		dojo.place("<option value='"+companyType.id+ "'>All Current " + ofwat.escapeHTML(companyType.code) + "</option>", elCompanyTypeSelector);
	}	
	dojo.place("<option value='exp'>Historic</option>", elCompanyTypeSelector);
	
};

ofwat.reportCompanyRunSelection.loadCompanies = function() {
	var xhrArgs = {
	        url: "../../rest-services/company/all",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.reportCompanyRunSelection.populateCompanies(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("parseCompanies errored", error);
			}
	    };
    	ofwat.showMessage("Loading companies... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};

ofwat.reportCompanyRunSelection.populateCompanies = function(companyArray) {
	ofwat.reportCompanyRunSelection.companyArray=companyArray;
	var sel = dojo.byId('company');
	// clear out any current options
	while (sel.childNodes[0])
	{
		sel.removeChild(sel.childNodes[0]);
	}
	for (i=0;i<companyArray.length;i++)
    {
	    {
	    try
	      {
	    	opt = document.createElement("option");
			opt_txt = document.createTextNode(companyArray[i].name+" ["+companyArray[i].code+"]");
			opt.appendChild(opt_txt);
			opt.setAttribute("value", companyArray[i].id);
			sel.appendChild(opt);
	      }
	    catch (er)
	      {
		      alert(er);
	      }
	    }
	}
	sel.selectedIndex=0;
};

ofwat.reportCompanyRunSelection.filterCompanies = function(){
	var elSelector = dojo.byId("companyType"); 
	var selectedTypeId = elSelector.options[elSelector.selectedIndex].value;
	var historic = (selectedTypeId == 'exp');
	var elCompanyList = dojo.byId("company");
	dojo.empty("company");
	for (i=0;i<ofwat.reportCompanyRunSelection.companyArray.length;i++)
	{
		var company=ofwat.reportCompanyRunSelection.companyArray[i];
	
		if((0 == selectedTypeId && !company.expired) || 
		   ((company.companyType.id == selectedTypeId) && 
		    !company.expired) || 
		    (historic && company.expired)){
			dojo.place("<option value='"+company.id+ "'>" + company.name + " ["+ofwat.escapeHTML(company.code)+"]</option>", elCompanyList);
		}
	}
};

ofwat.reportCompanyRunSelection.loadAgenda = function() {
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
            ofwat.reportCompanyRunSelection.populateAgenda(data);
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

ofwat.reportCompanyRunSelection.populateAgenda = function(data) {
    //alert("populating Agenda");
    var agendaArray = data;
    var elAgendaSelector = dojo.byId("agenda");
    dojo.empty("agenda");
    for (i=0;i<agendaArray.length;i++)
    {
        var agenda = agendaArray[i];
        dojo.place("<option value='"+agenda.id+ "'> " + ofwat.escapeHTML(agenda.name) + "</option>", elAgendaSelector);
    }
    var selectedAgendaId = elAgendaSelector.options[elAgendaSelector.selectedIndex].value;
    ofwat.reportCompanyRunSelection.loadRuns(selectedAgendaId);
}

ofwat.reportCompanyRunSelection.updateRuns = function(){
    var elSelector = dojo.byId("agenda");
    var selectedAgendaId = elSelector.options[elSelector.selectedIndex].value;
    ofwat.reportCompanyRunSelection.loadRuns(selectedAgendaId);
}

ofwat.reportCompanyRunSelection.loadRuns = function(agendaId) {
	
	var xhrArgs = {
	        url: "../../rest-services/runs?agendaId=" + agendaId,
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.reportCompanyRunSelection.populateRuns(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadRuns errored", error);
			}
	    };
    	ofwat.showMessage("Loading runs... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};

/**
 * handler function for loadRuns
 */
ofwat.reportCompanyRunSelection.populateRuns = function(data) {
	var runArray = data;
	var elRunSelector = dojo.byId("run");
	dojo.empty("run");
	
	for (i=0;i<runArray.length;i++) {
		var run = runArray[i];
		dojo.place("<option value='"+run.id+ "'>" + ofwat.escapeHTML(run.name) + "</option>", elRunSelector);
	}	
	elRunSelector.selectedIndex=0;
};

ofwat.reportCompanyRunSelection.loadDataTags = function(reportId) {
	var tagsUrl = "../../rest-services/tags/run?" + "reportId=" + reportId;
	if (!reportHasCompany) { 
		var companySelector = dojo.byId("company"); 
		var selectedCompanyId = companySelector.options[companySelector.selectedIndex].value;
		tagsUrl = tagsUrl + "&companyId=" + selectedCompanyId; 
	}
	if (!reportHasRun) {
		var runSelector = dojo.byId('run');
		//var selectedRunId = runSelector.options[runSelector.selectedIndex].value;
		var selectedRunId = (function(){if(runSelector.options[runSelector.selectedIndex]){return(runSelector.options[runSelector.selectedIndex].value);}else{return(null);}})()
		tagsUrl = tagsUrl + "&runId=" + selectedRunId;
	}

	var xhrArgs = {
	        url: tagsUrl,
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.reportCompanyRunSelection.populateDataTags(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadDataTags errored", error);
			}
	    };
    	ofwat.showMessage("Loading checkpoints... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};

ofwat.reportCompanyRunSelection.populateDataTags = function(data) {
	ofwat.reportCompanyRunSelection.dataTagArray = data;
	ofwat.reportCompanyRunSelection.filterDataTags();
};

ofwat.reportCompanyRunSelection.filterDataTags = function(){
	// clear out any current options
	var sel = dojo.byId('dataTag');
	while (sel.childNodes[0]) {
		sel.removeChild(sel.childNodes[0]);
	}

	var runSelector = dojo.byId('run');
	var selectedRunId = runSelector.options[runSelector.selectedIndex].value;
	if (selectedRunId != 0 &&
		runSelector.length != 1) {
		dojo.place("<option value='0'>Latest</option>", sel);
	}
	
	for (i=0; i<ofwat.reportCompanyRunSelection.dataTagArray.length; i++) {
		var dataTag = ofwat.reportCompanyRunSelection.dataTagArray[i];
//		if (dataTag.run &&
//			dataTag.run.id == selectedRunId) {
			dojo.place("<option value='"+dataTag.id+ "'>" + ofwat.escapeHTML(dataTag.displayName) + "</option>", sel);
//		}
	}	

	sel.selectedIndex = 0;
};

ofwat.reportCompanyRunSelection.getReportDetails = function(){
	var xhrArgs = {
			url: reportUrl + "/edit",
			handleAs: "json",
			headers: {"accept": "application/json"},
            sync: true,
			load: function(data){
				ofwat.reportCompanyRunSelection.processReportDetails(data);
				ofwat.showMessage("");
			},
			error: function(error) {
				ofwat.showError(error);
			}
	};
	ofwat.showMessage("Loading report... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.reportCompanyRunSelection.processReportDetails = function(data){
	if (data.runTagIds) {
		reportHasRun = 	true;
	}
	else {
		reportHasRun = 	false;
	}
	if (data.companies) {
		reportHasCompany = 	true;
	}
	else {
		reportHasCompany = 	false;
	}
};


