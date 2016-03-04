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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;

public class ModelTest extends TestCase {

	private ModelService mockModelService;
	private ItemService mockItemService;
	private Model model;
	private Interval mockYear;
	private ModelInput mockModelInput0;
	private ModelInput mockModelInput1;
	private ItemProperties mockItemProperties0; 
	private ItemProperties mockItemProperties1; 

	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		mockModelService = mock(ModelService.class);
		mockItemService = mock(ItemService.class);
		
		model = new Model("the model under test", "TEST1", ModelType.ICS);
		model.setBranch(new Branch(1, "sketch1", true));
		model.setId(1);
		model.setModelService(mockModelService);
		model.setItemService(mockItemService);
	
		System.out.println("TEST: Done");
}
	
	public void testGetPot_foundInTables() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPot_foundInTables");
		mockYear = mock(Interval.class);
		when(mockYear.getName()).thenReturn("4011-12");
		
		Pot mockPot = mock(Pot.class);
		mockFindPotFromAmongstTables(mockPot);

		Assert.assertEquals("", mockPot, model.getPot("B301", mockYear));
	
		System.out.println("TEST: Done");
}

	public void testGetPot_created() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPot_created");
		mockYear = mock(Interval.class);
	
		Pot mockPot23 = mock(Pot.class);

		mockFindPotFromAmongstTables();
		model.setRunId(1);
		when(mockModelService.createPot(model, "item23", mockYear, model.getRunId())).thenReturn(mockPot23);
		
		Assert.assertEquals("", mockPot23, model.getPot("item23", mockYear));
	
		System.out.println("TEST: Done");
}

	private void mockFindPotFromAmongstTables() {
		mockFindPotFromAmongstTables(mock(Pot.class));
	}
	
	private void mockFindPotFromAmongstTables(Pot mockPot) {
		List<Table> tables = new ArrayList<Table>();
		Table mockTable1 = mock(Table.class);
		Table mockTable2 = mock(Table.class);
		
		Map<String, Pot> potsForTable1 = new HashMap<String, Pot>();
		Pot mockPot11 = mock(Pot.class);
		potsForTable1.put("B101" + "2011-12", mockPot11);

		Pot mockPot12 = mock(Pot.class);
		potsForTable1.put("B102" + "2012-13", mockPot12);
		
		Map<String, Pot> potsForTable2 = new HashMap<String, Pot>();
		Pot mockPot21 = mock(Pot.class);
		potsForTable2.put("B201" + "3011-12", mockPot21);
		Pot mockPot22 = mockPot;
		potsForTable2.put("B301" + "4011-12", mockPot22);
		
		tables.add(mockTable1);
		tables.add(mockTable2);
		model.setTables(tables);

		Item mockItem11 = mock(Item.class);
		Item mockItem12 = mock(Item.class);
		Item mockItem21 = mock(Item.class);
		Item mockItem22 = mock(Item.class);
		
		when(mockTable1.getPots()).thenReturn(potsForTable1);
		when(mockPot11.getItem()).thenReturn(mockItem11);
		when(mockItem11.getCode()).thenReturn("item11");
		when(mockPot12.getItem()).thenReturn(mockItem12);
		when(mockItem12.getCode()).thenReturn("item12");

		when(mockTable2.getPots()).thenReturn(potsForTable2);
		when(mockPot21.getItem()).thenReturn(mockItem21);
		when(mockItem21.getCode()).thenReturn("item21");
		when(mockPot22.getItem()).thenReturn(mockItem22);
		when(mockItem22.getCode()).thenReturn("item22");
		when(mockPot22.getInterval()).thenReturn(mockYear);
	}

	

	public void testGetInputModels() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetInputModels");
		mockGetInputModels();
	
		System.out.println("TEST: Done");
}

	private void mockGetInputModels() {
		mockModelInput0 = mock(ModelInput.class);
		mockModelInput1 = mock(ModelInput.class);
		
		when(mockModelService.getModelCodeForInput("m0",model.getId())).thenReturn("model0");
		when(mockModelInput0.getChildModelCode()).thenReturn("model0");
		when(mockModelService.getModelCodeForInput("m1",model.getId())).thenReturn("model1");
		when(mockModelInput1.getChildModelCode()).thenReturn("model1");
		
		when(mockModelInput0.getCode()).thenReturn("m0");
		when(mockModelInput1.getCode()).thenReturn("m1");

		model.addModelInput(mockModelInput0);
		model.addModelInput(mockModelInput1);
		
		Map<String, ModelInput> inputModels =  model.getModelInputs();
		Assert.assertEquals("", mockModelInput0, inputModels.get("m0"));
		Assert.assertEquals("", mockModelInput1, inputModels.get("m1"));
	}
	
	public void testGetInputModel_whereModelIsNotAnInput() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetInputModel_whereModelIsNotAnInput");
		mockGetInputModels();

		Assert.assertEquals("", null, model.getInputModel("m3"));
	
		System.out.println("TEST: Done");
}
	
	public void testGetItemPropertiesForItem_withId() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetItemPropertiesForItem_withId");
		mockGetItemProperties();
		Assert.assertEquals("", mockItemProperties0, model.getItemPropertiesForItem(0));
		Assert.assertEquals("", mockItemProperties1, model.getItemPropertiesForItem(1));
	
		System.out.println("TEST: Done");
}

	public void testGetItemPropertiesForItem_withCode() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetItemPropertiesForItem_withCode");
		mockGetItemProperties();
		Assert.assertEquals("", mockItemProperties0, model.getItemPropertiesForItem("item0"));
		Assert.assertEquals("", mockItemProperties1, model.getItemPropertiesForItem("item1"));
	
		System.out.println("TEST: Done");
}

	public void testGetItemProperties() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetItemProperties");
		mockGetItemProperties();
	
		System.out.println("TEST: Done");
}
	
	public void mockGetItemProperties() {
		mockItemProperties0 = mock(ItemProperties.class); 
		mockItemProperties1 = mock(ItemProperties.class); 

		HashMap<Integer, ItemProperties> itemProperties = new HashMap<Integer, ItemProperties>();
		itemProperties.put(0, mockItemProperties0);
		itemProperties.put(1, mockItemProperties1);
		
		when(mockItemService.getPropertiesForModel(1)).thenReturn(itemProperties);
		when(mockItemService.getPropertiesForItemAndModel(0, 1)).thenReturn(mockItemProperties0);
		when(mockItemService.getPropertiesForItemAndModel(1, 1)).thenReturn(mockItemProperties1);
		when(mockItemService.getPropertiesForItemAndModel("item0", 1)).thenReturn(mockItemProperties0);
		when(mockItemService.getPropertiesForItemAndModel("item1", 1)).thenReturn(mockItemProperties1);
		
		Item mockItem0 = mock(Item.class);
		when(mockItemProperties0.getItem()).thenReturn(mockItem0);
		when(mockItem0.getCode()).thenReturn("item0");

		Item mockItem1 = mock(Item.class);
		when(mockItemProperties1.getItem()).thenReturn(mockItem1);
		when(mockItem1.getCode()).thenReturn("item1");
		
	}

}
