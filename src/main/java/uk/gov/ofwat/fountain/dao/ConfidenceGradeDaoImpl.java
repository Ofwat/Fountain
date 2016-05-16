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
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import uk.gov.ofwat.fountain.domain.ConfidenceGrade;

public class ConfidenceGradeDaoImpl extends JdbcDaoSupport  implements ConfidenceGradeDao {
	
	private static final String TABLE_NAME = "tbl_confidence_grade";
	
	private final RowMapper<ConfidenceGrade> ROW_MAPPER = new RowMapper<ConfidenceGrade>() {
	    public ConfidenceGrade mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ConfidenceGrade g = new ConfidenceGrade();
	    	g.setId(rs.getInt("id"));
	    	g.setCode(rs.getString("code"));
	        g.setRedundant(rs.getBoolean("redundant"));
	        return g;
	    }
	};	
	

	public synchronized ConfidenceGrade findById(int id) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE id=?";
	     return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}
	
	public synchronized ConfidenceGrade findByCode(String code) {
		 String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE code=?";
		 ConfidenceGrade cg = null;
	     try{
	    	 cg = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
	     }
	     catch(DataAccessException dae){
//	    	 System.out.println("Can't find CG " + code);
	     }
	     return cg;
	}
	
	public synchronized List<ConfidenceGrade> getAll(){
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY `cg_order`;";
		return getJdbcTemplate().query(sql, ROW_MAPPER, new Object[]{});
	}
}
