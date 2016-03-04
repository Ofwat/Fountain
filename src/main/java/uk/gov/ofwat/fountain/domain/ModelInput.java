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

@XmlRootElement(name = "modelInput")
@XmlType(propOrder = { "id", "code", "parentId", "childModelCode" })
public class ModelInput implements Entity, Coded, Serializable  {
	private int id;
	private int parentId;
	private String code;
	private String childModelCode;
	
	public ModelInput(int id, int parentId, String code, String childModelCode){
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.childModelCode = childModelCode;
	}
	
	public ModelInput() {
		// TODO Auto-generated constructor stub
	}

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getChildModelCode() {
		return childModelCode;
	}
	public void setChildModelCode(String childModelCode) {
		this.childModelCode = childModelCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ModelInput [id=" + id + ", parentId=" + parentId + ", code="
				+ code + ", childModelCode=" + childModelCode + "]";
	}	
	
	
	
}
