package uk.gov.ofwat.fountain.api;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.table.TableWrapperRenderer;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWCell;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWModel;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWRow;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWSection;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Map;
import java.util.Set;

/**
 * Created by Adam Edgar on 14/03/2017.
 */
public class TableWrapperServiceImpl implements TableWrapperService {

    private ModelService modelService;
    private TableStructureService tableStructureService;

//    private TableWrapperRenderer tableWrapperRenderer;
//
//    public TableWrapperRenderer getTableWrapperRenderer() {
//        return tableWrapperRenderer;
//    }
//
//    public void setTableWrapperRenderer(TableWrapperRenderer tableWrapperRenderer) {
//        this.tableWrapperRenderer = tableWrapperRenderer;
//    }


    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    public void setTableStructureService(TableStructureService tableStructureService) {
        this.tableStructureService = tableStructureService;
    }

    @Override
    public TWModel renderTable(int tableId) {
        TableWrapperRenderer tableWrapperRenderer = new TableWrapperRenderer();
        tableWrapperRenderer.setModelService(modelService);
        tableWrapperRenderer.setTableStructureService(tableStructureService);
        TWModel twModel = tableWrapperRenderer.renderTable(tableId);
        return twModel;
    }

    @Override
    public TWModel populateWithData(TWModel twModel, DataTable dataTable, int companyId) {

        Map<String, DataDto> dataMap = dataTable.getDataList();

//        for (String key: dataMap.keySet()) {
//            System.out.println("" + key);
//        }
// ToDo will need to deal with group entries
        Set<GroupEntry> groupEntrySet = dataTable.getGroupEntries();
        System.out.println("groupEntrySet.size() = " + groupEntrySet.size());
        int groupEntryId = 0;
        for (GroupEntry ge: groupEntrySet) {
            System.out.println("groupEntry Id = " + ge.getId());
            groupEntryId = ge.getId();
        }

        for (TWSection twSection: twModel.getTwTable().getTwSections()) {
            for (TWRow twRow: twSection.getTwRows()) {
                for (TWCell twCell: twRow.getTwCells()) {
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
            }
        }
        return twModel;
    }
}
