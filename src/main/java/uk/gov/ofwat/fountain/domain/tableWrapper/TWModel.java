package uk.gov.ofwat.fountain.domain.tableWrapper;

//import com.google.gson.annotations.SerializedName;
import javax.xml.bind.annotation.*;

/**
* Created by Adam Edgar on 06/03/2017.
 */
@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "twTable" })
public class TWModel implements TWComponent {
//    @SerializedName("table")
    @XmlElement(name="table")
    private TWTable twTable;

    public TWTable getTwTable() {
        return twTable;
    }

    public void setTwTable(TWTable twTable) {
        this.twTable = twTable;
    }
}
