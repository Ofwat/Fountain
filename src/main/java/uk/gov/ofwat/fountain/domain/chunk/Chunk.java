package uk.gov.ofwat.fountain.domain.chunk;

public class Chunk {
	String id;
	String fileRef;
	

	public String getChunkId() {
		return this.id;
	}


	public void setChunkId(String id) {
		this.id = id;		
	}


	public String getChunkFileRef() {
		return this.fileRef;
	}


	public void setChunkFileRef(String fileRef) {
		this.fileRef = fileRef;
	}
}
