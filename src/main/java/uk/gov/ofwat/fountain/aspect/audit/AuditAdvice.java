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

package uk.gov.ofwat.fountain.aspect.audit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.api.MongoService;
import uk.gov.ofwat.fountain.audit.AuditExclusionStrategy;
import uk.gov.ofwat.fountain.dao.RestAuditDao;
import uk.gov.ofwat.fountain.domain.RestAudit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

//import org.elasticsearch.groovy.client.GClient;

@Path("/abc")
@Aspect
@Order(11)
public class AuditAdvice {

	private static int MAX_SIZE_BYTES_RESPONSE = 14000000;
	private static int MAX_SIZE_BYTES_POST = 1000000;
//	private static org.apache.log4j.Logger log = Logger.getLogger(AuditAdvice.class);
	private final static Logger log = LoggerFactory.getLogger(AuditAdvice.class);
//	private static Logger logger = LoggerFactory.getLogger(AuditAdvice.class);

	private RestAuditDao restAuditDao;
	private MongoService mongoService;
	private Gson gson;

	public MongoService getMongoService() {
		return mongoService;
	}

	public void setMongoService(MongoService mongoService) {
		this.mongoService = mongoService;
	}

	public void setRestAuditDao(RestAuditDao restAuditDao) {
		this.restAuditDao = restAuditDao;
	}

	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;

	@Context
	public void setHttpServeletRequest(HttpServletRequest request) {
		servletRequest = request;
	}

	@Context
	public void setHttpServeletResponse(HttpServletResponse response) {
		servletResponse = response;
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.RunResource.*(..))")
	private void run() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.ReportResource.*(..))")
	private void report() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.DataResource.*(..))")
	private void data() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.BannerResource.*(..))")
	private void banner() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.TagResource.*(..))")
	private void tag() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.FileResource.*(..))")
	private void file() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.ItemResource.*(..))")
	private void item() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.CheckoutResource.*(..))")
	private void checkout() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.CacheResource.*(..))")
	private void cache() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.TableResource.*(..))")
	private void table() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.LockResource.*(..))")
	private void lock() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.BasketResource.*(..))")
	private void basket() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.IntervalResource.*(..))")
	private void interval() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.AuditResource.*(..))")
	private void audit() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.ConfidenceGradeResource.*(..))")
	private void confidenceGrade() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.CompanyResource.*(..))")
	private void company() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.GroupResource.*(..))")
	private void group() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.ModelResource.*(..))")
	private void model() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.TeamResource.*(..))")
	private void team() {
	}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.rest.WikiAddressResource.*(..))")
	private void wikiAddress() {
	}

	@Pointcut("wikiAddress() || " + "group() || " + "company() || "
			+ "confidenceGrade() || " + "cache() || "
			+ "lock() || "
			+ "interval() || "
			+
			// "banner() || " +
			"file() || " + 
			"run() || " + "report() || " + "data() || "
			+ "tag() || " + "item() || " + "checkout() || " + "table() || "
			+ "basket() || " + "audit() || " + "model() || " + "team()")
	private void all() {
	}

	/**
	 * List of GET methods that will be audited as well as all POST, PUT above.
	 */
	private ArrayList<String> auditedGetMethods = new ArrayList<String>(
			Arrays.asList("getReport", "getXmlReport", "getExcelReport", "getReportTable"));

	/**
	 * Interceptor to audit the request and response of a call to the Fountain methods
	 * marked above and the GEt methods in the list in 'auditedGetMethods'.
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("uk.gov.ofwat.fountain.aspect.audit.AuditAdvice.all()")
	public Object auditThis(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("AuditAdvice ... Class: "
				+ pjp.getTarget().getClass().getName() + " Method: "
				+ pjp.getSignature().getName());

		// Check that we have a PUT POST or DELETE or GET to selected methods,
		// If not then we will ignore the audit.
		if (!servletRequest.getMethod().equals("PUT")
				&& !servletRequest.getMethod().equals("POST")
				&& !servletRequest.getMethod().equals("DELETE")
				&& !auditedGetMethods.contains(pjp.getSignature().getName())) {
			return (Object) pjp.proceed();
		}

		Response response = null;
		Date startTime = new Date();		
		try{
			response = (Response) pjp.proceed();
			RestAudit audit = doAudit(pjp, startTime, response);
			return response;
		}catch(WebApplicationException webEx){
			log.error("Caught a WebApplicationException in auditAdvice: " + webEx.toString());
			RestAudit audit = doAudit(pjp, startTime, response, webEx);			
			throw webEx;
		}catch(Exception e){
			log.error("Caught an Exception in auditAdvice: " + e.toString());
			//return response;
			throw e;
		}
	}
	
	private RestAudit doAudit(ProceedingJoinPoint pjp, Date startTime, Response response){
		return doAudit(pjp, startTime, response, null);
	}	
	
	private RestAudit doAudit(ProceedingJoinPoint pjp, Date startTime, Response response, Exception e){
		// Build a RestAudit object with a view to persisting.
		RestAudit restAudit = new RestAudit();
		restAudit.setHttpMethod(servletRequest.getMethod());
		restAudit.setUri(servletRequest.getRequestURI());
		restAudit.setUser(servletRequest.getUserPrincipal().getName());
		restAudit.setStartDate(startTime);
		restAudit.setFountainUsers(servletRequest
				.isUserInRole("OFWAT\\Fountain.Users"));
		restAudit.setFountainEditors(servletRequest
				.isUserInRole("OFWAT\\Fountain.Editors"));
		restAudit.setFountainAdmins(servletRequest
				.isUserInRole("OFWAT\\Fountain.Admins"));
		restAudit.setFountainRunAdmin(servletRequest
				.isUserInRole("OFWAT\\G Fountain Run Admin"));
		restAudit.setResourceClass("" + pjp.getTarget().getClass().getName());
		restAudit.setResourceMethod("" + pjp.getSignature().getName());

		// Get the content!
		HashMap<String, Object> postedContent = getContent(pjp);
		// Vars to keep track if we are being sent or receiving a TableDTO
		Boolean postedTableDto = false;
		Boolean responseTableDto = false;
		// Grab the passed parameters and audit.
		try {
			HashMap<String, String> params = getParams(pjp);
			//Persist the RestAudit Item to the MongoDB and get the Key.     
			Key<RestAudit> key = restAuditDao.createUpdate(restAudit);
			//Initialise GSON
			gson = getGson();		
			//Turn posted content to JSON. 
			String jsonPostedContent = gson.toJson(postedContent);
			// We have to make special dispensation for tableDtos as they may be massive.
			if (params.get("tableDto") != null) {
				params.remove("tableDto"); // this is still audited as a JSON doc as the 'Content' and will only be in the params if it's a POST/PUT
				postedTableDto = true;
			}			
			//If there is a tableDto, persist the posted content to GridFS then strip the tableDto.
			if(postedTableDto){				
				ObjectId contentMongoId = mongoService.persistToGridFS(jsonPostedContent, servletRequest.getMethod() + ":" + pjp.getSignature().getName() + ".json", "application/json", "RestAudit");
				restAudit.setAttachedGridFsContent(contentMongoId);
				restAudit.setContent((DBObject) JSON.parse("{'GridFSData':true}"));
			}else{
				DBObject contentDbo = (DBObject) JSON.parse(jsonPostedContent);
				restAudit.setContent(contentDbo);
			}			
			//finish setting properties.
			restAudit.setAudits(getAudits(response));
			restAudit.setParams(params);
			restAuditDao.createUpdate(restAudit);
			
			//if e is not null we already have an exception, lets add it to the log.
			if((e != null) && (e.getClass() == WebApplicationException.class)){
				WebApplicationException webException = (WebApplicationException)e;
				response = webException.getResponse();
				restAudit.setResponseText(ExceptionUtils.getFullStackTrace(webException));
				restAudit.setResponseCode(Integer.toString(response.getStatus()));							
			}else{			
				if (response instanceof Response) {
					restAudit.setResponseCode(Integer
							.toString(((Response) response).getStatus()));
	
					// This entity can be a number of different DTOs, Lists or Domain Objects - we will build a JSON representation to save it.
					Object responseEntity = ((Response) response).getEntity();
					if((responseEntity != null) && (responseEntity.getClass() != null)){
						Class responseEntityClass = responseEntity.getClass();
						String responseEntityClassString = responseEntityClass.toString();
						if (responseEntityClassString.equals("class uk.gov.ofwat.fountain.rest.dto.Report") || responseEntityClassString.equals("class uk.gov.ofwat.fountain.rest.dto.TableDto")){
							responseTableDto = true;
						}
					}
					
					String jsonResponseContent = gson.toJson(responseEntity);
					//If we are sending a large DTO back persist this too!
					if(responseTableDto){
						ObjectId reponseMongoId = mongoService.persistToGridFS(jsonResponseContent, String.valueOf(response.getStatus()) + ":" + pjp.getSignature().getName() + ".json", "application/json", "RestAudit");
						restAudit.setAttachedGridFsResponse(reponseMongoId);
						restAudit.setResponseEntity((DBObject) JSON.parse("{'GridFSData':true}"));
					}else{
						HashMap<String, Object> responseEntityMap = new HashMap<String, Object>();
						responseEntityMap.put("entity", responseEntity);
						String jsonResponse = gson.toJson(responseEntityMap);
						restAudit.setResponseEntity((DBObject) JSON.parse(jsonResponse));						
					}
				}
			}
			restAudit.setEndDate(new Date());
			restAuditDao.createUpdate(restAudit);				
		} catch (Exception e1) {
			// log the exception
			if (MongoException.class.isAssignableFrom(e1.getClass())) {
				log.error("\n\n\n*** Potentially not logged anything - caught a Mongo Exception:" + e1.toString() + "\n\n\n***");
			} else {
				try{
					log.error("\n\n\n*** Caught an exception in audit advice, trying to persist a record: " + e1.toString() + " ***\n\n\n");
					restAudit.setResponseText(ExceptionUtils.getFullStackTrace(e1));
					restAudit.setResponseCode("ERR");
					restAudit.setEndDate(new Date());
					restAuditDao.createUpdate(restAudit);
				}catch(Exception e2){
					log.error("\n\n\n*** Unable to persist audit record correctly: " + e2.toString() + " ***\n\n\n");					
				}
			}
		}
		return restAudit;
	}
	
	
	
	private Gson getGson(){
		if(gson == null){
			gson = new GsonBuilder().setExclusionStrategies(new AuditExclusionStrategy()).create();
		}
		return gson;
	}
	
	private DBObject getAudits(Response response){
		//Get a list of the Fountain audits that have been placed on the response if any! 
		if(response != null){
			MultivaluedMap<String, Object> metaData = response.getMetadata();
			List<Object> audits = metaData.get("audits");
			metaData.remove("audits");	
			//Change audits to DBObject
			if((audits != null) && (audits.get(0) != null)){
				DBObject auditsDBObject = (DBObject) JSON.parse(getGson().toJson(audits.get(0)));
				//restAudit.setAudits(auditsDBObject);
				return auditsDBObject;
			}	
		}
		return null;
	}
	
	private HashMap<String, String> getParams(ProceedingJoinPoint pjp) {
		HashMap<String, String> params = new HashMap<String, String>();
		Object[] args = pjp.getArgs();
		Signature signature = pjp.getStaticPart().getSignature();
		if (signature instanceof MethodSignature) {
			MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			Annotation[][] parameterAnnotations = method
					.getParameterAnnotations();

			String[] parameterNames = ms.getParameterNames();
			for (int i = 0; i < args.length; i++) {
				String argsString = "";
				if(args[i] != null){
					argsString = args[i].toString();
				}			
				params.put(parameterNames[i], argsString);
			}
		}
		return params;
	}

	private HashMap<String, Object> getContent(ProceedingJoinPoint pjp) {
		HashMap<String, Object> content = new HashMap<String, Object>();
		Object[] args = pjp.getArgs();
		Signature signature = pjp.getStaticPart().getSignature();
		if (signature instanceof MethodSignature) {
			MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			Annotation[][] parameterAnnotations = method
					.getParameterAnnotations();
			String[] parameterNames = ms.getParameterNames();
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				if (org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput.class == parameterTypes[i] ||
					javax.ws.rs.core.SecurityContext.class == parameterTypes[i] ||
					javax.ws.rs.core.UriInfo.class == parameterTypes[i] ||
					javax.servlet.http.HttpServletRequest.class == parameterTypes[i]) {
					continue;
				}
				content.put(parameterNames[i], args[i]);
			}
		}
		return content;
	}

	private String getBadgerFishParameterValue(String paramName, Object object,
			Annotation[] annotations) {
		String value = "";
		for (Annotation annotation : annotations) {
			if (annotation instanceof BadgerFish) {
				// exclusions:
				// @BadgerFish List<Lock> locks
				BadgerFish myAnnotation = (BadgerFish) annotation;
				value = value + object.toString();
				System.out.println("BadgerFish type: "
						+ object.getClass().getSimpleName() + " name: "
						+ paramName + " value: " + object.toString());
			}
		}
		return value;
	}

}
