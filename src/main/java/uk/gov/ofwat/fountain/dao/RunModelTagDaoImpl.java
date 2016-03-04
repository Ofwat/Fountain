package uk.gov.ofwat.fountain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class RunModelTagDaoImpl extends SimpleJdbcDaoSupport implements RunModelTagDao {
	
	private static final String TABLE_NAME = "tbl_run_model_tag";

	private final RowMapper<RunModelTag> ROW_MAPPER = new RowMapper<RunModelTag>() {
	    public RunModelTag mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	RunModelTag runModelTag = new RunModelTag();
	    	runModelTag.setId(rs.getInt("id"));
	    	runModelTag.setRunId(rs.getInt("runId"));
	    	runModelTag.setModelId(rs.getInt("modelId"));
	    	runModelTag.setDisplayName(rs.getString("displayName"));
	    	runModelTag.setNote(rs.getString("note"));
	        return runModelTag;
	    }
	};
	
	@Override
	public RunModelTag findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	@Override
	public RunModelTag findTagByRunModel(int runId, int modelId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE runId=? and modelId=?";
		try {
			RunModelTag runModelTag = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, runId, modelId);
			return runModelTag;
		} catch (EmptyResultDataAccessException e) {
			return RunModelTag.PLACE_HOLDER;
		}
	}

	@Override
	public RunModelTag create(RunModelTag runModelTag) {
		
        String sql = "INSERT INTO " + TABLE_NAME + " (runId, modelId, displayName, note) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // displayName
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // note
        
        Object[] parameters = new Object[] {runModelTag.getRunId(), runModelTag.getModelId(), runModelTag.getDisplayName(), runModelTag.getNote()}; 
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        runModelTag.setId(keyHolder.getKey().intValue());
        
        return runModelTag;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		getSimpleJdbcTemplate().update(sql, id);
	}

	@Override
	public List<RunModelTag> findByRunId(int runId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE runId = ?";
		List<RunModelTag> runModelTags = getSimpleJdbcTemplate().query(sql, ROW_MAPPER, runId);
		return runModelTags;
	}

}
