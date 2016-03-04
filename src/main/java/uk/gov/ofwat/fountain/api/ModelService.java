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

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.Addressable;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.run.Run;

public interface ModelService {

	Model getModel(int id);

	Model getModel(String code);
	Model getModelByName(String code);
	
	List<Model> getModels();
	
	List<Model> getModels(int modelTypeId);
	
	List<ModelType> getModelTypes(List<ModelType> excludedTypes);
	
	List<ModelType> getModelTypes();
	
	List<ModelFamily> getAllModelFamilies();
	
	Table getTable(int id);

	/**
	 * Get the tables for this model and company - will use company type
	 * if there are no tables specified for this company. 
	 * @param modelId
	 * @param companyId
	 * @return List<Table>
	 */
	List<Table> getTablesForModelAndCompany(int modelId, int companyId);
	
	/**
	 * Get the tables for this model and company - will use company type
	 * if there are no tables specified for this company. 
	 * @param model code
	 * @param companyId
	 * @return List<Table>
	 */
	List<Table> getTablesForModelCodeAndCompany(String modelCode, int companyId);
	
	/**
	 * 
	 * @param companyId
	 * @param modelId
	 * @param tableId
	 * @return
	 */
	int addCompanyTable(int companyId, int modelId, int tableId);
	
	List<Table> getTablesForModel(int modelId);
	
	/**
	 * A far more performant method for getting the tables for the model
	 * if you just want the ids and names (e.g. for making links)
	 * @param modelId
	 * @return
	 */
	List<? extends Addressable> getTableAddressesForModelId(int modelId);
	
	List<Pot> getPotsForTable(int tableId);
	
	Pot createPot(Model model, String code,  Interval interval, int runId);
	
	/**
	 * create a new model (typically an ad-hoc report). Use this to add tables to
	 * @param runId 
	 * @param name, code and model type id
	 * @return - a wired model that you can update with tables etc
	 */
	Model createModel(String name, 
					  String code, 
					  String type,
					  String family,
					  String branch,
					  boolean parent,
					  int displayOrder, 
					  int runId);
	
	
	/**
	 * create a new model (typically an ad-hoc report). Use this to add tables to
	 * @param runId 
	 * @param name, code and model type id
	 * @return - a wired model that you can update with tables etc
	 */
	Model createModel(Integer id,
					  String name, 
					  String code, 
					  String type,
					  String family,
					  String branch,
					  boolean parent,
					  int displayOrder, 
					  int runId);
	
	
	/**
	 * add a table to an existing model
	 * @param modelId
	 * @param table
	 * @return table id of the new table
	 */
	int addTableToModel(int modelId, Table table);

	/**
	 * Take the internal code for the model input and return the real model code 
	 * for the parent model Id
	 * @param inputCode, parentId
	 * @return modelCode
	 */
	String getModelCodeForInput(String inputCode, int parentId);

	ModelInput createModelInput(ModelInput localInput);

	/**
	 * returns a list of Groups that are present in this table (i.e. as drop down, expanding or as specific group entries within a section)
	 * @param tableId
	 * @return the groups (may be empty)
	 */
	List<Group> getGroupsForTable(int tableId);
	
	/**
	 * Return the model that is the parent of the current model's model family.
	 * 
	 * If the model is not in a family, or it is a parent then this method will
	 * just return the same model that was passed in.
	 * @param model
	 * @return
	 */
	Model getFamilyParentModel(Model model);

	void updateModel(Model model);

	ModelFamily getModelFamily(String code);

	boolean assignExternalModelCompanyReports(Model model, int companyId, int fInputId, int fOutputId);
	boolean reportInUseByAnotherModel(int modelId, int reportId);

	ModelCompanyReport getExternalModel(String finFout, int modelId, int companyId);

	ModelCompanyReport getModelCompanyReport(int reportId);
	
	Model getFamilyParentModel(String familyCode);

	Branch getBranch(String branch);

	Branch getParentBranch(ModelFamily family);
		
	List<Model> getModelRunDependencies(int modelId);
	
	void addDependency(int modelId, int dependencyId);

	void removeDependency(int modelId, int dependencyId);
	
}
