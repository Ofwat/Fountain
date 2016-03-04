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

import uk.gov.ofwat.fountain.domain.Pot;

public class PotDaoImpl extends SimpleJdbcDaoSupport implements PotDao {
	
	private final static String TABLE_NAME = "tbl_pot";
	private final static String POT_JOIN_TABLE_NAME = "tbl_table_pots";
	
	ItemDao itemDao;
	IntervalDao intervalDao;
	ModelDao modelDao;
	ModelPropertiesMapDao modelPropertiesMapDao;
	
	public IntervalDao getYearDao() {
		return intervalDao;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public void setModelDao(ModelDao modelDao){
		this.modelDao = modelDao;
	}
	public void setModelPropertiesMapDao(ModelPropertiesMapDao modelPropertiesMapDao) {
		this.modelPropertiesMapDao = modelPropertiesMapDao;
	}


	private final RowMapper<Pot> POT_MAPPER = new RowMapper<Pot>() {
	    public Pot mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Pot p = new Pot();
	        p.setId(rs.getInt("Id"));
	        int itemId = rs.getInt("itemId");
	        int modelId = rs.getInt("modelId");
	        int runId = rs.getInt("runId");
	        p.setItem(itemDao.findById(itemId));
	        p.setInterval(intervalDao.findById(rs.getInt("intervalId")));
	        p.setModelId(modelId);
	        p.setModelPropertiesMap(modelPropertiesMapDao.findByModelAndItem(modelId, itemId));
	        p.setRunId(runId);
			return p;
	    }
	};	

	public synchronized int create(Pot pot, int tableId) {
		String sql = "INSERT INTO " + TABLE_NAME + " (IntervalId, ItemId, ModelId, RunId) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // interval
        su.declareParameter(new SqlParameter(Types.INTEGER)); // item
        su.declareParameter(new SqlParameter(Types.INTEGER)); // model
        su.declareParameter(new SqlParameter(Types.INTEGER)); // run
        Object[] parameters = new Object[] {pot.getInterval().getId(), pot.getItem().getId(), pot.getModel().getId(), pot.getRunTag().getRun().getId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        pot.setId(keyHolder.getKey().intValue());
        
        su = new SqlUpdate();
        su.setDataSource(getDataSource());
        sql = "INSERT INTO " + POT_JOIN_TABLE_NAME + " (PotId, TableId) VALUES (?, ?)";
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // pot id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // table id
        parameters = new Object[]{pot.getId(), tableId};
        su.update(parameters);
        return pot.getId();
	}

	public synchronized Pot findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID=?";
        return getSimpleJdbcTemplate().queryForObject(sql, POT_MAPPER, id);
	}


	public synchronized void update(Pot pot) {
		String sql = "UPDATE " + TABLE_NAME + " SET " +
        "ItemId=?, " + 
        "IntervalId=? " + 
        "ModelId=? " +
        "RunId=? " +
        "WHERE ID=?";
		 getSimpleJdbcTemplate().update(sql, pot.getItem().getId(), pot.getInterval().getId(), pot.getModel().getId(), pot.getId(), pot.getRunTag().getRun().getId());  
	}

	public List<Pot> findByTableId(int tableId) {
		String sql = "SELECT * FROM "+TABLE_NAME+" t INNER JOIN "+POT_JOIN_TABLE_NAME+" p on t.id = p.potId WHERE p.tableId = ?";
        return getSimpleJdbcTemplate().query(sql, POT_MAPPER, tableId);
	}

	
	

}
