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
package uk.gov.ofwat.fountain.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.Line;
import uk.gov.ofwat.fountain.domain.ModelItem;


@XmlRootElement(name = "modelItem")
@XmlType(propOrder = { "modelId", "modelCode", "modelName", "itemId", "itemCode", "itemName", "teamName", "modelPropertiesMapId", "description", "lineNumber"})

public class ModelItemDto {
	
	private int modelId;
	private String modelCode;
	private String modelName;
	private int itemId;
	private String itemCode;
	private String itemName;
	private String teamName;
	private int modelPropertiesMapId;
	private String description;
	private int lineNumber; 
	private String group;
	
	public ModelItemDto(ModelItem modelItem, Line line) {
		setItemCode(modelItem.getItemCode());
		setItemId(modelItem.getItemId());
		setItemName(modelItem.getItemName());
		setModelCode(modelItem.getModelCode());
		setModelId(modelItem.getModelId());
		setModelName(modelItem.getModelName());
		setTeamName(modelItem.getTeamName());
		setModelPropertiesMapId(modelItem.getModelPropertiesMapId());
		setGroup(modelItem.getGroup());
		setDescription(modelItem.getDescription());
		
		if (null != line) {
			setLineNumber(line.getLineNumber());
		}
	}	
	
	public ModelItemDto(){
		
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
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result
				+ ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + itemId;
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + lineNumber;
		result = prime * result
				+ ((modelCode == null) ? 0 : modelCode.hashCode());
		result = prime * result + modelId;
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + modelPropertiesMapId;
		result = prime * result
				+ ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelItemDto other = (ModelItemDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (modelCode == null) {
			if (other.modelCode != null)
				return false;
		} else if (!modelCode.equals(other.modelCode))
			return false;
		if (modelId != other.modelId)
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (modelPropertiesMapId != other.modelPropertiesMapId)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModelItemDto [modelId=" + modelId + ", modelCode=" + modelCode
				+ ", modelName=" + modelName + ", itemId=" + itemId
				+ ", itemCode=" + itemCode + ", itemName=" + itemName
				+ ", teamName=" + teamName + ", modelPropertiesMapId="
				+ modelPropertiesMapId + ", description=" + description
				+ ", lineNumber=" + lineNumber + ", group=" + group + "]";
	}
	
	
	
}
