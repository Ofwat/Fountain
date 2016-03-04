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

if(!ofwat.rest){
	alert("missing import: rest.js");
}

if(!ofwat.referenceSelection){
	alert("missing import: referenceSelection.js");
}

ofwat.landingPage = {
	companyArray: {}	
		
};

ofwat.landingPage.loadInitialData = function() {
	var deferred = ofwat.referenceSelection.loadCompanyTypes(); 
	deferred.then(ofwat.landingPage.loadCompanies).then(ofwat.landingPage.loadModels);
	//Switch selection to prev saved (if exists) #story 352
	//console.log("loading menu selections: " + ofwat.readCookie("companyTypeValue") + " " +  ofwat.readCookie("companyValue") + " " + ofwat.readCookie("modelValue") + " " + ofwat.readCookie("pageValue"));
	ofwat.landingPage.loadExistingMenuSelection();
};

//Load the existing menu selections stored in a cookie(!)
ofwat.landingPage.loadExistingMenuSelection = function(){
	var companyTypeSelectValue = ofwat.readCookie("companyTypeValue");
	var companySelectValue = ofwat.readCookie("companyValue");
	var modelSelectValue = ofwat.readCookie("modelValue");
	var pageSelectValue = ofwat.readCookie("pageValue");
	if(companyTypeSelectValue && companySelectValue && modelSelectValue && pageSelectValue){
		var companyTypeSelect = dojo.byId('companyTypes');
		companyTypeSelect.value = companyTypeSelectValue;
		//Manually trigger on Change event. 
		companyTypeSelect.onchange();
		var companySelect = dojo.byId('company');
		companySelect.value = companySelectValue;
		var modelSelect = dojo.byId('model');
		modelSelect.value = modelSelectValue;
		//fire off the pageLoadHandler so that we get the correct data loaded.
		ofwat.landingPage.loadPageHandler();
		//Can't do below until the change event has fired
		var pageSelect = dojo.byId('page');
		pageSelect.value = pageSelectValue;
	}
};

ofwat.landingPage.setupEventHandlers = function(){
	dojo.connect(dojo.byId("model"), "onchange", ofwat.landingPage, "loadPageHandler");
	dojo.connect(dojo.byId("company"), "onchange", ofwat.landingPage, "loadPageHandler");
};

ofwat.landingPage.loadCompanies = function(val) {
	var xhrArgs = {
	        url: "../../rest-services/company/all",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.landingPage.parseCompanies(data);
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
ofwat.landingPage.parseCompanies = function(companyArray) {
	
	ofwat.landingPage.companyArray=companyArray;
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

//	var companyIdx = (ofwat.getQueryVariable('companyIdx')!=null)?ofwat.getQueryVariable('companyIdx'):0;
//	sel.selectedIndex=companyIdx;
	sel.selectedIndex=0;
	
};

ofwat.landingPage.loadModels = function(){
	var xhrArgs = {
	        url: "../../rest-services/model?typeId=1",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.landingPage.parseModels(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadModels errored", error);
			}
	    };
    	ofwat.showMessage("Loading Models... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
};

ofwat.landingPage.parseModels = function(modelArray){
	var sel = dojo.byId('model');
	while (sel.childNodes[0])
	{
		sel.removeChild(sel.childNodes[0]);
	}
    var modelId = "not assigned";
	for (i=0;i<modelArray.length;i++)
    {
		if (modelArray[i].displayOrder > -1) {
	    {
		    if ("not assigned" == modelId) {
				modelId = modelArray[i].id;
			}
		    try
		      {
		    	opt = document.createElement("option");
				opt_txt = document.createTextNode(ofwat.escapeHTML(modelArray[i].name));
				opt.appendChild(opt_txt);
				opt.setAttribute("value", modelArray[i].id+"#"+modelArray[i].code);
				sel.appendChild(opt);
		      }
		    catch (er)
		      {
			      alert(er);
		      }
		    }
	    }
	}
	
//	var modelIdx = (ofwat.getQueryVariable('modelIdx')!=null)?ofwat.getQueryVariable('modelIdx'):0;
//	sel.selectedIndex=modelIdx;
	sel.selectedIndex=0;
	if (modelArray.length>0){
		var companySelect = dojo.byId("company");
		var companyId = companySelect.options[companySelect.selectedIndex].value;
		ofwat.landingPage.loadPages(modelId, companyId);
	}
	
};

ofwat.landingPage.loadPages = function(modelId, companyId){
	var xhrArgs = {
	        url: "../../rest-services/model/"+modelId + "/company?companyId=" + companyId, 
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.landingPage.parsePages(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadPages errored", error);
			}
	    };
    	ofwat.showMessage("Loading pages... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	    return deferred;
	
};


ofwat.landingPage.loadPageHandler = function(event){
	var modelSelect = dojo.byId("model");
	var modelId = modelSelect.options[modelSelect.selectedIndex].value.split('#')[0];
	var companySelect = dojo.byId("company");
	var companyId = companySelect.options[companySelect.selectedIndex].value;
	ofwat.landingPage.loadPages(modelId, companyId);
};





ofwat.landingPage.parsePages = function(pageArray){

	var sel = dojo.byId('page');
	while (sel.childNodes[0])
	{
		sel.removeChild(sel.childNodes[0]);
	}
	sel.options.length=0;
	for (i=0;i<pageArray.length;i++)
    {
	    {
	    try
	      {
	    	opt = document.createElement("option");
			opt_txt = document.createTextNode(pageArray[i].name + ' - ' + pageArray[i].description);
			opt.appendChild(opt_txt);
			opt.setAttribute("value", pageArray[i].name);
			sel.appendChild(opt);
	      }
	    catch (er)
	      {
		      alert(er);
	      }
	    }
	}

//	var pageIdx = (ofwat.getQueryVariable('pageIdx')!=null)?ofwat.getQueryVariable('pageIdx'):0;
//	sel.selectedIndex=pageIdx;
	sel.selectedIndex=0;
};

ofwat.landingPage.openModel = function (model, page) {
	
	if((model != null) && (page != null)){
		var companyTypeSelect = dojo.byId('companyTypes');
		var companyTypeValue = companyTypeSelect.options[companyTypeSelect.selectedIndex].value;
		var companySelect = dojo.byId('company');
		var companyValue = companySelect.options[companySelect.selectedIndex].value; 
		var modelValue = model;
		var modelInfo = modelValue.split("#");
		var pageValue = page;
	}else{
		var companyTypeSelect = dojo.byId('companyTypes');
		var companyTypeValue = companyTypeSelect.options[companyTypeSelect.selectedIndex].value;
		var companySelect = dojo.byId('company');
		var companyValue = companySelect.options[companySelect.selectedIndex].value; 
		var modelSelect = dojo.byId('model');
		var modelValue = modelSelect.options[modelSelect.selectedIndex].value;
		var modelInfo = modelValue.split("#");
		var pageSelect = dojo.byId('page');
		var pageValue = pageSelect.options[pageSelect.selectedIndex].value;		
	}
	
	// open the model as specified in the form
	/*
	var companyTypeSelect = dojo.byId('companyTypes');
	var companyTypeValue = companyTypeSelect.options[companyTypeSelect.selectedIndex].value;
	var companySelect = dojo.byId('company');
	var companyValue = companySelect.options[companySelect.selectedIndex].value; 
	var modelSelect = dojo.byId('model');
	var modelValue = modelSelect.options[modelSelect.selectedIndex].value;
	var modelInfo = modelValue.split("#");
	var pageSelect = dojo.byId('page');
	var pageValue = pageSelect.options[pageSelect.selectedIndex].value;
	*/
	
	//We'll use cookies, although we are a stateless app this doesn't affect REST principles - state is stored on client. 
	//console.log("saving changes:" + companyTypeValue + " " + companyValue + " " + modelValue + " " + pageValue);
	ofwat.createCookie("companyTypeValue", companyTypeValue, 1);
	ofwat.createCookie("companyValue", companyValue, 1);
	ofwat.createCookie("modelValue", modelValue, 1);
	ofwat.createCookie("pageValue", pageValue, 1);
	
	window.location.href = "model/"+modelInfo[1]+"/table_"+pageValue+".page?companyId="+companyValue;
//	var uri = "model/"+modelInfo[1]+"/table_"+pageValue+".page?companyId="+companyValue;
//	window.location.href = ofwat.indexPageSelectRewrite(uri);
	return false;
};

ofwat.landingPage.filterCompanies = function(){
	var elSelector = dojo.byId("companyTypes"); 
	var selectedTypeId = elSelector.options[elSelector.selectedIndex].value;
	var historic = (selectedTypeId == 'exp');
	var elCompanyList = dojo.byId("company");
	dojo.empty("company");
	for (i=0;i<ofwat.landingPage.companyArray.length;i++)
	{
		var company=ofwat.landingPage.companyArray[i];
	
		if((0 == selectedTypeId && !company.expired) || 
		   ((company.companyType.id == selectedTypeId) && 
		    !company.expired) || 
		    (historic && company.expired)){
			dojo.place("<option value='"+company.id+ "'>" + company.name + " ["+ofwat.escapeHTML(company.code)+"]</option>", elCompanyList);
		}
	}

};

// initialise
ofwat.landingPage.setupEventHandlers();
// Only load with the current companies (user can use checkbox to toggle if historic companies are included)
ofwat.landingPage.loadInitialData();
