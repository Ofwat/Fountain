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
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import uk.gov.ofwat.fountain.domain.Team;

public class TeamDaoImpl extends SimpleJdbcDaoSupport implements TeamDao{

	private static final String TEAM_TABLE_NAME = "tbl_team";
	
	private final RowMapper<Team> ROW_MAPPER = new RowMapper<Team>() {
	    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Team t = new Team();
	    	t.setId(rs.getInt("id"));
	    	t.setName(rs.getString("name"));
	    	t.setCode(rs.getString("code"));
	    	return t;
	    }
	};	
	
	public Team findById(int id) {
		String sql = "SELECT * FROM " + TEAM_TABLE_NAME+ " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public Team findByName(String name) {
		String sql = "SELECT * FROM " + TEAM_TABLE_NAME+ " WHERE name = ?";
		try {
			return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, name);
		} catch (DataAccessException e) {
			// Its OK to find no team.
			return null;
		}
	}

	public List<Team> getAllTeams() {
		String sql = "SELECT * FROM "+ TEAM_TABLE_NAME ;
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER,  new Object[]{});
	}

}
