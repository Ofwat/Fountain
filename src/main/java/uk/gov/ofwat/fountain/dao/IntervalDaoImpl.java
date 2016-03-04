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

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;

public class IntervalDaoImpl extends SimpleJdbcDaoSupport  implements IntervalDao{
	
	private static final String INTERVAL_TABLE_NAME = "tbl_interval";
	private static final String INTERVAL_TYPE_TABLE_NAME = "tbl_intervaltype";
	
	private final RowMapper<Interval> ROW_MAPPER = new RowMapper<Interval>() {
	    public Interval mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Interval y = new Interval();
	    	IntervalType t = new IntervalType();
	    	t.setId(rs.getInt("intervalTypeId"));
	    	t.setName(rs.getString("intervalTypeName"));
	    	t.setDisplayOrder(rs.getInt("displayOrder"));
	        y.setId(rs.getInt("Id"));
	        y.setName(rs.getString("Name"));
	        y.setIntervalType(t);
	        y.setIntervalIndex(rs.getInt("intervalIndex"));
	        return y;
	    }
	};	
	
	private final RowMapper<IntervalType> TYPE_ROW_MAPPER = new RowMapper<IntervalType>() {
		public IntervalType mapRow(ResultSet rs, int rowNum) throws SQLException{
			IntervalType it = new IntervalType();
			it.setId(rs.getInt("id"));
			it.setName(rs.getString("name"));
			it.setDisplayOrder(rs.getInt("displayOrder"));
			return it;
		}
	};
	
	public int create(Interval interval){
		String sql = "INSERT INTO " + INTERVAL_TABLE_NAME + " (Name, intervalTypeId, intervalIndex) VALUES (?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.INTEGER)); // interval type id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // interval index
        Object[] parameters = new Object[] {interval.getName(), interval.getIntervalType().getId(), interval.getIntervalIndex()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        interval.setId(keyHolder.getKey().intValue());
        return interval.getId();
	}

	public synchronized Interval findById(int id) {
		 String sql = "SELECT i.*, t.name as intervalTypeName, t.displayOrder  FROM " +INTERVAL_TABLE_NAME  + " as i INNER JOIN " + INTERVAL_TYPE_TABLE_NAME + " as t ON i.intervalTypeid = t.id WHERE i.Id=?";
	     return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}
	
	public synchronized List<Interval> getAll(){
		String sql = "SELECT i.*, t.name as intervalTypeName , t.displayOrder FROM " +INTERVAL_TABLE_NAME  + " as i INNER JOIN " + INTERVAL_TYPE_TABLE_NAME + " as t ON i.intervalTypeid = t.id order by intervalTypeId, intervalIndex";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, new Object[]{});
		
	}

	public List<Interval> getIntervalsByTypeId(int intervalTypeId) {
		String sql = "SELECT i.*, t.name as intervalTypeName, t.displayOrder  FROM " +INTERVAL_TABLE_NAME  + " as i INNER JOIN " + INTERVAL_TYPE_TABLE_NAME + " as t ON i.intervalTypeid = t.id WHERE t.id=? order by intervalTypeId, intervalIndex";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, new Object[]{intervalTypeId});
		
	}

	public List<IntervalType> getAllIntervalTypes() {
		String sql = "SELECT * FROM " + INTERVAL_TYPE_TABLE_NAME + " ORDER BY displayOrder ASC";
		return getSimpleJdbcTemplate().query(sql, TYPE_ROW_MAPPER, new Object[]{});
		
	}

	public Interval findByName(String intervalName) {
		 String sql = "SELECT i.*, t.name as intervalTypeName, t.displayOrder  FROM " +INTERVAL_TABLE_NAME  + " as i INNER JOIN " + INTERVAL_TYPE_TABLE_NAME + " as t ON i.intervalTypeid = t.id WHERE i.name=?";
	     Interval interval = null;
		 try{
		 	interval = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, intervalName);	
		 	}
		 catch(DataAccessException dae){
			 System.out.println("Can't find interval "+ intervalName);
			 throw dae;
		 }
		 return interval;
	}
}
