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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.advancedpwr.record.xstream.XstreamRecorder;

import uk.gov.ofwat.fountain.api.report.ReportLine;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryCompanyItemRowsYearCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryCompanyRowItemYearCols;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryCompanyRowYearItemCols;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryCompanyYearRowsItemCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryItemCompanyRowsYearCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryItemRowYearCompanyCols;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryItemYearRowsCompanyCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryYearCompanyRowsItemCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryYearCompanyRowsItemCol2;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryYearItemRowsCompanyCol;
import uk.gov.ofwat.fountain.api.report.generated.ReportStructureFactoryYearRowItemCompanyCols;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryCompanyItemRowsYearCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryCompanyRowItemYearCols;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryCompanyRowYearItemCols;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryCompanyYearRowsItemCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryItemCompanyRowsYearCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryItemRowYearCompanyCols;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryItemYearRowsCompanyCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryYearCompanyRowsItemCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryYearCompanyRowsItemCol2;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryYearItemRowsCompanyCol;
import uk.gov.ofwat.fountain.domain.generated.ReportDefinitionFactoryYearRowItemCompanyCols;
import uk.gov.ofwat.fountain.rest.dto.RunTagIdsDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test_beans.xml"})
public class ReportServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private ReportServiceImpl reportService;
	@Resource
	private UserService userService;
	@Resource
	private TeamService teamService;
	@Resource
	private ModelPropertiesMapDao modelPropertiesMapDao;
	@Resource
	private ModelService modelService;
	@Resource
	private ItemService itemService;
	@Resource
	private ReferenceService referenceService;
	@Resource 
	private ReportDao reportDao;
	
	private ReportDao mockReportDao;
	private List<ReportSummary> mockReportList;
	

	@Before
	public void setUp() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		mockReportDao = mock(ReportDao.class);
		mockReportList = new ArrayList<ReportSummary>();
		mockReportList.add(mock(ReportSummary.class));
		mockReportList.add(mock(ReportSummary.class));

		
	
		System.out.println("TEST: Done");
}

	@Test
	public void testSaveReportWithoutRunTags() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSaveReport");
			
		reportService.setReportDao(reportDao);

		String name =  "test1";
			User user = userService.getNamedUser("OFWAT\\Adam.Edgar");

			Team team = teamService.getTeamById(2);
			
			List<ModelItem> modelItems = new ArrayList<ModelItem>();  

			ModelItem modelItem = new ModelItem();
			modelItem.setItemId(47240);
			modelItem.setModelId(47);
			ModelPropertiesMap modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47240);
			modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
			modelItems.add(modelItem);
			
			modelItem = new ModelItem();
			modelItem.setItemId(47243);
			modelItem.setModelId(47);
			modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47243);
			modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
			modelItems.add(modelItem);
			
			modelItem = new ModelItem();
			modelItem.setItemId(47228);
			modelItem.setModelId(47);
			modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47228);
			modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
			modelItems.add(modelItem);
			
			String group = "NONE";

			Interval interval = referenceService.getInterval("");
			List<Integer> intervalIds = new ArrayList<Integer>();
			intervalIds.add(195);
			intervalIds.add(266);

			List<String> layoutLeft = new ArrayList<String>();
			layoutLeft.add("Company");
			layoutLeft.add("Item");
			
			List<String> layoutTop = new ArrayList<String>();
			layoutTop.add("Year");
			
			boolean publicReport = false; 
			
			List<Integer> companyIds = new ArrayList<Integer>();
			companyIds.add(30);
			companyIds.add(61);

            List<RunTagIdsDto> runTagIds = new ArrayList<RunTagIdsDto>();

			boolean displayCGs = true; 
			boolean displayUnit = true;
			boolean displayBoncode = true; 
			boolean displayDesc = true;
			boolean displayModel = true;
			boolean displayCompanyName = true;
			boolean displayCompanyAcronym = true;
            boolean displayRunName = false;
            boolean displayRunDescription = false;
            boolean displayTagName = false;
            boolean readOnly = false;
				
			int reportId = reportService.saveReport(name, 
									user, 
									team, 
									modelItems, 
									group, 
									intervalIds, 
									layoutLeft, 
									layoutTop, 
									publicReport, 
									companyIds, 
									displayCGs, 
									displayUnit, 
									displayBoncode, 
									displayDesc, 
									displayModel, 
									displayCompanyName, 
									displayCompanyAcronym,
                                    runTagIds,
                                    displayRunName,
                                    displayRunDescription,
                                    displayTagName,
                                    false,
                                    false,
                                    readOnly,
                                    null);

			ReportDefinition reportDef = reportService.getReportDefinition(reportId);
			Assert.assertEquals(name, reportDef.getName());
			Assert.assertEquals(user.getName(), reportDef.getOwnerUser());
			Assert.assertEquals(team.getId(), reportDef.getTeamId());
			for (int i=0; i<modelItems.size(); i++) {
				Assert.assertEquals(modelItems.get(i).getItemId(), reportDef.getModelItems().get(i).getItemId()); 
				Assert.assertEquals(modelItems.get(i).getModelId(), reportDef.getModelItems().get(i).getModelId()); 
				Assert.assertEquals(modelItems.get(i).getModelPropertiesMapId(), reportDef.getModelItems().get(i).getModelPropertiesMapId());
			}
			Assert.assertEquals(group, reportDef.getGroup().getName());
			Assert.assertEquals(intervalIds, reportDef.getIntervalIds());
			Assert.assertEquals(layoutLeft, reportDef.getLayoutLeft());
			Assert.assertEquals(layoutTop, reportDef.getLayoutTop());
			Assert.assertEquals(publicReport, reportDef.isPublicReport());
			Assert.assertEquals(companyIds, reportDef.getCompanyIds());
			Assert.assertEquals(displayCGs, reportDef.getReportDisplay().isDisplayCGs());
			Assert.assertEquals(displayUnit, reportDef.getReportDisplay().isDisplayUnit());
			Assert.assertEquals(displayBoncode, reportDef.getReportDisplay().isDisplayBoncode());
			Assert.assertEquals(displayDesc, reportDef.getReportDisplay().isDisplayDesc());
			Assert.assertEquals(displayModel, reportDef.getReportDisplay().isDisplayModel());
			Assert.assertEquals(displayCompanyName, reportDef.getReportDisplay().isDisplayCompanyName());
			Assert.assertEquals(displayCompanyAcronym, reportDef.getReportDisplay().isDisplayCompanyAcronym());
	
		System.out.println("TEST: Done");
}
	
	private int createReport1() {
		String name =  "test1";
		User user = userService.getNamedUser("OFWAT\\Adam.Edgar");

		Team team = teamService.getTeamById(2);
		
		List<ModelItem> modelItems = new ArrayList<ModelItem>();  

		ModelItem modelItem = new ModelItem();
		modelItem.setItemId(47240);
		modelItem.setModelId(47);
		ModelPropertiesMap modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47240);
		modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
		modelItems.add(modelItem);
		
		modelItem = new ModelItem();
		modelItem.setItemId(47243);
		modelItem.setModelId(47);
		modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47243);
		modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
		modelItems.add(modelItem);
		
		modelItem = new ModelItem();
		modelItem.setItemId(47228);
		modelItem.setModelId(47);
		modelPropertiesMap = modelPropertiesMapDao.findByModelAndItem(47, 47228);
		modelItem.setModelPropertiesMapId(modelPropertiesMap.getId());
		modelItems.add(modelItem);
		
		String group = "NONE";

		Interval interval = referenceService.getInterval("");
		List<Integer> intervalIds = new ArrayList<Integer>();
		intervalIds.add(195);
		intervalIds.add(266);

		List<String> layoutLeft = new ArrayList<String>();
		layoutLeft.add("Company");
		layoutLeft.add("Item");
		
		List<String> layoutTop = new ArrayList<String>();
		layoutTop.add("Year");
		
		boolean publicReport = false;

        List<RunTagIdsDto> runTagIds = new ArrayList<RunTagIdsDto>();
		
		List<Integer> companyIds = new ArrayList<Integer>();
		companyIds.add(30);
		companyIds.add(61);
		
		boolean displayCGs = true; 
		boolean displayUnit = true;
		boolean displayBoncode = true; 
		boolean displayDesc = true;
		boolean displayModel = true;
		boolean displayCompanyName = true;
		boolean displayCompanyAcronym = true;
        boolean displayRunName = false;
        boolean displayRunDescription = false;
        boolean displayTagName = false;
        boolean readOnly = false;
			
		int reportId = reportService.saveReport(name, 
								user, 
								team, 
								modelItems, 
								group, 
								intervalIds, 
								layoutLeft, 
								layoutTop, 
								publicReport, 
								companyIds, 
								displayCGs, 
								displayUnit, 
								displayBoncode, 
								displayDesc, 
								displayModel, 
								displayCompanyName, 
								displayCompanyAcronym,
                                runTagIds,
                                displayRunName,
                                displayRunDescription,
                                displayTagName,
                                false,
                                false,
                                readOnly,
                                null);
		return reportId;
	}
	
	@Test
	public void testGetReportsForTeam () {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForTeam ");
		reportService.setReportDao(mockReportDao);
		//when(mockReportDao.getReportsForTeam(1)).thenReturn(mockReportList);
		when(mockReportDao.getReportsForTeam(1, null)).thenReturn(mockReportList);
		
		List<ReportSummary> list = reportService.getReportsForTeam(1);
		assertEquals(2, list.size());
	
	
		System.out.println("TEST: Done");
}
	
/*	@Ignore
	@Test
	public void testSaveReport() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSaveReport");
		
		fail("Not yet implemented");
	
		System.out.println("TEST: Done");
}*/
	
	
	@Test
	public void testPublishReport() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testPublishReport");
		reportService.setReportDao(mockReportDao);
		when (mockReportDao.updatePublishedStatus(true, 1)).thenReturn(true);
		boolean res = reportService.publishReport(1);
		assertEquals(true, res);

	
		System.out.println("TEST: Done");
}

	@Test
	public void testHideReport() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testHideReport");
		reportService.setReportDao(mockReportDao);
		when (mockReportDao.updatePublishedStatus(false, 1)).thenReturn(true);
		boolean res = reportService.hideReport(1);
		assertEquals(true, res);

	
		System.out.println("TEST: Done");
}

//	@Test
//	public void testGetReportStructure_ItemCompanyRowsYearCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_ItemCompanyRowsYearCol");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryItemCompanyRowsYearCol reportDefinitionFactory = new ReportDefinitionFactoryItemCompanyRowsYearCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryItemCompanyRowsYearCol reportStructureFactory = new ReportStructureFactoryItemCompanyRowsYearCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_CompanyRowYearItemCols() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_CompanyRowYearItemCols");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryCompanyRowYearItemCols reportDefinitionFactory = new ReportDefinitionFactoryCompanyRowYearItemCols();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryCompanyRowYearItemCols reportStructureFactory = new ReportStructureFactoryCompanyRowYearItemCols();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_CompanyRowItemYearCols() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_CompanyRowItemYearCols");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryCompanyRowItemYearCols reportDefinitionFactory = new ReportDefinitionFactoryCompanyRowItemYearCols();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryCompanyRowItemYearCols reportStructureFactory = new ReportStructureFactoryCompanyRowItemYearCols();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_ItemYearRowsCompanyCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_ItemYearRowsCompanyCol");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryItemYearRowsCompanyCol reportDefinitionFactory = new ReportDefinitionFactoryItemYearRowsCompanyCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryItemYearRowsCompanyCol reportStructureFactory = new ReportStructureFactoryItemYearRowsCompanyCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_CompanyYearRowsItemCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_CompanyYearRowsItemCol");
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryCompanyYearRowsItemCol reportDefinitionFactory = new ReportDefinitionFactoryCompanyYearRowsItemCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryCompanyYearRowsItemCol reportStructureFactory = new ReportStructureFactoryCompanyYearRowsItemCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_YearItemRowsCompanyCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_YearItemRowsCompanyCol");
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryYearItemRowsCompanyCol reportDefinitionFactory = new ReportDefinitionFactoryYearItemRowsCompanyCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryYearItemRowsCompanyCol reportStructureFactory = new ReportStructureFactoryYearItemRowsCompanyCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_YearRowItemCompanyCols() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_YearRowItemCompanyCols");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryYearRowItemCompanyCols reportDefinitionFactory = new ReportDefinitionFactoryYearRowItemCompanyCols();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryYearRowItemCompanyCols reportStructureFactory = new ReportStructureFactoryYearRowItemCompanyCols();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_CompanyItemRowsYearCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_CompanyItemRowsYearCol");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryCompanyItemRowsYearCol reportDefinitionFactory = new ReportDefinitionFactoryCompanyItemRowsYearCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryCompanyItemRowsYearCol reportStructureFactory = new ReportStructureFactoryCompanyItemRowsYearCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_YearCompanyRowsItemCol() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_YearCompanyRowsItemCol");
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryYearCompanyRowsItemCol reportDefinitionFactory = new ReportDefinitionFactoryYearCompanyRowsItemCol();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryYearCompanyRowsItemCol reportStructureFactory = new ReportStructureFactoryYearCompanyRowsItemCol();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_ItemRowYearCompanyCols() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_ItemRowYearCompanyCols");
//
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryItemRowYearCompanyCols reportDefinitionFactory = new ReportDefinitionFactoryItemRowYearCompanyCols();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryItemRowYearCompanyCols reportStructureFactory = new ReportStructureFactoryItemRowYearCompanyCols();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}
//
//	@Test
//	public void testGetReportStructure_YearCompanyRowsItemCol2() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportStructure_YearCompanyRowsItemCol2");
//		
//		// Recall the previously recorded report definition from xml.
//		ReportDefinitionFactoryYearCompanyRowsItemCol2 reportDefinitionFactory = new ReportDefinitionFactoryYearCompanyRowsItemCol2();
//		ReportDefinition reportDef = reportDefinitionFactory.buildReportDefinition();
//		
//		// Create the report in the database from the report def
//		int reportId = reportDao.create(reportDef);
//		when(mockReportDao.findById(reportId)).thenReturn(reportDef);
//		reportService.setReportDao(mockReportDao);
//
//		// Recall the previously recorded report structure
//		ReportStructureFactoryYearCompanyRowsItemCol2 reportStructureFactory = new ReportStructureFactoryYearCompanyRowsItemCol2();
//		ReportStructure controlReportStructure = reportStructureFactory.buildReportStructure();
//		List<ReportLine> controlReportLines = controlReportStructure.getLines();
//
//		// Create the report structure
//		ReportStructure reportStructure = reportService.getReportStructure(reportId);
//		List<ReportLine> reportLines = reportStructure.getLines();
//		
////		rerecord(reportStructure);
//
//		Assert.assertEquals(reportLines, controlReportLines);
//	
//		System.out.println("TEST: Done");
//}

	private void rerecord(ReportStructure reportStructure) {
		XstreamRecorder recorder = new XstreamRecorder();
		recorder.setDestination("test");
		recorder.record(reportStructure);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setReportService(ReportServiceImpl reportService) {
		this.reportService = reportService;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	
}
