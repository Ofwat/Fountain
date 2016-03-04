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

import java.io.Serializable;

public class OldDictionaryValue implements Entity, Serializable  {
	private int id;
	private int itemPropertiesId;
	private int decimalPlaces;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemPropertiesId() {
		return itemPropertiesId;
	}
	public void setItemPropertiesId(int itemPropertiesId) {
		this.itemPropertiesId = itemPropertiesId;
	}
	public int getDecimalPlaces() {
		return decimalPlaces;
	}
	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}
	
	
}
