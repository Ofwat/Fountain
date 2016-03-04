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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.Interval;

@XmlRootElement(name = "interval")
@XmlType(propOrder = { "id", "intervalTypeId", "name", "index"})
public class IntervalDto {

	private int id;
	private int intervalTypeId;
	private String name;
	private int index;
	

	public IntervalDto(Interval interval) {
		setId(interval.getId());
		setName(interval.getName());
		setIntervalTypeId(interval.getIntervalType().getId());
		setIndex(interval.getIntervalIndex());
//		setIntervalTypeName(interval.getIntervalType().getName());
	}	
	public int getIntervalTypeId() {
		return intervalTypeId;
	}
	public void setIntervalTypeId(int intervalTypeId) {
		this.intervalTypeId = intervalTypeId;
	}
	public IntervalDto(){}
	
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
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + intervalTypeId;
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
		IntervalDto other = (IntervalDto) obj;
		if (id != other.id)
			return false;
		if (intervalTypeId != other.intervalTypeId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IntervalDto [id=" + id + ", intervalTypeId=" + intervalTypeId
				+ ", name=" + name + ", index=" + index + "]";
	}
	
	
	
}
