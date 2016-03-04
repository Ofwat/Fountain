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


ofwat.admin = {};

ofwat.admin.showLockedItems = function(){
	var xhrArgs = {
			url: "/Fountain/rest-services/lock/users",
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				ofwat.showMessage("");
				ofwat.admin.populateLockingUsers(data);
			},
			error: function(error) {
				// This is not a problem, means basket does not exist, so all ok
				ofwat.showMessage("error retrieving locking users ");
			}
	};
	ofwat.showMessage("loading users with locks... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.admin.populateLockingUsers = function(data){
	var userArray = data;
	var elStartList = dojo.byId("userStartList");
	dojo.empty("userStartList");
	
	for (i=0;i<userArray.length;i++)
    {
		var user=userArray[i];
		dojo.place("<option value='" + user.id + "' title='" + ofwat.escapeHTML(user.name) + "'>" +ofwat.escapeHTML(user.name) + "</option>", elStartList);
    }
	
};

ofwat.admin.deleteLocks = function(rtn){
	var elStartList = dojo.byId("userStartList");
	while(elStartList.selectedIndex != -1){
		ofwat.admin.deleteLock(elStartList.options[elStartList.selectedIndex].value);
		elStartList.remove(elStartList.selectedIndex);
	}
	 return rtn;
};

ofwat.admin.deleteLock = function(userId){
	
	
	/**
	 * Rest call to delete locks for a user.
	 */
	 var xhrArgs = {
	        url: "/Fountain/rest-services/lock/" + userId ,
	        handleAs: "json",
	        headers: {
	            "accept": "application/json"
	        },
            load: function(data) {
    			ofwat.showMessage("");
            },
	    	error: function(error) {
	            alert("delete locks errored"); 
			}
	 };
    ofwat.showMessage("Deleting locks... <img border='0' src='../../images/loader.gif'/>");
	 var deferred = dojo.xhrDelete(xhrArgs);
};





ofwat.admin.initialise = function(){
	ofwat.admin.showLockedItems();
};


ofwat.admin.initialise();