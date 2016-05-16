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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
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

public class DataDaoImpl extends JdbcDaoSupport  implements DataDao {
	private static final Log log = LogFactory.getLog(DataDaoImpl.class);
	
	private final static String DATA_TABLENAME = "tbl_data";
	private final static String VALUE_TABLENAME = "tbl_value";
	private final static String AUDIT__TABLENAME = "tbl_audit";
	private final static String BRANCH_TAG_TABLENAME = "tbl_branch_tag";
	
	private ItemDao itemDao;
	private IntervalDao intervalDao;
	private CompanyDao companyDao;
	private LockDao lockDao;
	private ConfidenceGradeDao confidenceGradeDao;
	private BranchDao branchDao;	
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setLockDao(LockDao lockDao) {
		this.lockDao = lockDao;
	}
	
	public LockDao getLockDao() {
		return lockDao;
	}

	public IntervalDao getIntervalDao() {
		return intervalDao;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}
	
	public void setCompanyDao(CompanyDao companyDao){
		this.companyDao = companyDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setConfidenceGradeDao(ConfidenceGradeDao confidenceGradeDao) {
		this.confidenceGradeDao = confidenceGradeDao;
	}
	
	public BranchDao getBranchDao() {
		return branchDao;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}



	private final RowMapper<Data> DATA_MAPPER = new RowMapper<Data>() {
	    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Data d = new Data();
	        d.setId(rs.getInt("id"));
	        d.setItem(itemDao.findById(rs.getInt("itemId")));
	        d.setInterval((Interval)intervalDao.findById(rs.getInt("intervalId")));
	        d.setValue(rs.getString("value"));
	        d.setBranch(branchDao.findById(rs.getInt("branchTagId")));
	        d.setCalculatedValue(false);
	        d.setCompany(companyDao.findById(rs.getInt("companyId")));
	        d.setGroupEntry(groupDao.findEntryById(rs.getInt("groupEntryId")));
	        d.setValueId(rs.getInt("v.id"));
	        int cgId = rs.getInt("confidenceGradeId");
	        if (cgId>0) {
	        	// CG is optional so only build if a CG ID is specified
	        	d.setConfidenceGrade(confidenceGradeDao.findById(cgId));
	        }
	        
		    d.setRunId(rs.getInt("runId"));
		    d.setTagId(rs.getLong("tagId"));
		        
        	return d;
	    }
	};	

	private final RowMapper<Data> BASIC_DATA_MAPPER = new RowMapper<Data>() {
	    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Data d = new Data();
	        d.setId(rs.getInt("id"));
	        d.setItem(new Item());
	        d.getItem().setId(rs.getInt("itemId"));
	        
	        d.setInterval(new Interval());
	        d.getInterval().setId(rs.getInt("intervalId"));

	        d.setGroupEntry(new GroupEntry());
	        d.getGroupEntry().setId(rs.getInt("groupEntryId"));
	        return d;
	    }
	};	

	public synchronized int create(Data data, Audit audit) {
        String sql = "INSERT INTO " + DATA_TABLENAME + " (  itemId, IntervalId, companyId, groupEntryId) VALUES (?, ?, ?, ?)";
        
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // item id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // interval id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // company id
        su.declareParameter(new SqlParameter(Types.INTEGER)); // groupEntryId id
        Object[] parameters = new Object[] {data.getItem().getId(), 
        		                            data.getInterval().getId(), 
        		                            data.getCompany().getId(),
        		                            data.getGroupEntry().getId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        data.setId(keyHolder.getKey().intValue());

        SqlUpdate vu = new SqlUpdate();
        vu.setDataSource(getDataSource());
        sql = "INSERT INTO " + VALUE_TABLENAME + " (value, dataId, auditId, pricebaseId, confidenceGradeId, branchTagId) VALUES (?, ?, ?, ?, ?, ?)"; 
        
        vu.setSql(sql);
        vu.declareParameter(new SqlParameter(Types.VARCHAR)); // Value
        vu.declareParameter(new SqlParameter(Types.INTEGER)); // data id
        vu.declareParameter(new SqlParameter(Types.INTEGER)); // audit id
    	vu.declareParameter(new SqlParameter(Types.INTEGER)); // pricebase id
    	vu.declareParameter(new SqlParameter(Types.INTEGER)); // cg id
    	vu.declareParameter(new SqlParameter(Types.INTEGER)); // branchtag id
    	Integer cgId = null;
    	if (null != data.getConfidenceGrade()) {
    		cgId = data.getConfidenceGrade().getId();
    	}
    	Integer btId = data.getBranch().getId();
    	parameters = new Object[] {data.getValue(),
				   data.getId(),
				   audit.getId(),
				   null, 
				   cgId,
				   btId};
        vu.update(parameters);
        return data.getId();
	}

	@SuppressWarnings("deprecation")
	public synchronized void updateValue(Data data, Audit audit) {
		String sql = "INSERT INTO " + VALUE_TABLENAME + " (value, dataId, auditId, pricebaseId, confidenceGradeId, branchTagId) VALUES (?, ?, ?, ?, ?, ?)"; 
		
    	Integer cgId = null;
    	if (null != data.getConfidenceGrade()) {
    		cgId = data.getConfidenceGrade().getId();
    	}
    	Integer btId = data.getBranch().getId();
		getJdbcTemplate().update(sql,
                data.getValue(),
				data.getId(),
				audit.getId(),
				null, 
                cgId,
                btId);  
	}
	
	public synchronized Data findById(int id) {
		String sql = "SELECT d.*, v.*, null as runId, null as tagId FROM " + DATA_TABLENAME + " d INNER JOIN " + VALUE_TABLENAME+ " v ON v.dataId = d.id WHERE d.Id=? ORDER BY v.id DESC LIMIT 1";
		// TODO - locks
		
		// todo - data has to have a value - get the latest?
		return getJdbcTemplate().queryForObject(sql, DATA_MAPPER, id);
	}
	
	public synchronized void bulkUpdate(List<Data> list, Audit audit) {
		if (list.size()==0) return;
		
		// Add all the data records and get the ids
		Map<String, Integer> dataIds = getAllDataItems(list);
		
		// Break into blocks and add the blocks
		int size = list.size();
		int block = 2000;
		for (int i=0; i<size; i=i+block) {
			// Calculate where we need to end and how big the block actually is
			int end = i+(block-1);
			while (end>=size && block>1) {
				// Once the block size is bigger than the available 
				// records, half the size until the block fits
				block = block/2;
				if (block<1) block = 1;
				end = i+(block-1);
			}
			log.info("Saving block of " + block + " records: " + i + " to " + end);
	
			// Start the prepared statement for the bulk insert
            StringBuffer sql = new StringBuffer();
   		 	sql.append("INSERT INTO " + VALUE_TABLENAME + " ");
   		 	sql.append("(value, dataId, auditId, pricebaseId, confidenceGradeId, branchTagId) ");
   		 	sql.append("VALUES ");
   		 
   		 	// Start an array to hold data for all 6 fields in the actual block of rows 
   		 	Object[] values = new Object[block*6];
            int pos = 0;
   		 	for (int j=i; j<=end; j++) {
   		 		Data data = list.get(j);
   			 
   		 		// Append the slots for the data - separating by commas each group 
   		 		sql.append("(?,?,?,?,?,?)");
   		 		if (j!=end) {
   		 			sql.append(",");
   		 		}

   		 		// Get the supplementary ids
   		 		int dataId = dataIds.get(buildKey(data));
   		 		Integer pbId = null; // TODO Get the price base
   		 		Integer cgId = data.getConfidenceGrade()==null ? null : data.getConfidenceGrade().getId();
   		 		Integer btId = data.getBranch().getId();

   		 		values[pos++] = data.getValue();
   		 		values[pos++] = dataId;
   		 		values[pos++] = audit.getId();
   		 		values[pos++] = pbId; 
   		 		values[pos++] = cgId;
   		 		values[pos++] = btId;
   		 	}
   		 
            getJdbcTemplate().update(sql.toString(), values);

		}
	}

	/**
	 * Before we can create the values attached to the data, we
	 * must make sure that the data records are created.
	 * The returned map contains all data items, whether they existed
	 * already or we created by this method. They are keyed using the 
	 * item, interval and group IDs.
	 * Not it is assumed that the list is all for the same company. 
	 */
	private Map<String, Integer> getAllDataItems(List<Data> list) {
		Map<String, Integer> dataIds = new HashMap<String, Integer>();
		if (list.size()==0) return dataIds;
		
		// Build a distinct list of item ids
		int companyId = list.get(0).getCompany().getId();
		Set<Integer> itemIds = new HashSet<Integer>();
		for (Data data : list) {
			itemIds.add(data.getItem().getId());
		}
		log.info("Reading the data attached to " + itemIds.size() + " items(s) for company " + companyId);
		
		// For each code, get ALL data records and put them in a map based on
		// item, interval and group id. The map is based on a key built up of 
		// these ids
		for (int itemId : itemIds) {
			String sql = "SELECT * FROM " + DATA_TABLENAME + " " + 
 						 "WHERE companyId=? AND itemId=?";
			List<Data> dataList = getJdbcTemplate().query(sql, BASIC_DATA_MAPPER, companyId, itemId);
			for (Data data : dataList) {
				dataIds.put(buildKey(data), data.getId());
			}
		}
		
		// For each value in the list that DOES NOT have a data record, build this now
		// Add each data id to the map that we return
		int added = 0;
		log.info("Creating new data records");

		for (Data data : list) {
			String key = buildKey(data);
			if (!dataIds.containsKey(key)) {
				// Not created so build it now
		        String sql = "INSERT INTO " + DATA_TABLENAME + " (  itemId, IntervalId, companyId, groupEntryId) VALUES (?, ?, ?, ?)";
		        SqlUpdate su = new SqlUpdate();
		        su.setDataSource(getDataSource());
		        su.setSql(sql);
		        su.declareParameter(new SqlParameter(Types.INTEGER)); // item id
		        su.declareParameter(new SqlParameter(Types.INTEGER)); // interval id
		        su.declareParameter(new SqlParameter(Types.INTEGER)); // company id
		        su.declareParameter(new SqlParameter(Types.INTEGER)); // groupEntryId id
		        Object[] parameters = new Object[] {data.getItem().getId(), 
		        		                            data.getInterval().getId(), 
		        		                            data.getCompany().getId(),
		        		                            data.getGroupEntry().getId()};
		        KeyHolder keyHolder = new GeneratedKeyHolder();
				su.setReturnGeneratedKeys(true);
		        su.update(parameters, keyHolder);
		        int dataId = (keyHolder.getKey().intValue());
		        dataIds.put(key, dataId);
		        added++;
			}
		}
		
		log.info("Created " + added + " data record(s)");
		return dataIds;
	}
	
	private String buildKey(Data data) {
		return data.getItem().getId() + "-" +
		       data.getInterval().getId() + "-" + 
		       data.getGroupEntry().getId();
	}

	public synchronized Data getBranchData(int itemId, int intervalId, int companyId, int groupEntryId, int branchId) {
		// Usually this method will not be used. Data should already be in the cache.
		log.warn("getBranchData() method is being used to get an individual piece of latest data. itemId: " + itemId + " yearId: " + intervalId + " companyId: " + companyId + " groupEntryId: " + groupEntryId + " branchId: " + branchId + new Date(System.currentTimeMillis()));
		Data data=null;
		String sql = "SELECT  *, null as runId, null as tagId FROM " + DATA_TABLENAME + " d INNER JOIN (" + VALUE_TABLENAME + " v INNER JOIN " + AUDIT__TABLENAME + " a ON v.auditId = a.id INNER Join " + BRANCH_TAG_TABLENAME + " b ON v.branchTagId = b.id) ON d.id = v.dataId WHERE d.itemId=? and d.IntervalId=? and d.CompanyId=? and d.groupEntryId = ? and b.id = ? order by a.timestamp desc, v.id desc limit 1";
		Object[] params = new Object[]{itemId, intervalId,  companyId, groupEntryId, branchId};
		try {
			data = getJdbcTemplate().queryForObject(sql, DATA_MAPPER, params);
		} catch(EmptyResultDataAccessException dae){
			// it's ok to have no data
			return null;
		}

	    return data;
	}
	
	public List<Data> getDataForAudit(Audit audit) {
		String sql = "SELECT *, null as runId, null as tagId FROM " + DATA_TABLENAME + " d INNER JOIN "+ VALUE_TABLENAME + " v ON d.id = v.dataId WHERE v.AuditId = ?";
		return getJdbcTemplate().query(sql, DATA_MAPPER, audit.getId());
	}

	@Override
	public List<Data> getReportData(List<Integer> branchIds, List<Integer> companyIds, List<Integer> itemIds, List<Integer> intervalIds, List<Integer> groupEntryIds) {
		if (null == branchIds || null == companyIds || null == itemIds || null == intervalIds || null == groupEntryIds ||
			branchIds.isEmpty() || companyIds.isEmpty() || itemIds.isEmpty() || intervalIds.isEmpty() || groupEntryIds.isEmpty()) {
			return new ArrayList<Data>();
		}
		
		List<Data> data = null;
		String branchCDLString = integerListToCDLString(branchIds);
		String companyCDLString = integerListToCDLString(companyIds);
		String itemCDLString = integerListToCDLString(itemIds);
		String intervalCDLString = integerListToCDLString(intervalIds);
		String groupEntryCDLString = integerListToCDLString(groupEntryIds);
		log.debug("branch     : " + branchCDLString);
		log.debug("company    : " + companyCDLString);
		log.debug("item       : " + itemCDLString);
		log.debug("interval   : " + intervalCDLString);
		log.debug("groupEntry : " + groupEntryCDLString);

		String sql = "SELECT *, null as runId, null as tagId FROM tbl_data d " + 
				"INNER JOIN tbl_value v " +
				"ON d.id = v.dataId " +
				"INNER JOIN tbl_audit a " +  
				"ON v.auditId = a.id  " +
				"inner join (  select max(a2.id) 'auditId', d2.id 'dataId' from tbl_data d2 " + 
				"  INNER JOIN tbl_value v2 " +
				"  ON d2.id = v2.dataId " +
				"  INNER JOIN tbl_audit a2 " + 
				"  ON v2.auditId = a2.id " +
				"  WHERE d2.CompanyId in ( " + companyCDLString + ") " + 
				"  and v2.branchTagId in (" + branchCDLString + ") " +
				"  group by d2.id " +
				") ss " +
				"ON a.id = ss.auditId and d.id = ss.dataId " +
				"WHERE v.branchTagId in (" + branchCDLString + ") " +
				"and d.CompanyId in (" + companyCDLString + ") " +
				"and d.itemId in (" + itemCDLString + ") " + 
				"and d.intervalId in (" + intervalCDLString + ") " +
				"and d.groupEntryId in (" + groupEntryCDLString + ") ";
		Object[] params = new Object[]{};
		log.debug("query    : " + sql.toString());
	    try{
	    	data = getJdbcTemplate().query(sql.toString(), DATA_MAPPER);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    }
        return data;
	}
	
	public List<Data> getTagDataForBranch(int branchId, int tagId) {
		List<Data> data = null;
		
		String sql = "SELECT " + 
					"		d.id as id, " + 
					"		d.itemId AS itemId,  " +
					"		d.intervalId AS intervalId, " +
					"		v.value AS value,  " +
					"	    v.branchTagId as branchTagId, " + 
					"	    d.companyId AS companyId,  " +
					"		v.id, " + 
					"		v.confidenceGradeId, " + 
					"	    d.groupEntryId AS groupEntryId, " +
					"	    v.confidenceGradeId as confidenceGradeId, " +
					"	    b.runId as runId, " +
					"	    t.id as tagId " +
					"	from tbl_value v " +
					"	inner join tbl_data d on d.id = v.dataId " + 
					"	inner join tbl_branch_tag b on b.id = v.branchTagId " +
					"	inner join tbl_run_model_company_tag t on t.id = " + tagId +
					"	where v.id in (  " +
					"		select max(v1.id) from tbl_value v1 " +
					"		where branchTagId = " +  branchId +
					"		and auditId <= (select id from tbl_audit where tbl_audit.timestamp <= (select dateCreated from tbl_run_model_company_tag where id = " + tagId + ") order by id desc limit 1)" +
					"		group by dataId  " +
					"	)";
		Object[] params = new Object[]{};
		log.debug("getTagData() query    : " + sql.toString());
	    try{
	    	data = getJdbcTemplate().query(sql.toString(), DATA_MAPPER);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    }
        return data;
	}
	
	
	private String integerListToCDLString(List<Integer> ids) {
		StringBuffer idsCDL = new StringBuffer("");
		boolean firstFlag = true;
		for (Integer id: ids) {
			if (firstFlag) {
				firstFlag = false;
			}
			else {
				idsCDL.append(", ");
			}
			idsCDL.append(id);
		}
		return idsCDL.toString();
	}

	public synchronized Data getDataForValueId(int valueId) {
		String sql = "SELECT * , null as runId, null as tagId FROM tbl_data d " + 
					"INNER JOIN tbl_value v " +
					"ON d.id = v.dataId " +
					"WHERE v.id = ? ";
		Object[] params = new Object[]{valueId};
		Data data = null;
	    try{
			data = getJdbcTemplate().queryForObject(sql, DATA_MAPPER, params);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    	//TODO is it ok to have no data? 
	    }
		return data;
	}

	@Override
	public synchronized List<Data> getDataForValueIds(ArrayList<Integer> valueIdList) {
		String valueCDLString = integerListToCDLString(valueIdList);
		String sql = "SELECT *, null as runId, null as tagId FROM tbl_data d " + 
				"INNER JOIN tbl_value v " +
				"ON d.id = v.dataId " +
				"WHERE v.id in (" + valueCDLString + ") ";
		List<Data> data = null;
	    try{
			data = getJdbcTemplate().query(sql, DATA_MAPPER);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    	//TODO is it ok to have no data? 
	    }
		return data;
	}

	public synchronized void removeFromCacheForValueId(int valueId) {
		// We never actually delete data!!!!! This method must never be implemented!
		// This method is just here to trigger the removal from cache.
	}

	public void addToCache(Map<String, Object> dataMap) {
		// This method is just here to trigger a cache operation.
	}
	public Boolean isDataInCache(String key) {
		// This method is just here to trigger a cache operation.
		return false;
	}

	public Data getTaggedData(int itemId, int yearId, int companyId, int groupEntryId, int runId, long tagId) {
		// Note: We could implement this if needed, as long as its not used regularly.
		throw new UnsupportedOperationException("Invalid attempt to get tagged data. itemId: " + itemId + " yearId: " + yearId + " companyId: " + companyId + " groupEntryId: " + groupEntryId + " runId: " + runId + " tagId: " + tagId);
	}

	@Override
	public void addToTaggedCache(Map<String, Object> dataMap) {
		// This method is just here to trigger a cache operation.
	}

}

