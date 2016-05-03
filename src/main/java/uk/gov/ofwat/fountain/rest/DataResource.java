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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.AuditService;
import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.form.DataKey;

@Path("/data")
public class DataResource extends RestResource {

	private AuditService auditService;
	private static Logger logger = LoggerFactory.getLogger(DataResource.class);
	
	
	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public DataResource() {
	}
	
	@GET
	@Path("auditedValues/{id}")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getAuditedValues(@PathParam("id") String id) {
		logger.debug("invoked getAuditedValues");
		DataKey dataKey = new DataKey(id);
		List<AuditedValue> avs = auditService.getAuditedValues(dataKey.getItemIdInteger(), dataKey.getIntervalIdInteger(), dataKey.getCompanyIdInteger(), dataKey.getGroupEntryIdInteger());
		logger.debug("finished getAuditedValues");
		GenericEntity<List<AuditedValue>> entity = new GenericEntity<List<AuditedValue>>(avs){};
		return Response.ok(entity).build();		
	}



}
