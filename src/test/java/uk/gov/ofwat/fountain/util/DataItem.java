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

public class DataItem implements Comparable<DataItem>{

	
	private String bonCode;
	private String interval;
	private String group;
	private String value;
	private String cellType;
	private String identifier; // concat of boncode interval and group in that order. Used for ordering in sets
	
	
	public DataItem(String bonCode, String interval, String group, String value, String cellType){
		this.bonCode = bonCode;
		this.interval = interval;
		this.group = group;
		this.value = value;
		this.cellType = cellType;
		this.identifier = bonCode + "-" + interval + "-" + group;
	}
	
	public String getBonCode() {
		return bonCode;
	}
	public String getInterval() {
		return interval;
	}
	public String getGroup() {
		return group;
	}
	public String getValue() {
		return value;
	}
	public String getIdentifier(){
		return identifier;
	}
	public String getCellType() {
		return cellType;
	}
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	
	public int compareTo(DataItem other) {
		// comparing identifiers only
		return this.identifier.compareTo(other.getIdentifier());
	}
}
