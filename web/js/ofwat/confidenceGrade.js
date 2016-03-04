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

ofwat.confidenceGrade = {
	maoCGs: null,
	xmlhttpGetConfidenceGrades: {}
};

ofwat.confidenceGrade.getCGs = function() {
	return ofwat.confidenceGrade.maoCGs;
};


/*	==============================
 *	Hidden functions
 *	==============================
 */
ofwat.confidenceGrade.cg_setupCGs = function() {
	// Not yet read so get them now
	ofwat.confidenceGrade.maoCGs = new Array;
	ofwat.confidenceGrade.xmlhttpGetConfidenceGrades=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if (this.xmlhttpGetConfidenceGrades!=null)
	  {
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.onreadystatechange=ofwat.confidenceGrade.cg_populateCGs;
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.open("GET", "/Fountain/rest-services/confidencegrades", true);
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.setRequestHeader("accept", "application/json");
		ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.send(null);
	  }
	else
	  {
	  	alert("Your browser does not support XMLHTTP.");
	  }
};

/*	===========================
 * 	Build the confidence grades
 *	===========================
 */
ofwat.confidenceGrade.cg_populateCGs = function()
{
	if(ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.readyState!=4) return;
	if(ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.status!=200) {
		ofwat.rest.handleError(ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.responseXML);
	  	return;
	}
	var aoRet = JSON.parse(ofwat.confidenceGrade.xmlhttpGetConfidenceGrades.responseText);

	var j = 0;
	for (var i=0; i<aoRet.length; i++) {
		if (!aoRet[i].redundant) {
			ofwat.confidenceGrade.maoCGs[j] = aoRet[i];
			j++;
		}
	}
};

ofwat.confidenceGrade.cg_setupCGs();