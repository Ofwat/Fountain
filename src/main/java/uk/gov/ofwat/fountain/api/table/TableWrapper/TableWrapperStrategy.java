package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWComponent;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Map;

/**
 * Created by Adam Edgar on 15/05/2017.
 */
public abstract class TableWrapperStrategy {

    abstract TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage, PageSection pageSection, FormDisplayCell[] formDisplayRow, FormDisplayCell formDisplayCell);
    abstract String compare(String errors, TWComponent clientTWComponent, TWComponent serverTWComponent);
    abstract void populate(DataTable dataTable, int companyId, Map<String, DataDto> dataMap, int groupEntryId, TWComponent twComponent);

    String error(String klass, String field, String serverValue, String clientValue) {
        String error = "server " + klass + " " + field + " " + serverValue;
        error = error +"client " + klass + " " + field + " " + clientValue;
        return error;
    }

}