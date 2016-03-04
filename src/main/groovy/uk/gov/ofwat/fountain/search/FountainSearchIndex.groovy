package uk.gov.ofwat.fountain.search;

public enum FountainSearchIndex {
	REPORT("report"),
	ITEM("item"),
	TABLE("table");
	    
	private final String searchIndexName;

	FountainSearchIndex(String searchIndexName) {
        this.searchIndexName = searchIndexName;
    }
	
    public String getSearchIndexName() {
    	return this.searchIndexName;
    }	
}
