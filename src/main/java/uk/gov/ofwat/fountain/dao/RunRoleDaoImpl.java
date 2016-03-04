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

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import uk.gov.ofwat.fountain.domain.Coded;
import uk.gov.ofwat.fountain.domain.RunRole;

public class RunRoleDaoImpl extends SimpleJdbcDaoSupport implements RunRoleDao {

	private static final String TABLE_NAME = "tbl_run_role";
	
	private final RowMapper<RunRole> ROW_MAPPER = new RowMapper<RunRole>() {
	    public RunRole mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	RunRole runRole = null;
//	    	new RunRole();
//	    	runRole.setId(rs.getInt("id"));
//	    	runRole.setCode(rs.getString("code"));
	        return runRole;
	    }
	};
	
	public RunRole findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public RunRole findByCode(String code) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE code = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
	}

}
