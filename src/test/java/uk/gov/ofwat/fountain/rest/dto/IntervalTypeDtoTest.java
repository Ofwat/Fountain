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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uk.gov.ofwat.fountain.domain.IntervalType;

public class IntervalTypeDtoTest {

	@Test
	public void test() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":test");
		IntervalType iType = new IntervalType();
		iType.setId(23);
		iType.setName("porridge");
		IntervalTypeDto dto = new IntervalTypeDto(iType);
		assertEquals("wrong id", iType.getId(), dto.getId());
		assertEquals("wrong name", iType.getName(), dto.getName());
	
		System.out.println("TEST: Done");
}

	@Test
	public void testEquals() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquals");
		IntervalType iType1 = new IntervalType();
		iType1.setId(23);
		iType1.setName("porridge");

		IntervalType iType2 = new IntervalType();
		iType2.setId(46);
		iType2.setName("museli");

		IntervalTypeDto dto1 = new IntervalTypeDto(iType1);

		// same in all respects
		IntervalTypeDto dto2 = new IntervalTypeDto(iType1);

		// different interval type
		IntervalTypeDto dto3 = new IntervalTypeDto(iType2);

		// different dto list
		IntervalTypeDto dto4 = new IntervalTypeDto(iType1);

		IntervalDto idto1 = new IntervalDto();
		idto1.setId(1);
		idto1.setIntervalTypeId(1);
		idto1.setName("interval 1");

		IntervalDto idto2 = new IntervalDto();
		idto2.setId(1);
		idto2.setIntervalTypeId(1);
		idto2.setName("interval 2");

		IntervalDto idto3 = new IntervalDto();
		idto3.setId(3);
		idto3.setIntervalTypeId(1);
		idto3.setName("interval 3");

		IntervalDto idto4 = new IntervalDto();
		idto4.setId(4);
		idto4.setIntervalTypeId(1);
		idto4.setName("interval 4");

		List<IntervalDto> dtoList1 = new ArrayList<IntervalDto>();
		dtoList1.add(idto1);
		dtoList1.add(idto2);
		dtoList1.add(idto3);
		dtoList1.add(idto4);

		IntervalDto idto5 = new IntervalDto();
		idto5.setId(5);
		idto5.setIntervalTypeId(1);
		idto5.setName("interval 5");

		IntervalDto idto6 = new IntervalDto();
		idto6.setId(6);
		idto6.setIntervalTypeId(1);
		idto6.setName("interval 6");

		IntervalDto idto7 = new IntervalDto();
		idto7.setId(7);
		idto7.setIntervalTypeId(1);
		idto7.setName("interval 7");

		IntervalDto idto8 = new IntervalDto();
		idto8.setId(8);
		idto8.setIntervalTypeId(1);
		idto8.setName("interval 8");

		List<IntervalDto> dtoList2 = new ArrayList<IntervalDto>();
		dtoList2.add(idto5);
		dtoList2.add(idto6);
		dtoList2.add(idto7);
		dtoList2.add(idto8);

		dto1.setIntervalDtos(dtoList1);
		dto2.setIntervalDtos(dtoList1);
		dto3.setIntervalDtos(dtoList1);
		dto4.setIntervalDtos(dtoList2);

		assertTrue("dtos should be equal", dto1.equals(dto2));
		assertTrue("dtos should be equal in both directions", dto2.equals(dto1));
		assertFalse("should fail on type", dto1.equals(dto3));
		assertFalse("should fail on type", dto3.equals(dto1));
		assertFalse("should fail on dto list", dto1.equals(dto4));
		assertFalse("should fail on dto list", dto4.equals(dto1));
	
		System.out.println("TEST: Done");
}

}
