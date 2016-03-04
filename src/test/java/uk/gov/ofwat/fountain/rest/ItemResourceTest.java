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

package uk.gov.ofwat.fountain.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.rest.dto.IntervalTypeDto;
import uk.gov.ofwat.fountain.rest.dto.ItemDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.util.Locations;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = { "classpath:test_beans.xml" })
public class ItemResourceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	/************** STATICS **********************/
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			Locations.CONFIG_LOCATION);
	private static ItemResource itemResource = new ItemResource();
	static {
		itemResource.setItemService((ItemService) context
				.getBean("itemService"));
		itemResource.setModelItemService((ModelItemService) context
				.getBean("modelItemService"));
		itemResource.setModelService((ModelService) context
				.getBean("modelService"));
	}

	@Resource
	private ModelService modelService;
	@Resource
	private ModelItemService modelItemService;

	private ItemDao itemDao;

	@Test
	@Rollback(true)
	public void testGetItem() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testGetItem");
		itemResource.setModelService(modelService);
		itemResource.setModelItemService(modelItemService);

		itemDao = (ItemDao) context.getBean("itemDao");
		Item item = itemDao.getItemsByPosition(0, 1).get(0);
		SecurityContext securityContext = mock(SecurityContext.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("quetzalcoatl");
		when(securityContext.getUserPrincipal()).thenReturn(principal);

		Response response = itemResource.getItem(item.getId(), securityContext,
				httpServletRequest);
		ItemDto dto = (ItemDto)response.getEntity();
		assertNotNull("failed to retrieve dto", dto);
		assertEquals("wrong code", item.getCode(), dto.getCode());
		assertEquals("wrong description", item.getLatestDescription(),
				dto.getDescription());
		assertEquals("wrong id", item.getId(), dto.getId());

		System.out.println("TEST: Done");
	}

	@Test
	@Rollback(true)
	public void testSearchItem() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSearchItem");
		itemResource.setModelService(modelService);
		itemResource.setModelItemService(modelItemService);

		itemDao = (ItemDao) context.getBean("itemDao");
		SecurityContext securityContext = mock(SecurityContext.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("quetzalcoatl");
		when(securityContext.getUserPrincipal()).thenReturn(principal);

		Response response = itemResource.searchItem("turn", "1", false);
		GenericEntity<List<ModelItemDto>> entity = (GenericEntity<List<ModelItemDto>>)response.getEntity();
		List<ModelItemDto> dtos = (List<ModelItemDto>)entity.getEntity();
		assertNotNull("failed to retrieve dto", dtos);

		System.out.println("TEST: Done");
	}

	@Test
	@Rollback(true)
	public void testSearchItemDefinition() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSearchItemDefinition");
		itemDao = (ItemDao) context.getBean("itemDao");
		SecurityContext securityContext = mock(SecurityContext.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("quetzalcoatl");
		when(securityContext.getUserPrincipal()).thenReturn(principal);

		Response response = itemResource.searchItem("stuff", "1", true);
		GenericEntity<List<ModelItemDto>> entity = (GenericEntity<List<ModelItemDto>>)response.getEntity();
		List<ModelItemDto> dtos = (List<ModelItemDto>)entity.getEntity();
		assertNotNull("failed to retrieve dto", dtos);

		System.out.println("TEST: Done");
	}

	@Test
	public void testSearchChildModel() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSearchChildModel");

		ModelService mockModelService = mock(ModelService.class);
		ModelItemService mockModelItemService = mock(ModelItemService.class);

		Model mockParent = mock(Model.class);
		when(mockParent.getId()).thenReturn(4);
		Model mockChild = mock(Model.class);
		when(mockChild.getId()).thenReturn(3);
		when(mockModelService.getModel(3)).thenReturn(mockChild);
		when(mockModelService.getFamilyParentModel(mockChild)).thenReturn(
				mockParent);

		List<ModelItem> parentItems = new ArrayList<ModelItem>();
		List<ModelItem> childItems = new ArrayList<ModelItem>();
		ModelItem mi1 = new ModelItem();
		mi1.setModelName("parent");
		ModelItem mi2 = new ModelItem();
		mi2.setModelName("child");
		parentItems.add(mi1);
		childItems.add(mi2);
		when(
				mockModelItemService.searchForItemViaDefinition("stuff",
						new int[] { 4 })).thenReturn(parentItems);
		when(
				mockModelItemService.searchForItemViaDefinition("stuff",
						new int[] { 3 })).thenReturn(childItems);

		itemDao = (ItemDao) context.getBean("itemDao");
		SecurityContext securityContext = mock(SecurityContext.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("quetzalcoatl");
		when(securityContext.getUserPrincipal()).thenReturn(principal);
		itemResource.setModelService(mockModelService);
		itemResource.setModelItemService(mockModelItemService);
		Response response = itemResource.searchItem("stuff", "3", true);
		GenericEntity<List<ModelItemDto>> entity = (GenericEntity<List<ModelItemDto>>)response.getEntity();		
		List<ModelItemDto> dtos = (List<ModelItemDto>)entity.getEntity();
		assertNotNull("failed to retrieve dto", dtos);

		// make sure that the items came from parent
		for (ModelItemDto mid : dtos) {
			assertEquals("incorrect model name", "parent", mid.getModelName());
		}

		System.out.println("TEST: Done");
	}

}
