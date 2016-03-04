package uk.gov.ofwat.fountain.domain.chunk;

import java.util.ArrayList;

public class ReportChunkSet {
	
	private String chunkSetId;
	private Integer chunkCount;
	private ArrayList<Chunk> chunks;
	
	public String getChunkSetId(){
		return this.chunkSetId;
	}
	public void setChunkSetId(String chunkSetId){
		this.chunkSetId = chunkSetId;
	}
	public Integer getChunkCount(){
		return this.chunkCount;	
	}
	public void setChunkCount(Integer chunkCount){
		this.chunkCount = chunkCount;
	}
	public void setChunks(ArrayList<Chunk> chunks){
		this.chunks = chunks;
	}
	public ArrayList<Chunk> getChunks(){
		return this.chunks;
	}
}
