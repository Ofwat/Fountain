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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

public class ModelPropertiesMapDaoImpl extends JdbcDaoSupport  implements ModelPropertiesMapDao {

	private ItemPropertiesDao itemPropertiesDao;
	private ItemPropertyIntervalDao itemPropertyIntervalDao;
	
	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}
	public void setItemPropertyIntervalDao(
			ItemPropertyIntervalDao itemPropertyIntervalDao) {
		this.itemPropertyIntervalDao = itemPropertyIntervalDao;
	}


	private static final RowMapper<ModelPropertiesMap> ROW_MAPPER = new RowMapper<ModelPropertiesMap>() {
	    public ModelPropertiesMap mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int id = rs.getInt("id");
	    	int modelId = rs.getInt("modelId");
	    	int itemId = rs.getInt("itemId");
	    	int itemPropertiesId = rs.getInt("itemPropertiesId");
	    	String itemCode = rs.getString("itemCode");
	     	ModelPropertiesMap m = new ModelPropertiesMap(id, modelId, itemId, itemCode, itemPropertiesId);
	        return m;
	    }
	};	
	
	private final RowMapper<ModelItem> MODELITEM_ROW_MAPPER = new RowMapper<ModelItem>() {
	    public ModelItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ModelItem mi = new ModelItem();
	    	mi.setItemCode(rs.getString("itemCode"));
	    	int itemId = rs.getInt("itemId");
	    	mi.setItemId(itemId);
	    	mi.setItemName(rs.getString("itemName"));
	    	mi.setModelCode(rs.getString("modelCode"));
	    	int modelId = rs.getInt("modelId");
	    	mi.setModelId(modelId);
	    	mi.setModelName(rs.getString("modelName"));
	    	mi.setModelPropertiesMapId(rs.getInt("modelPropertiesMapId"));
			ItemProperties itemProperties = itemPropertiesDao.findByItemAndModel(itemId, modelId);  
	    	mi.setDescription(itemProperties.getDescription());
	    	
	    	return mi;
	    }
	};	
	
	public int create(ModelPropertiesMap modelPropertiesMap) {
        String sql = "INSERT INTO tbl_modelpropertiesmap (modelid, itemid, itemCode, ItemPropertiesId) VALUES (?,?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ModelId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ItemId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // ItemCode
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ItemPropertiesId
        Object[] parameters = new Object[] {modelPropertiesMap.getModelId(),
        		                            modelPropertiesMap.getItemId(),
        		                            modelPropertiesMap.getItemCode(),
        		                            modelPropertiesMap.getItemPropertiesId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        modelPropertiesMap.setId(keyHolder.getKey().intValue());
        return modelPropertiesMap.getId();
	}

	public ModelPropertiesMap findByModelAndItem(int modelId, int itemId) {
        String sql = "SELECT * FROM tbl_modelpropertiesmap WHERE modelId=? and ItemID=?";
        ModelPropertiesMap mpm = null;
        try {
        	mpm = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, modelId, itemId);
		} catch (DataAccessException e) {
			// Deliberate behaviour. Returns null if no code found 
		}
        return mpm;
	}

	public HashMap<Integer, ItemProperties> getAllForModel(int modelId) {
		String sql = "SELECT * FROM tbl_modelpropertiesmap WHERE modelId=?";
		List<ModelPropertiesMap> mpmList = getJdbcTemplate().query(sql, ROW_MAPPER, modelId);
		
		HashMap<Integer, ItemProperties> retval = new HashMap<Integer, ItemProperties>();

		for (ModelPropertiesMap modelPropertiesMap : mpmList) {
			
			ItemProperties ip = itemPropertiesDao.findById(modelPropertiesMap.getItemPropertiesId());
			List<ItemPropertyInterval> ipis = itemPropertyIntervalDao.getIntervalPropertiesForItemPropertyId(ip.getId());
	        for(ItemPropertyInterval ipi: ipis){
	        	ip.getFormulae().put(ipi.getInterval(), ipi);
	        }
			retval.put(modelPropertiesMap.getItemId(), ip);
		}
		
		return retval;
	}

	public void update(ModelPropertiesMap modelPropertiesMap) {
		throw new RuntimeException("Not yet implemented");

	}
	public List<ModelItem> searchByCodeOrDescription(String criteria,
			int[] modelFilters) {

		StringBuffer sqlQuery= new StringBuffer("SELECT i.Id itemId, i.Code itemCode, i.Name itemName, " +
				"mpm.modelId modelId, mpm.id modelPropertiesMapId, m.Name modelName, m.code modelCode, ip.Description " +
				"FROM    (   (   tbl_modelpropertiesmap mpm " +
				"INNER JOIN tbl_itemproperties ip " +
				"ON (mpm.itemPropertiesId = ip.Id))" +
				"INNER JOIN tbl_item i ON (ip.ItemId = i.Id) " +
				"AND (mpm.itemId = i.Id)) " +
				"INNER JOIN tbl_model m ON (mpm.modelId = m.Id) " +
				"WHERE (i.Name LIKE ? or i.Code like ? or ip.Description like ?) " +
		"AND (mpm.modelId IN (");
		// INSERT the model ids
		int len = modelFilters.length;
		for(int i = 0; i < (len - 1); i++ ){
			sqlQuery.append(modelFilters[i]);
			sqlQuery.append(", ");
		}
		sqlQuery.append(modelFilters[len-1]);
		sqlQuery.append(")) " +
//				"	and ip.version in (SELECT max(version) " +
//				"   FROM tbl_itemproperties p2 WHERE p2.itemId = i.id) "+
				"GROUP BY i.Id");
//		List<ModelItem> tmp = getJdbcTemplate().query(sqlQuery.toString(), MODELITEM_ROW_MAPPER, "%" + criteria  + "%", "%" + criteria  + "%", "%" + criteria  + "%");
		return getJdbcTemplate().query(sqlQuery.toString(), MODELITEM_ROW_MAPPER, "%" + criteria  + "%", "%" + criteria  + "%", "%" + criteria  + "%");
	}

	public List<ModelItem> searchByLatestDefinitions(String criteria,
			int[] modelFilters) {

		StringBuffer sqlQuery= new StringBuffer("SELECT i.Id itemId, i.Code itemCode, i.Name itemName, " +
				"i.GroupID, mpm.modelId, mpm.id modelPropertiesMapId,  m.Name modelName, m.Code modelCode, ip.Description,ip.Definition " +
				"FROM ((tbl_modelpropertiesmap mpm INNER JOIN tbl_itemproperties ip " +
				"ON (mpm.itemPropertiesId = ip.Id)) " +
				"INNER JOIN tbl_item i ON (ip.ItemId = i.Id) AND (mpm.itemId = i.Id)) " +
				"INNER JOIN tbl_model m ON (mpm.modelId = m.Id) " +
				"WHERE (i.Name LIKE ? or i.Code like ? or ip.Description like ? or ip.Definition like ?)" +
		"AND (mpm.modelId IN (");
		// INSERT the model Ids
		int len = modelFilters.length;
		for(int i = 0; i < (len - 1); i++ ){
			sqlQuery.append(modelFilters[i]);
			sqlQuery.append(", ");
		}
		sqlQuery.append(modelFilters[len-1]);
		sqlQuery.append(")) " +
//				"	and ip.version in (SELECT max(version) " +
//				"   FROM tbl_itemproperties p2 WHERE p2.itemId = i.id) "+
				"GROUP BY i.Id");
		
		return getJdbcTemplate().query(sqlQuery.toString(), MODELITEM_ROW_MAPPER, "%" + criteria  + "%", "%" + criteria  + "%", "%" + criteria  + "%", "%" + criteria  + "%");
	}

	
}
