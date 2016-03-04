package uk.gov.ofwat.fountain.api

import uk.gov.ofwat.fountain.domain.chunk.ChunkSet
import uk.gov.ofwat.fountain.domain.chunk.Chunkable
import uk.gov.ofwat.fountain.rest.dto.DataDto

interface ChunkService {
	
	ChunkSet doChunking(Chunkable chunkable);
	
	List getChunk(chunkSetId, chunkId);
	
	Boolean checkIfChunked();
	
}
