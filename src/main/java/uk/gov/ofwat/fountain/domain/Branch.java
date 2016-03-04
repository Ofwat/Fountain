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

@XmlRootElement(name = "branch")
@XmlType(propOrder = { "id", "name", "editable", "companyId", "runId" })
public class Branch implements Entity, Serializable {

	private static final long serialVersionUID = 3235601234151391208L;

	private int id;
	private String name;
	private boolean editable;
	private int companyId;
	private int runId;
	
	public Branch() {
	}
	
	public Branch(int id, String name, boolean editable) {
		this.id = id;
		this.name = name;
		this.editable = editable;
	}
	
	public Branch(int id, String name, boolean editable, int companyId, int runId) {
		this.id = id;
		this.name = name;
		this.editable = editable;
		this.companyId = companyId;
		this.runId = runId;
	}
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + runId;
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
		Branch other = (Branch) obj;
		if (companyId != other.companyId)
			return false;
		if (editable != other.editable)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (runId != other.runId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", editable=" + editable
				+ ", companyId=" + companyId + ", runId=" + runId + "]";
	}

	
	
}
