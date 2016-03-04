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

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ItemPropertiesTest extends TestCase {

	private int id;
	private Item item;
	private int version;
	private String description;
	private String definition;
	private String equation;

	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		id = 7;
		item = new Item();
		item.setCode("CODE1");
		item.setName("ITEM1");
		item.setId(99);
		item.setUnit("cubits");
		version = 2;
		description = "description";
		equation = "equation";
		definition = "definition";
	
		System.out.println("TEST: Done");
}
	
	public void testItemProperties() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testItemProperties");
		ItemProperties itemProperties = new ItemProperties(item, version, description, null, 1, null, true, equation, "", 1, false);
		itemProperties.setId(id);

		Assert.assertEquals("", id, itemProperties.getId());
		Assert.assertEquals("", item, itemProperties.getItem());
		Assert.assertEquals("", version, itemProperties.getVersion());
		Assert.assertEquals("", description, itemProperties.getDescription());
		Assert.assertEquals("", equation, itemProperties.getGeneralFormula());
	
		System.out.println("TEST: Done");
}
	
	public void testEquals(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquals");
		
		ItemProperties original = new ItemProperties(item, version, description, definition, 1, makeMap(), false, null , "", 1, false);
		ItemProperties same = new ItemProperties(item, version, description, definition, 1, makeMap(), false, null, "", 1, false );
		ItemProperties diffVersion = new ItemProperties(item, 1, description, definition, 1, makeMap(), false, null, "", 1, false );
		ItemProperties diffDesc = new ItemProperties(item, version, "different", definition, 1, makeMap(), false, null, "", 1, false );
		ItemProperties diffDef = new ItemProperties(item, version, description, "different", 1, makeMap(), false, null, "", 1, false );
		ItemProperties diffEqus = new ItemProperties(item, version, description, definition, 1, null, false, null, "", 1, false );
		ItemProperties diffGenEq = new ItemProperties(item, version, description, definition, 1, makeMap(), true, "A+B+C", "", 1, false);
		
		assertTrue("original and same should match", original.equals(same));
		assertTrue("same and original should match", same.equals(original));
		assertFalse("should fail on version", original.equals(diffVersion));
		assertFalse("should fail on desc", original.equals(diffDesc));
		assertFalse("should fail on def", original.equals(diffDef));
		assertFalse("should fail on eqs", original.equals(diffEqus));
		assertFalse("should fail on general eq", original.equals(diffGenEq));
	
		System.out.println("TEST: Done");
}
	
	private Map<Interval, ItemPropertyInterval> makeMap(){
		Map<Interval, ItemPropertyInterval>formulae = new HashMap<Interval, ItemPropertyInterval>();

		ItemProperties alternative = new ItemProperties(item, 3, "alt desc", "alt def", 1, null, true, "pi * d", "", 1, false);

		IntervalType iType = new IntervalType();
		iType.setId(233);
		iType.setName("epochs");
		
		Interval interval1 = new Interval();
		interval1.setId(100);
		interval1.setName("100");
		interval1.setIntervalType(iType);
		
		ItemPropertyInterval ipi1 = new ItemPropertyInterval();
		ipi1.setDescription("ipi1");
		ipi1.setFormula("e = mc^2+ 3");
		ipi1.setInterval(interval1);
		ipi1.setItemProperties(alternative);
		
		formulae.put(interval1, ipi1);
		
		Interval interval2 = new Interval();
		interval2.setId(101);
		interval2.setName("101");
		interval2.setIntervalType(iType);
		ItemPropertyInterval ipi2 = new ItemPropertyInterval();
		ipi2.setDescription("ipi2");
		ipi2.setFormula("e = mc5");
		ipi2.setInterval(interval2);
		ipi2.setItemProperties(alternative);
		
		formulae.put(interval2, ipi2);
		
		Interval interval3 = new Interval();
		interval3.setId(102);
		interval3.setName("102");
		interval3.setIntervalType(iType);
		ItemPropertyInterval ipi3 = new ItemPropertyInterval();
		ipi3.setDescription("ipi3");
		ipi3.setFormula("e = mc squeezemaster j");
		ipi3.setInterval(interval3);
		ipi3.setItemProperties(alternative);
		
		formulae.put(interval3, ipi3);
		
		return formulae;
	}
}
