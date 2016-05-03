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

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.xmlbeans.XmlObject;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.api.FileService;
import uk.gov.ofwat.fountain.api.ListException;
import uk.gov.ofwat.fountain.api.TableService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.rest.dto.ImportResponseDto;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;
import uk.gov.ofwat.fountain.tools.AuditAndDataImporter;
import uk.gov.ofwat.model2.ModelDocument;

@Path("/file")
public class FileResource extends RestResource {

	private FileService fileService;
	
	private AuditAndDataImporter auditAndDataImporter;
	private static Logger logger = LoggerFactory.getLogger(FileResource.class);
	private DaoCacheService daoCacheService;
	private UserService userService;
	private TableService tableService;
	private FileResourceHelper fileResourceHelper;


	public void setFileResourceHelper(FileResourceHelper fileResourceHelper) {
		this.fileResourceHelper = fileResourceHelper;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DaoCacheService getDaoCacheService() {
		return daoCacheService;
	}

	public void setDaoCacheService(DaoCacheService daoCacheService) {
		this.daoCacheService = daoCacheService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	public void setAuditAndDataImporter(AuditAndDataImporter auditAndDataImporter) {
		this.auditAndDataImporter = auditAndDataImporter;
	}


	public FileResource() {
	}
	
	
	@POST
	@Path("/dictionary")
	@Consumes("multipart/form-data")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response uploadDictionary(@Context SecurityContext securityContext,
									 @Context HttpServletRequest httpServletRequest,
									 MultipartFormDataInput input
	) throws Exception {
		logger.debug("started uploadDictionary");
		daoCacheService.disableCache();

		ImportResponseDto responseDto = new ImportResponseDto();
		List<String> results = null;
	
		
		for (InputPart part: input.getParts()) {
			
			if (part.getMediaType().equals(MediaType.TEXT_XML_TYPE)){
				
				XmlObject xmlObjExpected =
					XmlObject.Factory.parse(part.getBodyAsString());

				results = fileService.processDictionary(xmlObjExpected);
			}

		}
		
		// make some info to go back to the browser - if it worked or not (successful)
		if(null == results || results.isEmpty()){
			// success
			responseDto.setSuccess(true);
		}
		else{
			responseDto.setSuccess(false);
			responseDto.setNotices(results);
		}
		
		URI responseURI = new URI("../jsp/protected/dictionaryUploadResponse.jsp");
		httpServletRequest.getSession().setAttribute("results", responseDto);
		daoCacheService.enableCache();
		logger.debug("finished uploadDictionary");
		return Response.seeOther(responseURI).build();
	}
	
	
	@POST
	@Path("/table")
	@Consumes("multipart/form-data")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response uploadTable(@Context SecurityContext securityContext,
									 @Context HttpServletRequest httpServletRequest,
									 MultipartFormDataInput input) throws Exception {
		logger.debug("started uploadTable");
		User user = userService.getUser(securityContext);
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);

		ImportResponseDto responseDto = new ImportResponseDto();
		
		InputStream excelFileInputStream = fileResourceHelper.extractExcelFileInputStream(input);
		String uploadFileName = fileResourceHelper.getFileName(input);
		responseDto.setUploadFileName(uploadFileName);
		if (null != excelFileInputStream) {
			TableUploadMetaData metaData = new TableUploadMetaData(input);
			metaData.setUploadFileName(uploadFileName);
			metaData = tableService.uploadTable(metaData, excelFileInputStream, user, roleChecker);
			
			// make some info to go back to the browser
			if(metaData.getErrors().isEmpty()){
				// success
				responseDto.setSuccess(true);
			}
			else{
				// failure
				responseDto.setSuccess(false);
				responseDto.addNotices(metaData.getErrors());
			}
			responseDto.addNotices(metaData.getCellWarnings());
			responseDto.addNotices(metaData.getResults());
		}
		else {
			responseDto.setSuccess(false);
			List<String> error = new ArrayList<String>();
			error.add("Excel file may be open. Please make sure that the file is closed before import.");
			error.add("File may not be an Excel xlsx file. Please note that xls files are not valid.");
			responseDto.addNotices(error);
		}

		responseDto.setLogFileId(fileService.writeTableUploadLogFile(responseDto));
		
		URI responseURI;
		if (responseDto.isSuccess()) {
			responseURI = new URI("../jsp/protected/tableUploadResponseSuccess.jsp");
		}
		else {
			responseURI = new URI("../jsp/protected/tableUploadResponseFailure.jsp");
		}
		httpServletRequest.getSession().setAttribute("results", responseDto);
		logger.debug("finished uploadTable");
		return Response.seeOther(responseURI).build();
	}

	@GET
	@Path("/table/log/{id}")
	@Produces({"text/plain"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response getTableUploadLog(@PathParam("id") long id) throws Exception {
		logger.debug("started getTableUploadLog");
		
		File file = fileService.readTableUploadLogFile(id);
		
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		logger.debug("finished getTableUploadLog");
		return response.build();		
	}

	
	@POST
	@Path("/model")
	@Consumes("multipart/form-data")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response uploadModel(@Context SecurityContext securityContext,
								@Context HttpServletRequest httpServletRequest,
			                     MultipartFormDataInput input)throws Exception{
		logger.debug("started uploadModel");
		daoCacheService.disableCache();

		ImportResponseDto responseDto = new ImportResponseDto();
		List<String> modelResults = null;
		List<String> modelFormResults = null;
		for(InputPart part: input.getParts()){
			if (part.getMediaType().equals(MediaType.TEXT_XML_TYPE)){
				
				ModelDocument md = ModelDocument.Factory.parse(part.getBodyAsString());
				try{
					modelResults = fileService.processModel(md);
				}
				catch(ListException le){
					// no point in carrying on as the first stage failed
					modelResults = le.getErrors();
					break;
				}
				try{
					modelFormResults = fileService.processModelForms(md);
				}
				catch(ListException le){
					modelFormResults = le.getErrors();
					break;
				}
				break; // there should only be one model
			}
		}
		
		// work out what the results were and combine the two into a single list
		if(null != modelResults  && null != modelFormResults){
			// combine the two
			modelResults.addAll(modelFormResults);
		}
		else if(null != modelFormResults){
			modelResults = modelFormResults;
		}
		// by this point modelResults contains all the results
		if(null == modelResults || modelResults.isEmpty()){
			// everything's groovy
			responseDto.setSuccess(true);
		}
		else{
			responseDto.setSuccess(false);
			responseDto.setNotices(modelResults);
		}
		
		URI responseURI = new URI("../jsp/protected/modelUploadResponse.jsp");
		httpServletRequest.getSession().setAttribute("results", responseDto);
		
		daoCacheService.enableCache();
		logger.debug("finished uploadModel");
		return Response.seeOther(responseURI).build();
	}
	
	@POST
	@Path("/modelJSPs")
	@Consumes("multipart/form-data")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response generateJSPs(@Context SecurityContext securityContext,
								@Context HttpServletRequest httpServletRequest,
			                     MultipartFormDataInput input)throws Exception{
		
		logger.debug("started generateJSPs");
		daoCacheService.disableCache();

		ImportResponseDto responseDto = new ImportResponseDto();
		List<String> modelResults = null;
		List<String> modelFormResults = null;
		for(InputPart part: input.getParts()){
			if (part.getMediaType().equals(MediaType.TEXT_XML_TYPE)){
				
				ModelDocument md = ModelDocument.Factory.parse(part.getBodyAsString());
				ModelDocument.Model model = md.getModel();
				try{
					modelFormResults = fileService.processModelForms(model);
				}
				catch(ListException le){
					modelFormResults = le.getErrors();
					break;
				}
				break; // there should only be one model
			}
		}
		
		// work out what the results were and combine the two into a single list
		if(null != modelResults  && null != modelFormResults){
			// combine the two
			modelResults.addAll(modelFormResults);
		}
		else if(null != modelFormResults){
			modelResults = modelFormResults;
		}
		// by this point modelResults contains all the results
		if(null == modelResults || modelResults.isEmpty()){
			// everything's groovy
			responseDto.setSuccess(true);
		}
		else{
			responseDto.setSuccess(false);
			responseDto.setNotices(modelResults);
		}
		
		URI responseURI = new URI("../jsp/protected/modelUploadResponse.jsp");
		httpServletRequest.getSession().setAttribute("results", responseDto);

		daoCacheService.enableCache();
		logger.debug("Finished generateJsps");
		return Response.seeOther(responseURI).build();
	}
	
	@POST
	@Path("/data")
	@Consumes("multipart/form-data")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response uploadAuditData(@Context SecurityContext securityContext,
			@Context HttpServletRequest httpServletRequest,
            MultipartFormDataInput input)throws Exception{
		// TODO	use responseDto like the other file recsource methods. 
		logger.debug("started uploadAuditData");
		daoCacheService.disableCache();

		ImportResponseDto responseDto = new ImportResponseDto();
		String errorMessage = null;

		for(InputPart part: input.getParts()){
			if (part.getMediaType().equals(MediaType.TEXT_XML_TYPE)){
				
				AuditsDocument ad = AuditsDocument.Factory.parse(part.getBodyAsString());
				try {
					auditAndDataImporter.importData(ad);
				} catch (Exception e) {
					errorMessage = e.getMessage();
					break;
				}	
				
				break;
			}
		}
		
		if(null == errorMessage){
			responseDto.setSuccess(true);
		}
		else{
			responseDto.setSuccess(false);
			responseDto.addNotice(errorMessage);
		}

		URI responseURI = new URI("../jsp/protected/dataUploadResponse.jsp");
		httpServletRequest.getSession().setAttribute("results", responseDto);

		daoCacheService.enableCache();
		logger.debug("finished uploadAuditData");
		return Response.seeOther(responseURI).build();
	}
	
	@POST
	@Path("/bulkUpload")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response bulkDataUpload(@Context SecurityContext securityContext,
			                       @Context HttpServletRequest httpServletRequest)
								throws Exception{
		logger.debug("started bulkDataUpload");
		daoCacheService.disableCache();

		List<String> results = auditAndDataImporter.bulkImport();
		ImportResponseDto resp = new ImportResponseDto();
		resp.setNotices(results);
		resp.setSuccess(results.size() == 0);
		URI responseURI = new URI("../jsp/protected/dataUploadResponse.jsp");
		
		// put results where they can be read by the jsp
		httpServletRequest.getSession().setAttribute("results", resp);
		
		daoCacheService.enableCache();
		logger.debug("finished bulkDataUpload");
		return Response.seeOther(responseURI).build();
	}
		
	

	@POST
	@Path("/bulkModelUpload")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response bulkModelUpload(@Context SecurityContext securityContext,
			                       @Context HttpServletRequest httpServletRequest)
								throws Exception{
		logger.debug("started bulkModelUpload");
		daoCacheService.disableCache();

		List<String> results = fileService.bulkModelImport();
		ImportResponseDto resp = new ImportResponseDto();
		resp.setNotices(results);
		resp.setSuccess(results.size() == 0);
		URI responseURI = new URI("../jsp/protected/modelUploadResponse.jsp");
		
		// put tresults where they can be read by the jsp
		httpServletRequest.getSession().setAttribute("results", resp);
		
		daoCacheService.enableCache();
		logger.debug("finished bulkModelUpload");
		return Response.seeOther(responseURI).build();
	}
		
	

}
