package uk.gov.ofwat.fountain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;

public class RunTemplateDaoImpl extends SimpleJdbcDaoSupport  implements RunTemplateDao {

	private ModelDao modelDao;
	
	public ModelDao getModelDao(){
		return modelDao;
	}
	
	public void setModelDao(ModelDao modelDao){
		this.modelDao = modelDao;
	}
	
	public static final String RUN_TEMPLATE_TABLE_NAME = "tbl_run_template";
	public static final String RUN_TEMPLATE_MODEL_JOIN_TABLE_NAME = "tbl_run_template_model";
	
	private final RowMapper<RunTemplate> RUN_TEMPLATE_ROW_MAPPER = new RowMapper<RunTemplate>() {
	    public RunTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int id = rs.getInt("id");
	    	String name = rs.getString("name");
	    	String description = rs.getString("description");
	    	Date dateCreated = rs.getDate("created");
	    	String createdBy = rs.getString("createdBy");
	    	RunTemplate runTemplate = new RunTemplate();
	    	runTemplate.setId(id);
	    	runTemplate.setDescription(description);
	    	runTemplate.setName(name);
	    	runTemplate.setCreatedBy(createdBy);
	    	runTemplate.setCreated(dateCreated);
	    	//Get the models that are used in this run.
	    	ArrayList<Integer> modelIds = getModelIdsForRunTemplate(id);	    	
	    	runTemplate.setModelIds(modelIds);
	    	return runTemplate;
	    }
	};		
	
	@Override
	public List<RunTemplate> getAll() {
		String sql = "SELECT id, description, name, created, createdBy FROM " + RUN_TEMPLATE_TABLE_NAME + " where deleted = 0";
        List<RunTemplate> runTemplates = getSimpleJdbcTemplate().query(sql, RUN_TEMPLATE_ROW_MAPPER);
        return runTemplates;		
	}	
	
	@Override
	public RunTemplate create(RunTemplate runTemplate) {
	        String sql = "INSERT INTO " + RUN_TEMPLATE_TABLE_NAME + " (name, description, created, createdBy) VALUES (?, ?, ?, ?)";
	        SqlUpdate su = new SqlUpdate();
	        su.setDataSource(getDataSource());
	        su.setSql(sql);
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
	        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
	        Timestamp now = new Timestamp((new Date()).getTime());
	        Object[] parameters = new Object[] {runTemplate.getName(), runTemplate.getDescription(), now, runTemplate.getCreatedBy()};
	        KeyHolder keyHolder = new GeneratedKeyHolder();
			su.setReturnGeneratedKeys(true);
	        su.update(parameters, keyHolder);
	        runTemplate.setId(keyHolder.getKey().intValue());
	        runTemplate.setCreated(now);
	        return runTemplate;
	}

	@Override
	public boolean delete(int runTemplateId) {
		String sql = "UPDATE " + RUN_TEMPLATE_TABLE_NAME + " SET DELETED = 1 WHERE id = ?";
		int result = getSimpleJdbcTemplate().update(sql, runTemplateId);
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void addModel(int templateId, int modelId, int runOrder) {
        String sql = "INSERT INTO " + RUN_TEMPLATE_MODEL_JOIN_TABLE_NAME + " (runTemplateId, modelId, runOrder) VALUES (?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER));
        su.declareParameter(new SqlParameter(Types.INTEGER));
        su.declareParameter(new SqlParameter(Types.INTEGER));
        Object[] parameters = new Object[] {templateId, modelId, runOrder};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
	}
	
	@Override
	public void removeAllModels(int runTemplateId){
		String sql = "DELETE FROM " + RUN_TEMPLATE_MODEL_JOIN_TABLE_NAME + " WHERE runTemplateId = ?";
		getSimpleJdbcTemplate().update(sql, runTemplateId);		
	}

	@Override
	public void removeModel(int templateId, int modelId) {
		// assuming the database will clear up the dependent tables
		String sql = "DELETE FROM " + RUN_TEMPLATE_MODEL_JOIN_TABLE_NAME + " WHERE runTemplateId = ? and modelId = ?";
		getSimpleJdbcTemplate().update(sql, templateId, modelId);
	}

	@Override
	public RunTemplate findById(int id) {
		String sql = "SELECT id, description, name, created, createdBy FROM " + RUN_TEMPLATE_TABLE_NAME +  " WHERE id=? and deleted = 0";
    	try {
			return getSimpleJdbcTemplate().queryForObject(sql, RUN_TEMPLATE_ROW_MAPPER, id);
		} catch (DataAccessException e) {
			// Its OK to find no RunTemplate.
			return null;
		}
	}
	
	private ArrayList<Integer> getModelIdsForRunTemplate(int id){
		ArrayList<Integer> ids = (ArrayList<Integer>)modelDao.getModelsForRunTemplate(id);
		return ids;		
	}
}
