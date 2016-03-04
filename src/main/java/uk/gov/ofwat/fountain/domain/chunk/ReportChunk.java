package uk.gov.ofwat.fountain.domain.chunk;

public class ReportChunk {

	String chunkId;
	String fileRef;
	

	public String getChunkId() {
		return this.chunkId;
	}


	public void setChunkId(String id) {
		this.chunkId = id;		
	}

	public String getChunkFileRef() {
		return this.fileRef;
	}

	public void setChunkFileRef(String fileRef) {
		this.fileRef = fileRef;
	}
	
}
