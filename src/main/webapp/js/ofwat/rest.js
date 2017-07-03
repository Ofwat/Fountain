// *****************************
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
// ** Generic Rest functions  **
// *****************************

// dependency check
if(!ofwat){
	alert("missing import: ofwat.js");
}

// add restful functions to the ofwat.rest 'package'

// page level variables

ofwat.rest = {
	xmlhttp: {}
};


ofwat.rest.loadJSON = function (url, handler)
{
	this.xmlhttp=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Mozilla, etc.
	 	 this.xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE5, IE6
	 	 this.xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if (this.xmlhttp!=null)
	  {
		  this.xmlhttp.onreadystatechange=handler;
		  this.xmlhttp.open("GET",url,true);
		  this.xmlhttp.setRequestHeader("accept", "application/json");
		  this.xmlhttp.send(null);
	  }
	else
	  {
	  	alert("Your browser does not support XMLHTTP.");
	  }
};


ofwat.rest.handleError = function (xmlErrorResponse){
	
	// code to parse out the error message
	// currently spits these out to the client
	// but could do something nicer like write a little 
	// error message at the top of the page?
	// have an error <div> at the top of EVERY page, then can just slot the salient info in.
	var type = xmlErrorResponse.getElementsByTagName("type")[0].firstChild.nodeValue;
	var message = xmlErrorResponse.getElementsByTagName("message")[0].firstChild.nodeValue;
	var trace = xmlErrorResponse.getElementsByTagName("trace")[0].firstChild.nodeValue;
	
	alert ("error type = " + type);
	alert ("error message = " + message);
	alert ("error trace = " + trace);
};