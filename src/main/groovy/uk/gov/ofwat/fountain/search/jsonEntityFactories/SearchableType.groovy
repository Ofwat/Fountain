package uk.gov.ofwat.fountain.search.jsonEntityFactories

interface SearchableType extends JsonProducer{
	public String getElasticSearchType()
}
