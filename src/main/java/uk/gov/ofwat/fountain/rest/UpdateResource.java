package uk.gov.ofwat.fountain.rest;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;



import org.joda.time.format.*;
import org.joda.time.DateTime;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.UpdateService;
import uk.gov.ofwat.fountain.domain.Agenda;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.update.Release;
import uk.gov.ofwat.fountain.domain.update.Update;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

@Path("/release")
public class UpdateResource extends RestResource {

	private static Logger logger = LoggerFactory.getLogger(UpdateResource.class);

	UpdateService updateService;
	
	public UpdateService getUpdateService() {
		return updateService;
	}

	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

	@GET
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	@Path("/")
	public Response getAllReleases(@Context SecurityContext securityContext, @Context UriInfo uriInfo){
		logger.debug("invoked getAllReleases");
		List<Release> releases = updateService.getAllReleases();
		logger.debug("finished getLatestUpdates");
		return Response.ok(releases).build();
	}	
	
	
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	@Path("/latest")
	public Response getLatestRelease(@Context SecurityContext securityContext, @Context UriInfo uriInfo){
		logger.debug("invoked getLatestUpdates");
		Release release = updateService.getLatestRelease();
		logger.debug("finished getLatestUpdates");
		return Response.ok(release).build();
	}	
	
	//TODO
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/create")
	public Response createRelease(@BadgerFish Release release, @Context SecurityContext securityContext, @Context UriInfo uriInfo){
		logger.debug("invoked createRelease");
		Release newRelease = updateService.createRelease(release);
		logger.debug("finished createRelease");
		return Response.ok(newRelease).build();		
	};

	//TODO
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/{id}")
	public Response updateRelease(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id, @FormParam("name") String name, @FormParam("version") String version, @FormParam("releaseDate") String strDate, @FormParam("published") Boolean published){
		logger.debug("invoked updateRelease");
		//Parse the release date. 
		//15/Jan/2001 12:00 AM
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
		DateTime date = formatter.parseDateTime(strDate);
		Release release = new Release();
		release.setId(id);
		release.setPublished(published);
		release.setReleaseDate(date.toDate());
		release.setReleaseName(name);
		release.setReleaseVersion(version);
		Release newRelease = updateService.updateRelease(release);
		logger.debug("finished createRelease");
		return Response.ok(newRelease).build();		
	};
	
	@GET
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/{id}")
	public Response getRelease(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		logger.debug("invoked getRelease");
		Release release = updateService.getRelease(id);
		logger.debug("finished getRelease");
		return Response.ok(release).build();		
	};
	
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/{id}/update/")
	public Response addUpdate(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id, @FormParam("title") String title, @FormParam("externalLink") String externalLink, @FormParam("description") String description, @FormParam("sortOrder") Long sortOrder){
		//@Param("title") String title, @FormParam("externalLink") String externalLink, @FormParam("description") String description
		logger.debug("invoked addUpdate");
		Update update = new Update();
		update.setTitle(title);
		update.setSortOrder(sortOrder);
		update.setExternalLink(externalLink);
		update.setDescription(description);
		Update newUpdate = updateService.addUpdate(id, update);
		logger.debug("finished addupdate");
		return Response.ok(newUpdate).build();		
	};
	
	@DELETE
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/update/{id}")
	public Response removeUpdate(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		logger.debug("invoked removeUpdate");
		Update update = updateService.getUpdate(id);
		Boolean success = updateService.deleteUpdate(id);
		logger.debug("finished removeupdate");
		return Response.ok(update).build();		
	};	
	
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})	
	@Path("/publish/{id}")
	public Response publishRelease(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		logger.debug("invoked publishRelease");
		updateService.setPublished(id, true);
		logger.debug("finished publishRelease");
		return Response.ok().build();		
	};
	
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	@Path("/hide/{id}")
	public Response hideRelease(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		logger.debug("invoked hideRelease");
		updateService.setPublished(id, false);
		logger.debug("finished hideRelease");
		return Response.ok().build();		
	};
	
	
}
