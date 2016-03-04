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
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.isA;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.rest.dto.ModelSummaryDto;
import uk.gov.ofwat.fountain.util.Locations;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ModelResourceTest extends AbstractTransactionalJUnit4SpringContextTests{

	
	/************** STATICS **********************/
	private static ApplicationContext context = new ClassPathXmlApplicationContext(Locations.CONFIG_LOCATION);
	private static ModelResource modelResource = new ModelResource();

	private ModelDao modelDao;
	
	@Test
	@Rollback(true)
	public void testGetModel(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetModel");
		modelResource.setModelService((ModelService)context.getBean("modelService"));
		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		modelDao = (ModelDao)context.getBean("modelDao");
		int modelTypeId = modelDao.getAllModelTypes().get(0).getId(); 
		Model model = modelDao.getAllModels(modelTypeId).get(0);
		Response response = modelResource.getModel(model.getId(), uriInfo, mock(SecurityContext.class));
		ModelDto dto = (ModelDto)response.getEntity();
		assertNotNull("failed to get modelDto", dto);
		assertEquals("Code doesn't match", model.getCode(), dto.getCode());
		assertEquals("Name doesn't match", model.getName(), dto.getName());
		assertEquals("Type doesn't match", model.getModelType().getId(), dto.getModelTypeId());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testGetModels(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetModels");
		modelResource.setModelService((ModelService)context.getBean("modelService"));
		UriInfo uriInfo = mock(UriInfo.class);
		modelDao = (ModelDao)context.getBean("modelDao");
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		ModelType type = modelDao.getAllModelTypes().get(0);
		int daoListSize = modelDao.getAllModels(type.getId()).size(); 
		Response response = modelResource.getModels(type.getId(), uriInfo);
		@SuppressWarnings("unchecked")
		GenericEntity<List<ModelSummaryDto>> entity = (GenericEntity<List<ModelSummaryDto>>)response.getEntity();
		List<ModelSummaryDto> dtos = (List<ModelSummaryDto>)entity.getEntity();
		assertNotNull("no model summaries returned", dtos);
		assertEquals("incorrect size of results", daoListSize, dtos.size());
		for(ModelSummaryDto dto: dtos){
			assertEquals("wrong type name", type.getName(), dto.getModelType());
		}
	
		System.out.println("TEST: Done");
}
	
	
	@Test
	public void testUpdateModel_oustandingEditsPresent() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateModel_oustandingEditsPresent");
		Branch branch = new Branch();
		ModelSummaryDto modelSummaryDto = new ModelSummaryDto();
		modelSummaryDto.setBranch(branch);
		BasketService mockBasketService = mock(BasketService.class);
		List<UserEdit> userEdits = new ArrayList<UserEdit>();
		UserEdit userEdit = new UserEdit();
		userEdits.add(userEdit);
		when(mockBasketService.getEditsForBranch(branch)).thenReturn(userEdits);
		
		
		ModelResource modelResource = new ModelResource();
		modelResource.setBasketService(mockBasketService);
		try {
			modelResource.updateModel(modelSummaryDto, mock(SecurityContext.class));
			fail("Should have got 409 CONFLICT.");
		} catch (WebApplicationException e) {
			assertEquals("expected 409 status (HTTP CONFLICT)", 409, e.getResponse().getStatus());
		}
		System.out.println("TEST: Done");
		
	}
	
	
	@Test
	public void testUpdateModel() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateModel");
		Branch branch = new Branch();
		ModelSummaryDto modelSummaryDto = new ModelSummaryDto();
		modelSummaryDto.setId(12);
		modelSummaryDto.setCode("code");
		modelSummaryDto.setDisplayOrder(2);
		modelSummaryDto.setFamily(new ModelFamily());
		modelSummaryDto.setModelType(ModelType.FOUNDATION.getName());
		modelSummaryDto.setName("name");
		modelSummaryDto.setParent(true);
		modelSummaryDto.setModelInputs(new ArrayList<ModelInput>());
		modelSummaryDto.setBranch(branch);
		
		BasketService mockBasketService = mock(BasketService.class);
		List<UserEdit> userEdits = new ArrayList<UserEdit>();
		when(mockBasketService.getEditsForBranch(branch)).thenReturn(userEdits);
		ModelService mockModelService = mock(ModelService.class);
		Model mockModel = mock(Model.class);;
		when(mockModelService.getModel(12)).thenReturn(mockModel);
		
		
		ModelResource modelResource = new ModelResource();
		modelResource.setBasketService(mockBasketService);
		modelResource.setModelService(mockModelService);
		modelResource.updateModel(modelSummaryDto, mock(SecurityContext.class));
		
		verify(mockModel).setBranch(branch);
		verify(mockModel).setCode("code");
		verify(mockModel).setDisplayOrder(2);
		verify(mockModel).setFamily(isA(ModelFamily.class));
		verify(mockModel).setModelType(ModelType.getByName(ModelType.FOUNDATION.getName()));
		verify(mockModel).setName("name");
		verify(mockModel).setParent(true);
		Map<String, ModelInput> modelInputs = new HashMap<String, ModelInput>();
		verify(mockModel).setModelInputs(modelInputs);

		verify(mockModelService).updateModel(mockModel);
		System.out.println("TEST: Done");
	}

	
//	@Test
//	public void testPutExternalModelSummary_newModel_codeInUse() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateModel");
//		ModelSummaryDto modelSummaryDto = new ModelSummaryDto();
//		modelSummaryDto.setId(0);
//		modelSummaryDto.setName("name");
//		modelSummaryDto.setCode("code");
//		ModelFamily modelFamily = new ModelFamily();
//		modelFamily.setCode("familyCode");
//		modelSummaryDto.setFamily(modelFamily);
//		modelSummaryDto.setFInputId(11);
//		modelSummaryDto.setFOutputId(12);
//
//		ModelService mockModelService = mock(ModelService.class);
//		Model preExistingModel = new Model();
//		preExistingModel.setCode("code");
//		when(mockModelService.getModel("code")).thenReturn(preExistingModel);
//		modelResource.setModelService(mockModelService);
//		try {
//			modelResource.putExternalModelSummary(modelSummaryDto, 7, mock(SecurityContext.class));
//			fail("expected a WebApplicationException");
//		} 
//		catch (WebApplicationException wae) {
//			Response response = wae.getResponse();
//			assertNotNull("no response", response);
//			assertEquals("expected 409 status (HTTP CONFLICT)", 409, response.getStatus());
//			assertEquals("Expected error message is incorrect.", "model code is already in use by another model", response.getEntity());
//		}
//	}
	
}
