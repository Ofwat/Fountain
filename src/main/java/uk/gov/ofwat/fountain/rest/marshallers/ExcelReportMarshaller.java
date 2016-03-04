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

package uk.gov.ofwat.fountain.rest.marshallers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import uk.gov.ofwat.fountain.api.report.POIReportWriter;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;

@Provider
@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
public class ExcelReportMarshaller implements MessageBodyWriter<ReportDto>{

	@Context
	protected Providers providers;
	
	private POIReportWriter POIWriter;

	public void setPOIReportWriter(POIReportWriter pOIReportWriter) {
		POIWriter = pOIReportWriter;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object, java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	public long getSize(ReportDto arg0, Class<?> arg1, Type arg2, Annotation[] arg3,
			MediaType arg4) {
		// let the client worry about the message size
		return -1;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#isWriteable(java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	public boolean isWriteable(Class<?> marshaleeClass, 
			                   Type marshaleeType, 
			                   Annotation[] annotations,
			                   MediaType mediaType) {
		// currently we can only handle dataTable
		return marshaleeClass.equals(ReportDto.class);
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object, java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap, java.io.OutputStream)
	 */
	public void writeTo(ReportDto tables, 
						Class<?> claz, 
						Type type, 
						Annotation[] annotations,
						MediaType mediaType, 
						MultivaluedMap<String, Object> mvm,
						OutputStream stream) throws IOException, WebApplicationException {
		try{
			POIWriter.streamTableToExcel(tables, stream);
		}
		catch(IOException ioe){
			System.out.println("IOE: " + ioe.getMessage());
		}
	}
	
}
