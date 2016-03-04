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

@XmlRootElement(name = "ConfidenceGrade")
@XmlType(propOrder = { "id", "code", "redundant"  })
public class ConfidenceGrade implements Entity, Coded, Serializable {

	private static final long serialVersionUID = 3719535248705792631L;
	
	private int id;
	private boolean redundant;
	private String code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isRedundant() {
		return redundant;
	}
	public void setRedundant(boolean redundant) {
		this.redundant = redundant;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ConfidenceGrade [id=" + id + ", redundant=" + redundant
				+ ", code=" + code + "]";
	}	
}
