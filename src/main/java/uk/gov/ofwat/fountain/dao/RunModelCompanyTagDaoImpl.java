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

public class RunModelCompanyTagDaoImpl extends SimpleJdbcDaoSupport implements RunModelCompanyTagDao {

	private static final String TABLE_NAME = "tbl_run_model_company_tag";

	
	private final RowMapper<RunModelCompanyTag> ROW_MAPPER = new RowMapper<RunModelCompanyTag>() {
	    public RunModelCompanyTag mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	RunModelCompanyTag runModelCompanyTag = new RunModelCompanyTag();
	    	runModelCompanyTag.setId(rs.getInt("id"));
	    	runModelCompanyTag.setRunModelTagId(rs.getInt("runModelTagId"));
	    	runModelCompanyTag.setCompanyId(rs.getInt("companyId"));
	    	runModelCompanyTag.setDateCreated(rs.getTimestamp("dateCreated"));
	    	runModelCompanyTag.setCreatedBy(rs.getString("createdBy"));
	        return runModelCompanyTag;
	    }
	};

	@Override
	public RunModelCompanyTag findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	@Override
	public List<RunModelCompanyTag> findByRunModelTagId(int runModelTagId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE runModelTagId = ?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, runModelTagId);
	}

	@Override
	public RunModelCompanyTag findTagByRunModelTagAndCompany(int runModelTagId, int companyId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE runModelTagId=? and companyId=?";
		try {
			RunModelCompanyTag runModelCompanyTag = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, runModelTagId, companyId);
			return runModelCompanyTag;
		} catch (EmptyResultDataAccessException e) {
			return RunModelCompanyTag.PLACE_HOLDER;
		}
	}

	@Override
	public RunModelCompanyTag create(RunModelCompanyTag runModelCompanyTag) {
		
        String sql = "INSERT INTO " + TABLE_NAME + " (runModelTagId, companyId, dateCreated, createdBy) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runModelTagId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // dateCreated
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
        
        Date now = new Date();
        runModelCompanyTag.setDateCreated(now);
        Object[] parameters = new Object[] {runModelCompanyTag.getRunModelTagId(), runModelCompanyTag.getCompanyId(), new Timestamp(now.getTime()), "System"}; 
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        runModelCompanyTag.setId(keyHolder.getKey().intValue());
        
        return runModelCompanyTag;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		getSimpleJdbcTemplate().update(sql, id);
	}

}
