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

/**
 * contains all of the specific properties relating to a single interval
 * within an item's properties.
 * 
 * Typically this will be formula as formula can change by interval for a given item.
 * 
 * There is scope here to add description etc should each individual formula need it
 */
public class ItemPropertyInterval implements Entity, Serializable  {
	
	private int id;
	
	private String formula;
	
	private String description;
	
	private ItemProperties itemProperties;
	
	private Interval interval;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemProperties getItemProperties() {
		return itemProperties;
	}

	public void setItemProperties(ItemProperties itemProperties) {
		this.itemProperties = itemProperties;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((interval == null) ? 0 : interval.hashCode());
		result = prime * result
				+ ((itemProperties == null) ? 0 : itemProperties.hashCode());
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
		ItemPropertyInterval other = (ItemPropertyInterval) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		if (id != other.id)
			return false;
		if (interval == null) {
			if (other.interval != null)
				return false;
		} else if (!interval.equals(other.interval))
			return false;
		if (itemProperties == null) {
			if (other.itemProperties != null)
				return false;
		} else if (!itemProperties.equals(other.itemProperties))
			return false;
		return true;
	}
	
	
	

}
