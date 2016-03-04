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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.report.HtmlReportWriter;
import uk.gov.ofwat.fountain.api.report.ReportBuilder;
import uk.gov.ofwat.fountain.api.report.ReportCompanyGroups;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.api.report.ReportTableWriter;
import uk.gov.ofwat.fountain.api.report.ReportWriter;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemInEquation;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ListItem;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.ReportCollector;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportDisplay;
import uk.gov.ofwat.fountain.domain.ReportQuery;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.formula.FormulaParser;
import uk.gov.ofwat.fountain.domain.formula.FormulaParserImpl;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.rest.dto.BulkDeleteReportDto;
import uk.gov.ofwat.fountain.rest.dto.BulkModifyReportDto;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;
import uk.gov.ofwat.fountain.rest.dto.RowDto;
import uk.gov.ofwat.fountain.rest.dto.RunTagIdsDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;
import uk.gov.ofwat.fountain.search.ReportSearchHelper;

public class ReportServiceImpl implements ReportService,
		ApplicationContextAware {

	private ReportDao reportDao;
	private ReferenceService referenceService;
	private ItemService itemService;
	private TagService tagService;
	private DataService dataService;
	private GroupService groupService;
	private ModelService modelService;
	private BasketService basketService;
	private LockService lockService;
	private ConfidenceGradeService confidenceGradeService;
	private String reportTemplate;
	private String outputDir;
	private String localDir;
	private ReportTableWriter reportTableWriter;
	private RunService runService;
	private BranchDao branchDao;
	private RunTagService runTagService;
	private IndexService indexService;

	private static Logger logger = LoggerFactory
			.getLogger(ReportServiceImpl.class);
	public static String EXCEL_AUDIT_PREFIX = "XL:";
	private static ApplicationContext applicationContext;

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public RunTagService getRunTagService() {
		return runTagService;
	}
	
	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public RunService  getRunService() {
		return runService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setReportTableWriter(ReportTableWriter reportTableWriter) {
		this.reportTableWriter = reportTableWriter;
	}

	public void setConfidenceGradeService(
			ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public void setReportTemplate(String reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}
	
	/**
	 * update the description of a report. 
	 */
	public Boolean updateReportDescription(int id, String description){
		ReportDefinition rd = reportDao.findById(id);
		rd.setDescription(description);
		Boolean result = false;
		try{
			reportDao.update(rd);
			indexService.index(ReportSearchWrapper.class, rd.getId());
			result = true;
		}catch(Exception e){
			logger.error(e.toString());
		}
		return result;
	}	
	
	/**
	 * This is a scheduled task to update the popularity values on individual reports. Will be run nightly through Quartz.
	 */
	public void updateReportPopularity(){
		//Run a series of updates on the report table. 
		
		//update the week count -- sunday is day 1!
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK); 		
		int startDayOfWeek = Calendar.getInstance().getMinimum(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == startDayOfWeek){
			//reset week this will affect entire table.
		}
		
		//update the month count
		//when do we reset the counters? - reset the week on sunday
		int maxDaysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayInMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		if(dayInMonth >= maxDaysInMonth){
			//reset month this will affect entire table.
		}
		
		//reset the day this will affect entire table.
	}

	@Transactional
	public int saveReport(final String name, final User user, final Team team,
			final List<ModelItem> modelItems, final String group,
			final List<Integer> intervalIds, final List<String> layoutLeft,
			final List<String> layoutTop, final boolean publicReport,
			final List<Integer> companyIds, final boolean displayCGs,
			final boolean displayUnit, final boolean displayBoncode,
			final boolean displayDesc, final boolean displayModel,
			final boolean displayCompanyName,
			final boolean displayCompanyAcronym,
			List<RunTagIdsDto> runTagIds, final boolean displayRunName,
			final boolean displayRunDesc, final boolean displayTagDisplayName,
			final boolean displayAgendaName, final boolean displayAgendaCode, final boolean readOnly, String description) {

		ReportDefinition reportDef = new ReportDefinition();
		reportDef.setCompanyIds(companyIds);
		reportDef.setIntervalIds(intervalIds);
		reportDef.setModelItems(modelItems);
		reportDef.setName(name);
		reportDef.setOwnerUser(user.getName());
		reportDef.setLayoutLeft(layoutLeft);
		reportDef.setLayoutTop(layoutTop);
		reportDef.setPublicReport(publicReport);
		reportDef.setReportDisplay(new ReportDisplay());
		reportDef.setReadOnly(readOnly);
		reportDef.getReportDisplay().setDisplayBoncode(displayBoncode);
		reportDef.getReportDisplay().setDisplayCGs(displayCGs);
		reportDef.getReportDisplay().setDisplayCompanyAcronym(displayCompanyAcronym);
		reportDef.getReportDisplay().setDisplayCompanyName(displayCompanyName);
		reportDef.getReportDisplay().setDisplayDesc(displayDesc);
		reportDef.getReportDisplay().setDisplayModel(displayModel);
		reportDef.getReportDisplay().setDisplayUnit(displayUnit);
		reportDef.setTeamId(team.getId());
		reportDef.getReportDisplay().setDisplayRunDesc(displayRunDesc);
		reportDef.getReportDisplay().setDisplayRunName(displayRunName);
		reportDef.getReportDisplay().setDisplayTagDisplayName(displayTagDisplayName);
		reportDef.getReportDisplay().setDisplayAgendaName(displayAgendaName);
		reportDef.getReportDisplay().setDisplayAgendaCode(displayAgendaCode);

		if (null != group && !"".equals(group)) {
			reportDef.setGroup(groupService.getGroupByCode(group));
		}

		// Set the runTags if they are required.
		reportDef.setRunTags(getRunTags(runTagIds, false));

		if(description!=null){
			reportDef.setDescription(description);
		}		
		
		int id = reportDao.create(reportDef);
		reportDef.setId(id);

		return id;
	}

	public ReportSummary getSummaryById(int id) {
		ReportSummary reportSummary = reportDao.findSummaryById(id);
		if (null != reportSummary) {
			completeReportSummary(reportSummary);
		}
		return reportSummary;

	}

	public ReportSummary getReportSummary(Integer reportId){
		ReportSummary reportSummary = reportDao.findSummaryById(reportId);
		completeReportSummary(reportSummary);
		return reportSummary;
	}
	
	public List<ReportSummary> getReportsForTeam(final Integer teamId) {
		return getReportsForTeam(teamId, null);
	}

	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds) {
		return getReportsForTeams(teamIds, null);
	}
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, boolean completeSummary) {
		return getReportsForTeams(teamIds, completeSummary, null);
	}	
	
	public List<ReportSummary> getReportsForTeam(final Integer teamId, Integer limit) {
		List<ReportSummary> reportSummaries = reportDao.getReportsForTeam(teamId, limit);
		for (ReportSummary reportSummary : reportSummaries) {
			completeReportSummary(reportSummary);
		}
		return reportSummaries;
	}

	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, Integer limit) {
		return getReportsForTeams(teamIds, true);
	}
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, boolean completeSummary, Integer limit) {
		List<ReportSummary> reportSummaries = reportDao.getReportsForTeams(teamIds, limit);
		if(completeSummary){
			for (ReportSummary reportSummary : reportSummaries) {
				completeReportSummary(reportSummary);
			}
		}
		return reportSummaries;
	}

	private ReportSummary completeReportSummary(ReportSummary reportSummary) {
		List<Integer> companyIds = reportDao.findReportCompaniesById(reportSummary.getId());
		if (companyIds.isEmpty()) {
			reportSummary.setInterchangeableCompany(true);
		} else {
			reportSummary.setInterchangeableCompany(false);
		}
		List<Integer> runIds = reportDao.findReportRunsById(reportSummary.getId());
		if (runIds.isEmpty()) {
			reportSummary.setInterchangeableRun(true);
		} else {
			reportSummary.setInterchangeableRun(false);
		}
		return reportSummary;
	}
	
	public ReportStructure getReportStructure(int id) {
		return getReportStructure(id, false);
	}

	public ReportStructure getReportStructure(int id, boolean showAllHeadings) {
		ReportDefinition reportDef = getReportDefinition(id);
		ReportBuilder newBuilder = new ReportBuilder(this, modelService, itemService, referenceService);
		ReportStructure struc = newBuilder.makeReportStructure(reportDef, showAllHeadings);
		return struc;
	}
	
	public List<ReportStructure> getAllReportStructures(){
		//Get all the reports in the system 
		List<ReportStructure> reportStructures = new ArrayList<ReportStructure>();
		List<ReportSummary> summaries = reportDao.getAll();
		//Get the structures for all the reports. 
		for(ReportSummary reportSummary : summaries){
			ReportDefinition reportDef = getReportDefinition(reportSummary.getId());
			ReportBuilder newBuilder = new ReportBuilder(this, modelService, itemService, referenceService);
			ReportStructure struc = newBuilder.makeReportStructure(reportDef, true);
			reportStructures.add(struc);
		}
		return reportStructures;
	}

	public Report runReport(int id, User user, RoleChecker roleChecker) {
		return runReport(id, user, roleChecker, 0, 0, 0);
	}

	public Report runReport(int id, User user, RoleChecker roleChecker, int suppliedCompanyId) {
		return runReport(id, user, roleChecker, suppliedCompanyId, 0, 0);
	}

	public Report runReport(int id, User user, RoleChecker roleChecker, int runId, int runModelTagId) {
		return runReport(id, user, roleChecker, 0, runId, runModelTagId);
	}

	public Report runReport(int id, User user, RoleChecker roleChecker, int suppliedCompanyId, int suppliedRunId, int suppliedRunModelTagId) {
		logger.info("Running report id: " + id + " for user " + user.getName());

		RunModelTag suppliedTag = runTagService.findRunModelTagById(suppliedRunModelTagId);
		
		ReportQuery reportQuery = new ReportQuery(this, id, suppliedCompanyId, suppliedRunId, suppliedTag);
		List<ItemInEquation> itemsInEquation = getEquationItems(user, roleChecker, reportQuery);
		reportQuery.addEquationItems(itemsInEquation);
		cacheReportData(reportQuery);	
		
		ReportCollector reportCollector = new ReportCollector(user, roleChecker, basketService.getBasketForUser(user), reportQuery.getReportDef(), reportQuery.getRunTags());
		for (int companyId : reportQuery.getCompanyIds()) {
			Company company = referenceService.getCompany(companyId);
			ReportCompanyGroups reportCompanyGroups = new ReportCompanyGroups(companyId);
			reportCollector.getMapOfReportCompanyGroups().put(companyId, reportCompanyGroups);

			processModelItems(reportCollector, company, reportCompanyGroups);
		}
			
		
		Map defaultRunIdMap = getDefaultRunIdMap(reportQuery); 
		Report report = new Report(	reportQuery.getId(), 
									reportCollector.getDataList(), 
									reportQuery.getGroupId(),
									retrieveGroupEntriesByCompany(reportCollector.getMapOfReportCompanyGroups()),
									confidenceGradeService.getConfidenceGrades(),
									mapCodeListsByCodeList(reportCollector.getCodeLists()), 
									reportCollector.getLocks(), 
									reportQuery.getSuppliedCompany(),
									reportQuery.getSuppliedRun(),
									reportQuery.getSuppliedTag(),
									reportQuery.getTagMap(),
									defaultRunIdMap);
        logger.info("Completed report id: " + reportQuery.getId() + " for user " + user.getName());
        return report;
	}
	
	private Map<Integer, Integer> getDefaultRunIdMap(ReportQuery reportQuery) {
		Map<Integer, Integer> defaultRunIdMap = new HashMap<Integer, Integer>();
		for (RunTag runTag: reportQuery.getReportDef().getRunTags()) {
			if (RunModelTag.LATEST.equals(runTag.getRunModelTag())) {
				// latest
				if (RunRole.PROXY == runTag.getRun().getRunRole()) {
					// proxy
					if (null == defaultRunIdMap.get(runTag.getRun().getId())) {
						// not in map yet
						defaultRunIdMap.put(runTag.getRun().getId(), runService.getRun(runTag.getRun().getId()).getId());
					}
				}
			}
		}
		return defaultRunIdMap;
	}

	private void processModelItems(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups) {
		for (ModelItem modelItem : reportCollector.getReportDef().getModelItems()) {
			processModelItemOnRun(reportCollector, company, reportCompanyGroups, modelItem);
		}
	}
	private void processModelItemOnRun(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups, ModelItem modelItem) {
		for (RunTag runTag: reportCollector.getRunTags()) {
			if (!runTag.getRun().getCompanyIds().contains(company.getId())) {
				// company is not in this run
				continue;
			}
			processModelItem(reportCollector, company, reportCompanyGroups, modelItem, runTag);
		}
	}
	private void processModelItem(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups, ModelItem mi, RunTag runTag) {
		FormulaParser fp = new FormulaParserImpl();
		Model model = modelService.getModel(mi.getModelId());
		ItemProperties ip = model.getItemPropertiesForItem(mi.getItemId());
		
		int tagId = runTag.getRunModelTag().getRunModelCompanyTag(company.getId()).getId();
		
		for (Integer intervalId : reportCollector.getReportDef().getIntervalIds()) {
			Interval interval = referenceService.getInterval(intervalId);
			Pot pot = createPot(fp, runTag, model, ip, interval);
			reportCollector.getPots().add(pot);
			List<GroupEntry> groupEntries = pot.getGroupsForPotItem(company);
			for (GroupEntry groupEntry : groupEntries) {
				Data data = pot.getFormattedData(	company,
													reportCollector.getTransientDataCache(), 
													reportCollector.getBasket(), 
													groupEntry,
													true);
				Boolean editable = isEditable(reportCollector.getRoleChecker(), pot, company);
				DataDto dto = new DataDto(	company, interval, editable, ip,
											groupEntry, data.getValue(),
											data.getConfidenceGrade(), data.getRawValue(),
											new ModelDto(model, null), data.getBranchId(), 
											runTag.getRun().getId(), tagId);
				doLocking(reportCollector.getUser(), reportCollector.getBasket(), company, pot, editable, dto, reportCollector.getLocks());
				reportCollector.getDataList().put(dto.getIdentifier(), dto);
				reportCompanyGroups.addEntry(data, groupEntry);
			}
		}
		if (null != ip.getItem().getCodeList()) {
			reportCollector.getCodeLists().add(ip.getItem().getCodeList());
		}
		
	}

	private List<ItemInEquation> getEquationItems(User user, RoleChecker roleChecker, ReportQuery reportQuery ) {
		List<ItemInEquation> itemsInEquation = new ArrayList<ItemInEquation>();

		ReportCollector reportCollector = new ReportCollector(user, roleChecker, basketService.getBasketForUser(user), reportQuery.getReportDef(), reportQuery.getRunTags());
		for (int companyId : reportQuery.getCompanyIds()) {
			Company company = referenceService.getCompany(companyId);
			ReportCompanyGroups reportCompanyGroups = new ReportCompanyGroups(companyId);
			reportCollector.getMapOfReportCompanyGroups().put(companyId, reportCompanyGroups);
			equationModelItems(reportCollector, company, reportCompanyGroups, itemsInEquation);
		}

        return itemsInEquation;
	}
	private void equationModelItems(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups, List<ItemInEquation> itemsInEquation) {
		for (ModelItem modelItem : reportCollector.getReportDef().getModelItems()) {
			equationModelItemOnRun(reportCollector, company, reportCompanyGroups, modelItem, itemsInEquation);
		}
	}
	private void equationModelItemOnRun(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups, ModelItem modelItem, List<ItemInEquation> itemsInEquation) {
		for (RunTag runTag: reportCollector.getRunTags()) {
			if (!runTag.getRun().getCompanyIds().contains(company.getId())) {
				// company is not in this run
				continue;
			}
			if (runTag.getRunModelTag() == RunModelTag.LATEST) {
				equationModelItem(reportCollector, company, reportCompanyGroups, modelItem, runTag, itemsInEquation);
			}
			else {
				// proper tag
				equationModelItem(reportCollector, company, reportCompanyGroups, modelItem, runTag, itemsInEquation);
			}
		}
	}
	
	private void equationModelItem(ReportCollector reportCollector, Company company, ReportCompanyGroups reportCompanyGroups, ModelItem mi, RunTag runTag, List<ItemInEquation> itemsInEquation) {
		FormulaParser fp = new FormulaParserImpl();
		Model model = modelService.getModel(mi.getModelId());
		ItemProperties ip = model.getItemPropertiesForItem(mi.getItemId());

		for (Integer intervalId : reportCollector.getReportDef().getIntervalIds()) {
			Interval interval = referenceService.getInterval(intervalId);
			Pot pot = createPot(fp, runTag, model, ip, interval);  

			List<GroupEntry> groupEntries = pot.getGroupsForPotItem(company);
			for (GroupEntry groupEntry : groupEntries) {
				pot.getItemsInEquation(company, groupEntry, itemsInEquation);
			}
		}
	}

	
	
	private Pot createPot(FormulaParser fp, RunTag runTag, Model model, ItemProperties ip, Interval interval) {
		Pot pot = new Pot();
		pot.setInterval(interval);
		pot.setItem(ip.getItem());
		pot.setModelId(model.getId());
		pot.setModelService(modelService);
		pot.setRunTag(runTag);
		pot.setDataService(dataService);
		pot.setFormulaParser(fp);
		pot.setItemService(itemService);
		pot.setGroupService(groupService);
		pot.setReferenceService(referenceService);
		pot.setBranchDao(branchDao);
		return pot;
	}
	

	public void cacheReportData(ReportQuery reportQuery) {
		logger.debug("cacheReportData start");
		
		cacheTaggedData(reportQuery);
		cacheLatestData(reportQuery);
		
		logger.debug("cacheReportData end");
	}
	
	private void cacheTaggedData(ReportQuery reportQuery) {
		Map<String, Object> procDataMap = dataService.getTaggedData(reportQuery.getRunTags(), reportQuery.getCompanyIds(), reportQuery.getIntervalIds(), reportQuery.getItemIds());

		for (Integer runId : reportQuery.getRunIds()) {
			for (Integer companyId : reportQuery.getCompanyIds()) {
				removeCalcsFromTaggedDataMap(reportQuery, runId, companyId, procDataMap);
			}
		}
		
		dataService.addToTaggedCache(procDataMap);
	}

	private void cacheLatestData(ReportQuery reportQuery) {
		for (Integer runId : reportQuery.getRunIds()) {
			for (Integer companyId : reportQuery.getCompanyIds()) {
				if (null == getBranch(companyId, runId)) {
					logger.debug("Company: " + companyId + " is not on run: " + runId);
					continue;
				}
				ArrayList<Integer> singleCompanyId = new ArrayList<Integer>();
				singleCompanyId.add(companyId);
				List<Data> dataList = dataService.getReportData(getBranchIds(companyId, runId), 
																singleCompanyId, 
																reportQuery.getItemIds(), 
																reportQuery.getIntervalIds(), 
																reportQuery.getGroupEntries(companyId));
				Map<String, Object> dataMap = dataListToMap(dataList);
				//	- Add blank data. This is to stop the old style data query defaulting down to base run or trunk data. Since the old query is being removed, addBlanksToDataMap() may well not be needed.  
				addBlanksToDataMap(reportQuery, runId, companyId, dataMap);
				// Remove calcs before they are added to the cache. Otherwise blank data will be sitting in the cache and could be picked up and used later.
				removeCalcsFromDataMap(reportQuery, runId, companyId, dataMap); 
				dataService.addToCache(dataMap); 
			}
		}
	}

	private List<RunTag> getRunTags(List<RunTagIdsDto> runTagIds, boolean substituteProxy) {
		List<RunTag> runTags = new ArrayList<RunTag>();
		if(runTagIds != null){
			for (RunTagIdsDto runTagIdDto : runTagIds) {
				Run run = runService.getRun((int) runTagIdDto.getRunId(), substituteProxy);
				if (runTagIdDto.getTagId() == RunModelTag.LATEST.getId()) {
					// Latest
					runTags.add(runTagService.getLatestRunTag(run.getId(), substituteProxy));
				} else {
					// Tag
					RunTag runTag = runTagService.getRunTag(run.getId(), runTagIdDto.getTagId(), substituteProxy);
					runTags.add(runTag);
				}
			}
		}
		return runTags;
	}

	private int getNumberOfExpectedDataInCache(Integer runId,
			ReportDefinition reportDef, Map<String, Object> dataExpected) {
		logger.debug("Get data from cache.");
		int dataAlreadyInCacheCount = 0;
		for (String key : dataExpected.keySet()) {
			if (dataService.isDataInCache(key)) {
				dataAlreadyInCacheCount++;
			}
		}
		logger.debug("Get data from cache end");
		return dataAlreadyInCacheCount;
	}

	private void logSizesOfReportDimensions(List<Integer> branchIds,
			List<Integer> itemIds, List<Integer> companyIds,
			List<Integer> groupEntryIds, List<Integer> intervalIds) {
		logger.debug("no of branches = " + branchIds.size());
		logger.debug("no of items = " + itemIds.size());
		logger.debug("no of intervals = " + intervalIds.size());
		logger.debug("no of company = " + companyIds.size());
		logger.debug("no of groupEntries = " + groupEntryIds.size());
	}
	
	private void removeCalcsFromDataMap(	ReportQuery reportQuery,
											Integer runId,
											Integer companyId, 
											Map<String, Object> dataMap
											) {
		logger.debug("Remove calculated items from data map - start.");

		Branch branch = getBranch(companyId, runId);
		if (null == branch) {
			logger.debug("Company: " + companyId + " is not on run: " + runId);
			logger.debug("Remove calculated items from data map - aborted.");
			return;
		}
		
		// to find a calc we need model and itemproperties
		// get all model Ids
		Set<Integer> modelIdsSet = new HashSet<Integer>();
		for (ModelItem mi: reportQuery.getReportDef().getModelItems()) {
			modelIdsSet.add(mi.getModelId());
		}
		List<Integer> modelIds = new ArrayList<Integer>(modelIdsSet);

		int calcsRemovedCount = 0;
		int itemNotInModelRemovedCount = 0;
		for (Integer modelId : modelIds) {
			Model model = modelService.getModel(modelId);
			for (Integer itemId : reportQuery.getItemIds()) {
				ItemProperties ip = model.getItemPropertiesForItem(itemId);
				if (null == ip) {
					// The item is not in this model, so we don't want it in the cache.
					// Remove from the dataMap for each groupEntry, interval, branch and company
					// But we only want to remove from branches associated with this model as it may be associated with one of the other models in the report.
					// So we need to get the branches for this model!
					Item item = itemService.getItem(itemId);
					logger.debug("Item NOT USED in this model. Removing item from data map. Model: " + model.getCode() + " Item: " + item.getCode() + " " + " Branch: " + branch.getName() + "(run: " + branch.getRunId() + " company: " + branch.getCompanyId() + ")");
					for (Integer groupEntryId : reportQuery.getGroupEntries(companyId)) {
						for (Integer intervalId : reportQuery.getIntervalIds()) {
							Integer beforeSize = dataMap.size();
							String cacheKey = itemId + "-" + intervalId + "-" + companyId + "-" + groupEntryId + "-" + branch.getId();
							dataMap.remove(cacheKey);
							if (beforeSize > dataMap.size()) {
								itemNotInModelRemovedCount++;
								logger.debug("NOT USED Removed: " + cacheKey);
							}
						}
					}
					continue;
				} else {
					Item item = itemService.getItem(itemId);
					for (Integer groupEntryId : reportQuery.getGroupEntries(companyId)) {
						GroupEntry groupEntry = groupService.getGroupEntry(groupEntryId);
						for (Integer intervalId : reportQuery.getIntervalIds()) {
							Interval interval = referenceService.getInterval(intervalId);
							boolean isEquation = groupEntry.isAggregate() || (!ip.isRawDataValue(interval));
							if (isEquation) {
								if (null == branch) {
									logger.debug("Item " + item.getCode() + " is a CALC in model " + model.getCode() + ", but company " + companyId + " is not on run " + runId + " so there is no value for it in the datamap.");
									continue;
								}
								logger.debug("Item is a CALC in this model. Removing item from data map. Model: " + model.getCode() + " Item: " + item.getCode() + " " + " Branch: " + branch.getName() + "(run: " + branch.getRunId() + " company: " + branch.getCompanyId() + ")");
								// Remove from the dataMap 
								Integer beforeSize = dataMap.size();
								String cacheKey = itemId + "-" + intervalId + "-" + companyId + "-" + groupEntryId + "-" + branch.getId();
								dataMap.remove(cacheKey);
								if (beforeSize > dataMap.size()) {
									calcsRemovedCount++;
									logger.debug("CALC Removed: " + cacheKey);
								}
							}
						}
					}
				}
			}
		}

		logger.debug("Items not in model removed = " + itemNotInModelRemovedCount);
		logger.debug("Calcs removed = " + calcsRemovedCount);
		logger.debug("Remove calculated items from data map - end.");
	}

	
	private void removeCalcsFromTaggedDataMap(	ReportQuery reportQuery,
												Integer runId,
												Integer companyId, 
												Map<String, Object> dataMap) {
		logger.debug("Remove calculated items from data map - start.");
		Branch branch = getBranch(companyId, runId);
		if (null == branch) {
			logger.debug("Company: " + companyId + " is not on run: " + runId);
			logger.debug("Remove calculated items from data map - aborted.");
			return;
		}
		// to find a calc we need model and itemproperties
		// get all model Ids
		Set<Integer> modelIdsSet = new HashSet<Integer>();
		for (ModelItem mi: reportQuery.getReportDef().getModelItems()) {
			modelIdsSet.add(mi.getModelId());
		}
		List<Integer> modelIds = new ArrayList<Integer>(modelIdsSet);

		int calcsRemovedCount = 0;
		int itemNotInModelRemovedCount = 0;
		for (Integer modelId : modelIds) {
			Model model = modelService.getModel(modelId);
			for (Integer itemId : reportQuery.getItemIds()) {
				ItemProperties ip = model.getItemPropertiesForItem(itemId);
				if (null == ip) {
					continue;
				} 
				Item item = itemService.getItem(itemId);
				for (Integer groupEntryId : reportQuery.getGroupEntries(companyId)) {
					GroupEntry groupEntry = groupService.getGroupEntry(groupEntryId);
					for (Integer intervalId : reportQuery.getIntervalIds()) {
						Interval interval = referenceService.getInterval(intervalId);
						boolean isEquation = groupEntry.isAggregate() || (!ip.isRawDataValue(interval));
						if (isEquation) {
							logger.debug("Item is a CALC in this model. Removing item from data map. Model: " + model.getCode() + " Item: " + item.getCode() + " " + " Branch: " + branch.getName() + "(run: " + branch.getRunId() + " company: " + branch.getCompanyId() + ")");
							// Remove from the dataMap 
							// TODO is this right??? There could be many models. The item could be a calc in one model and not in another. BUT there is no difference in the key between the two! 
							// Surely the key should have a model! We should be using a uniform key throughout anyway! Will need to revisit this.
							for (RunTag runTag: reportQuery.getRunTags()) {
								Integer beforeSize = dataMap.size();
								String cacheKey = itemId + "-" + intervalId + "-" + companyId + "-" + groupEntryId + "-" + runTag.getRun().getId() + "-" + runTag.getRunModelTag().getRunModelCompanyTag(companyId).getId();
								dataMap.remove(cacheKey);
								if (beforeSize > dataMap.size()) {
									calcsRemovedCount++;
									logger.debug("CALC Removed: " + cacheKey);
								}
							}
						}
					}
				}
			}
		}

		logger.debug("Items not in model removed = " + itemNotInModelRemovedCount);
		logger.debug("Calcs removed = " + calcsRemovedCount);
		logger.debug("Remove calculated items from data map - end.");
	}

	private void addBlanksToDataMap(ReportQuery reportQuery, Integer runId, Integer companyId, Map<String, Object> dataMap) {
		logger.debug("Add blanks to data map");
		// put blanks in the cache where there are no values
		int blankCount = 0;
		for (Integer branchId : getBranchIds(companyId, runId)) {
			for (Integer itemId : reportQuery.getItemIds()) {
				for (Integer intervalId : reportQuery.getIntervalIds()) {
					for (Integer groupEntryId : reportQuery.getGroupEntries(companyId)) {
						String key = itemId + "-" + intervalId + "-" + companyId + "-" + groupEntryId + "-" + branchId;
						if (!dataMap.containsKey(key)) {
							dataMap.put(key, null);
							blankCount++;
						}
					}
				}
			}
		}
		logger.debug("blankCount = " + blankCount);
		logger.debug("Size of data map = " + dataMap.size());
		logger.debug("end put blanks into cache");
	}

	private Map<String, Object> dataListToMap(List<Data> dataList) {
		logger.debug("dataListToMap start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for (Data data : dataList) {
			// put data into cache
			String key = data.getItem().getId() + "-" + data.getInterval().getId() + "-" + data.getCompany().getId() + "-" + data.getGroupEntry().getId() + "-" + data.getBranch().getId();
			dataMap.remove(key);
			dataMap.put(key, (Object) data);
		}
		logger.debug("dataListToMap end");
		return dataMap;
	}

	private Branch getBranch(int companyId, int runId) {
		return branchDao.findByCompanyAndRun(companyId, runId);
	}

	private List<Integer> getBranchIds(int companyId, int runId) {
		List<Integer> branchIds = new ArrayList<Integer>();
		Branch branch = getBranch(companyId, runId);
		if (null != branch)  { // null indicates that there is no data company and run.
			branchIds.add(branch.getId());
		}
		return branchIds;
	}

	private boolean isEditable(RoleChecker roleChecker, Pot pot, Company company) {
		boolean editable = false;
		if(	null != pot.getBranch(company.getId()) &&
			pot.getBranch(company.getId()).isEditable()) {
			editable = roleChecker.isUserInRole(User.FOUNTAIN_ADMIN_ROLE) || roleChecker.isUserInRole(pot.getItem().getTeam().getCode());
		}
		if (!RunModelTag.LATEST.equals(pot.getRunTag().getRunModelTag())) {
			editable = false; // can't edit tagged data.
		}
		return editable;
	}

	private Map<Integer, List<ListItem>> mapCodeListsByCodeList(
			List<CodeList> codeLists) {
		Map<Integer, List<ListItem>> codeListsByCodeList = new HashMap<Integer, List<ListItem>>();
		for (CodeList codeList : codeLists) {
			List<ListItem> items = itemService.getItemsForCodeList(codeList);
			codeListsByCodeList.put(codeList.getId(), items);
		}
		return codeListsByCodeList;
	}

	private void doLocking(User user, Basket basket, Company company, Pot pot,
			Boolean editable, DataDto dto, List<Lock> locks) {
		User lockingUser = null;
		Lock lock = null;
		if (null != basket) {
			// editing
			lock = lockService.updateLockStatus(pot.getItem().getId(), pot
					.getInterval().getId(), company.getId(), user, editable, dto.getRunId());
			if (null != lock) {
				lockingUser = lock.getUser();
				locks.add(lock);
			}
		}

		if (null != lockingUser) {
			dto.setLocked(!user.equals(lockingUser));
			dto.setLockingUser(lockingUser.getName());
		} else {
			dto.setLocked(false);
		}
	}

	private Map<Integer, Set<GroupEntry>> retrieveGroupEntriesByCompany(
			Map<Integer, ReportCompanyGroups> mapOfReportCompanyGroups) {
		Map<Integer, Set<GroupEntry>> groupEntriesByCompanyMap = new HashMap<Integer, Set<GroupEntry>>();
		Set<Entry<Integer, ReportCompanyGroups>> reportCompanyGroupsMap = mapOfReportCompanyGroups
				.entrySet();
		for (Entry<Integer, ReportCompanyGroups> reportCompaniesGroup : reportCompanyGroupsMap) {
			groupEntriesByCompanyMap.put(reportCompaniesGroup.getKey(),
					reportCompaniesGroup.getValue().retrieveEntries());
		}
		return groupEntriesByCompanyMap;
	}

	public void deleteReport(int id) {
		reportDao.delete(id);
		indexService.index(ReportSearchWrapper.class, id);
	}
	
	public List<BulkDeleteReportDto> bulkDeleteReports(String ids, SecurityContext sc){
		String[] idArr = ids.split(",");
		Boolean success = true;
		ArrayList<BulkDeleteReportDto> bulkDeleteReportDtos = new ArrayList<BulkDeleteReportDto>();
		for(String id : idArr){
			Integer deleteId = new Integer(id);
			BulkDeleteReportDto dto = new BulkDeleteReportDto();
			dto.name = reportDao.findSummaryById(deleteId).getName();
			try{
				//Get the report and check the owner if they aren't an admin that is...
				if(sc.isUserInRole(User.FOUNTAIN_ADMIN_ROLE)){
					reportDao.delete(deleteId);
					//Update the search index...
					indexService.index(ReportSearchWrapper.class, deleteId);
					dto.deleted = true;
					dto.id = deleteId;
				}else{
					ReportDefinition rd = getReportDefinition(deleteId);
					String userName = sc.getUserPrincipal().getName();
					if(rd.getOwnerUser().equals(userName)){
						reportDao.delete(deleteId);
						indexService.index(ReportSearchWrapper.class, deleteId);
						dto.deleted = true;
						dto.id = deleteId;
					}else{
						//error
						dto.deleted = false;
						dto.id = deleteId;
						success = false;
						dto.message = "Sorry - unable to delete a report you did not create.";
					}
				}
			}catch(Exception e){
				logger.error(e.toString());
				reportDao.delete(deleteId);
				dto.deleted = false;
				dto.id = deleteId;
				dto.message = "There was an error deleting this report.";
				success = false;
			}
			bulkDeleteReportDtos.add(dto);
		}
		return bulkDeleteReportDtos;
	}
	
	public List<BulkModifyReportDto> bulkUpdateReportReadOnlyStatus(String ids, SecurityContext sc, boolean readOnly){
		String[] idArr = ids.split(",");
		Boolean success = true;
		ArrayList<BulkModifyReportDto> bulkModifyReportDtos = new ArrayList<BulkModifyReportDto>();
		for(String id : idArr){
			Integer reportId = new Integer(id);
			BulkModifyReportDto dto = new BulkModifyReportDto();
			dto.name = reportDao.findSummaryById(reportId).getName();
			try{
				//Get the report and check the owner if they aren't an admin that is...
				if(sc.isUserInRole(User.FOUNTAIN_ADMIN_ROLE)){
					indexService.index(ReportSearchWrapper.class, reportId);
					reportDao.setWriteStatus(reportId, readOnly);
					dto.readOnly = true;
					dto.id = reportId;
				}else{ 
					ReportDefinition rd = getReportDefinition(reportId);
					String userName = sc.getUserPrincipal().getName();
					if(rd.getOwnerUser().equals(userName)){
						reportDao.setWriteStatus(reportId, readOnly);
						indexService.index(ReportSearchWrapper.class, reportId);
						dto.readOnly = true;
						dto.id = reportId;
					}else{
						//error
						dto.readOnly = false;
						dto.id = reportId;
						success = false;
						dto.message = "Sorry - unable to modify a report you did not create.";
					}
				}
			}catch(Exception e){
				logger.error(e.toString());
				reportDao.delete(reportId);
				dto.deleted = false;
				dto.id = reportId;
				dto.message = "Sorry there was a problem updating this report.";
				success = false;
			}
			bulkModifyReportDtos.add(dto);
		}
		return bulkModifyReportDtos;
	}	

	public ReportDefinition getReportDefinition(int id) {
		ReportDefinition reportDef = reportDao.findById(id);
		
		//List<Tag> tags = tagService.getTags(reportDef);
		//String[] tagsArr = new String[tags.size()];				
		//reportDef.setTags(tags.toArray(tagsArr));
		
		// if there are no companies, add a place holder company
		if (reportDef.getCompanyIds().isEmpty()) {
			List<Integer> companyIds = new ArrayList<Integer>();
			companyIds.add(CompanyPlaceHolder.COMPANY_PLACE_HOLDER.getId());
			reportDef.setCompanyIds(companyIds);
		}
		// if there are no runtags, add a place holder runtag
		if (reportDef.getRunTags().isEmpty()) {
			List<RunTag> runTags = new ArrayList<RunTag>();
			runTags.add(new RunTag(RunPlaceHolder.RUN_PLACE_HOLDER, RunModelTag.PLACE_HOLDER));
			reportDef.setRunTags(runTags);
		}
		else {
			// Replace runTags which only have ids with full runTags
			List<RunTag> fullRunTags = new ArrayList<RunTag>();
			for (RunTag runTag: reportDef.getRunTags()) {
				fullRunTags.add(runTagService.getRunTag(runTag.getRun().getId(), runTag.getRunModelTag().getId(), false));
			}
			reportDef.setRunTags(fullRunTags);
		}
		return reportDef;
	}

	public boolean hideReport(int id) {
		return reportDao.updatePublishedStatus(false, id);
	}

	public boolean publishReport(int id) {
		return reportDao.updatePublishedStatus(true, id);
	}

	@Transactional
	public int saveUpdatedReport(int reportId, String name, User user,
			Team team, List<ModelItem> modelItems, final String group,
			List<Integer> intervalIds, List<String> layoutLeft,
			List<String> layoutTop, boolean publicReport,
			List<Integer> companyIds, boolean displayCGs, boolean displayUnit,
			boolean displayBoncode, boolean displayDesc, boolean displayModel,
			boolean displayCompanyName, boolean displayCompanyAcronym,
			List<RunTagIdsDto> runTagIds,
			final boolean displayRunName, final boolean displayRunDesc,
			final boolean displayTagDisplayName,
   			final boolean displayAgendaName, final boolean displayAgendaCode,
			final boolean readOnly,
			String description) {

		ReportDefinition reportDef = new ReportDefinition();
		reportDef.setCompanyIds(companyIds);
		reportDef.setIntervalIds(intervalIds);
		reportDef.setModelItems(modelItems);
		reportDef.setName(name);
		reportDef.setOwnerUser(user.getName());
		reportDef.setLayoutLeft(layoutLeft);
		reportDef.setLayoutTop(layoutTop);
		reportDef.setPublicReport(publicReport);
		reportDef.setTeamId(team.getId());
		reportDef.setId(reportId);
		reportDef.setReportDisplay(new ReportDisplay());
		reportDef.setReadOnly(readOnly);
		reportDef.getReportDisplay().setDisplayBoncode(displayBoncode);
		reportDef.getReportDisplay().setDisplayCGs(displayCGs);
		reportDef.getReportDisplay().setDisplayCompanyAcronym(displayCompanyAcronym);
		reportDef.getReportDisplay().setDisplayCompanyName(displayCompanyName);
		reportDef.getReportDisplay().setDisplayDesc(displayDesc);
		reportDef.getReportDisplay().setDisplayModel(displayModel);
		reportDef.getReportDisplay().setDisplayUnit(displayUnit);

		reportDef.getReportDisplay().setDisplayRunDesc(displayRunDesc);
		reportDef.getReportDisplay().setDisplayRunName(displayRunName);
		reportDef.getReportDisplay().setDisplayTagDisplayName(displayTagDisplayName);
		reportDef.getReportDisplay().setDisplayAgendaName(displayAgendaName);
		reportDef.getReportDisplay().setDisplayAgendaCode(displayAgendaCode);

		// Set the runTags if they are required.
		reportDef.setRunTags(getRunTags(runTagIds, false));
		if(description!=null){
			reportDef.setDescription(description);
		}

		if (null != group && !"".equals(group)) {
			reportDef.setGroup(groupService.getGroupByCode(group));
		}
		reportDao.update(reportDef);

		return reportId;
	}

	public void buildReport(int reportId) {
		ReportStructure struc = getReportStructure(reportId);
		ReportWriter reportWriter = new HtmlReportWriter(struc);
		try {
			File reportTemplateFile = applicationContext.getResource(reportTemplate).getFile();
			File outputDirFile = applicationContext.getResource(outputDir).getFile();
			reportWriter.generateReportFile(reportTemplateFile, outputDirFile, localDir);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}

	public TableDto writeReportTable(ReportDto report) {
		return reportTableWriter.write(report);
	}

	public TableDto writeReportTableStructure(ReportDto report) {
		return reportTableWriter.write(report, false);
	}

	public ReportCommitResult commitReportTableData(TableDto reportTableDto,
			String auditComment, User user, RoleChecker roleChecker,
			Report report, int companyId) {

		ReportCommitResult result = new ReportCommitResult();
		ReportCommitStatus status = ReportCommitStatus.OK;
		result.setStatus(status);

		if (null == auditComment) {
			result.setStatus(ReportCommitStatus.NO_AUDIT_COMMENT);
			return result;
		}

		// get basket
		Basket basket = basketService.getBasketForUser(user);
		if (null != basket) {
			result.setStatus(ReportCommitStatus.PRE_EXISTING_EDIT_SESSION);
			return result;
		}
		basket = basketService.createBasket(user);

		status = extractEditsFromTableDto(reportTableDto, user, report, status,
				basket, companyId);

		if (ReportCommitStatus.CELL_ERROR == status) {
			closeBasket(user);
			result.setStatus(ReportCommitStatus.CELL_ERROR);
			return result;
		}

		if (basket.isEmpty()) {
			// No changes have been made.
			closeBasket(user);
			result.setStatus(ReportCommitStatus.NO_CHANGES_MADE);
			return result;
		}

		basket.setAuditComment(EXCEL_AUDIT_PREFIX + auditComment);

		try {
			List<Audit> audits = dataService.saveUserChanges(basket);
			result.setAudits(audits);
		} catch (BranchNotEditableException e) {
			result.setStatus(ReportCommitStatus.BRANCH_NOT_EDITABLE);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeBasket(user);
		}
		result.setStatus(ReportCommitStatus.OK);
		return result;
	}

	private ReportCommitStatus extractEditsFromTableDto(
			TableDto reportTableDto, User user, Report report,
			ReportCommitStatus status, Basket basket, int companyId) {
		logger.info("start extract edits from TableDto.");
		Map<String, DataDto> dataMap = report.getDataList();
		for (RowDto rowDto : reportTableDto.getRows()) {
			for (CellDto cellDto : rowDto.getCells()) {
				//
				// NOTE: PLEASE READ THIS.
				// The order of these checks is very carefully considered.
				// Please do not change the
				// order unless you fully understand the implications. Please
				// read and understand
				// all called methods before making any changes.
				//
				if (cellHasNoValue(cellDto)) {
					continue; // We don't want to overwrite an existing value
								// with a blank value.
				}
				if (notADataCell(cellDto)) {
					continue;
				}
				if (cellNotInReport(dataMap, cellDto)) {
					logger.error("Cell key not found in report. It must be the wrong report.");
					status = ReportCommitStatus.CELL_ERROR;
					updateCellToNotFoundError(cellDto);
					closeBasket(user);
					continue;
				}
				if (cellDto.getValue().getBytes().length > 255) {
					status = ReportCommitStatus.CELL_ERROR;
					updateCellToLongDataError(cellDto);
					closeBasket(user);
					continue;
				}
				DataDto dataDto = null;
				if (0 != companyId) {
					String key = cellDto.getKey();
					DataKey dataKey = new DataKey(key);
					dataKey.setCompanyId(companyId);
					dataKey.getKey(true, true);
					dataDto = dataMap.get(cellDto.getKey());
				} else {
					String key = cellDto.getKey();
					dataDto = dataMap.get(key);
				}

				if (!valueHasChanged(cellDto, dataDto)) {
					continue;
				}
				if (cellIsACalc(cellDto)) {
					logger.error("Cell is a Calc. It cannot be edited.");
					status = ReportCommitStatus.CELL_ERROR;
					updateCellToCalcError(cellDto);
					closeBasket(user);
					continue;
				}

				if (cellLockedByOtherUser(user, dataDto)) {
					logger.error("This cell is locked for editing by user "
							+ dataDto.getLockingUser() + ".");
					status = ReportCommitStatus.CELL_ERROR;
					updateCellToLockedError(cellDto, dataDto.getLockingUser());
					closeBasket(user);
					continue;
				}

				if (ReportCommitStatus.CELL_ERROR == status) {
					// We want to collect errors but not build up any more
					// edits.
					continue;
				}

				try {
					updateValue(user, cellDto, dataDto, basket);
				} catch (Exception e) {
					status = ReportCommitStatus.CELL_ERROR;
					closeBasket(user);
					updateCellToUnknownError(cellDto, e);
					e.printStackTrace();
					continue;
				}

				if (basket.getCachedEdits().size() % 100 == 0) {
					logger.info("Edits extracted: "
							+ basket.getCachedEdits().size());
				}
			}
		}
		logger.info("Edits extracted: " + basket.getCachedEdits().size());
		logger.info("end extract edits from TableDto.");
		return status;
	}

	public String checkHeaderCellValuesAreTheSame(
			TableDto serverReportTableDto, TableDto clientReportTableDto) {
		logger.info("Check header cels are consistant with the server for report id: " + serverReportTableDto.getId());
		String errors = "";
		if (clientReportTableDto.getRows().size() != serverReportTableDto.getRows().size()) {
			errors = errors + "Structure Error: Excel has "
					+ clientReportTableDto.getRows().size()
					+ " rows. Server has "
					+ serverReportTableDto.getRows().size() + " rows.";
			return errors;
		}
		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows().iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows().iterator();
		int rowCount = 0;
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			rowCount++;
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();
			if (clientRowDto.getCells().size() != serverRowDto.getCells().size()) {
				errors = errors + "Structure Error: Row " + rowCount
						+ ". Excel has " + clientRowDto.getCells().size()
						+ " cells. Server has "
						+ serverRowDto.getCells().size() + " cells.";
				break; // only report the first error with different size cells.
			}

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells().iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells().iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				if (serverCellDto.getCellType() == CellType.COL_HEADING
						|| serverCellDto.getCellType() == CellType.EMPTY
						|| serverCellDto.getCellType() == CellType.GROUP_ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING_NON_REPEAT) {
					// Ignore values for item description and unit.
					if (clientCellDto.getCol() != 2
							&& clientCellDto.getCol() != 3) {
						// null from the client is the same as zero length
						// string on the server.
						if (clientCellDto.getValue() == null) {
							clientCellDto.setValue("");
						}
						// check that header cell values are the same.
						if (!clientCellDto.getValue().trim().equalsIgnoreCase(serverCellDto.getValue().trim())) {
							if (serverCellDto.getValue().trim().equalsIgnoreCase("Acronym")) {
								if (!clientCellDto.getValue().trim().equalsIgnoreCase("Company")) {
									// let users use either 'acronym' or
									// 'company' to indicate acronym.
									errors = errors + equalityError(clientCellDto,
																	serverCellDto,
																	" Excel cell value is '" + clientCellDto.getValue() + "'. Server cell value is '" + serverCellDto.getValue() + "'.");
								}
							} else if (serverCellDto.getValue().trim().equalsIgnoreCase("Model")) {
								if (!clientCellDto.getValue().trim()
										.equalsIgnoreCase("Destination model")) {
									// let users use either 'model' or
									// 'destination model' to indicate model.
									errors = errors + equalityError(clientCellDto,
																	serverCellDto,
																	" Excel cell value is '" + clientCellDto.getValue() + "'. Server cell value is '" + serverCellDto.getValue() + "'.");
								}
							} else {
								errors = errors + equalityError(clientCellDto,
																serverCellDto,
																" Excel cell value is '" + clientCellDto.getValue() + "'. Server cell value is '" + serverCellDto.getValue() + "'.");
							}
						}
					}
				}
			}
		}
		logger.info("Completed check header cels are consistant with the server for report id: "
				+ serverReportTableDto.getId());
		return errors;
	}

	public void correctHeaderValues(TableDto serverReportTableDto,
			TableDto clientReportTableDto) {
		logger.info("Correct header values for TableDto for report id: "
				+ serverReportTableDto.getId());
		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows()
				.iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows()
				.iterator();
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells()
					.iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells()
					.iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				// check that header cell values are the same. But copy the
				// value for item description and unit.
				if (serverCellDto.getCellType() == CellType.COL_HEADING
						|| serverCellDto.getCellType() == CellType.EMPTY
						|| serverCellDto.getCellType() == CellType.GROUP_ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING_NON_REPEAT) {
					if (null != clientCellDto.getValue()
							&& !clientCellDto
									.getValue()
									.trim()
									.equalsIgnoreCase(
											serverCellDto.getValue().trim())) {
						if (serverCellDto.getValue().trim()
								.equalsIgnoreCase("Acronym")
								&& clientCellDto.getValue().trim()
										.equalsIgnoreCase("Company")) {
							// Change 'company' to 'acronym' to indicate
							// acronym.
							clientCellDto.setValue(serverCellDto.getValue());
						} else if (serverCellDto.getValue().trim()
								.equalsIgnoreCase("Model")
								&& clientCellDto.getValue().trim()
										.equalsIgnoreCase("Destination model")) {
							// Change 'destination model' to 'model' to indicate
							// model.
							clientCellDto.setValue(serverCellDto.getValue());
						}
					}
				}
			}
		}
		logger.info("Completed correct header values for TableDto for report id: "
				+ serverReportTableDto.getId());
	}

	public void copyValuesForItemDescriptionAndUnit(
			TableDto serverReportTableDto, TableDto clientReportTableDto) {
		logger.info("Copy values for item desc and unit in TableDto for report id: "
				+ serverReportTableDto.getId());
		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows()
				.iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows()
				.iterator();
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells()
					.iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells()
					.iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				if (serverCellDto.getCellType() == CellType.COL_HEADING
						|| serverCellDto.getCellType() == CellType.EMPTY
						|| serverCellDto.getCellType() == CellType.GROUP_ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING_NON_REPEAT) {
					if (2 == clientCellDto.getCol()
							|| 3 == clientCellDto.getCol()) {
						// Cols 2 & 3 for description and unit get overwritten
						// by value from the server.
						clientCellDto.setValue(serverCellDto.getValue());
					}
				}
			}
		}
		logger.info("Compalted copy values for item desc and unit in TableDto for report id: "
				+ serverReportTableDto.getId());
	}

	public void copyDataCellKeysAndFormating(TableDto serverReportTableDto,
			TableDto clientReportTableDto) {
		logger.info("Copy cell keys and formating in TableDto for report id: "
				+ serverReportTableDto.getId());
		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows()
				.iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows()
				.iterator();
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells()
					.iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells()
					.iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				// and copy data cell keys and formating from server to client
				// report.
				if (serverCellDto.getCellType() == CellType.INPUT
						|| serverCellDto.getCellType() == CellType.CALC
						|| serverCellDto.getCellType() == CellType.COPYCELL) {
					clientCellDto.setKey(serverCellDto.getKey());
					clientCellDto.setDataType(serverCellDto.getDataType());
					clientCellDto.setStyle(serverCellDto.getStyle());
					clientCellDto.setDataFormat(serverCellDto.getDataFormat());
					clientCellDto.setCellFormat(serverCellDto.getCellFormat());
					clientCellDto.setDecimalPlaces(serverCellDto
							.getDecimalPlaces());
				}
			}
		}
		logger.info("Complated copy cell keys and formating in TableDto for report id: "
				+ serverReportTableDto.getId());
	}

	public void copyHeaderCellFormating(TableDto serverReportTableDto,
			TableDto clientReportTableDto) {
		logger.info("Copy header formating in TableDto for report id: "
				+ serverReportTableDto.getId());
		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows()
				.iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows()
				.iterator();
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells()
					.iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells()
					.iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				// and copy formating from server to client report.
				if (serverCellDto.getCellType() == CellType.COL_HEADING
						|| serverCellDto.getCellType() == CellType.EMPTY
						|| serverCellDto.getCellType() == CellType.GROUP_ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING
						|| serverCellDto.getCellType() == CellType.ROW_HEADING_NON_REPEAT) {
					clientCellDto.setCellFormat(serverCellDto.getCellFormat());
					clientCellDto.setStyle(serverCellDto.getStyle());
				}
			}
		}
		logger.info("Complated copy header formating in TableDto for report id: "
				+ serverReportTableDto.getId());
	}

	public boolean isAcronymColEmpty(TableDto reportTableDto) {
		logger.info("isAcronymColEmpty report id: " + reportTableDto.getId());
		boolean acronymColEmpty = true;
		for (RowDto rowDto : reportTableDto.getRows()) {
			for (CellDto cellDto : rowDto.getCells()) {
				if (0 == cellDto.getCol()) {
					if (null != cellDto.getValue()
							&& !cellDto.getValue().isEmpty()
							&& !cellDto.getValue().trim()
									.equalsIgnoreCase("Acronym")
							&& !cellDto.getValue().trim()
									.equalsIgnoreCase("Company")) {
						acronymColEmpty = false;
					}
				}
			}
		}
		logger.info("isAcronymColEmpty report id: " + reportTableDto.getId()
				+ " returning " + acronymColEmpty);
		return acronymColEmpty;
	}

	public void removeCompanyAcronyms(TableDto reportTableDto) {
		logger.info("addCompanyAcronyms to report id: "
				+ reportTableDto.getId());
		for (RowDto rowDto : reportTableDto.getRows()) {
			for (CellDto cellDto : rowDto.getCells()) {
				if (0 == cellDto.getCol()) {
					if (cellDto.getValue() == null
							|| (!cellDto.getValue().trim()
									.equalsIgnoreCase("Acronym") && !cellDto
									.getValue().trim()
									.equalsIgnoreCase("Company"))) {
						cellDto.setValue("");
					}
				}
			}
		}
		logger.info("addCompanyAcronyms to report id: "
				+ reportTableDto.getId());
	}

	private void updateValue(User user, CellDto cellDto, DataDto dataDto, Basket basket) {
		String editedValueString = cellDto.getValue();
		if (editedValueString.equalsIgnoreCase("##BLANK")) {
			if (null == dataDto.getRawValue()) {
				/**
				 * ========================================================
				 * FIPL-698 - Text values are not overwritten with ##BLANK.
				 * ========================================================
				 * Raw value is always null for text type data it is only set if the value is convertible into a number - see Formatter.java line 97.
				 * So we are checking to see if the value of dataDto is the empty string and not changing it if it already is this. 
				 * 
				 */
				// There remains a problem though. Text data cannot be deleted from numeric fields with ##BLANK.
				// The solution I think is to introduce validation to stop text data getting into numeric fields.  
				if((cellDto.getCellFormat() == "text") && (dataDto.getValue() != "")){  
					editedValueString = "";		
				}
				else{
					// If no value is present, DO NOT add a blank value. ##BLANK is
					// to set a value back to blank. Not to fill the database with
					// blank data.
					return;
				}
			}
			editedValueString = "";
		} else if (dataDto.getItem().getUnit().equals("%")) {
			editedValueString = String.valueOf(Double
					.parseDouble(editedValueString) * 100);
		}
		logger.debug("Edit being created for "
				+ cellDto.getKey()
				+ " changing from '"
				+ dataDto.getRawValue()
				+ "' to '"
				+ ((null == cellDto.getValue()) ? cellDto.getValue() + "'"
						: "'"));

		dataDto.setUpdatedValue(editedValueString);
		UserEdit edit = new UserEdit(user, dataDto, EditType.VALUE);
		basket.putEdit(edit);
		lockService.lockForEdit(edit, user);
		// Not saved to user edit table in database.
	}

	private void updateCellToLockedError(CellDto cellDto, String lockingUser) {
		cellDto.setStyle("error");
		cellDto.setErrorFlag(true);
		cellDto.setErrorText("This cell is locked for editing by user "
				+ lockingUser + ".");
	}

	private void updateCellToNotFoundError(CellDto cellDto) {
		cellDto.setStyle("error");
		cellDto.setErrorFlag(true);
		cellDto.setErrorText("This cell could not be found in the Fountain report corresponding to this sheet. Please check that you are using the correct report.");
	}

	private void updateCellToCalcError(CellDto cellDto) {
		cellDto.setStyle("error");
		cellDto.setErrorFlag(true);
		cellDto.setErrorText("This cell is a calc. It cannot be edited.");
	}

	private void updateCellToLongDataError(CellDto cellDto) {
		cellDto.setStyle("error");
		cellDto.setErrorFlag(true);
		cellDto.setErrorText("The value in this cell is more than 255 characters.");
	}

	private void updateCellToUnknownError(CellDto cellDto, Exception e) {
		cellDto.setStyle("error");
		cellDto.setErrorFlag(true);
		cellDto.setErrorText("This cell caused the following error: "
				+ e.getMessage());
		logger.error("This cell caused the following error: " + e.getMessage());
		e.printStackTrace();
	}

	private boolean cellLockedByOtherUser(User user, DataDto dataDto) {
		return dataDto.isLocked()
				&& !user.getName().equals(dataDto.getLockingUser());
	}

	private boolean cellNotInReport(Map<String, DataDto> dataMap,
			CellDto cellDto) {
		if (null == dataMap.get(cellDto.getKey())) {
			return true;
		}
		return false;
	}

	private boolean notADataCell(CellDto cellDto) {
		if (cellDto.getKey() == null || cellDto.getKey().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean cellHasNoValue(CellDto cellDto) {
		if (cellDto.getValue() == null || cellDto.getValue().trim().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean cellIsACalc(CellDto cellDto) {
		if (cellDto.getDataType().equalsIgnoreCase("calc")) {
			return true;
		}
		return false;
	}

	public boolean valueHasChanged(CellDto cellDto, DataDto dataDto) {
		if (dataDto.getRawValue() == null) {
			if (cellDto.getValue().equals("")) {
				// Both represent the same value. IE there has been no edit.
				return false;
			} else {
				// changing from nothing to something!
				return true;
			}
		}

		try {
			// // new comparison here. This compares the number of dp submitted,
			// but at least the dp of the item!
			// int precision = precisionForComparison(cellDto.getValue(),
			// dataDto);
			// MathContext mathContext = new MathContext(precision,
			// RoundingMode.HALF_UP);
			// // RoundingMode has a dramatic effect
			// // 34.8990253411306 to precision of 4 gives 34.90 NOT 34.89 when
			// using HALF_UP.
			// // 34.8990253411306 to precision of 15 gives 34.8990253411306
			// using HALF_UP and 34.8990253411307 when using UP.
			// // Using DOWN should mean that extra didgits are just droped.
			// // But this would mean to precision of 34.8990253411307 would
			// give 34.8. If a value of 34.9 was submitted the comparison would
			// fail and the value would be changed!
			// BigDecimal proposedValue = new BigDecimal(cellDto.getValue(),
			// mathContext);
			// BigDecimal existingValue = new BigDecimal(dataDto.getRawValue(),
			// mathContext);
			// if (0 == (proposedValue.compareTo(existingValue))) {
			// // no change to the value
			// return false;
			// }

			// // ... and another here. This works like the web front end edits.
			// Any change to the display value is treated as an edit.
			// // This mimics the web front end but whilst the web client has
			// formated data, the excel client has raw value.
			// if (cellDto.getValue().equals(dataDto.getValue())) {
			// // no change to the value
			// return false;
			// }

			// Table of edits made to a value dependent on whih client and
			// algorithm makes the change.
			// original - server on client displayed edit change to
			// Web front end 34.8990253411306 34.9 34.9 34.9 No change
			// Excel - value 34.8990253411306 34.8990253411306 34.9 34.9 No
			// change
			// Excel - raw value 34.8990253411306 34.8990253411306 34.9 34.9
			// 34.9
			//
			// Web front end 34.8990253411306 34.9 34.9 34.8990253411306
			// 34.8990253411306
			// Excel - value 34.8990253411306 34.8990253411306 34.9
			// 34.8990253411306 34.8990253411306
			// Excel - raw value 34.8990253411306 34.8990253411306 34.9
			// 34.8990253411306 No change
			//
			// Web front end 34.8990253411306 34.9 34.9 34.89 34.89
			// Excel - value 34.8990253411306 34.8990253411306 34.9 34.89 34.89
			// Excel - raw value 34.8990253411306 34.8990253411306 34.9 34.89
			// 34.89

			// This mimics the web front end but uses the raw value since that
			// is what the excel client uses.
			String rawValue = adjustRawValueToExcelMaxPrecision(dataDto);
			if (cellDto.getValue().equals(rawValue)) {
				// no change to the value
				return false;
			}
		} catch (NumberFormatException e) {
			// Not a number. Text is valid so use a string comparison.
			if (cellDto.getValue().equals("" + dataDto.getRawValue())) {
				// no change to the value
				return false;
			}
		}
		return true;
	}

	private String adjustRawValueToExcelMaxPrecision(DataDto dataDto) {
		String rawValue = "" + dataDto.getRawValue();
		int maxStringPrecision = 15; // Excel works to precision of 15
		if (rawValue.contains(".")) {
			maxStringPrecision = 16;
		}
		if (rawValue.length() > maxStringPrecision) {
			rawValue = rawValue.substring(0, maxStringPrecision);
		}
		// remove trailing zeros
		while (rawValue.endsWith("0")) {
			rawValue = rawValue.substring(0, rawValue.length() - 1);
		}

		// remove trailing decimal point
		if (rawValue.endsWith(".")) {
			rawValue = rawValue.substring(0, rawValue.length() - 1);
		}

		return rawValue;
	}

	private int precisionForComparison(String value, DataDto dataDto) {
		// precision should be the greatest of: the precision of the proposed;
		// and the number of integers of the existing value plus the number of
		// DP of the item.
		BigDecimal proposedValue = new BigDecimal(value);
		int precisionOfProposed = proposedValue.precision();
		BigDecimal existingValue = new BigDecimal(dataDto.getRawValue());
		int precisionOfExisting = existingValue.divideToIntegralValue(
				BigDecimal.ONE).precision()
				+ dataDto.getItemPropertiesDto().getDecimalPlaces();

		return (precisionOfProposed < precisionOfExisting) ? precisionOfProposed
				: precisionOfExisting;
	}

	private void closeBasket(User user) {
		lockService.clearLocksForUser(user.getId());
		basketService.clearBasket(user);
	}

	public enum ReportCommitStatus {
		OK, NO_AUDIT_COMMENT, PRE_EXISTING_EDIT_SESSION, NO_CHANGES_MADE, CELL_ERROR, REPORT_STRUCTURE_UNEQUAL, BRANCH_NOT_EDITABLE;

	}

	public class ReportCommitResult {
		private ReportCommitStatus status;
		private List<Audit> audits;

		public List<Audit> getAudits() {
			return audits;
		}

		public void setAudits(List<Audit> audits) {
			this.audits = audits;
		}

		public ReportCommitStatus getStatus() {
			return status;
		}

		public void setStatus(ReportCommitStatus status) {
			this.status = status;
		}

	}

	public String checkReportTableEquality(TableDto clientReportTableDto,
			TableDto serverReportTableDto) {

		if (clientReportTableDto.getRows().size() != serverReportTableDto
				.getRows().size()) {
			return "Structure Error: Excel has "
					+ clientReportTableDto.getRows().size()
					+ " rows. Server has "
					+ serverReportTableDto.getRows().size() + " rows.";
		}

		Iterator<RowDto> clientRowIterator = clientReportTableDto.getRows()
				.iterator();
		Iterator<RowDto> serverRowIterator = serverReportTableDto.getRows()
				.iterator();

		String errors = "";
		int rowCount = 0;
		while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
			rowCount++;
			RowDto clientRowDto = clientRowIterator.next();
			RowDto serverRowDto = serverRowIterator.next();

			if (clientRowDto.getCells().size() != serverRowDto.getCells()
					.size()) {
				errors = errors + "Structure Error: Row " + rowCount
						+ ". Excel has " + clientRowDto.getCells().size()
						+ " cells. Server has "
						+ serverRowDto.getCells().size() + " cells.";
				break; // only report the first error with different size cells.
			}

			Iterator<CellDto> clientCellIterator = clientRowDto.getCells()
					.iterator();
			Iterator<CellDto> serverCellIterator = serverRowDto.getCells()
					.iterator();
			while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
				CellDto clientCellDto = clientCellIterator.next();
				CellDto serverCellDto = serverCellIterator.next();
				// check that cells are the same.
				if (clientCellDto == null)
					errors = errors
							+ equalityError(clientCellDto, serverCellDto,
									" Excel cell does not exist.");
				if (serverCellDto == null)
					errors = errors
							+ equalityError(clientCellDto, serverCellDto,
									" Server cell does not exist.");
				if (clientCellDto.getCol() != serverCellDto.getCol())
					errors = errors
							+ equalityError(
									clientCellDto,
									serverCellDto,
									" Server col no. is "
											+ serverCellDto.getCol() + 1);
				if (clientCellDto.getRow() != serverCellDto.getRow())
					errors = errors
							+ equalityError(
									clientCellDto,
									serverCellDto,
									" Server row no. is "
											+ serverCellDto.getRow() + 1);
				if (clientCellDto.getValue() == null) {
					if (serverCellDto.getValue() != null
							&& !serverCellDto.getValue().isEmpty())
						errors = errors
								+ equalityError(clientCellDto, serverCellDto,
										" Excel cell has no value. Server cell value is '"
												+ serverCellDto.getValue()
												+ "'.");
				} else if (!valuesEqual(clientCellDto.getValue(),
						serverCellDto.getValue())) {
					errors = errors
							+ equalityError(
									clientCellDto,
									serverCellDto,
									" Excel cell value is '"
											+ clientCellDto.getValue()
											+ "'. Server cell value is '"
											+ serverCellDto.getValue() + "'.");
				}
			}
		}

		if (!errors.isEmpty()) {
			return errors;
		}
		return "equal";
	}

	private String equalityError(CellDto clientCellDto, CellDto serverCellDto,
			String message) {
		return "Cell Error: row " + (clientCellDto.getRow() + 1) + " col "
				+ (clientCellDto.getCol() + 1) + ": " + message + "\\n";
	}

	private boolean valuesEqual(String value, String otherValue) {
		try {
			MathContext mathContext = new MathContext(15, RoundingMode.DOWN);
			if (0 == (new BigDecimal(value, mathContext))
					.compareTo(new BigDecimal(otherValue, mathContext))) {
				// no change to the value
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			// Not a number. Text is valid so use a string comparison.
			if (value.equals(otherValue)) {
				// no change to the value
				return true;
			}
		}
		return false;
	}

	public boolean isValidReportTable(int id) {
		ReportDefinition reportDef = getReportDefinition(id);
		if (reportDef.getLayoutLeft().size() != 2
				|| reportDef.getLayoutTop().size() != 2) {
			// Report is not the correct shape for a report table.
			return false;
		}
		if (!reportDef.getLayoutLeft().get(0).equalsIgnoreCase("Company")
				|| !reportDef.getLayoutLeft().get(1).equalsIgnoreCase("Item")
				|| !reportDef.getReportDisplay().isDisplayDesc()
				|| !reportDef.getReportDisplay().isDisplayUnit()
				|| !reportDef.getReportDisplay().isDisplayBoncode()
				|| reportDef.getReportDisplay().isDisplayCGs()
				|| !reportDef.getReportDisplay().isDisplayModel()
				|| reportDef.getReportDisplay().isDisplayCompanyName()
				|| !reportDef.getReportDisplay().isDisplayCompanyAcronym()) {
			// Report is not the correct shape for a report table.
			return false;
		}
		return true;
	}
	
	private void writeAxisInfo(Map<String, DataDto> dataList) {
		Set<String> keyList = new HashSet<String>();
		Map<String, Integer> runs = new HashMap<String, Integer>();
		Map<String, Integer> tags = new HashMap<String, Integer>();
		Map<String, Integer> companies = new HashMap<String, Integer>();
		Map<String, Integer> intervals = new HashMap<String, Integer>();
		Map<String, Integer> items = new HashMap<String, Integer>();
		Map<String, Integer> itemProperties = new HashMap<String, Integer>();
		Map<String, Integer> groupEntries = new HashMap<String, Integer>();
		Map<String, Integer> models = new HashMap<String, Integer>();
		Map<String, Integer> branches = new HashMap<String, Integer>();
		for (String key: dataList.keySet()) {
			System.out.println(key + " = " + "|" + dataList.get(key).getValue() + "|" + dataList.get(key).getRawValue());
			DataKey dataKey = new DataKey(key); 
			Integer runCount = runs.get(dataKey.getRunId());
			if (null == runCount) { runCount = 0; } 
			runs.put(dataKey.getRunId(), ++runCount);
			
			Integer companyCount = companies.get(dataKey.getCompanyId());
			if (null == companyCount) { companyCount = 0; } 
			companies.put(dataKey.getCompanyId(), ++companyCount);
			
			Integer tagCount = tags.get(dataKey.getTagId());
			if (null == tagCount) { tagCount = 0; } 
			tags.put(dataKey.getTagId(), ++tagCount);
			
			Integer intervalCount = intervals.get(dataKey.getIntervalId());
			if (null == intervalCount) { intervalCount = 0; } 
			intervals.put(dataKey.getIntervalId(), ++intervalCount);
			
			Integer itemCount = items.get(dataKey.getItemId());
			if (null == itemCount) { itemCount = 0; } 
			items.put(dataKey.getItemId(), ++itemCount);
			
			Integer itemPropertyCount = itemProperties.get(dataKey.getItemPropertiesId());
			if (null == itemPropertyCount) { itemPropertyCount = 0; } 
			itemProperties.put(dataKey.getItemPropertiesId(), ++itemPropertyCount);
			
			Integer groupEntryCount = groupEntries.get(dataKey.getGroupEntryId());
			if (null == groupEntryCount) { groupEntryCount = 0; } 
			groupEntries.put(dataKey.getGroupEntryId(), ++groupEntryCount);
			
			Integer modelCount = models.get(dataKey.getModelId());
			if (null == modelCount) { modelCount = 0; } 
			models.put(dataKey.getModelId(), ++modelCount);
			
			Integer branchCount = branches.get(dataKey.getBranchId());
			if (null == branchCount) { branchCount = 0; } 
			branches.put(dataKey.getBranchId(), ++branchCount);
		}

		printAxisStat("Run", runs);
		printAxisStat("Tag", tags);
		printAxisStat("Company", companies);
		printAxisStat("Interval", intervals);
		printAxisStat("Item", items);
		printAxisStat("ItemProperty", itemProperties);
		printAxisStat("GroupEntry", groupEntries);
		printAxisStat("Model", models);
		printAxisStat("Branch", branches);
		
	}

	private void printAxisStat(String axisName, Map<String, Integer> axis) {
		for (String key: axis.keySet()) {
			System.out.println(axisName + ": " + key + " = " + axis.get(key) );
		}
		System.out.println(axisName + " total keys = " + axis.size() );
	}
	
	/**
	 * Check if a report is marked as readOnly. 
	 */
	public boolean isReportReadOnly(Integer id){
		throw new NotImplementedException();
	}
	
	/**
	 * Return a list of the lightweight report search wrappers that are used to bulk populate the search indexes.
	 * The actual report Domain objects are to heavyweight for this to be feasible. 
	 * @return A list of ReprotSearchWrapppers.
	 */
	public List<ReportSearchWrapper> getAllReportSearchWrappers(){
		return reportDao.getAllReportSearchWrappers();
	}
	
	public ReportSearchWrapper getReportSearchWrapper(Integer id){
		return reportDao.getReportSearchWrapper(id);
	}	

	public Boolean indexAllReports(){
		ReportSearchHelper.getInstance().indexReports();
		return null;
	}
}
