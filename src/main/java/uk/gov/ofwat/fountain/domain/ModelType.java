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

/**
 * The model types available in the system. Currently these are:
 * <ul>
 *   <li>
 *     ICS<br>
 *     Information Capture Systems that were sent to companies
 *   </li>
 *   <li>
 *     FOUNDATION<br>
 *     Foundation models are simple backing models that link to data. Typically 
 *     they link to branch tags and are used by other models as inputs. For example,
 *     the RFBP09 ICS model uses CB04, PR04 FOUNDATION models.
 *   </li>
 * </ul>
 */
@XmlRootElement(name = "modelType")
@XmlType(propOrder = { "id", "name"})
public class ModelType implements Entity, Serializable  {
	public static final ModelType ICS = new ModelType(1, "ICS");
	public static final ModelType REPORT = new ModelType(2, "REPORT");
	public static final ModelType SCENARIO = new ModelType(3, "SCENARIO");
	public static final ModelType FOUNDATION = new ModelType(4, "FOUNDATION");
	public static final ModelType EXTERNAL = new ModelType(5, "EXTERNAL");
	public static final ModelType TAG_POINT = new ModelType(6, "TAG_POINT");
	
	private int id;
	private String name;
	
	public static ModelType getByName(String name) {
		if (name.equalsIgnoreCase(ICS.getName())) {
			return ICS;
		}
		else if (name.equalsIgnoreCase(REPORT.getName())) {
			return REPORT;
		}
		else if (name.equalsIgnoreCase(SCENARIO.getName())) {
			return SCENARIO;
		}
		else if (name.equalsIgnoreCase(FOUNDATION.getName())) {
			return FOUNDATION;
		}
		else if (name.equalsIgnoreCase(EXTERNAL.getName())) {
			return EXTERNAL;
		}
		else if (name.equalsIgnoreCase(TAG_POINT.getName())) {
			return TAG_POINT;
		}
		return null;
	}
	
	public static ModelType getById(int id) {
		if (ICS.getId() == id) {
			return ICS;
		}
		else if (REPORT.getId() == id) {
			return REPORT;
		}
		else if (SCENARIO.getId() == id) {
			return SCENARIO;
		}
		else if (FOUNDATION.getId() == id) {
			return FOUNDATION;
		}
		else if (EXTERNAL.getId() == id) {
			return EXTERNAL;
		}
		else if (TAG_POINT.getId() == id) {
			return TAG_POINT;
		}
		return null;
	}
	
	private ModelType(int id,  String name){
		this.id = id;
		this.name = name;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ModelType other = (ModelType) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
