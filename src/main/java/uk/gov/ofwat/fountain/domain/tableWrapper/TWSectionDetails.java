package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;

/**
 * Created by Adam Edgar on 06/03/2017.
 */
@XmlRootElement(name = "sectionDetails")
@XmlType(propOrder = {
        "id",
        "twSectionId",
//        "display",
        "code",
//        "useLineNumber",
//        "useItemCode",
//        "useItemDescription",
//        "useUnit",
//        "useYearCode",
//        "useConfidenceGrades",
//        "allowGroupSelection",
        "sectionType",
//        "itemCodeColumn"
        "rowCount",
        "colCount"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class TWSectionDetails {
    private int id;
    @XmlElement(name="sectionId")
    private int twSectionId;
    @XmlTransient
    private String display;
    private String code;
    @XmlTransient
    private Boolean useLineNumber;
    @XmlTransient
    private Boolean useItemCode;
    @XmlTransient
    private Boolean useItemDescription;
    @XmlTransient
    private Boolean useUnit;
    @XmlTransient
    private Boolean useYearCode;
    @XmlTransient
    private Boolean useConfidenceGrades;
    @XmlTransient
    private Boolean allowGroupSelection;
    private String sectionType;
    @XmlTransient
    private int itemCodeColumn;
    private int rowCount;
    private int colCount;

    public TWSectionDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTwSectionId() {
        return twSectionId;
    }

    public void setTwSectionId(int twSectionId) {
        this.twSectionId = twSectionId;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getUseLineNumber() {
        return useLineNumber;
    }

    public void setUseLineNumber(Boolean useLineNumber) {
        this.useLineNumber = useLineNumber;
    }

    public Boolean getUseItemCode() {
        return useItemCode;
    }

    public void setUseItemCode(Boolean useItemCode) {
        this.useItemCode = useItemCode;
    }

    public Boolean getUseItemDescription() {
        return useItemDescription;
    }

    public void setUseItemDescription(Boolean useItemDescription) {
        this.useItemDescription = useItemDescription;
    }

    public Boolean getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(Boolean useUnit) {
        this.useUnit = useUnit;
    }

    public Boolean getUseYearCode() {
        return useYearCode;
    }

    public void setUseYearCode(Boolean useYearCode) {
        this.useYearCode = useYearCode;
    }

    public Boolean getUseConfidenceGrades() {
        return useConfidenceGrades;
    }

    public void setUseConfidenceGrades(Boolean useConfidenceGrades) {
        this.useConfidenceGrades = useConfidenceGrades;
    }

    public Boolean getAllowGroupSelection() {
        return allowGroupSelection;
    }

    public void setAllowGroupSelection(Boolean allowGroupSelection) {
        this.allowGroupSelection = allowGroupSelection;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public int getItemCodeColumn() {
        return itemCodeColumn;
    }

    public void setItemCodeColumn(int itemCodeColumn) {
        this.itemCodeColumn = itemCodeColumn;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }
}
