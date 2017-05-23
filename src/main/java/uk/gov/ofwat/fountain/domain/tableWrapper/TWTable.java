package uk.gov.ofwat.fountain.domain.tableWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adam Edgar on 06/03/2017.
 */

@XmlRootElement(name = "table")
@XmlType(propOrder = { "id", "name", "description", "twSections" })
@XmlAccessorType(XmlAccessType.FIELD)
public class TWTable implements TWComponent {
    private int id;
    private String name;
    private String description;
    private String companyType;

    @XmlElement(name="sections")
    private List<TWSection> twSections;

    public TWTable() {
        this.twSections = new ArrayList<TWSection>();
    }

    @XmlElement(name="fountainId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<TWSection> getTwSections() {
        return twSections;
    }

    public void setTwSections(List<TWSection> twSections) {
        this.twSections = twSections;
    }

    public void addTwSection(TWSection twSection) {
        this.twSections.add(twSection);
    }

}
