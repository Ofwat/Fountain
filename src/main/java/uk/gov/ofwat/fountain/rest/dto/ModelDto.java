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

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.rest.Link;

@XmlRootElement(name = "model")
@XmlType(propOrder = { "id", "name" , "code", "modelTypeId", "company", "tableLinks", "branch", "displayOrder" })

public class ModelDto {

	int id;
	int modelTypeId;
	String name;
	String code;
	Company company;
	Collection<Link> tableLinks;
	Branch branch;
	int displayOrder;

	public ModelDto () {
	}
	
	public ModelDto (Model model, Collection<Link> tableLinks) {
		setId(model.getId());
		setName(model.getName());
		setCode(model.getCode());
		setTableLinks(tableLinks);
		setModelTypeId(model.getModelType().getId());
		setBranch(model.getBranch());
		setDisplayOrder(model.getDisplayOrder());
	}
	
	public int getDisplayOrder() {
		return displayOrder;
	}
	
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	
	
	public int getModelTypeId() {
		return modelTypeId;
	}

	public void setModelTypeId(int modelTypeId) {
		this.modelTypeId = modelTypeId;
	}

	public Collection<Link> getTableLinks() {
		return tableLinks;
	}
	public void setTableLinks(Collection<Link> tableLinks) {
		this.tableLinks = tableLinks;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "ModelDto [id=" + id + ", modelTypeId=" + modelTypeId
				+ ", name=" + name + ", code=" + code + ", company=" + company
				+ ", tableLinks=" + tableLinks + ", branch=" + branch
				+ ", displayOrder=" + displayOrder + "]";
	}

	
}
