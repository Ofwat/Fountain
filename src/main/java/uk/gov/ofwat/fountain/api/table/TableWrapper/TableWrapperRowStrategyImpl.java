package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWCell;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWComponent;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWRow;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Adam Edgar on 16/05/2017.
 */
public class TableWrapperRowStrategyImpl extends TableWrapperStrategy {

    @Override
    public TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage, PageSection pageSection, FormDisplayCell[] formDisplayRow, FormDisplayCell formDisplayCell) {
        return render(tableWrapperRenderer, formDisplayRow);
    }

    private TWComponent render(TableWrapperRenderer tableWrapperRenderer, FormDisplayCell[] formDisplayRow) {
        if (null == formDisplayRow) {
            class Local {};
            throw new RuntimeException("Precondition violation in "
                    + this.getClass().getName() + "."
                    + Local.class.getEnclosingMethod().getName()
                    + "(). 'formDisplayRow' is null.");
        }

        TWRow twRow = new TWRow();
        twRow.setId(tableWrapperRenderer.nextRowId());
        twRow.setRowNumber(twRow.getId());
        for (FormDisplayCell formDisplayCell:  Arrays.asList(formDisplayRow)) {
            // CELL
            if (tableWrapperRenderer.getSectionLayout().getCellLayout()[formDisplayCell.getRow()][formDisplayCell.getColumn()].contains("~")) {
                // drop empty cells that there is no room for.
                continue;
            }
            TableWrapperCellStrategyImpl cellStrategy = new TableWrapperCellStrategyImpl();
            TWCell twCell =  (TWCell)cellStrategy.render(tableWrapperRenderer, null,null, null, formDisplayCell);
            twRow.addTwCell(twCell);
        }
        return twRow;
    }

    @Override
    public String compare(String errors, TWComponent clientTWComponent, TWComponent serverTWComponent) {
        TWRow clientTWRow = (TWRow)clientTWComponent;
        TWRow serverTWRow = (TWRow)serverTWComponent;

        if (!(serverTWRow.getId() == clientTWRow.getId())) {
            errors = errors + error("row", "", ""+serverTWRow.getId(), ""+clientTWRow.getId());
        }
        if (!(serverTWRow.getRowNumber() == clientTWRow.getRowNumber())) {
            errors = errors + error("row", "", ""+serverTWRow.getRowNumber(), ""+clientTWRow.getRowNumber());
        }

        // check cells
        Iterator<TWCell> clientCellIterator = clientTWRow.getTwCells().iterator();
        Iterator<TWCell> serverCellIterator = serverTWRow.getTwCells().iterator();
        while (clientCellIterator.hasNext() && serverCellIterator.hasNext()) {
            TableWrapperCellStrategyImpl cellStrategy = new TableWrapperCellStrategyImpl();
            errors = errors + cellStrategy.compare(errors, clientCellIterator.next(), serverCellIterator.next());
        }
        return errors;
    }

    @Override
    public void populate(DataTable dataTable, int companyId, Map<String, DataDto> dataMap, int groupEntryId, TWComponent twComponent) {
        TWRow twRow = (TWRow)twComponent;
        for (TWCell twCell: twRow.getTwCells()) {
            TableWrapperCellStrategyImpl cellStrategy = new TableWrapperCellStrategyImpl();
            cellStrategy.populate(dataTable, companyId, dataMap, groupEntryId, twCell);
        }
    }

}
