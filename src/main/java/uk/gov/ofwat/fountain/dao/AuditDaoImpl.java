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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.User;

public class AuditDaoImpl extends SimpleJdbcDaoSupport implements AuditDao {

	private static final String TABLE_NAME = "tbl_audit";
	private static final String USER_TABLE_NAME = "tbl_user";
	private static final String COMPANY_TABLE_NAME = "tbl_company";
	
	CompanyDao companyDao; 
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	private final RowMapper<Audit> ROW_MAPPER = new RowMapper<Audit>() {
	    public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Audit audit = new Audit();
	    	audit.setComment(rs.getString("comment"));
	    	audit.setDate(rs.getTimestamp("timestamp"));
	    	User user = new User();
	    	user.setId(rs.getInt("userId"));
	    	user.setName(rs.getString("userName"));
	    	audit.setUser(user);
	    	audit.setId(rs.getInt("Id"));
	    	Company company = companyDao.findById(rs.getInt("companyId"));
	    	audit.setCompany(company);
	        return audit;
	    }
	};
	
	private final RowMapper<AuditedValue> VALUE_ROW_MAPPER = new RowMapper<AuditedValue>() {
	    public AuditedValue mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditedValue av = new AuditedValue();
	    	av.setComment(rs.getString("comment"));
	    	av.setDate(rs.getTimestamp("timestamp"));
	    	User user = new User();
	    	av.setId(rs.getInt("userId"));
	    	user.setName(rs.getString("userName"));
	    	av.setUser(user);
	    	av.setId(rs.getInt("Id"));
	    	
	    	av.setValue(rs.getString("value"));
	    	av.setCg(rs.getString("cg"));
	        return av;
	    }
	};	

	private final RowMapper<AuditedValue> VALUE_ROW_MAPPER_WITH_RUN = new RowMapper<AuditedValue>() {
	    public AuditedValue mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditedValue av = new AuditedValue();
	    	av.setComment(rs.getString("comment"));
	    	av.setDate(rs.getTimestamp("timestamp"));
	    	User user = new User();
	    	av.setId(rs.getInt("userId"));
	    	user.setName(rs.getString("userName"));
	    	av.setUser(user);
	    	av.setId(rs.getInt("Id"));
	    	
	    	av.setValue(rs.getString("value"));
	    	av.setCg(rs.getString("cg"));
	    	av.setRunName(rs.getString("runName"));
	        return av;
	    }
	};	

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.dao.AuditDao#create(uk.gov.ofwat.fountain.domain.Audit)
	 */
	public int create(Audit audit) {
		String sql = null;
		if(null != audit.getDate()){
			// insert the timestamp
			sql = "INSERT INTO " + TABLE_NAME + "(comment, userId, companyId, timestamp) VALUES (?, ?, ?, ? )";
		}
		else{
			sql = "INSERT INTO " + TABLE_NAME + "(comment, userId, companyId) VALUES (?, ?, ? )";
		}
		SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // comment
        su.declareParameter(new SqlParameter(Types.INTEGER)); // user id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // company id
        Object[] parameters = null;
        if(null != audit.getDate()){
        	su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // timestamp
/*
 * STOP - READ THIS!
 * (Taken from http://dev.mysql.com/doc/refman/5.7/en/date-and-time-literals.html)
 * Values specified as numbers should be 6, 8, 12, or 14 digits long. If a number is 8 or 14 digits long, it is assumed to 
 * be in YYYYMMDD or YYYYMMDDHHMMSS format and that the year is given by the first 4 digits. If the number is 6 or 12 digits 
 * long, it is assumed to be in YYMMDD or YYMMDDHHMMSS format and that the year is given by the first 2 digits. Numbers that 
 * are not one of these lengths are interpreted as though padded with leading zeros to the closest length.         	
 */
        	parameters = new Object[] {audit.getComment(), audit.getUser().getId(), audit.getCompany().getId(), new Timestamp(audit.getDate().getTime())};
        }
        else{
        	parameters = new Object[] {audit.getComment(), audit.getUser().getId(), audit.getCompany().getId()};
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        audit.setId(keyHolder.getKey().intValue());
        return audit.getId();
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.dao.AuditDao#findByID(int)
	 */
	public Audit findById(int id) {
		String sql = "SELECT a.*, u.id as userid, u.name as userName FROM " + TABLE_NAME + " a INNER JOIN "+ USER_TABLE_NAME + " u ON a.userId = u.id  WHERE a.id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.dao.AuditDao#findByUser(java.lang.String)
	 */
	public List<Audit> findByUser(User user) {
		String sql = "SELECT a.*, u.id as userid, u.name as userName  FROM " + TABLE_NAME + " a INNER JOIN " + USER_TABLE_NAME + " u ON a.userId = u.id  " + "WHERE u.id = ?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, user.getId());
	}

	public List<AuditedValue> getAuditedValues(int itemId, int intervalId, int companyId, int groupEntryId) {
		// TODO does this need to change now that an audit only contains one company
		String sql = "SELECT DISTINCT A.*, U.name AS userName, V.value, CG.Code AS cg " +
					 "FROM tbl_data AS D " + 
					 	"INNER JOIN tbl_value AS V ON V.dataId=D.ID " +
						"LEFT OUTER JOIN tbl_confidence_grade AS CG ON V.confidenceGradeID=CG.id " + 
					 		"INNER JOIN tbl_audit AS A ON V.auditId=A.id " +
					 			"INNER JOIN tbl_user AS U ON A.userId=U.ID " +
					 			
					 "WHERE D.itemId=? AND D.intervalId=? AND D.companyId=? AND D.groupEntryId=? ORDER BY A.timestamp DESC;";
		return getSimpleJdbcTemplate().query(sql, VALUE_ROW_MAPPER, itemId, intervalId, companyId, groupEntryId);		
	}

	public List<AuditedValue> getAuditedValues(int itemId, int intervalId, int companyId, int groupEntryId, int runId, int tagId) {
		String sql = "SELECT DISTINCT A.*, " +  
					"  U.name AS userName,  " + 
					"  V.value,  " + 
					"  CG.Code AS cg, " +  
					"  R.name AS runName  " + 
					"FROM tbl_data AS D  " + 
					"INNER JOIN tbl_value AS V ON V.dataId=D.ID " +  
					"LEFT OUTER JOIN tbl_confidence_grade AS CG ON V.confidenceGradeID=CG.id " +  
					"INNER JOIN tbl_audit AS A ON V.auditId=A.id  " + 
					"INNER JOIN tbl_user AS U ON A.userId=U.ID  " + 
					"LEFT OUTER JOIN tbl_branch_tag AS BT ON V.branchTagId=BT.id " + 
					"LEFT OUTER JOIN tbl_run AS R ON BT.`runId` = R.id  " + 
					"WHERE D.itemId=?  " + 
					"AND D.intervalId=?  " + 
					"AND D.companyId=?  " + 
					"AND D.groupEntryId=? " +  
					"AND R.id=?  " + 
					((0 == tagId) ? "" : "AND auditId <= (select id from tbl_audit where tbl_audit.timestamp <= (select dateCreated from tbl_run_model_company_tag where id = " + tagId + ") order by id desc limit 1)") +
					"ORDER BY A.timestamp DESC; ";
		return getSimpleJdbcTemplate().query(sql, VALUE_ROW_MAPPER_WITH_RUN, itemId, intervalId, companyId, groupEntryId, runId);		
	}

	public void delete(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
		getSimpleJdbcTemplate().update(sql, id);
		
	}

	public List<Audit> findByUserCompanyAndTimestamp(User user, Company company, long timestamp) {
		String sql = "SELECT a.*, u.id as userid, u.name as userName  FROM " + TABLE_NAME + " a INNER JOIN " + USER_TABLE_NAME + " u ON a.userId = u.id  INNER JOIN " + COMPANY_TABLE_NAME + " c ON a.companyId = c.id " + "WHERE u.id = ? and c.id = ? and a.timestamp = ?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, user.getId(), company.getId(), new SqlParameterValue(Types.TIMESTAMP, new Timestamp(timestamp)));
	}

}
