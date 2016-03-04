package uk.gov.ofwat.fountain.domain.chunk;

import java.util.Map;

public interface Chunkable {
	//Get a Map<String,Obj> of the data to chunk. 
	public Map<String, Object> getChunkableData();
	//Get the class that we will use in AbstractJsonFactory to turn the chunkable data into JSON. 
	public Class getChunkableClass();
}
