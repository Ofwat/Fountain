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

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.formula.EquationParseStratagyCollectItems;
import uk.gov.ofwat.fountain.domain.formula.EvaluatableFormula;
import uk.gov.ofwat.fountain.domain.formula.FormulaContext;
import uk.gov.ofwat.fountain.domain.formula.FormulaParser;
import uk.gov.ofwat.fountain.domain.formula.FormulaParserImpl;
import uk.gov.ofwat.fountain.domain.formula.GroupTotalFormulaParserImpl;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class Pot implements Serializable, Entity {
	private static Logger logger = LoggerFactory.getLogger(Pot.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3601791864359767853L;
	private int id;
	private Item item;
	private Model model;
	private Interval interval;
	private FormulaParser formulaParser = null;
	private DataService dataService;
	private ReferenceService referenceService;
	private GroupService groupService;
	private ModelPropertiesMap modelPropertiesMap;
	private ItemService itemService;
	private ModelService modelService;
	private Integer modelId;
	private BranchDao branchDao;
	private RunTag runTag;
	private int runId;
	
	// the five most likely decimal formats - to save creating new ones each time
	private final static Formatter formatter = new Formatter();
	
	
	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setDataService(DataService dataService) {
		if(null == dataService){
			throw new RuntimeException("null dataservice");
		}
		this.dataService = dataService;
	}
	
	public FormulaParser getFormulaParser() {
		if (null == formulaParser) {
			formulaParser = new FormulaParserImpl();
		}
		return formulaParser;
	}
	public void setFormulaParser(FormulaParser formulaParser) {
		this.formulaParser = formulaParser;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public Model getModel() {
		return model;
	}
	
	public Branch getBranch(int companyId) {
		return getBranchDao().findByCompanyAndRun(companyId, runTag.getRun().getId());
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
		if (null != modelId) {
			this.model = modelService.getModel(modelId);
		}
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
		if (null != modelService) {
			this.model = modelService.getModel(modelId);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public ModelPropertiesMap getModelPropertiesMap() {
		return modelPropertiesMap;
	}
	public void setModelPropertiesMap(ModelPropertiesMap modelPropertiesMap) {
		this.modelPropertiesMap = modelPropertiesMap;
	}

	public DataService getDataService() {
		return dataService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	@Override
	public boolean equals(Object o) {
		// other object mus be a Pot
		if(! (o instanceof Pot)){return false;}
		Pot other = (Pot)o;
		
		// test ids if they've both been set
		if(other.id > 0 && id > 0){return id == other.id;}
		
		// otherwise go through the rest of the fields
		if(!interval.equals(other.interval)){return false;}
		if(!item.equals(other.item)){return false;}
		
		// must be true by now
		return true;
	}
	
	/**
	 * given a company, this will return a list of group entries available for this pot's item.
	 * @param company
	 * @return
	 */
	public List<GroupEntry> getGroupsForPotItem(Company company){
		return groupService.getGroupEntriesForItem(item, company);
	}

	public void getItemsInEquation(Company company, GroupEntry groupEntry, List<ItemInEquation> itemsInEquation) {

		ItemProperties itemProperties = model.getItemPropertiesForItem(this.item.getId());
		boolean useEq = groupEntry.isAggregate() || (!itemProperties.isRawDataValue(this.interval));
		
		if(useEq) {
			String formula = itemProperties.getFormula(this.interval);
			EvaluatableFormula evaluatableFormula = (EvaluatableFormula) getFormulaParser().parseFormula(formula);
			FormulaContext formulaContext = new FormulaContext(this, model, company, groupEntry, referenceService, groupService, itemsInEquation);
			formulaContext.setEquationParseStratagy(new EquationParseStratagyCollectItems());
			evaluatableFormula.evaluate(formulaContext);
		}
	}

	
	public Data getData(Company company, TransientDataCache transientDataCache, Basket basket, GroupEntry groupEntry, boolean processingReport) {

		boolean calculatedValue = true;
		String value = null;
		String cg = null;
		ItemProperties ip = model.getItemPropertiesForItem(this.item.getId());
		
		boolean useEq = groupEntry.isAggregate() || (!ip.isRawDataValue(this.interval));
		if (!useEq) {
			// Equations can't be tagged. But this isn't an equation so it might be tagged!
			Data data = getTaggedData(company, groupEntry);
			if (null != data) {
				// Got some tagged data. Send it back.
				return data;
			}
		}
		
		if(groupEntry.isAggregate()){
			if(this.item.getUnit().equalsIgnoreCase("Text")){
				value = "Total";
			}
			else{
				value = getValueFromEquation(company, transientDataCache, basket, groupEntry, this.getItem().getCode() + "[TOTAL]", processingReport);
			}
		}
		else if(useEq) {
			value = getValueFromEquation(company, transientDataCache, basket, groupEntry, processingReport);
		}
		else{
			
			value = getValueFromBasket( company, basket, groupEntry);
			if (null == value) {
				value = getValueFromDataBase( company, basket, groupEntry);
			}
			if (null != value) {
				calculatedValue = false;
			}
		}
		if(null != value){
			cg = getConfidenceGradeFromBasket( company, basket, groupEntry);
			if (null == cg) {
				cg = getConfidenceGradeFromDataBase( company, basket, groupEntry);
			}
		}
		Data data = dataService.createData(this, company, groupEntry, value, cg, calculatedValue);

		return data;
	}
	
	public Data getGroupTotal(Company company, TransientDataCache transientDataCache, Basket basket){
		return getGroupTotal(company, null, null, transientDataCache, basket);
	}
	
	public Data getGroupTotal(Company company, GroupEntry groupEntry, List<ItemInEquation> itemsInEquation, TransientDataCache transientDataCache, Basket basket){
		// don't process an entry for a group ending in "TOTAL" as that's already a g
		
		ItemProperties ip = model.getItemPropertiesForItem(this.item.getId());
		String gtEq = ip.getGroupTotalFormula();
		String gtVal = null;
		
		
		// assuming we can just add the value for each group entry
		if(null == gtEq){
			List<GroupEntry> entries = getGroupsForPotItem(company);
			double groupTotal = 0.0d;
			for(GroupEntry entry: entries){
				
				if(entry.getDescription().toUpperCase().endsWith("TOTAL")){
					continue;
				}
				Data groupVal = getData(company, transientDataCache, basket, entry, false);
				if(!(entry.isAggregate()) && null != groupVal && null != groupVal.getValue()){
					try{
						groupTotal += Double.parseDouble(groupVal.getValue());
					}
					catch(NumberFormatException nfe){
						// can't add non numeric values
					}
				}
			}
			gtVal = "" + groupTotal;
		}
		else{
			// there is a group total
			// Note that at present there are no groupTotalFormula in the database. AE 13/01/15.
			if ("".equals(gtEq)){
				gtVal = "";
			}
			else{
				// some other eq that needs to be recursed
				gtVal = getValFromGroupTotalEq(gtEq, company, groupEntry, itemsInEquation, transientDataCache,  basket); 
			}
		}
		return dataService.createData(this, company, null, gtVal, null, true);
	}
	
	private String getValFromGroupTotalEq(String eq, Company company, GroupEntry groupEntry, List<ItemInEquation> itemsInEquation, TransientDataCache transientDataCache, Basket basket){
		GroupTotalFormulaParserImpl gtfp = new GroupTotalFormulaParserImpl(this, 
				                                                           company, 
				                                                           itemService, 
				                                                           modelService,
				                                                           transientDataCache, 
				                                                           basket);
		
		EvaluatableFormula groupFormula = (EvaluatableFormula) gtfp.parseFormula(eq);
		
		FormulaContext formulaContext = new FormulaContext(this, model, company, groupEntry, referenceService, groupService, itemsInEquation);
//		FormulaContext formulaContext = new FormulaContext(this, model, company, null, referenceService, groupService, itemsInEquation);
		if (null != itemsInEquation) {
			formulaContext.setEquationParseStratagy(new EquationParseStratagyCollectItems());
		}
		formulaContext.setBasket(basket);
		formulaContext.setTransientDataCache(transientDataCache);
		return groupFormula.evaluate(formulaContext);
	}
	
	public Data getFormattedData(Company company, TransientDataCache transientDataCache, Basket basket, GroupEntry groupEntry, boolean processingReport) {
		Data data = getData(company, transientDataCache, basket, groupEntry, processingReport);

		formatter.formatData(model, item, data);
		return data;
	}

	private Data getTaggedData(Company company, GroupEntry groupEntry) {
		Data data = null;
		if (!runTag.getRunModelTag().equals(RunModelTag.LATEST)) {
//		if (null != runTag.getTag() && 			// The Data we want is tagged ...
//			0 != runTag.getTag().getId() ) {	// ... a proper tag - rather than a latest 'tag'.
			// All tagged data needs to come from the DB. Its not in a basket, or the transient cache or from a calc.
			Data dataFromDB = dataService.getDataForPot(this, company, groupEntry, true);
			if(null == dataFromDB){
				// data not found in taggedData because there was no value at the time it was tagged!
				data = dataService.createData(this, company, groupEntry, null, null, false);
			}
			else {
				// NOTE: Must create a new data at this point, so that we are not using the same one as in the cache.
				String value = dataFromDB.getValue();
				ConfidenceGrade cg = dataFromDB.getConfidenceGrade();
				String cgCode = "";  
				if (cg != null) {
					cgCode = cg.getCode();
				}
				data = dataService.createData(this, company, groupEntry, value, cgCode, false);
			}
		}
		return data;
	}
	
	
	private String getValueFromBasket(Company company, Basket basket, GroupEntry groupEntry) {
		String value = null;
		if(null != basket) {
			// see if the value is in the basket
			DataKey key = new DataKey(item, interval, company, groupEntry, EditType.VALUE);
			if (null == getBranch(company.getId())) {
				key.setBranchId(0);
			}
			else {
				key.setBranchId(getBranch(company.getId()).getId());
			}
			
			if (null == setRunTagInKey(key)) {
				return null;
			}

			UserEdit basketEdit = basket.getEdit(key.getKey(true, true));
			if(null != basketEdit) {
				value = basketEdit.getValue();
			}
		}
		return value;
	}

	private DataKey setRunTagInKey(DataKey key) {
		if (null == getRunTag() ||
			RunPlaceHolder.RUN_PLACE_HOLDER == getRunTag().getRun() ) {
			// no runTag
			key.setRunId(null);
			key.setTagId(null);
		}
		else {
			// on a run ...
			key.setRunId("" + getRunTag().getRun().getId());
			if (runTag.getRunModelTag().equals(RunModelTag.LATEST)) {
				key.setTagId("0");
			}
//			if (null != getRunTag().getTag()) {
//				if (0 == getRunTag().getTag().getId()) {
//					// Its the latest tag
//					key.setTagId("0");
//				}
//				else {
//					// The data is tagged, but there can't be any tagged data in the basket.
//					return null;
//				}
//			}
			else {
				key.setTagId(null);
			}
			key.setRunTag(true);
		}
		return key;
	}

	private String getValueFromDataBase(Company company, Basket basket, GroupEntry groupEntry) {
		String value = null;
		ItemProperties itemProperties = model.getItemPropertiesForItem(item.getId());
		if(itemProperties.isRawDataValue(interval)){
			Data data = dataService.getDataForPot(this, company, groupEntry, null == basket);
			if(null != data){
				value = data.getValue();
			}
		}
		return value;
	}


	private String getValueFromEquation(Company company, TransientDataCache transientDataCache, Basket basket,	GroupEntry groupEntry, boolean processingReport) {
		// data is calculated - do the equation and create a local data to return it in
		ItemProperties itemProperties = model.getItemPropertiesForItem(item.getId());
		return getValueFromEquation(company, transientDataCache, basket, groupEntry, itemProperties.getFormula(this.interval), processingReport);
	}
	
	private String getValueFromEquation(Company company, TransientDataCache transientDataCache, Basket basket,	GroupEntry groupEntry, String formula, boolean processingReport) {
		// If already cached, then use this value
		if (transientDataCache!=null && transientDataCache.hasValue(model, company, item, interval, groupEntry, runTag.getRun(), runTag.getRunModelTag().getRunModelCompanyTag(company.getId()))) {
			return transientDataCache.getValue(model, company, item, interval, groupEntry, runTag.getRun(), runTag.getRunModelTag().getRunModelCompanyTag(company.getId()));
		}

		// data is calculated - do the equation and create a local data to return it in
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) getFormulaParser().parseFormula(formula);
		
		FormulaContext formulaContext = new FormulaContext(this, model, company, groupEntry, referenceService, groupService, processingReport); 
		formulaContext.setBasket(basket);
		formulaContext.setTransientDataCache(transientDataCache);
		String value = null;
		try{
			value =  evaluatableFormula.evaluate(formulaContext);
		}
		catch(NumberFormatException nfe){
			value = "formula error";
		}

		// Cache the value
		if (transientDataCache!=null) {
			transientDataCache.setValue(model, company, item, interval, groupEntry, runTag.getRun(), runTag.getRunModelTag().getRunModelCompanyTag(company.getId()), value);
		}

		return value;
	}

	private String getConfidenceGradeFromBasket(Company company, Basket basket, GroupEntry groupEntry) {
		String value = null;
		if(null != basket) {
			// see if the value is in the basket
			DataKey key = new DataKey(item, interval, company, groupEntry, EditType.CONFIDENCE_GRADE);
			// TODO - must work with run & tag
			if (null == getBranch(company.getId())) {
				key.setBranchId(0);
			} else {
				key.setBranchId(getBranch(company.getId()).getId());
			}
			
			if (null == setRunTagInKey(key)) {
				return null;
			}

			UserEdit basketEdit = basket.getEdit(key.getKey(true, true));
			if(null != basketEdit) {
				value = basketEdit.getValue();
			}
		}
		return value;
	}

	private String getConfidenceGradeFromDataBase(Company company, Basket basket, GroupEntry groupEntry) {
		String value = null;
		Data data = dataService.getDataForPot(this, company, groupEntry, null == basket);
		if (null != data &&
		    null != data.getConfidenceGrade()) {
			value = data.getConfidenceGrade().getCode();
		}
		return value;
	}

	public void setRunTag(RunTag runTag) {
		this.runTag = runTag; 
	}

	public RunTag getRunTag() {
		return runTag;
	}

	public BranchDao getBranchDao() {
		return branchDao;
	}
	
}
