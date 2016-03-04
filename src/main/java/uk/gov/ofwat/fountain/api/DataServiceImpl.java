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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.Value;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

/**
 * Service class that has operations relating to the actual instance data
 */
public class DataServiceImpl implements DataService {
	
	private static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

	private DataDao dataDao;
	private ReferenceService referenceService;
	private LockService lockService;
	private AuditService auditService;
	private ConfidenceGradeService confidenceGradeService;	
	private ItemService itemService;
	private GroupService groupService;
	private BranchDao branchDao;
	private RunTagService runTagService; 
	private RunService runService;
	private ModelService modelService;

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public void setConfidenceGradeService(
			ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	
	public Data getDataForPot(Pot pot, Company company, GroupEntry groupEntry, boolean readOnly){
		Data data = null;
		Branch branch = pot.getBranch(company.getId());

		if (null == branch ||
			branch.getName().equals("NONE")) {
			// blank data
			logger.debug("getDataForPot() blank data created for Item: " + pot.getItem().getId() + " Interval: " + pot.getInterval().getId() + " Company: " + company.getId() + " Model: " + pot.getModel().getId() + " Branch: " + ((null == branch)? "none" : branch.getId()));
			data = createBlankData(pot.getItem(), pot.getInterval(), company, groupEntry);
		}
		else {
			if (pot.getRunTag().getRunModelTag() == RunModelTag.LATEST) {
				// Data is the latest on a run.
				data = dataDao.getBranchData(pot.getItem().getId(), pot.getInterval().getId(), company.getId(), groupEntry.getId(), branch.getId());
			}
			else {
				// Tagged data. It should be in the cache already
				int tagIdForCompany = pot.getRunTag().getRunModelTag().getRunModelCompanyTag(company.getId()).getId();
				data = dataDao.getTaggedData(pot.getItem().getId(), pot.getInterval().getId(), company.getId(), groupEntry.getId(), pot.getRunTag().getRun().getId(), tagIdForCompany);
			}
		}
		
		if (null != data) {
	        data.setCompany(company);
	        if (!readOnly) {
			    User user = lockService.getLockingUser(pot.getItem().getId(), pot.getInterval().getId(), company.getId());
			    if(null != user){
			    	data.setLocked(true);
			    	data.setLockingUser(user);
			    }
	        }
		}
		return data;
	}

	public Data createData(Pot pot, Company company, GroupEntry groupEntry, String value, String cg, boolean calculatedValue) {
		Branch branch = pot.getBranch(company.getId());
		if (null != branch &&
			branch.getName().equals("NONE")) {
			logger.debug("createData(): blank data created for Item: " + pot.getItem().getId() + " Interval: " + pot.getInterval().getId() + " Company: " + company.getId() + " Model: " + pot.getModel().getId() + " Branch: " + branch.getId());
			return createBlankData(pot.getItem(), pot.getInterval(), company, groupEntry);
		}
		return createData(company, pot.getItem(), groupEntry, value, cg, calculatedValue, branch, pot.getInterval());
	}

	public Data createBlankData(Item item, Interval interval, Company company, GroupEntry groupEntry) {
		return createData(company, item, groupEntry, "", "", false, null, interval);
	}

	private Data createData(Company company, Item item, GroupEntry groupEntry, String value, String cg, boolean calculatedValue, Branch branch, Interval interval) {
		Data data = new Data();
		data.setCompany(company);
		data.setItem(item);
		data.setGroupEntry(groupEntry);
		data.setValue(value);
		data.setConfidenceGrade(confidenceGradeService.getConfidenceGrade(cg));
		data.setCalculatedValue(calculatedValue);
		data.setBranch(branch);
		data.setInterval(interval);
		return data;
	}

	public List<Audit> saveUserChanges(Basket basket) throws BranchNotEditableException {
		return saveUserChanges(basket, false);
	}
	
	public List<Audit> saveUserChanges(Basket basket, boolean newBranch) throws BranchNotEditableException {
		logger.info("start dataService.saveUserChanges(basket).");
		
		Map<Integer, Audit> currentAudits = new HashMap<Integer, Audit>(); // will contain 1 audit for each company
		Audit audit = null;

		int editCount = 0;
		for(UserEdit edit : basket.getCachedEdits()){
			Branch branch = branchDao.findById(edit.getBranchId());
			if (!branch.isEditable()) {
				String msg = "Branch " + branch.getName() + " is not editable.";
				logger.debug(msg);
				throw new BranchNotEditableException(msg);
			}
			audit = currentAudits.get(edit.getCompanyId());
			if (null == audit) {
				audit = auditService.createAudit(basket.getAuditComment(), basket.getUser(), referenceService.getCompany(edit.getCompanyId()));
				currentAudits.put(edit.getCompanyId(), audit);
			}
			Data oldData; 
			if (newBranch) {
				oldData = null;
			}
			else {
				oldData = dataDao.getBranchData(edit.getItemId(), edit.getIntervalId(), edit.getCompanyId(), edit.getGroupEntryId(), edit.getBranchId());
			}
			
			// if oldData is null, there was no original value - so look up the item, interval, group entry and company.. just won't have a value
			if (oldData==null){
				oldData = createData(edit);
			}
			if (edit.getEditType() == EditType.CONFIDENCE_GRADE) {
				// put the new cg into the data
				if(null != edit.getValue() ){
					oldData.setConfidenceGrade(confidenceGradeService.getConfidenceGrade(edit.getValue()));
				}
			}
			else {
				// put the new value into the data
				String newVal = edit.getValue();

				if (!itemService.getItem(edit.getItemId()).getUnit().equalsIgnoreCase("Text")) { // leave text data as it is 
					// unformat the data
					NumberFormat f = NumberFormat.getInstance();
					try {
						Number value = f.parse(newVal);
						newVal = value.toString();
					} catch (ParseException pe) {
						// can't parse it so leave it as it was.
						// numeric validation needs to happen elsewhere.
					}
				}

				oldData.setValue(newVal);
			}
			if (oldData.getId()==0){
				dataDao.create(oldData, audit);
			} else {
				dataDao.updateValue(oldData, audit);
			}

			if ((++editCount)%1000 == 0) {
				logger.info("Edits saved: " + editCount);
			}
		}

		if (!newBranch) {
			// new branches are not locked.
			lockService.clearLocksForUser(basket.getUser().getId());
		}
		logger.info("Edits saved: " + editCount);
		logger.info("end dataService.saveUserChanges(basket).");
		
		return new ArrayList<Audit>(currentAudits.values());
	}
	
	public List<Audit> saveNewRunBasket(Basket basket) throws BranchNotEditableException {
		logger.info("start dataService.saveUserChanges(basket).");

		Map<String, Data> newDataMap = collectEditsIntoData(basket);
		Map<Integer, Audit> currentAudits = saveNewRunData(basket, newDataMap);

		logger.info("Edits  saved: " + basket.getCachedEdits().size());
		logger.info("Values saved: " + newDataMap.values().size());
		logger.info("end dataService.saveUserChanges(basket).");
		
		return new ArrayList<Audit>(currentAudits.values());
	}

	private Map<Integer, Audit> saveNewRunData(Basket basket,
			Map<String, Data> newDataMap) {
		Map<Integer, Audit> currentAudits = new HashMap<Integer, Audit>(); // will contain 1 audit for each company
		int valueCount = 0;
		for (Data data: newDataMap.values()) {
			Audit audit = currentAudits.get(data.getCompany().getId());
			if (null == audit) {
				audit = auditService.createAudit(basket.getAuditComment(), basket.getUser(), referenceService.getCompany(data.getCompany().getId()));
				currentAudits.put(data.getCompany().getId(), audit);
			}
			dataDao.create(data, audit);
			if ((++valueCount)%1000 == 0) {
				logger.info("Values saved: " + valueCount);
			}
		}
		return currentAudits;
	}

	private Map<String, Data> collectEditsIntoData(Basket basket)
			throws BranchNotEditableException {
		Map<String, Data> newDataMap = new HashMap<String, Data>();
		int editCount = 0;
		for(UserEdit edit : basket.getCachedEdits()){
			Branch branch = branchDao.findById(edit.getBranchId());
			if (!branch.isEditable()) {
				String msg = "Branch " + branch.getName() + " is not editable.";
				logger.debug(msg);
				throw new BranchNotEditableException(msg);
			}

			if (edit.getEditType() == EditType.CONFIDENCE_GRADE) {
				addCGToDataMap(newDataMap, edit);
			}
			else {
				addValueToDataMap(newDataMap, edit);
			}
			if ((++editCount)%1000 == 0) {
				logger.info("Edits processed: " + editCount);
			}
		}
		return newDataMap;
	}

	private void addValueToDataMap(Map<String, Data> newDataMap, UserEdit edit) {
		String newVal = edit.getValue();
		if (!itemService.getItem(edit.getItemId()).getUnit().equalsIgnoreCase("Text")) { // leave text data as it is 
			// unformat the data
			NumberFormat f = NumberFormat.getInstance();
			try {
				Number value = f.parse(newVal);
				newVal = value.toString();
			} catch (ParseException pe) {
				// can't parse it so leave it as it was.
				// numeric validation needs to happen elsewhere.
			}
		}

		String key = edit.getKey();
		if (newDataMap.containsKey(key)) {
			// get the data from the map
			Data data = newDataMap.get(key);
			// add the value to the data
			data.setValue(newVal);
		}
		else {
			// create the data with the value 
			Data data = createData(edit);
			// add the value to the data
			data.setValue(newVal);
			// put the data into the map
			newDataMap.put(key, data);
		}
	}

	private void addCGToDataMap(Map<String, Data> newDataMap, UserEdit edit) {
		if(null != edit.getValue() ){
			String keyWithoutCG = edit.getKey().replaceFirst("CG", "");
			if (newDataMap.containsKey(keyWithoutCG)) {
				// get the data from the map
				Data data = newDataMap.get(keyWithoutCG);
				// add the CG to the data
				data.setConfidenceGrade(confidenceGradeService.getConfidenceGrade(edit.getValue()));
			}
			else {
				// create the data with the CG 
				Data data = createData(edit);
				// add the CG to the data
				data.setConfidenceGrade(confidenceGradeService.getConfidenceGrade(edit.getValue()));
				// put the data into the map
				newDataMap.put(keyWithoutCG, data);
			}
		}
	}

	private Data createData(UserEdit edit) {
		Data data = createData(	referenceService.getCompany(edit.getCompanyId()), 
				itemService.getItem(edit.getItemId()), 
				groupService.getGroupEntry(edit.getGroupEntryId()), 
				null, 
				null, 
				false, 
				branchDao.findById(edit.getBranchId()), 
				referenceService.getInterval(edit.getIntervalId()));
		return data;
	}
	
	@Transactional
	public void freeDataTag(String tagDisplayName, int runId, String name, User user) throws TagAllreadyExistsException {
		Run run = runService.getRun(runId);
		Model freeTagModel = modelService.createModel("FT-" + ((run.getName().length() > 33) ? run.getName().substring(0, 33) : run.getName()), 
													"FT_RUNID" + run.getId(), 
													ModelType.TAG_POINT.getName(), 
													"PR14", 
													null, 
													false, 
													0,
													RunPlaceHolder.RUN_PLACE_HOLDER.getId());

		for (Integer companyId: run.getCompanyIds()) {
			runService.createRunModel(run, companyId, user, freeTagModel);
		}
		RunModelTag newTag = runTagService.tagRun(tagDisplayName, run.getId(), freeTagModel.getId());
		if (null == newTag) {
			throw new TagAllreadyExistsException();
		}
	}

	@Transactional
	public boolean completeModel(String tagDisplayName, int runId, int companyId, int modelId, String userName, boolean tagOnCompletion) {
		if (tagOnCompletion) {
			RunModelCompanyTag newTag = runTagService.tagRunCompany(tagDisplayName, runId, modelId, companyId);
			if (null == newTag) {
				return false;
			}
		}
		
		Run run = runService.getRun(runId);
		RunModel runModel = runService.getRunModel(modelId, run, companyId);
		runModel.setLastModifiedBy(userName);
		runModel.setCompleted(true);
		runService.updateRunModel(runModel);
		return true;
	}

	private List<Value> extractValuesFromData(List<Data> dataList) {
		List<Value> values = new ArrayList<Value>();
		for (Data data: dataList) {
			Value value = new Value();
			value.setId(data.getValueId());
			values.add(value);
		}
		return values;
	}

	public List<Data> getReportData(List<Integer> branchIds, List<Integer> companyIds, List<Integer> itemIds, List<Integer> intervalIds, List<Integer> groupEntryIds) {
		return dataDao.getReportData(branchIds, companyIds, itemIds, intervalIds, groupEntryIds);
	}
	
	@Override
	public Map<String, Object> getTaggedData(List<RunTag> runTags, List<Integer> companyIds, List<Integer> intervalIds, List<Integer> itemIds) {
		Map<String, Object> dataMapCollector = new HashMap<String, Object>();
		for (RunTag runTag: runTags) {
			if (runTag.getRunModelTag().equals(RunModelTag.LATEST)) {
				// Only getting tagged data here. Not latest.
				continue;
			}
			for (Integer companyId: companyIds) {
				RunModelCompanyTag runModelCompanyTag = runTag.getRunModelTag().getRunModelCompanyTag(companyId);
				List<Data> data = getTaggedDataForRun(runTag.getRun().getId(), companyId, runModelCompanyTag.getId());
				if (null != data) {
					List<Data> filteredData = filterByIntervalAndItem(data, intervalIds, itemIds);
					Map<String, Object> dataMap = taggedDataListToMap(filteredData);
					dataMapCollector.putAll(dataMap);
				}
			}
		}
		return dataMapCollector;
	}
	
	private List<Data> filterByIntervalAndItem(List<Data> data, List<Integer> intervalIds, List<Integer> itemIds) {
		List<Data> filteredData = new ArrayList<Data>();
		for (Data dat: data) {
			//System.out.println("Data id:" + dat.getId() + ":" + dat.getItem() + ":" + dat.getInterval());
			if (intervalIds.contains(dat.getInterval().getId()) &&
				itemIds.contains(dat.getItem().getId())) {
				filteredData.add(dat);
			}
		}
		return filteredData;
	}

	@Override
	public List<Data> getTaggedDataForRun(int sourceRunId, int companyId, int tagId) {
		Branch branch = branchDao.findByCompanyAndRun(companyId, sourceRunId);
		if (null == branch) {
			return null; // No branch. No data.
		}
		List<Data> data = dataDao.getTagDataForBranch(branch.getId(), tagId);
		return data;
	}
	
	private Map<String, Object> taggedDataListToMap(List<Data> dataList) {
		logger.debug("dataListToMap start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for (Data data : dataList) {
			// put data into cache
			String key = data.getItem().getId() + "-"
					+ data.getInterval().getId() + "-"
					+ data.getCompany().getId() + "-"
					+ data.getGroupEntry().getId() + "-"
					+ data.getRunId() + "-"
					+ data.getTagId();
			dataMap.remove(key);
			dataMap.put(key, (Object) data);
		}
		logger.debug("dataListToMap end");
		return dataMap;
	}

	@Override
	public void addToCache(Map<String, Object> dataMap) {
		dataDao.addToCache(dataMap);
	}

	public Boolean isDataInCache(String key) {
		return dataDao.isDataInCache(key);
	}

	@Override
	public void addToTaggedCache(Map<String, Object> dataMap) {
		dataDao.addToTaggedCache(dataMap);
	}

}
