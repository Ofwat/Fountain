package uk.gov.ofwat.fountain.rest.marshallers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.api.table.POITableReader;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.User;

public class ExcelDataTableUnmarshaller implements MessageBodyReader<List<DataTable>> {

	private POITableReader poiReader;
	private User user;
	private RoleChecker roleChecker;
	
	public void setPOITableReader(POITableReader pOITableReader) {
		poiReader = pOITableReader;
	}

	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO add implementation
		return true;
	}

	public List<DataTable> readFrom(	Class<List<DataTable>> claz, 
			Type type, 
			Annotation[] annotations,
			MediaType mediaType, 
			MultivaluedMap<String, String> mvm,
			InputStream inputStream) throws IOException, WebApplicationException {

		List<DataTable> dataTables = new ArrayList<DataTable>();
		
//		dataTables = poiReader.streamExcelToTable(inputStream, user, roleChecker);
		
		return dataTables;
	}

	public List<DataTable> readFrom(InputStream inputStream) throws IOException, WebApplicationException {
		return readFrom(null, null, null, null, null, inputStream);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoleChecker(RoleChecker roleChecker) {
		this.roleChecker = roleChecker;
	}

}
