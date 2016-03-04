package uk.gov.ofwat.fountain.search.jsonEntityFactories

import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.api.report.ReportWriter;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.chunk.ReportChunkSet;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

class AbstractJsonEntityFactory {
	private ItemJsonEntityFactory itemJsonEntityFactory
	private ReportJsonEntityFactory reportJsonEntityFactory
	private TableJsonEntityFactory tableJsonEntityFactory
	private DefaultJsonEntityFactory defaultJsonEntityFactory
	private ReportChunkJsonEntityFactory reportChunkJsonEntityFactory
	
	public void setReportChunkJsonEntityFactory(ReportChunkJsonEntityFactory reportChunkJsonEntityFactory) {
		this.reportChunkJsonEntityFactory = reportChunkJsonEntityFactory
	}
	
	public void setItemJsonEntityFactory(ItemJsonEntityFactory itemJsonEntityFactory) {
		this.itemJsonEntityFactory = itemJsonEntityFactory
	}
	
	public void setReportJsonEntityFactory(ReportJsonEntityFactory reportJsonEntityFactory) {
		this.reportJsonEntityFactory = reportJsonEntityFactory
	}
	
	public void setTableJsonEntityFactory(TableJsonEntityFactory tableJsonEntityFactory) {
		this.tableJsonEntityFactory = tableJsonEntityFactory
	}
	
	public void setDefaultJsonEntityFactory(DefaultJsonEntityFactory defaultJsonEntityFactory) {
		this.defaultJsonEntityFactory = defaultJsonEntityFactory
	}
	
	def getJsonEntityFactory(Class clazz){
		switch(clazz){
			case ReportChunkSet:
				return reportChunkJsonEntityFactory
			case ReportSearchWrapper:
				return reportJsonEntityFactory
			case Item:
				return itemJsonEntityFactory
			case Table:
				return tableJsonEntityFactory
			default:
				return defaultJsonEntityFactory
		}
	}
}
