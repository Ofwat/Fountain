package uk.gov.ofwat.fountain.api.table.reader.table;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.gov.ofwat.fountain.api.ConfidenceGradeService;
import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.table.TableStructure;
import uk.gov.ofwat.fountain.api.table.reader.Section;
import uk.gov.ofwat.fountain.api.table.reader.line.LineReader;
import uk.gov.ofwat.fountain.api.table.reader.line.OrderedLineReader;
import uk.gov.ofwat.fountain.api.table.reader.line.UnorderedItemPrefixLineReader;
import uk.gov.ofwat.fountain.api.table.reader.line.UnorderedLineReader;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;

public class TableReader {

	private static Log log = LogFactory.getLog(TableReader.class);

	protected Section section;
	protected DataTable dataTable;
	protected TableUploadMetaData metaData;
	private GroupService groupService;
	protected ConfidenceGradeService confidenceGradeService;
	private ItemService itemService;
	
	protected OrderedLineReader orderedLineReader;
	protected UnorderedLineReader unorderedLineReader;
	protected UnorderedItemPrefixLineReader unorderedItemPrefixLineReader;

	public void read(DataTable dataTable, TableUploadMetaData metaData, TableStructure tableStructure, Sheet sheet) {
		this.dataTable = dataTable;
		this.metaData = metaData;
		
		int rownum = 4;
		for (PageSection pageSection: tableStructure.getModelPage().getPageSections()) {
			for (PageForm form: pageSection.getPageForms()) {
				Section section = new Section(pageSection);
				if (dataTable.getCompany().isCompanyInType(form.getCompanyType())) {
					FormDisplayCell[][] cellGrid = form.getCellGrid();
					for(FormDisplayCell[] line: cellGrid) {
						LineReader lineReader;
						if (pageSection.getSectionType().equals("unordered")) {
							lineReader = unorderedLineReader;
						}
						else if (pageSection.getSectionType().equals("unorderedItemPrefix")) {
							lineReader = unorderedItemPrefixLineReader;
						}
						else {
							lineReader = orderedLineReader;
						}
						Row row  = sheet.getRow(rownum);
						if (null == row) {
							metaData.addError("The imported excel table is not the same size as the table in Fountain. Fountain is trying to import line " + rownum + ". This does not exist in the excel table.");
						}
						else {
							lineReader.initialise(dataTable, metaData, line, section, sheet, row);
			    			if (pageSection.isGrouped()) {
				    			readGroupedLine(lineReader);
			    			}
			    			else {
				    			readUngroupedLine(lineReader);
			    			}
		    			}
		    			rownum++;
					}
				}
			}
		}
	}
	
	

	public void readUngroupedLine(LineReader lineReader) {
		Set<GroupEntry> groupEntrySet = dataTable.getGroupEntries();
		if ((lineReader.hasDataCells() || lineReader.hasCalcCells()) &&
				null != groupEntrySet) {
			for(GroupEntry ge: groupEntrySet){
				if (ge.getDescription().equals("NON GROUPED ITEM")) {
					lineReader.read(ge, dataTable.getCompany());
				}
			}
		}
		else {
			GroupEntry ge = lookUpGroupEntry(dataTable.getCompany());
			lineReader.read(ge, dataTable.getCompany());
		}
	}

	public void readGroupedLine(LineReader lineReader) {
		Set<GroupEntry> groupEntrySet = dataTable.getGroupEntries();
		if ((lineReader.hasDataCells() || lineReader.hasCalcCells()) &&
			null != groupEntrySet) {
			for(GroupEntry ge: groupEntrySet){
				if (!ge.isAggregate() &&
					!lineReader.isBlank(dataTable.getCompany(), ge, dataTable.getDataList())) {
					lineReader.read(ge, dataTable.getCompany());
				}
			}
		}
		else {
			GroupEntry ge = lookUpGroupEntry(dataTable.getCompany());
			lineReader.read(ge, dataTable.getCompany());
		}
	}

	
	public boolean isEmpty( TableStructure tableStructure,
							DataTable table, 
							Sheet sheet,
							GroupEntry ge) {
		for (PageSection section: tableStructure.getModelPage().getPageSections()) {
			for (PageForm form: section.getPageForms()) {
				if (table.getCompany().isCompanyInType(form.getCompanyType())) {
					FormDisplayCell[][] cellGrid = form.getCellGrid();
					for(FormDisplayCell[] line: cellGrid){
						orderedLineReader.setDataTable(dataTable);
						orderedLineReader.setMetaData(metaData);
						orderedLineReader.setCells(line);
						if(	(orderedLineReader.hasDataCells() || orderedLineReader.hasCalcCells())){
							if (orderedLineReader.hasData(table.getCompany(), ge, table.getDataList())) {
								return false;
							}
			    		}
					}
				}
			}
		}
		return true;
	}

	private GroupEntry lookUpGroupEntry(Company company) {
		Group group = groupService.getGroupByCode("NONE");
		List<GroupEntry> groupEntries = groupService.getGroupEntriesForCompany(company.getId(), group.getId());
		if (groupEntries.size() != 1) {
			System.out.println("There are " + groupEntries.size() + " group entries. There should only be one.");
			//TODO this should cause an error
		}
		return groupEntries.get(0);
	}

	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public ConfidenceGradeService getConfidenceGradeService() {
		return confidenceGradeService;
	}
	public void setConfidenceGradeService(
			ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}
	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public GroupService getGroupService() {
		return groupService;
	}
	public OrderedLineReader getOrderedLineReader() {
		return orderedLineReader;
	}
	public void setOrderedLineReader(OrderedLineReader orderedLineReader) {
		this.orderedLineReader = orderedLineReader;
	}
	public UnorderedLineReader getUnorderedLineReader() {
		return unorderedLineReader;
	}
	public void setUnorderedLineReader(UnorderedLineReader unorderedLineReader) {
		this.unorderedLineReader = unorderedLineReader;
	}
	public UnorderedItemPrefixLineReader getUnorderedItemPrefixLineReader() {
		return unorderedItemPrefixLineReader;
	}
	public void setUnorderedItemPrefixLineReader(
			UnorderedItemPrefixLineReader unorderedItemPrefixLineReader) {
		this.unorderedItemPrefixLineReader = unorderedItemPrefixLineReader;
	}
}
