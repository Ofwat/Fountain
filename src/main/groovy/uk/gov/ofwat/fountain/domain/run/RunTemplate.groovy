package uk.gov.ofwat.fountain.domain.run

import org.apache.commons.lang.NotImplementedException
import uk.gov.ofwat.fountain.domain.Model;

class RunTemplate {
	int id
	String name
	String description
	Date created
	String createdBy
	ArrayList<Model> models;
	ArrayList<Integer> modelIds;
	
	public RunTemplate() {
		models = new ArrayList<Model>();
		modelIds = new ArrayList<Integer>();
	}
	
	//TODO These need to maintain order of the items in the arraylist/shuffle items. 
	public void addModel(Model model){
		throw new NotImplementedException()
	}	
		
	public void removeModel(Model model){
		throw new NotImplementedException()
	}
	
}
