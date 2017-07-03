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
if(!ofwat.referenceSelection){
	alert("missing import: referenceSelection.js");
}


ofwat.itemRoles = {
	
};


var getElementsByClassName = function (className, tag, elm){
	if (document.getElementsByClassName) {
		getElementsByClassName = function (className, tag, elm) {
			elm = elm || document;
			var elements = elm.getElementsByClassName(className),
				nodeName = (tag)? new RegExp("\\b" + tag + "\\b", "i") : null,
				returnElements = [],
				current;
			for(var i=0, il=elements.length; i<il; i+=1){
				current = elements[i];
				if(!nodeName || nodeName.test(current.nodeName)) {
					returnElements.push(current);
				}
			}
			return returnElements;
		};
	}
	else if (document.evaluate) {
		getElementsByClassName = function (className, tag, elm) {
			tag = tag || "*";
			elm = elm || document;
			var classes = className.split(" "),
				classesToCheck = "",
				xhtmlNamespace = "http://www.w3.org/1999/xhtml",
				namespaceResolver = (document.documentElement.namespaceURI === xhtmlNamespace)? xhtmlNamespace : null,
				returnElements = [],
				elements,
				node;
			for(var j=0, jl=classes.length; j<jl; j+=1){
				classesToCheck += "[contains(concat(' ', @class, ' '), ' " + classes[j] + " ')]";
			}
			try	{
				elements = document.evaluate(".//" + tag + classesToCheck, elm, namespaceResolver, 0, null);
			}
			catch (e) {
				elements = document.evaluate(".//" + tag + classesToCheck, elm, null, 0, null);
			}
			while ((node = elements.iterateNext())) {
				returnElements.push(node);
			}
			return returnElements;
		};
	}
	else {
		getElementsByClassName = function (className, tag, elm) {
			tag = tag || "*";
			elm = elm || document;
			var classes = className.split(" "),
				classesToCheck = [],
				elements = (tag === "*" && elm.all)? elm.all : elm.getElementsByTagName(tag),
				current,
				returnElements = [],
				match;
			for(var k=0, kl=classes.length; k<kl; k+=1){
				classesToCheck.push(new RegExp("(^|\\s)" + classes[k] + "(\\s|$)"));
			}
			for(var l=0, ll=elements.length; l<ll; l+=1){
				current = elements[l];
				match = false;
				for(var m=0, ml=classesToCheck.length; m<ml; m+=1){
					match = classesToCheck[m].test(current.className);
					if (!match) {
						break;
					}
				}
				if (match) {
					returnElements.push(current);
				}
			}
			return returnElements;
		};
	}
	return getElementsByClassName(className, tag, elm);
};

var rowList = getElementsByClassName("contentPane", "div", null); // all div tags with class=contentPane
for(var i = 0; i < rowList.length; i++ ){
    rowList[i].setAttribute('rel', 'dijit.layout.ContentPane');
}
rowList = getElementsByClassName("tabContainer", "div", null);
for(var i = 0; i < rowList.length; i++ ){
	rowList[i].setAttribute('rel', 'dijit.layout.TabContainer');
    rowList[i].setAttribute('doLayout', 'false');
}
rowList = getElementsByClassName("dojoButton", "div", null);
for(var i = 0; i < rowList.length; i++ ){
    rowList[i].setAttribute('dojoType', 'dijit.form.Button');
}
rowList = getElementsByClassName("dojoTextBox", "div", null);
for(var i = 0; i < rowList.length; i++ ){
	rowList[i].setAttribute('dojoType', 'dijit.form.TextBox');
	rowList[i].setAttribute('trim', 'true');
	rowList[i].setAttribute('propercase', 'false');
    rowList[i].setAttribute('placeHolder', 'Search here');
}

ofwat.itemRoles.assignTeam = function(){
	var itemList = dojo.byId("itemStartList"); 
	var teamSelect = dojo.byId("teamSelect");
	var currentTeamNameDiv = dojo.byId("currentTeamName");
	
	if(itemList[itemList.selectedIndex] && teamSelect[teamSelect.selectedIndex].value){
		var itemId = itemList[itemList.selectedIndex].value;
		itemId = itemId.split("#")[0];
		var teamId = teamSelect[teamSelect.selectedIndex].value;
		var teamName = teamSelect[teamSelect.selectedIndex].text;
		ofwat.itemRoles.setTeamOnItem(teamId, itemId);
		
		for (var i=0;i<ofwat.referenceSelection.itemArray.length;i++)
	    {
			var item = ofwat.referenceSelection.itemArray[i];
			if(item.itemId == itemId){
				item.teamName = teamName;
				currentTeamNameDiv.innerHTML = item.teamName;
				return false;
			}
	    }
		return false;
	}
	alert("Please select an item and a team");
	
};

ofwat.itemRoles.setTeamOnItem = function(teamId, itemId){
	/**
	 * Rest call to get teams.
	 */
	 var xhrArgs = {
	        url: "../../rest-services/item/" + itemId + "/team/" + teamId,
	        handleAs: "json",
	        headers: {
	            "accept": "application/json"
	        },
            load: function(data) {
    			ofwat.showMessage("");
            },
	    	error: function(error) {
	            alert("set Team On Item errored"); 
			}
	 };
	ofwat.showMessage("Setting team for the item... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrPost(xhrArgs);

};


ofwat.itemRoles.showTeam = function(){
	ofwat.itemRoles.setupTeams();	
	
	var itemList = dojo.byId("itemStartList"); 
	var currentTeamNameDiv = dojo.byId("currentTeamName");
	var itemId = parseInt(itemList[itemList.selectedIndex].value.split("#")[0]);
	for (var i=0;i<ofwat.referenceSelection.itemArray.length;i++)
    {
		var item = ofwat.referenceSelection.itemArray[i];
		if(item.itemId == itemId){
			currentTeamNameDiv.innerHTML = item.teamName;
			return false;
		}
    }
};

ofwat.itemRoles.setupTeams = function() {
	// Count the current number of teams. If we've already set this up then we can stop setting up.
	var teamSelect = dojo.byId("teamSelect");
	var opts = teamSelect.getElementsByTagName("option");
	var count = 0;
	if (opts) {
		count = opts.length;
	}
	if (count<=0) {
		// No teams set up so we do it now
		var teams = ofwat.referenceSelection.teamArray;	
		teams.sort(ofwat.itemRoles.teamComparator);
		
		var teamSelect = dojo.byId("teamSelect");
		if (teamSelect) {
			dojo.empty(teamSelect);
			
			for (var i=0; i<teams.length; i++) {
				var team=teams[i];
				dojo.place("<option value='"+team.id+ "'>" + ofwat.escapeHTML(team.name) +"</option>", teamSelect);
			}
		}
	}
};

ofwat.itemRoles.teamComparator = function(a, b) {
	if (a.code=="Unassigned") 		return -1;
	else if (b.code=="Unassigned") 	return 1;
	else if (a.name<b.name) 		return -1;
	else if (a.name>b.name) 		return 1;
	else 							return 0;
	
};

//initialisation call
ofwat.referenceSelection.loadModels();
ofwat.referenceSelection.loadTeams();
ofwat.referenceSelection.clearLists();


