package uk.gov.ofwat.fountain.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import uk.gov.ofwat.fountain.domain.TableUploadMetaData;

public class FileResourceHelper {

	public InputStream extractExcelFileInputStream(MultipartFormDataInput input) throws IOException {
		for (InputPart part: input.getParts()) {
			if (part.getMediaType().getType().equals("application") &&
				(part.getMediaType().getSubtype().equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
				 part.getMediaType().getSubtype().equals("vnd.ms-excel.12"))) {
				return part.getBody(InputStream.class,null);
			}
		}
		return null;
	}
	
	public String getFileName(MultipartFormDataInput input) {
		for (InputPart part: input.getParts()) {
			String[] contentDisposition = part.getHeaders().getFirst("Content-Disposition").split(";");
			for (String filename : contentDisposition) {
				if ((filename.trim().startsWith("filename"))) {
					String[] name = filename.split("=");
					String finalFileName = name[1].trim().replaceAll("\"", "");
					return finalFileName;
				}
			}
		}
		return "unknown";
	}

}
