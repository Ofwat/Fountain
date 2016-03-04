package uk.gov.ofwat.fountain.search.jsonEntityFactories

import clover.gnu.cajo.utils.extra.Implements;

import com.google.gson.Gson;

import uk.gov.ofwat.fountain.domain.Entity;

class DefaultJsonEntityFactory implements SearchableType, JsonProducer{

	String getElasticSearchType(){
		return "default"
	}
	
	String getJson(Entity Entity, Gson gson){
		String json = gson.toJson(entity)
		return json
	}
	
}
