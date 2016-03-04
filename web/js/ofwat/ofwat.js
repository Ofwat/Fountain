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
// Just define the ofwat object. Can add top level attributes and methods

var ofwat = {};
	
ofwat.escapeHTML = function(inputString) {
	var div = document.createElement('div');
	var text = document.createTextNode(inputString);
	div.appendChild(text);
	return div.innerHTML;
};


ofwat.getQueryVariable = function(variable) {
	  var query = window.location.search.substring(1);
	  var vars = query.split("&");
	  for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=");
	    if (pair[0] == variable) {
	      return pair[1];
	    }
	  } 
	  return null;
};

ofwat.showError = function(error, message){
	if (typeof $ !== 'undefined'){
		var responses = $("[id=response]");
		responses.each(function(index, response){
			$(response).addClass("error");
			response.innerHTML = message + "(Error: " + error.responseText +")";	
		});
		return responses[0];
	}else{
		var response = dojo.byId("response");
		var responseOuter = dojo.byId("responseOuter");
		if(response && responseOuter){
			dojo.addClass(response, "error");
			dojo.addClass(responseOuter, "alert");
			dojo.addClass(responseOuter, "alert-error");
			response.innerHTML = message + "(Error: " + error.responseText +")";
		}
		else if (response) {
			dojo.addClass(response, "error");
			response.innerHTML = message + "(Error: " + error.responseText +")";
		}
		return response;
	}
};

ofwat.showMessage = function(message){
	//Using jquery to select all divs with class response. 
	if (typeof $ !== 'undefined'){
		var responses = $("[id=response]");
		responses.each(function(index, response){
			$(response).removeClass("error");
			response.innerHTML = message;		
		});
		return responses[0];
	}else{
		var response = dojo.byId("response");
		if (response){
			dojo.removeClass(response, "error");
			response.innerHTML = message;
		}
		return response;
	}
};

// Loader for banner menu
ofwat._initLoader = function () {
	/*
	var userMenu = dojo.byId("userMenu");
	
	// user menu special effects
	if (userMenu) {
		dojo.query(".dropdown").forEach(function(n) {
	    	var l = dojo.query(n);
	        l.parent().at(0)
				.onmouseenter(function(){
					dojo.style(l[0], "left", "0");
					var wipeIn = new dojo.fx.wipeIn({node: l[0], duration: 500});
					wipeIn.play();
				})
				.onmouseleave(function(){
					dojo.style(l[0], "left", "-999em");
					dojo.style(l[0], "display", "none");
				});
	    });
    }
    */
};  

ofwat.toggleRightPane = function(hideDiv, showDiv, show) {
//	// get all the children from these divs and use the dojo foreach thing
//	 var div1 = dojo.query("."+indiv).children();
//	 
//	 div1.forEach(function(n) {
//			dojo.fadeIn({node : n, duration: 50}).play();
//	 });
//	 var div2 = dojo.query("."+outdiv).children();
//	 div2.forEach(function(n) {
//		dojo.fadeOut({node : n, duration: 50}).play();
//	 });		
	dojo.removeClass(hideDiv, "show");
	dojo.addClass(hideDiv, "invisible");
	dojo.removeClass(showDiv, "invisible");
	dojo.addClass(showDiv, "show");
	var menu = dijit.byId('rightMenu');
	if (menu) {
		if (show) {
			menu.domNode.style.display = 'block';
		} else {
			menu.domNode.style.display = 'none';
		}
		menu.resize();
	}
};

ofwat.hideButton = function (button, msg, fileInput){
	dojo.style(button, "display", "none");
	// hide the other buttons too...
	dojo.query('input').style("display", "none");
	var message = "";
	if(msg=='bulk'){
		message = "Doing " + msg + " upload.";
	} else {
		message = "Uploading " + dojo.byId(fileInput).value;
	}
	
	ofwat.showMessage( message + " <img border='0' src='../../images/loader.gif'/>");
};

ofwat.createCookie = function(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
};

ofwat.readCookie = function(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) {
			if (c.substring(nameEQ.length,c.length) == "null") {
				return null;
			}
			return c.substring(nameEQ.length,c.length);
		}
	}
	return null;
};

ofwat.eraseCookie = function(name) {
	ofwat.createCookie(name,"",-1);
};

/**
 * Call to dynamically get and inject a template in a page.
 * node - The DOM node to inject it in. 
 * config - The config block containing details about the template + callback. 
 * params -  Optional params.
 **/
ofwat.loadTemplate = function(node, config, params) {
	var source;
	var template;
	var templateData;
	var node = node;
	var config = config;

	//Break into a function
	if (config.dataUrl) {
		var dataUrl = config.dataUrl;
		if(params && params.url){
			dataUrl = dataUrl + params.url;
		}
		var xhrArgsTemplateData = {
			url: dataUrl,
			sync: true,
			handleAs: "json",
			preventCache: true,
			headers: {"Content-Type": "application/json"},
			load: function(data){
				templateData = data;
				config.dataCallback(data);
			},
			error: function(error) {
				ofwat.showError("Problem getting template data " + error);
			}
		};
		dojo.xhrGet(xhrArgsTemplateData); //Sync true
	}
	//See if we are just passing the data and not getting it from a resource
	if(params && params.data){
		templateData = data;
		config.dataCallback();
	}

	//Do the actual templating - is it an inline template or do we need to get it?
	if(config.inline){
		var tmp = document.createElement("div");
		tmp.appendChild(dojo.clone(node));
		ofwat.doTemplate(tmp.innerHTML, templateData, node, "replace");
		config.templateCallback();		
	}else{
		var xhrArgsTemplate = {
				url: "/Fountain/jsp/protected/templates/" + config.name + ".html",
				handleAs: "text",
				preventCache: true,
				sync: true,
				load: function(data){
					ofwat.doTemplate(data, templateData, node, "replace");
					config.templateCallback();
				},
				error: function(error) {
					ofwat.showError("Problem loading template " + error);
				}
		};
		var deferred = dojo.xhrGet(xhrArgsTemplate);
	}
};

/*
	Helper function to do the actual templating. 
*/
ofwat.doTemplate = function (templateSource, templateData, node, action){
	template = Handlebars.compile(templateSource);
	var newNode = dojo.create("div");
	newNode.innerHTML = template(templateData);
	var oldNode = dojo.query(node)[0];
	dojo.place(newNode.innerHTML, oldNode, action);
	var x = newNode.getElementsByTagName("script");   
	for (var i=0;i<x.length;i++) {  
		eval(x[i].text);  
	} 
};


ofwat.registerPartials = function(partials){
	//iterate through each of the partials and call Handlebars.registerPartial with the html of the
	//the template. 
	for(var i=0;i<partials.length;i++){
		var partial = partials[i];
		//Get the html for the template.
		var xhrArgsPartial = {
				url: "/Fountain/jsp/protected/templates/partials/" + partial + ".html",
				handleAs: "text",
				preventCache: true,
				sync: true,
				load: function(data){
					Handlebars.registerPartial(partial, data);
				},
				error: function(error) {
					ofwat.showError("Problem loading template " + error);
				}
		};
		var deferred = dojo.xhrGet(xhrArgsTemplate);		
	}
}

//Declaration of the templates we are using. 
ofwat.template = {
	banner:{
		name:"banner",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/banner"
	},
	footer:{
		name:"footer",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){}
	},
	reportEditButtons:{
		name:"reportEditButtons",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/banner",
		inline:true
	},
	bannerTheme:{
		name:"bannerTheme",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/banner",
		inline:true
	},
	runList:{
		name:"runList",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/runs?standardRole=true&defaultRole=true"
	},
	runTemplateList:{
		name:"runTemplateList",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/runs/templates"
	},	
	runCompanyTemplateList:{
		name:"runCompanyTemplateList",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/runs/companyTemplate"
	},	
	editRun:{
		name:"editRun",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/runs"		
	},	
	dragSelect:{
		name:"dragSelect",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){}
	},
	externalModelList:{
		name:"modelList",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/model?typeId=5"
	},
	updateList:{
		name:"updateList",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/release"
	},
	viewRelease:{
		name:"viewRelease",
		dataCallback:function(){},
		templateCallback:function(){},
		getPostData:function(){},
		dataUrl:"/Fountain/rest-services/release/"
	}	
};


/*
	Handlebars helper function to provide 'inRole' functionality.
*/
ofwat.checkUserInRole = function(testRoles, roles, options){
	//if they are in 'this.roles'
	var y = 0;
	testRoles = testRoles.split(",");
	while(y<testRoles.length){
		var i = 0;
		while(i<Object.keys(roles).length){
			if(roles[testRoles[y].replace(/^\s+|\s+$/g, '')] == true){
				//console.log("user:" + this.remoteUserName + " is in role " + testRoles[y]);
			    return options.fn(this);
			}
			i++;
		}
		y++;
	}
};

/*
Handlebars helper function to provide 'forEach' with 'last' functionality.
*/
ofwat.forEachHelper = function(arr,options) {
    if(options.inverse && !arr.length)
        return options.inverse(this);

    return arr.map(function(item,index) {
        item.$index = index;
        item.$first = index === 0;
        item.$last  = index === arr.length-1;
        return options.fn(item);
    }).join('');
};

ofwat.dateHelper = function(currentValue) {
	var date = moment(new Date(currentValue)).format('DD/MMM/YYYY h:mm A');
	return date
};

ofwat.checkedHelper = function(currentValue) {
    return currentValue == '1' ? ' checked="checked"' : '';
};

ofwat.disabledHelper = function(currentValue) {
    return currentValue == '1' ? ' disabled="true"' : '';
};

ofwat.defaultRunHelper = function(currentValue) {
    return currentValue == 'DEFAULT' ? ' checked="checked"' : '';
};

ofwat.formattedTimeAndDate = function(){
	//TODO This has an implied dependency on moment.js! Need a module loader for the JS to resolve some of these issues. 
	return moment().format('DD/MMM/YYYY h:mm A');
};

ofwat.showLoading = function(nodeToHide, options){
	var $node = $(nodeToHide);
	$node.hide();
	//Append a new Node with the loading screen. 
	var $waitingNode = $('<div id="loadingReportBuilder" style="width: 100%; background-color: red; height: 500px;"></div>');
	//Spinner and loading message
	$node.after($waitingNode);	
}

ofwat.hideLoading = function(nodeToShow, options){
	var $waitingNode = $('#loadingReportBuilder');
	$waitingNode.hide();
	$(nodeToShow).show();
}

ofwat.wikiPage = function(){
	var xhrArgs = {
        url: "/Fountain/rest-services/wikiAddress",
        handleAs: "json",
		sync: true,
        headers: {"accept": "application/json"},
        load: function(data){
			ofwat.showWikiLink(data);
			ofwat.showMessage("");
		},
        error: function(error) {
            ofwat.showError("get wiki address errored", error);
		}
    };
	ofwat.showMessage("Loading company... <img border='0' src='/Fountain/images/loader.gif'/>");
    dojo.xhrGet(xhrArgs);
};


ofwat.showWikiLink = function (data){
	var wikiHelpLinkDiv = dojo.query("#wikiHelpLink")[0];
	//wikiHelpLinkDiv.innerHTML = "<a href=\""+data.uri+helpPage + ".aspx\" target=\"_blank\"><img alt=\"help\" src=\"/Fountain/images/help.png\" title=\"Help\" border=0></img></a>";
	wikiHelpLinkDiv.innerHTML = "<a href=\""+data.uri+helpPage + ".aspx\" target=\"_blank\"><i style=\"color:white;\" class=\"fa fa-question-circle fa-lg\"></i></a>";
};


