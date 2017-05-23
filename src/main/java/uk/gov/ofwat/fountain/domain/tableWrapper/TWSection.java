package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Edgar on 06/03/2017.
 */
@XmlRootElement(name = "section")
//@XmlType(propOrder = { "id", "twSectionDetails", "twColumns", "twRows" })
@XmlType(propOrder = { "id", "twSectionDetails", "twRows" })
@XmlAccessorType(XmlAccessType.FIELD)
public class TWSection implements TWComponent {
    private int id;
    @XmlElement(name="sectionDetails")
    private TWSectionDetails twSectionDetails;
//    @XmlElement(name="columns")
//    private List<TWColumn> twColumns;
    @XmlElement(name="rows")
    private List<TWRow> twRows;
//    @XmlElement(name="cells")
//    private Map<Integer, TWCell> twCells;

    public TWSection() {
//        this.twColumns = new ArrayList<TWColumn>();
        this.twRows = new ArrayList<TWRow>();
//        this.twCells = new HashMap<Integer, TWCell>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<TWColumn> getTwColumns() {
//        return twColumns;
//    }
//
//    public void setTwColumns(List<TWColumn> twColumns) {
//        this.twColumns = twColumns;
//    }
//
//    public void addTwColumn(TWColumn twColumn) {
//        this.twColumns.add(twColumn);
//    }

    public List<TWRow> getTwRows() {
        return twRows;
    }

    public void setTwRows(List<TWRow> twRows) {
        this.twRows = twRows;
    }

    public void addTwRow(TWRow twRow) {
        this.twRows.add(twRow);
    }

//    public Map<Integer, TWCell> getTwCells() {
//        return twCells;
//    }
//
//    public void setTwCells(Map<Integer, TWCell> twCells) {
//        this.twCells = twCells;
//    }
//
//    public void setTwCell(TWCell twCell) {
//        this.twCells.put(twCell.getId(), twCell);
//    }

    public TWSectionDetails getTwSectionDetails() {
        return twSectionDetails;
    }

    public void setTwSectionDetails(TWSectionDetails twSectionDetails) {
        this.twSectionDetails = twSectionDetails;
    }
}
