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

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.ConfidenceGradeService;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;

@Path("/confidencegrades")
public class ConfidenceGradeResource extends RestResource {
	
	private ConfidenceGradeService confidenceGradeService;
	private static Logger logger = LoggerFactory.getLogger(ConfidenceGradeResource.class);
	

	public void setConfidenceGradeService(ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	public ConfidenceGradeResource() {		
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="Confidence-Grades")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getConfidenceGrades(){
		logger.debug("invoked getConfidenceGrades");
		List<ConfidenceGrade> cgs =  confidenceGradeService.getConfidenceGrades();
		logger.debug("finished getConfidenceGrades");
		GenericEntity<List<ConfidenceGrade>> entity = new GenericEntity<List<ConfidenceGrade>>(cgs){};
		return Response.ok(entity).build();
	}
}
