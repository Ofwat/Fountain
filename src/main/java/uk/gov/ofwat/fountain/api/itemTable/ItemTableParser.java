package uk.gov.ofwat.fountain.api.itemTable;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public abstract class ItemTableParser {

	public static final String CODE_TOKEN_REGEXP = "(" + "[A-Z][A-Z_]*[0-9]+[A-Z0-9_]*" + ")";

	@Transactional
	public abstract TableDto parse();
	
	protected boolean addToColNameToNumber(List<CellDto> cellDtos, int cellIndex, String displayText, Map <String, Integer> colNameToNumber, String mapKey) {
		if (cellDtos.get(cellIndex).getValue().equalsIgnoreCase(displayText)) {
			colNameToNumber.put(mapKey, cellDtos.get(cellIndex).getCol());
			return true;
		}
		CellDto cellDto = cellDtos.get(cellIndex);
		setCellError(cellDto, "Error. This column should be '" + displayText + "'.");
		return false;
	}

	protected void setCellError(CellDto cellDto, String message) {
		cellDto.setErrorFlag(true);
		cellDto.setStyle("error");
		cellDto.setErrorText(message);
	}

}