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
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;

/**
 * Not cachable as it calls on itemPropertiesDAO
 */
public class ItemPropertyIntervalDaoImpl extends SimpleJdbcDaoSupport implements ItemPropertyIntervalDao{
	
	
	private final static String TABLE_NAME = "tbl_itempropertyinterval";
	private final static String INTERVAL_TABLE_NAME = "tbl_interval";
	
	private ItemPropertiesDao itemPropertiesDao;
	private IntervalDao intervalDao;
	
	
	public ItemPropertiesDao getItemPropertiesDao() {
		return itemPropertiesDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public IntervalDao getIntervalDao() {
		return intervalDao;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}

	private final RowMapper<ItemPropertyInterval> ROW_MAPPER = new RowMapper<ItemPropertyInterval>() {
		public synchronized ItemPropertyInterval mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			String desc = rs.getString("description");
			String formula = rs.getString("formula");
			ItemPropertyInterval ip = new ItemPropertyInterval();
			ip.setItemProperties(itemPropertiesDao.findById(rs.getInt("itemPropertyId")));
			ip.setInterval(intervalDao.findById(rs.getInt("intervalId")));
			ip.setId(rs.getInt("ID"));       
			ip.setDescription(desc);
			ip.setFormula(formula);
			return ip;
		}
	};	

	private final RowMapper<ItemPropertyInterval> ROW_MAPPER_LITE = new RowMapper<ItemPropertyInterval>() {
	    public synchronized ItemPropertyInterval mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	
	    	String desc = rs.getString("description");
	    	String formula = rs.getString("formula");
	    	ItemPropertyInterval ip = new ItemPropertyInterval();
	    	ip.setInterval(intervalDao.findById(rs.getInt("intervalId")));
	    	ip.setId(rs.getInt("ID"));       
	        ip.setDescription(desc);
	        ip.setFormula(formula);
	        return ip;
	    }
	};	
	
	private final RowMapper<Interval> INTERVAL_ROW_MAPPER = new RowMapper<Interval>() {
	    public Interval mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Interval y = new Interval();
	    	IntervalType t = new IntervalType();
	    	t.setId(rs.getInt("intervalTypeId"));
	    	t.setName(rs.getString("intervalTypeName"));
	        y.setId(rs.getInt("Id"));
	        y.setName(rs.getString("Name"));
	        y.setIntervalType(t);
	        return y;
	    }
	};	

	public int create(ItemPropertyInterval ipi) {
		String sql = "INSERT INTO " + TABLE_NAME + " (intervalId, itemPropertyId, formula, description) VALUES (?,?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // intervalId 
		su.declareParameter(new SqlParameter(Types.INTEGER)); // itemPropertyId
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // formula
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
		
		Object[] parameters = new Object[] {ipi.getInterval().getId(), 
				                            ipi.getItemProperties().getId() , 
				                            ipi.getFormula(),
				                            ipi.getDescription()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		ipi.setId(keyHolder.getKey().intValue());
		return ipi.getId();
		
	}

	public ItemPropertyInterval findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? ";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, new Object[]{id});
	}

	public List<ItemPropertyInterval> getIntervalPropertiesForItemPropertyId(
			int itemPropertiesId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE itemPropertyId = ? ";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER_LITE, itemPropertiesId);
	}

	public List<Interval> getPropertyIntervalsForItemProperties(
			int itemPropertiesId) {
		String sql = "SELECT i.* from " + INTERVAL_TABLE_NAME + " i INNER JOIN " + TABLE_NAME + " p ON i.id = p.intervalId WHERE p.itemPropertyId = ?";
		return getSimpleJdbcTemplate().query(sql, INTERVAL_ROW_MAPPER, itemPropertiesId);
	}

}
