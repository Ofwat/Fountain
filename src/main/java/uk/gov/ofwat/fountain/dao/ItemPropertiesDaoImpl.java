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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;

/**
 * Not cachable as it contains item
 */
public class ItemPropertiesDaoImpl extends SimpleJdbcDaoSupport implements
		ItemPropertiesDao {

	private static final String TABLE_NAME = "tbl_itemproperties";
	private static final String MODELMAP_TABLE_NAME = "tbl_modelpropertiesmap";
	private static final String ITEM_TABLE_NAME = "tbl_item";
	
	private ItemDao itemDao;
	private PurposeDao purposeDao;
	private PriceBaseDao priceBaseDao;
	private InflationTypeDao inflationTypeDao;
	private IntervalDao intervalDao;

	private Map<Integer, Interval> intervalMap;
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public InflationTypeDao getInflationTypeDao() {
		return inflationTypeDao;
	}
	public void setInflationTypeDao(InflationTypeDao inflationTypeDao) {
		this.inflationTypeDao = inflationTypeDao;
	}
	public PurposeDao getPurposeDao() {
		return purposeDao;
	}
	public void setPurposeDao(PurposeDao purposeDao) {
		this.purposeDao = purposeDao;
	}
	public PriceBaseDao getPriceBaseDao() {
		return priceBaseDao;
	}
	public void setPriceBaseDao(PriceBaseDao priceBaseDao) {
		this.priceBaseDao = priceBaseDao;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}

	private final RowMapper<ItemProperties> ROW_MAPPER = new RowMapper<ItemProperties>() {
	    public synchronized ItemProperties mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	int version = rs.getInt("version");
	    	String desc = rs.getString("description");
	    	boolean fixedFormula = rs.getBoolean("fixedFormula");
	    	String generalFormula = rs.getString("generalFormula");
	    	int decimalPlaces = rs.getInt("dp"); 
	    	String definition = rs.getString("definition");
	    	String groupTotalFormula = rs.getString("groupTotalFormula");
	    	Item item = itemDao.findById(rs.getInt("itemId"));
	    	Map<Interval, ItemPropertyInterval>formulae = new HashMap<Interval, ItemPropertyInterval>();
	    	int reservoirVersion = rs.getInt("reservoirVersion");
	    	boolean attachedToModel = rs.getBoolean("attachedToModel");
	    	
	    	ItemProperties ip = new ItemProperties(item, version, desc, definition, decimalPlaces, formulae, fixedFormula, generalFormula, groupTotalFormula, reservoirVersion, attachedToModel);
	    	ip.setId(rs.getInt("ID"));
		        
	    	int purposeId = rs.getInt("purposeId");
	    	if (purposeId>0){
	    		ip.setPurpose(purposeDao.findById(purposeId));
	    	}
	    	int priceBaseId = rs.getInt("priceBaseId");
	    	if (priceBaseId>0){
	    		ip.setPriceBase(priceBaseDao.findById(priceBaseId));
	    	}
	    	int inflationTypeId = rs.getInt("inflationTypeId");
	    	if (inflationTypeId>0){
	    		ip.setInflationType(inflationTypeDao.findById(inflationTypeId));
	    	}
	        return ip;
	    }
	};	

	private final RowMapper<ItemProperties> IP_ROW_MAPPER = new RowMapper<ItemProperties>() {
	    public synchronized ItemProperties mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	int version = rs.getInt("version");
	    	String desc = rs.getString("description");
	    	boolean fixedFormula = rs.getBoolean("fixedFormula");
	    	String generalFormula = rs.getString("generalFormula");
	    	String groupTotalFormula = rs.getString("groupTotalFormula");
	    	int decimalPlaces = rs.getInt("dp"); 

	    	int itemId = rs.getInt("itemId");
	    	String itemCode = rs.getString("itemCode");

	    	Map<Interval, ItemPropertyInterval>formulae = new HashMap<Interval, ItemPropertyInterval>();
	    	int reservoirVersion = rs.getInt("reservoirVersion");
	    	boolean attachedToModel = rs.getBoolean("attachedToModel");

	    	
	    	Item item = new Item();
	    	item.setId(itemId);
	    	item.setCode(itemCode);

	    	ItemProperties ip = new ItemProperties(item, version, desc, null, decimalPlaces, formulae, fixedFormula, generalFormula, groupTotalFormula, reservoirVersion, attachedToModel);
	    	ip.setId(rs.getInt("ID"));		        
	        return ip;
	    }
	};	

	private final RowMapper<ItemPropertyInterval> IPI_ROW_MAPPER = new RowMapper<ItemPropertyInterval>() {
	    public synchronized ItemPropertyInterval mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int intervalId = rs.getInt("intervalId");
	        Interval interval = getInterval(intervalId);
	        
	        ItemPropertyInterval ipi = new ItemPropertyInterval();
	        ipi.setInterval(interval);
	        
	        ipi.setFormula(rs.getString("formula"));
	        ipi.setId(rs.getInt("id"));
	        
	        ItemProperties ip = new ItemProperties(null, 0, null, null, 0, null, false, null, null, 0, false);
	        ip.setId(rs.getInt("itemPropertyId"));
	        ipi.setItemProperties(ip);
	        return ipi;
	    }
	};	

	public int create(ItemProperties itemProperties) {
		String sql = "INSERT INTO " + TABLE_NAME + " (" + 
					 "`itemId`, `version`, `description`, `definition`, `dp`, `fixedFormula`, `generalFormula`, `purposeId`, " +
					 "`pricebaseId`, `inflationTypeId`, `groupTotalFormula`, `reservoirVersion`, `attachedToModel`) " +
					 "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		
		su.declareParameter(new SqlParameter(Types.INTEGER)); // ItemId 
		su.declareParameter(new SqlParameter(Types.INTEGER)); // Version
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // Description
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // definition
		su.declareParameter(new SqlParameter(Types.INTEGER)); // precision
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // FixedFormula
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // Forumla
		su.declareParameter(new SqlParameter(Types.INTEGER)); // purposeId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // pricebaseId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // inflationtypeId
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // groupTotalFormula
		su.declareParameter(new SqlParameter(Types.INTEGER)); // reservoirVersion
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // attachedToModel

		
		Object[] parameters = new Object[] {itemProperties.getItem().getId(), 
				                            itemProperties.getVersion(), 
				                            itemProperties.getDescription(),
				                            itemProperties.getDefinition(),
				                            itemProperties.getDecimalPlaces(),
				                            itemProperties.isFixedFormula(),
				                            itemProperties.getGeneralFormula(),
				                            itemProperties.getPurpose()==null?null:itemProperties.getPurpose().getId(),
				                            itemProperties.getPriceBase()==null?null:itemProperties.getPriceBase().getId(),
				                            itemProperties.getInflationType()==null?null:itemProperties.getInflationType().getId(), 
				                            itemProperties.getGroupTotalFormula(),
				                            itemProperties.getReservoirVersion(),
				                            itemProperties.isAttachedToModel()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		itemProperties.setId(keyHolder.getKey().intValue());
		return itemProperties.getId();

	}
	

	public void update(ItemProperties itemProperties) {
		String sql = "UPDATE " + TABLE_NAME + " SET " + 
					 "description=?, " + 
					 "definition=?, " + 
					 "generalFormula=?, " +
					 "dp=?, " + 
					 "groupTotalFormula=?, " +
					 "reservoirVersion=?, " + 
					 "attachedToModel=?";
		getSimpleJdbcTemplate().update(sql, itemProperties.getDescription(), 
											itemProperties.getDefinition(), 
											itemProperties.getGeneralFormula(),
											itemProperties.getDecimalPlaces(),
											itemProperties.getGroupTotalFormula(),
											itemProperties.getReservoirVersion(),
											itemProperties.isAttachedToModel());
	}

	public ItemProperties findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public List<ItemProperties> getAllForItemId(int itemId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE itemid=?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, itemId);
	}

	public ItemProperties getLatestVersionForItemId(int itemId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE itemid=? and version = (select max(version) from tbl_itemproperties where itemid = ?)";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, itemId, itemId);
	}	
	
	public ItemProperties findByItemAndModel(int itemId, int modelId) {	
		String sql = "SELECT ip.id," +
		" ip.itemId," +
		" ip.version," +
		" ip.description," +
		" ip.definition," +
		" ip.fixedFormula," +
		" ip.generalFormula," +
		" ip.purposeId," +
		" ip.priceBaseId," +
		" ip.inflationTypeId," +
		" ip.dp," +
		" ip.groupTotalFormula," +
		" ip.reservoirVersion," +
		" ip.attachedToModel FROM " + MODELMAP_TABLE_NAME + " mpm " +
		"INNER JOIN " + TABLE_NAME + " ip ON (mpm.itemPropertiesId = ip.id) " +
		"WHERE (mpm.modelId = ?) "  +
		"AND (mpm.itemId = ?)";

		ItemProperties ip = null;
		try {
			ip = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, modelId, itemId);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    }
		return ip;
	}

	public ItemProperties findByItemAndModel(String itemCode, int modelId) {
	
		// use the other dao
		String sql = "SELECT ip.id," +
		" ip.itemId," +
		" ip.version," +
		" ip.description," +
		" ip.definition," +
		" ip.fixedFormula," +
		" ip.generalFormula," +
		" ip.purposeId," +
		" ip.priceBaseId," +
		" ip.inflationTypeId," +
		" ip.dp," +
		" ip.groupTotalFormula," +
		" ip.reservoirVersion," +
		" ip.attachedToModel " +
				"FROM " + MODELMAP_TABLE_NAME + " mpm " +
		"INNER JOIN " + TABLE_NAME + " ip ON (mpm.itemPropertiesId = ip.id) " +
		"WHERE (mpm.modelId = ?) "  +
		"AND (mpm.itemCode = ?)";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, modelId, itemCode);
		//String sql = "select itemPropertiesId from " + MODELMAP_TABLE_NAME + " where modelId=? and itemCode=?";
		//int propertiesId = 0;
		//propertiesId = getSimpleJdbcTemplate().queryForInt(sql, modelId, itemCode);
		//return findById(propertiesId);		
	}
	
	
	public ItemProperties findByItemCodeAndVersion(String itemCode, int version) {
		
		String sql = "SELECT p.* FROM " + TABLE_NAME + " p INNER JOIN " + ITEM_TABLE_NAME + " i ON p.itemId = i.id WHERE i.code=? AND p.version = ?";
		try {
			return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, itemCode, version);
	    }
	    catch(EmptyResultDataAccessException erdae){
	    	// ok to have no data
	    	return null;
	    }
	}
	
	
	public List<ItemProperties> getForCodes(String[] codes) {
		clearCache();
		
		// Read all the itemproperties in 500s
		int pos = 0;
		List<ItemProperties> allIps = new Vector<ItemProperties>();
		
		int size = 500;
		while (pos<codes.length) {
			
			StringBuffer b = new StringBuffer();
			b.append("SELECT "); 
			b.append("  i.id AS itemId, i.code AS ItemCode, "); 
			b.append("  ip.id, ip.version, ip.reservoirVersion, ip.attachedToModel, ");
			b.append("  ip.description, ip.fixedFormula, ip.generalFormula, ");
			b.append("  ip.dp, ip.groupTotalFormula ");
			b.append("FROM tbl_item AS i ");
			b.append("INNER JOIN tbl_itemproperties AS ip ON i.id=ip.itemId ");
			b.append("WHERE i.code IN(");
			for (int i=0; i<size && pos<codes.length; i++) {
				if (i>0) b.append(",");
				b.append("\"" + codes[pos] + "\"");
				pos++;
			}
			b.append(");");
			String sql = b.toString();
			List<ItemProperties> ips = getSimpleJdbcTemplate().query(sql, IP_ROW_MAPPER);
			allIps.addAll(ips);
		}

		// Map properties by ID
		Map<Integer, ItemProperties> ipMap = new HashMap<Integer, ItemProperties>();
		for (ItemProperties ip : allIps) {
			ipMap.put(ip.getId(), ip);
		}
		
		// Now we need to bulk read the intervals - in 500's
		pos = 0;
		while (pos<allIps.size()) {
			StringBuffer b = new StringBuffer();
			b.append("SELECT ");
			b.append("  i.id, i.formula, i.itemPropertyId, i.intervalId ");
			b.append("FROM tbl_itempropertyinterval AS i ");
			b.append("WHERE i.itemPropertyId IN(");
			for (int i=0; i<size && pos<allIps.size(); i++) {
				if (i>0) b.append(",");
				b.append(allIps.get(pos).getId());
				pos++;
			}
			b.append(");");
			String sql = b.toString();
			List<ItemPropertyInterval> ipis = getSimpleJdbcTemplate().query(sql, IPI_ROW_MAPPER);
			
			// Make sure that we cross map all intervals and properties
			for (ItemPropertyInterval ipi : ipis) {
				ItemProperties ip = ipMap.get(ipi.getItemProperties().getId());
				ipi.setItemProperties(ip);
				
				ip.getFormulae().put(ipi.getInterval(), ipi);
			}
		}
		
		
		return allIps;
	}

	public void attachToModel(int itemPropertiesId) {
		String sql = "UPDATE " + TABLE_NAME + " SET attachedToModel=1 WHERE id=?";
		getSimpleJdbcTemplate().update(sql, itemPropertiesId);
	}
	
	private void clearCache() {
		intervalMap = new HashMap<Integer, Interval>();
	}
	private Interval getInterval(int id) {
		if (intervalMap.containsKey(id)) {
			return intervalMap.get(id);
		}
		else {
			Interval interval = intervalDao.findById(id);
			intervalMap.put(id, interval);
			return interval;
		}
		
	}
}
