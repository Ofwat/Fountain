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

import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.LineDocument.Line;
import uk.gov.ofwat.model2.SectionDocument.Section;
import uk.gov.ofwat.model2.SectiondetailsDocument.Sectiondetails;

public class PageSection implements Entity {
	
	private Map<String, Cell> cellCodeToLineCellMap;
	private Map<String, Line> cellCodeToLineMap;
	private Section section;
	private List<PageForm> pageForms = new ArrayList<PageForm>();
	private boolean grouped;
	private String sectionType;
	private String code;
	private Integer itemCodeColumn;
	
	
	// For DB use
	private int id;
	private ModelPage modelPage;
	
	public boolean isGrouped() {
		return grouped;
	}
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ModelPage getModelPage() {
		return modelPage;
	}
	public void setModelPage(ModelPage modelPage) {
		this.modelPage = modelPage;
	}

	
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	public String getSectionType() {
		return sectionType;
	}
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
	public Integer getItemCodeColumn() {
		return itemCodeColumn;
	}
	public void setItemCodeColumn(Integer itemCodeColumn) {
		this.itemCodeColumn = itemCodeColumn;
	}
	// TODO temp default constructor - used by DAO. Needs to be a simple one that takes in the table and the id only.
	public PageSection(){
		super();
	}
	
	public PageSection(Section section){
		this.cellCodeToLineCellMap = mapCellCodeToLineCell(section);
		this.cellCodeToLineMap = mapCellCodeToLine(section);
		this.section = section;
		this.code = section.getSectiondetails().getCode();
		if (section.getSectiondetails().isSetGrouptype() &&
			!section.getSectiondetails().getGrouptype().equalsIgnoreCase("NONE")) {
			this.grouped = true;
		}
		else {
			this.grouped = false;
		}
		if (section.getSectiondetails().isSetSectionType()) { 
			this.sectionType = section.getSectiondetails().getSectionType();
		}
		else {
			this.sectionType = "ordered"; // default
		}
		if (section.getSectiondetails().isSetItemCodeColumn()) { 
			this.itemCodeColumn = section.getSectiondetails().getItemCodeColumn().intValue();
		}
	}
	public Map<String, Cell> getCellCodeToLineCellMap(){
		return cellCodeToLineCellMap;
	}
	public Map<String, Line>getCellCodeToLineMap(){
		return cellCodeToLineMap;
	}
	public Sectiondetails getSectionDetails(){
		return section.getSectiondetails();
	}
	public void addPageForm(PageForm pageForm){
		this.pageForms.add(pageForm);
	}
	public List<PageForm> getPageForms() {
		return pageForms;
	}
	
	private Map<String, Cell>  mapCellCodeToLineCell(Section section) {
		Map<String, Cell> cellCodeToLineCellMap = new HashMap<String, Cell>();
		if (section.isSetLines()) { 
			for (Line line: section.getLines().getLineArray()) {
				if (line.isSetCells()) {
					for (Cell lineCell: line.getCells().getCellArray()) {
						cellCodeToLineCellMap.put(lineCell.getCode(), lineCell);
					}
				}
			}
		}
		return cellCodeToLineCellMap;
	}
	
	private Map<String, Line>  mapCellCodeToLine(Section section) {
		Map<String, Line> cellCodeToLineMap = new HashMap<String, Line>();
		if (section.isSetLines()) { 
			for (Line line: section.getLines().getLineArray()) {
				if (line.isSetCells()) {
					for (Cell lineCell: line.getCells().getCellArray()) {
						cellCodeToLineMap.put(lineCell.getCode(), line);
					}
				}
			}
		}
		return cellCodeToLineMap;
	}
	
	public String toHtml(){
		StringBuffer html = new StringBuffer();
		for(PageForm pageForm: pageForms){
			html.append(pageForm.toHtml());
		}
		return html.toString();
	}


}
