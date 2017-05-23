package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Adam Edgar on 06/03/2017.
 */
//@XmlRootElement(name = "twCell")
@XmlRootElement(name = "cell")
@XmlType(propOrder = {
        "id",
        "displayText",
        "header",
        "sectionId",
        "startRowId",
        "startColumnNo",
        "cellType",
        "validations",
        "colSpan",
        "rowSpan",
        "content",
        "key"})
@XmlAccessorType(XmlAccessType.FIELD)
public class TWCell implements TWComponent {
    private int id;
    private String displayText;
//    @SerializedName("isHeader")
    @XmlElement(name="isHeader")
    private Boolean header;
    private int sectionId;
    private int startRowId;
    @XmlElement(name="startColumnId")
    private int startColumnNo;
    private String cellType;
    private List<String> validations;
    private int colSpan;
    private int rowSpan;
    private String content;
    private String key;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Boolean getHeader() {
        return header;
    }

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getStartRowId() {
        return startRowId;
    }

    public void setStartRowId(int startRowId) {
        this.startRowId = startRowId;
    }

    public int getStartColumnNo() {
        return startColumnNo;
    }

    public void setStartColumnNo(int startColumnNo) {
        this.startColumnNo = startColumnNo;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public List<String> getValidations() {
        return validations;
    }

    public void setValidations(List<String> validations) {
        this.validations = validations;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
