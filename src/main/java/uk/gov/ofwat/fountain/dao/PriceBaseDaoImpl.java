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
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.PriceBase;

public class PriceBaseDaoImpl extends SimpleJdbcDaoSupport implements PriceBaseDao {
	private static final String TABLE_NAME = "tbl_pricebase";
	
	private final RowMapper<PriceBase> ROW_MAPPER = new RowMapper<PriceBase>() {
	    public PriceBase mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	PriceBase p = new PriceBase();
	    	p.setId(rs.getInt("id"));  
	    	p.setCode(rs.getString("code"));
	    	p.setDescription(rs.getString("description"));
	    	return p;
	    	}
	};	
	
	public PriceBase findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public int create(PriceBase priceBase) {
		String sql = "INSERT INTO " + TABLE_NAME + " (id, code, description) VALUES (?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // id 
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // Description
		
		Object[] parameters = new Object[] {priceBase.getId(), 
				priceBase.getCode(), 
				priceBase.getDescription()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		priceBase.setId(keyHolder.getKey().intValue());
		return priceBase.getId();
	}

	public PriceBase findByCode(String code) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
	}

}
