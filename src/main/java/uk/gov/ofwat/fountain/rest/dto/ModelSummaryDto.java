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

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.rest.Link;

/**
 * lightweight dto for inclusion in lists etc.
 * 
 * does not contain details of the content (tables etc)
 */
@XmlRootElement(name = "modelSummary")
@XmlType(propOrder = { "id", "name" , "code", "modelType", "modelTypeName", "displayOrder", "link", "branch", "family", "parent", "modelInputs", "FInputId", "FOutputId"})
public class ModelSummaryDto implements Linkable{

	private int id;
	private String modelType;
	private String name;
	private String code;
	private String modelTypeName;
	private int displayOrder;
	private Link link;
	private Branch branch; 
	private ModelFamily family;
	private boolean parent;
	private Collection<ModelInput> modelInputs;
	private int fInputId;
	private int fOutputId;
	
	public ModelSummaryDto() {
	}
	
	/**
	 * @param model
	 */
	public ModelSummaryDto(Model model) {
		this.code = model.getCode();
		this.name = model.getName();
		this.id = model.getId();
		this.modelType = model.getModelType().getName();
		this.modelTypeName = model.getModelType().getName();
		this.displayOrder = model.getDisplayOrder();

		this.branch  = model.getBranch();
		this.family = model.getFamily();
		this.parent = model.isParent();
		
		Collection<ModelInput> modelInputs = model.getModelInputs().values();
		if (null == modelInputs) {
			this.modelInputs = new ArrayList<ModelInput>();
		}
		else {
			this.modelInputs = modelInputs;
		}
	}
	
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
	public ModelFamily getFamily() {
		return family;
	}
	public void setFamily(ModelFamily family) {
		this.family = family;
	}
	
	public boolean isParent() {
		return parent;
	}
	public void setParent(boolean parent) {
		this.parent = parent;
	}
	
	public Collection<ModelInput> getModelInputs() {
		return modelInputs;
	}
	public void setModelInputs(Collection<ModelInput> modelInputs) {
		this.modelInputs = modelInputs;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelTypeName() {
		return modelTypeName;
	}
	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@XmlTransient
	public String getUri() {
		return link.getUri();
	}

	public int getFInputId() {
		return fInputId;
	}

	public void setFInputId(int fInputId) {
		this.fInputId = fInputId;
	}

	public int getFOutputId() {
		return fOutputId;
	}

	public void setFOutputId(int fOutputId) {
		this.fOutputId = fOutputId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + displayOrder;
		result = prime * result + fInputId;
		result = prime * result + fOutputId;
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + id;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((modelInputs == null) ? 0 : modelInputs.hashCode());
		result = prime * result
				+ ((modelType == null) ? 0 : modelType.hashCode());
		result = prime * result
				+ ((modelTypeName == null) ? 0 : modelTypeName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (parent ? 1231 : 1237);
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
		ModelSummaryDto other = (ModelSummaryDto) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (displayOrder != other.displayOrder)
			return false;
		if (fInputId != other.fInputId)
			return false;
		if (fOutputId != other.fOutputId)
			return false;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (id != other.id)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (modelInputs == null) {
			if (other.modelInputs != null)
				return false;
		} else if (!modelInputs.equals(other.modelInputs))
			return false;
		if (modelType == null) {
			if (other.modelType != null)
				return false;
		} else if (!modelType.equals(other.modelType))
			return false;
		if (modelTypeName == null) {
			if (other.modelTypeName != null)
				return false;
		} else if (!modelTypeName.equals(other.modelTypeName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent != other.parent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModelSummaryDto [id=" + id + ", modelType=" + modelType
				+ ", name=" + name + ", code=" + code + ", modelTypeName="
				+ modelTypeName + ", displayOrder=" + displayOrder + ", link="
				+ link + ", branch=" + branch + ", family=" + family
				+ ", parent=" + parent + ", modelInputs=" + modelInputs
				+ ", fInputId=" + fInputId + ", fOutputId=" + fOutputId + "]";
	}
	
	

}
