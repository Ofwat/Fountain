package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWCell;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWComponent;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Map;

/**
 * Created by Adam Edgar on 15/05/2017.
 */
public class TableWrapperCellStrategyImpl extends TableWrapperStrategy {

    @Override
    public TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage, PageSection pageSection, FormDisplayCell[] formDisplayRow, FormDisplayCell formDisplayCell) {
        return render(tableWrapperRenderer, formDisplayCell);
    }

    private TWComponent render(TableWrapperRenderer tableWrapperRenderer, FormDisplayCell formDisplayCell) {
        if (null == formDisplayCell) {
            class Local {};
            throw new RuntimeException("Precondition violation in "
                    + this.getClass().getName() + "."
                    + Local.class.getEnclosingMethod().getName()
                    + "(). 'formDisplayCell' is null.");
        }

        TWCell twCell = new TWCell();
        twCell.setId(tableWrapperRenderer.nextCellId());
        twCell.setHeader(formDisplayCell.getCellType().equals(CellType.COL_HEADING));
        twCell.setSectionId(tableWrapperRenderer.currentSectionId());

        twCell.setStartRowId(tableWrapperRenderer.currentRowId());
        twCell.setRowSpan(formDisplayCell.getRowSpan());
        twCell.setStartColumnNo(formDisplayCell.getColumn() +1);
        twCell.setColSpan(formDisplayCell.getColumnSpan());

        twCell.setCellType(formDisplayCell.getCellType().name());
//						twCell.setValidations();
        if (formDisplayCell.getCellType().equals(CellType.INPUT) ||
            formDisplayCell.getCellType().equals(CellType.CALC) ||
            formDisplayCell.getCellType().equals(CellType.COPYCELL)) {
            twCell.setKey(formDisplayCell.getCellContents());
            twCell.setDisplayText("");
        }
        else {
            twCell.setKey("");
            twCell.setDisplayText(formDisplayCell.getCellContents());
        }
        twCell.setContent("");

        return twCell;
    }

    @Override
    public void populate(DataTable dataTable, int companyId, Map<String, DataDto> dataMap, int groupEntryId, TWComponent twComponent) {
        TWCell twCell = (TWCell)twComponent;
        if (twCell.getCellType().equals(CellType.INPUT.name()) ||
            twCell.getCellType().equals(CellType.CALC.name()) ||
            twCell.getCellType().equals(CellType.COPYCELL.name())) {

            String keyString = twCell.getKey();
            DataKey key = new DataKey(keyString); // for data cells getCellContents() is the key.
            key.setRunId(dataTable.getDefaultRunIdMap().get(key.getRunIdInteger()));
            key.setRunTag(true);
            key.setCompanyId(companyId);
            key.setGroupEntryId(groupEntryId);

            String completedKeyString = key.getKey(true);
            twCell.setKey(completedKeyString);
            DataDto dataDto = dataMap.get(completedKeyString);
            if (null != dataDto) {
                twCell.setDisplayText(dataDto.getValue());
            }
        }
    }

    @Override
    public String compare(String errors, TWComponent clientTWComponent, TWComponent serverTWComponent) {
        TWCell clientTWCell = (TWCell)clientTWComponent;
        TWCell serverTWCell = (TWCell)serverTWComponent;

        if (!(serverTWCell.getId() == clientTWCell.getId())) {
            errors = errors + error("cell", "", ""+serverTWCell.getId(), ""+clientTWCell.getId());
        }
        if (!(serverTWCell.getSectionId() == clientTWCell.getSectionId())) {
            errors = errors + error("cell", "", ""+serverTWCell.getSectionId(), ""+clientTWCell.getSectionId());
        }
        if (!(serverTWCell.getColSpan() == clientTWCell.getColSpan())) {
            errors = errors + error("cell", "", ""+serverTWCell.getColSpan(), ""+clientTWCell.getColSpan());
        }
        if (!(serverTWCell.getRowSpan() == clientTWCell.getRowSpan())) {
            errors = errors + error("cell", "", ""+serverTWCell.getRowSpan(), ""+clientTWCell.getRowSpan());
        }
        if (!(serverTWCell.getStartColumnNo() == clientTWCell.getStartColumnNo())) {
            errors = errors + error("cell", "", ""+serverTWCell.getStartColumnNo(), ""+clientTWCell.getStartColumnNo());
        }
        if (!(serverTWCell.getStartRowId() == clientTWCell.getStartRowId())) {
            errors = errors + error("cell", "", ""+serverTWCell.getStartRowId(), ""+clientTWCell.getStartRowId());
        }

        if (!(serverTWCell.getCellType().equals(clientTWCell.getCellType()))) {
            errors = errors + error("cell", "", ""+serverTWCell.getCellType(), ""+clientTWCell.getCellType());
        }
        if (!(serverTWCell.getKey().equals(clientTWCell.getKey()))) {
            errors = errors + error("cell", "", ""+serverTWCell.getKey(), ""+clientTWCell.getKey());
        }
        if (!(serverTWCell.getDisplayText().equals(clientTWCell.getDisplayText()))) {
            errors = errors + error("cell", "", ""+serverTWCell.getDisplayText(), ""+clientTWCell.getDisplayText());
        }
        if (!(serverTWCell.getContent().equals(clientTWCell.getContent()))) {
            errors = errors + error("cell", "", ""+serverTWCell.getContent(), ""+clientTWCell.getContent());
        }
        if (!(serverTWCell.getHeader().equals(clientTWCell.getHeader()))) {
            errors = errors + error("cell", "", ""+serverTWCell.getHeader(), ""+clientTWCell.getHeader());
        }
//                    if (!(serverTWCell.getValidations().equals(clientTWCell.getValidations()))) {
//                        errors = errors + error("cell", "", ""+serverTWCell.getValidations().toString(), ""+clientTWCell.getValidations().toString());
//                    }
        return errors;
  }

}
