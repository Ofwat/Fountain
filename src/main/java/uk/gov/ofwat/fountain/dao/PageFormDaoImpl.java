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
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;

public class PageFormDaoImpl extends JdbcDaoSupport implements
		PageFormDao {
	private static final String TABLE_NAME = "tbl_page_form";
	
	private PageSectionDao pageSectionDao;
	
	public void setPageSectionDao(PageSectionDao pageSectionDao) {
		this.pageSectionDao = pageSectionDao;
	}

	private final RowMapper<PageForm> ROW_MAPPER = new RowMapper<PageForm>() {
	    public PageForm mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	PageForm pf = new PageForm();
	    	pf.setId(rs.getInt("id"));
	     	PageSection ps = new PageSection();
	    	ps = pageSectionDao.findById(rs.getInt("pageSectionId"));
	    	pf.setPageSection(ps);
	    	pf.setCompanyType(rs.getString("companyType"));
	    	pf.setRows(rs.getInt("rows"));
	    	pf.setCols(rs.getInt("cols"));
	    	return pf;
	    }
	};	
	
	public PageForm findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	public int create(PageForm pageForm) {
		String sql = "INSERT INTO " + TABLE_NAME + " (pageSectionId, companyType, rows, cols) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // pageSectionId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // companyType
        su.declareParameter(new SqlParameter(Types.INTEGER)); // rows
        su.declareParameter(new SqlParameter(Types.INTEGER)); // cols
        int pageSectionId = 0;
        if (pageForm.getPageSection()!=null){
        	pageSectionId = pageForm.getPageSection().getId();
        }
       
        Object[] parameters = new Object[] {pageSectionId, pageForm.getCompanyType(), pageForm.getRows(), pageForm.getCols()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        pageForm.setId(keyHolder.getKey().intValue());
        return pageForm.getId();
	}

	public List<PageForm> findByPageSectionId(int pageSectionId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE pageSectionId = ?";
		return getJdbcTemplate().query(sql, ROW_MAPPER, pageSectionId);
	}

}
