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

package uk.gov.ofwat.fountain.domain;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import uk.gov.ofwat.fountain.rest.dto.DataDto;



/**
 * can either be a single line of values (item by year) or
 * can be a grouped item in which case it will have multiple lines
 * of data for the given item. 
 * Possibly it will also have the capability of adding (or deleting?) group
 * rows.
 */
public class TableItemLine {

	public boolean grouped;
	public boolean editableList;
	public Item item;
	List<SortedMap<String, Map<String, DataDto>>> lines;
	
	// a single row is a map with the 
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isGrouped() {
		return grouped;
	}

	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}

	public boolean isEditableList() {
		return editableList;
	}

	public void setEditableList(boolean editableList) {
		this.editableList = editableList;
	}
	
	public void setLines(List<SortedMap<String, Map<String, DataDto>>> lines){
		this.lines = lines;
	}

	List<SortedMap<String, Map<String, DataDto>>> getLines(){
		return null;
	}
	
}
