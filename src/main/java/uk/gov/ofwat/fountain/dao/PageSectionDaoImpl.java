/*
 *  Copyright (C) 2011 Water Services Regulation Authority (Ofwat)
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

import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageSection;

public class PageSectionDaoImpl extends SimpleJdbcDaoSupport implements PageSectionDao {
	private static final String TABLE_NAME = "tbl_page_section";
	
	private ModelPageDao modelPageDao;

	public ModelPageDao getModelPageDao() {
		return modelPageDao;
	}

	public void setModelPageDao(ModelPageDao modelPageDao) {
		this.modelPageDao = modelPageDao;
	}


	private final RowMapper<PageSection> ROW_MAPPER = new RowMapper<PageSection>() {

		public PageSection mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	PageSection ps = new PageSection(); // TODO remove default constructor?
	    	ps.setId(rs.getInt("id"));
	    	ModelPage modelPage = modelPageDao.findById(rs.getInt("modelPageId"));
	    	ps.setModelPage(modelPage);
	    	ps.setGrouped(rs.getBoolean("grouped"));
	    	ps.setSectionType(rs.getString("sectionType"));
	    	ps.setCode(rs.getString("code"));
	    	ps.setItemCodeColumn(rs.getInt("itemCodeColumn"));
	    	return ps;
	    }
	};	
	
	public int create(PageSection pageSection) {
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.declareParameter(new SqlParameter(Types.INTEGER)); // modelPageId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // grouped
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // sectionType
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
        
        int modelPageId = 0;
        if (null!=pageSection.getModelPage()){
        	modelPageId = pageSection.getModelPage().getId();
        }

        String sql = "INSERT INTO " + TABLE_NAME + " (modelPageId, grouped, sectionType, code) VALUES (?, ?, ?, ?)";
        Object[] parameters = new Object[] {modelPageId, pageSection.isGrouped(), (null == pageSection.getSectionType()) ? "ordered" : pageSection.getSectionType(), pageSection.getCode()};
        if (null != pageSection.getSectionType() &&
        	(pageSection.getSectionType().equals("unorderedItemPrefix") ||
        	 pageSection.getSectionType().equals("unordered"))) {
        	// The system needs an itemCodeColumn
        	if (pageSection.getItemCodeColumn() == null) {
        		// Oh dear there is no itemCodeColumn.  
        		pageSection.setItemCodeColumn(2); // set default
        	}
	        sql = "INSERT INTO " + TABLE_NAME + " (modelPageId, grouped, sectionType, code, itemCodeColumn) VALUES (?, ?, ?, ?, ?)";
	        su.declareParameter(new SqlParameter(Types.INTEGER)); // itemCodeColumn
	        parameters = new Object[] {modelPageId, pageSection.isGrouped(), (null == pageSection.getSectionType()) ? "ordered" : pageSection.getSectionType(), pageSection.getCode(), pageSection.getItemCodeColumn()};
        }
        su.setSql(sql);

        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        pageSection.setId(keyHolder.getKey().intValue());
        return pageSection.getId();
	}

	public PageSection findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public List<PageSection> findByModelPageId(int modelPageId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE modelPageId = ?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, modelPageId);
	}

}
