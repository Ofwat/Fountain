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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;

public class UserEditDaoImpl extends JdbcDaoSupport  implements UserEditDao {
	
	protected static Log log = LogFactory.getLog(UserEditDaoImpl.class);
	private static final String TABLE_NAME = "tbl_user_edit";
	
	private final RowMapper<UserEdit> ROW_MAPPER = new RowMapper<UserEdit>() {
	    public UserEdit mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	String userName = rs.getString("user");
	    	int itemId = rs.getInt("itemId");
	    	int intervalId = rs.getInt("intervalId");
	    	int companyId = rs.getInt("companyId");
	    	int groupEntryId = rs.getInt("groupEntryId");
	        String value = rs.getString("value");
	        String original = rs.getString("original");
	        EditType editType = Enum.valueOf(EditType.class, rs.getString("editType"));
	        int branchId = rs.getInt("branchTagId");
	        long runId = rs.getLong("runId");
	        User user = new User();
	        user.setName(userName);
	    	
	    	return new UserEdit(user, itemId, intervalId, companyId, groupEntryId, branchId, value, original, editType, runId);
	    }
	};	

	public int create(UserEdit userEdit) {
		  String sql = "INSERT INTO " + TABLE_NAME + " (ItemId, IntervalId, CompanyId, groupEntryId, user, value, original, editType, branchTagId, runId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        SqlUpdate su = new SqlUpdate();
	        su.setDataSource(getDataSource());
	        su.setSql(sql);
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // itemId
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // intervalId
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // CompanyId
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // GroupEntryId
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // User
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Value
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Original
	        su.declareParameter(new SqlParameter(Types.VARCHAR)); // editType
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // branchTagId
	        su.declareParameter(new SqlParameter(Types.BIGINT)); // runId
	        Object[] parameters = new Object[] {userEdit.getItemId(), 
	        									userEdit.getIntervalId(),
	        									userEdit.getCompanyId(),
	        									userEdit.getGroupEntryId(),
	        									userEdit.getUser().getName(),
	        									userEdit.getValue(),
	        									userEdit.getOriginal(),
	        									userEdit.getEditType().toString(),
	        									userEdit.getBranchId(),
	        									userEdit.getRunId()};
	        KeyHolder keyHolder = new GeneratedKeyHolder();
			su.setReturnGeneratedKeys(true);
	        su.update(parameters, keyHolder);
	        userEdit.setId(keyHolder.getKey().intValue());
	        
	        return userEdit.getId();
	}
	
	public UserEdit findByUserEditId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Id=?";
		UserEdit userEdit = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
    	return userEdit;
	}

	public List<UserEdit> findByItemIntervalCompany(int itemId, int intervalId,
			int companyId) {
		String sql = "select * from " + TABLE_NAME + " WHERE itemId = ? AND intervalId = ? AND companyId = ?";
		List<UserEdit> edits = getJdbcTemplate().query(sql, ROW_MAPPER, itemId, intervalId, companyId);
		return edits;
	}

	public List<UserEdit> findByUser(User user){
		String sql = "select * from " + TABLE_NAME + " WHERE user = ?";
		List<UserEdit> edits = getJdbcTemplate().query(sql, ROW_MAPPER, user.getName());
		return edits;
	}

	public List<UserEdit> findByBranchId(int branchId){
		String sql = "select * from " + TABLE_NAME + " WHERE branchTagId = ?";
		List<UserEdit> edits = getJdbcTemplate().query(sql, ROW_MAPPER, branchId);
		return edits;
	}

	public void update(UserEdit userEdit) {
		String sql = "UPDATE " + TABLE_NAME + " SET " + 
        "itemId=?, " +
        "intervalId=?, " + 
        "companyId=?, " + 
        "groupEntryId=?, " + 
        "user=?, " + 
        "value=?, " + 
        "original = ?, " +
        "editType = ?, " +
        "modeId = ?, " +
        "runId = ?, " +
        "WHERE ID=?";
		getJdbcTemplate().update(sql,
				                        userEdit.getItemId(), 
				                        userEdit.getIntervalId(), 
				                        userEdit.getCompanyId(),
				                        userEdit.getGroupEntryId(),
				                        userEdit.getUser().getName(),
				                        userEdit.getValue(),
				                        userEdit.getOriginal(),
				                        userEdit.getEditType().toString(),
				                        userEdit.getBranchId(),
				                        userEdit.getRunId());
	}

	public void saveEdits(final List<UserEdit> edits, final User user) {
		// wipe out all edits for this user then save the new ones.
		
		// the original value cannot be changed so this is not sent to the database
		String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE user = ?";
		String sqlInsert = "INSERT INTO " + TABLE_NAME + " (ItemId, IntervalId, CompanyId, GroupEntryId, user, value, original, editType, branchTagId, runId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sqlDelete, user.getName());
		
		getJdbcTemplate().batchUpdate( sqlInsert,
			new BatchPreparedStatementSetter() {
				
				public int getBatchSize() {
					return edits.size();
				}

				public void setValues(PreparedStatement ps, int i) throws SQLException 
				{		
					UserEdit edit  = edits.get(i);
																	
					ps.setInt(1, edit.getItemId());
					ps.setInt(2, edit.getIntervalId());
					ps.setInt(3, edit.getCompanyId());				
					ps.setInt(4, edit.getGroupEntryId());				
					ps.setString(5, user.getName());
					ps.setString(6, edit.getValue());
					if (null == edit.getOriginal()) {
						ps.setString(7, "");
//						log.warn("Item Id: " + edit.getItemId() + " Interval Id: "  + edit.getIntervalId() + " BranchTag Id: " + edit.getBranchTagId() + ". Has an original value of null.");
					}
					else {
						ps.setString(7, edit.getOriginal());
					}
					ps.setString(8, edit.getEditType().toString());
					ps.setInt(9, edit.getBranchId());
					ps.setLong(10, edit.getRunId());
				}
			}
		);
	}

	public void refreshEditsForUser(User user) {
		String sql = "update " + TABLE_NAME + " set timestamp=CURRENT_TIMESTAMP WHERE user = ?";
		getJdbcTemplate().update(sql, user.getName());
		
	}

	public void removeUserEdits(User user) {
		String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE user = ?";
		getJdbcTemplate().update(sqlDelete, user.getName());
	}
	
	
	
}
