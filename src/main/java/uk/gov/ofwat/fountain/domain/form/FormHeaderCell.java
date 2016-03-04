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

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.ModelFormsImporter;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.FormCellDocument.FormCell;
import uk.gov.ofwat.model2.FormHeadingCellDocument.FormHeadingCell;
import uk.gov.ofwat.model2.LineDocument.Line;

public class FormHeaderCell extends FormDisplayCell {

	private static Logger logger = LoggerFactory.getLogger(FormHeaderCell.class);
	
	// TODO prevent use of this apart from from DAO
	public FormHeaderCell(){
		super();
	}
	
	public FormHeaderCell(ModelFormsImporter context,
			PageSection pageSection,
			PageForm pageForm,
			Map<String, FormCell> rowToFormCellMap,
			FormHeadingCell formHeadingCell,
			String companyType,
			int rowOffset,
			int colOffset) {

		this.setSection(pageSection.getSectionDetails().getCode());
		this.setPageForm(pageForm);
		this.setColumnSpan(0);
		this.setRowSpan(0);
		this.setCompanyType(companyType);
		this.setLineNo(formHeadingCell.isSetUseLineNumber());
		this.setModel(context.getModel());

		this.setColumn(Integer.parseInt(formHeadingCell.getColumn()) + colOffset -1);
		this.setRow(Integer.parseInt(formHeadingCell.getRow()) + rowOffset -1);
		boolean groupSelect = pageSection.getSectionDetails().getAllowGroupSelection();
		String groupType = pageSection.getSectionDetails().getGrouptype();
		if (groupSelect || null == groupType ||groupType.equals("NONE")) {
			this.setExpandable(false);
		}
		else {
			this.setExpandable(true);
		}
		
		if (formHeadingCell.isSetColumnSpan()) { 
			this.setColumnSpan(Integer.parseInt(formHeadingCell.getColumnSpan()));
		}
		else {
			this.setColumnSpan(1);
		}
		if (formHeadingCell.isSetRowSpan()) { 
			this.setRowSpan(Integer.parseInt(formHeadingCell.getRowSpan()));
		}
		else {
			this.setRowSpan(1);
		}

		if (formHeadingCell.isSetUseConfidenceGrades()) {
			this.setUseConfidenceGrade(formHeadingCell.getUseConfidenceGrades());
		}
		
		if (formHeadingCell.isSetWidth()) {
			this.setWidth(formHeadingCell.getWidth());
		}
		
		if (formHeadingCell.isSetCellCode()) {
			this.setCellCode(formHeadingCell.getCellCode());
		}
	
		if (formHeadingCell.isSetGroupDescriptionCode()) {
			this.setGroupDescriptionCode(formHeadingCell.getGroupDescriptionCode());
		}

		setCssClass("rowHeading");
		// TODO sort celltype out - shouldn't be set here like this
		if (isExpandable()) {
			setCellType(CellType.GROUP_ROW_HEADING);
		}
		else {
			setCellType(CellType.ROW_HEADING);
		}
		// getText
		String text = "";
		if (formHeadingCell.isSetCellCode()){
			// Ignore other settings to do with text. Instead put in a dynamic reference to a pot.
			this.setCellCode(decode(formHeadingCell.getCellCode()));

			String itemCode = getItemCode(formHeadingCell, pageSection.getCellCodeToLineMap());
			ItemProperties itemProperties = null;
			if (null != itemCode) {
				itemProperties = context.getModel().getItemPropertiesForItem(itemCode);
				if (null != itemProperties) {
					this.setItemId(itemProperties.getItem().getId());
					this.setItemPropId(itemProperties.getId());
				}
				else {
					logger.info("itemProperties not found for " + itemCode);
				}
			}
			else {
				logger.info("itemCode not found for " + formHeadingCell.getCellCode());
			}

			Cell lineCell = pageSection.getCellCodeToLineCellMap().get(formHeadingCell.getCellCode());
			if (null != lineCell) {
				// get type
				this.setType(lineCell.getType());
				
				//this.setCellType(CellType.fromValue(value));
				
				// get interval
				Interval interval = context.getReferenceService().getInterval(lineCell.getYear());
				if (null != interval) {
					this.setIntervalId(interval.getId());
				}
				else {
					logger.info("interval not found for " + lineCell.getYear());
				}
			}
			else {
				logger.info("lineCell not found for " + formHeadingCell.getCellCode());
			}

		}
		else {
			String itemCode = getItemCode(formHeadingCell, pageSection.getCellCodeToLineMap(), rowToFormCellMap);
			ItemProperties itemProperties = null;
			if (null != itemCode) {
				itemProperties = context.getModel().getItemPropertiesForItem(itemCode);
			}

			if (formHeadingCell.isSetText()) {
				text = decode(formHeadingCell.getText());
				setCssClass("colHeading");
				// TODO sort celltype out - shouldn't be set here like this
				setCellType(CellType.COL_HEADING);
			}
			else {
				if (formHeadingCell.getUseItemCode()) {
					text = text + itemCode;
				}
				if (formHeadingCell.getUseItemDescription()) {
					// Attempt to use line description. Only use the item description if no line description exists.
					boolean lineDescription = false;
					FormCell formCell = rowToFormCellMap.get(formHeadingCell.getRow());
					if (null != formCell) {
						Line line = pageSection.getCellCodeToLineMap().get(formCell.getCellCode());
						if (null != line) {
							if (line.getLinedetails().isSetDescription()) {
								text = text + decode(line.getLinedetails().getDescription());
								lineDescription = true;
							}
						}
					}
					if (!lineDescription) {
						if (null != itemProperties) {
							text = text + decode(itemProperties.getDescription());
						}
					}
				}
				if (formHeadingCell.getUseLineNumber()) {
					FormCell formCell = rowToFormCellMap.get(formHeadingCell.getRow());
					if (null != formCell) {
						Line line = pageSection.getCellCodeToLineMap().get(formCell.getCellCode());
						if (null != line) {
							if (line.getLinedetails().isSetLinenumber()) {
								text = text + decode(line.getLinedetails().getLinenumber());
							}
						}
					}
				}
				if (formHeadingCell.getUseUnit()) {
					if (null != itemProperties) {
						text = text + decode(itemProperties.getItem().getUnit());
					}
				}
				if (formHeadingCell.getUseYearCode()) {
					if (formHeadingCell.isSetYearCode()) {
						text = text + decode(formHeadingCell.getYearCode());
					}
					else {
						text = text + "not present";
					}
				}
			}
		}
		this.setText(text);
	}
	
	public String toHTML() {
		String html = "";
		if (isLineNo()) {
			html = html + "<td colspan=" + getColumnSpan() + " class=\"" + getCssClass() + " lineNumber\">";
		}
		else {
			if (0 == getItemId()) {
				html = html + "<td colspan=" + getColumnSpan() + " class=\"" + getCssClass() + "\">";
			}
			else {
				String divClass = "i" + getItemId() + "-p" + getItemPropId() + "-y" + getIntervalId() + "-c${company.id}-m" + getModel().getId();
				
				
				html = html + "<td colspan=" + getColumnSpan() + " rowspan=" + getRowSpan() + "  class=\"" + getCssClass() + "\">";
				html = html + "<div id=\"";
				html = html + getItemId() + "-" + getIntervalId() + "-${company.id}\" class=\"" + divClass + "\">";
				html = html + "<c:out value=\"" + getCellCode() + "\"/>";
				html = html + "</div>";
				html = html + "</td>";
			}
		}
		html = html + StringEscapeUtils.escapeHtml(getText());
		html = html + "</td>";
		return html;
	}

	@Override
	public String getCellContents() {
		return getText();
	}
}
