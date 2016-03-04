package uk.gov.ofwat.fountain.api

import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.search.FountainSearchIndex;

interface IndexService {
	
	public Boolean index(Class clazz, String ids)
	
	public Boolean index(Class clazz, ArrayList ids)

	public Boolean indexAll(Class clazz)

	public boolean index(Class clazz, Integer id)
	
}