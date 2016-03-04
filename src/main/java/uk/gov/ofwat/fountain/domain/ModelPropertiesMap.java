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

public class ModelPropertiesMap implements Entity, Serializable  {
	
	private int id;
	private int modelId;
	private int itemId;
	private int itemPropertiesId;
	private String itemCode;
	
	public ModelPropertiesMap(){
		
	}
	
	public ModelPropertiesMap(int id, int modelId, int itemId, String itemCode, int itemPropertiesId) {
		this.setId(id);
		this.setItemId(itemId);
		this.setModelId(modelId);
		this.setItemCode(itemCode);
		this.setItemPropertiesId(itemPropertiesId);
	}
	public int getId() {
		return id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemPropertiesId() {
		return itemPropertiesId;
	}
	public void setItemPropertiesId(int itemPropertiesId) {
		this.itemPropertiesId = itemPropertiesId;
	}
		
}
