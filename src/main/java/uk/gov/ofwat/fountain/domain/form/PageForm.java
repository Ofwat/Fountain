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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.ModelFormsImporter;
import uk.gov.ofwat.model2.FormCellDocument.FormCell;
import uk.gov.ofwat.model2.FormDocument.Form;
import uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell;

public class PageForm {
	
	private String companyType; // TODO change this to the actual object, then remove the coType field below
	private List<FormDisplayCell> topDisplayCells = new ArrayList<FormDisplayCell>();
	private List<FormDisplayCell> leftDisplayCells = new ArrayList<FormDisplayCell>();
	private List<FormDisplayCell> dataDisplayCells = new ArrayList<FormDisplayCell>();
	private Map<String, FormCell> rowToFormCellMap; // one cell per row to allow further processing to get the line item / year
	private Form form;
	private PageSection pageSection;
	private ModelFormsImporter mfiContext;
	private FormDisplayCell[][] cellGrid;
	private int rows;
	private int cols;
	
	// For DB use
	private int id;
	
	// the number of left hand heading cells before the data cells start
	private int dataCellCollOffset;
	
	protected int getDataCellCollOffset() {
		return dataCellCollOffset;
	}
	protected int getDataCellRowOffset() {
		return dataCellRowOffset;
	}

	// the number of top heading rows before the data cells start
	private int dataCellRowOffset;
	
	// the number of data cell rows 
	private int totalDataRows;
	
	// the number of data cell columns
	private int totalFormCols;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public PageSection getPageSection() {
		return pageSection;
	}
	public void setPageSection(PageSection pageSection) {
		this.pageSection = pageSection;
	}
	
	// TODO remove this default constructor and create one that populates the item from the DB
	public PageForm(){
		super();
	}
	
	public PageForm(Form form, PageSection pageSection, ModelFormsImporter mfi){
		this.form = form;
		this.pageSection = pageSection;
		this.mfiContext = mfi;
		setCompanyType(form.getFormDetails().getCompanyType());
		rowToFormCellMap = mapRowToFormCell(form);
		
		determineTopOffsetAndTotalFormCols();
		determineLeftOffsetAndTotalDataRows();
		
		processFormHeadingsTop();
		processFormHeadingsLeft();
		processFormCells();
		makeCellGrid();
	}
	

	/**
	 *  creates all the form data cells and calculates the
	 *  total data rows (not including the top header offsets) 
	 *  and the number of data cell cols (not including left header offset)
	 */
	private void processFormCells() {
		for (FormCell formCell: form.getFormCells().getFormCellArray()) {
			int row = Integer.parseInt(formCell.getRow());
			int col = Integer.parseInt(formCell.getColumn());
			if(null != formCell.getRowSpan() && !("".equals(formCell.getRowSpan()))){
				int span = Integer.parseInt(formCell.getRowSpan());
				row+=--span; 
			}
			if(null != formCell.getColumnSpan() && !("".equals(formCell.getColumnSpan()))){
				int span = Integer.parseInt(formCell.getColumnSpan());
				col+=--span; 
			}
			totalDataRows = totalDataRows >= row ? totalDataRows : row;  
			totalFormCols = totalFormCols >= col ? totalFormCols : col;  
			dataDisplayCells.add(new FormDataCell(mfiContext, 
					                              pageSection,
					                              this,
					                              rowToFormCellMap, 
					                              formCell, 
					                              form.getFormDetails().getCompanyType()));
		}
	}
	
	/**
	 * Fills the collection of top heading cells and also calculates
	 * the offset in rows before the data cells start
	 */
	private void determineTopOffsetAndTotalFormCols() {
		if (form.isSetFormHeadingsTop()) {
			for (FormHeadingCell formHeadingCell: form.getFormHeadingsTop().getFormHeadingCellArray()) {
				int formHeaderRow = Integer.parseInt(formHeadingCell.getRow());
				int col = Integer.parseInt(formHeadingCell.getColumn());
				if(null != formHeadingCell.getRowSpan() && !("".equals(formHeadingCell.getRowSpan()))){
					int span = Integer.parseInt(formHeadingCell.getRowSpan());
					formHeaderRow+=--span; 
				}
				if(null != formHeadingCell.getColumnSpan() && !("".equals(formHeadingCell.getColumnSpan()))){
					int span = Integer.parseInt(formHeadingCell.getColumnSpan());
					col+=--span; 
				}
				totalFormCols = totalFormCols >= col ? totalFormCols : col;  
				dataCellRowOffset  = dataCellRowOffset >= formHeaderRow ? dataCellRowOffset : formHeaderRow; // whichever is greater
			}
		}	
	}
	
	/**
	 * Fills the collection of top heading cells and also calculates
	 * the offset in rows before the data cells start
	 */
	private void processFormHeadingsTop() {
		if (form.isSetFormHeadingsTop()) {
			for (FormHeadingCell formHeadingCell: form.getFormHeadingsTop().getFormHeadingCellArray()) {
				FormDisplayCell fhc = new FormHeaderCell(this.mfiContext, 
                        pageSection, 
                        this,
                        rowToFormCellMap, 
                        formHeadingCell, 
                        form.getFormDetails().getCompanyType(),
						0,
						dataCellCollOffset);
				fhc.setExpandable(false); // we don't want top headers expanding
				topDisplayCells.add(fhc);
			}
		}	
	}
	
	/**
	 * fills the collection of left side headers and also calculates the 
	 * offset in columns to the start of the data cells and increases total 
	 * data row count if needed
	 * 
	 */
	private void determineLeftOffsetAndTotalDataRows() { 
		if (form.isSetFormHeadingsLeft()) {
			for (FormHeadingCell formHeadingCell: form.getFormHeadingsLeft().getFormHeadingCellArray()) {
				int formHeadingRow = Integer.parseInt(formHeadingCell.getRow());
				int formHeadingCol = Integer.parseInt(formHeadingCell.getColumn());
				if(null != formHeadingCell.getColumnSpan() && !("".equals(formHeadingCell.getColumnSpan()))){
					int span = Integer.parseInt(formHeadingCell.getColumnSpan());
					formHeadingCol+=--span; 
				}
				if(null != formHeadingCell.getRowSpan() && !("".equals(formHeadingCell.getRowSpan()))){
					int span = Integer.parseInt(formHeadingCell.getRowSpan());
					formHeadingRow+=--span; 
				}
				totalDataRows = totalDataRows >= formHeadingRow ? totalDataRows : formHeadingRow;  
				dataCellCollOffset= dataCellCollOffset >= formHeadingCol ? dataCellCollOffset : formHeadingCol;  
			}
		}
	}

	/**
	 * fills the collection of left side headers and also calculates the 
	 * offset in columns to the start of the data cells and increases total 
	 * data row count if needed
	 * 
	 */
	private void processFormHeadingsLeft() { 
		if (form.isSetFormHeadingsLeft()) {
			for (FormHeadingCell formHeadingCell: form.getFormHeadingsLeft().getFormHeadingCellArray()) {
				leftDisplayCells.add(new FormHeaderCell(this.mfiContext, 
                        pageSection,
                        this,
                        rowToFormCellMap, 
                        formHeadingCell, 
                        form.getFormDetails().getCompanyType(),
                        dataCellRowOffset,
                        0));
			}
		}
	}
	
	public List<FormDisplayCell> getTopDisplayCells() {
		return topDisplayCells;
	}
	public void setTopDisplayCells(List<FormDisplayCell> topDisplayCells) {
		this.topDisplayCells = topDisplayCells;
	}
	public List<FormDisplayCell> getLeftDisplayCells() {
		return leftDisplayCells;
	}
	public void setLeftDisplayCells(List<FormDisplayCell> leftDisplayCells) {
		this.leftDisplayCells = leftDisplayCells;
	}
	public List<FormDisplayCell> getDataDisplayCells() {
		return dataDisplayCells;
	}
	public void setDataDisplayCells(List<FormDisplayCell> dataDisplayCells) {
		this.dataDisplayCells = dataDisplayCells;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}

	private Map<String, FormCell> mapRowToFormCell(Form form) {
		Map<String, FormCell> rowToFormCellMap = new HashMap<String, FormCell>(); 
		FormCell[] formCells = form.getFormCells().getFormCellArray();
		for (FormCell formCell: formCells) {
			// There may be more than one form cell for a row. 
			// We always want the first one to be mapped against the row.
			// Don't let subsequent form cells overwrite the first.  
			if (!rowToFormCellMap.containsKey(formCell.getRow())) {
				rowToFormCellMap.put(formCell.getRow(), formCell);
			}
		}
		return rowToFormCellMap;
	}
	

	/**
	 * returns a grid ([row][coll]) of all the cells on the form
	 * in the right place. 
	 * 
	 * null indicates the position should be ignored (e.g. due to previous colspan)
	 *  
	 * Empty cells have been populated as needed
	 * 
	 * @return grid of cells
	 */
	private void makeCellGrid(){
		
		rows = totalDataRows + dataCellRowOffset;
		cols = totalFormCols + dataCellCollOffset;
		cellGrid = new FormDisplayCell[rows][cols]; 
		
		// first populate the existing cells
		for(FormDisplayCell cell: topDisplayCells){
			cellGrid[cell.getRow()][cell.getColumn()] = cell;
		}
		
		for(FormDisplayCell cell: leftDisplayCells){
			cellGrid[cell.getRow()][cell.getColumn()] = cell;
		}
		
		for(FormDisplayCell cell: dataDisplayCells){
			cellGrid[cell.getRow()][cell.getColumn()] = cell;
		}
		
		// now populate any empty cells.
		FormDisplayCell emptyCell = new FormEmptyCell(this); // we can re-use this one
		
		for(int rowIdx = 0; rowIdx < cellGrid.length; rowIdx++){
			FormDisplayCell[] row = cellGrid[rowIdx];
			int lastSpan = 1;
			for(int colIdx = 0; colIdx < row.length; colIdx++){
				FormDisplayCell cell = row[colIdx];
				if(null != cell){
					lastSpan = cell.getColumnSpan(); 
				}
				else if(lastSpan > 1){
					lastSpan--; // still within a colspan. Leave this cell null
				}
				else{
					// need an empty cell
					cellGrid[rowIdx][colIdx] = emptyCell;
				}
			}
		}
	}

	public FormDisplayCell[][] getCellGrid(){
		return cellGrid;
	}

	public String toHtml(){
	
		StringBuffer html = new StringBuffer("");	
		FormDisplayCell[][] cellGrid = getCellGrid();
		
		for(FormDisplayCell[] row: cellGrid){
			Boolean optionalRow = false;
			if ((pageSection.getSectionType() != null) &&
				(pageSection.getSectionType().equals("unordered") ||
				 pageSection.getSectionType().equals("unorderedItemPrefix"))) {
				optionalRow = true;
			}
			StringBuffer rowBuff = new StringBuffer();
			boolean expandable = false;
			boolean hasGroupEntryId = false;
			if (optionalRow) {
				rowBuff.append("<tr class='optionalRow'>\n");
			}
			else {
				rowBuff.append("<tr>\n");
			}
			for(FormDisplayCell cell: row){
				if(null != cell){
					rowBuff.append(cell.toHTML());
					if (cell.isExpandable()) {
						expandable = true;
					}
					if(null != cell.getGroupDescriptionCode()){
						hasGroupEntryId = true;
					}
				}
			}
			rowBuff.append("</tr>");

			if(!expandable || hasGroupEntryId){
				if (optionalRow) {
					html.append(rowBuff.toString().replaceFirst("<tr class='optionalRow'>", "<tr class='optionalRow nonGroupRow companyType " + companyType.toUpperCase() + "'>"));
				}
				else {
					html.append(rowBuff.toString().replaceFirst("<tr>", "<tr class='nonGroupRow companyType " + companyType.toUpperCase() + "'>"));
				}
			}
			else{
				html.append(rowBuff.toString().replaceFirst("<tr>", "<tr class='groupRow companyType " + companyType.toUpperCase() + "'>"));
			}
		}
		return html.toString();
	}
	
	public void setCellGrid(FormDisplayCell[][] cellGrid) {
		this.cellGrid = cellGrid;
	}
	
}
