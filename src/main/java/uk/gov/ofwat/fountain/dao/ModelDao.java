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
package uk.gov.ofwat.fountain.dao;

import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;

public interface ModelDao extends CachableCodedDao, CachableEntityDao {
	
	public int create(Model model);
	
	public int create(int id, Model model);
	
	public void update(Model model);
	
	public void delete(int id);
	
	public Model findById(int id);
	
	public Model findByCode(String code);
	
	public List<Model> getAllModels();
	
	public List<Model> getAllModels(int modelTypeId);
	
	public List<ModelType>getAllModelTypes();
	
	public ModelType findModelTypeByName(String name);
	
	public ModelType getModelType(int typeId);
	
	/**
	 * Get the model that is the parent for the family
	 */
	public Model getFamilyParentModel(int familyId);
	
	public void invalidateCache();

	public String getInputModelCode(String inputCode, int modelId);
	
	public Map<String, ModelInput>getModelInputs(int parentModelId);

	public int createInput(ModelInput modelInput);
	
    public int createModelCompanyReport(String finfout, int modelId, int companyId, int reportId);
    public void updateModelCompanyReport(String finfout, int modelId, int companyId, int reportId);
    public ModelCompanyReport findModelCompanyReport(String finfout, int modelId, int companyId);
	public ModelCompanyReport findModelCompanyReport(int reportId);

	public void deleteModelCompanyReport(String finfout, int modelId, int companyId);
	
	/* Dependencies for a model. */
	public List<Model> getModelRunDependencies(int modelId);
	public void addModelDependency(int modelId, int dependencyId);
	public void removeModelDependency(int modelId, int dependencyId);

	/* Run templates*/
	public List<Integer> getModelsForRunTemplate(int runTemplateId);

	public Model getModelByName(String name);
}
