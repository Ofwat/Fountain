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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "reportStructure")
@XmlType(propOrder = { "id", "name", "owningUser", "modelItems", "intervalIds", "intervals"})
public class ReportStructureDto {
	
	private int id;
	private String name;
	private String owningUser;
	@XmlTransient private List<ModelItemDto>modelItemDtos;
	private List<Integer>intervalIds;
	@XmlTransient private List<IntervalDto>intervalDtos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOwningUser() {
		return owningUser;
	}
	public void setOwningUser(String owningUser) {
		this.owningUser = owningUser;
	}

	@XmlElement(name="modelItems")
	public List<ModelItemDto> getModelItemDtos() {
		return modelItemDtos;
	}
	public void setModelItemDtos(List<ModelItemDto> modelItemDtos) {
		this.modelItemDtos = modelItemDtos;
	}
	public List<Integer> getIntervalIds() {
		return intervalIds;
	}
	public void setIntervalIds(List<Integer> intervalIds) {
		this.intervalIds = intervalIds;
	}

	@XmlElement(name = "intervals")
	public List<IntervalDto> getIntervalDtos() {
		return intervalDtos;
	}
	public void setIntervalDtos(List<IntervalDto> intervalDtos) {
		this.intervalDtos = intervalDtos;
	}
	@Override
	public String toString() {
		return "ReportStructureDto [id=" + id + ", name=" + name
				+ ", owningUser=" + owningUser + ", modelItemDtos="
				+ modelItemDtos + ", intervalIds=" + intervalIds
				+ ", intervalDtos=" + intervalDtos + "]";
	}
	
	

}
