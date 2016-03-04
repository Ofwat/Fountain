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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "groupEntry")
@XmlType(propOrder = { "id", "ordinal", "company", "group", "description", "aggregate" })
public class GroupEntry implements Entity, Comparable<Object>, Serializable {
	
	private static final long serialVersionUID = 2403566822800001265L;

	private int id;
	private int ordinal;
	private Company company;
	private Group group;
	private String description;
	private boolean aggregate = false;
	
	
	public boolean isAggregate() {
		return aggregate;
	}
	public void setAggregate(boolean aggregate) {
		this.aggregate = aggregate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
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
		result = prime * result + (aggregate ? 1231 : 1237);
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + id;
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
		GroupEntry other = (GroupEntry) obj;
		if (aggregate != other.aggregate)
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
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
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(Object arg0) {
		GroupEntry other = (GroupEntry)arg0;
		return this.id - other.id;
	}
	@Override
	public String toString() {
		return "GroupEntry [id=" + id + ", ordinal=" + ordinal + ", company="
				+ company + ", group=" + group + ", description=" + description
				+ ", aggregate=" + aggregate + "]";
	}

	
	
}
