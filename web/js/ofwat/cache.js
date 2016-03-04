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
if(!ofwat.rest){
	alert("missing import: rest.js");
}
if(!ofwat.listUtils){
	alert("missing import: listUtils.js");
}


ofwat.cache = {};

ofwat.cache.showCacheLevels = function(){
	var xhrArgs = {
			url: "/Fountain/rest-services/cache/levels",
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				ofwat.showMessage("");
				ofwat.cache.populateCacheLevels(data);
			}
	};
	ofwat.showMessage("loading cache levels... <img border='0' src='../../images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.cache.populateCacheLevels = function(data){
	var daoCacheTable = dojo.byId("daoCacheTable");
	dojo.empty("daoCacheTable");
	
	var txt="<table class='reportType'>";
	txt=txt+"<tr><th colspan='3'>Dao Cache Levels</td></tr>";
	txt=txt+"<tr><th>Cache Name</th><th>Target Name</th><th>Percentage used</th></tr>";
	
	for (var i=0; i<data.length; i++) {
		txt=txt+"<tr>";
		var tdClass = "reportListOdd";
		if ((i % 2) == 0) {
			tdClass = "reportListEven";
		}
		txt=txt+"<td class='" + tdClass + "'>" + ofwat.escapeHTML(data[i].cacheName) + "</td>";
		txt=txt+"<td class='" + tdClass + "'>" + ofwat.escapeHTML(data[i].targetName) + "</td>";
		txt=txt+"<td class='" + tdClass + "'>" + ofwat.escapeHTML(data[i].percentageFull) + "%</td>";
		txt=txt+"</tr>";
	}
	txt=txt+"</table>";
	daoCacheTable.innerHTML=txt;	
	
};

ofwat.cache.initialise = function(){
	ofwat.cache.showCacheLevels();
};


ofwat.cache.initialise();