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

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.form.ModelPage;

public class ModelPageDaoImpl extends SimpleJdbcDaoSupport  implements ModelPageDao {    
	
	private TableDao tableDao;
	private ModelDao modelDao;

	private static final String MODEL_PAGE_TABLE_NAME = "tbl_model_page";
	
	public TableDao getTableDao() {
		return tableDao;
	}
	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}
	public ModelDao getModelDao() {
		return modelDao;
	}
	public void setModelDao(ModelDao modelDao) {
		this.modelDao = modelDao;
	}

	private final RowMapper<ModelPage> ROW_MAPPER = new RowMapper<ModelPage>() {
	    public ModelPage mapRow(ResultSet rs, int rowNum) throws SQLException {
	        int id = rs.getInt("id");
	    	int tableId = rs.getInt("tableId");
	    	Table table = tableDao.findById(tableId);
	    	String tableDescription = rs.getString("tableDescription");
	    	int modelId = rs.getInt("modelId");
	    	Model model = modelDao.findById(modelId);
	    	boolean groupSelect = rs.getBoolean("groupSelect");
	    	String companyType = rs.getString("companyType");
	    	int cornerRowSpan = rs.getInt("cornerRowSpan");
	    	int cornerColSpan = rs.getInt("cornerColSpan");

	    	ModelPage mp = new ModelPage();
	    	mp.setId(id);
	    	mp.setTable(table);
	    	mp.setTableDescription(tableDescription);
	    	mp.setModel(model);
	    	mp.setGroupSelect(groupSelect);
	    	mp.setCompanyType(companyType);
	    	mp.setCornerRowSpan(cornerRowSpan);
	    	mp.setCornerColSpan(cornerColSpan);
	        return mp;
	    }
	};	
	
    public int create(ModelPage modelPage) {
   		String sql = "INSERT INTO " + MODEL_PAGE_TABLE_NAME + " (tableId, tableDescription, modelId, groupSelect, companyType, cornerRowSpan, cornerColSpan) VALUES (?,?,?,?,?,?,?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // tableId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // tableDescription
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // groupSelect
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // companyType
        su.declareParameter(new SqlParameter(Types.INTEGER)); // cornerRowSpan
        su.declareParameter(new SqlParameter(Types.INTEGER)); // cornerColSpan
        Object[] parameters = new Object[] {modelPage.getTable().getId(), modelPage.getTableDescription(), modelPage.getModel().getId(), 
        									modelPage.isGroupSelect(), modelPage.getCompanyType(), modelPage.getCornerRowSpan(), modelPage.getCornerColSpan()};
        KeyHolder keyHolder = new GeneratedKeyHolder();  
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        modelPage.setId(keyHolder.getKey().intValue());
        return modelPage.getId();
	}

	public ModelPage findById(int id) {
        String sql = "SELECT * FROM " + MODEL_PAGE_TABLE_NAME + " WHERE ID=?";
        ModelPage modelPage = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
        return modelPage;
	}
	
	public ModelPage findByTableId(int tableId) {
        String sql = "SELECT * FROM " + MODEL_PAGE_TABLE_NAME + " WHERE tableId=?";
        ModelPage modelPage = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, tableId);
        return modelPage;
	}
	
}
