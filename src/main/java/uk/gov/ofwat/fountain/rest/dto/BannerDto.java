package uk.gov.ofwat.fountain.rest.dto;

import java.util.HashMap;
/**
 * DTO class to hold the data to be passed back from the server to provide data for banner.  
 * @author james.toddington
 *
 */
public class BannerDto {
	
	private String remoteUserName;
	private HashMap<String, Boolean> roles;
	private String theme;

	public void setRoles(HashMap<String, Boolean> roles){
		this.roles = roles;
	}

	public HashMap<String, Boolean> getRoles(){
		return this.roles;
	}
	
	public String getRemoteUserName() {
		return remoteUserName;
	}

	public void setRemoteUserName(String remoteUserName) {
		this.remoteUserName = remoteUserName;
	}	

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}	

}
