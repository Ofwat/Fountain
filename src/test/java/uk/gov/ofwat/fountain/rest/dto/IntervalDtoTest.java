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

import org.junit.Test;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;

public class IntervalDtoTest {

	@Test
	public void testIntervalDto() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testIntervalDto");
		IntervalType iType = new IntervalType();
		iType.setId(23);
		iType.setName("thee interval type");
		Interval interval = new Interval();
		interval.setId(1);
		interval.setName("2010");
		interval.setIntervalType(iType);

		IntervalDto intervalDto = new IntervalDto(interval);
		assertEquals("wrong id", interval.getId(), intervalDto.getId());
		assertEquals("wrong name", interval.getName(), intervalDto.getName());
		assertEquals("wrong type id", iType.getId(), intervalDto
				.getIntervalTypeId());
	
		System.out.println("TEST: Done");
}

	@Test
	public void testEquals() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquals");
		IntervalDto dto1 = new IntervalDto();
		dto1.setId(1);
		dto1.setIntervalTypeId(1);
		dto1.setName("name");

		// all fields equal
		IntervalDto dto2 = new IntervalDto();
		dto2.setId(1);
		dto2.setIntervalTypeId(1);
		dto2.setName("name");

		// different id
		IntervalDto dto3 = new IntervalDto();
		dto3.setId(2);
		dto3.setIntervalTypeId(1);
		dto3.setName("name");

		// different type
		IntervalDto dto4 = new IntervalDto();
		dto4.setId(1);
		dto4.setIntervalTypeId(2);
		dto4.setName("name");

		// different name
		IntervalDto dto5 = new IntervalDto();
		dto5.setId(1);
		dto5.setIntervalTypeId(1);
		dto5.setName("jennifer");

		assertTrue("should be equal", dto1.equals(dto2));
		assertTrue("should be equal both directions", dto2.equals(dto1));
		assertFalse("should fail on id", dto1.equals(dto3));
		assertFalse("should fail on id", dto3.equals(dto1));
		assertFalse("should fail on type", dto1.equals(dto4));
		assertFalse("should fail on type", dto4.equals(dto1));
		assertFalse("should fail on name", dto1.equals(dto5));
		assertFalse("should fail on name", dto5.equals(dto1));
	
		System.out.println("TEST: Done");
}
}
