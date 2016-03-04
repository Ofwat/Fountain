package uk.gov.ofwat.fountain.rest.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.dao.RestAuditDao;
import uk.gov.ofwat.fountain.domain.RestAudit;

@Provider
@ServerInterceptor
public class AuditInterceptor implements PreProcessInterceptor {
	private static Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);

	private RestAuditDao restAuditDao;
	
	public void setRestAuditDao(RestAuditDao restAuditDao) {
		this.restAuditDao = restAuditDao;
	}

	@Context
    HttpServletRequest servletRequest;

    public ServerResponse preProcess(HttpRequest request,
            ResourceMethod resourceMethod) throws Failure,
            WebApplicationException {
    	
        if (!servletRequest.getMethod().equals("PUT") &&
        	!servletRequest.getMethod().equals("POST")) {
        	return null;
        }
        	
        RestAudit restAudit = new RestAudit();
        restAudit.setHttpMethod(servletRequest.getMethod());
        restAudit.setUri(servletRequest.getRequestURI());
        restAudit.setUser(servletRequest.getUserPrincipal().getName());
        restAudit.setStartDate(new Date());
        restAudit.setEndDate(new Date());
        restAudit.setFountainUsers(servletRequest.isUserInRole("OFWAT\\Fountain.Users"));
        restAudit.setFountainEditors(servletRequest.isUserInRole("OFWAT\\Fountain.Editors"));
        restAudit.setFountainAdmins(servletRequest.isUserInRole("OFWAT\\Fountain.Admins"));
//        logger.info(" " + servletRequest.isUserInRole(""));
        restAudit.setFountainRunAdmin(servletRequest.isUserInRole("OFWAT\\G Fountain Run Admin"));
        restAudit.setResourceClass(resourceMethod.getClass().getName());
        restAudit.setResourceMethod(resourceMethod.getMethod().getName());
        restAudit.setResponseCode("");
        restAudit.setResponseText("");

// play        
//      restAudit.setResponseText("" + response.getEntity().toString());
      Method method = resourceMethod.getMethod();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		Class[] parameterTypes = method.getParameterTypes();
		
		int i=0;
		for(Annotation[] annotations : parameterAnnotations){
		  Class parameterType = parameterTypes[i++];
		
		  for(Annotation annotation : annotations){
		    if(annotation instanceof BadgerFish){
		    	BadgerFish myAnnotation = (BadgerFish) annotation;
		        System.out.println("param: " + parameterType.getName());
		    }
		  }
		}
//end play		  

        restAuditDao.createUpdate(restAudit);
        
        return null;
    }
}
