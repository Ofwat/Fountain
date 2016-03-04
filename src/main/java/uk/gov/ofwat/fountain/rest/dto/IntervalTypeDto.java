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
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.IntervalType;

@XmlRootElement(name = "intervalType")
@XmlType(propOrder = { "name", "id", "intervalDtos"})

public class IntervalTypeDto implements Comparable<IntervalTypeDto>{
	
	private String name;
	private int id;
	private int displayOrder;
	private List<IntervalDto>intervalDtos;
	
	
	/**
	 * 
	 */
	public IntervalTypeDto() {
		
	}
	/**
	 * @param type
	 */
	public IntervalTypeDto(IntervalType type) {
		intervalDtos = new ArrayList<IntervalDto>();
		name = type.getName();
		id = type.getId();
		this.displayOrder = type.getDisplayOrder();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<IntervalDto> getIntervalDtos() {
		return intervalDtos;
	}
	public void setIntervalDtos(List<IntervalDto> intervalDtos) {
		this.intervalDtos = intervalDtos;
	}
	/**
	 * @param intervalDto
	 */
	public void addIntervalDto(IntervalDto intervalDto) {
		this.intervalDtos.add(intervalDto);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((intervalDtos == null) ? 0 : intervalDtos.hashCode());
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
		IntervalTypeDto other = (IntervalTypeDto) obj;
		if (id != other.id)
			return false;
		if (intervalDtos == null) {
			if (other.intervalDtos != null)
				return false;
		} else if (!intervalDtos.equals(other.intervalDtos))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public int compareTo(IntervalTypeDto o) {
		if(null == o){
			return 1;
		}
		return this.displayOrder - o.displayOrder;
	}
	@Override
	public String toString() {
		return "IntervalTypeDto [name=" + name + ", id=" + id
				+ ", displayOrder=" + displayOrder + ", intervalDtos="
				+ intervalDtos + "]";
	}
	
	

	
	
	
	
	

}
