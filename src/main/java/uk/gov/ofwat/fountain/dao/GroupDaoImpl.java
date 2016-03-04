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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;

public class GroupDaoImpl extends SimpleJdbcDaoSupport  implements GroupDao{

	private final static String GROUP_TABLE_NAME = "tbl_group";
	private final static String GROUP_ENTRY_TABLE_NAME = "tbl_groupentry";
	private final static String COMPANY_GROUP_TABLE_NAME = "tbl_companygroup";
	
	private static List<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
	
	// the name of the default ungrouped data group
	private static final String NO_GROUP_NAME = "NONE";
	
	private static final Group UNGROUPED = new Group();
	static{
		UNGROUPED.setId(1);
		UNGROUPED.setName("No Group");
	}
	
	private final RowMapper<Group> GROUP_ROW_MAPPER = new RowMapper<Group>() {
	    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Group g = new Group();
	    	g.setId(rs.getInt("id"));
	    	g.setName(rs.getString("name"));
	    	return g;
	    }
	};	
	
	private final RowMapper<GroupEntry> GROUP_ENTRY_ROW_MAPPER = new RowMapper<GroupEntry>() {
		public GroupEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
			GroupEntry ge = new GroupEntry();
			ge.setId(rs.getInt("id"));   	
			ge.setDescription(rs.getString("description"));
			ge.setOrdinal(rs.getInt("ordinal"));
			return ge;
		}
	};	
	
	
	
	public int create(Group group) {
		String sql = "INSERT INTO " + GROUP_TABLE_NAME + " (name, description) VALUES (?, ?)";
		SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
        Object[] parameters = new Object[] {group.getName(), "Imported"};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	

	public int createGroupEntry(GroupEntry groupEntry) {
		
		// check that the company group exists
		int companyGroupId = findCompanyGroupId(groupEntry.getGroup().getId(), groupEntry.getCompany().getId()); 
		if(-1 == companyGroupId){
			companyGroupId = createCompanyGroup(groupEntry.getGroup(), groupEntry.getCompany());
		}
		
		String sql = "INSERT INTO " + GROUP_ENTRY_TABLE_NAME + " (CompanyGroupId, description, ordinal) VALUES (?, ?, ?)";
		SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyGroupId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ordinal
        Object[] parameters = new Object[] {companyGroupId, groupEntry.getDescription(), groupEntry.getOrdinal()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
		return keyHolder.getKey().intValue();
		
	}


	/**
	 * returns the companyGroup id or -1 if it doesn't exist
	 * @param groupId
	 * @param companyId
	 * @return
	 */
	private int findCompanyGroupId(int groupId, int companyId){
		int cgId = -1;
		String sql = "SELECT id FROM " + COMPANY_GROUP_TABLE_NAME + " WHERE groupId = ? and companyId = ?";
		try{
			cgId = getSimpleJdbcTemplate().queryForInt(sql, groupId, companyId);
		}
		catch(Exception e){
			// no such companyGroup - return -1
		}
		return cgId;
	}
	
	private int createCompanyGroup(Group group, Company company) {
		String sql = "INSERT INTO " + COMPANY_GROUP_TABLE_NAME + " (groupId, companyId) VALUES (?, ?)";
		SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // groupId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyId
        Object[] parameters = new Object[] {group.getId(), company.getId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
		return keyHolder.getKey().intValue();
	}



	public Group findById(int groupId) {
		if(0 == groupId){
			return UNGROUPED;
		}
		String sql = "SELECT * FROM " + GROUP_TABLE_NAME+ " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ROW_MAPPER, groupId);
	}

	public List<GroupEntry> findEntriesForCompanyAndGroup(Company company, Group group) {
		String sql = "SELECT ge.* FROM "+GROUP_ENTRY_TABLE_NAME+" ge INNER JOIN "+COMPANY_GROUP_TABLE_NAME+" cg ON cg.id = ge.companyGroupID WHERE cg.companyId = ? AND cg.groupID = ?";
		
		List<GroupEntry> entries = getSimpleJdbcTemplate().query(sql, GROUP_ENTRY_ROW_MAPPER, new Object[]{company.getId(), group.getId()});
		for(GroupEntry entry: entries){
			entry.setGroup(group);
			entry.setCompany(company);
		}
		return entries;
	}

	public GroupEntry findEntryById(int groupEntryId) {
		String sql = "SELECT * FROM " + GROUP_ENTRY_TABLE_NAME+ " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ENTRY_ROW_MAPPER, groupEntryId);
	}
	
	

	public GroupEntry findEntryCompanyAndName(int companyId,
			String groupEntryName) {
		GroupEntry ge = null;
		String sql = "SELECT ge.* FROM " + GROUP_ENTRY_TABLE_NAME + " ge INNER JOIN (" +  COMPANY_GROUP_TABLE_NAME + " cg INNER JOIN " + GROUP_TABLE_NAME + "  gp ON cg.groupId = gp.id)  ON ge.companyGroupId = cg.id WHERE cg.companyId = ? AND ge.description = ?";
		try{
			ge = getSimpleJdbcTemplate().queryForObject(sql, GROUP_ENTRY_ROW_MAPPER, companyId, groupEntryName);
		}
		catch(IncorrectResultSizeDataAccessException irsdae){
			// it's ok to have no results. return null
			return null;
		}
		return ge;
	}
	
	



	public GroupEntry findUngroupedEntry(Company company) {
		try {
			// Get the ungrouped entry
			String sql = "SELECT ge.* FROM " + GROUP_ENTRY_TABLE_NAME + " ge INNER JOIN (" +  COMPANY_GROUP_TABLE_NAME + " cg INNER JOIN " + GROUP_TABLE_NAME + "  gp ON cg.groupId = gp.id)  ON ge.companyGroupId = cg.id WHERE cg.companyId = ? AND gp.name = ?";
			return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ENTRY_ROW_MAPPER, company.getId(), NO_GROUP_NAME);
		}
		catch (EmptyResultDataAccessException ex1) {
			GroupEntry ge = new GroupEntry();
			ge.setCompany(company);
			ge.setDescription("NON GROUPED ITEM");
			ge.setGroup(UNGROUPED);

			int id = createGroupEntry(ge);
			String sql = "SELECT * FROM " + GROUP_ENTRY_TABLE_NAME + " WHERE  id=?";
			return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ENTRY_ROW_MAPPER, id);
		}
	}



	public Group findByName(String groupName) {
		String sql = "select * from " + GROUP_TABLE_NAME + " where name = ?";
		try {
			return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ROW_MAPPER, groupName);
		}
		catch (EmptyResultDataAccessException ex1) {
			// Its valid to find no group.
			return null;
		}
	}



	public List<Group> getAllGroups() {
		String sql = "SELECT * FROM "+ GROUP_TABLE_NAME;
		return getSimpleJdbcTemplate().query(sql, GROUP_ROW_MAPPER, new Object[]{});
	}



	public void updateGroupEntryDescription(int groupEntryId, String description) {
		String sql = "UPDATE " + GROUP_ENTRY_TABLE_NAME + " SET description = ? WHERE id = ?";
		getSimpleJdbcTemplate().update(sql, new Object[]{description, groupEntryId});
		alertLiseners();
	}


	public void addItemUpdateListener(UpdateListener listener) {
		updateListeners.add(listener);
	}
	
	
	public GroupEntry findEntryCompanyGroupAndOrdinal(int companyId,
			String groupName, int ordinal) {
		String sql =  "select g.*, e.* from " + GROUP_TABLE_NAME + " g inner join (" + COMPANY_GROUP_TABLE_NAME + " c inner join " + GROUP_ENTRY_TABLE_NAME + " e on c.id = e.companygroupid) on g.id = c.groupId where e.ordinal = ? and g.name like ? and c.companyId = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, GROUP_ENTRY_ROW_MAPPER, new Object[]{ordinal, groupName, companyId});
	}



	private static void alertLiseners(){
		for(UpdateListener listener: updateListeners){
			listener.updateOccurred();
		}
	}
	

	

}
