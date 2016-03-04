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

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.InflationType;

public class InflationTypeDaoImpl extends SimpleJdbcDaoSupport implements
		InflationTypeDao {
	private static final String TABLE_NAME = "tbl_inflationtype";
	
	private final RowMapper<InflationType> ROW_MAPPER = new RowMapper<InflationType>() {
	    public InflationType mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	InflationType it = new InflationType();
	    	it.setId(rs.getInt("id"));  
	    	it.setCode(rs.getString("code"));
	    	it.setDescription(rs.getString("description"));
	    	return it;
	    	}
	};	
	
	public InflationType findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public int create(InflationType inflationType) {
		String sql = "INSERT INTO " + TABLE_NAME + " (id, code, description) VALUES (?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // id 
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // Description
		
		Object[] parameters = new Object[] {inflationType.getId(), 
				inflationType.getCode(), 
				inflationType.getDescription()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		inflationType.setId(keyHolder.getKey().intValue());
		return inflationType.getId();
	}

	public InflationType findByCode(String code) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
		try {
			return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
		}
		catch (EmptyResultDataAccessException ex1) {
			// Its valid to find no inflation type.
			return null;
		}
	}

}
