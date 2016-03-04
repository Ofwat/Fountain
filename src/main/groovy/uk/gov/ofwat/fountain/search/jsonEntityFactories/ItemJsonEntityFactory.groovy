package uk.gov.ofwat.fountain.search.jsonEntityFactories

import com.google.gson.Gson;

import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;

class ItemJsonEntityFactory implements SearchableType, JsonProducer{
	
	
	
	ReportDao reportDao
	ModelDao modelDao
	TableDao tableDao
	ItemPropertiesDao itemPropertiesDao
	
	String getElasticSearchType(){
		return "item"
	}
	
		
	String getJson(Entity entity, Gson gson){
		//Cast the entity to Type Item.
		Item i = (Item)entity
		
		//Ok - We'll just get the latest version of the item here - We will assume that the previous version have already been indexed...
		ItemProperties ip = itemPropertiesDao.getLatestVersionForItemId(i.id)
		
		//Create the item map
        def item = [id:i.id, code:i.code, unit:i.unit, name:i.name, description:ip.description, definition:ip.definition]       
        def reports = []
		def tables = []
		
		//Look up all the reports for an item
		def reportSummaries = reportDao.findReportsByItemId(i.getId())
		reportSummaries.each { rs ->
			def report = [id:rs.id, name:rs.name]
            reports.add(report)
		}
		//add the reports to the item map. 
		item.put("reports", reports)
		
		//Need to look up all the tables too.
		def tableDomains = tableDao.getTablesForItem(i.id);
		
		//For each table get the model details. 
		tableDomains.each{ td ->
			def model = modelDao.findById(td.modelId)
			def table = [name:td.name, model_name:model.name, model_code:model.code]
			tables.add(table)
		}
		
		item.put("tables", tables)	
		def itemJson = gson.toJson(item)
		return itemJson
	}
	
}
