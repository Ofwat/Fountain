package uk.gov.ofwat.fountain.api.table.reader;

import java.util.HashMap;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.form.PageSection;

public class Section {
	
	private Map<Integer, Column> columns = new HashMap<Integer, Column>();
	private Integer itemCodeColumn;

	public Section(PageSection pageSection) {
		this.itemCodeColumn = pageSection.getItemCodeColumn();
	}
	
	public Column getColumn(Integer colIdx) {
		return columns.get(colIdx);
	}

	public void addColumn(Integer colIdx, Column column) {
		this.columns.put(colIdx, column);
	}

	public Integer getItemCodeColumn() {
		return itemCodeColumn;
	}

	public void setItemCodeColumn(Integer itemCodeColumn) {
		this.itemCodeColumn = itemCodeColumn;
	}
	

}
