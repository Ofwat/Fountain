package uk.gov.ofwat.fountain.search.jsonEntityFactories

import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Table

import com.google.gson.Gson;

class TableJsonEntityFactory implements SearchableType, JsonProducer{

	TableDao tableDao
	ItemDao itemDao	
	ItemPropertiesDao itemPropertiesDao
	ModelDao modelDao
		
	String getElasticSearchType(){
		return "table"
	}
	
	String getJson(Entity entity, Gson gson){
		//Cast the entity to Type Item.
		Table t = (Table)entity
		
		//Create the table map
		def table = [id:t.id, name:t.name]
		
		def items = []

		//look up the model for a table.
		def model = modelDao.findById(t.modelId)
		table.put("model_name", model.name)
		table.put("model_code", model.code)
		table.put("model_type", model.modelType.name)
		table.put("model_id", model.id)
				
		//Look up all the items for a table
		def itemDomains = itemDao.findByTableId(t.id)
		itemDomains.each{ id ->
			def ip = itemPropertiesDao.getLatestVersionForItemId(id.id) 
			def item = [id:id.id, version:ip.version, code:id.code, unit:id.unit, description:ip.description, definition:ip.definition, model_id:model.id]
			items.add(item)
		}
		table.put("items", items)
		def tableJson = gson.toJson(table)
		return tableJson
	}
}
