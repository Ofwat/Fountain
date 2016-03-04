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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.LineDao;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.dao.PotDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.tools.InvalidDocumentException;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.CompanyPageDocument.CompanyPage;
import uk.gov.ofwat.model2.InputDocument.Input;
import uk.gov.ofwat.model2.LineDocument.Line;
import uk.gov.ofwat.model2.ModelDocument.Model;
import uk.gov.ofwat.model2.PageDocument.Page;
import uk.gov.ofwat.model2.SectionDocument.Section;

public class ModelImporterImpl implements ModelImporter {

	private static Log log = LogFactory.getLog(ModelImporterImpl.class);

	// NOTE - the word 'local' is used here to refer to classes in the 'fountain' domain as
	// opposed to the xmlBeans ('incoming') types
	private ModelDao modelDao;
	private IntervalDao intervalDao;
	private PotDao potDao;
	private TableDao tableDao;
	private LineDao lineDao;
	private ModelPropertiesMapDao mpmDao;
	private ModelService modelService;
	private List<String> lineNumberList = new ArrayList<String>();
	private CompanyService companyService;
	private ReservoirDictionaryModelImporter reservoirImporter;
	private GroupService groupService;
	private RunTagService runTagService;
	private RunService runService;


	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	public ModelService getModelService() {
		return modelService;
	}
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	public void setIntervalDao(IntervalDao intervalDao){
		this.intervalDao = intervalDao;
	}
	public void setPotDao(PotDao potDao){
		this.potDao = potDao;
	}
	public void setTableDao(TableDao tableDao){
		this.tableDao = tableDao;
	}
	public void setModelPropertiesMapDao(ModelPropertiesMapDao mpmDao){
		this.mpmDao = mpmDao;
	}
	public void setModelDao(ModelDao modelDao){
		this.modelDao = modelDao;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}
	public void setReservoirDictionaryModelImporter(ReservoirDictionaryModelImporter reservoirImporter) {
		this.reservoirImporter = reservoirImporter;
	}
	
	/**
	 * <pre>
	 * Reservoir local models go:
	 * model
	 *	|- table
	 *			|- pot <item, year>
	 * whereas the imported models go:
     * model
     *    |-page
     *       |- section
     *                |- line
     *                       |- cell <item, year>
     * so we're flattening the structure somewhat
     * </pre>
	 * 
	 */
	public List<String> importReservoirModel(Model incomingModel) {
		// Start with the dictionary merge - no more to do if this fails
		DictionaryImportResults results = reservoirImporter.importModel(incomingModel);
		if (results.isFailure()) {
			return results.getErrors();
		}
		
		ArrayList<String> errors = new ArrayList<String>();
		modelDao.invalidateCache();
		// check to see if we already have this model
		uk.gov.ofwat.fountain.domain.Model oldModel = modelDao.findByCode(incomingModel.getModeldetails().getCode());
		Integer modelId = null;
		if(null != oldModel){
			modelId = oldModel.getId();
			modelDao.delete(oldModel.getId());
		}

		uk.gov.ofwat.fountain.domain.Model localModel = createLocalModel(incomingModel, modelId);
		uk.gov.ofwat.model2.ItemDocument.Item[] modelItems = incomingModel.getItems().getItemArray();;
		Map<String, Integer> pageIdMap = new HashMap<String, Integer>(); // table name to id map
		Map<String, Interval> intervalMap = mapLocalIntervalsByName();
		mapIncomingItemCodesToItemProperties(errors, localModel, modelItems, results.getAttachedPropertiesMap());		
		IntervalType FINANCIAL_YEAR_TYPE = getFinancialYearType();
		if (errors.size()>0) {
			return errors;
		}

		String runCode = incomingModel.getModeldetails().getRunCode();
		Run run = runService.getRun(runCode, false);
		localModel.setRunId(run.getId());
		
		log.info("Processing pages");
		for(Page page:  incomingModel.getPages().getPageArray()){
			if(!page.getPagedetails().isSetHidden()) {
				int tableId = processPage(errors, localModel, results.getAttachedPropertiesMap(), intervalMap, FINANCIAL_YEAR_TYPE, page);
				pageIdMap.put(page.getPagedetails().getCode().toUpperCase(), tableId);
				modelDao.update(localModel);
			}
		}
		if(null != incomingModel.getCompanyPages()){
			log.info("Processing company pages");
			int count = 0;
			for(CompanyPage cp : incomingModel.getCompanyPages().getCompanyPageArray()){
				count++;
				Integer pageId = pageIdMap.get(cp.getPageCode().toUpperCase());
				if(null != pageId){ // if it doesn't exist it was a hidden page
					Company company = companyService.getCompanyByCode(cp.getCompanyCode());
					tableDao.addCompanyTable(company.getId(), localModel.getId(), pageId);
				}
			}
			log.info("Processed " + count + " company pages");
		}
		log.info("Finished processing pages");		
		return errors;
	}

	public List<String> importFountainModel(Model incomingModel) {
		throw new UnsupportedOperationException("No Fountain format imports implemented yet"); 
	}

	
	private int processPage(
			List<String> errors,
			uk.gov.ofwat.fountain.domain.Model localModel,
			Map<String, ItemProperties> localItemPropMap,
			Map<String, Interval> intervalMap,
			IntervalType FINANCIAL_YEAR_TYPE, Page page) {
			Table table = createTable(localModel,  page);
		log.info("Processing page " + page.getPagedetails().getCode());
		Set<Interval> pageIntervals = getPageIntervals(intervalMap, FINANCIAL_YEAR_TYPE, page);
		for(Section section: page.getSections().getSectionArray()) {
			processSection(localModel, localItemPropMap, intervalMap, page, section, table, pageIntervals);
			addTableToModel(localModel, table);
		}
		log.info("Finished processing page " + page.getPagedetails().getCode());
		return table.getId();
	}



	private IntervalType getFinancialYearType() {
		// TODO - need a dynamic way to work out the year type
		// currently hard coded to financial year
		IntervalType FINANCIAL_YEAR_TYPE = null;
		for(IntervalType iType: intervalDao.getAllIntervalTypes()){
			if(iType.getName().equals("Financial Year (YYYY-YY)")){
				FINANCIAL_YEAR_TYPE = iType;
				break;
			}
		}
		if(null == FINANCIAL_YEAR_TYPE){
			log.error("Error locating default year type 'FINANCIAL YEAR'");
		}
		return FINANCIAL_YEAR_TYPE;
	}

	private void addTableToModel(uk.gov.ofwat.fountain.domain.Model localModel,
			Table table) {
		// add the table to the model
		localModel.getTables().add(table);
	}

	private Set<Interval> getPageIntervals(
			Map<String, Interval> intervalMap,
			IntervalType FINANCIAL_YEAR_TYPE, Page page) {
		// first parse the page to find out how many years (intervals)
		// are being used so we can fill in the gaps and make a complete grid
		Set<Interval> pageIntervals = new HashSet<Interval>();
		for(Section section: page.getSections().getSectionArray()){
			for(Line line: section.getLines().getLineArray()){
				if(line.getCells() == null ||
						   line.getCells().getCellArray() == null ||
						   line.getCells().getCellArray().length == 0){
							continue;
						}
				for(Cell cell: line.getCells().getCellArray()){
					// TODO Think we should delegate the interval collection and create to the reference service.
					Interval interval = intervalMap.get(cell.getYear());
					if(null == interval){
						// missing intervals will now be considered an error
						log.error("Unrecognised interval: " + cell.getYear());
						throw new InvalidDocumentException("Unrecognised interval: " + cell.getYear());
					}
					pageIntervals.add(interval);
				}
			}
		}
		return pageIntervals;
	}

	private Table createTable(uk.gov.ofwat.fountain.domain.Model localModel,
			Page page) {
		
		// get the groups for this table (0 or 1 per section)
		Set<Group> groups = new HashSet<Group>();
		
		for(Section section : page.getSections().getSectionArray()){
			String groupString = section.getSectiondetails().getGrouptype();
			if(null != groupString && !"".equals(groupString)){
				groups.add(groupService.getGroupByCode(groupString));
			}
		}
		
		// create table here
		Table table = new Table();
		table.setTableGroups(groups);
		table.setModelId(localModel.getId());
		table.setName(page.getPagedetails().getCode());
		
		String companyTypeString = page.getPagedetails().getCompanyType();
		int companyTypeId = companyService.getCompanyTypeId(companyTypeString);
		table.setCompanyTypeId(companyTypeId);
		table.setId(tableDao.create(table));
		Collection<Pot> pots = new ArrayList<Pot>();
		table.setPots(pots);
		return table;
	}

	private void processSection(uk.gov.ofwat.fountain.domain.Model localModel,
			                    Map<String, ItemProperties> localItemPropMap,
			                    Map<String, Interval> intervalMap,
			                    Page page,
			                    Section section,
			                    Table table,
			                    Set<Interval> pageIntervals) {
		log.info("Processing section " + section.getSectiondetails().getCode());
		for(Line line: section.getLines().getLineArray()){
			// some lines are just headings
			if(line.getCells() == null ||
			   line.getCells().getCellArray() == null ||
			   line.getCells().getCellArray().length == 0){
				continue;
			}

			// line defaults
			String itemCode = line.getLinedetails().getCode(); // this should be the item code
			String lineEq = line.getLinedetails().getEquation();
			ItemProperties lineProperties = localItemPropMap.get(itemCode);
			if(null == lineProperties){
				throw new InvalidDocumentException("Error - no item properties for line item code " + itemCode);
			}

			Set<Interval> lineIntervals = new HashSet<Interval>();
			for(Cell cell: line.getCells().getCellArray()){
				Pot pot = processCellToPot(localModel, intervalMap, page, section, table, lineEq, lineProperties, lineIntervals, cell);
				// add the pot to the table
				table.addPot(pot);
				generateNewLine(table, line, lineProperties);
			}
			if(lineIntervals.size() != pageIntervals.size()){
				// we need to create some blank cells
				// we just need to add a pot with the correct item and interval
				for(Interval secInt: pageIntervals){
					boolean exists = false;
					for(Interval lineInt: lineIntervals){
						if(lineInt.equals(secInt)){
							exists = true;
							break;
						}
					}
					if(!exists){
						Pot p = new Pot();
						p.setInterval(secInt);
						p.setItem(lineProperties.getItem());
						p.setModelId(localModel.getId());
						p.setModelService(modelService);
						RunTag runTag = runTagService.getRunTag(localModel.getRunId(), RunModelTag.LATEST.getId(), false);
						p.setRunTag(runTag);
						p.setId(potDao.create(p, table.getId()));
						table.addPot(p);
					}
				}
			}
		}
		
		log.info("Finished section " + section.getSectiondetails().getCode());
	}
	
	private void generateNewLine(Table table, Line line, ItemProperties lineProperties) {
		if (line.getLinedetails().isSetLinenumber() &&
			null != lineProperties.getItem() &&
			null != table) {
			uk.gov.ofwat.fountain.domain.Line fountainLine = new uk.gov.ofwat.fountain.domain.Line();
			fountainLine.setTableId(table.getId());
			fountainLine.setItemId(lineProperties.getItem().getId());
			
			String lineNoString = line.getLinedetails().getLinenumber();
			int lineNoInt = Integer.parseInt(lineNoString);
			fountainLine.setLineNumber(lineNoInt);
			
			if (!lineNumberList.contains("" + fountainLine.getTableId() + "-" + fountainLine.getItemId() + "-" + fountainLine.getLineNumber())) {
				lineDao.create(fountainLine);
				lineNumberList.add("" + fountainLine.getTableId() + "-" + fountainLine.getItemId() + "-" + fountainLine.getLineNumber());
			}
		}
	}

	/**
 	 * create a new pot.
 	 *  cell can be input, copy data, copy cell or calc
  	 * a pot will be created for the default (line) code and cell
  	 * year regardless of whether the cell says otherwise (no overrides)
  	 * therefore each section will be normalised to a complete grid.
 	 * 
  	 * cells overriding the line cell code will be ignored.
 	 * 
  	 * new cells will be introduced unless all lines in the section cover
  	 * the same years:
 	 * <pre> 
	 * line 1 item bon101:       2001; 2002; 2003
	 * line 2 item bon102:             2002; 2003; 2004
 	 * 
	 * will result in a table
	 * bon101:   2001; 2002; 2003; 2004
	 * bon102:   2001; 2002; 2003; 2004
 	 * 
	 * even though there are more elements in the new table than the original section.
 	 * 
	 * cell overrides of boncode or year will be ignored.
	 * cell overrides of equations will be processed as long as no contradictions occur
	 * </pre>
	 */
	private Pot processCellToPot(uk.gov.ofwat.fountain.domain.Model localModel,
			Map<String, Interval> intervalMap, Page page, Section section,
			Table table, String lineEq, ItemProperties lineProperties,
			Set<Interval> lineIntervals, Cell cell) {

		Pot pot = new Pot();
		Interval interval = intervalMap.get(cell.getYear());
		if(null == interval){
			log.error("unrecognised interval: " + cell.getYear());
		}
		lineIntervals.add(interval);
		pot.setInterval(interval);
		pot.setItem(lineProperties.getItem());
		pot.setModelId(localModel.getId());
		pot.setModelService(modelService);
		RunTag runTag = runTagService.getRunTag(localModel.getRunId(), RunModelTag.LATEST.getId(), false);
		pot.setRunTag(runTag);
		pot.setId(potDao.create(pot, table.getId()));
		return pot;
	}

	private Map<String, Interval> mapLocalIntervalsByName() {
		Map<String, Interval> intervalMap = new HashMap<String, Interval>();
		for(Interval interval: intervalDao.getAll()){
			intervalMap.put(interval.getName(), interval);
		}
		return intervalMap;
	}
	
	
	private void mapIncomingItemCodesToItemProperties(
			List<String> errors,
			uk.gov.ofwat.fountain.domain.Model localModel,
			uk.gov.ofwat.model2.ItemDocument.Item[] modelItems,
			Map<String, ItemProperties> itemPropertiesMap) {
		for(uk.gov.ofwat.model2.ItemDocument.Item mItem: modelItems){
			// get the correct properties for it.

			ItemProperties props = itemPropertiesMap.get(mItem.getCode());
			if (null == props){
				errors.add("ModelImporter: unable to find item properties for " + mItem.getCode());
				continue;
			}

			// also store the relationship in the database.
			ModelPropertiesMap mpm = new ModelPropertiesMap();
			mpm.setItemId(props.getItem().getId());
			mpm.setItemCode(props.getItem().getCode());
			mpm.setItemPropertiesId(props.getId());
			mpm.setModelId(localModel.getId());
			mpmDao.create(mpm);
		}
	}
	
	private uk.gov.ofwat.fountain.domain.Model createLocalModel(Model incomingModel, Integer modelId) {
		String branch = null;
		if (incomingModel.getModeldetails().isSetBranchTag()) {
			branch = incomingModel.getModeldetails().getBranchTag();
		}
		uk.gov.ofwat.fountain.domain.Model localModel = null;
		
		
		int displayOrder = 0; // default value - should be handled by the schema but xmlbeans doesn't do it.
		if(null != incomingModel.getModeldetails().getDisplayOrder()){
			displayOrder = incomingModel.getModeldetails().getDisplayOrder().intValue();
		}
		
		Run run = runService.getRun(incomingModel.getModeldetails().getRunCode(), false); 
		int runId = run.getId();
		
		if (null == modelId) {
			localModel = modelService.createModel(incomingModel.getModeldetails().getName(),
											      incomingModel.getModeldetails().getCode(),
											      incomingModel.getModeldetails().getType(),
											      incomingModel.getModeldetails().getModelFamilyCode(),
											      branch,
											      incomingModel.getModeldetails().getModelFamilyParent(),
											      displayOrder,
											      runId
											      );
		}
		else {
			localModel = modelService.createModel(modelId,
												  incomingModel.getModeldetails().getName(),
											      incomingModel.getModeldetails().getCode(),
											      incomingModel.getModeldetails().getType(),
											      incomingModel.getModeldetails().getModelFamilyCode(),
											      branch,
											      incomingModel.getModeldetails().getModelFamilyParent(),
											      displayOrder,
											      runId
											      );
		}
		// get the input models

		for (Input incomingInput : incomingModel.getInputs().getInputArray()) {
			if (!incomingInput.getDefault()) {
				ModelInput localInput = new ModelInput();
				localInput.setCode(incomingInput.getCode());
				localInput.setChildModelCode(incomingInput.getModel());
				localInput.setParentId(localModel.getId());

				// add these inputs to the model input stash on the model
				localModel.addModelInput(localInput);
				// save the inputs
				localInput = modelService.createModelInput(localInput);
			}
		}
		List<Table> tables = new ArrayList<Table>();
		localModel.setTables(tables);
		return localModel;
	}
}
