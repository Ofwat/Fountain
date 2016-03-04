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

ofwat.modelInformation = {
	models: {}	
		
};

ofwat.modelInformation.loadInitialData = function() {
	ofwat.modelInformation.loadModels();
	ofwat.modelInformation.displayModelInfo();
};

ofwat.modelInformation.loadModels = function(){
	var xhrArgs = {
	        url: "../../rest-services/model?typeId=0",
	        handleAs: "json",
	        preventCache: true,
			sync: true,
            headers: {
                "accept": "application/json"
            },
	        load: function(data){
				ofwat.modelInformation.parseModels(data);
				ofwat.showMessage("");
			},
            error: function(error) {
                ofwat.showError("Error loading models", error);
			}
	    };
    	ofwat.showMessage("Loading Models... <img border='0' src='../../images/loader.gif'/>");
	    var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.modelInformation.parseModels = function(modelArray){
	ofwat.modelInformation.models = modelArray;
	
	var sel = dojo.byId('model');
	while (sel.childNodes[0])
	{
		sel.removeChild(sel.childNodes[0]);
	}
    var modelId = "not assigned";
	for (var i=0;i<modelArray.length;i++)
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
				opt.setAttribute("value", modelArray[i].id);
				sel.appendChild(opt);
		      }
		    catch (er)
		      {
			      alert(er);
		      }
		    }
	    }
	}
	
	
};

ofwat.modelInformation.displayModelInfo = function() {

	ofwat.showError("");

	var modelSelect = dojo.byId('model');
	var selectedModelCode = modelSelect.options[modelSelect.selectedIndex].value;

	for (var i=0; i<ofwat.modelInformation.models.length; i++) {
		if (ofwat.modelInformation.models[i].id == selectedModelCode)
		{
			var table ="<table width='50%'>";
			table+="<tr><th>Table Suite Name</th><td style='padding-left=10'>"+ ofwat.modelInformation.models[i].name + "</td></tr>";
			table+="<tr><th>Table Suite Code</th><td>"+ ofwat.modelInformation.models[i].code + "</td></tr>";
			table+="<tr><th>Table Suite Type</th><td>"+ ofwat.modelInformation.models[i].modelTypeName + "</td></tr>";
			table+="<tr><th>Display Order</th><td>"+ ofwat.modelInformation.models[i].displayOrder + "</td></tr>";
			
			if (ofwat.modelInformation.models[i].branch != null) {
				table+="<tr><th>Branch Tag</th><td>"+ ofwat.modelInformation.models[i].branch.name + "</td></tr>";
				table+="<tr><th>Editable Branch</th><td><input type='checkbox' name='editableCheckbox' id='editableCheckbox' " + (ofwat.modelInformation.models[i].branch.editable ? "checked" : "") + "/></td></tr>";
			}
			
			if (ofwat.modelInformation.models[i].family != null) {
				table+="<tr><th>Family</th><td>"+ ofwat.modelInformation.models[i].family.name + "</td></tr>";
				table+="<tr><th>Parent</th><td>"+ ofwat.modelInformation.models[i].parent + "</td></tr>";
			}

			for (var j=0; j < ofwat.modelInformation.models[i].modelInputs.length; j++) {
				table+="<tr><th>Model Inputs</th><td>"+ ofwat.modelInformation.models[i].modelInputs[j].code + "</td></tr>";
			}

			table+="</table>";
			dojo.byId('modelInfoDiv').innerHTML = table;
		}
    }
};

ofwat.modelInformation.saveModel = function() {
	ofwat.showError("");
	ofwat.modelInformation.updateModel();
};

ofwat.modelInformation.updateModel = function(){

	var editable = dojo.byId('editableCheckbox').checked;
	var modelSelect = dojo.byId('model');
	var selectedModelCode = modelSelect.options[modelSelect.selectedIndex].value;

	var selectedModel = null;
	for (var i=0; i<ofwat.modelInformation.models.length; i++) {
		if (ofwat.modelInformation.models[i].id == selectedModelCode) {
			selectedModel = ofwat.modelInformation.models[i];
			selectedModel.branch.editable = editable;
		}
	}
	
	var xhrArgs = {
	        url: "../../rest-services/model",
	        putData: dojo.toJson(selectedModel),
	        handleAs: "json",
            sync: true,
	        headers: {"Content-Type": "application/json"},
	        load: function(data){
				ofwat.showMessage("");
				window.location.href = "/Fountain/jsp/protected/ModelInformation.jsp";
			},
            error: function(error, ioargs){
                var message = "";
                switch(ioargs.xhr.status){
                   case 409:
                     message = "Existing baskets contain edits for a branch tag you have attempted to change. Please close editing sessions or 'Remove edit locks' and then retry the operation.";
                     break;
                   default:
                     message = error;
                }
                ofwat.showError("Error saving model. ", message);
			}
	    };
    	ofwat.showMessage("Updating model... <img border='0' src='../../images/loader.gif'/>");
	    dojo.xhrPut(xhrArgs);
};

