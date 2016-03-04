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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.dao.PotDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;


public class ModelServiceTest extends TestCase {
	
	private ModelDao mockModelDao;
	private TableDao mockTableDao;
	private PotDao mockPotDao;
	private ModelPropertiesMapDao mockModelPropertiesMapDao;
	
	private ModelServiceImpl modelService;
	private ReferenceService mockReferenceService;
	private ItemService mockItemService;
	private DataService mockDataService;
	private GroupService mockGroupService;
	
	private Model mockModel;
	private List<Pot> pots;
	private BranchDao mockBranchDao; 
	private Branch branch;
	private RunTagService mockRunTagService;

	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		mockModelDao = mock(ModelDao.class);
		mockTableDao = mock(TableDao.class);
		mockPotDao = mock(PotDao.class);
		mockModelPropertiesMapDao = mock(ModelPropertiesMapDao.class);
		mockReferenceService = mock(ReferenceService.class);
		mockItemService = mock(ItemService.class);
		mockDataService = mock(DataService.class);
		mockGroupService = mock(GroupService.class);
		mockBranchDao = mock(BranchDao.class);
		mockRunTagService = mock(RunTagService.class);
		
		modelService = new ModelServiceImpl();
		modelService.setReferenceService(mockReferenceService);
		modelService.setDataService(mockDataService);
		modelService.setItemService(mockItemService);
		modelService.setModelDao(mockModelDao);
		modelService.setTableDao(mockTableDao);
		modelService.setPotDao(mockPotDao);
		modelService.setModelPropertiesMapDao(mockModelPropertiesMapDao);
		modelService.setBranchDao(mockBranchDao);
		modelService.setRunTagService(mockRunTagService);

		mockModel = mock(Model.class);
		
		pots = new ArrayList<Pot>(); 
	
		branch = new Branch();
		branch.setId(4);

		System.out.println("TEST: Done");
}

	public void testGetModel() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetModel");
		when(mockModelDao.findById(1)).thenReturn(mockModel);

		setBranchOnModel();

		Assert.assertEquals(mockModel, modelService.getModel(1));
		
		verifySetBranchOnModel();
		verifySetServicesOnModel();
		System.out.println("TEST: Done");
}
	
	public void testGetModels() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetModels");
		List<Model> models = new ArrayList<Model>();
		models.add(mockModel);
		
		when(mockModelDao.getAllModels(1)).thenReturn(models);
		setBranchOnModel();

		List<Model> testModels = modelService.getModels(1);
		Assert.assertEquals(models, testModels);
		Assert.assertEquals(models.get(0), testModels.get(0));
	
		verifySetBranchOnModel();
		verifySetServicesOnModel();
		System.out.println("TEST: Done");
}

	public void testGetTable() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetTable");
		Table table = new Table();
		when(mockTableDao.findById(1)).thenReturn(table);
		getPotsForTable();
		
		ArrayList<Pot> testPots = new ArrayList<Pot>();
		Pot pot11 = new Pot();
		Item pot11Item = new Item();
		pot11Item.setCode("B101");
		pot11.setItem(pot11Item);
		Interval pot11Interval = new Interval();
		pot11Interval.setName("2011-12");
		pot11.setInterval(pot11Interval);
		testPots.add(pot11);

		Pot pot12 = new Pot();
		Item pot12Item = new Item();
		pot12Item.setCode("B102");
		pot12.setItem(pot12Item);
		Interval pot12Interval = new Interval();
		pot12Interval.setName("2012-13");
		pot12.setInterval(pot12Interval);
		testPots.add(pot12);
		
		table.setPots(testPots);

		Table testTable = modelService.getTable(1);
		Assert.assertEquals(table, testTable);
		Collection<Pot> testTablePots = (Collection<Pot>) testTable.getPots().values();
		Collection<Pot> tablePots = (Collection<Pot>) table.getPots().values();
		Assert.assertEquals(testTablePots.toArray()[0], tablePots.toArray()[0]);
	
		System.out.println("TEST: Done");
}
	
	public void testGetTablesForModel(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetTablesForModel");
		Table mockTable = mock(Table.class);
		
		List<Table> tables = new ArrayList<Table>(); 
		tables.add(mockTable);
		when(mockTableDao.findByModelId(1)).thenReturn(tables);
		getPotsForTable();
		mockTable.setPots(pots);
		
		List<Table> testTables = modelService.getTablesForModel(1);
		Assert.assertEquals(tables, testTables);
		Assert.assertEquals(tables.get(0), testTables.get(0));
	
		System.out.println("TEST: Done");
}
	
	public void testGetModelCodeForInput() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetModelCodeForInput");
		ModelInput mockModelInput0 = mock(ModelInput.class);
		
		when(mockModelDao.getInputModelCode("m0", 0)).thenReturn("model0");
		when(mockModelInput0.getChildModelCode()).thenReturn("model0");
		when(mockModelInput0.getCode()).thenReturn("m0");
		
		String modelCode = modelService.getModelCodeForInput("m0", mockModel.getId());
		Assert.assertEquals("", mockModelInput0.getChildModelCode(), modelCode);

		System.out.println("TEST: Done");
}
	
	public void testGetPotsForTable(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPotsForTable");
		getPotsForTable();
		
		List<Pot> testPots = modelService.getPotsForTable(1);
		Assert.assertEquals(pots, testPots);
		Assert.assertEquals(pots.get(0), testPots.get(0));
	
		System.out.println("TEST: Done");
}
	
	public void testCreatePot() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreatePot");
		ItemProperties mockItemProperties = mock(ItemProperties.class);
		when(mockModel.getItemPropertiesForItem("code")).thenReturn(mockItemProperties);
		Item item = new Item();
		item.setId(1);

		when(mockModel.getId()).thenReturn(1);
		when(mockModelDao.findById(1)).thenReturn(mockModel);
		when(mockModel.getBranchId()).thenReturn(1);
		when(mockBranchDao.findById(4)).thenReturn(branch);
		
		when(mockItemProperties.getItem()).thenReturn(item);
		ModelPropertiesMap mockMpm = mock(ModelPropertiesMap.class);
		when(mockModelPropertiesMapDao.findByModelAndItem(1, 1)).thenReturn(mockMpm);
		
		Integer runId = 1;
		Run run = new Run();
		run.setId(2);
		RunTag runTag = new RunTag(run, RunModelTag.LATEST);
		when(mockRunTagService.getRunTag(runId, RunModelTag.LATEST.getId())).thenReturn(runTag);

		Interval interval = new Interval();
		Pot pot = new Pot();
		pot.setInterval(interval);
		pot.setItem(item);
		pot.setDataService(mockDataService);
		pot.setReferenceService(mockReferenceService);
		pot.setGroupService(mockGroupService);
		pot.setRunTag(runTag);
		
		Assert.assertEquals(pot, modelService.createPot(mockModel, "code", interval, runId));
	
		System.out.println("TEST: Done");
}
	
	private void getPotsForTable() {
		Pot mockPot = mock(Pot.class);
		pots.add(mockPot);
		Model mockModel = mock(Model.class);
		when(mockPotDao.findByTableId(1)).thenReturn(pots);
		mockPot.setDataService(mockDataService);
		mockPot.setReferenceService(mockReferenceService);
		mockPot.setGroupService(mockGroupService);
		Interval mockInterval = mock(Interval.class);
		Item mockItem = mock(Item.class);
		when(mockPot.getInterval()).thenReturn(mockInterval);
		when(mockInterval.getName()).thenReturn("2011-12");

		when(mockPot.getItem()).thenReturn(mockItem);
		when(mockItem.getCode()).thenReturn("B101");

		when(mockPot.getModel()).thenReturn(mockModel);
		mockPot.setInterval(mockInterval);
	}

	private void verifySetServicesOnModel() {
		verify(mockModel).setModelService(modelService);
		verify(mockModel).setItemService(mockItemService);
	}
	
	private void setBranchOnModel() {
		when(mockModel.getBranchId()).thenReturn(4);
		when(mockBranchDao.findById(4)).thenReturn(branch);
	}
	
	private void verifySetBranchOnModel() {
		verify(mockModel).setBranch(branch);
	}
	
	public void testCreateModel(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreateModel");
		String name = "testName";
		String code = "testCode";
		String type = "capture system";
		String branch = null;
		
		when(mockModelDao.create(any(Model.class))).thenReturn(17);
		when(mockModelDao.findModelTypeByName(type)).thenReturn(ModelType.ICS);

		Model rtn = modelService.createModel(name, code, type, null, branch, false, 0, 0);
		assertNotNull("failed to create model", rtn);
	
		System.out.println("TEST: Done");
	}
	
	public void testUpdateModel_noChangeToBranch() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateModel_noChangeToBranch");
		Model model = new Model("name", "code", ModelType.ICS);
		model.setBranch(branch);
		
		when(mockBranchDao.findById(3)).thenReturn(branch);
		modelService.updateModel(model);
		
		verify(mockModelDao).update(model);
		System.out.println("TEST: Done");
	}

	public void testUpdateModel_BranchChanged() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateModel_BranchChanged");
		Branch changedBranch = new Branch();
		changedBranch.setId(3);
		Model model = new Model("name", "code", ModelType.ICS);
		model.setBranch(changedBranch);
		Branch branchFromDB = new Branch();
		branchFromDB.setId(5);
		
		when(mockBranchDao.findById(3)).thenReturn(branchFromDB);
		modelService.updateModel(model);
		
		verify(mockBranchDao).update(changedBranch);
		verify(mockModelDao).update(model);
		System.out.println("TEST: Done");
	}
	
	public void testAddModelRunDependency(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddModelRunDepdendency");

		when(mockModelDao.findModelTypeByName("EXTERNAL")).thenReturn(ModelType.EXTERNAL);
		when(mockModelDao.create(isA(Model.class))).thenReturn(0);
		Model dependant = modelService.createModel("dependant", "parentCode", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);

		when(mockModelDao.create(isA(Model.class))).thenReturn(1);
		Model dependancy1 = modelService.createModel("dependancy1", "dep1Code", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);
		when(mockModelDao.findById(dependant.getId())).thenReturn(dependant);
		modelService.addDependency(dependant.getId(), dependancy1.getId());
		verify(mockModelDao).addModelDependency(dependant.getId(), dependancy1.getId());

		when(mockModelDao.create(isA(Model.class))).thenReturn(2);
		Model dependancy2 = modelService.createModel("dependancy2", "dep2Code", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);
		when(mockModelDao.findById(dependant.getId())).thenReturn(dependant);
		modelService.addDependency(dependant.getId(), dependancy2.getId());
		verify(mockModelDao).addModelDependency(dependant.getId(), dependancy2.getId());
	}	
	
	public void testGetModelRunDependency(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddModelRunDepdendency");

		when(mockModelDao.findModelTypeByName("EXTERNAL")).thenReturn(ModelType.EXTERNAL);
		when(mockModelDao.create(isA(Model.class))).thenReturn(0);
		Model dependant = modelService.createModel("dependant", "parentCode", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);

		when(mockModelDao.create(isA(Model.class))).thenReturn(1);
		Model dependancy1 = modelService.createModel("dependancy1", "dep1Code", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);

		when(mockModelDao.create(isA(Model.class))).thenReturn(2);
		Model dependancy2 = modelService.createModel("dependancy2", "dep2Code", ModelType.EXTERNAL.getName(), null, "", false, 1, 0);

		when(mockModelDao.findById(dependant.getId())).thenReturn(dependant);
		List<Model> dependencies = new ArrayList<Model>();
		dependencies.add(dependancy1);
		dependencies.add(dependancy2);
		when(mockModelDao.getModelRunDependencies(dependant.getId())).thenReturn(dependencies);
		List<Model> deps = modelService.getModelRunDependencies(dependant.getId());
		assertTrue(deps.size() == 2);
		
	}	
	
	public void testRemoveModelRunDependency(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testRemoveModelRunDepdendency");
	}
	
}

