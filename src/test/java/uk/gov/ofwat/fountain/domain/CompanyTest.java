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

import junit.framework.TestCase;

public class CompanyTest extends TestCase{
	
	public void testEquals(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquals");
		Company original = new Company();
		original.setCode("Morse");
		original.setId(123);
		original.setName("Arnold");
		
		Company same = new Company();
		same.setCode("Morse");
		same.setId(123);
		same.setName("Arnold");
		
		Company diffCode = new Company();
		diffCode.setCode("Bar");
		diffCode.setId(123);
		diffCode.setName("Arnold");
		
		Company diffId = new Company();
		diffId.setCode("Morse");
		diffId.setId(321);
		diffId.setName("Arnold");
		
		Company diffName = new Company();
		diffName.setCode("Morse");
		diffName.setId(123);
		diffName.setName("Henry");
		
		assertTrue("same companies should match", original.equals(same));
		assertTrue("same companies should match both ways", same.equals(original));
		assertFalse("should fail on code", original.equals(diffCode));
		assertFalse("should fail on id", original.equals(diffId));
		assertFalse("should fail on name", original.equals(diffName));
		
	
		System.out.println("TEST: Done");
}

}
