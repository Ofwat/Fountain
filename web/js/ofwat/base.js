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


//top level 'package' object
var Ofwat = {
		// keep track of modules
		_loadedModules:  [],	
		_inFlightCount: 0,
		_hasResource:  {},
		_loadedUrls:   []
};

//add some behaviour

Ofwat._moduleHasPrefix = function(/*String*/module){
	// summary: checks to see if module has been established
	var mp = Ofwat._modulePrefixes;
	return !!(mp[module] && mp[module].value); // Boolean
};

Ofwat._getModulePrefix = function(/*String*/module){
	// summary: gets the prefix associated with module
	var mp = Ofwat._modulePrefixes;
	if(Ofwat._moduleHasPrefix(module)){
		return mp[module].value; // String
	}
	return module; // String
};

Ofwat._getModuleSymbols = function(/*String*/modulename){
	// summary:
	//		Converts a module name in dotted JS notation to an array
	//		representing the path in the source tree
	var syms = modulename.split(".");
	for(var i = syms.length; i>0; i--){
		var parentModule = syms.slice(0, i).join(".");
		if(i == 1 && !Ofwat._moduleHasPrefix(parentModule)){		
			// Support default module directory (sibling of dojo) for top-level modules 
			syms[0] = "../" + syms[0];
		}else{
			var parentModulePath = Ofwat._getModulePrefix(parentModule);
			if(parentModulePath != parentModule){
				syms.splice(0, i, parentModulePath);
				break;
			}
		}
	}
	return syms; // Array
};

Ofwat._loadUri = function(/*String*/uri, /*Function?*/cb){
	//	summary:
	//		Loads JavaScript from a URI
	//	description:
	//		Reads the contents of the URI, and evaluates the contents.  This is
	//		used to load modules as well as resource bundles. Returns true if
	//		it succeeded. Returns false if the URI reading failed.  Throws if
	//		the evaluation throws.
	//	uri: a uri which points at the script to be loaded
	//	cb: 
	//		a callback function to process the result of evaluating the script
	//		as an expression, typically used by the resource bundle loader to
	//		load JSON-style resources

	if(Ofwat._loadedUrls[uri]){
		return true; // Boolean
	}
	Ofwat._inFlightCount++; // block addOnLoad calls that arrive while we're busy downloading
	var contents = Ofwat._getText(uri, true);
	if(contents){ // not 404, et al
		Ofwat._loadedUrls[uri] = true;
		Ofwat._loadedUrls.push(uri);
		if(cb){
			contents = '('+contents+')';
		}else{
			//Only do the scoping if no callback. If a callback is specified,
			//it is most likely the i18n bundle stuff.
			contents = Ofwat._scopePrefix + contents + Ofwat._scopeSuffix;
		}
		if(!Ofwat.isIE){ contents += "\r\n//@ sourceURL=" + uri; } // debugging assist for Firebug
		var value = Ofwat["eval"](contents);
		if(cb){ cb(value); }
	}
	// Check to see if we need to call _callLoaded() due to an addOnLoad() that arrived while we were busy downloading
	if(--Ofwat._inFlightCount == 0 && Ofwat._postLoad && Ofwat._loaders.length){
		// We shouldn't be allowed to get here but Firefox allows an event 
		// (mouse, keybd, async xhrGet) to interrupt a synchronous xhrGet. 
		// If the current script block contains multiple require() statements, then after each
		// require() returns, inFlightCount == 0, but we want to hold the _callLoaded() until
		// all require()s are done since the out-of-sequence addOnLoad() presumably needs them all.
		// setTimeout allows the next require() to start (if needed), and then we check this again.
		setTimeout(function(){ 
			// If inFlightCount > 0, then multiple require()s are running sequentially and 
			// the next require() started after setTimeout() was executed but before we got here.
			if(Ofwat._inFlightCount == 0){ 
				Ofwat._callLoaded();
			}
		}, 0);
	}
	return !!contents; // Boolean: contents? true : false
};

Ofwat._loadUriAndCheck = function(/*String*/uri, /*String*/moduleName, /*Function?*/cb){
	// summary: calls loadUri then findModule and returns true if both succeed
	var ok = false;
	try{
		ok = Ofwat._loadUri(uri, cb);
	}catch(e){
		console.error("failed loading " + uri + " with error: " + e);
	}
	return !!(ok && Ofwat._loadedModules[moduleName]); // Boolean
};


Ofwat._loadPath = function(/*String*/relpath, /*String?*/module, /*Function?*/cb){
	// 	summary:
	//		Load a Javascript module given a relative path
	//
	//	description:
	//		Loads and interprets the script located at relpath, which is
	//		relative to the script root directory.  If the script is found but
	//		its interpretation causes a runtime exception, that exception is
	//		not caught by us, so the caller will see it.  We return a true
	//		value if and only if the script is found.
	//
	// relpath: 
	//		A relative path to a script (no leading '/', and typically ending
	//		in '.js').
	// module: 
	//		A module whose existance to check for after loading a path.  Can be
	//		used to determine success or failure of the load.
	// cb: 
	//		a callback function to pass the result of evaluating the script

	var uri = ((relpath.charAt(0) == '/' || relpath.match(/^\w+:/)) ? "" : Ofwat.baseUrl) + relpath;
	try{
		return !module ? Ofwat._loadUri(uri, cb) : Ofwat._loadUriAndCheck(uri, module, cb); // Boolean
	}catch(e){
		console.error(e);
		return false; // Boolean
	}
};

Ofwat._callLoaded = function(){

	// The "object" check is for IE, and the other opera check fixes an
	// issue in Opera where it could not find the body element in some
	// widget test cases.  For 0.9, maybe route all browsers through the
	// setTimeout (need protection still for non-browser environments
	// though). This might also help the issue with FF 2.0 and freezing
	// issues where we try to do sync xhr while background css images are
	// being loaded (trac #2572)? Consider for 0.9.
	if(typeof setTimeout == "object" || (Ofwat.config.useXDomain && Ofwat.isOpera)){
		setTimeout(
				Ofwat.isAIR ? function(){ Ofwat.loaded(); } : Ofwat._scopeName + ".loaded();",
			0);
	}else{
		Ofwat.loaded();
	}
}

Ofwat.require = function(name){
//	do some loading
	alert("Ofwat.require called with "+ name);
	
	
	var module = Ofwat._loadedModules[moduleName];
	if(module){
		return module;
	}
	
	var relpath = Ofwat._getModuleSymbols(moduleName).join("/") + '.js';

	var modArg = !omitModuleCheck ? moduleName : null;
	var ok = Ofwat._loadPath(relpath, modArg);

};