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

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang.StringEscapeUtils;

import uk.gov.ofwat.fountain.api.ModelFormsImporter;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.FormCellDocument.FormCell;
import uk.gov.ofwat.model2.LineDocument.Line;

public class FormDataCell extends FormDisplayCell {

	private static Logger logger = LoggerFactory.getLogger(FormDataCell.class);
	private String itemCode;
	private String align = "alignRight"; // default for numbers.
	private String ruleText;
	private String dataKey;
	private Run run;
	
	
	private static Random rn = new Random(6644940332l);
	
	public static int rand(int lo, int hi)
    {
            int n = hi - lo + 1;
            int i = rn.nextInt() % n;
            if (i < 0)
                    i = -i;
            return lo + i;
    }


	public FormDataCell(ModelFormsImporter context,
			            PageSection pageSection,
			            PageForm pageForm,
			            Map<String, FormCell> rowToFormCellMap,
						FormCell formCell, 
						String companyType) {

		this.setRun(context.getRun());
		this.setModel(context.getModel());
		this.setSection(pageSection.getSectionDetails().getCode());
		this.setColumn(Integer.parseInt(formCell.getColumn()) + pageForm.getDataCellCollOffset() -1);
		this.setRow(Integer.parseInt(formCell.getRow()) + pageForm.getDataCellRowOffset() -1);
		this.setCompanyType(companyType);
		this.setPageForm(pageForm);
		setGroupDescriptionCode(formCell.getGroupDescriptionCode()); 
		
		if (formCell.isSetColumnSpan()) {
			this.setColumnSpan(Integer.parseInt(formCell.getColumnSpan()));
		}
		else {
			this.setColumnSpan(1);
		}
		if (formCell.isSetRowSpan()) {
			this.setRowSpan(Integer.parseInt(formCell.getRowSpan()));
		}
		else {
			this.setRowSpan(1);
		}

		if (formCell.isSetWidth()) {
			this.setWidth(formCell.getWidth());
		}
		
		this.setCellCode(formCell.getCellCode());

		if (formCell.isSetUseConfidenceGrade()) {
			this.setUseConfidenceGrade(formCell.getUseConfidenceGrade());
		}

		if (formCell.isSetGroupDescriptionCode()) {
			this.setGroupDescriptionCode(formCell.getGroupDescriptionCode());
		}
		Line line = pageSection.getCellCodeToLineMap().get(formCell.getCellCode());
		if (null != line) {
			ruleText =  line.getLinedetails().getRuletext();
		}

		String itemCode = getItemCode(formCell, pageSection.getCellCodeToLineMap());
		ItemProperties itemProperties = null;
		if (null != itemCode) {
			itemProperties = context.getModel().getItemPropertiesForItem(itemCode);
			if (null != itemProperties) {
				this.setItemId(itemProperties.getItem().getId());
				this.setItemPropId(itemProperties.getId());
				this.setItemCode(itemCode);
				if(itemProperties.getItem().getUnit().equalsIgnoreCase("text")){
					align = "alignLeft"; // left align text data
				}
			}
			else {
				logger.info("itemProperties not found for " + itemCode);
			}
		}
		else {
			logger.info("itemCode not found for " + formCell.getCellCode());
		}

		Cell lineCell = pageSection.getCellCodeToLineCellMap().get(formCell.getCellCode());
		if (null != lineCell) {
			// get interval
			Interval interval = context.getReferenceService().getInterval(lineCell.getYear());
			if (null != interval) {
				this.setIntervalId(interval.getId());
				this.setInterval(interval.getName());
			}
			else {
				logger.error("interval not found for " + lineCell.getYear());
			}

			// get type
			if (isUseConfidenceGrade()) {
				// CG
				String cellType;
				if (lineCell.isSetCgtype()) {
					// use specific cg type
					cellType = lineCell.getCgtype();
				}
				else {
					cellType = lineCell.getType();
				}
					
				if (cellType.equals("COPYCELL") &&
					lineCell.isSetEquation()) {
					// copycell
					setType("INPUT");
				}
				else {
					setType(cellType);
				}
			} 
			else {
				// value
				if (lineCell.getType().equals("COPYCELL") &&
					lineCell.isSetEquation()) {
					// copycell
					ItemProperties ip = context.getItemService().getPropertiesForItemAndModel(lineCell.getEquation(), getModel().getId());
					if (ip.isRawDataValue(interval)) {
						setType("INPUT");
					}
					else {
						setType("CALC");
					}
				}
				else {
					setType(lineCell.getType());
				}
			}
		}
		else {
			logger.error("lineCell not found for " + formCell.getCellCode());
		}
		
		determinCssClass();
		createDataKey();
	}



	private void setRun(Run run) {
		this.run = run;
	}


	public FormDataCell() {
	}

	public void createDataKey() {
		String content; 
		DataKey key = new DataKey();
		key.setCg(isUseConfidenceGrade());
		key.setItemId("" + getItemId());
		key.setItemPropertiesId("" + getItemPropId());
		key.setIntervalId("" + getIntervalId());
		key.setCompanyId("${company.id}");
		key.setRunId(run.getId());
		key.setTagId(RunModelTag.LATEST.getId());
		key.setRunTag(true);
		key.setModelId("" + getModel().getId());
		if(null == getGroupDescriptionCode()){
			content = key.getKey(false);
		}
		else{
			key.setGroupEntryId("${" + getGroupDescriptionCode()+"}");
			content = key.getKey(true);
		}
		setDataKey(content);
	}

	public String getCellContents() {
		return getDataKey();
	}

	public void setDataKey(String key) {
		this.dataKey = key;
	}

	public String getDataKey() {
		return dataKey;
	}
	
	public String toHTML() {
		
		byte b[] = new byte[10];
        for (int i = 0; i < 10; i++){
                b[i] = (byte)rand('a', 'z');
        }
        String divId = new String(b, Charset.forName("UTF-8"));;
		
        String divClass = getCellContents();
		
		String html = "";

		html = html + "<td title=\""+ itemCode+"\" colspan=" + getColumnSpan() + " rowspan=" + getRowSpan() + "  class=\"" + getCssClass() + "\">";
		
		html = html + "<div id=\"" + divId + "\" class=\"" + divClass + "\">";
		
		html = html + "<input type=\"text\" readonly=\"readonly\" class=\"" + getCssClass() + 
		" disabled\" onchange=\"ofwat.editor.postUpdate('"+itemCode+"', '"+getInterval()+"', this.value, 'value')\"></input>";
		
		html = html + "</div>";
		
		if(null != this.ruleText){
			html = html + "<form><input type='hidden' class='ruletext' value='" + StringEscapeUtils.escapeHtml(this.ruleText) + "'/></form>";
		}
		html = html + "</td>";
		return html; 
	}


	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	// Should use the cell type to determine this.
	private void determinCssClass() {
		// TODO this should be done in the constructor
		if ("COPYDATA".equals(getType())) {
			setCssClass("copy" + " " + align);
			setCellType(CellType.COPYCELL);
		}
		else if ("INPUT".equals(getType())) {
			if(getColumnSpan() > 1){
				// use one of the wide classes to include more text
				setCssClass("inputWide" + getColumnSpan() + " " + align);
			}
			else{
				setCssClass("input" + " " + align);
			}
			setCellType(CellType.INPUT);
		}
		else if ("CALC".equals(getType())) {
			setCssClass("calc" + " " + align);
			setCellType(CellType.CALC);
		}
		else if ("COPYCELL".equals(getType())) {
			setCssClass("copy" + " " + align);
			setCellType(CellType.COPYCELL);
		}
		else {
			setCssClass("empty" + " " + align);
			setCellType(CellType.EMPTY);
		}
	}

}
