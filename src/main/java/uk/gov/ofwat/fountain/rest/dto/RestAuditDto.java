package uk.gov.ofwat.fountain.rest.dto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class RestAuditDto {
	@Id
	private ObjectId id;
	
	private Object responseEntity;
	
	private Object postEntity;

	
	public Object getPostEntity() {
		return postEntity;
	}

	public void setPostEntity(Object postEntity) {
		this.postEntity = postEntity;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Object getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(Object responseEntity) {
		this.responseEntity = responseEntity;
	}
	
}
