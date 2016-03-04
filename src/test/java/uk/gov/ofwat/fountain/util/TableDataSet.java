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

package uk.gov.ofwat.fountain.util;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class TableDataSet {

	SortedMap<String, DataItem> items = new TreeMap<String, DataItem>();
	
	public void addDataItem(DataItem item){
		items.put(item.getIdentifier(), item);
	}
	
	public DataItem getItem(String identifier){
		return items.get(identifier);
	}
	
	public int size(){
		return items.size();
	}
	
	Collection<DataItem> getItems(){
		return items.values();
	}
}
