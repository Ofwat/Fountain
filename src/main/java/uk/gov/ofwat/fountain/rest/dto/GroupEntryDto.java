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

import uk.gov.ofwat.fountain.domain.GroupEntry;

@XmlRootElement(name = "groupEntry")
@XmlType(propOrder = { "id", "groupName", "description", "companyName" })

public class GroupEntryDto {
	
	private int id;
	private String groupName;
	private String description;
	private String companyName;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public GroupEntryDto(){
		
	}
	
	public GroupEntryDto(GroupEntry groupEntry){
		this.id = groupEntry.getId();
		this.groupName = groupEntry.getGroup().getName();
		this.description = groupEntry.getDescription();
		this.companyName = groupEntry.getCompany().getName();
	}

	@Override
	public String toString() {
		return "GroupEntryDto [id=" + id + ", groupName=" + groupName
				+ ", description=" + description + ", companyName="
				+ companyName + "]";
	}

	
	
}
