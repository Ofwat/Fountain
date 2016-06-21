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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;

public class ModelDaoImpl extends JdbcDaoSupport  implements ModelDao {
	
	
	private BranchDao branchDao;	
	private ModelFamilyDao modelFamilyDao;

	
	private static final String MODEL_TABLE_NAME = "tbl_model";
	private final static String MODEL_INPUTS_TABLE_NAME = "tbl_modelinputs";
	private static final String MODEL_TYPE_TABLE_NAME = "tbl_modeltype";
	private static final String MODEL_COMPANY_REPORT_TABLE_NAME = "tbl_model_company_report";
	private static final String MODEL_RUN_DEPENDENCY_TABLE_NAME = "tbl_model_run_dependency";
	

	public BranchDao getBranchDao() {
		return branchDao;
	}

	public ModelFamilyDao getModelFamilyDao() {
		return modelFamilyDao;
	}

	public void setModelFamilyDao(ModelFamilyDao modelFamilyDao) {
		this.modelFamilyDao = modelFamilyDao;
	}


	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	private final RowMapper<Integer> MODELID_ROW_MAPPER = new RowMapper<Integer>() {
	    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        int id = rs.getInt("ID");
	        return id;
	    }
	};	
	
	private final RowMapper<Model> MODEL_ROW_MAPPER = new RowMapper<Model>() {
	    public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	String name = rs.getString("name");
	    	String code = rs.getString("code");
	    	boolean parent = rs.getBoolean("parent");
	    	int modelFamilyId = rs.getInt("familyId");
	    	ModelFamily family = null;
	    	if(0 != modelFamilyId){
	    		family = modelFamilyDao.findById(modelFamilyId);
	    	}
	    	String modelTypeName = rs.getString("modelTypeName");
	    	ModelType mt = ModelType.getByName(modelTypeName);
	        int branchId = rs.getInt("branchTagId");
	        int id = rs.getInt("id");
	        int displayOrder = rs.getInt("displayOrder");
	        int runId = rs.getInt("runId");
	        
	    	Model m = new Model(name, code, mt);
	    	m.setBranchId(branchId);
	    	m.setFamily(family);
	    	m.setId(id);
	        Map<String, ModelInput> modelInputs = getModelInputs(m.getId());
	        m.setModelInputs(modelInputs);
	        m.setParent(parent);
	        m.setDisplayOrder(displayOrder);
			m.setRunId(runId);
	        return m;
	    }
	};	
	
	private static final RowMapper<ModelType> MODEL_TYPE_ROW_MAPPER = new RowMapper<ModelType>() {
		public ModelType mapRow(ResultSet rs, int rowNum) throws SQLException{
			String name = rs.getString("name");
			return ModelType.getByName(name);
		}
	};

	private static final RowMapper<ModelInput> MODEL_INPUT_ROW_MAPPER = new RowMapper<ModelInput>() {
		public ModelInput mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			int parentId = rs.getInt("parentId");
			String code = rs.getString("code");
			String childCode = rs.getString("childModelCode");
			return new ModelInput(id, parentId, code, childCode);
		}
	};

	private static final RowMapper<ModelCompanyReport> MODEL_COMPANY_REPORT_ROW_MAPPER = new RowMapper<ModelCompanyReport>() {
		public ModelCompanyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ModelCompanyReport(rs.getString("finfout"), rs.getInt("modelId"), rs.getInt("companyId"), rs.getInt("reportId"));
		}
	};

	
    public int create(Model model) {
   		String sql = "INSERT INTO " + MODEL_TABLE_NAME + " (name, code, modelTypeId, familyId, branchTagId, displayOrder, parent, runId) VALUES (?,?,?,?,?,?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Code
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelTypeId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // familyId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // branchTagId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // dispalyOrder
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // parent
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        Branch branch = model.getBranch();
        Integer branchId = null;
        Integer familyId = null;
        if (null != branch) {
			branchId = branch.getId();
	    }
        if(null != model.getFamily()){
        	familyId = model.getFamily().getId();
        }
        Object[] parameters = new Object[] {model.getName(), model.getCode(), model.getModelType().getId(), familyId,  branchId, model.getDisplayOrder(), model.isParent(), model.getRunId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        model.setId(keyHolder.getKey().intValue());
        
        return model.getId();
	}

    public int create(int id, Model model) {
   		String sql = "INSERT INTO " + MODEL_TABLE_NAME + " (id, Name, Code, modelTypeId, familyId, branchTagId, displayOrder, parent, runId) VALUES (?,?,?,?,?,?,?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // Id
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Code
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelTypeId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // familyId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelTypeId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // displayOrder
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // parent
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        Branch branch = model.getBranch();
        Integer branchId = null;
        Integer familyId = null;
        if (null != branch) {
			branchId = branch.getId();
	    }
        if(null != model.getFamily()){
        	familyId = model.getFamily().getId();
        }
        Object[] parameters = new Object[] {id, model.getName(), model.getCode(), model.getModelType().getId(), familyId, branchId, model.getDisplayOrder(), model.isParent(), model.getRunId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        model.setId(keyHolder.getKey().intValue());
        
        return model.getId();
	}

	public Model findByCode(String code) {
        String sql = "SELECT m.*, t.id as modelTypeId, t.name as modelTypeName FROM " + MODEL_TABLE_NAME + " m inner join " + MODEL_TYPE_TABLE_NAME + " t on m.modelTypeId = t.id WHERE m.code=?";
        List<Model> models = getJdbcTemplate().query(sql, MODEL_ROW_MAPPER, code);
        if (models.size() == 0) {
        	return null;
        }
        else {
        	return models.get(models.size()-1);
        }
	}

	public Model findById(int id){
		String sql = "SELECT m.*, t.id as modelTypeId, t.name as modelTypeName FROM " + MODEL_TABLE_NAME + " m inner join " + MODEL_TYPE_TABLE_NAME + " t on m.modelTypeId = t.id WHERE m.id=?";
        Model model = getJdbcTemplate().queryForObject(sql, MODEL_ROW_MAPPER, id);
        return model;
	}

	public List<Model> getAllModels() {
		String sql = "SELECT " +
						"m.*, t.id AS modelTypeId, t.name AS modelTypeName " + 
					 "FROM " + MODEL_TABLE_NAME + " AS m " + 
					 	"INNER JOIN " + MODEL_TYPE_TABLE_NAME + " AS t on m.modelTypeId = t.id " +
					 "ORDER BY m.displayOrder";
		List<Model> models =  getJdbcTemplate().query(sql, MODEL_ROW_MAPPER);
        return models;
	}

	public List<Model> getAllModels(int modelTypeId) {
		String sql = "SELECT " +
						"m.*, t.id AS modelTypeId, t.name AS modelTypeName " + 
					 "FROM " + MODEL_TABLE_NAME + " AS m " + 
					 	"INNER JOIN " + MODEL_TYPE_TABLE_NAME + " AS t on m.modelTypeId = t.id and t.id=? " +
					 "ORDER BY m.displayOrder";
		List<Model> models =  getJdbcTemplate().query(sql, MODEL_ROW_MAPPER, modelTypeId);
        return models;
	}

	public void update(Model model) {
		 String sql = "UPDATE " + MODEL_TABLE_NAME + " SET " + 
         "Code=?, " +
         "Name=?, " + 
         "ModelTypeId=?, " + 
         "BranchTagId=?, " +
         "familyId=? " +
         "WHERE ID=?";
		 Branch branch = model.getBranch();
		 Integer branchId = null;
		 if (null != branch) {
			 branchId = branch.getId();
		 }
		 ModelFamily modelFamily = model.getFamily(); 
		 Integer familyId = null;
		 if (null != modelFamily) {
			 familyId = modelFamily.getId();
		 }
		 getJdbcTemplate().update(sql, model.getCode(), model.getName(), model.getModelType().getId(), branchId, familyId, model.getId());
	}

	public List<ModelType> getAllModelTypes() {
		String sql = "SELECT * FROM " + MODEL_TYPE_TABLE_NAME ;
		return getJdbcTemplate().query(sql, MODEL_TYPE_ROW_MAPPER);
	}

	public ModelType findModelTypeByName(String name) {
		String sql = "SELECT * FROM " + MODEL_TYPE_TABLE_NAME + " WHERE name = ?";
		return getJdbcTemplate().queryForObject(sql, MODEL_TYPE_ROW_MAPPER, name);
	}
	
	public ModelType getModelType(int typeId) {
		String sql = "SELECT * FROM " + MODEL_TYPE_TABLE_NAME + " WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, MODEL_TYPE_ROW_MAPPER, typeId);
	}

	public void invalidateCache() {
	}
	
	public void delete(int id){
		
		// assuming the database will clear up the dependent tables
		String sql = "DELETE FROM " + MODEL_TABLE_NAME + " WHERE id = ?";
		getJdbcTemplate().update(sql, id);
	}

	public String getInputModelCode(String inputCode, int modelId) {
		String sql = "SELECT childModelCode FROM " + MODEL_INPUTS_TABLE_NAME + " WHERE code = ? and parentId = ?";
		return getJdbcTemplate().queryForObject(sql, String.class, inputCode, modelId);
	}

	public Map<String, ModelInput> getModelInputs(int parentModelId) {
		String sql = "SELECT * FROM " + MODEL_INPUTS_TABLE_NAME + " WHERE parentId = ?";
		List<ModelInput> inputs = getJdbcTemplate().query(sql, MODEL_INPUT_ROW_MAPPER, parentModelId);
		HashMap<String, ModelInput> mi = new HashMap<String, ModelInput>();
		for (ModelInput modelInput : inputs) {
			mi.put(modelInput.getCode(), modelInput);
		}
		return mi;
	}

	public int createInput(ModelInput modelInput) {
	    String sql = "INSERT INTO " + MODEL_INPUTS_TABLE_NAME + " (parentId, Code, childModelCode) VALUES (?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // Parent Model Id
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Code
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // child model code
        Object[] parameters = new Object[] {modelInput.getParentId(), modelInput.getCode(), modelInput.getChildModelCode()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        modelInput.setId(keyHolder.getKey().intValue());
        
        return modelInput.getId();
	}

	public Model getFamilyParentModel(int familyId) {
		
		String sql = "SELECT  m.*, t.id as modelTypeId, t.name as modelTypeName FROM " + MODEL_TABLE_NAME + " m inner join " + MODEL_TYPE_TABLE_NAME + " t on m.modelTypeId = t.id WHERE m.parent = true and m.familyId = ?";
		Model model = null;
		try{
			model =  getJdbcTemplate().queryForObject(sql, MODEL_ROW_MAPPER, familyId);
		}
		catch(EmptyResultDataAccessException erdae){
			logger.debug("no parent model for family id " + familyId);
		}
		return model;
	}
    
    public int createModelCompanyReport(String finfout, int modelId, int companyId, int reportId) {
   		String sql = "INSERT INTO " + MODEL_COMPANY_REPORT_TABLE_NAME + " (finfout, modelId, companyId, reportId) VALUES (?,?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // finfout
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // reportId
        Object[] parameters = new Object[] {finfout, modelId, companyId, reportId};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }
    
    public void updateModelCompanyReport(String finfout, int modelId, int companyId, int reportId) {
   		String sql = "update " + MODEL_COMPANY_REPORT_TABLE_NAME + " SET " +
   		"reportId=? " +
   		"WHERE finfout=? " +
   		"AND modelId=? " +
   		"AND companyId=?";
		 getJdbcTemplate().update(sql, reportId, finfout, modelId, companyId);
    }

    public ModelCompanyReport findModelCompanyReport(String finfout, int modelId, int companyId) {
    	String sql = "SELECT * FROM " + MODEL_COMPANY_REPORT_TABLE_NAME + " WHERE finfout=? AND modelId=? AND companyId=?";
    	try {
			return getJdbcTemplate().queryForObject(sql, MODEL_COMPANY_REPORT_ROW_MAPPER, finfout, modelId, companyId);
		} catch (DataAccessException e) {
			// Its OK to find no ModelCommpanyReport.
			return null;
		}
    }

	public ModelCompanyReport findModelCompanyReport(int reportId) {
    	String sql = "SELECT * FROM " + MODEL_COMPANY_REPORT_TABLE_NAME + " WHERE reportId=? ";
    	try {
			return getJdbcTemplate().queryForObject(sql, MODEL_COMPANY_REPORT_ROW_MAPPER, reportId);
		} catch (DataAccessException e) {
			// Its OK to find no ModelCommpanyReport.
			return null;
		}
	}

	public void deleteModelCompanyReport(String finfout, int modelId, int companyId) {
    	String sql = "DELETE FROM " + MODEL_COMPANY_REPORT_TABLE_NAME + " WHERE finfout=? AND modelId=? AND companyId=?";
		getJdbcTemplate().update(sql, finfout, modelId, companyId);
	}

	public List<Model> getModelRunDependencies(int modelId) {
		String sql = "SELECT m.*, t.id AS modelTypeId, t.name AS modelTypeName" + 
				" FROM tbl_model AS m" +
				" INNER JOIN tbl_modeltype AS t on m.modelTypeId = t.id" + 
				" INNER JOIN " + MODEL_RUN_DEPENDENCY_TABLE_NAME + " AS d on m.id = d.dependencyId" +
				" where d.modelId = ?" + 
				" ORDER BY m.displayOrder;";
			List<Model> models =  getJdbcTemplate().query(sql, MODEL_ROW_MAPPER, modelId);
		return models;
	}

	public void addModelDependency(int modelId, int dependencyId) {
   		String sql = "INSERT INTO " + MODEL_RUN_DEPENDENCY_TABLE_NAME + " (modelId, dependencyId) VALUES (?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // dependencyId
        Object[] parameters = new Object[] {modelId, dependencyId};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
	}

	public void removeModelDependency(int modelId, int dependencyId) {
    	String sql = "DELETE FROM " + MODEL_RUN_DEPENDENCY_TABLE_NAME + " WHERE modelId=? AND dependencyId=?";
		getJdbcTemplate().update(sql, modelId, dependencyId);
	}

	/*
	 * Get the models that are associated with a particular Run Template.  
	 */
	public List<Integer> getModelsForRunTemplate(int runTemplateId){
		String sql = "SELECT DISTINCT " +
				"m.id " +
				"FROM tbl_model AS m " +
				"INNER JOIN " + RunTemplateDaoImpl.RUN_TEMPLATE_MODEL_JOIN_TABLE_NAME + " AS rtm on m.id = rtm.modelId " +
				"INNER JOIN " + RunTemplateDaoImpl.RUN_TEMPLATE_TABLE_NAME + " as rt on rtm.runTemplateId = ? " +
				"ORDER BY rtm.runOrder;";
		List<Integer> ids = getJdbcTemplate().query(sql, MODELID_ROW_MAPPER, runTemplateId);
		return ids;
	}

	@Override
	public Model getModelByName(String name) {
        String sql = "SELECT m.*, t.id as modelTypeId, t.name as modelTypeName FROM " + MODEL_TABLE_NAME + " m inner join " + MODEL_TYPE_TABLE_NAME + " t on m.modelTypeId = t.id WHERE m.name=?";
        try {
			return getJdbcTemplate().queryForObject(sql, MODEL_ROW_MAPPER, name);
		} catch (EmptyResultDataAccessException e) {
			// Its OK to find no ModelCommpanyReport.
			return null;
		}
	}

	
}
