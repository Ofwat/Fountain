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
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.ModelFamily;

public class ModelFamilyDaoImpl extends JdbcDaoSupport implements ModelFamilyDao{

//	private static final String MODEL_TABLE_NAME = "tbl_model";
	private final static String MODEL_FAMILY_TABLE_NAME = "tbl_modelfamily";
	
	private final RowMapper<ModelFamily> ROW_MAPPER = new RowMapper<ModelFamily>() {
	    public ModelFamily mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int id = rs.getInt("ID");
	    	ModelFamily m = new ModelFamily();
	    	m.setId(id);
	    	m.setName(rs.getString("name"));
	    	m.setCode(rs.getString("code"));
	    	m.setId(id);
	        return m;
	    }
	};	
	
	public List<ModelFamily> findAll() {
		String sql = "SELECT * FROM " + MODEL_FAMILY_TABLE_NAME + "";
		return getJdbcTemplate().query(sql, ROW_MAPPER);
	}	
	
	public ModelFamily findByCode(String code) {
		String sql = "SELECT * FROM " + MODEL_FAMILY_TABLE_NAME + " WHERE code = ?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
	}

	public ModelFamily findById(int id) {
		String sql = "SELECT * FROM " + MODEL_FAMILY_TABLE_NAME + " WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public int create(ModelFamily family) {	
		String sql = "INSERT INTO " + MODEL_FAMILY_TABLE_NAME + " ( Name, Code) VALUES (?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Code
        Object[] parameters = new Object[] { family.getName(), family.getCode()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        family.setId(keyHolder.getKey().intValue());
        return family.getId();
	}

	public void update(ModelFamily modelFamily) {
		 String sql = "UPDATE " + MODEL_FAMILY_TABLE_NAME + " SET " + 
         "Code=?, " +
         "Name=?, " + 
         "WHERE ID=?";		
		 getJdbcTemplate().update(sql, modelFamily.getCode(), modelFamily.getName(), modelFamily.getId());
		
	}
	
	

}
