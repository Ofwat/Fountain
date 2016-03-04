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
	alert("missing import: referenceSelection.js");
}

ofwat.groupEntry = [];


ofwat.groupEntry.renameGroup = function(){
	// get the groupID and the new name
	var elGroupEntrySelect = dojo.byId("groupEntryStartList");
	
	
	var newName = dojo.byId("newGroupName").value;
	
	if(0 > elGroupEntrySelect.selectedIndex){
		alert("You must select a group entry");
		return false;
	}
	var groupEntryId = elGroupEntrySelect[elGroupEntrySelect.selectedIndex].value;	
	if(!newName || "" == newName){
		alert("You must enter a descriptive name for this group");
		return false;
	}
	ofwat.groupEntry.sendUpdate(groupEntryId, newName);
	
	
};

ofwat.groupEntry.sendUpdate = function(groupEntryId, newName){
	var payload = {description:{}, id:{}};
	payload.description = newName;
	payload.id = groupEntryId;
	var xhrArgs = {
	        url: "../../rest-services/group/group-entry",
	        handleAs: "json",
	        postData: dojo.toJson(payload),
	        headers: {"Content-Type": "application/json"},
	        load: function(data){
	        	ofwat.referenceSelection.loadCompanyGroups();
	        	ofwat.showMessage("");
	    	},
	    	error: function(error) {
	            ofwat.showError("update group entry", error);
			}
	    	
 };
	dojo.xhrPost(xhrArgs);

};




ofwat.groupEntry.initialise = function(){
	
	dojo.byId("newGroupName").value = "";
	ofwat.referenceSelection.loadCompanies();
	ofwat.referenceSelection.loadGroups();
	ofwat.referenceSelection.clearLists();
};




ofwat.groupEntry.initialise();