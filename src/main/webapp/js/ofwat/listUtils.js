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

ofwat.listUtils = {
	
};



/**
 * Takes all selected items from a list with id <fromlist> and adds to the list with id <toList>
 * 
 * The items will be deleted from <fromList> unless you pass true as a third parameter
 */
ofwat.listUtils.move = function (fromList, toList, keep, callback) {

	var pickList = dojo.byId(fromList);
	var pickIndex = pickList.selectedIndex;
	var pickOptions = pickList.options;

	
	var selectList = null;
	var selectOptions = null;
	var selectOLength = null;
	
	if (toList!=null){
		selectList = dojo.byId(toList);
		selectOptions = selectList.options;
		selectOLength = selectOptions.length;
	}

    while(pickList.selectedIndex != -1){
		var newOption = new Option(pickList.options[pickList.selectedIndex].text);
		newOption.value = pickList.options[pickList.selectedIndex].value;
		newOption.title = pickList.options[pickList.selectedIndex].title;
		selectOptions[selectOptions.length] = newOption; 
		if(!keep){
			pickList.remove(pickList.selectedIndex);
		}
		else{
			pickList.options[pickList.selectedIndex].selected = false;
		}
	}

    if(!callback == null){
        callback(pickList, selectList, keep);
    }
};

/**
 * Takes all selected items from a list with id <fromlist> and adds to the list with id <toList>
 */
ofwat.listUtils.removeOption = function (list, textToRemove) {
	var pickList = dojo.byId(list);

	for (i=0; i<pickList.options.length; i++){
		var item = pickList.options[i];
		if (item.text == textToRemove){
			pickList.remove(i);
		}
	}
};

ofwat.listUtils.addOptionToFirstListIfItsNotOnEither = function (firstList, secondList, textToAdd) {
	var pickList = dojo.byId(secondList);

	for (i=0; i<pickList.options.length; i++){
		var item = pickList.options[i];
		if (item.text == textToAdd){
			return; // The option is already on the list
		}
	}
	ofwat.listUtils.addOption(firstList, textToAdd);
};



ofwat.listUtils.addOption = function (list, textToAdd) {
	var pickList = dojo.byId(list);

	for (i=0; i<pickList.options.length; i++){
		var item = pickList.options[i];
		if (item.text == textToAdd){
			return; // The option is already on the list
		}
	}

	var newOption = new Option(textToAdd);
	newOption.value = pickList.options.length+1;
	pickList.add(newOption);
	pickList.selectedIndex = pickList.options.length; 
};

ofwat.listUtils.containsOption = function(list, textToCheck){
    var pickList = dojo.byId(list);

    for (i=0; i<pickList.options.length; i++){
        var item = pickList.options[i];
        if (item.text == textToCheck){
            return true; // The option is on the list
        }
    }
    return false;
};

/**
 * removes all selected items from the list with id <fromList>
 */
ofwat.listUtils.remove = function (fromList) {

	var pickList = dojo.byId(fromList);
	while(pickList.selectedIndex != -1){
		pickList.remove(pickList.selectedIndex);
	}
};


/**
 * moves an item up (negative offset) or down (positive offset) an option list
 * for which you provide the id
 */
ofwat.listUtils.ListItemMove = function(offset, listId){
	
	var list = dojo.byId(listId);
	var idx = list.selectedIndex; 
	if( (-1 == idx) || (idx + offset) < 0 || (idx + offset) > (list.options.length-1)) {
		// either nothing is selected or the item cannot be moved any further in that direction   
		return;
	}
	var selText = list.options[idx].text;
	var selValue = list.options[idx].value;
	list.options[idx].text = list.options[idx + offset].text; 
	list.options[idx].value = list.options[idx + offset].value; 
	list.options[idx + offset].text = selText; 
	list.options[idx + offset].value = selValue; 
	list.selectedIndex = idx + offset;
};


/**
* Get a node from the provided list with the provided value
*
*/
ofwat.listUtils.ListGetNodeText = function (listId, value) {

	var list = dojo.byId(listId);  
	
	for (i=0; i<list.options.length; i++){
		var item = list.options[i];
		if (item.value == value){
			return item.text;
		}
	}
	
	return null;
	
};