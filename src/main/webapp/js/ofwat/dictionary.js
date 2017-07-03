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
ofwat.dictionary = {
};


//ofwat.dictionary.showDictionary = function (oDataDto, divId, legacy, node){
ofwat.dictionary.showDictionary = function (params){
	
	//Ok forget the dictionary pane lets create a pop-up to the right and put the data in there!
	var dictionaryPane = dojo.byId("dictionary");
		
	var varItem = params.dto.item;
	var varItemProps = params.dto.itemPropertiesDto;
	
	// TODO - get some values for these
	var currentDiv
	if(params.node){
		currentDiv = params.node;
	}else{
		currentDiv = dojo.byId(params.divId);	
	}
	
	var ruleText;
	var cellType;
	var lineNum;
	// find the lineNum
	if(currentDiv){
		var tdNode = currentDiv.parentNode;
		if(dojo.hasClass(tdNode, "calc")){
			cellType = "Calculated";
		}
		else if(dojo.hasClass(tdNode, "copy")){
			cellType = "Copy";
		}
		else if(dojo.hasClass(tdNode, "input")){
			cellType = "Input";	
		}
		dojo.query("input.ruletext", tdNode).forEach(function(inputNode){
			ruleText = inputNode.value;
		});

		
		var trNode = tdNode.parentNode; // move up from div to td to tr
		// now search it's siblings for a tr with class lineNum
		dojo.query("td.lineNumber", trNode).forEach(function(lineNumNode){
			lineNum = lineNumNode.innerHTML;
		});
	}
	
	var txt="<table class='dictionary space'>";

	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>"+ofwat.escapeHTML(varItem.code)+"</th></tr>";
	if(lineNum){
		txt=txt+"<tr><td>Line Number</td><td>"+lineNum+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Line Number</td><td></td></tr>";
	}
	txt=txt+"<tr><td>Interval</td><td>"+ofwat.escapeHTML(params.dto.intervalDto.name)+"</td></tr>";
	if(cellType){
		txt=txt+"<tr><td>Cell Type</td><td>"+cellType+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Cell Type</td><td></td></tr>";
	}
	txt=txt+"<tr><td>Formula</td><td>"+ofwat.escapeHTML(params.dto.formula)+"</td></tr>";

	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Details</td></tr>";
	txt=txt+"<tr><td>Description</td><td>"+ofwat.escapeHTML(varItemProps.description)+"</td></tr>";
	if(varItemProps.purpose){	
		txt=txt+"<tr><td>Purpose</td><td>"+ofwat.escapeHTML(varItemProps.purpose)+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Purpose</td><td></td></tr>";
	}
	if(ruleText){
		txt=txt+"<tr><td>Rule Text</td><td>"+ruleText+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Rule Text</td><td></td></tr>";
	}
	if(varItemProps.definition){
		txt=txt+"<tr><td>Definition</td><td>"+ofwat.escapeHTML(varItemProps.definition)+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Definition</td><td></td></tr>";
	}
	if(varItemProps.priceBase){
		txt=txt+"<tr><td>Price Base</td><td>"+ofwat.escapeHTML(varItemProps.priceBase)+"</td></tr>";
	}
	else{
		txt=txt+"<tr><td>Price Base</td><td></td></tr>";
	}
	txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Format</td></tr>";
	txt=txt+"<tr><td>Unit</td><td>"+ofwat.escapeHTML(varItem.unit)+"</td></tr>";
	txt=txt+"<tr><td>Decimal Places</td><td>"+ofwat.escapeHTML(varItemProps.decimalPlaces)+"</td></tr>";
	
	txt=txt+"</table>";
	
	//We want to show the legacy data dictionary here.
	if(params.legacy == true){
		dictionaryPane.style.display = "inline";	
		dictionaryPane.innerHTML=txt;
	}else{
		return txt;
	}
	//dictionaryPane.innerHTML=txt;
	/*
	$('#'+divId).popover({
		placement:"right", 
		html: true,
		content: txt
	});
	*/
	//$('#'+divId).popover('show');
	
	
};

//ofwat.dictionary.showAudits = function(identifier, dictionaryHtml, callback, legacy) {
ofwat.dictionary.showAudits = function(params) {
	
	var xhrArgs = {
			url: "/Fountain/rest-services/audits/" + params.divId,
			handleAs: "json",
			headers: {"accept": "application/json"},
			load: function(data){
				var txt = ofwat.dictionary.populateAudit(data, params.legacy);
				if(params.legacy != true){
					var infoHtml = params.dictionaryHtml;
					if(txt != null){
						infoHtml = infoHtml + txt;
					}
					params.callback(infoHtml);
				}
				ofwat.showMessage("");
			},
			error: function(error) {
				ofwat.showError("Problem loading audits " + error +")");
			}
	};
	ofwat.showMessage("Loading audits <img border='0' src='/Fountain/images/loader.gif'/>");
	var deferred = dojo.xhrGet(xhrArgs);
};

ofwat.dictionary.populateAudit = function(audits, legacy) {
	var auditPane = dojo.byId("audit");
	if (audits.length >0) {
		
		var txt="<table class='dictionary space'>";
		txt=txt+"<tr><td class='dictionaryHeader' colspan=2>Audits</td></tr>";
		
		for (var i=0; i<audits.length; i++) {
			txt=txt+"<tr>";
			txt=txt+"<td class='dictionaryHeader'>Audit Id.</td>";
			txt=txt+"<td class='dictionaryHeader'>" + ofwat.escapeHTML(audits[i].id) + "</td>";
			txt=txt+"</tr>";
			txt=txt+"<tr>";
			txt=txt+"<td>Date</td>";
			txt=txt+"<td>" + ofwat.escapeHTML(audits[i].date) + "</td>";
			txt=txt+"</tr>";
			txt=txt+"<tr>";
			txt=txt+"<td>Name</td>";
			txt=txt+"<td>" + ofwat.escapeHTML(audits[i].user.name) + "</td>";
			txt=txt+"</tr>";
			txt=txt+"<tr>";
			txt=txt+"<td>Value</td>";
			txt=txt+"<td>" + ofwat.escapeHTML(audits[i].value) + "</td>";
			txt=txt+"</tr>";
			if (audits[i].cg){
				txt=txt+"<tr>";
				txt=txt+"<td>CG</td>";
				txt=txt+"<td>" + ofwat.escapeHTML(audits[i].cg) + "</td>";
				txt=txt+"</tr>";
			}
			txt=txt+"<tr>";
			txt=txt+"<td>Comment</td>";
			txt=txt+"<td>";
			txt=txt+ofwat.escapeHTML(audits[i].comment);
			txt=txt+"</td>";
			txt=txt+"</tr>";
			if (audits[i].runName) {
				txt=txt+"<tr>";
				txt=txt+"<td>Run</td>";
				txt=txt+"<td>";
				txt=txt+ofwat.escapeHTML(audits[i].runName);
				txt=txt+"</td>";
				txt=txt+"</tr>";
			}
		}
		txt=txt+"</table>";
		if(legacy == true){
			auditPane.style.display = "inline";
			auditPane.innerHTML=txt;
		}
	} else {
		if(legacy == true){
			auditPane.innerHTML="";
		}
	}
	return txt;
	//ofwat.dictionary.showInfo();
};

ofwat.dictionary.showAllInfo = function(html, divId, node){
	//alert("Show all info" + html);
	var selDiv = $(node);
	
	//Clear all popovers. 
	//iterate over all and if it isnt selDiv then remove the popover! 
	$.each($('.po'), function(index, value){
		if($(value).attr('id') != selDiv.attr('id')){
			$(value).popover('hide');
		}
	});
		
	//if it's not a popover then make it one and show!
	if(!selDiv.hasClass('po')){
		selDiv.popover({
			placement:"rightTop", 
			html: true,
			content: html,
			animation: false
		}).popover('show');
		selDiv.addClass('po');
	}else{
		if(selDiv.hasClass('po')){
			selDiv.popover('show');
		}
	}
}

//We need to also set up a key press listener for tabs to clear any existing dictionary popovers. 
//$(document).keypress(function(event) {
	//console.log(event.keyCode);
//});

ofwat.dictionary.clearPopovers = function(){
	var popOvers = $('.po');
	//remove the others!
	$.each(popOvers, function(index, value){		
		$(value).popover('hide');
		$(value.parentNode).removeClass("highlight");
	})
}

//Helper function to clear an audit if you click anywhere but in the audit.
/*
$(document).click(function(event) {
	ofwat.dictionary.clearPopovers();
});
*/


