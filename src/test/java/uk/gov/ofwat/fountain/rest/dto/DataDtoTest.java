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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;

public class DataDtoTest {

	@Test
	public void testDataDto_notCalculated() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDataDto_notCalculated");
		Interval mockYear = mock(Interval.class);
		Item mockItem = mock(Item.class);
		when(mockItem.getId()).thenReturn(23);
		Company mockCompany = mock(Company.class);
		ItemProperties mockItemProperties = mock(ItemProperties.class);
		when(mockItemProperties.getItem()).thenReturn(mockItem);
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setId(0);
		groupEntry.setDescription("NO GROUP");

		when(mockItemProperties.getGeneralFormula()).thenReturn(null);
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);
		IntervalType itype = new IntervalType();
		itype.setId(23);
		itype.setName("test type");
		when(mockYear.getIntervalType()).thenReturn(itype);

		Data data = new Data();
		data.setCompany(mockCompany);
		data.setId(1);
		data.setItem(mockItem);
		data.setValue("value");
		data.setInterval(mockYear);

		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setId(7);
		
		DataDto dataDto = new DataDto(mockCompany, mockYear, true,
				mockItemProperties, groupEntry, "value", null, null, mockModelDto);

		Assert.assertEquals("", data.getCompany(), dataDto.getCompany());
		Assert.assertEquals("", data.getItem(), dataDto.getItem());
		Assert.assertEquals("", data.getValue(), dataDto.getValue());
		Assert.assertEquals("", true, dataDto.isEditable());
		Assert.assertEquals("", false, dataDto.isCalculated());
		Assert.assertEquals("", 0, dataDto.getItemPropertiesDto().getId());
		Assert.assertEquals("", 7, dataDto.getModelDto().getId());
		Assert.assertTrue("", dataDto.getIntervalDto() instanceof IntervalDto);
	
		System.out.println("TEST: Done");
}

	@Test
	public void testDataDto_calculated() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDataDto_calculated");
		Interval mockYear = mock(Interval.class);
		IntervalType itype = new IntervalType();
		itype.setId(23);
		itype.setName("test type");
		when(mockYear.getIntervalType()).thenReturn(itype);
		Item mockItem = mock(Item.class);
		when(mockItem.getId()).thenReturn(23);
		Company mockCompany = mock(Company.class);
		ItemProperties mockItemProperties = mock(ItemProperties.class);
		when(mockItemProperties.getItem()).thenReturn(mockItem);
		String equation = "an equation";
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setId(0);
		groupEntry.setDescription("NO GROUP");

		when(mockItemProperties.getGeneralFormula()).thenReturn(equation);

		Data data = new Data();
		data.setCompany(mockCompany);
		data.setId(1);
		data.setItem(mockItem);
		data.setValue("value");
		data.setInterval(mockYear);

		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setId(7);

		DataDto dataDto = new DataDto(mockCompany, mockYear, true,
				mockItemProperties, groupEntry, "value", null, null, mockModelDto);

		Assert.assertEquals("", data.getCompany(), dataDto.getCompany());
		Assert.assertEquals("", data.getItem(), dataDto.getItem());
		Assert.assertEquals("", data.getValue(), dataDto.getValue());
		Assert.assertEquals("", true, dataDto.isEditable());
		Assert.assertEquals("", true, dataDto.isCalculated());
		Assert.assertEquals("", 0, dataDto.getItemPropertiesDto().getId());
		Assert.assertEquals("", 7, dataDto.getModelDto().getId());
		Assert.assertTrue("", dataDto.getIntervalDto() instanceof IntervalDto);
	
		System.out.println("TEST: Done");
}
}
