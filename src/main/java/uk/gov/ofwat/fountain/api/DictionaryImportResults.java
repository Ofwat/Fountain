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
package uk.gov.ofwat.fountain.api;

import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.ItemProperties;

/**
 * The results of the import operation 
 */
public class DictionaryImportResults {
	private List<String> errors;
	private Map<String, ItemProperties> attachedPropertiesMap;
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	/**
	 * Given an item code, return the properties attached to the map
	 * @return {@link ItemProperties}
	 */
	public Map<String, ItemProperties> getAttachedPropertiesMap() {
		return attachedPropertiesMap;
	}
	public void setAttachedPropertiesMap(Map<String, ItemProperties> attachedPropertiesMap) {
		this.attachedPropertiesMap = attachedPropertiesMap;
	}
	
	public boolean isFailure() {
		return (errors!=null && errors.size()>0);
	}
}
