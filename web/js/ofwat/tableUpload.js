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

ofwat.tableUpload = {
	companyArray: {}	
		
};

ofwat.tableUpload.loadInitialData = function() {
	ofwat.tableUpload.loadCompanies();
	ofwat.tableUpload.loadModels();

};

ofwat.tableUpload.setupEventHandlers = function(){
	
	dojo.connect(dojo.byId("model"), "onchange", ofwat.tableUpload, "loadPageHandler");
	dojo.connect(dojo.byId("company"), "onchange", ofwat.tableUpload, "loadPageHandler");

};

ofwat.tableUpload.loadPageHandler = function(event){
	var modelSelect = dojo.byId("model");
	if (0 == modelSelect.options[modelSelect.selectedIndex].value) {
		var sel = dojo.byId('page');
		ofwat.tableUpload.blankOptions(sel);
	}
	else {
		var modelId = modelSelect.options[modelSelect.selectedIndex].value.split('#')[0];
		var companySelect = dojo.byId("company");
		var companyId = companySelect.options[companySelect.selectedIndex].value;
		if (0 == companyId) {
			var sel = dojo.byId('page');
			ofwat.tableUpload.blankOptions(sel);
		}
		else {
			ofwat.tableUpload.loadPages(modelId, companyId);
		}
	}
};

ofwat.tableUpload.loadCompanies = function() {
	var xhrArgs = {
	        url: "../../rest-services/company/all",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.tableUpload.parseCompanies(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("parseCompanies errored", error);
			}
	    };
    	ofwat.showMessage("Loading companies... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	
};
ofwat.tableUpload.parseCompanies = function(companyArray) {
	
	ofwat.tableUpload.companyArray=companyArray;
	var sel = dojo.byId('company');
	ofwat.tableUpload.blankOptions(sel);

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

ofwat.tableUpload.loadModels = function(){
	var xhrArgs = {
	        url: "../../rest-services/model?typeId=1",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.tableUpload.parseModels(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadModels errored", error);
			}
	    };
    	ofwat.showMessage("Loading Models... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.tableUpload.parseModels = function(modelArray){
	var sel = dojo.byId('model');
	ofwat.tableUpload.blankOptions(sel);

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
	sel.selectedIndex=0;
	if (modelArray.length>0){
		var companySelect = dojo.byId("company");
		var companyId = companySelect.options[companySelect.selectedIndex].value;
		ofwat.tableUpload.loadPages(modelId, companyId);
	}
	
};


ofwat.tableUpload.loadPages = function(modelId, companyId){
	var xhrArgs = {
	        url: "../../rest-services/model/"+modelId + "/company?companyId=" + companyId, 
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.tableUpload.parsePages(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("loadPages errored", error);
			}
	    };
    	ofwat.showMessage("Loading pages... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
	
};


ofwat.tableUpload.blankOptions = function(sel) {
	while (sel.childNodes[0])
	{
		sel.removeChild(sel.childNodes[0]);
	}
	
	try {
		opt = document.createElement("option");
		opt_txt = document.createTextNode("");
		opt.appendChild(opt_txt);
		opt.setAttribute("value", 0);
		sel.appendChild(opt);
	}
	catch (er) {
		      alert(er);
	}
}

ofwat.tableUpload.parsePages = function(pageArray){

	var sel = dojo.byId('page');
	ofwat.tableUpload.blankOptions(sel);
	
	sel.options.length=1;
	for (i=0;i<pageArray.length;i++)
    {
	    {
	    try
	      {
	    	opt = document.createElement("option");
			opt_txt = document.createTextNode(ofwat.escapeHTML(pageArray[i].name));
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
	sel.selectedIndex=0;
};

// initialise
ofwat.tableUpload.setupEventHandlers();
// Only load with the current companies (user can use checkbox to toggle if historic companies are included)
ofwat.tableUpload.loadInitialData();
