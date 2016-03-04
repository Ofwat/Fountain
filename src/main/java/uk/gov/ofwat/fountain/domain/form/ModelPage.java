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
import java.util.List;

import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Table;


public class ModelPage implements Entity {
	private int id;
	private String tableDescription;
	private Model model;
	private boolean groupSelect = false;
	private String companyType;
	private Table table;

	
	private List<FormDisplayCell> topDisplayCells = new ArrayList<FormDisplayCell>();
	private List<FormDisplayCell> leftDisplayCells = new ArrayList<FormDisplayCell>();
	private List<FormDisplayCell> dataDisplayCells = new ArrayList<FormDisplayCell>();
	
	private List<PageSection> pageSections = new ArrayList<PageSection>();

	private String dataTableHtml = null;
	private int cornerRowSpan;
	private int cornerColSpan;
	
	public ModelPage() {
		dataTableHtml = null;

		for (FormDisplayCell formHeaderCell: topDisplayCells) {
			if (cornerRowSpan < formHeaderCell.getRow()) {
				cornerRowSpan = formHeaderCell.getRow();
			}
		}
		for (FormDisplayCell formHeaderCell: leftDisplayCells) {
			if (cornerColSpan < formHeaderCell.getColumn()) {
				cornerColSpan = formHeaderCell.getColumn();
			}
		}
	}

	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public boolean isGroupSelect() {
		return groupSelect;
	}
	public void setGroupSelect(boolean groupSelect) {
		this.groupSelect = groupSelect;
	}
	public String getPageTitleHtml() {
		return getModel().getCode() + " Table " + getTable().getName();
	}
	public String getTableDiv() {
		return getTable().getName() + " - " + getTableDescription();
	}
	public String getPopulatePageHtml() {
		return "ofwat.modelPage.populatePage(\"/Fountain/rest-services/table/" + getTable().getId() + "?companyId=\" + companyId);"; 
	}
	public void addPageSection(PageSection pageSection){
		pageSection.setModelPage(this);
		this.pageSections.add(pageSection);
	}

	public String getDataTableHtml() {
	
		if (null == dataTableHtml) { 
			StringBuffer html = new StringBuffer();
			
			
		
			if(this.groupSelect){
				html.append("<table class=\"pure-table pure-table-bordered \">\n");
				html.append("<tr>\n");
				html.append("<td colspan=3>\n");
				html.append("<div id=\"groupSelect\">\n");
				html.append("</div>\n");
				html.append("</td>\n");
				html.append("</tr>\n");
				html.append("<tr>\n");
				html.append("<td>\n");
			}

			
			html.append("<table class=\"pure-table pure-table-bordered ICS_MARKER\">\n");
			
			for(PageSection pageSection: pageSections){
				html.append(pageSection.toHtml());
			}

			html.append("</table>\n");
			
			if(this.groupSelect){
				html.append("</td>\n");
				html.append("</tr>\n");
				html.append("</table>\n");
			}
			dataTableHtml = html.toString();
		}
		return dataTableHtml;
	}


	public String getPageName() {
		return "table_" + getTable().getName();
	}
	public int getCornerRowSpan() {
		return cornerRowSpan;
	}
	public int getCornerColSpan() {
		return cornerColSpan;
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
	public String getTableDescription() {
		return tableDescription;
	}

	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}
	public List<PageSection> getPageSections() {
		return pageSections;
	}

	public void setPageSections(List<PageSection> pageSections) {
		this.pageSections = pageSections;
	}

	public void setCornerRowSpan(int cornerRowSpan) {
		this.cornerRowSpan = cornerRowSpan;
	}

	public void setCornerColSpan(int cornerColSpan) {
		this.cornerColSpan = cornerColSpan;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
