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

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.domain.formula.EvaluatableFormula;
import uk.gov.ofwat.fountain.domain.formula.FormulaContext;
import uk.gov.ofwat.fountain.domain.formula.FormulaParserImpl;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class PotTest extends TestCase {

	private int itemId;
	private String anEquation;
	private String value;
	private Interval mockYear;
	private Model mockModel;
	private Company mockCompany;
	private GroupEntry mockGroupEntry;
	private Item mockItem;
	private ItemProperties mockItemProperties;
	private DataService mockDataService;
	private Data mockData;
	private FormulaParserImpl mockEquationParser2Impl;
	private EvaluatableFormula mockEvaluatableEquation;
	private Basket mockBasket;
	private ModelService mockModelService;
	private BranchDao mockBranchDao;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		itemId = 1;
		value = "returned value";
		
		mockYear = mock(Interval.class);
		mockModel = mock(Model.class);
		mockCompany = mock(Company.class);
		mockGroupEntry = mock(GroupEntry.class);
		mockItem = mock(Item.class);
		mockItemProperties = mock(ItemProperties.class);
		mockDataService = mock(DataService.class);
		mockData = mock(Data.class);
		mockEquationParser2Impl = mock(FormulaParserImpl.class);
		mockEvaluatableEquation = mock(EvaluatableFormula.class);
		mockBasket = mock(Basket.class);
		mockModelService = mock(ModelService.class);
		mockBranchDao = mock(BranchDao.class);
		
		when(mockItem.getId()).thenReturn(itemId);
		when(mockModel.getItemPropertiesForItem(itemId)).thenReturn(mockItemProperties);
	
		System.out.println("TEST: Done");
}
	
	public void testUnTaggedModelsUseEquation(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUnTaggedModelsUseEquation");
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		when(mockModel.getBranch()).thenReturn(null);
		when(mockModel.getModelType()).thenReturn(ModelType.ICS);
		String equationResult = "equation result";
		anEquation = "anEquation";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(false);
		when(mockItemProperties.getGeneralFormula()).thenReturn(anEquation);
		when(mockItemProperties.getFormula(mockYear)).thenReturn(anEquation);
		when(mockEquationParser2Impl.parseFormula(anEquation)).thenReturn(mockEvaluatableEquation);
		when(mockEvaluatableEquation.evaluate(isA(FormulaContext.class))).thenReturn(equationResult);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, equationResult, cg, true)).thenReturn(mockData);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setFormulaParser(mockEquationParser2Impl);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		
		pot.getData(mockCompany, null, null, mockGroupEntry, true);
		// ensure that the equation was evaluated
		verify(mockEvaluatableEquation).evaluate(isA(FormulaContext.class));
		
	
		System.out.println("TEST: Done");
}
	
	
	
	public void testGetValue() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetValue");
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		
		anEquation = "";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);		
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		when(mockItem.getId()).thenReturn(23);
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Data data = pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true);
		Assert.assertEquals("", value, data.getValue());
		Assert.assertEquals("", cg, data.getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}

	public void testGetReadOnlyValue() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReadOnlyValue");
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		
		anEquation = "";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);		
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, true)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		when(mockItem.getId()).thenReturn(23);
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Data data = pot.getData(mockCompany, null, null, mockGroupEntry, true);
		Assert.assertEquals("", value, data.getValue());
		Assert.assertEquals("", cg, data.getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}

	public void testGetValue_fromBasket_onRun() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetValue_fromBasket");
		when(mockModelService.getModel(1)).thenReturn(mockModel);

		String cg = "cg";
		Pot pot = new Pot();
		when(mockYear.getId()).thenReturn(2);
		when(mockCompany.getId()).thenReturn(3);
		when(mockCompany.getId()).thenReturn(3);
		when(mockGroupEntry.getId()).thenReturn(4);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		UserEdit mockUserEdit = mock(UserEdit.class);
		
		Run run = new Run();
		run.setId(7);
		RunTag runTag = new RunTag(run , RunModelTag.LATEST);
		Branch branch = new Branch();
		branch.setId(14);

		when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);
//		when(mockModel.getBranchId()).thenReturn(14);
		when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);
		when(mockBasket.getEdit("i1-pnull-y2-c3-g4-mnull-b14-r7-t0")).thenReturn(mockUserEdit);
		when(mockBasket.getEdit("CGi1-pnull-y2-c3-g4-mnull-b14-r7-t0")).thenReturn(mockUserEdit);
		when(mockUserEdit.getValue()).thenReturn(value).thenReturn(cg);
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setFormulaParser(mockEquationParser2Impl);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		pot.setRunTag(runTag);
		pot.setBranchDao(mockBranchDao);

		Data data = pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true);
		Assert.assertEquals("", value, data.getValue());
		Assert.assertEquals("", cg, data.getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}

	public void testGetValue_fromEquation() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetValue_fromEquation");
		when(mockModelService.getModel(1)).thenReturn(mockModel);

		anEquation = "anEquation";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(false);
		when(mockItemProperties.getGeneralFormula()).thenReturn(anEquation);
		when(mockItemProperties.getFormula(mockYear)).thenReturn(anEquation);
		when(mockEquationParser2Impl.parseFormula(anEquation)).thenReturn(mockEvaluatableEquation);
		when(mockEvaluatableEquation.evaluate(isA(FormulaContext.class))).thenReturn(value);
		when(mockData.getValue()).thenReturn(value);
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, true)).thenReturn(mockData);
		when(mockModel.getModelType()).thenReturn(ModelType.ICS);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setFormulaParser(mockEquationParser2Impl);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Assert.assertEquals("", value, pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true).getValue());
		Assert.assertEquals("", cg, pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true).getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}
	
	public void testGetDefaultGroupTotal(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetDefaultGroupTotal");
		when(mockModelService.getModel(1)).thenReturn(mockModel);

		anEquation = "anEquation";
		Pot pot = new Pot();
		
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockGroupEntry.isAggregate()).thenReturn(true);
		
		
		when(mockItem.getUnit()).thenReturn("nr");
		when(mockItem.getCode()).thenReturn("B101");
		
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(false);
		when(mockItemProperties.getGeneralFormula()).thenReturn(anEquation);
		when(mockItemProperties.getFormula(mockYear)).thenReturn(anEquation);
		
		when(mockEquationParser2Impl.parseFormula("B101[TOTAL]")).thenReturn(mockEvaluatableEquation);
		
		when(mockEvaluatableEquation.evaluate(isA(FormulaContext.class))).thenReturn(value);
		
		when(mockData.getValue()).thenReturn(value);
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, true)).thenReturn(mockData);
		when(mockModel.getModelType()).thenReturn(ModelType.ICS);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//

		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(mockItem);
		pot.setDataService(mockDataService);
		pot.setFormulaParser(mockEquationParser2Impl);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Assert.assertEquals("", value, pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true).getValue());
		Assert.assertEquals("", cg, pot.getData(mockCompany, null, mockBasket, mockGroupEntry, true).getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}
	

	
	public void testGetFormattedPercentValue(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetFormattedPercentValue");
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		
		Item percentItem = mock(Item.class);
		when(percentItem.getUnit()).thenReturn("%");
		when(percentItem.getId()).thenReturn(itemId);
		when(mockModel.getItemPropertiesForItem(itemId)).thenReturn(mockItemProperties);
		value = "0.99";
		
		anEquation = "";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);		
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		when(percentItem.getId()).thenReturn(23);
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		
		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(percentItem);
		pot.setDataService(mockDataService);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//

		Data data = pot.getFormattedData(mockCompany, null, mockBasket, mockGroupEntry, true);
		verify(data).setValue("99");

		// and change the decimal palces
		when(mockItemProperties.getDecimalPlaces()).thenReturn(4);
		data = pot.getFormattedData(mockCompany, null, mockBasket, mockGroupEntry, true);
		verify(data).setValue("99.0000");
	
		System.out.println("TEST: Done");
}
	
	public void testFormattingSmallNegativeNumber(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFormattingSmallNegativeNumber");
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		Item nrItem = mock(Item.class);
		when(nrItem.getUnit()).thenReturn("nr");
		when(nrItem.getId()).thenReturn(itemId);
		when(mockModel.getItemPropertiesForItem(itemId)).thenReturn(mockItemProperties);
		value = "-0.0001";
		
		anEquation = "";
		Pot pot = new Pot();
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);		
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		when(nrItem.getId()).thenReturn(23);
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//

		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(nrItem);
		pot.setDataService(mockDataService);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Data data = pot.getFormattedData(mockCompany, null, mockBasket, mockGroupEntry, true);
		
		// when rounded to 0dp, should appear as 0 (not -0)
		verify(data).setValue("0");
		
		// and change the decimal places we should still get the negative number
		when(mockItemProperties.getDecimalPlaces()).thenReturn(4);
		data = pot.getFormattedData(mockCompany, null, mockBasket, mockGroupEntry, true);
		verify(data).setValue("-0.0001");
	
		System.out.println("TEST: Done");
}

	public void testGetFormattedDataRunButNoTag(){
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		
		Item anItem = mock(Item.class);
		when(anItem.getUnit()).thenReturn("some unit");
		when(anItem.getId()).thenReturn(itemId);
		when(mockModel.getItemPropertiesForItem(itemId)).thenReturn(mockItemProperties);
		value = "7";
		
		anEquation = "";
		Pot pot = new Pot();
//		pot.setRunModel(new RunModel(91, false));
		when(mockGroupEntry.getId()).thenReturn(17);
		when(mockGroupEntry.getDescription()).thenReturn("DESC1");
		when(mockItemProperties.isRawDataValue(mockYear)).thenReturn(true);		
		when(mockDataService.getDataForPot(pot, mockCompany, mockGroupEntry, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn(value);
		when(anItem.getId()).thenReturn(23);
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		String cg = "cg";
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockData.getConfidenceGrade()).thenReturn(confidenceGrade);
		when(mockDataService.createData(pot, mockCompany, mockGroupEntry, value, cg, false)).thenReturn(mockData);
		
		// runtag
				Run run = new Run();
				run.setId(7);
				RunTag runTag = new RunTag(run , RunModelTag.LATEST);
				Branch branch = new Branch();
				branch.setId(14);

				when(mockBranchDao.findByCompanyAndRun(3, run.getId())).thenReturn(branch);

				pot.setRunTag(runTag);
				pot.setBranchDao(mockBranchDao);
		//

		pot.setId(1);
		pot.setInterval(mockYear);
		pot.setItem(anItem);
		pot.setDataService(mockDataService);
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		Data returnedData = pot.getFormattedData(mockCompany, null, mockBasket, mockGroupEntry, true);
		verify(returnedData).setValue("7");
		Assert.assertEquals("7", returnedData.getValue());
	}
	

	public void testGetFormattedDataRunAndTagBlankData(){
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		Item item = new Item();
		item.setId(1);
		Interval interval = new Interval();
		interval.setId(3);
		Company company = new Company();
		company.setId(4);
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setId(5);
		Run run = new Run();
		run.setId(7);
		RunModelTag tag = new RunModelTag();
		tag.setId(11);
//		tag.setName("A tag");
		RunTag runTag = new RunTag(run, tag);

		Pot pot = new Pot();
		pot.setItem(item);
		pot.setInterval(interval);
		pot.setRunTag(runTag);
		Branch branch = new Branch();
		branch.setId(9);
	
		when(mockCompany.getId()).thenReturn(company.getId());
		when(mockBranchDao.findByCompanyAndRun(company.getId(), run.getId())).thenReturn(branch);
		when(mockDataService.createData(pot, company, groupEntry, null, null, false)).thenReturn(mockData);
		when(mockData.getValue()).thenReturn("");
		when(mockModel.getItemPropertiesForItem(23)).thenReturn(mockItemProperties);
		when(mockItemProperties.isRawDataValue(interval)).thenReturn(true);

		
		pot.setBranchDao(mockBranchDao);
		pot.setDataService(mockDataService);

		pot.setModelId(1);
		pot.setModelService(mockModelService);

		Data returnedData = pot.getFormattedData(company, null, null, groupEntry, true);
		Assert.assertEquals("", returnedData.getValue());
	}
	

	public void testGetFormattedDataRunAndTagHasData(){
		when(mockModelService.getModel(1)).thenReturn(mockModel);
		Item item = new Item();
		item.setId(1);
		Interval interval = new Interval();
		interval.setId(3);
		Company company = new Company();
		company.setId(4);
		GroupEntry groupEntry = new GroupEntry();
		groupEntry.setId(5);
		Run run = new Run();
		run.setId(7);
//		RunModel runModel = new RunModel(8, false);
//		runModel.setRun(run);
		RunModelTag tag = new RunModelTag();
		tag.setId(11);
//		tag.setName("A tag");
		RunTag runTag = new RunTag(run, tag);

		Pot pot = new Pot();
		pot.setItem(item);
		pot.setInterval(interval);
//		pot.setRunModel(runModel);
		pot.setRunTag(runTag);
		Branch branch = new Branch();
		branch.setId(9);
	

		when(mockCompany.getId()).thenReturn(company.getId());
		when(mockBranchDao.findByCompanyAndRun(company.getId(), run.getId())).thenReturn(branch);
		
		Data dataFromDataService = new Data();
		dataFromDataService.setValue("123");
		when(mockDataService.getDataForPot(pot, company, groupEntry, true)).thenReturn(dataFromDataService);

		Data dataCreatedByDataService = new Data();
		dataCreatedByDataService.setValue("123");
		when(mockDataService.createData(pot, company, groupEntry, "123", "", false)).thenReturn(dataCreatedByDataService);
		
		pot.setBranchDao(mockBranchDao);
		pot.setDataService(mockDataService);
		when(mockItemProperties.getDecimalPlaces()).thenReturn(2);

		
		// format
		pot.setModelId(1);
		pot.setModelService(mockModelService);
		when(mockModelService.getModel(1)).thenReturn(mockModel);

		ItemProperties itemProperties = new ItemProperties();
		itemProperties.setDecimalPlaces(1);
		item.setUnit("parts per million");
		when(mockModel.getItemPropertiesForItem(item.getId())).thenReturn(itemProperties);
		
		Data returnedData = pot.getFormattedData(company, null, null, groupEntry, true);
		Assert.assertEquals("123.0", returnedData.getValue());
		Assert.assertNotSame(dataFromDataService, returnedData); // Data must be recreated as we must not return the data from the cache. 
		Assert.assertSame(dataCreatedByDataService, returnedData);  
	}
	
	public void testGetBranch(){
		Company company = new Company();
		company.setId(4);
		Run run = new Run();
		run.setId(7);
		RunModelTag tag = new RunModelTag();
		tag.setId(0);
//		tag.setName("A tag");
		RunTag runTag = new RunTag(run, tag);
		Branch runBranch = new Branch();
		runBranch.setId(9);
		Branch modelBranch = new Branch();
		modelBranch.setId(99);
		Model model = new Model();
		model.setBranch(modelBranch);

		Pot pot = new Pot();
		pot.setRunTag(runTag);
		pot.setBranchDao(mockBranchDao);
		when(mockBranchDao.findByCompanyAndRun(company.getId(), run.getId())).thenReturn(runBranch);

		Branch returnedBranch = pot.getBranch(company.getId());
		Assert.assertEquals(runBranch.getId(), returnedBranch.getId());
	}
	

}

