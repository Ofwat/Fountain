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
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.LockService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.rest.dto.BasketDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.IntervalDto;
import uk.gov.ofwat.fountain.rest.dto.ItemDto;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;
import uk.gov.ofwat.fountain.rest.dto.factory.BasketDtoFactory;
import uk.gov.ofwat.fountain.rest.dto.factory.UserEditDtoFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class BasketResourceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/************** STATICS **********************/
	private static BasketResource basketResource = null;
	private static BasketService mockBasketService = mock(BasketService.class);	
	private static LockService mockLockService = mock(LockService.class);
	private static UserService mockUserService = mock(UserService.class);
	private static UserEditDtoFactory mockUserEditDtoFactory = mock(UserEditDtoFactory.class);
	private static BasketDtoFactory mockBasketDtoFactory = mock(BasketDtoFactory.class);
	
	private static SecurityContext mockSecurityContext = mock(SecurityContext.class);
	private static User mockUser = mock(User.class);
	private static Principal mockPrincipal = mock(Principal.class);

	static{
		basketResource = new BasketResource();
		basketResource.setLockService(mockLockService);
		basketResource.setBasketService(mockBasketService);
		basketResource.setUserService(mockUserService);
		basketResource.setBasketDtoFactory(mockBasketDtoFactory);
		basketResource.setUserEditDtoFactory(mockUserEditDtoFactory);
	}
	
	@Before
	public void onSetUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":onSetUp");
		when(mockPrincipal.getName()).thenReturn("quetzalcoatl");
		when(mockSecurityContext.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockUserService.getUser(mockSecurityContext)).thenReturn(mockUser);
		when(mockUser.getName()).thenReturn("quetzalcoatl");
		when(mockUser.getId()).thenReturn(2334);
	
		System.out.println("TEST: Done");
}

	@Test
	@Rollback(true)
	public void testGetBasket(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetBasket");

		Basket mockCache = mock(Basket.class);
		List<UserEdit> edits = new ArrayList<UserEdit>();
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);
		when(mockBasketDtoFactory.createBasketDto(mockCache)).thenReturn(setupBasketDto(mockUser));
		
		Response response = basketResource.getBasket(mockSecurityContext);
		assertNotNull("no response", response);
		assertEquals("expected 200 status (HTTP SUCCESS)", 200, response.getStatus());
		BasketDto dto = (BasketDto)response.getEntity();
		assertNotNull("no dto", dto);
		assertEquals("audit incorrect", "test audit", dto.getAudit());
		Iterable<UserEditDto> rtn =  dto.getEdits();
		int count = 0;
		for(UserEditDto editDto: rtn){
			count++;
			assertEquals("incorrect item id", 1, editDto.getItemDto().getId());
			assertEquals("incorrect company id", 2, editDto.getCompany().getId());
			assertEquals("incorrect group entry id", 3, editDto.getGroupEntry().getId());
			assertEquals("incorrect value for interval", "7", editDto.getValue());
		}
		assertEquals("wrong number of dtos", 1, count);
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testGetBasket_whereCacheIsNull(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetBasket_whereCacheIsNull");

		Basket mockCache = null;

		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);
		
		Response response = basketResource.getBasket(mockSecurityContext);
		assertNotNull("no response", response);
		assertEquals("expected 404 status (HTTP NOT FOUND)", 404, response.getStatus());
	
		System.out.println("TEST: Done");
}
	
	private BasketDto setupBasketDto(User user) {
		BasketDto basketDto = new BasketDto();
		basketDto.setAudit("test audit");
		List<UserEditDto> edits = new ArrayList<UserEditDto>();
		edits.add(setupUserEditDto(user));
		basketDto.setEdits(edits);
		return basketDto;
	}

	
	private UserEditDto setupUserEditDto(User user) {
		UserEditDto edit1 = new UserEditDto();
		ItemDto itemDto = new ItemDto();
		itemDto.setId(1);
		edit1.setItemDto(itemDto);
		Company company = new Company();
		company.setId(2);
		edit1.setCompany(company);
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setId(3);
		edit1.setGroupEntry(groupEntry);
		edit1.setId(4);
		IntervalDto intervalDto = new IntervalDto();
		intervalDto.setId(5);
		edit1.setIntervalDto(intervalDto);
		edit1.setUser(user);
		edit1.setValue("7");
		edit1.setOriginal("0");
		edit1.setEditType(EditType.CONFIDENCE_GRADE);
		return edit1;
	}

	@Test
	@Rollback(true)
	public void testPutBasket_whereBasketDtoIsNullAndCacheIsNull() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testPutBasket_whereBasketDtoIsNullAndCacheIsNull");
		
		Basket mockCache = mock(Basket.class);
		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getRequestUri()).thenReturn(new URI("http://sample.domain"));
		
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(null);
		when(mockBasketService.createBasket(mockUser)).thenReturn(mockCache);
		
		Response response = basketResource.putBasket(mockSecurityContext);
		assertNotNull("response is null", response);
		assertEquals("expected 201 status (HTTP CREATED)", 201, response.getStatus());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEdits_Value() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_Value");
		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setBranch(null);

		DataDto dataDto = setupDataDto();
		dataDto.setLockingUser("quetzalcoatl");
		dataDto.setModelDto(mockModelDto);
		
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(true);
		
		Basket mockCache = mock(Basket.class);
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);

		UserEdit userEdit = new UserEdit(mockUser, dataDto, EditType.VALUE);
		assertEquals("userEdit original is incorrect" , "10", userEdit.getOriginal());
		assertEquals("userEdit value is incorrect" , "11", userEdit.getValue());
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-0");

		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getBaseUri()).thenReturn(new URI("http://sample.domain/"));
		
		Response response = basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
		assertNotNull("response is null", response);
		assertEquals("expected 201 status (HTTP CREATED)", 201, response.getStatus());
		Object o = response.getMetadata().get("location");
		assertNotNull("no location", o);
		URI location = (URI)((List)o).get(0);
		assertNotNull("no location uri", location);
		assertEquals("incorrect url", "http://sample.domain/basket/edits/23-34-100-33-0", location.toString());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEdits_Percene() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_Value");
		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setBranch(null);

		DataDto dataDto = setupDataDto();
		dataDto.setLockingUser("quetzalcoatl");
		dataDto.setModelDto(mockModelDto);
		dataDto.getItem().setUnit("%");
		
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(true);
		
		Basket mockCache = mock(Basket.class);
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);

		UserEdit userEdit = new UserEdit(mockUser, dataDto, EditType.VALUE);
		assertEquals("userEdit original is incorrect" , "0.1", userEdit.getOriginal());
		assertEquals("userEdit value is incorrect" , "0.11", userEdit.getValue());
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-0");

		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getBaseUri()).thenReturn(new URI("http://sample.domain/"));
		
		Response response = basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
		assertNotNull("response is null", response);
		assertEquals("expected 201 status (HTTP CREATED)", 201, response.getStatus());
		Object o = response.getMetadata().get("location");
		assertNotNull("no location", o);
		URI location = (URI)((List)o).get(0);
		assertNotNull("no location uri", location);
		assertEquals("incorrect url", "http://sample.domain/basket/edits/23-34-100-33-0", location.toString());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEditsToBranch() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_Value");
		Branch branch = new Branch();
		branch.setId(14);
		branch.setName("PS");
		branch.setEditable(true);
		ModelDto modelDto = new ModelDto();
		modelDto.setBranch(branch);

		DataDto dataDto = setupDataDto();
		dataDto.setLockingUser("quetzalcoatl");
		dataDto.setModelDto(modelDto);
		
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(true);
		
		Basket mockCache = mock(Basket.class);
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);

		UserEdit userEdit = new UserEdit(mockUser, dataDto, EditType.VALUE);
		assertEquals("userEdit original is incorrect" , "10", userEdit.getOriginal());
		assertEquals("userEdit value is incorrect" , "11", userEdit.getValue());
		assertEquals("branch is incorrect" , 14, userEdit.getBranchId());
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-0");

		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getBaseUri()).thenReturn(new URI("http://sample.domain/"));
		
		Response response = basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
		assertNotNull("response is null", response);
		assertEquals("expected 201 status (HTTP CREATED)", 201, response.getStatus());
		Object o = response.getMetadata().get("location");
		assertNotNull("no location", o);
		URI location = (URI)((List)o).get(0);
		assertNotNull("no location uri", location);
		assertEquals("incorrect url", "http://sample.domain/basket/edits/23-34-100-33-0", location.toString());
	
		
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEdits_CG() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_CG");
		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setBranch(null);

		DataDto dataDto = setupDataDto();
		dataDto.setValue("");
		dataDto.setUpdatedValue("");
		dataDto.setConfidenceGrade("A1");
		dataDto.setUpdatedConfidenceGrade("A2");
		dataDto.setLockingUser("quetzalcoatl");
		dataDto.setModelDto(mockModelDto);
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(true);
		
		Basket mockCache = mock(Basket.class);
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);

		UserEdit userEdit = new UserEdit(mockUser, dataDto, EditType.CONFIDENCE_GRADE);
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-1");

		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getBaseUri()).thenReturn(new URI("http://sample.domain/"));
		
		Response response = basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
		assertNotNull("response is null", response);
		assertEquals("expected 201 status (HTTP CREATED)", 201, response.getStatus());
		Object o = response.getMetadata().get("location");
		assertNotNull("no location", o);
		URI location = (URI)((List)o).get(0);
		assertNotNull("no location uri", location);
		assertEquals("incorrect url", "http://sample.domain/basket/edits/23-34-100-33-1", location.toString());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEdits_ValueAndCG_BothChanged() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_ValueAndCG_BothChanged");
		ModelDto mockModelDto = new ModelDto();
		mockModelDto.setBranch(null);

		DataDto dataDto = setupDataDto();
		dataDto.setConfidenceGrade("A1");
		dataDto.setUpdatedConfidenceGrade("A2");
		dataDto.setLockingUser("quetzalcoatl");
		dataDto.setModelDto(mockModelDto);
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(true);
		
		Basket mockCache = mock(Basket.class);
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);

		UserEdit userEdit = new UserEdit(mockUser, dataDto, EditType.VALUE);
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-0");
		userEdit = new UserEdit(mockUser, dataDto, EditType.CONFIDENCE_GRADE);
		when(mockBasketService.saveEdit(userEdit, mockUser)).thenReturn("23-34-100-33-1");

		UriInfo mockUriInfo = mock(UriInfo.class);
		when(mockUriInfo.getBaseUri()).thenReturn(new URI("http://sample.domain/"));
		
		try {
			basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
			fail("Should have got 409 CONFLICT.");
		} catch (WebApplicationException e) {
			assertEquals("expected 409 status (HTTP CONFLICT)", 409, e.getResponse().getStatus());
		}
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddEdits_Unauthorised() throws URISyntaxException{
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddEdits_Unauthorised");
		DataDto dataDto = setupDataDto();
		
		when(mockSecurityContext.isUserInRole("quetzalcoatl")).thenReturn(false);
		UriInfo mockUriInfo = mock(UriInfo.class);
		
		try {
			basketResource.addEdit(dataDto, mockSecurityContext, mockUriInfo);
			fail("Should have got 401 UNAUTHORIZED.");
		} catch (WebApplicationException e) {
			assertEquals("expected 401 status (HTTP UNAUTHORIZED)", 401, e.getResponse().getStatus());
		}

	
		System.out.println("TEST: Done");
}
	
	private DataDto setupDataDto() {
		DataDto dataDto = new DataDto();
		dataDto.setCalculated(false);
		Team team = new Team();
		team.setName("A team");
		team.setCode("quetzalcoatl");
		Company company = new Company();
		company.setId(100);
		company.setName("test company");
		company.setCode("code");
		Item item = new Item();
		item.setCode("item1");
		item.setId(23);
		item.setLatestDescription("description");
		item.setName("test item");
		item.setUnit("not a percene");
		item.setTeam(team);
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setCompany(company);
		groupEntry.setDescription("test group entry");
		groupEntry.setId(33);
		IntervalDto intervalDto = new IntervalDto();
		intervalDto.setId(34);
		intervalDto.setIntervalTypeId(1);
		intervalDto.setName("test interval dto");
		dataDto.setCompany(company);
		dataDto.setItem(item);
		dataDto.setFormula("A+B");
		dataDto.setGroupEntry(groupEntry);
		dataDto.setValue("10");
		dataDto.setUpdatedValue("11");
		dataDto.setIntervalDto(intervalDto);
		return dataDto;
	}

	@Test
	@Rollback(true)
	public void testDeleteBasket() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDeleteBasket");
		Response response = basketResource.deleteBasket(mockSecurityContext);
		
		verify(mockLockService).clearLocksForUser(mockUser.getId());
		verify(mockBasketService).clearBasket(mockUser);

		assertNotNull("response is null", response);
		assertEquals("expected 204 status (HTTP NO CONTENT)", 204, response.getStatus());
	
		System.out.println("TEST: Done");
}


	
	@Test
	@Rollback(true)
	public void testGetEdit_cacheNull(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetEdit_cacheNull");
		String id = "1-2-3-4-" + EditType.VALUE.ordinal();
		Basket cache = null;
		
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(cache);
		
		UriInfo mockUriInfo = mock(UriInfo.class);
		Response response = basketResource.getValueEdit(id, mockSecurityContext, mockUriInfo);
		
		assertNotNull("response is null", response);
		assertEquals("expected 404 status (HTTP NOT FOUND)", 404, response.getStatus());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testGetEdit_editNotFound(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetEdit_editNotFound");
		String id = "1-2-3-4-" + EditType.VALUE.ordinal();
		Basket mockCache = mock(Basket.class);
		
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);
		when(mockCache.getEdit(id)).thenReturn(null);
		UriInfo mockUriInfo = mock(UriInfo.class);
		
		Response response = basketResource.getValueEdit(id, mockSecurityContext, mockUriInfo);
		
		assertNotNull("response is null", response);
		assertEquals("expected 404 status (HTTP NOT FOUND)", 404, response.getStatus());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testDeleteEdit() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDeleteEdit");
		String id = "1-2-3-4-" + EditType.VALUE.ordinal();
		Basket mockCache = mock(Basket.class);
		
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);
		
		Response response = basketResource.deleteEdit(id, mockSecurityContext);
		
		verify(mockCache).removeEdit(id);
		
		assertNotNull("response is null", response);
		assertEquals("expected 204 status (HTTP NO CONTENT)", 204, response.getStatus());
	
		System.out.println("TEST: Done");
}

	@Test
	@Rollback(true)
	public void testDeleteEdit_cacheIsNull() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDeleteEdit_cacheIsNull");
		String id = "1-2-3-4-" + EditType.VALUE.ordinal();
		Basket mockCache = null;
		
		when(mockBasketService.getBasketForUser(mockUser)).thenReturn(mockCache);
		
		Response response = basketResource.deleteEdit(id, mockSecurityContext);
		
		assertNotNull("response is null", response);
		assertEquals("expected 204 status (HTTP NO CONTENT)", 204, response.getStatus());
	
		System.out.println("TEST: Done");
}
}
