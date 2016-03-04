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
package uk.gov.ofwat.fountain.dao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ItemDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static final int MODEL_ID_1 = 1;
	private static final int MODEL_ID_2 = 2;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemPropertiesDao itemPropertiesDao;
	
	@Autowired
	private ModelPropertiesMapDao modelPropertiesMapDao;

	

	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		Item item = new Item();
		item.setName("test item");
		item.setCode("T101");
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		item.setGroup(group);

		
		int id = itemDao.create(item); 
		// itemDao.findById needs an itemproperties 
		ItemProperties ip = new ItemProperties(item, 1, "T101 description 1", "T101 definition ZZ101", 1,  null, false, null, "", 1, false);
		ip.setId(itemPropertiesDao.create(ip));
		Item rtn = itemDao.findById(id);
		assertNotNull("null item returned", rtn);
		assertEquals("name not correct", item.getName(), rtn.getName());
		assertEquals("code not correct", item.getCode(), rtn.getCode());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testSearch(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearch");
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		
		item1.setName("item 1 name");
		item1.setCode("ZZ101");
		item1.setGroup(group);

		item2.setName("zzoo animals");
		item2.setCode("T101");
		item2.setGroup(group);
		
		item3.setName("item 3 name");
		item3.setCode("T102");
		item3.setGroup(group);
		
		item4.setName("item 4 name");
		item4.setCode("T103");
		item4.setGroup(group);
		
		int id1 =itemDao.create(item1); 
		int id2 =itemDao.create(item2); 
		itemDao.create(item3); 
		itemDao.create(item4);
		
		List<Item> searchResults = itemDao.searchByCodeOrDescription("z");
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 2, searchResults.size());
		
		boolean found1 = false;
		boolean found2 = false;
		
		for(Item item: searchResults){
			if(item.getId() == id1){
				found1 = true;
			}
			else if(item.getId() == id2){
				found2 = true;
			}
		}
		assertTrue("Failed to find item 1", found1);
		assertTrue("Failed to find item 2", found2);
		
		
	
		System.out.println("TEST: Done");
}
	
	
	@Test
	@Rollback(true)
	public void testSearchByDefinitions(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchByDefinitions");
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		
		
		item1.setName("item 1 name");
		item1.setCode("0");
		item1.setGroup(group);
		int id1 =itemDao.create(item1);
		item1.setId(id1);
		ItemProperties ip1 = new ItemProperties(item1, 1, "description 1", "definition ZZ101", 1,  null, false, null, "", 1, false);
		ip1.setId(itemPropertiesDao.create(ip1));
		
		
		item2.setName("item 2 animals");
		item2.setCode("0");
		item2.setGroup(group);
		int id2 =itemDao.create(item2);
		item2.setId(id2);
		ItemProperties ip2 = new ItemProperties(item2, 1, "description 2", "definition ZZ202", 1,  null, false, null, "", 1, false);
		ip2.setId(itemPropertiesDao.create(ip2));
		ItemProperties ip2a = new ItemProperties(item2, 2, "description 3", "definition YY202", 1, null, false, null, "", 1, false);
		ip2a.setId(itemPropertiesDao.create(ip2a));
		
		
		item3.setName("item 3 name");
		item3.setCode("0");
		item3.setGroup(group);
		int id3 =itemDao.create(item3);
		item3.setId(id3);
		ItemProperties ip3 = new ItemProperties(item3, 1,"description 4",  "definition T102", 1, null, false, null, "", 1, false);
		ip3.setId(itemPropertiesDao.create(ip3));
		
		
		item4.setName("item 4 name");
		item4.setCode("T103");
		item4.setGroup(group);
		int id4 =itemDao.create(item4);
		item1.setId(id4);
		ItemProperties ip4 = new ItemProperties(item4, 1, "description 5", "definition T103", 1, null, false, null, "", 1, false);
		ip4.setId(itemPropertiesDao.create(ip4));
		
		List<Item> searchResults = itemDao.searchByLastestDefinitions("zz");
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 1, searchResults.size());
		
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testSearchByDefinitionWithFilter(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchByDefinitionWithFilter");
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		
		item1.setName("item 1 name");
		item1.setCode("0");
		item1.setGroup(group);
		int id1 =itemDao.create(item1);
		item1.setId(id1);
		ItemProperties ip1 = new ItemProperties(item1, 1, "description 1", "definition ZZ101", 1, null, false, null, "", 1, false);
		ip1.setId(itemPropertiesDao.create(ip1));
		
		
		item2.setName("item 2 animals");
		item2.setCode("0");
		item2.setGroup(group);
		int id2 =itemDao.create(item2);
		item2.setId(id2);
		ItemProperties ip2 = new ItemProperties(item2, 1, "description 2", "definition ZZ202", 1, null, false, null, "", 1, false);
		ip2.setId(itemPropertiesDao.create(ip2));
		ItemProperties ip2a = new ItemProperties(item2, 2, "description 3", "definition YY202", 1, null, false, null, "", 1, false);
		ip2a.setId(itemPropertiesDao.create(ip2a));
		
		
		item3.setName("item 3 name");
		item3.setCode("0");
		item3.setGroup(group);
		int id3 =itemDao.create(item3);
		item3.setId(id3);
		ItemProperties ip3 = new ItemProperties(item3, 1,"description 4",  "definition ZZ102", 1, null, false, null, "", 1, false);
		ip3.setId(itemPropertiesDao.create(ip3));
		
		
		item4.setName("item 4 name");
		item4.setCode("T103");
		item4.setGroup(group);
		int id4 =itemDao.create(item4);
		item4.setId(id4);
		ItemProperties ip4 = new ItemProperties(item4, 1, "description 5", "definition ZZ103", 1, null, false, null, "", 1, false);
		ip4.setId(itemPropertiesDao.create(ip4));
		
	
		ModelPropertiesMap mpm1 = new ModelPropertiesMap(0, MODEL_ID_1, item1.getId(), item1.getCode(), ip1.getId());
		ModelPropertiesMap mpm2 = new ModelPropertiesMap(0, MODEL_ID_1, item2.getId(), item2.getCode(), ip2.getId());
		modelPropertiesMapDao.create(mpm1);
		modelPropertiesMapDao.create(mpm2);
		
		
		// only ip1 should be found. ip2 is not returned as there is a later version - even though it is
		// not connected with model 10
		List<Item> searchResults = itemDao.searchByLastestDefinitions("zz", new int[]{MODEL_ID_1});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 1, searchResults.size());
		
		// ip2a should be picked up as it's the latest version of item 2's properties and item 2 is linked to model 10
		// even though it's via a different property version. 
		searchResults = itemDao.searchByLastestDefinitions("yy", new int[]{MODEL_ID_1});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 1, searchResults.size());
		
		// no results should be found for model 11 as none of the items are present
		searchResults = itemDao.searchByLastestDefinitions("zz", new int[]{MODEL_ID_2});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 0, searchResults.size());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testSearchByFilteredCodeOrDescription(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSearchByFilteredCodeOrDescription");
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		
		item1.setName("item 1 name");
		item1.setCode("ZZ");
		item1.setGroup(group);
		int id1 =itemDao.create(item1);
		item1.setId(id1);
		ItemProperties ip1 = new ItemProperties(item1, 1, "description 1", "definition AA101", 2, null, false, null, "", 1, false);
		ip1.setId(itemPropertiesDao.create(ip1));
		
		
		item2.setName("item 2 animals");
		item2.setCode("YY");
		item2.setGroup(group);
		int id2 =itemDao.create(item2);
		item2.setId(id2);
		ItemProperties ip2 = new ItemProperties(item2, 1, "zzz 2", "definition ZZ202", 2, null, false, null, "", 1, false);
		ip2.setId(itemPropertiesDao.create(ip2));
		ItemProperties ip2a = new ItemProperties(item2, 2, "qqq 3", "definition ZZ203", 2, null, false, null, "", 1, false);
		ip2a.setId(itemPropertiesDao.create(ip2a));
		
		
		item3.setName("item 3 name");
		item3.setCode("XX");
		item3.setGroup(group);
		int id3 =itemDao.create(item3);
		item3.setId(id3);
		ItemProperties ip3 = new ItemProperties(item3, 1,"description 4",  "definition ZZ102", 2, null, false, null, "", 1, false);
		ip3.setId(itemPropertiesDao.create(ip3));
		
		
		item4.setName("item 4 name");
		item4.setCode("WW");
		item4.setGroup(group);
		int id4 =itemDao.create(item4);
		item4.setId(id4);
		ItemProperties ip4 = new ItemProperties(item4, 1, "ZZZ 5", "definition ZZ103", 2, null, false, null, "", 1, false);
		ip4.setId(itemPropertiesDao.create(ip4));
		
	
		ModelPropertiesMap mpm1 = new ModelPropertiesMap(0, MODEL_ID_1, item1.getId(), item1.getCode(), ip1.getId());
		ModelPropertiesMap mpm2 = new ModelPropertiesMap(0, MODEL_ID_1, item2.getId(), item2.getCode(), ip2.getId());  // version 1 of item 2 props
		ModelPropertiesMap mpm3 = new ModelPropertiesMap(0, MODEL_ID_1, item3.getId(), item3.getCode(), ip3.getId());
		ModelPropertiesMap mpm4 = new ModelPropertiesMap(0, MODEL_ID_1, item4.getId(), item4.getCode(), ip4.getId());
		modelPropertiesMapDao.create(mpm1);
		modelPropertiesMapDao.create(mpm2);
		modelPropertiesMapDao.create(mpm3);
		modelPropertiesMapDao.create(mpm4);
		
		// should pick up item 1 (via item code) and item 4 via the property description. It should not 
		// find item 2 as version 1 is a previous version and version 2 only matches on definition
		List<Item> searchResults = itemDao.searchByCodeOrDescription("zz", new int[]{MODEL_ID_1});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 2, searchResults.size());
		
		// ip2a should be picked up as it's the latest version of item 2's properties and item 2 is linked to model 10
		// even though it's via a different property version. 
		searchResults = itemDao.searchByCodeOrDescription("qq", new int[]{MODEL_ID_1});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 1, searchResults.size());
		
		// no results should be found for model 2 as none of the items are present
		searchResults = itemDao.searchByCodeOrDescription("zz", new int[]{MODEL_ID_2});
		assertNotNull("no search results returned", searchResults);
		assertEquals("incorrect number of results", 0, searchResults.size());
	
		System.out.println("TEST: Done");
}

}
