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


public enum RunRole implements Serializable {

	NONE(0),
	STANDARD(1),
	PROXY(2),
	DEFAULT(3);
	
	public static RunRole getById(int id) {
		if (STANDARD.getId() == id) {
			return STANDARD;
		}
		else if (PROXY.getId() == id) {
			return PROXY;
		}
		else if (DEFAULT.getId() == id) {
			return DEFAULT;
		}
		return NONE;
		
	}
	
	private int id;

	private RunRole(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
