/*
 *  Copyright (C) 2013 Water Services Regulation Authority (Ofwat)
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

ofwat.reportPreview = {
};

ofwat.reportPreview.generatePreview = function(){

	var leftList = dojo.byId("layoutLeftList"); 
	var topList = dojo.byId("layoutTopList");
	if(leftList.options.length < 1){
		alert("You must have at least one row criteria");
		return;
	}
	if(topList.options.length < 1){
		alert("You must have at least one column criteria");
		return;
	}
	var elPreviewDiv = dojo.byId("preview");
	dojo.empty(elPreviewDiv);
	// draw a little preview
	var table = dojo.place("<table style=\"padding:10px\"></table>", elPreviewDiv);
	var tBody = dojo.place("<tbody></tbody>", table);
	if(leftList.options.length == 2 && topList.options.length == 1){
		// 1 col criteria and two row criteria
		var firstLeftCriteria = leftList.options[0].text;
		var secondLeftCriteria = leftList.options[1].text;
		var topCriteria = topList.options[0].text;
		
		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, topCriteria, tr, 2, 0);

		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 1, secondLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 2, secondLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 3, secondLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 3);
	}
	else if(leftList.options.length == 1 && topList.options.length == 2){
		// 1 criteria in rows and 2 in columns
		var firstTopCriteria = topList.options[0].text;
		var secondTopCriteria = topList.options[1].text;
		var firstLeftCriteria = leftList.options[0].text;
		
		var tr = dojo.place("<tr></tr>", tBody);
		var td = dojo.place("<td></td>", tr, "first");

		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, firstTopCriteria, tr, 1, 2);
		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 1, 0);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 0, 0);

		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 1, 9);
		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 2, 9);
		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 3, 9);
	}
	else if(leftList.options.length == 3 && topList.options.length == 1){
		// 1 col criteria and 3 row criteria
		var firstLeftCriteria = leftList.options[0].text;
		var secondLeftCriteria = leftList.options[1].text;
		var thirdLeftCriteria = leftList.options[2].text;
		var topCriteria = topList.options[0].text;

		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, topCriteria, tr, 3, 0);

		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, firstLeftCriteria, 1, secondLeftCriteria, 1, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 2, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 3, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, firstLeftCriteria, 2, secondLeftCriteria, 1, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 2, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 3, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, firstLeftCriteria, 3, secondLeftCriteria, 1, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 2, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", secondLeftCriteria, 3, thirdLeftCriteria, 1, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 2, 3);
		ofwat.reportPreview.addDataRowForThreeLeftHeaders(tBody, "", "", "", "", thirdLeftCriteria, 3, 3);
	}
	else if(leftList.options.length == 1 && topList.options.length == 3){
		// 3 col criteria and 1 row criteria
		var firstLeftCriteria = leftList.options[0].text;
		var firstTopCriteria = topList.options[0].text;
		var secondTopCriteria = topList.options[1].text;
		var thirdTopCriteria = topList.options[2].text;

		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, firstTopCriteria, tr, 1, 8);
		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 1, 2);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 2, 2);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 2, 2);
		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 1, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, thirdTopCriteria, tr, 0, 0);

		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 1, 27);
		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 2, 27);
		ofwat.reportPreview.addDataRowForOneLeftHeader(tBody, firstLeftCriteria, 3, 27);
	}
	else if(leftList.options.length == 2 && topList.options.length == 2){
		// 2 col criteria and 2 row criteria
		var firstLeftCriteria = leftList.options[0].text;
		var secondLeftCriteria = leftList.options[1].text;
		var firstTopCriteria = topList.options[0].text;
		var secondTopCriteria = topList.options[1].text;

		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, firstTopCriteria, tr, 2, 2);
		var tr = dojo.place("<tr></tr>", tBody);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 2, 0);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 0, 0);
		ofwat.reportPreview.addTopRow(tBody, secondTopCriteria, tr, 0, 0);

		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 1, secondLeftCriteria, 1, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 2, secondLeftCriteria, 1, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, firstLeftCriteria, 3, secondLeftCriteria, 1, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 2, 9);
		ofwat.reportPreview.addDataRowForTwoLeftHeaders(tBody, "", "", secondLeftCriteria, 3, 9);
	}
};
	
ofwat.reportPreview.addTopRow = function(tBody, topCriteria, tr, offset, spacing) {
	for (i=0; i<offset; i++){
		dojo.place("<td></td>", tr, "last");
	}
	dojo.place("<td>" + topCriteria + "1</td>", tr, "last");
	for (i=0; i<spacing; i++){
		dojo.place("<td></td>", tr, "last");
	}
	dojo.place("<td>" + topCriteria + "2</td>", tr, "last");
	for (i=0; i<spacing; i++){
		dojo.place("<td></td>", tr, "last");
	}
	dojo.place("<td>" + topCriteria + "3</td>", tr, "last");
};
	
	
ofwat.reportPreview.addOneLeftHeader = function(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount) {
	td = dojo.place("<td>" + firstLeftCriteria + firstLeftCriteriaCount +"</td>", tr, "last");
};

ofwat.reportPreview.addTwoLeftHeaders = function(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount) {
	td = dojo.place("<td>" + firstLeftCriteria + firstLeftCriteriaCount +"</td>", tr, "last");
	td = dojo.place("<td>" + secondLeftCriteria + secondLeftCriteriaCount +"</td>", tr, "last");
};

ofwat.reportPreview.addThreeLeftHeaders = function(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount, thirdLeftCriteria, thirdLeftCriteriaCount) {
	td = dojo.place("<td>" + firstLeftCriteria + firstLeftCriteriaCount +"</td>", tr, "last");
	td = dojo.place("<td>" + secondLeftCriteria + secondLeftCriteriaCount +"</td>", tr, "last");
	td = dojo.place("<td>" + thirdLeftCriteria + thirdLeftCriteriaCount +"</td>", tr, "last");
};

ofwat.reportPreview.addDataRowForOneLeftHeader = function(tBody, firstLeftCriteria, firstLeftCriteriaCount, noOfDataCells) {
	var tr = dojo.place("<tr></tr>", tBody);
	ofwat.reportPreview.addOneLeftHeader(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount);
	for (i=0; i<noOfDataCells; i++) {
		td = dojo.place("<td bgcolor='#DDDDDD'>data</td>", tr, "last");
	}
};

ofwat.reportPreview.addDataRowForTwoLeftHeaders = function(tBody, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount, noOfDataCells) {
	var tr = dojo.place("<tr></tr>", tBody);
	ofwat.reportPreview.addTwoLeftHeaders(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount);
	for (i=0; i<noOfDataCells; i++) {
		td = dojo.place("<td bgcolor='#DDDDDD'>data</td>", tr, "last");
	}
};

ofwat.reportPreview.addDataRowForThreeLeftHeaders = function(tBody, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount, thirdLeftCriteria, thirdLeftCriteriaCount, noOfDataCells) {
	var tr = dojo.place("<tr></tr>", tBody);
	ofwat.reportPreview.addThreeLeftHeaders(tBody, tr, firstLeftCriteria, firstLeftCriteriaCount, secondLeftCriteria, secondLeftCriteriaCount, thirdLeftCriteria, thirdLeftCriteriaCount);
	for (i=0; i<noOfDataCells; i++) {
		td = dojo.place("<td bgcolor='#DDDDDD'>data</td>", tr, "last");
	}
};
