package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Edgar on 06/03/2017.
 */
//@XmlRootElement(name = "twColumn")
@XmlRootElement(name = "column")
@XmlType(propOrder = { "id", "twCells" })
@XmlAccessorType(XmlAccessType.FIELD)
public class TWColumn {
    private int id;

    @XmlElement(name="cells")
    private List<TWCell> twCells;

    public TWColumn() {
        this.setTwCells(new ArrayList<TWCell>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TWCell> getTwCells() {
        return twCells;
    }

    public void setTwCells(List<TWCell> twCells) {
        this.twCells = twCells;
    }

    public void addTwCell(TWCell twCell) {
        this.getTwCells().add(twCell);
    }
}
