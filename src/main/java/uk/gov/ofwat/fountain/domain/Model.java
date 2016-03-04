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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.audit.SkipAudit;

public class Model implements Addressable, Entity, Coded , Serializable {

	private int id;
	private String name;
	private String code;
	private int displayOrder = -1; // default value: do not display. 
	private ModelType modelType;
	private ModelFamily family;
	private boolean parent;
	private List<Table> tables;
	private Map<Integer, ItemProperties> itemProperties;
	private Map<String, Integer> propNameMap;
	private Map<String, ModelInput> modelInputs;	
	@SkipAudit private ModelService modelService;
	@SkipAudit private ItemService itemService;
	private int branchId;
	private String description;
	private ArrayList<Model> RunDependencies;
	private int runId;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Branch branch;
	
	public Model(){
	}

	public Model(String name, String code, ModelType modelType){
		this.name = name;
		this.code = code;
		this.modelType = modelType;
	}
	
	public Map<String, ModelInput> getModelInputs() {
		return modelInputs;
	}

	public void setModelInputs(Map<String, ModelInput> modelInputs) {
		this.modelInputs = modelInputs;
	}

	public void addModelInput(ModelInput modelInput){
		if (modelInputs == null){
			modelInputs = new HashMap<String, ModelInput>();
		}
		modelInputs.put(modelInput.getCode(), modelInput);
	}
	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	
	public void setItemService(ItemService itemService){
		this.itemService = itemService;
	}
	
	public ModelFamily getFamily() {
		return family;
	}

	public void setFamily(ModelFamily family) {
		this.family = family;
	}

	public Pot getPot(String code, Interval interval) {
		Pot pot = findPotFromAmongstTables(code, interval);
		
		if (null == pot) {
			pot = modelService.createPot(this, code, interval, this.getRunId());
		}
		return pot;
	}

	public Pot createPot(String code, Interval interval, Integer runId) {
		return modelService.createPot(this, code, interval, runId);
	}

	private Pot findPotFromAmongstTables(String code, Interval interval) {
		for (Table table : getTables()) {
			if (table.getPots().containsKey(code + interval.getName())) {
				return table.getPots().get(code + interval.getName());
			}
		}
		return null;
	}

	
	public Model getInputModel(String code){
		// return the direct input model if exists. 
		//If it doesn't exist as a direct input (child) then ask each child in turn
		// for their inputs. The first one to
		//Model inputModel = getInputModels().get(modelNameMap.get(code));

		ModelInput modelInput = modelInputs.get(code);
		if (modelInput!= null){
			return modelService.getModel(modelInput.getChildModelCode());
		} else return null;

	}

	public ItemProperties getItemPropertiesForItem(int itemId){
		if (null==itemProperties){
			itemProperties = new HashMap<Integer, ItemProperties>();
		}
		if(null == propNameMap){
			propNameMap = new HashMap<String, Integer>(); 
		}
		ItemProperties ip = itemProperties.get(itemId);
		if(null == ip){
			ip = itemService.getPropertiesForItemAndModel(itemId, this.id);
			if(null != ip){
				itemProperties.put(itemId, ip);
				propNameMap.put(ip.getItem().getCode(), ip.getId());
			}
		}
		
		return ip;
	}
		
	public ItemProperties getItemPropertiesForItem(String code) {
		// Start the caches if not already done so
		if (null==itemProperties) {
			itemProperties = new HashMap<Integer, ItemProperties>();
		}
		if(null == propNameMap){
			propNameMap = new HashMap<String, Integer>(); 
		}

		// Get the properties
		ItemProperties ip = null;
		if(null == propNameMap.get(code)){
			ip = itemService.getPropertiesForItemAndModel(code, this.id);
			if(null != ip){
				itemProperties.put(ip.getId(), ip);
				propNameMap.put(ip.getItem().getCode(), ip.getId());
			}	
		}
		else{
			ip = itemProperties.get(propNameMap.get(code));
			if(null == ip){
				ip = itemService.getPropertiesForItemAndModel(code, this.id);
				itemProperties.put(ip.getId(), ip);
				propNameMap.put(ip.getItem().getCode(), ip.getId());
			}
		}
		
		return ip;
	}
	public List<Table> getTables() {
		if (null == tables){
			setTables(modelService.getTablesForModel(id));
		}
		return tables;
	}
	
	public Table getTable(String name) {
		Table retTable = null;
		List<Table> tables = getTables();
		for (Table table: tables) {
			if (table.getName().equals(name)) {
				retTable = table;
				break;
			}
		}
		return retTable;
	}
	
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ModelType getModelType() {
		return modelType;
	}
	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}

	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
		if (null == branch) {
			this.branchId = 0;
		}
		else {
			this.branchId = branch.getId();
		}
	}

	public int getBranchId() {
		if (null == branch) {
			return branchId;
		}
		else {
			return branch.getId();
		}
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getModelCodeForInput(String inputCode) {
		// undo redirection
		return modelService.getModelCodeForInput(inputCode, this.id);
	}

	public ArrayList<Model> getRunDependencies() {
		return RunDependencies;
	}

	public void setRunDependencies(ArrayList<Model> runDependencies) {
		RunDependencies = runDependencies;
	}

	public int getRunId() {
		return runId;
	}
	
	public void setRunId(int runId) {
		this.runId = runId;
	}

}
