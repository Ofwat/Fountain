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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.api.table.POITableReader;
import uk.gov.ofwat.fountain.dao.ModelPageDao;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ListItem;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.TransientDataCache;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.marshallers.ExcelDataTableUnmarshaller;

public class TableServiceImpl implements TableService {

	private static Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

	private ModelService modelService;
	private ReferenceService referenceService;
	private LockService lockService;
	private BasketService basketService;
	private ConfidenceGradeService confidenceGradeService;
	private ItemService itemService;
	private GroupEntryOrdinalComparator groupEntryOrdinalComparator;
	private ExcelDataTableUnmarshaller excelDataTableUnmarshaller; 
	private POITableReader poiReader;
	private ModelPageDao modelPageDao;	
	private RunService runService;

	public DataTable getTableForCompany(int tableId, int companyId, User user, RoleChecker roleChecker) {
		// TODO When we get reports we batch up all of the items we need, including ones in calcs, and fetch 
		// them all in one go. This is more efficient than the way we do it here. But 
		// it shouldn't be any slower now than before. A good improvement though would be to fetch all data
		// from the DB in one hit. This would mean fetching more data than we need as we need to get a 
		// whole multi-dimensional block from the DB. Filters need to be applied to remove unwanted 
		// data. The rest is then put into the cache and the code below will retrieve it from the cache as
		// needed. Simples.
		
		// TODO - should really remove references to the DTO package
		// within this class.
		int itemCount = 0;
		
		Table table = modelService.getTable(tableId);
		Company company = referenceService.getCompany(companyId);
		
		SortedSet<String>intervals = new TreeSet<String>();
		SortedSet<String>items = new TreeSet<String>();
		Map<String, DataDto> dtoList = new HashMap<String, DataDto>();
		Comparator<GroupEntry> comparator = groupEntryOrdinalComparator;
		SortedSet<GroupEntry> groupEntries = new TreeSet<GroupEntry>(comparator);
		
		ItemProperties itemProperties = null;
		
		// Transient cache used only in this table
		TransientDataCache transientDataCache = new TransientDataCache();
		
		// temp cache of details about items to avoid repeated db reads
		Map<Integer, ItemDetails> itemDetailsCache = new TreeMap<Integer, ItemDetails>();

		List<CodeList> codeLists = new ArrayList<CodeList>(); 
		List<Lock> locks = new ArrayList<Lock>();
		Model model = null;
		Basket basket = null;
		for(Pot pot : table.getPots().values()){
			if(null == model){
				model = pot.getModel();
				basket = basketService.getBasketForUser(user);
			}
			ItemDetails iDet = itemDetailsCache.get(pot.getItem().getId());
			if(null == iDet){
				iDet = new ItemDetails();
				iDet.groupEntries = pot.getGroupsForPotItem(company);
				itemDetailsCache.put(pot.getItem().getId(), iDet);
			}
			
			itemProperties = model.getItemPropertiesForItem(pot.getItem().getId());
			
			boolean needTotal = false;
			Group group = null;
			for(GroupEntry groupEntry: iDet.groupEntries){
				if(groupEntry.getOrdinal() > 0){
					// this means the group is a real group, not a non grouped row
					// so we'll add a total row for it when we're done here.
					needTotal = true;
					group = groupEntry.getGroup();
				}
				 processGroupEntry(user, transientDataCache,
						company,
						intervals, items,  itemProperties,
						basket, pot, 
						groupEntry, model, dtoList,
			        	locks,
			        	roleChecker);
				 
				 groupEntries.add(groupEntry);
//				 logger.warn("itemCount = " + itemCount);
			}
			if(needTotal){
				// now calculate a total row.
				GroupEntry groupEntry = processTotal(user, 
												     transientDataCache,
												     company,
												     itemProperties,
												     basket, 
												     pot, 
												     group, 
												     dtoList);
				 groupEntries.add(groupEntry);
				 logger.warn("itemCount = " + itemCount);
			}
			
			
			if (null != itemProperties.getItem().getCodeList()) {
				codeLists.add(itemProperties.getItem().getCodeList());
			}
		}

		DataTable dataTable = new DataTable();
		dataTable.setId(table.getId());
		dataTable.setName(table.getName());
		dataTable.setColHeadings(intervals);
		dataTable.setGroupEntries(groupEntries);
		dataTable.setDataList(dtoList);
		dataTable.setCgs(confidenceGradeService.getConfidenceGrades());
		dataTable.setCodeListsByCodeList(mapCodeListsByCodeList(codeLists));
		dataTable.setLocks(locks);
		dataTable.setCompany(company);
		Map<Integer, Integer> defaultRunIdMap = getDefaultRunIdMap(dataTable); 
		dataTable.setDefaultRunIdMap(defaultRunIdMap); 

		return dataTable;
	}

	private Map<Integer, Integer> getDefaultRunIdMap(DataTable dataTable) {
		Map<Integer, Integer> defaultRunIdMap = new HashMap<Integer, Integer>();
		
		Set<Integer> runIds = new HashSet<Integer>();
		for (DataDto dataDto: dataTable.getDataList().values()) {
			runIds.add(dataDto.getRunId());
		}

		for (Integer runId: runIds) {
			Run runFromDataDto = runService.getRun(runId);
			List<RunRole> roles = new ArrayList<RunRole>();
			roles.add(RunRole.PROXY);			
			List<Run> proxyRunList = runService.getRoleFilteredRunsByAgenda(runFromDataDto.getAgenda().getId(), roles);
			Run proxyRun = proxyRunList.get(0);
			if (null == defaultRunIdMap.get(proxyRun.getId())) {
				// not in map yet
				defaultRunIdMap.put(proxyRun.getId(), runFromDataDto.getId());
			}
		}
		return defaultRunIdMap;
	}


	
	private User updateLocks(User user, Company company, List<Lock> locks,
			Basket basket, Pot pot, boolean editable) {
		User lockingUser = null;
		if (null != basket) {
			// editing
			Lock lock = lockService.updateLockStatus(pot.getItem().getId(),
									 					pot.getInterval().getId(),
									 					company.getId(),
									 					user, 
									 					editable);
			if (null != lock) {
				lockingUser = lock.getUser(); 
				locks.add(lock);
			}
		}
		return lockingUser;
	}

	private boolean isEditable(RoleChecker roleChecker, Pot pot, Company company) {
		boolean editable = false;
		if(	null != pot.getBranch(company.getId()) &&
			pot.getBranch(company.getId()).isEditable()) {
			editable = roleChecker.isUserInRole(User.FOUNTAIN_ADMIN_ROLE) || roleChecker.isUserInRole(pot.getItem().getTeam().getCode());
		}
		return editable;
	}

	private Map<Integer, List<ListItem>> mapCodeListsByCodeList(
			List<CodeList> codeLists) {
		Map<Integer, List<ListItem>> codeListsByCodeList = new HashMap<Integer, List<ListItem>>(); 
		for (CodeList codeList: codeLists) {
			List<ListItem> items = itemService.getItemsForCodeList(codeList);
			codeListsByCodeList.put(codeList.getId(), items);
		}
		return codeListsByCodeList;
	}

	private void processGroupEntry(User user,
        TransientDataCache transientDataCache, 
        Company company,
        SortedSet<String> intervals, 
        SortedSet<String> items,
        ItemProperties itemProperties, 
        Basket basket,
        Pot pot, 
        GroupEntry groupEntry, Model model,
        Map<String, DataDto> dtoList,
        List<Lock> locks,
        RoleChecker roleChecker) {

		Data data = pot.getFormattedData(company, transientDataCache,basket, groupEntry, false);
		boolean editable = isEditable(roleChecker, pot, company);
		User lockingUser = updateLocks(user, company, locks, basket, pot, editable);

		intervals.add(pot.getInterval().getName());
		items.add(pot.getItem().getCode());
		String value = null;
		Double rawValue = null;
		ConfidenceGrade cg = null;
		if (null != data) {
			value = data.getValue();
			cg = data.getConfidenceGrade();
			rawValue = data.getRawValue();
		}
		Branch branch = pot.getBranch(company.getId());
		DataDto dto = new DataDto(company, pot.getInterval(), editable, 
								  itemProperties, groupEntry, 
					              value, cg, rawValue,  
					              new ModelDto(model, null), 
					              (null == branch) ? 0 : branch.getId(), 
					              pot.getRunTag().getRun().getId(), 
					              pot.getRunTag().getRunModelTag().getId());
		if (null != lockingUser) {
			dto.setLocked(!user.equals(lockingUser));
			dto.setLockingUser(lockingUser.getName());
		} else {
			dto.setLocked(false);
		}
		
		dtoList.put(dto.getIdentifier(), dto);
	}

	/**
	 * @param user
	 * @param transientDataCache
	 * @param company
	 * @param intervals
	 * @param items
	 * @param itemProperties
	 * @param basket
	 * @param pot
	 * @param editable
	 * @param lockingUser
	 * @param row
	 */
	private GroupEntry processTotal(User user,
			                  TransientDataCache transientDataCache, 
			                  Company company,
			                  ItemProperties itemProperties, 
			                  Basket basket,
			                  Pot pot,  
			                  Group group,
			                  Map<String, DataDto> dtoList) {
		
		
		
//		create a fictional group entry called total
		GroupEntry ge = new GroupEntry();
		ge.setAggregate(true);
		ge.setCompany(company);
		ge.setDescription("total");
		ge.setGroup(group);
		ge.setOrdinal(0);
		ge.setId(0);
		
		Data data = pot.getFormattedData(company, transientDataCache, basket, ge, false);
		ModelDto modelDto = new ModelDto(pot.getModel(), null);
		
		int branchId = pot.getBranch(company.getId()).getId();
		DataDto dto = new DataDto(company, pot.getInterval(), false, itemProperties, ge, 
								  data.getValue(), null, null, modelDto, branchId, 
					              pot.getRunTag().getRun().getId(), 
					              pot.getRunTag().getRunModelTag().getId());
	
		dtoList.put(dto.getIdentifier(), dto);
		return ge;
	}
	
	public ModelPageDao getModelPageDao() {
		return modelPageDao;
	}

	public void setModelPageDao(ModelPageDao modelPageDao) {
		this.modelPageDao = modelPageDao;
	}
	
	private class ItemDetails{
		private List<GroupEntry>groupEntries;
	}
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}


	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}


	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}


	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}


	public void setConfidenceGradeService(
			ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}


	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public void setGroupEntryOrdinalComparator(
			GroupEntryOrdinalComparator groupEntryOrdinalComparator) {
		this.groupEntryOrdinalComparator = groupEntryOrdinalComparator;
	}

	
	public void setExcelDataTableUnmarshaller(
			ExcelDataTableUnmarshaller excelDataTableUnmarshaller) {
		this.excelDataTableUnmarshaller = excelDataTableUnmarshaller;
	}


	public void setPOITableReader(POITableReader pOITableReader) {
		poiReader = pOITableReader;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public TableUploadMetaData uploadTable(TableUploadMetaData metaData, InputStream inputStream, User user, RoleChecker roleChecker) {

		Basket basket = basketService.getBasketForUser(user);
		if (null != basket) {
			metaData.addError("Error: An edit session is already in use. Please save or cancel edits and re-run the table upload.");
			return metaData;
		}
		
		try {
			poiReader.setWorkBook(inputStream);
			metaData = poiReader.streamExcelToTable(metaData, user, roleChecker);
		} catch (IOException e) {
			e.printStackTrace();
			metaData.getErrors().add("Error: " + e.getMessage());
		}

		
		basket = basketService.getBasketForUser(user);
		if (!metaData.getErrors().isEmpty()) {
			basketService.clearBasket(user);
			metaData.getErrors().add("Changes abandoned. Please correct the errors above and re-run the table upload.");
		}
		else if (basket.isEmpty()) {
			basketService.clearBasket(user);
			metaData.getErrors().add("No changes have been found in " + metaData.getUploadFileName());
		}
		return metaData;
	}

	/**
	 * Get the description for a particular table. 
	 * @param table description
	 * @return
	 */
	private String getTableDescription(Table table){
		ModelPage modelPage = modelPageDao.findByTableId(table.getId());
		return modelPage.getTableDescription();
	}
	
	/**
	 * Append the description to the passed list of tables. 
	 * @param tables
	 * @return tables with updated names. 
	 */
	public List<Table> addTableDescriptions(List<Table> tables){
		List<Table> tablesWithDesc = new ArrayList<Table>();  
		for(Table table : tables){
			table.setDescription(getTableDescription(table));
			tablesWithDesc.add(table);
		}
		return tablesWithDesc;
	}	
	
}

