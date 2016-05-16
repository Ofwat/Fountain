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

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.CompanyTable;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Table;

public class TableDaoImpl extends JdbcDaoSupport  implements TableDao {

	private static final String COMPANY_TABLE = "tbl_companyTable";
	private static final String TABLE_TABLE = "tbl_table";
	private static final String COMPANY_GROUP_TABLE = "tbl_tablegroups";
	
	private static final RowMapper<Table> ROW_MAPPER = new RowMapper<Table>() {
	    public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Table t = new Table();
	        t.setId(rs.getInt("ID"));
	        t.setName(rs.getString("name"));
	        t.setModelId(rs.getInt("modelId"));
	        t.setCompanyTypeId(rs.getInt("companyTypeId"));
	        return t;
	    }
	};	
	
	private static final RowMapper<Integer> GROUPID_ROW_MAPPER = new RowMapper<Integer>() {
	    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getInt("groupId");
	    }
	};	
	
	private static final RowMapper<CompanyTable> COMPANYTABLE_ROW_MAPPER = new RowMapper<CompanyTable>() {
		
		public CompanyTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CompanyTable ct = new CompanyTable();
			ct.setId(rs.getInt("id"));
			ct.setCompanyId(rs.getInt("companyId"));
			ct.setModelId(rs.getInt("modelId"));
			ct.setTableId(rs.getInt("tableId"));
			return ct;
		}
	};
	
	
	public int create(Table table) {
        String sql = "INSERT INTO " + TABLE_TABLE + " (Name, modelId, companyTypeId) VALUES (?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ModelId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyTypeId
        Object[] parameters = new Object[] {table.getName(), table.getModelId(), table.getCompanyTypeId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        table.setId(keyHolder.getKey().intValue());
        if(null != table.getTableGroups()){
        	String groupSql = "INSERT INTO " + COMPANY_GROUP_TABLE + " (tableId, groupId) VALUES (?, ?)";
        	su = new SqlUpdate();
        	su.setDataSource(getDataSource());
            su.setSql(groupSql);
            su.declareParameter(new SqlParameter(Types.INTEGER)); // tableId
            su.declareParameter(new SqlParameter(Types.INTEGER)); // groupId
    		su.setReturnGeneratedKeys(true);
        	for(Group group: table.getTableGroups()){
        		parameters = new Object[] {table.getId(), group.getId()};
        		su.update(parameters, keyHolder);
        	}
        }
        return table.getId();
	}

	public Table findById(int id) {
        String sql = "SELECT * FROM tbl_table WHERE ID=?";
        Table table = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
        return table;
	}
	
	public List<Table>getAll(){
		
		String sql = "SELECT * FROM tbl_table";
		List<Table> tables = getJdbcTemplate().query(sql, ROW_MAPPER);
		return tables;
	}

	public List<Table> findByModelId(int modelId) {
		String sql = "SELECT * FROM tbl_table WHERE modelId=? ORDER BY Id";
        return getJdbcTemplate().query(sql, ROW_MAPPER, modelId);
	}
	
	public List<Table> findByModelIdAndCompanyId(int modelId, int companyId) {
		String sql = "SELECT * FROM " + COMPANY_TABLE + " WHERE modelId=? AND companyId =? ORDER BY tableId";
		List<CompanyTable> companyTables = getJdbcTemplate().query(sql, COMPANYTABLE_ROW_MAPPER, modelId, companyId);
		List<Table> tables = new ArrayList<Table>();
		for (CompanyTable companyTable : companyTables) {
			Table t = findById(companyTable.getTableId());
			tables.add(t);
		}
		return tables;
	}

	public List<Table> findByModelIdAndCompanyType(int modelId,	int companyTypeId) {
		String sql = "SELECT * FROM " + TABLE_TABLE + " WHERE modelId=? AND (companyTypeId=? OR companyTypeId = null OR companyTypeId = 0) ORDER BY id";
		return getJdbcTemplate().query(sql, ROW_MAPPER, modelId, companyTypeId);
	}

	public int addCompanyTable(int companyId, int modelId, int tableId) {
		 String sql = "INSERT INTO " + COMPANY_TABLE +"(companyId, modelId, tableId) VALUES (?, ?, ?)";
	        SqlUpdate su = new SqlUpdate();
	        su.setDataSource(getDataSource());
	        su.setSql(sql);
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // CompanyId
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // ModelId
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // TableId
	        Object[] parameters = new Object[] {companyId, modelId, tableId};
	        KeyHolder keyHolder = new GeneratedKeyHolder();
			su.setReturnGeneratedKeys(true);
	        su.update(parameters, keyHolder);
	        return keyHolder.getKey().intValue();
	}

	public List<Integer> getGroupIdsForTable(int tableId) {
		String sql = "select groupId from " + COMPANY_GROUP_TABLE + " where tableId = ?";
		return getJdbcTemplate().query(sql, GROUPID_ROW_MAPPER, tableId);
	}
	
	public List<Table> getTablesForItem(int itemId){
		//We need to select certain elements from the Model and the Table here - do we create a specific DTO or fudge two together?
		String sql = "SELECT DISTINCT t.id, t.name, t.modelId, t.companyTypeId FROM tbl_table t, tbl_pot p, tbl_table_pots tp, tbl_model m " + 
		"where p.itemid = ? and p.id = tp.potId and t.id = tp.tableId " +  
		"and m.id = t.modelId";
		List<Table> tables = getJdbcTemplate().query(sql, ROW_MAPPER, itemId);
		return tables;	
	}
	
	public List<Table>findAll(){
		String sql = "SELECT * FROM tbl_table ORDER BY Id";
        return getJdbcTemplate().query(sql, ROW_MAPPER);
	}
	
}
