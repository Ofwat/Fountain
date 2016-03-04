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

public class Interval implements Entity, Serializable {

	private static final long serialVersionUID = 4408785657293246967L;

	private int id;
	private IntervalType intervalType;
	private String name;
	private int intervalIndex;

	public int getIntervalIndex() {
		return intervalIndex;
	}


	public void setIntervalIndex(int intervalIndex) {
		this.intervalIndex = intervalIndex;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public IntervalType getIntervalType() {
		return intervalType;
	}


	public void setIntervalType(IntervalType intervalType) {
		this.intervalType = intervalType;
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
		Interval other = (Interval) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		return "Interval[" + this.name + "]";
	}

}
