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

import junit.framework.Assert;
import junit.framework.TestCase;

public class ModelPropertiesMapTest extends TestCase {

	private int id;
	private int modelId;
	private int itemId;
	private int itemPropertiesId;
	private String itemCode;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		id = 1;
		modelId = 2;
		itemId = 3;
		itemCode = "ABC";
		itemPropertiesId = 4;
	
		System.out.println("TEST: Done");
}
	
	public void testModelProperties() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testModelProperties");
		ModelPropertiesMap modelPropertiesMap = new ModelPropertiesMap(0, modelId, itemId, itemCode, itemPropertiesId);
		modelPropertiesMap.setId(id);
		
		Assert.assertEquals("", id, modelPropertiesMap.getId());
		Assert.assertEquals("", modelId, modelPropertiesMap.getModelId());
		Assert.assertEquals("", itemId, modelPropertiesMap.getItemId());
		Assert.assertEquals("", itemPropertiesId, modelPropertiesMap.getItemPropertiesId());
		Assert.assertEquals("", itemCode, modelPropertiesMap.getItemCode());
	
		System.out.println("TEST: Done");
}

}
