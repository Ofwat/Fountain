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

import java.util.Comparator;

/**
 * comparator for sorting modelItemDtos.
 * 
 * Tries to sort on line number, failing that sorts on description.
 */
public class ModelItemDtoComparator implements Comparator<ModelItemDto>{

	public int compare(ModelItemDto o1, ModelItemDto o2) {
		if(0 == (o1.getLineNumber() + o2.getLineNumber())){
			// no line numbers so order on description
			int result = o1.getDescription().compareTo(o2.getDescription());
			if(0 == result){
				// we don't want to return 0 
				return 1;
			}
			return result;
		}
		if(o1.getLineNumber() < o2.getLineNumber()){
			return -1;
		}
		return 1;
	}

	
}
