package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWComponent;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWTable;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Adam Edgar on 18/05/2017.
 */
public class TableWrapperTableStrategyImpl extends TableWrapperStrategy {

    @Override
    public TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage, PageSection pageSection, FormDisplayCell[] formDisplayRow, FormDisplayCell formDisplayCell) {
        return render(tableWrapperRenderer, modelPage);
    }

    private TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage) {
        if (null == modelPage) {
            class Local {};
            throw new RuntimeException("Precondition violation in "
                                        + this.getClass().getName() + "."
                                        + Local.class.getEnclosingMethod().getName()
                                        + "(). 'modelPage' is null.");
        }

        TWTable twTable = new TWTable();
        twTable.setId(modelPage.getTable().getId());
        String nameString = "" + modelPage.getModel().getName() +
                " " + modelPage.getPageName() +
                " " + modelPage.getTableDescription();
        twTable.setName(nameString);
        twTable.setDescription(modelPage.getTableDescription());
        twTable.setCompanyType(modelPage.getCompanyType());

        for (PageSection pageSection: modelPage.getPageSections()) {
            TableWrapperSectionStrategyImpl tableWrapperSectionStrategyImpl = new TableWrapperSectionStrategyImpl();
            TWSection twSection = (TWSection)tableWrapperSectionStrategyImpl.render(tableWrapperRenderer, null, pageSection, null, null);
            twTable.addTwSection(twSection);
        }
        return twTable;
    }

    @Override
    public String compare(String errors, TWComponent clientTWComponent, TWComponent serverTWComponent) {
        TWTable serverTWTable = (TWTable)serverTWComponent;
        TWTable clientTWTable = (TWTable)clientTWComponent;
        if (!(serverTWTable.getId() == clientTWTable.getId())) {
            errors = errors + error("model", "id", ""+serverTWTable.getId(), ""+clientTWTable.getId());
        }
        if (!(serverTWTable.getName().equals(clientTWTable.getName()))) {
            errors = errors + error("model", "name", serverTWTable.getName(), clientTWTable.getName());
        }
        if (!(serverTWTable.getDescription().equals(clientTWTable.getDescription()))) {
            errors = errors + error("model", "description", serverTWTable.getDescription(), clientTWTable.getDescription());
        }
        if (!(serverTWTable.getCompanyType().equals(clientTWTable.getCompanyType()))) {
            errors = errors + error("model", "companyType", serverTWTable.getCompanyType(), clientTWTable.getCompanyType());
        }
        if (!(serverTWTable.getTwSections().size() == clientTWTable.getTwSections().size())) {
            errors = errors + error("model", "number of sections", ""+serverTWTable.getTwSections().size(), ""+clientTWTable.getTwSections().size());
        }

        Iterator<TWSection> clientSectionIterator = clientTWTable.getTwSections().iterator();
        Iterator<TWSection> serverSectionIterator = serverTWTable.getTwSections().iterator();
        while (clientSectionIterator.hasNext() && serverSectionIterator.hasNext()) {
            // check sections
            TableWrapperSectionStrategyImpl sectionStrategy = new TableWrapperSectionStrategyImpl();
            errors = errors + sectionStrategy.compare(errors, clientSectionIterator.next(), serverSectionIterator.next());
        }
        return errors;
    }

    @Override
    public void populate(DataTable dataTable, int companyId, Map<String, DataDto> dataMap, int groupEntryId, TWComponent twComponent) {
        TWTable twTable = (TWTable)twComponent;
        for (TWSection twSection: twTable.getTwSections()) {
            TableWrapperSectionStrategyImpl sectionStrategy = new TableWrapperSectionStrategyImpl();
            sectionStrategy.populate(dataTable, companyId, dataMap, groupEntryId, twSection);
        }
    }
}
