package uk.gov.ofwat.fountain.search.jsonEntityFactories

import java.security.interfaces.RSAKey;

import com.google.gson.Gson;

import uk.gov.ofwat.fountain.api.IndexService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.TagService;
import uk.gov.ofwat.fountain.api.report.ReportItem;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.search.wrapper.ItemSearchWrapper;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

class ReportJsonEntityFactory implements SearchableType, JsonProducer{
	
	ReportDao reportDao
	ReportService reportService
	IndexService indexService
	TagService tagService
	
	String getElasticSearchType(){
		return "report"
	}
	
	String getJson(Entity entity, Gson gson){
		ReportSearchWrapper rsw= (ReportSearchWrapper)entity
		
		def reportSummary = reportDao.findSummaryById(rsw.id)
		def reportDefinition = reportService.getReportDefinition(rsw.id)
		
		def date = new Date(rsw.lastModified.getTime())
		def dateStr = date.format("yyyy-MM-dd'T'HH:mm:ss")
		def tags = tagService.getTags(reportDefinition)
		def report = [id:rsw.id, name:rsw.name, tags:tags.toArray(), user:rsw.user, lastModified:dateStr, deleted:rsw.deleted, public:rsw.publicReport , groupId:rsw.groupId, iplRun:rsw.iplRun, readOnly:rsw.readOnly, description:rsw.description]
	
		//get all the items and item properties.
		def items = []
		
		List<ItemSearchWrapper> reportItems = rsw.items
		
		reportItems.each{ ri ->
			def item = [id:ri.id, code:ri.code, unit:ri.unit, description:ri.description, definition:ri.definition, model_id:ri.modelId, version:ri.version]
			items.add(item)
			//We need to re-index all the items too!
			indexService.index(Item.class, ri.id)
		}
		
		report.put("items", items)
		
		//Convert these to a DTO to store in ES or the correct JSON to store in ES.
		def json = gson.toJson(report);
		return json
	}
	
	
}
