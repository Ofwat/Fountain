package uk.gov.ofwat.fountain.api;

import uk.gov.ofwat.fountain.api.table.TableWrapper.TableWrapperCellStrategyImpl;
import uk.gov.ofwat.fountain.api.table.TableWrapper.TableWrapperRenderer;
import uk.gov.ofwat.fountain.api.table.TableWrapper.TableWrapperTableStrategyImpl;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWModel;

import java.util.Set;

/**
 * Created by Adam Edgar on 14/03/2017.
 */
public class TableWrapperServiceImpl implements TableWrapperService {

    private ModelService modelService;
    private TableStructureService tableStructureService;

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
        // ToDo will need to deal with group entries
        Set<GroupEntry> groupEntrySet = dataTable.getGroupEntries();
        System.out.println("groupEntrySet.size() = " + groupEntrySet.size());
        int groupEntryId = 0;
        for (GroupEntry ge: groupEntrySet) {
            System.out.println("groupEntry Id = " + ge.getId());
            groupEntryId = ge.getId();
        }
        TableWrapperTableStrategyImpl modelStrategy = new TableWrapperTableStrategyImpl();
        modelStrategy.populate(dataTable, companyId, dataTable.getDataList(), groupEntryId, twModel.getTwTable());
        return twModel;
    }

    public String checkHeaderCellValuesAreTheSame(TWModel serverTWModel, TWModel clientTWModel) {
        String errors = "";
        TableWrapperTableStrategyImpl modelStrategy = new TableWrapperTableStrategyImpl();
        errors = errors + modelStrategy.compare(errors, serverTWModel.getTwTable(), clientTWModel.getTwTable());
        return errors;
    }

}
