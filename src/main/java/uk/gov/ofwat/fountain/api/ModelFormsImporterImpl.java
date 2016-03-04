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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.NavigationPane;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.model2.FormDocument.Form;
import uk.gov.ofwat.model2.PageDocument.Page;
import uk.gov.ofwat.model2.SectionDocument.Section;

public class ModelFormsImporterImpl implements ModelFormsImporter, ApplicationContextAware {
	 private static ApplicationContext applicationContext = null;
	 private static Log log = LogFactory.getLog(ModelFormsImporterImpl.class);

	private ModelService modelService;
	private ReferenceService referenceService;
	private ConfidenceGradeService confidenceGradeService;
	private ItemService itemService;
	private TableStructureService tableStructureService;
	private List<ModelPage> modelPages = null;
	private Model model;
	private static String tableTemplate;
	private static String outputDir;
	private static String localDir;
	private RunService runService;
	private Run run;

	
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setTableTemplate(String template) {
		tableTemplate = template;
	}

	public String getTableTemplate() {
		return tableTemplate;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String dir) {
		outputDir = dir;
	}
	
	public String getLocalDir() {
		return localDir;
	}

	public void setLocalDir(String dir) {
		localDir = dir;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}

	public ConfidenceGradeService getConfidenceGradeService() {
		return confidenceGradeService;
	}

	public void setConfidenceGradeService(ConfidenceGradeService cGService) {
		this.confidenceGradeService = cGService;
	}

	public Model getModel() {
		return model;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	
	public void setTableStructureService(TableStructureService tableStructureService) {
		this.tableStructureService = tableStructureService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public Run getRun() {
		return run;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.ModelFormsImporter#getFormPages()
	 */
	public List<ModelPage> getFormPages() {
		return modelPages;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.ModelFormsImporter#importModel(uk.gov.ofwat.model2.ModelDocument.Model)
	 */
	public List<String> importModel(uk.gov.ofwat.model2.ModelDocument.Model incomingModel) {
		List<String> errors = new ArrayList<String>();
		
		model = modelService.getModel(incomingModel.getModeldetails().getCode());
		if (model.getModelType().getName().equalsIgnoreCase(ModelType.FOUNDATION.getName())) {
			// DATAVIEW models do not need the HTML views
			log.info ("Model " + model.getCode() + " is a Foundation model. Not building HTMLs");
			return errors;
		}
		
		run = runService.getRun(incomingModel.getModeldetails().getRunCode(), false); 
		
		List<ModelPage> modelPages = new ArrayList<ModelPage>();
		NavigationPane navigationPane = new NavigationPane();
		for(Page page:  incomingModel.getPages().getPageArray()){
			if (page.getPagedetails().isSetHidden() && page.getPagedetails().getHidden()) {
				log.info("Page " + page.getPagedetails().getCode() + " is hidden - ignoring");
				continue;
			}
			ModelPage modelPage = processPageForm(errors, page);
			navigationPane.addPage(modelPage);
			modelPages.add(modelPage);
		}
		
		for(ModelPage modelPage: modelPages) {
			createHtmlFromTemplate(errors, modelPage, navigationPane);
		}

		return errors;
	}
	
	


	private ModelPage processPageForm(List<String> errors, Page page) {
		log.info("Processing page " + page.getPagedetails().getCode());
		
		String companyType = page.getPagedetails().getCompanyType();
		ModelPage modelPage = new ModelPage();
		
		Table table = model.getTable(page.getPagedetails().getCode()); 
		modelPage.setTable(table);
		modelPage.setModel(model);
		modelPage.setTableDescription(page.getPagedetails().getDescription());
		modelPage.setCompanyType(companyType);
		
		boolean needsGroupSelect = false;
		
		for(Section section: page.getSections().getSectionArray()){
			PageSection pageSection = new PageSection(section);
			if (section.isSetForms()) {
				for (Form form: section.getForms().getFormArray()) {
					PageForm pageForm = new PageForm(form, pageSection, this);
					pageSection.addPageForm(pageForm);
				}
			}
			if(section.getSectiondetails().getAllowGroupSelection()){
				needsGroupSelect = true;
			}
			modelPage.addPageSection(pageSection);
			// save the page section
		}
		modelPage.setGroupSelect(needsGroupSelect);
		
		tableStructureService.saveModelPageStructure(modelPage);
		return modelPage;
	}
	
	private void createHtmlFromTemplate(List<String> errors, ModelPage modelPage, NavigationPane navigationPane) {
		
		String line;	
		try {
			Resource inResource = applicationContext.getResource(tableTemplate);
			FileInputStream	fis=new FileInputStream(inResource.getFile());
			BufferedReader input = new BufferedReader(new InputStreamReader(fis));

			Resource outResourceDir = applicationContext.getResource(outputDir);
			
			File outLocal = new File(localDir);
			File outDir = outResourceDir.getFile();
			
			File newLocalFile = new File(outLocal.getPath()+"/"+modelPage.getModel().getCode());
			File newFile = new File(outDir.getPath()+"/"+modelPage.getModel().getCode());
			
			// create the output directory if it doesn't exist, the model code.
			if (!newFile.exists()){
				newFile.mkdir();
			}
		
			if (!newLocalFile.exists()){
				newLocalFile.mkdir();
			}
			
			String newLocalFileName = newLocalFile.getAbsolutePath()+"/"+modelPage.getPageName()+ ".html";
			log.info("Building HTML page: " + newLocalFileName);
			String newFileName = newFile.getAbsolutePath() + "/" + modelPage.getPageName() + ".html";
			FileOutputStream localFos = new FileOutputStream(newLocalFileName);
			FileOutputStream fos=new FileOutputStream(newFileName);
			
			DataOutputStream localOutput = new DataOutputStream(localFos);
			DataOutputStream   output = new DataOutputStream (fos);

			while (null != ((line = input.readLine())))
			{
				try{
					// need to do this first as the replacement text itself includes tokens that should be replaced below
					line = line.replace("TOKEN_START_BUTTONS", START_BUTTONS);
					line = line.replace("TOKEN_EDIT_BUTTONS", EDIT_BUTTONS);
					line = line.replace("TOKEN_UNIVERSAL_EDIT_BUTTONS", NO_EDIT_BUTTONS);
					line = line.replace("TOKEN_END_BUTTONS", END_BUTTONS);

					line = line.replace("TOKEN_MODEL", modelPage.getModel().getCode()) + "\n";
					line = line.replace("TOKEN_PAGE_TITLE", modelPage.getPageTitleHtml()) + "\n";
					line = line.replace("TOKEN_TABLE_TITLE", modelPage.getTableDiv()) + "\n";
					line = line.replace("TOKEN_TABLE_ID", Integer.toString(modelPage.getTable().getId()));
					line = line.replace("TOKEN_DATA_TABLE", modelPage.getDataTableHtml()) + "\n";
					line = line.replace("TOKEN_NAVIGATION_PANE", navigationPane.toHtml(modelPage)) + "\n";
					line = line.replace("TOKEN_POPULATE_PAGE", modelPage.getPopulatePageHtml()) + "\n";
					
				}
				catch(Exception e){
					log.error("Unable to process HTML page. Problem replacing template tokens.", e);
		        	errors.add("Unable to process HTML page. Problem replacing template tokens." + e.getMessage());
				}
				output.writeBytes(line);
				localOutput.writeBytes(line);
			}
	    }
        catch (Exception ex) {
            log.error("Unable to process HTML page.", ex);
        	errors.add("Unable to process HTML page: " + ex.getMessage());
        }
    }

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}

	private static String START_BUTTONS = "<div class=\"editButtons\">\n";
	
	private static String EDIT_BUTTONS = "<button id=\"editButton\" onClick=\"ofwat.editor.startEdit()\" dojoType=\"dijit.form.Button\">Edit Data</button>\n" +
    "<button id=\"saveButton\" onClick=\"ofwat.editor.showSaveDialog()\" dojoType=\"dijit.form.Button\" disabled=\"disabled\">Save Edits</button>\n" +
    "<button id=\"cancelEditing\" onClick=\"ofwat.editor.cancelEdits()\" dojoType=\"dijit.form.Button\" disabled=\"disabled\">Cancel Editing</button>\n";

	private static String NO_EDIT_BUTTONS = "<button title=\"Create a report from this page\" id=\"convertToReport\" onClick=\"ofwat.modelPage.sendToReport(TOKEN_TABLE_ID)\" dojoType=\"dijit.form.Button\">Create A Report</button>\n" +
	"<a title=\"Export table to Excel\" href=\"javascript:ofwat.modelPage.exportTableToExcel('TOKEN_TABLE_ID')\"><img border='0' src='/Fountain/images/page_excel.png'></a>&nbsp;";
	

	private static String END_BUTTONS = "<span id=\"response\"></span> \n</div>";


}
