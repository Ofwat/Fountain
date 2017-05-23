/*
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
package uk.gov.ofwat.fountain.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.ofwat.fountain.domain.ImportResponse;
import uk.gov.ofwat.fountain.domain.Notice;
import uk.gov.ofwat.fountain.rest.dto.ImportResponseDto;
import uk.gov.ofwat.fountain.tools.InvalidDocumentException;
import uk.gov.ofwat.model2.ModelDocument;
import uk.gov.ofwat.model2.ModelDocument.Model;
import uk.gov.ofwat.version1.DictionaryDocument;
import uk.gov.ofwat.version1.DictionaryDocument.Dictionary;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileServiceImpl implements FileService {

	private ModelImporter modelImporter;
	private ModelFormsImporter modelFormsImporter;
	private ReservoirDictionaryItemsImporter reservoirImporter;
	protected String bulkUploadDir;
	private String tableUploadLogDir;
	private String modelBuilderUploadDir;

	private static final Log log = LogFactory.getLog(FileServiceImpl.class);

	public void setModelImporter(ModelImporter modelImporter){
		this.modelImporter = modelImporter;
	}

	public void setModelFormsImporter(ModelFormsImporter modelFormsImporter){
		this.modelFormsImporter = modelFormsImporter;
	}
	
	public void setReservoirDictionaryItemsImporter(ReservoirDictionaryItemsImporter reservoirImporter) {
		this.reservoirImporter = reservoirImporter;
	}
	

	@Transactional
	public List<String> processDictionary(Object dictionary) {
		log.info("Processing dictionary");
		List<String> errors = null;
		if (dictionary instanceof uk.gov.ofwat.version1.DictionaryDocument) {
			// Reservoir dictionary
			DictionaryDocument doc = (DictionaryDocument) dictionary;
			Dictionary dct = doc.getDictionary();
			errors = reservoirImporter.importDictionary(dct).getErrors();
		}
		else if (dictionary instanceof uk.gov.ofwat.version2.impl.DictionaryDocumentImpl) {
			// Fountain dictionary
			throw new UnsupportedOperationException("Fountain format import not yet implemented");
		}
		else {
			throw new InvalidDocumentException("Document is not a reservoir or fountain dictionary document");
		}
		
		if (errors.size()>0) {
			throw new ListException(errors);
		}
		return errors;
	}
	
	public List<String> bulkModelImport() {
		log.info("Started bulk import");
		
		File uploadDir = new File(bulkUploadDir);
		File finishedDir = new File(bulkUploadDir, "done");
		
		List<String> reports = new ArrayList<String>();
		if (!createDirOrFail(reports, uploadDir)) return reports;
		if (!createDirOrFail(reports, finishedDir)) return reports;
		
		reports.add("Reading all files in " + uploadDir);
		String[] fileNames = uploadDir.list();
		Arrays.sort(fileNames);
		log.info("Found " + fileNames.length + " file(s) in " + uploadDir);
		int processed = 0;
		for(String fileName: fileNames){
			if(fileName.endsWith(".xml")){
				File modelFile = new File(uploadDir.getAbsolutePath(), fileName);
				String destString = finishedDir.getAbsolutePath() + "\\" + fileName;

				reports.add("about to process " + fileName);
				try{
					log.info("About to processing " + fileName);
					XmlObject xmlObjExpected = XmlObject.Factory.parse(modelFile);
					if(xmlObjExpected instanceof ModelDocument){
						ModelDocument md = (ModelDocument)(xmlObjExpected);
						processModel(md);
						reports.add("finished processing " + fileName);
						destString += "." + getTimeStamp() + ".done";
						log.info("Finished processing " + fileName);
						processModelForms(md);
						reports.add("finished processing " + fileName);
						processed++;
					}
					else{
						log.info("Invalid file: " + fileName );
						reports.add("file " + fileName + " was not a valid audit data file");
						destString += ".err";
					}

				}
				catch(Exception e){
					log.error("Couldn't import " + fileName, e);
					reports.add("error processing " + modelFile.getName() + ": " + e.getMessage());
					destString += ".err";
				}
				File destFile = new File(destString);
				if (!modelFile.renameTo(destFile)) {
					reports.add("Couldn't rename file to " + destString);
				}
				log.info("Moved finished file to " + destFile);
			}
		}
		
		reports.add("Successfully processed " + processed + " file(s)");
		return reports;
	}

	@Transactional
	public ImportResponse singleModelImport(String filename) {
		ImportResponse importResponse = new ImportResponse();
		importResponse.setUploadFileName(filename);
		importResponse.setSuccess(false);

		File uploadDir = new File(modelBuilderUploadDir);
		File finishedDir = new File(bulkUploadDir, "done");

		List<String> reports = new ArrayList<String>();

		if (!createDirOrFail(reports, uploadDir)) {
			importResponse.addNotice(new Notice("Error", reports.get(0), ""));
			return importResponse;
		}
		if (!createDirOrFail(reports, finishedDir)) {
			importResponse.addNotice(new Notice("Error", reports.get(0), ""));
			return importResponse;
		}
		if(!filename.endsWith(".xml")){
			importResponse.addNotice(new Notice("Error", filename + " is not an XML file.", ""));
			return importResponse;
		}

		File modelFile = new File(uploadDir.getAbsolutePath(), filename);

		XmlObject xmlObjExpected = null;
		try {
			xmlObjExpected = XmlObject.Factory.parse(modelFile);
		} catch(Exception e) {
			log.error("Invalid file: " + filename + ". Not a valid XML file.");
			e.printStackTrace();
			importResponse.addNotice((new Notice("Error", "File " + filename + " was not a valid XML file.", "Please see server log. " + e.getMessage().toString())));
			moveCompletedFile(importResponse, modelFile, finishedDir);
			return importResponse;
		}
		
		if (null == xmlObjExpected ||
			!(xmlObjExpected instanceof ModelDocument)) {
			log.error("Invalid file: " + filename + ". Not a valid model file.");
			importResponse.addNotice((new Notice("Error", "File " + filename + " was not a valid model file.", "")));
			moveCompletedFile(importResponse, modelFile, finishedDir);
			return importResponse;
		}

		ModelDocument modelDocument = (ModelDocument)xmlObjExpected;
		Model mdl = modelDocument.getModel();
		List<String> errors = null;
		try {
			errors = modelImporter.importReservoirModel(mdl);
		} catch (Exception e) {
			log.error("Invalid file: " + filename + ". Not a valid model file.");
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			importResponse.addNotice((new Notice("Error", "File " + filename + " was not a valid model file.", "" + stringWriter.toString())));
			moveCompletedFile(importResponse, modelFile, finishedDir);
			return importResponse;
		}
		if (!errors.isEmpty()) {
			for (String error: errors) {
				importResponse.addNotice(new Notice("Error", error, ""));
			}
			moveCompletedFile(importResponse, modelFile, finishedDir);
			return importResponse;
		}
		errors = modelFormsImporter.importModel(mdl);
		if (!errors.isEmpty()) {
			for (String error : errors) {
				importResponse.addNotice(new Notice("Error", error, ""));
			}
			moveCompletedFile(importResponse, modelFile, finishedDir);
			return importResponse;
		}

		importResponse.setSuccess(true);
		moveCompletedFile(importResponse, modelFile, finishedDir);
		return importResponse;
	}

	private boolean moveCompletedFile(ImportResponse importResponse, File modelFile, File finishedDir) {
		String destFilename = finishedDir.getAbsolutePath() + "\\" + modelFile.getName();
		if (importResponse.isSuccess()) {
			destFilename += "." + getTimeStamp() + ".done";
		}
		else {
			destFilename += "." + getTimeStamp() + ".err";
		}
		File destFile = new File(destFilename);
		if (!modelFile.renameTo(destFile)) {
			importResponse.addNotice(new Notice("Error", "Could not rename file to " + destFilename, ""));
			importResponse.setSuccess(false);
			return false;
		}

		log.info("Moved finished file to " + destFilename);
		importResponse.addNotice(new Notice("Info", "Moved file to " + destFilename, ""));
		return true;
	}

	@Transactional
	public List<String> processModel(Object model) {
		log.info("Processing model");

		List<String> errors = null;
		if (model instanceof ModelDocument) {
			ModelDocument doc = (ModelDocument) model;
			Model mdl = doc.getModel();
			errors = modelImporter.importReservoirModel(mdl);
		}
		else {
			// TODO implement the fountain format
			if (errors==null) errors = new ArrayList<String>();
			errors.add("Document is not a reservoir model document");
		}

		if (errors.size()>0) {
			throw new ListException(errors);
		}
		return errors;
	}
	
	@Transactional
	public List<String> processModelForms(Object model) {
		log.info("Processing model forms");

		List<String> errors = null;
		if (model instanceof ModelDocument) {
			ModelDocument doc = (ModelDocument) model;
			Model mdl = doc.getModel();
			errors = modelFormsImporter.importModel(mdl);
		}
		else {
			// TODO implement the fountain format
			if (errors==null) errors = new ArrayList<String>();
			errors.add("Document is not a reservoir model document");
		}

		if (errors.size()>0) {
			throw new ListException(errors);
		}
		return errors;
	}
	
	private String getTimeStamp() {
		String fmt = "yyyyMMdd-kkmmss";
	    SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	    Calendar n = Calendar.getInstance();
	    
	    return sdf.format(n.getTimeInMillis());
	}
	
	private boolean createDirOrFail(List<String> reports, File file) {
		try {
			if (!file.exists()) {
				// Try to create it
				file.mkdirs();
			}
		}
		catch (Exception ex) {
			log.error("Could't create folder " + file, ex);
			reports.add("Couldn't create " + file + ": " + ex.getMessage());
			return false;
		}
		return true;
	}

	
	public String writeTableUploadLogFile(ImportResponseDto responseDto) {
		Long timeStamp = (long)0;
		try{
			File outDir = new File(getTableUploadLogDir());
			if (!outDir.exists()){
				outDir.mkdir();
			}
			timeStamp = System.currentTimeMillis();
			File newFile = new File(outDir, "tableUploadLog" + timeStamp + ".txt");
			FileOutputStream fos=new FileOutputStream(newFile);
			DataOutputStream output = new DataOutputStream (fos);

			output.writeBytes("Excel table upload log." + "\r\n");
			output.writeBytes("Please note that valid changes are not shown here." + "\r\n");
			output.writeBytes("Upload file: " + responseDto.getUploadFileName() + "\r\n");
			output.writeBytes("Upload " + (responseDto.isSuccess() ? "succeeded." : "failed.") + "\r\n");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" + "\r\n");
			Date date = new Date(timeStamp);
			output.writeBytes(dateFormat.format(date) + "\r\n");
			output.writeBytes("" + "\r\n");
			for (String notice: responseDto.getNotices()) {
				output.writeBytes(notice + "\r\n");
			}
	    }
        catch (IOException ioe)
        {
            log.error("I/O Error - " + ioe);
        }
			
		return "" + timeStamp;
	}

	public File readTableUploadLogFile(long id) {
		return new File(getTableUploadLogDir() + "/tableUploadLog" + id + ".txt");
	}
	
	public String getTableUploadLogDir() {
		return tableUploadLogDir;
	}

	public void setTableUploadLogDir(String tableUploadLogDir) {
		this.tableUploadLogDir = tableUploadLogDir;
	}

	public String getBulkUploadDir() {
		return bulkUploadDir;
	}

	public void setBulkUploadDir(String bulkUploadDir) {
		this.bulkUploadDir = bulkUploadDir;
	}

	public String getModelBuilderUploadDir() {
		return modelBuilderUploadDir;
	}

	public void setModelBuilderUploadDir(String modelBuilderUploadDir) {
		this.modelBuilderUploadDir = modelBuilderUploadDir;
	}
}
