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
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Line;

public class LineDaoImpl extends JdbcDaoSupport implements LineDao {

	private final static String TABLE_NAME = "tbl_line";

	private final RowMapper<Line> ROW_MAPPER = new RowMapper<Line>() {
		public synchronized Line mapRow(ResultSet rs, int rowNum) throws SQLException {
			Line line = new Line();
			line.setTableId(rs.getInt("tableId"));
			line.setItemId(rs.getInt("itemId"));
			line.setLineNumber(rs.getInt("lineNumber"));
			return line;
		}
	};	

	public void create(Line line) {
		String sql = "INSERT INTO " + TABLE_NAME + " (tableId, itemId, lineNumber) VALUES (?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // tableId 
		su.declareParameter(new SqlParameter(Types.INTEGER)); // itemId
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // lineNumber
		
		Object[] parameters = new Object[] {line.getTableId(), 
				                            line.getItemId(), 
				                            line.getLineNumber()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
	}



	public Line findByTableItemLineNumber(int tableId, int itemId, int lineNumber) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE tableId = ? and itemId = ? and lineNumber = ?";
		Line line = null;
		try {
			line = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, new Object[]{tableId}, new Object[]{itemId}, new Object[]{lineNumber});
		} catch (DataAccessException e) {
	    	 // Its OK to find no data. 
	    	 // TODO log this.
		}
		return line;
	}

	public List<Line> findByTableItem(int tableId, int itemId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE tableId = ? and itemId = ?";
		List<Line> lines = null;
		try {
			lines = getJdbcTemplate().query(sql, ROW_MAPPER, tableId, itemId);
		} catch (DataAccessException e) {
	    	 // Its OK to find no data. 
	    	 // TODO log this.
		}
		return lines;
	}

}
