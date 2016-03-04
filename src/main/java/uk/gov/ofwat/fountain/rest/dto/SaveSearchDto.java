package uk.gov.ofwat.fountain.rest.dto;

import java.util.Date;

public class SaveSearchDto {
	Long id;
	String searchString;
	Boolean myDataOnly;
	Boolean includeDeleted;
	String searchName;
	Date createdAt;
	String user;
	Boolean favorite;
	Boolean sortByDate;
	String sortOrder;
	String searchType;
	
	public String getSearchType(){
		return this.searchType;
	}
	
	public void setSearchType(String searchType){
		this.searchType = searchType;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getSortByDate() {
		return sortByDate;
	}
	public void setSortByDate(Boolean sortByDate) {
		this.sortByDate = sortByDate;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Boolean getMyDataOnly() {
		return myDataOnly;
	}
	public void setMyDataOnly(Boolean myDataOnly) {
		this.myDataOnly = myDataOnly;
	}
	public Boolean getIncludeDeleted() {
		return includeDeleted;
	}
	public void setIncludeDeleted(Boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	
	
}
