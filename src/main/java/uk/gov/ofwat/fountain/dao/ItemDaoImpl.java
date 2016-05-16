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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Team;

public class ItemDaoImpl extends JdbcDaoSupport  implements ItemDao{
	
	private static final String ITEM_TABLE_NAME = "tbl_item";
	private static final String ITEM_PROPERTIES_TABLE_NAME = "tbl_itemproperties";
	
	private static List<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
	
	private GroupDao groupDao;
	private TeamDao teamDao;
	private CodeListDao codeListDao;
	
	public void setCodeListDao(CodeListDao codeListDao){
		this.codeListDao = codeListDao;
	}
	
	public void setGroupDao(GroupDao groupDao){
		this.groupDao = groupDao; 
	}
	
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}



	private final RowMapper<Item> ROW_MAPPER = new RowMapper<Item>() {
	    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Item i = new Item();
	        i.setId(rs.getInt("Id"));
	        i.setCode(rs.getString("Code"));
	        i.setName(rs.getString("name"));
	        i.setUnit(rs.getString("unit"));
//	        i.setLatestDescription(rs.getString("description"));
	        i.setLatestDescription("Description not set");
	        int groupId = rs.getInt("GroupId");
	        Group group = groupDao.findById(groupId);
	        i.setGroup(group);
	        int teamId = rs.getInt("teamId");
	        Team team = (Team)teamDao.findById(teamId);
	        i.setTeam(team);
	        int codeListId = rs.getInt("ListId");
	        if(0 != codeListId){
	        	i.setCodeList(codeListDao.findById(codeListId));
	        }
	        return i;
	    }
	};	

	
	public int create(Item item){
		String sql = "INSERT INTO " + ITEM_TABLE_NAME + " (Code, Name, Unit, GroupId, teamId, listId) VALUES (?, ?, ?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Code
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Unit
        su.declareParameter(new SqlParameter(Types.INTEGER)); // Group Id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // Team Id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // List Id
        Team team = item.getTeam();
        int teamId = 0; // nominal unassigned group
        int listId = 0; // assume no list
        if(null != team){
        	teamId = team.getId();
        }
        if(null != item.getCodeList()){
        	listId = item.getCodeList().getId();
        }
        Object[] parameters = new Object[] {item.getCode(), item.getName(), item.getUnit(), item.getGroup().getId(), teamId, listId};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        item.setId(keyHolder.getKey().intValue());
        return item.getId();
	}

	public synchronized Item findById(int id) {
//		String sql = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE i.id = ? GROUP BY i.id ";
//			String sql = "select i.*, p.description " +
//					"from tbl_itemproperties as p " +
//					"inner join( " +
//					"    select MAX(version) version, itemId, id, description " +
//					"    from tbl_itemproperties " +
//					"    group by itemId " +
//					") as ss on p.itemId = ss.itemId and p.version = ss.version " +
//					"inner join tbl_item as i  " +
//					"on i.id = p.itemId " +
//					"where i.id = ?";
//		 return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
		 String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE Id=?";
	     return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}
	
	public synchronized List<Item>getItemsByPosition(int startRec, int noOfRecs){
		String sql = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID GROUP BY i.id limit " + startRec + ", " + noOfRecs;		
		Object[] args = new Object[]{};
		return getJdbcTemplate().query(sql, ROW_MAPPER, args);
	}

	public Item findByCode(String code){
//		String sql = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE i.code = ? GROUP BY i.id ";
//		String sql = "select i.*, p.description " +
//		"from tbl_itemproperties as p " +
//		"inner join( " +
//		"    select MAX(version) version, itemId, id, description " +
//		"    from tbl_itemproperties " +
//		"    group by itemId " +
//		") as ss on p.itemId = ss.itemId and p.version = ss.version " +
//		"inner join tbl_item as i  " +
//		"on i.id = p.itemId " +
//		"where i.code = ?";
		String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE Code=?";
		Item item = null;
		try{
	    	item = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, code);
		}
		catch(DataAccessException dae) {
			// Deliberate behaviour. Returns null if no code found 
		}
		return item;
	}
	
	public Item findByName(String name) {
		String sql = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE i.name = ? GROUP BY i.id";
	    return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, name);
	}
	
	/**
	 * Find all the items for a particular table. 
	 */
	public List<Item> findByTableId(int tableId){
		//Need to list all items for a table.		
		String sql = "select distinct i.code, i.id, " + 
        "i.name, i.unit, i.groupId, i.teamId, i.listId " +
        "from tbl_table t, tbl_table_pots tp, tbl_pot p, " +
        "tbl_item i, tbl_itemproperties ip " +
        "where tp.tableId = t.id " +
        "and p.id = tp.potId " + 
        "and p.itemId = i.id " +
        "and ip.itemId = i.id " +
        "and t.id = ?";		
		return getJdbcTemplate().query(sql, ROW_MAPPER, tableId);
	}

	public List<Item> searchByCodeOrDescription(String criteria) {
		String sqlCode = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE i.CODE LIKE ? GROUP BY i.id";
		String sqlName = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE i.NAME LIKE ? GROUP BY i.id";
		List<Item> results = getJdbcTemplate().query(sqlCode, ROW_MAPPER, criteria  + "%");// match beginning of item code
		results.addAll(getJdbcTemplate().query(sqlName, ROW_MAPPER,"%" +  criteria  + "%"));// match anywhere in description
		return results;
	}

	public List<Item> searchByLastestDefinitions(String criteria) {
		String sql = "SELECT i.*, p.description, p.definition, p.id as propId, MAX(p.version) as maxP FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID WHERE p.definition LIKE ? and p.version in (Select Max(version) from " + ITEM_PROPERTIES_TABLE_NAME + " p2 where p2.itemId = i.id) GROUP BY i.id";
		return getJdbcTemplate().query(sql, ROW_MAPPER, "%" + criteria  + "%");
	}

	public List<Item> searchByCodeOrDescription(String criteria,
			int[] modelFilters) {
		if(null == modelFilters || 0 == modelFilters.length){
			return searchByCodeOrDescription(criteria);
		}
		String sqlCode = buildFilteredSearchQuery("Code", modelFilters);
		String sqlName = buildFilteredSearchQuery("Description", modelFilters);
		List<Item> results = getJdbcTemplate().query(sqlCode, ROW_MAPPER, criteria  + "%"); // match beginning of item code
		results.addAll(getJdbcTemplate().query(sqlName, ROW_MAPPER,"%" +  criteria  + "%")); // match anywhere in description
		return results;
	}

	public List<Item> searchByLastestDefinitions(String criteria,
			int[] modelFilters) {
		if(null == modelFilters || 0 == modelFilters.length){
			return searchByLastestDefinitions(criteria);
		}
		StringBuffer sqlBuf = new StringBuffer("SELECT i.*, p.description, MAX(p.version) FROM " );
		sqlBuf.append(ITEM_TABLE_NAME);
		sqlBuf.append(" as i LEFT JOIN ");
		sqlBuf.append(ITEM_PROPERTIES_TABLE_NAME);
		sqlBuf.append(" as p ON i.id = p.itemID WHERE p.definition LIKE ? ");
		sqlBuf.append("and p.version in (Select Max(version) from ");
		sqlBuf.append(ITEM_PROPERTIES_TABLE_NAME);
		sqlBuf.append(" p2 where p2.itemId = i.id )");
		sqlBuf.append(" and i.id in (SELECT itemId FROM tbl_modelpropertiesmap t where modelId in ( ");
		int len = modelFilters.length;
		for(int i = 0; i < (len - 1); i++ ){
			sqlBuf.append(modelFilters[i]);
			sqlBuf.append(", ");
		}
		sqlBuf.append(modelFilters[len-1]);
		sqlBuf.append(")) GROUP BY i.id");
		
		return getJdbcTemplate().query(sqlBuf.toString(), ROW_MAPPER, "%" + criteria  + "%");
	} 
	
	private String buildFilteredSearchQuery(String aspect, int[] modelIds){
		
		StringBuffer sqlBuf = new StringBuffer("SELECT i.*, p.description, MAX(p.version) FROM ");
		sqlBuf.append(ITEM_TABLE_NAME);
		sqlBuf.append(" as i LEFT JOIN ");
		sqlBuf.append(ITEM_PROPERTIES_TABLE_NAME);
		sqlBuf.append(" as p ON i.id = p.itemID WHERE ");
		sqlBuf.append(aspect);
		sqlBuf.append(" LIKE ? ");
		sqlBuf.append("and p.version in (Select Max(version) from ");
		sqlBuf.append(ITEM_PROPERTIES_TABLE_NAME);
		sqlBuf.append(" p2 where p2.itemId = i.id )");
		sqlBuf.append(" AND i.id in (SELECT itemId FROM tbl_modelpropertiesmap t where modelId in ( ");
		int len = modelIds.length;
		for(int i = 0; i < (len - 1); i++ ){
			sqlBuf.append(modelIds[i]);
			sqlBuf.append(", ");
		}
		sqlBuf.append(modelIds[len-1]);
		sqlBuf.append(")) GROUP BY i.id");
		return sqlBuf.toString();
	}

	public void update(Item item) {
		String sql = "UPDATE " + ITEM_TABLE_NAME + " SET name = ?, code = ?,  unit = ?, groupId = ?, teamId = ?, listId = ? WHERE id = ?";
		int teamId = 0;
		if(null != item.getTeam()){
			teamId = item.getTeam().getId();
		}
		int listId = 0;
		if(null != item.getCodeList()){
			listId = item.getCodeList().getId();
		}
		getJdbcTemplate().update(sql, new Object[]{item.getName(), item.getCode(), item.getUnit(), item.getGroup().getId(), teamId, listId, item.getId()});
		alertListeners();
	}
	
	public List<Item> findAll(){
		String sql = "SELECT i.*, p.description, MAX(p.version) FROM " + ITEM_TABLE_NAME + " as i LEFT JOIN " + ITEM_PROPERTIES_TABLE_NAME + " as p ON i.id = p.itemID GROUP BY i.id";		
		return getJdbcTemplate().query(sql, ROW_MAPPER);
	}
	

	public void addItemUpdateListener(UpdateListener listener) {
		updateListeners.add(listener);
	}
	
	
	private static void alertListeners(){
		for(UpdateListener listener: updateListeners){
			listener.updateOccurred();
		}
	}

}
