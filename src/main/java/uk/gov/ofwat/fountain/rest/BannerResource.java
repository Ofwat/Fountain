/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.rest;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.rest.dto.BannerDto;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;

@Path("/banner")
public class BannerResource extends RestResource implements ApplicationContextAware{

	private static Logger logger = LoggerFactory.getLogger(BannerResource.class);
	private UserService userService;
	private ApplicationContext applicationContext = null;	
	
	public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext = applicationContext;
    }		

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BannerResource(){
	}
	
	@GET
	@Produces({"application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})	
	public Response getBannerInformation(@Context SecurityContext securityContext){  
		logger.debug("Getting banner information");
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		User user = userService.getUser(securityContext);
		HashMap<String, Boolean> roles = new HashMap<String, Boolean>();
		
		if(rc.isUserInRole("OFWAT\\Fountain.Users")){
			roles.put("OFWAT\\Fountain.Users", true);
		}
		if(rc.isUserInRole("OFWAT\\Fountain.Editors")){
			roles.put("OFWAT\\Fountain.Editors", true);
		}
		if(rc.isUserInRole("OFWAT\\Fountain.Admins")){
			roles.put("OFWAT\\Fountain.Admins", true);
		}
		if(rc.isUserInRole("OFWAT\\G Fountain Run Admin")){
			roles.put("OFWAT\\G Fountain Run Admin", true);
		}		
			    
		BannerDto bdto = new BannerDto();
		bdto.setTheme(getThemeValue("colourscheme"));
		bdto.setRoles(roles);
		bdto.setRemoteUserName(user.getName());
		ResponseBuilder responseBuilder = Response.ok(bdto);
		return responseBuilder.build();
	}
	
	private String getThemeValue(String property){
		Properties props = new Properties();
		ClassLoader sysClassLoader1 = applicationContext.getClassLoader();
		URL url = sysClassLoader1.getResource("theme.properties");
		try {
			props.load(url.openStream());
		} catch (Exception e) {
			logger.error("Unable to load theme.properties from classpath!");
			e.printStackTrace();
		}
		String result = props.getProperty(property);
		return result;
	}
	
}

