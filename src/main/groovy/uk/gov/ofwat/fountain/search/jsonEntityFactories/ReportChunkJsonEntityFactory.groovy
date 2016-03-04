package uk.gov.ofwat.fountain.search.jsonEntityFactories

import java.util.Map;

import uk.gov.ofwat.fountain.api.ReportService
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.report.ReportCell;
import uk.gov.ofwat.fountain.api.report.ReportLine
import uk.gov.ofwat.fountain.api.report.ReportStructure
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;

import clover.org.apache.commons.lang.NotImplementedException
import com.google.gson.Gson;

class ReportChunkJsonEntityFactory implements JsonProducer{ //We are going to use Duck Typing here...

	ReportService reportService
	
	private String addGroupId(ReportLine currentRow, companyGroupsEntries, groupEntryIndex, groupEntriesByCompany, companyId){
		currentRow.cells.each { cell ->
			if(groupEntriesByCompany[companyId]){
				def groupEntryId = groupEntriesByCompany[companyId][groupEntryIndex].id;
				//key = ofwat.keyUtils.setGroupEntryId(key, groupEntryId);
			}
			else
			if (companyGroupsEntries[groupEntryIndex]) {
				// Only add a group entry if we have one for this company.
				def groupEntryId = companyGroupsEntries[groupEntryIndex].id;
				//key = ofwat.keyUtils.setGroupEntryId(key, groupEntryId);
			}
		}
	}
	
	/**
	 * Method to insert an identifier in a 'key' string.
	 * @param key The key string to insert into 
	 * @param identifier The identifier to insert e.g 'c', 'g' etc 
	 * @param value The value to put after the identifier.
	 * @param afterIdentifier The existing identifier to put the new one after - if it doesn't exist then it will go at the end. 
	 * @return
	 */
	private String insertIdentifier(String key, String identifier, Integer value, String afterIdentifier){
		def pat =  "${afterIdentifier}"
		def insertNew = "-${identifier}${value}"
		def find = (key =~ /${pat}[0-9]+/)
		if(find.size() > 0){
			def r = find.getAt(0)
			key = key.replaceFirst(r, r+"${insertNew}")
		}else{
			key = key + "${insertNew}"
		}
		
		return key
	}
	
	/**
	 * Method to get an identifiers value from a 'key' String.
	 * @param key
	 * @param cell
	 * @return
	 */
	private String getIdentifier(String key, ReportCell cell){
        def pat =  "${key}"
        def find = (cell.getCellContents() =~ /${pat}[0-9]+/)
		if(find.size() > 0){
			def r = find.getAt(0)
			return r[1..r.size()-1]
		}
		return null
	}
	
	@Override
	public String getJson(Entity entity, Gson gson) {
		
		/**
		 * Apologies for all the commented out printlns - eclipse wouldn't let me view the values of variables inside a
		 * closure which makes things a little difficult...
		 */ 	
		
		ReportDto reportDto = (ReportDto)entity
		//reportDto.groupEntriesByCompany.each{
		//	println it
		//}
		
		
		/**
		 * Get the report structure!
		 */
		def sortedData = []
		ReportStructure rs = reportService.getReportStructure(reportDto.id)
		/**
		 * Define a pattern for selecting the company as part of the key. 
		 */
		def cPat = /c[0-9]+/
		/**
		 * If the report has a company grab the company id. 
		 */
		def cStr = reportDto.company?.id
		
		/**
		 * For each of the report structure lines we need to make sure the key is the same as the keys that we have in the report data so that 
		 * we can select the report data items in the same order as they appear in the report structure and can chunk them in the correct order. 
		 * This means adding the appropriate company, run, tag and group values.
		 */ 
		
		//Iterate over all the report structure lines. 
		rs.getLines().each{ reportLine ->
			def cells = reportLine.cells
			//Iterate over all the cells!
			cells.each{ cell ->
				//println cell.getCellContents()
				//If the cell is EMPTY, INPUT or CALC we need to put it in our list and use it to sort!
		
				def cellType = cell.cellType 
				if((cellType == CellType.INPUT) || (cellType == CellType.CALC)){//|| (cellType == CellType.EMPTY) ){

					/**
					 * Populate group id.
					 * Get the company id - this will depend on the cell and if we have a report defined companyId.
					 */
					def companyId
					if(cStr == null){
						companyId = Integer.parseInt(getIdentifier('c', cell))
					}else{
						companyId = Integer.parseInt(cStr)
					}
					
					//println "companyId:${companyId}"
					
					/**
					 * 
					 * IMPORTANT!!!!
					 * 
					 * Ok now we have to allocate the group id's.
					 * This is duplicating the functionality found in:
					 * 
					 * reportDisplayPage.js->expandRows
					 * reportDisplayPage.js->addGroupIds
					 * 
					 * As we need these to select the data cell positions form the reportStructure cells. 
					 * 
					 * If you change anything here make sure you aren't changing any functionality from the above method calls  as these *NEED* to produce the same results!!!
					 * 
					 */				
					def companyGroupsEntries = reportDto.groupEntriesByCompany.get(companyId); //returns Set<GroupEntry>
					//println "companyGroupEntries:" + companyGroupsEntries
					companyGroupsEntries.eachWithIndex{ cge, i ->
						//println "${i}:${companyId}:${cge}"
						if(reportDto.groupEntriesByCompany.get(companyId)){
							//println reportDto.groupEntriesByCompany.get(companyId)
							def groupEntryId = reportDto.groupEntriesByCompany.get(companyId)[i].id
							//println groupEntryId
							cell.setCellContents(insertIdentifier(cell.cellContents, 'g', groupEntryId, 'c'))
							//println cell.cellContents
						} else if(companyGroupsEntries.getAt(i) != null){
							// Only add a group entry if we have one for this company.
							//println companyGroupsEntries.getAt(i)
							def groupEntryId = companyGroupsEntries.getAt(i).id;
							//println groupEntryId
							cell.setCellContents(insertIdentifier(cell.cellContents, 'g', groupEntryId, 'c'))
							//println cell.cellContents
						}					
					}
					
					
					/**
					 * Ok  - If the report has a run/tag or company add them into the key string.  
					 */
					def cellStr = cell.cellContents
					def runTagStr = ''
					
					if(reportDto.company != null){
						//TODO refactor to use insertIdentifier
						def find = (cellStr =~ /$cPat/)
						cellStr = cellStr.replaceFirst(find.getAt(0), 'c'+cStr)
					}
					
					if(reportDto.run != null){
						//TODO refactor to use insertIdentifier
						runTagStr = "-r${reportDto.run.id}-t${reportDto.tagId}"
					}
					sortedData.add(cellStr + runTagStr);
				}
			}
		}
		/**
		 * Ok now we need to sort the report data so it's the same order as the report structure items
		 * by iterating over the structure (with correct keys) and picking the items in the same order from the report data.
		 */
		Map<String, DataDto> reportData = reportDto.getDataList()  
		def dataArr = []
		sortedData.each{ sortedDataItem ->
			//println sortedDataItem
			def dataItem = reportData.get(sortedDataItem)
			if(dataItem != null){
				dataArr.add(dataItem)
			}
		}
		/**
		 * Return the JSON string.
		 */
		return gson.toJson(dataArr);				
	}
}
