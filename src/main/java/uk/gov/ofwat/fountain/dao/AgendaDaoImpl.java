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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Agenda;

public class AgendaDaoImpl extends SimpleJdbcDaoSupport implements AgendaDao {

	private static final String TABLE_NAME = "tbl_agenda";
	
	private final RowMapper<Agenda> ROW_MAPPER = new RowMapper<Agenda>() {
	    public Agenda mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Agenda agenda = new Agenda();
	    	agenda.setId(rs.getInt("id"));
	    	agenda.setCode(rs.getString("code"));
	    	agenda.setName(rs.getString("name"));
	    	agenda.setDescription(rs.getString("description"));
	        return agenda;
	    }
	};
	
	public Agenda findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public Agenda findByCode(String code) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE code = ?";
		try {
			return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
		} catch (DataAccessException e) {
			// It's OK to find no agenda.
			return null;
		}
	}

	public synchronized int create(Agenda agenda) {
        String sql = "INSERT INTO "+TABLE_NAME+" (code, name, description) VALUES (?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
        Object[] parameters = new Object[] {agenda.getCode(), agenda.getName(), agenda.getDescription()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        agenda.setId(keyHolder.getKey().intValue());
        return agenda.getId();
	}

	@Override
	public List<Agenda> findAllAgenda() {
		String sql = "SELECT * FROM " + TABLE_NAME;
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER);
	}


}
