package uk.gov.ofwat.fountain.api.table.reader.table;

import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.api.table.TableStructure;
import uk.gov.ofwat.fountain.api.table.reader.Section;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;

public class GroupSelectTableReader extends TableReader {

	public void read(DataTable dataTable, TableUploadMetaData metaData, TableStructure tableStructure, XSSFWorkbook workBook, Sheet sheet) {
		this.dataTable = dataTable;
		this.metaData = metaData;

		// 17A type table
		int rownum = 4;
		Set<GroupEntry> groupEntrySet = dataTable.getGroupEntries();
		for(GroupEntry ge: groupEntrySet){
			if (!isEmpty(tableStructure, dataTable, sheet, ge)) {
				for (PageSection pageSection: tableStructure.getModelPage().getPageSections()) {
					for (PageForm form: pageSection.getPageForms()) {
						Section section = new Section(pageSection);
						if (dataTable.getCompany().isCompanyInType(form.getCompanyType())) {
							FormDisplayCell[][] cellGrid = form.getCellGrid();
							for(FormDisplayCell[] line: cellGrid){
								Row row  = sheet.createRow(rownum);
								rownum++;
								orderedLineReader.initialise(dataTable, metaData, line, section, sheet, row);
								orderedLineReader.read(ge, dataTable.getCompany());
				    		}
						}
					}
				}
				sheet.createRow(rownum);
				rownum++;
				sheet.createRow(rownum);
				rownum++;
			}
		}
	}


}
