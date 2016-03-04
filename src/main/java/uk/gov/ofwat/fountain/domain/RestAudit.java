package uk.gov.ofwat.fountain.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

@org.mongodb.morphia.annotations.Entity
public class RestAudit {

	@Id
	private ObjectId id;
	private DBObject responseEntity;
	private DBObject audits;
	private HashMap<String,Object> postEntity;
	private ObjectId attachedGridFsResponse;
	private ObjectId attachedGridFsContent;
	private ObjectId spreadsheetGridFsId;
	private String httpMethod;
	private String uri;
	private String user;
	private Date startDate;
	private Date endDate;
	private boolean fountainUsers;
	private boolean fountainEditors;
	private boolean fountainAdmins;
	//logger.info(" " + servletRequest.isUserInRole(""));
	private boolean fountainRunAdmin;
	private String resourceClass;
	private String resourceMethod;
	private DBObject content;
	private String responseCode;
	private String responseText;
	private HashMap<String, String> params;
	
	public DBObject getAudits() {
		return audits;
	}

	public void setAudits(DBObject audits) {
		this.audits = audits;
	}

	public ObjectId getSpreadsheetGridFsId() {
		return spreadsheetGridFsId;
	}

	public void setSpreadsheetGridFsId(ObjectId spreadsheetGridFsId) {
		this.spreadsheetGridFsId = spreadsheetGridFsId;
	}


	public ObjectId getAttachedGridFsContent() {
		return attachedGridFsContent;
	}


	public void setAttachedGridFsContent(ObjectId attachedGridFsContent) {
		this.attachedGridFsContent = attachedGridFsContent;
	}


	public ObjectId getAttachedGridFsResponse() {
		return attachedGridFsResponse;
	}


	public void setAttachedGridFsResponse(ObjectId attachedGridFsResponse) {
		this.attachedGridFsResponse = attachedGridFsResponse;
	}


	public DBObject getResponseEntity() {
		return responseEntity;
	}


	public void setResponseEntity(DBObject responseEntity) {
		this.responseEntity = responseEntity;
	}


	public HashMap<String,Object> getPostEntity() {
		return postEntity;
	}


	public void setPostEntity(HashMap<String,Object> postEntity) {
		this.postEntity = postEntity;
	}


	public HashMap<String, String> getParams() {
		return params;
	}


	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}


	public ObjectId getId() {
		return id;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getResponseText() {
		return responseText;
	}


	public String getHttpMethod() {
		return httpMethod;
	}


	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public boolean isFountainUsers() {
		return fountainUsers;
	}


	public void setFountainUsers(boolean fountainUsers) {
		this.fountainUsers = fountainUsers;
	}


	public boolean isFountainEditors() {
		return fountainEditors;
	}


	public void setFountainEditors(boolean fountainEditors) {
		this.fountainEditors = fountainEditors;
	}


	public boolean isFountainAdmins() {
		return fountainAdmins;
	}


	public void setFountainAdmins(boolean fountainAdmins) {
		this.fountainAdmins = fountainAdmins;
	}


	public boolean isFountainRunAdmin() {
		return fountainRunAdmin;
	}


	public void setFountainRunAdmin(boolean fountainRunAdmin) {
		this.fountainRunAdmin = fountainRunAdmin;
	}


	public String getResourceClass() {
		return resourceClass;
	}


	public void setResourceClass(String resourceClass) {
		this.resourceClass = resourceClass;
	}


	public String getResourceMethod() {
		return resourceMethod;
	}


	public void setResourceMethod(String resourceMethod) {
		this.resourceMethod = resourceMethod;
	}


	public DBObject getContent() {
		return content;
	}


	public void setContent(DBObject content) {
		this.content = content;
	}


	public String getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	public String getRssponseText() {
		return responseText;
	}


	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}

	
	
}
