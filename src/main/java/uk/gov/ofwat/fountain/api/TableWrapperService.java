package uk.gov.ofwat.fountain.api;

import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWModel;

/**
 * Created by Adam Edgar on 14/03/2017.
 */
public interface TableWrapperService {

    public TWModel renderTable(int tableId);
    public TWModel populateWithData(TWModel twModel, DataTable dataTable, int companyId);

}
