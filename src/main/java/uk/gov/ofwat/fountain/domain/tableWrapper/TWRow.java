package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Edgar on 06/03/2017.
 */
//@XmlRootElement(name = "twRow")
@XmlRootElement(name = "row")
@XmlType(propOrder = { "id", "rowNumber", "twCells" })
@XmlAccessorType(XmlAccessType.FIELD)
public class TWRow {
    private int id;
    private int rowNumber;
    @XmlElement(name="cells")
    private List<TWCell> twCells;

    public TWRow() {
        this.twCells = new ArrayList<TWCell>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<TWCell> getTwCells() {
        return twCells;
    }

    public void setTwCells(List<TWCell> twCells) {
        this.twCells = twCells;
    }

    public void addTwCell(TWCell twCell) {
        this.twCells.add(twCell);
    }

}
