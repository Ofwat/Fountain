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
ofwat.keyUtils = {};

ofwat.keyUtils.parseToKeyStruct = function(keyString) {
	var keyStruct = {};
	keyStruct.CG = false;
	if (0 == keyString.indexOf("CG")) {
		keyStruct.CG = true;
		keyString = keyString.substring(2);
	}
	var keys = keyString.split("-");

	for ( var idx in keys) {
		var key = new String(keys[idx]);
		var code = key.substring(0, 1);
		switch (code) {
		case 'c':
			keyStruct.companyId = key.substring(1);
			break;
		case 'i':
			keyStruct.itemId = key.substring(1);
			break;
		case 'p':
			keyStruct.itemPropertiesId = key.substring(1);
			break;
		case 'g':
			keyStruct.groupEntryId = key.substring(1);
			break;
		case 'm':
			keyStruct.modelId = key.substring(1);
			break;
		case 'y':
			keyStruct.intervalId = key.substring(1);
			break;
        case 'r':
            keyStruct.runId = key.substring(1);
            break;
        case 't':
            keyStruct.tagId = key.substring(1);
            break;
		}
	}
	keyStruct.noGroupEntry = function() {
		return (keyStruct.CG ? "CG" : "") + 
				"i" + keyStruct.itemId + 
				"-p" + keyStruct.itemPropertiesId + 
				"-y" + keyStruct.intervalId +
				(keyStruct.companyId ? "-c" + keyStruct.companyId : "") + 
				"-m" + keyStruct.modelId +
                //Run and tag added based on whether they are in keyStruct
                (keyStruct.runId ? "-r" + keyStruct.runId : "") +
                (keyStruct.tagId != null ? "-t" + keyStruct.tagId : "")
	};

	keyStruct.noCG = function() {
		return "i" + keyStruct.itemId + 
				"-p" + keyStruct.itemPropertiesId +
				"-y" + keyStruct.intervalId + 
				(keyStruct.companyId ? "-c" + keyStruct.companyId : "") + 
				"-g" + keyStruct.groupEntryId + 
				"-m" + keyStruct.modelId +
                //Run and tag added based on whether they are in keyStruct
                (keyStruct.runId ? "-r" + keyStruct.runId : "") +
                (keyStruct.tagId != null ? "-t" + keyStruct.tagId : "")
	};

	keyStruct.getKey = function() {
		return (keyStruct.CG ? "CG" : "") + 
				"i" + keyStruct.itemId + 
				"-p" + keyStruct.itemPropertiesId + 
				"-y" + keyStruct.intervalId + 
				(keyStruct.companyId ? "-c" + keyStruct.companyId : "") + 
				"-g" + keyStruct.groupEntryId + 
				"-m" + keyStruct.modelId +
                //Run and tag added based on whether they are in keyStruct
                (keyStruct.runId ? "-r" + keyStruct.runId : "") +
                (keyStruct.tagId != null ? "-t" + keyStruct.tagId : "")
	};
	return keyStruct;

};

ofwat.keyUtils.getModelId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.modelId;
};

ofwat.keyUtils.setModelId = function(keyString, modelId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.modelId = modelId;
	return keyStruct.getKey();
};
ofwat.keyUtils.getGroupEntryId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.groupEntryId;
};
ofwat.keyUtils.setGroupEntryId = function(keyString, groupEntryId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.groupEntryId = groupEntryId;
	return keyStruct.getKey();
};

ofwat.keyUtils.getItemId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.itemId;
};
ofwat.keyUtils.setItemId = function(keyString, itemId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.itemId = itemId;
	return keyStruct.getKey();
};

ofwat.keyUtils.getRunId = function(keyString) {
    var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
    return keyStruct.runId;
};
ofwat.keyUtils.setRunId = function(keyString, runId) {
    var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
    keyStruct.runId = runId;
    return keyStruct.getKey();
};

ofwat.keyUtils.getTagId = function(keyString) {
    var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
    return keyStruct.tagId;
};
ofwat.keyUtils.setTagId = function(keyString, tagId) {
    var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
    keyStruct.tagId = tagId;
    return keyStruct.getKey();
};

ofwat.keyUtils.getItemPropertiesId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.itemPropertiesId;
};
ofwat.keyUtils.setItemPropertiesId = function(keyString, itemPropertiesId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.itemPropertiesId = itemPropertiesId;
	return keyStruct.getKey();
};

ofwat.keyUtils.getIntervalId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.intervalId;
};
ofwat.keyUtils.setIntervalId = function(keyString, intervalId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.intervalId = intervalId;
	return keyStruct.getKey();
};

ofwat.keyUtils.getCompanyId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.companyId;
};
ofwat.keyUtils.setCompanyId = function(keyString, companyId) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	keyStruct.companyId = companyId;
	return keyStruct.getKey();
};
ofwat.keyUtils.removeGroupId = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.noGroupEntry();
};

ofwat.keyUtils.removeCG = function(keyString) {
	var keyStruct = ofwat.keyUtils.parseToKeyStruct(keyString);
	return keyStruct.noCG();
};

// a cursory parse to see if it looks key shaped
ofwat.keyUtils.isKeyString = function(key) {
	key = new String(key);
	if (0 == key.length) {
		return false;
	}
	if (key.indexOf(" ") > -1) {
		return false;
	}
	if (0 == key.indexOf("i") && 
		(-1 != key.indexOf("-p")) && 
		(-1 != key.indexOf("-y"))) {
		return true;
	}
	if (0 == key.indexOf("CGi") && (-1 != key.indexOf("-p"))) {
		return true;
	}
	return false;
};

ofwat.keyUtils.getKeyFromString = function(stringContainingKey) {
	var words = stringContainingKey.split(" ");

	for ( var wordIdx in words) {
		if (ofwat.keyUtils.isKeyString(words[wordIdx])) {
			return words[wordIdx];
		}
	}
	return "";
};
