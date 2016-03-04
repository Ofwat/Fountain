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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Table implements Addressable, Entity, Serializable   {

	private int id;
	private String name;
    private Map<String, Pot> pots;
    private Set<Group> tableGroups;
    private int modelId;
    private int companyTypeId;
    private String description;

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Table() {
    	pots = new HashMap<String, Pot>();
	}

    public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public Map<String, Pot> getPots() {
		return pots;
	}
	public void setPots(Collection<Pot> pots) {
		for (Pot pot: pots) {
			addPot(pot);
		}	
	}	
	public void addPot (Pot pot) {
		this.pots.put(pot.getItem().getCode() + pot.getInterval().getName(), pot);
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
	public int getCompanyTypeId() {
		return companyTypeId;
	}
	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
	}
	public Set<Group> getTableGroups() {
		return tableGroups;
	}
	public void setTableGroups(Set<Group> tableGroups) {
		this.tableGroups = tableGroups;
	}
	
}
