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

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Branch;


public class BranchDaoImpl extends SimpleJdbcDaoSupport  implements BranchDao {
	
	
	private static final String TABLE_NAME = "tbl_branch_tag";
	
	private final RowMapper<Branch> ROW_MAPPER = new RowMapper<Branch>() {
	    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	
	        int id = rs.getInt("id");
	    	String tag = rs.getString("tag");
	    	boolean editable = rs.getBoolean("editable");
	    	int companyId = rs.getInt("companyId");
	    	int runId = rs.getInt("runId");
	    	
	    	Branch b = new Branch(id, tag, editable, companyId, runId);
	        return b;
	    }
	};	

	public synchronized Branch findById(int id) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE id=?";
		 Branch bt = null;
	     try{
	    	 bt = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	     }
	     catch(EmptyResultDataAccessException ex){
	    	 // Its OK to find no data. 
	     }
	     return bt;
	}
	
	public synchronized Branch findByCompanyAndRun(int companyId, int runId) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE companyId=? and runId=?";
		 Branch bt = null;
	     try{
	    	 bt = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, companyId, runId);
	     }
	     catch(EmptyResultDataAccessException ex){
	    	 // Its OK to find no data. 
	     }
	     return bt;
	}
	
	public synchronized Branch findByRun(int runId) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE runId=?";
		 Branch bt = null;
	     try{
	    	 bt = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, runId);
	     }
	     catch(EmptyResultDataAccessException ex){
	    	 // Its OK to find no data. 
	     }
	     return bt;
	}
	
	public synchronized Branch findByName(String name) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE tag=?";
		 Branch bt = null;
	     try{
	    	 bt = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, name);
	     }
	     catch(EmptyResultDataAccessException ex){
	    	 // Its OK to find no data. 
	     }
	     return bt;
	}
	
	public synchronized Branch create(String tag) {
        String sql = "INSERT INTO " + TABLE_NAME + " (tag, editable, companyId, runId) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // tag
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // editable
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        Object[] parameters = new Object[] {tag, true, 0, 0};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        Branch bt = new Branch(keyHolder.getKey().intValue(), tag, true);
        return bt;
	}

	public synchronized Branch create(String tag, int companyId, int runId) {
        String sql = "INSERT INTO " + TABLE_NAME + " (tag, editable, companyId, runId) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // tag
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // editable
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // runId
        Object[] parameters = new Object[] {tag, true, companyId, runId};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        Branch bt = new Branch(keyHolder.getKey().intValue(), tag, true, companyId, runId);
        return bt;
	}

	public void update(Branch branch) {
		 String sql = "UPDATE " + TABLE_NAME + " SET " + 
		         "tag=?, " +
		         "editable=?, " +
		         "companyId=?, " +
		         "runId=? " +
		         "where id=?"; 
		 getSimpleJdbcTemplate().update(sql, branch.getName(), branch.isEditable(), branch.getCompanyId(), branch.getRunId(), branch.getId());   
	}

}




