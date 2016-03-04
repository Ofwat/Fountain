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

package uk.gov.ofwat.fountain.api.report;

import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;


/**
 * Everything a report needs to represent an item. Most of the 
 * fields can be obtained from the item properties but model name is also needed
 * on reports.
 */
public class ReportItem {
	
	private ItemProperties itemProperties;
	private ModelDto modelDto;
	public ItemProperties getItemProperties() {
		return itemProperties;
	}
	public void setItemProperties(ItemProperties itemProperties) {
		this.itemProperties = itemProperties;
	}
	public ModelDto getModelDto() {
		return modelDto;
	}
	public void setModelDto(ModelDto modelDto) {
		this.modelDto = modelDto;
	}
	
	

}
