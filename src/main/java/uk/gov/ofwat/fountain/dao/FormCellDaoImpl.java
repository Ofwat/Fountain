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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.form.FormDataCell;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.FormEmptyCell;
import uk.gov.ofwat.fountain.domain.form.FormHeaderCell;
import uk.gov.ofwat.fountain.domain.form.PageForm;

public class FormCellDaoImpl extends SimpleJdbcDaoSupport implements FormCellDao {

	private static final String TABLE_NAME = "tbl_form_cell";
	private PageFormDao pageFormDao;
	private ModelDao modelDao;
	
	public void setPageFormDao(PageFormDao pageFormDao) {
		this.pageFormDao = pageFormDao;
	}

	public void setModelDao(ModelDao modelDao) {
		this.modelDao = modelDao;
	}

	private final RowMapper<FormDisplayCell> ROW_MAPPER = new RowMapper<FormDisplayCell>() {
	    public FormDisplayCell mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	FormDisplayCell fdc = getCell(CellType.fromValue(rs.getInt("cellTypeId")), rs.getString("cellContent")); 
	    	fdc.setCellType(CellType.fromValue(rs.getInt("cellTypeId")));
	    	fdc.setRow(rs.getInt("rowNum"));
	    	fdc.setRowSpan(rs.getInt("rowSpan"));
	    	fdc.setColumn(rs.getInt("columnNum"));
	    	fdc.setColumnSpan(rs.getInt("colSpan"));
	    	fdc.setExpandable(rs.getBoolean("expandable"));
	    	fdc.setLineNo(rs.getBoolean("lineNo"));
	    	
	    	PageForm pf = pageFormDao.findById(rs.getInt("pageFormId"));
	    	fdc.setPageForm(pf);
	    	Model model = modelDao.findById(rs.getInt("modelId"));
	    	fdc.setModel(model);
	    	return fdc;
	    }
	};	
	
	private FormDisplayCell getCell(CellType cellType, String content){
		FormDisplayCell fdc = null;
		switch (cellType) {
		case EMPTY:
			fdc = new FormEmptyCell();
		case CALC:
		case INPUT:
		case COPYCELL:
			FormDataCell formDataCell = new FormDataCell();
			formDataCell.setDataKey(content);
			fdc = formDataCell; 
			break;
		case COL_HEADING:
		case ROW_HEADING:
		case GROUP_ROW_HEADING:
		case ROW_HEADING_NON_REPEAT:
			FormDisplayCell formHeaderCell = new FormHeaderCell();
			formHeaderCell.setText(content);
			fdc = formHeaderCell; 
			break;
		default:
			break;
		}
		return fdc;
	}
	
	// don't want to use this - want to use the batchInsert below
	public int create(FormDisplayCell formDisplayCell) {
		throw new UnsupportedOperationException("Use the batch insert instead");
	}

	public List<FormDisplayCell> findByFormId(int formId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE pageFormId = ?";
		return getSimpleJdbcTemplate().query(sql, ROW_MAPPER, formId);
	}

	public FormDisplayCell findById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
		return getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
	}

	
	private JdbcTemplate jdbcTemplate;
	
	public int[] batchInsert(final List<FormDisplayCell> formDisplayCells){
		jdbcTemplate = new JdbcTemplate(getDataSource());
		
		 int[] insertCounts = jdbcTemplate.batchUpdate(
	                "INSERT INTO " + TABLE_NAME + " (pageFormId, rowNum, columnNum, rowspan, colspan, cellContent, cellTypeId, modelId, expandable, lineNo) " +
	                		"values (?,?,?,?,?,?,?,?,?,?)",
	                new BatchPreparedStatementSetter() {
	
	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
	                    	FormDisplayCell cell = formDisplayCells.get(i);
	                    	int pageFormId = 0;
	                    	if (cell.getPageForm()!=null){
	                    		pageFormId = cell.getPageForm().getId();
	                    	}
	                 
	                        ps.setInt(1, pageFormId);
	                        ps.setInt(2, cell.getRow());
	                        ps.setInt(3, cell.getColumn());
	                        ps.setInt(4, cell.getRowSpan());
	                        ps.setInt(5, cell.getColumnSpan());
	                        ps.setString(6, cell.getCellContents());
	                        ps.setInt(7, cell.getCellType().toValue());
	                        ps.setInt(8, cell.getModel().getId());
	                        ps.setBoolean(9, cell.isExpandable());
	                        ps.setBoolean(10, cell.isLineNo());
	                    }

	                    public int getBatchSize() {
	                        return formDisplayCells.size();
	                    }
	                } );
	        return insertCounts;
		
	}
	
}
