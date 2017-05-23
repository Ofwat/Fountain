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

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.RunModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunModelDaoImpl extends JdbcDaoSupport implements RunModelDao {
	
	private RunDao runDao;
	private UserService userService;
	private static final String RUN_MODEL_TABLE_NAME = "tbl_runmodel";

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRunDao(RunDao runDao) {
		this.runDao = runDao;
	}

	private final RowMapper<RunModel> ROW_MAPPER = new RowMapper<RunModel>() {
	    public RunModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	int id = rs.getInt("id");
	    	int runId = rs.getInt("runId");
	    	int modelId = rs.getInt("modelId");
	    	int companyId = rs.getInt("companyId");
	    	boolean completed = rs.getBoolean("completed");
	    	Date lastModified = rs.getTimestamp("lastModified");
	    	String lastModifiedBy = rs.getString("lastModifiedBy");
	    	int runOrder = rs.getInt("runOrder");
	    	RunModel rm = new RunModel(id, completed);
	    	rm.setId(id);
	    	rm.setModelId(modelId);
	    	rm.setCompanyId(companyId);
	    	if(completed){
	    		rm.setCompletedDate(lastModified);
	    		rm.setCompletedBy(lastModifiedBy);
	    	}
	    	rm.setRunId(runId);
	    	rm.setCompleted(completed);
	    	rm.setRunOrder(runOrder);
	        return rm;
	    }
	};


	@Override
	public synchronized int create(RunModel runModel) {
		
        String sql = "INSERT INTO " + RUN_MODEL_TABLE_NAME + " (runId, modelId, companyId, completed, created, createdBy, lastModified, lastModifiedBy, runOrder)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // completed
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // lastModified
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // lastModifiedBy
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runOrder
        
        Date now = new Date();
        Object[] parameters = new Object[] {runModel.getRun().getId(), runModel.getModelId(), runModel.getCompanyId(), runModel.isCompleted(), new Timestamp(now.getTime()), runModel.getLastModifiedBy(), new Timestamp(now.getTime()), runModel.getLastModifiedBy(), runModel.getRunOrder()}; //TODO need a real user 
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        runModel.setId(keyHolder.getKey().intValue());
        
        return runModel.getId();
	}

	@Override
	public synchronized void update(RunModel runModel) {
        String sql = "UPDATE " + RUN_MODEL_TABLE_NAME + " SET completed=?, lastModified=?, lastModifiedBy=? WHERE id=?";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // completed
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // lastModified
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // lastModifiedBy
        su.declareParameter(new SqlParameter(Types.INTEGER)); // id
        
        Date now = new Date();
        Object[] parameters = new Object[] {runModel.isCompleted(), new Timestamp(now.getTime()), runModel.getLastModifiedBy(), runModel.getId()}; 
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
	}

	@Override
	public RunModel findById(int id) {
		String sql = "SELECT * FROM " + RUN_MODEL_TABLE_NAME +  " WHERE id=?";
        RunModel runModel = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
        return runModel;
	}

	@Override
	public RunModel findByRunAndModelAndCompany(int runId, int modelId, int companyId) {
		String sql = "SELECT * FROM " + RUN_MODEL_TABLE_NAME +  " WHERE runId=? and modelId=? and companyId=?";
		try {
			RunModel runModel = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, runId, modelId, companyId);
			return runModel;
		} catch (EmptyResultDataAccessException e) {
			// Its OK to have no RunModel. 
			return null;
		}
	}

	@Override
	public ArrayList<RunModel> findByRun(int runId) {
		String sql = "SELECT * FROM " + RUN_MODEL_TABLE_NAME +  " WHERE runId=? ORDER BY runOrder ASC";
		ArrayList<RunModel> runModels = (ArrayList<RunModel>) getJdbcTemplate().query(sql, ROW_MAPPER, runId);
        return runModels;
	}

	@Override
	public ArrayList<RunModel> findByModel(int modelId) {
		String sql = "SELECT * FROM " + RUN_MODEL_TABLE_NAME +  " WHERE runId=? and modelId=? ORDER BY runOrder ASC";
		ArrayList<RunModel> runModels = (ArrayList<RunModel>) getJdbcTemplate().query(sql, ROW_MAPPER, modelId);
        return runModels;
	}

	@Override
	public List<RunModel> findByRunAndCompany(int runId, int companyId) {
		String sql = "SELECT * FROM " + RUN_MODEL_TABLE_NAME +  " WHERE runId=? AND companyId=? ORDER BY runOrder ASC";
		ArrayList<RunModel> runModels = (ArrayList<RunModel>) getJdbcTemplate().query(sql, ROW_MAPPER, runId, companyId);
        return runModels;
	}

}
