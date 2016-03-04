package uk.gov.ofwat.fountain.api.table.reader.line;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class OrderedLineReader extends LineReader {

	public void read(GroupEntry groupEntry, Company company) {

		int colIdx = 0;
		for(FormDisplayCell cell: cells){
//			log.info("Row: " + row.getRowNum()+1 + "    Col: " + colIdx);
			DataKey key = makeKey(groupEntry, company, cell);
			DataDto dto = dataTable.getDataList().get(key.getKey(true));
			String newValue; 
			String newCG; 
			if (null != cell) {
	    		switch(cell.getCellType()){
	    		case EMPTY:
	    			colIdx++;
	    			break;
	    		case ROW_HEADING_NON_REPEAT:
	    			colIdx++;
	    			break;
	    		case COL_HEADING:
	    			colIdx++;
	    			break;
	    		case ROW_HEADING:
	    			colIdx++;
	    			break;
	    		case CALC:
	    			colIdx++;
	    			break;
	    		case COPYCELL:
	    			if(key.isCg()){
	    				key.setCg(false);
	    				newCG = readCGCell(row, colIdx, dataTable.getDataList().get(key.getKey(true)), cell.getCellType());
	    				if (metaData.isAllowHistoricData()) {
		    				updateDtoConfidenceGrade(row, colIdx, dto, cell.getCellType(), newCG);
	    				}
	    				else {
	    					reportInvalidChange(row.getRowNum(), colIdx, dto, cell.getCellType(), newCG);
	    				}
	    			}
	    			else{
	    				newValue = readDataCell(row, colIdx, dto, cell.getCellType());
	    				if (metaData.isAllowHistoricData()) {
		    				updateDtoValue(dto, cell.getCellType(), newValue);
	    				}
	    				else {
	    					reportInvalidChange(row.getRowNum(), colIdx, dto, cell.getCellType(), newValue);
	    				}
	    			}
	    			colIdx++;
	    			break;
	    		case INPUT:
	    			if(key.isCg()){
	    				key.setCg(false);
	    				newCG = readCGCell(row, colIdx, dataTable.getDataList().get(key.getKey(true)), cell.getCellType());
	    				updateDtoConfidenceGrade(row, colIdx, dto, cell.getCellType(), newCG);
	    			}
	    			else{
	    				newValue = readDataCell(row, colIdx, dto, cell.getCellType());
	    				updateDtoValue(dto, cell.getCellType(), newValue);
	    			}
	    			colIdx++;
	    			break;
	    		case GROUP_ROW_HEADING:
	    			if(!groupEntry.getGroup().getName().equals("NONE")){
		    			colIdx++;
	    			}
	    			break;
	    		}
			}
    	}
	}

}
