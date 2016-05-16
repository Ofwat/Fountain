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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Value;

public class ValueDaoImpl extends JdbcDaoSupport  implements ValueDao {

	private static final Log log = LogFactory.getLog(ValueDaoImpl.class);
	
	private final static String VALUE_TABLENAME = "tbl_value";
	
	private final RowMapper<Value> DATA_MAPPER = new RowMapper<Value>() {
	    public Value mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	//Value v = new Value();
	        //v.setId(rs.getInt("id"));
//	        v.setItem(itemDao.findById(rs.getInt("itemId")));
//	        v.setInterval(intervalDao.findById(rs.getInt("intervalId")));
//	        v.setValue(rs.getString("value"));
//	        v.setBranch(branchDao.findById(rs.getInt("branchTagId")));
//	        v.setCalculatedValue(false);
//	        v.setCompany(companyDao.findById(rs.getInt("companyId")));
//	        v.setGroupEntry(groupDao.findEntryById(rs.getInt("groupEntryId")));
//	        v.setValueId(rs.getInt(6));
	        
	    	Value v= getValue(rs.getInt("id"));
	        return v;
	    }
	};	

	private Value getValue(int id){
    	Value v = new Value();
        v.setId(id);
        return v;
	}
	
	public synchronized Value findById(int id) {
		 String sql = "SELECT * FROM " + VALUE_TABLENAME + " WHERE id=?";
	     return getJdbcTemplate().queryForObject(sql, DATA_MAPPER, id);
	}

	/**
	 * Get a List of value from a passed array of Ids using an 'IN' statement.
	 */
	
	public synchronized List<Value> findByIdList(int[] valueIds){
		//build the string.
		String inList = "";
		int maxId = 0;
		int minId = Integer.MAX_VALUE;		
		for(int i=0;i<valueIds.length;i++){
			if(valueIds[i] > maxId){
				maxId = valueIds[i];
			}
			if(valueIds[i] < minId){
				minId = valueIds[i];
			}			
			inList = inList + valueIds[i] + ",";
		}
		List<Value> result = new ArrayList<Value>();
		if (!inList.isEmpty()) {
			//remove the last comma
			inList = inList.substring(0,inList.length()-1);
			String sql = "SELECT * FROM " + VALUE_TABLENAME + " WHERE id >= " + minId + " and id <= " + maxId + " and id in (" + inList + ")";
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> valueMap : rows){
				result.add(getValue((int)valueMap.get("id")));
			}
		}
		return result;
	}
	
	/**
	 * Get a List of value from a passed array of Ids using a macx and min statment.
	 */
	/*
	public synchronized List<Value> findByIdList(int[] valueIds){
		int maxId = 0;
		int minId = Integer.MAX_VALUE;
		for(int i=0;i<valueIds.length;i++){
			if(valueIds[i] > maxId){
				maxId = valueIds[i];
			}
			if(valueIds[i] < minId){
				minId = valueIds[i];
			}
		}
		String sql = "SELECT * FROM " + VALUE_TABLENAME + " WHERE id >= " + minId + " and id <= " + maxId;
		List<Value> result = new ArrayList<Value>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for(Map<String, Object> valueMap : rows){
			result.add(getValue((int)valueMap.get("id")));
		}
		return result;
	}
	*/
	
}
