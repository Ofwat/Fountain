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
package uk.gov.ofwat.fountain.domain.form;

import java.util.Map;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.model2.FormCellDocument.FormCell;
import uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell;
import uk.gov.ofwat.model2.LineDocument.Line;

public abstract class FormDisplayCell {

	private int row;
	private int column;
	private int rowSpan;
	private int columnSpan;
	private boolean useConfidenceGrade = false;
	private String width;
	private String cellCode;
	private String groupDescriptionCode;
	private String text;
	private String cssClass;
	private String section;
	private boolean expandable;
	private int itemId;
	private String type;
	private String interval;
	private int intervalId;
	private int itemPropId;
	private String companyType;
	private Model model;
	
	// for DB
	private int id;
	private PageForm pageForm;
	private CellType cellType;
	private boolean lineNo = false;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public PageForm getPageForm() {
		return pageForm;
	}
	public void setPageForm(PageForm pageForm) {
		this.pageForm = pageForm;
	}

	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	public abstract String toHTML();
	public abstract String getCellContents();


	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getText()
	 */
	protected String getText() {
		return text;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setText(java.lang.String)
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public int getItemPropId() {
		return itemPropId;
	}

	public void setItemPropId(int itemPropId) {
		this.itemPropId = itemPropId;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getRow()
	 */
	public int getRow() {
		return row;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setRow(int)
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getColumn()
	 */
	public int getColumn() {
		return column;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setColumn(int)
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getRowSpan()
	 */
	public int getRowSpan() {
		return rowSpan;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setRowSpan(int)
	 */
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getColumnSpan()
	 */
	public int getColumnSpan() {
		return columnSpan;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setColumnSpan(int)
	 */
	public void setColumnSpan(int columnSpan) {
		this.columnSpan = columnSpan;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#isUseConfidenceGrade()
	 */
	protected boolean isUseConfidenceGrade() {
		return useConfidenceGrade;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setUseConfidenceGrade(boolean)
	 */
	protected void setUseConfidenceGrade(boolean useConfidenceGrade) {
		this.useConfidenceGrade = useConfidenceGrade;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getWidth()
	 */
	protected String getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setWidth(java.lang.String)
	 */
	protected void setWidth(String width) {
		this.width = width;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getCellCode()
	 */
	public String getCellCode() {
		return cellCode;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setCellCode(java.lang.String)
	 */
	protected void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getGroupDescriptionCode()
	 */
	protected String getGroupDescriptionCode() {
		return groupDescriptionCode;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setGroupDescriptionCode(java.lang.String)
	 */
	protected void setGroupDescriptionCode(String groupDescriptionCode) {
		this.groupDescriptionCode = groupDescriptionCode;
	}
	
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#getCssClass()
	 */
	protected String getCssClass() {
		return cssClass;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.domain.form.FormDisplayCell#setCssClass(java.lang.String)
	 */
	protected void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	protected String getItemCode(FormHeadingCell formHeadingCell, 
			Map<String, Line> cellCodeToLineMap,
			Map<String, FormCell> rowToFormCellMap) {
		if (formHeadingCell.isSetItemCode()) {
			return formHeadingCell.getItemCode();
		}
		
		FormCell formCell = rowToFormCellMap.get(formHeadingCell.getRow());
		return getItemCode(formCell, cellCodeToLineMap);
	}

	protected String getItemCode(FormHeadingCell formHeadingCell, 
			Map<String, Line> cellCodeToLineMap) {
		if (null != formHeadingCell) {
			Line line = cellCodeToLineMap.get(formHeadingCell.getCellCode());
			if (line.getLinedetails().isSetCode()) {
				return line.getLinedetails().getCode();
			}
		}
		return null;
	}
	
	protected String getItemCode(FormCell formCell, 
			Map<String, Line> cellCodeToLineMap) {
		if (null != formCell) {
			Line line = cellCodeToLineMap.get(formCell.getCellCode());
			if (line.getLinedetails().isSetCode()) {
				return line.getLinedetails().getCode();
			}
		}
		return null;
	}
	
	//TODO - remove this when encoding on resouces if fixed
	protected String decode(String original){
		
		return null == original? original: original.replaceAll("~~~~pound~~~~", "�");
	}
	

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public boolean isExpandable() {
		return expandable;
	}


	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}


	public String getInterval() {
		return interval;
	}


	public void setInterval(String interval) {
		this.interval = interval;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getIntervalId() {
		return intervalId;
	}


	public void setIntervalId(int intervalId) {
		this.intervalId = intervalId;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public boolean isLineNo() {
		return lineNo;
	}
	public void setLineNo(boolean lineNo) {
		this.lineNo = lineNo;
	}


}