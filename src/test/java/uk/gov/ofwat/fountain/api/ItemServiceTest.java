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
package uk.gov.ofwat.fountain.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ItemPropertyIntervalDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;


public class ItemServiceTest extends TestCase {
	
	private ItemServiceImpl itemService;
	private ItemPropertiesDao mockItemPropertiesDao;
	private ItemPropertyIntervalDao mockItemPropertyIntervalDao;
	private ItemDao mockItemDao;
	private ModelPropertiesMapDao mockModelPropertiesMapDao;
	
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		System.out.println("TEST: ItemServiceTest.setUp()");
		itemService = new ItemServiceImpl();

		mockItemPropertiesDao = mock(ItemPropertiesDao.class);
		mockItemDao = mock(ItemDao.class);
		mockModelPropertiesMapDao = mock(ModelPropertiesMapDao.class);
		mockItemPropertyIntervalDao = mock(ItemPropertyIntervalDao.class);
		
		itemService.setItemPropertiesDao(mockItemPropertiesDao);
		itemService.setItemPropertyIntervalDao(mockItemPropertyIntervalDao);
		itemService.setItemDao(mockItemDao);
		itemService.setModelPropertiesMapDao(mockModelPropertiesMapDao);
	
		System.out.println("TEST: Done");
}

	public void testGetPropertiesForItemAndModel() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPropertiesForItemAndModel");
		System.out.println("TEST: ItemServiceTest.testGetPropertiesForItemAndModel()");
		ItemProperties itemProperties = new ItemProperties(null, 0, null, null,0, null,  false, null, "", 1, false);
		when(mockItemPropertiesDao.findByItemAndModel(1, 1)).thenReturn(itemProperties);
		Assert.assertEquals(itemProperties, itemService.getPropertiesForItemAndModel(1, 1));
	
		System.out.println("TEST: Done");
}

	public void testGetPropertiesForModel() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPropertiesForModel");
		System.out.println("TEST: ItemServiceTest.testGetPropertiesForModel()");
		HashMap<Integer, ItemProperties> itemPropertiesMap = new HashMap<Integer, ItemProperties>();

		when(mockModelPropertiesMapDao.getAllForModel(1)).thenReturn(itemPropertiesMap);

		Assert.assertEquals(itemPropertiesMap, itemService.getPropertiesForModel(1));
	
		System.out.println("TEST: Done");
}
	
	public void testGetItem() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetItem");
		System.out.println("TEST: ItemServiceTest.testGetItem()");
		Item item = new Item();
		when(mockItemDao.findById(1)).thenReturn(item);
		Assert.assertEquals(item, itemService.getItem(1));
	
		System.out.println("TEST: Done");
}

	public void testSearchForItem() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchForItem");
		System.out.println("TEST: ItemServiceTest.testSearchForItem()");
		List<Item> items = new ArrayList<Item>(); 
		when(mockItemDao.searchByCodeOrDescription("criteria")).thenReturn(items);
		Assert.assertEquals(items, itemService.searchForItem("criteria"));
	
		System.out.println("TEST: Done");
}

	public void testSearchForItemViaDefinition() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchForItemViaDefinition");
		System.out.println("TEST: ItemServiceTest.testSearchForItemViaDefinition()");
		List<Item> items = new ArrayList<Item>(); 
		when(mockItemDao.searchByLastestDefinitions("criteria")).thenReturn(items);
		Assert.assertEquals(items, itemService.searchForItemViaDefinition("criteria"));
	
		System.out.println("TEST: Done");
}

	public void testSearchForItem_WithModelFilters() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchForItem_WithModelFilters");
		System.out.println("TEST: ItemServiceTest.testSearchForItem_WithModelFilters()");
		List<Item> items = new ArrayList<Item>(); 
		int[] modelFilters = {};
		when(mockItemDao.searchByCodeOrDescription("criteria", modelFilters)).thenReturn(items);
		Assert.assertEquals(items, itemService.searchForItem("criteria", modelFilters));
	
		System.out.println("TEST: Done");
}

	public void testSearchForItemViaDefinition_WithModelFilters() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchForItemViaDefinition_WithModelFilters");
		System.out.println("TEST: ItemServiceTest.testSearchForItemViaDefinition_WithModelFilters()");
		List<Item> items = new ArrayList<Item>(); 
		int[] modelFilters = {};
		when(mockItemDao.searchByLastestDefinitions("criteria", modelFilters)).thenReturn(items);
		Assert.assertEquals(items, itemService.searchForItemViaDefinition("criteria", modelFilters));
	
		System.out.println("TEST: Done");
}
}
