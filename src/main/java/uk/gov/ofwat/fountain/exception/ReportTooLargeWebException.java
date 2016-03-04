package uk.gov.ofwat.fountain.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ReportTooLargeWebException extends WebApplicationException {
	public ReportTooLargeWebException(String message){
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message).type(MediaType.APPLICATION_JSON).build());		
	}
}
