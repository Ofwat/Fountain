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
if(!ofwat){
	alert("missing import: ofwat.js");
}

if(!ofwat.referenceSelection){
	alert("Missing import: referenceSelection.js");
}

ofwat.exporting = {
	companyArray: {},
	tableArray: {},
	modelArray: {}
};

ofwat.exporting.populateOptions = function () {
	// get the models
	ofwat.exporting.loadModels();
	ofwat.referenceSelection.loadCompanyTypes();
	
	// get the companies
	ofwat.exporting.loadCompanies();

};

ofwat.exporting.loadCompanies = function() {
   var xhrArgs = {
        url: "../../rest-services/company/all",
        handleAs: "json",
        headers: {
            "accept": "application/json"
        },
        load: function(data){
    		ofwat.exporting.populateCompanies(data);
    		ofwat.showMessage("");
    	},
    	error: function(error) {
    		ofwat.showError("loadCompanies errored", error);
		}
    };
	ofwat.showMessage("Loading companies... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.exporting.populateCompanies = function(data) {
	// get the company names, put them into a big list of checkboxes
	ofwat.exporting.companyArray = data;
	var place = dojo.byId("companies");
	dojo.empty(place);
	var checkBox = "";

	for (var i=0; i<ofwat.exporting.companyArray.length; i++) {
		var company = ofwat.exporting.companyArray[i];
		// Assuming only displaying current companies on first view of the page
		if (!company.expired){
			// create a new checkbox
			var companyId = "cb"+company.code;
			checkBox = "<div class='span2'><label class='export' for='"+companyId+"'><input title='"+company.name+"' type=checkbox class='dojoCheckBox companycb export' name='"+company.id + "' id='"+companyId+"' onclick=\"ofwat.exporting.unCheckAll(this,'cbAllCompanies')\"/> "+company.code+"</label></div>";
			dojo.place(checkBox, place, "last");
		}
	}
	checkBox = "<label class='export' for='cbAllCompanies'><input title='Select All Companies' type=checkbox class='dojoCheckBox export' name='All Companies' id='cbAllCompanies' onclick=\"ofwat.exporting.toggle('companycb', this.checked)\"/> Select All</label>";
	dojo.place(checkBox, "companyAll", "only");
};

ofwat.exporting.loadPages = function() {
	var elModelSelect = dojo.byId('modelSelect');
	var modelId = elModelSelect[elModelSelect.selectedIndex].value; 
	var tables = dojo.byId("tables");
	dojo.empty(tables);

	 var xhrArgs = {
		        url: "../../rest-services/model/" + modelId + "/table/structure",
		        handleAs: "json",
		        headers: {
		            "accept": "application/json"
		        },
		        load: function(data){
		        	ofwat.exporting.populateTableList(data);
		        	ofwat.showMessage("");
		    	},
		    	error: function(error) {
		            ofwat.showError("loadPages errored", error);
				}
	 };
	 ofwat.showMessage("Loading pages... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.exporting.populateTableList = function(data){
	ofwat.exporting.tableArray = data;
	var place = dojo.byId("tables");
	
	for (var i=0; i<ofwat.exporting.tableArray.length; i++) {
		var table = ofwat.exporting.tableArray[i];
		// create a new checkbox
		var tableId = "cb"+table.name;
		var id = ofwat.exporting.getIdFromLink(table.uri);
		checkBox = "<div class='span2'><label class='export' for='"+tableId+"'><input type=checkbox class='dojoCheckBox tablecb export' name='"+ id + "' id='"+tableId+"' onclick=\"ofwat.exporting.unCheckAll(this,'cbAllTables')\"/> "+table.name+"</label></div>";
//		checkBox = "<div class='span4'><div class='box'>aaaaaaaaaa</div></div>";
//		checkBox = "<li class='col-sm-3'><label class='export' for='"+tableId+"'>"+table.name+"<input type=checkbox class='dojoCheckBox tablecb export' name='"+ id + "' id='"+tableId+"' onclick=\"ofwat.exporting.unCheckAll(this,'cbAllTables')\"/></label></li>";
		dojo.place(checkBox, place, "last");
	}
	var checkBox = "<label class='export' for='cbAllTables'><input title='Select All Tables' type=checkbox class='dojoCheckBox export' name='All Tables' id='cbAllTables' onclick=\"ofwat.exporting.toggle('tablecb', this.checked)\"/> Select All</label>";
	dojo.place(checkBox, "tableAll", "only");
};

ofwat.exporting.getIdFromLink = function(link) {
	var i = link.indexOf("rest-services/table/")+20;
	var j = link.indexOf("/structure");
	return link.substring(i, j);
};
// if the Select All box is checked and this is not, then unselect Select All
ofwat.exporting.unCheckAll = function(checkBox, allBox){
	var check = dojo.byId(checkBox);
	var all = dojo.byId(allBox);
	if (!check.checked){
		all.checked = false;
	}
};

ofwat.exporting.loadModels = function() {
	var xhrArgs = {
        url: "../../rest-services/model?typeId=1",
        handleAs: "json",
        headers: {
            "accept": "application/json"
        },
        load: function(data){
    		ofwat.exporting.populateModels(data);
    		ofwat.showMessage("");
    	},
    	error: function(error) {
            ofwat.showError("loadModels errored", error);
		}  	
    };
 	ofwat.showMessage("Loading models... <img border='0' src='../../images/loader.gif'/>");
    var deferred = dojo.xhrGet(xhrArgs);

};
ofwat.exporting.populateModels = function(data) {
	
	var modelArray = data;
	var elModelSelect = dojo.byId("modelSelect");
	for (var i=0; i<modelArray.length; i++)
	{
		var model = modelArray[i];
		if (model.displayOrder > -1) {
			dojo.place("<option value='" + model.id + "'>" + ofwat.escapeHTML(model.name) + "</option>", elModelSelect);
		}
	}
	ofwat.exporting.loadPages();
};

ofwat.exporting.exportToExcel = function() {
	var sendObject = {
			companyId : [],
			tableId : []
	};
	
	dojo.query('input.companycb').forEach(function(node) {
		// add to a list of companies if selected
			if (node.checked) {
				sendObject.companyId.push(node.name);
			}
		});

	dojo.query('input.tablecb').forEach(function(node) {
		// add to a list of tables if selected
			if (node.checked) {
				sendObject.tableId.push(node.name);
			}
		});

	if ((dojo.byId("cbAllTables").checked && dojo.byId("cbAllCompanies").checked) || 
	((sendObject.tableId.length * sendObject.companyId.length) > 250)){
		alert("You have selected too many tables and companies.\n " +
		"The maximum number of exported tables is 250, this is tables multiplied by companies");
			return;
		
	}
	if ((sendObject.tableId.length * sendObject.companyId.length) > 150){
		if (!confirm("You have selected a large number of companies and tables.\n"+
				"Are you sure you wish to continue?")){
			return;
		}
	}
	
	if (sendObject.tableId.length==0 && sendObject.companyId.length==0){
		alert("No Tables or Companies Selected");
		return;
	}
	
	if (sendObject.tableId.length==0){
		alert("No Tables Selected");
		return;
	}
	
	if (sendObject.companyId.length==0){
		alert("No Companies Selected");
		return;
	}
	ofwat.showMessage("Generating Tables.... <img border='0' src='../../images/loader.gif'/>");
//	var downloadExcelIframeName = "downloadExcelIframe"; 
	var queryStr = dojo.objectToQuery(sendObject);
	
	var url = "/Fountain/rest-services/table/excel/tables?"+queryStr;
	document.location = url;
	//var iframe = dojo.io.iframe.create(downloadExcelIframeName);
	//dojo.io.iframe.setSrc(iframe, url, true);
	//var deferred = dojo.io.iframe.send({
	//	url : url//,
//		error: function(response, ioArgs){
//		ofwat.showError(response);
//		console.log("error", response, ioArgs);
//		}, 
//		load: function(response){
//			ofwat.showMessage("");
//			console.log("finished", response);
//		}
//	});

	// hide the dojo.byId('goButton');
	// TODO need to know when it's finished
	// ofwat.showMessage("");
};

ofwat.exporting.toggle = function(searchStub, isChecked) {
	nodes = dojo.query('input.'+searchStub);
	dojo.forEach(nodes,
	    function(node) {
			if (isChecked){
				node.checked = true;
			} else {
				 node.checked = false;
			}
	    }
	);
};

ofwat.exporting.filterCompanies = function() {
	var elSelector = dojo.byId("companyTypes"); 
	var selectedTypeId = elSelector.options[elSelector.selectedIndex].value;	
	var historic = (selectedTypeId=='exp');
	var elCompanyList = dojo.byId("companies");
	dojo.empty(elCompanyList);
	for (i=0;i<ofwat.exporting.companyArray.length;i++) {
		var company=ofwat.exporting.companyArray[i]; 
		if((0 == selectedTypeId && !company.expired)|| ((company.companyType.id == selectedTypeId) && !company.expired ) || (historic && company.expired)){
			
			var companyId = "cb"+company.code;
			var checkBox = "<div class='span2'><label class='export' for='"+companyId+"'><input title='"+company.name+"' type=checkbox class='dojoCheckBox companycb export' name='"+company.id + "' id='"+companyId+"''/> "+company.code+"</label></div>";
			dojo.place(checkBox, elCompanyList, "last");
		}
	}
	var allCheckBox = "<label class='export' for='cbAllCompanies'><input title='Select All Companies' type=checkbox class='dojoCheckBox export' name='All Companies' id='cbAllCompanies' onclick=\"ofwat.exporting.toggle('companycb', this.checked)\"/> Select All</label>";
	dojo.place(allCheckBox, "companyAll", "only");
};