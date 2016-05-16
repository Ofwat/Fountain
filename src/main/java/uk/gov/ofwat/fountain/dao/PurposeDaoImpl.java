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

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Purpose;

public class PurposeDaoImpl extends JdbcDaoSupport implements PurposeDao {
	private static final String TABLE_NAME = "tbl_purpose";
	
	private final RowMapper<Purpose> ROW_MAPPER = new RowMapper<Purpose>() {
	    public Purpose mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Purpose p = new Purpose();
	    	p.setId(rs.getInt("id"));  
	    	p.setCode(rs.getString("code"));
	    	p.setDescription(rs.getString("description"));
	    	return p;
	    	}
	};	
	
	public Purpose findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public int create(Purpose purpose) {
		String sql = "INSERT INTO " + TABLE_NAME + " (id, code, description) VALUES (?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // id 
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // Description
		
		Object[] parameters = new Object[] {purpose.getId(), 
                purpose.getCode(), 
                purpose.getDescription()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		purpose.setId(keyHolder.getKey().intValue());
		return purpose.getId();
	}

	public Purpose findByCode(String code) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
	}
	
}
