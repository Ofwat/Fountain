package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.api.table.reader.SectionLayout;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWComponent;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWRow;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWSectionDetails;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Adam Edgar on 16/05/2017.
 */
public class TableWrapperSectionStrategyImpl extends TableWrapperStrategy{

    @Override
    public TWComponent render(TableWrapperRenderer tableWrapperRenderer, ModelPage modelPage, PageSection pageSection, FormDisplayCell[] formDisplayRow, FormDisplayCell formDisplayCell) {
        return render(tableWrapperRenderer, pageSection);
    }

    private TWComponent render(TableWrapperRenderer tableWrapperRenderer, PageSection pageSection) {
        if (null == pageSection) {
            class Local {};
            throw new RuntimeException("Precondition violation in "
                    + this.getClass().getName() + "."
                    + Local.class.getEnclosingMethod().getName()
                    + "(). 'pageSection' is null.");
        }

        TWSection twSection = new TWSection();
        TWSectionDetails twSectionDetails = renderTWSectionDetails(tableWrapperRenderer, pageSection, twSection);

        for (PageForm pageForm: pageSection.getPageForms()) {
            // FORM
            twSectionDetails.setRowCount(pageForm.getRows());
            twSectionDetails.setColCount(pageForm.getCols());

            FormDisplayCell[][] formDisplayGrid = pageForm.getCellGrid();
            tableWrapperRenderer.setSectionLayout(new SectionLayout(formDisplayGrid, pageForm.getRows(), pageForm.getCols()));

            for (FormDisplayCell[] formDisplayRow: Arrays.asList(formDisplayGrid)) {
                // ROW
                TableWrapperRowStrategyImpl rowStrategy = new TableWrapperRowStrategyImpl();
                TWRow twRow = (TWRow)rowStrategy.render(tableWrapperRenderer, null,null, formDisplayRow, null);
                twSection.addTwRow(twRow);
            }
        }
        return twSection;
    }

    private TWSectionDetails renderTWSectionDetails(TableWrapperRenderer tableWrapperRenderer, PageSection pageSection, TWSection twSection) {
        TWSectionDetails twSectionDetails = new TWSectionDetails();
        twSection.setTwSectionDetails(twSectionDetails);
        twSection.setId(tableWrapperRenderer.nextSectionId());
        // set section details
        twSectionDetails.setTwSectionId(twSection.getId());
        twSectionDetails.setCode(pageSection.getCode());
        // get company type from form
//			twSectionDetails.setItemCodeColumn(pageSection.getItemCodeColumn().intValue());
        twSectionDetails.setSectionType(pageSection.getSectionType());
//			twSectionDetails.setDisplay(pageSection.getSectionDetails().getDisplay());
//			twSectionDetails.setAllowGroupSelection(pageSection.getSectionDetails().getAllowGroupSelection());
//			twSectionDetails.setItemCodeColumn(pageSection.getSectionDetails().getItemCodeColumn().intValue());
//			twSectionDetails.setSectionType(pageSection.getSectionDetails().getSectionType());
//			twSectionDetails.setUseConfidenceGrades(pageSection.getSectionDetails().getUseConfidenceGrades());
//			twSectionDetails.setUseItemCode(pageSection.getSectionDetails().getUseItemCode());
//			twSectionDetails.setUseItemDescription(pageSection.getSectionDetails().getUseItemDescription());
//			twSectionDetails.setUseLineNumber(pageSection.getSectionDetails().getUseLineNumber());
//			twSectionDetails.setUseUnit(pageSection.getSectionDetails().getUseUnit());
//			twSectionDetails.setUseYearCode(pageSection.getSectionDetails().getUseYearCode());
        return twSectionDetails;
    }

    @Override
    public String compare(String errors, TWComponent clientTWComponent, TWComponent serverTWComponent) {
        TWSection clientTWSection = (TWSection)clientTWComponent;
        TWSection serverTWSection = (TWSection)serverTWComponent;

        if (!(serverTWSection.getId() == clientTWSection.getId())) {
            errors = errors + error("section", "id", ""+serverTWSection.getId(), ""+clientTWSection.getId());
        }

        // check sectiondetails fields
        errors = compareSectionDetails(errors, clientTWSection, serverTWSection);

        // check rows
        Iterator<TWRow> clientRowIterator = clientTWSection.getTwRows().iterator();
        Iterator<TWRow> serverRowIterator = serverTWSection.getTwRows().iterator();
        while (clientRowIterator.hasNext() && serverRowIterator.hasNext()) {
            // check Rows
            TableWrapperRowStrategyImpl rowStrategy = new TableWrapperRowStrategyImpl();
            errors = errors + rowStrategy.compare(errors, clientRowIterator.next(), serverRowIterator.next());
        }
        return errors;
    }

    private String compareSectionDetails(String errors, TWSection clientTWSection, TWSection serverTWSection) {
        TWSectionDetails serverTWSectionDetails = serverTWSection.getTwSectionDetails();
        TWSectionDetails clientTWSectionDetails = clientTWSection.getTwSectionDetails();
        if (!(serverTWSectionDetails.getTwSectionId() == clientTWSectionDetails.getTwSectionId())) {
            errors = errors + error("section details", "sectionId", ""+serverTWSectionDetails.getTwSectionId(), ""+clientTWSectionDetails.getTwSectionId());
        }
        if (!(serverTWSectionDetails.getColCount() == clientTWSectionDetails.getColCount())) {
            errors = errors + error("section details", "coldCount", ""+serverTWSectionDetails.getColCount(), ""+clientTWSectionDetails.getColCount());
        }
        if (!(serverTWSectionDetails.getRowCount() == clientTWSectionDetails.getRowCount())) {
            errors = errors + error("section details", "rowCount", ""+serverTWSectionDetails.getRowCount(), ""+clientTWSectionDetails.getRowCount());
        }
        if (!(serverTWSectionDetails.getCode().equals(clientTWSectionDetails.getCode()))) {
            errors = errors + error("section details", "code", ""+serverTWSectionDetails.getCode(), ""+clientTWSectionDetails.getCode());
        }
//            if (!(serverTWSectionDetails.getDisplay().equals(clientTWSectionDetails.getDisplay()))) {
//                errors = errors + error("section details", "display", ""+serverTWSectionDetails.getDisplay(), ""+clientTWSectionDetails.getDisplay());
//            }
        if (!(serverTWSectionDetails.getSectionType().equals(clientTWSectionDetails.getSectionType()))) {
            errors = errors + error("section details", "sectionType", ""+serverTWSectionDetails.getSectionType(), ""+clientTWSectionDetails.getSectionType());
        }
        return errors;
    }

    @Override
    public void populate(DataTable dataTable, int companyId, Map<String, DataDto> dataMap, int groupEntryId, TWComponent twComponent) {
        TWSection twSection = (TWSection)twComponent;
        for (TWRow twRow: twSection.getTwRows()) {
            TableWrapperRowStrategyImpl rowStrategy = new TableWrapperRowStrategyImpl();
            rowStrategy.populate(dataTable, companyId, dataMap, groupEntryId, twRow);
        }
    }

    @Override
    String error(String klass, String field, String serverValue, String clientValue) {
        return super.error(klass, field, serverValue, clientValue);
    }
}
