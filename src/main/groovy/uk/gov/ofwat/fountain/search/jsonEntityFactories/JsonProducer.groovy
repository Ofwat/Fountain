package uk.gov.ofwat.fountain.search.jsonEntityFactories

import uk.gov.ofwat.fountain.domain.Entity;
import com.google.gson.Gson;

interface JsonProducer{
	public String getJson(Entity entity, Gson gson)
}
