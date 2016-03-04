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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;


@XmlRootElement(name = "ModelItem")
@XmlType(propOrder = { "modelId", "modelCode", "modelName", "itemId", "itemCode", "itemName", "modelPropertiesMapId", "description"})


public class ModelItem {
	private int modelId;
	private String modelCode;
	private String modelName;
	private int itemId;
	private String itemCode;
	private String itemName;
	private String teamName;
	private int modelPropertiesMapId;
	private String description;
	private String group;
	
	public ModelItem(){
		
	}
	public ModelItem(ModelItemDto dto){
		setModelCode(dto.getModelCode());
		setItemCode(dto.getItemCode());
		setModelId(dto.getModelId());
		setModelName(dto.getModelName());
		setModelPropertiesMapId(dto.getModelPropertiesMapId());
		setItemId(dto.getItemId());
		setItemName(dto.getItemName());
		setTeamName(dto.getTeamName());
		setDescription(dto.getDescription());
		setGroup(dto.getGroup());
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getModelCode() {
		return modelCode;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getModelPropertiesMapId() {
		return modelPropertiesMapId;
	}
	public void setModelPropertiesMapId(int modelPropertiesMapId) {
		this.modelPropertiesMapId = modelPropertiesMapId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
