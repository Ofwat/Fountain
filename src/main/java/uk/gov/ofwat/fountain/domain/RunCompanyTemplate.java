package uk.gov.ofwat.fountain.domain;

import java.util.Date;
import java.util.List;

public class RunCompanyTemplate implements Entity {

	private int id;
	private String name;
	private String description;
	private Date created;
	private String createdBy;
	private List<Company> companies;
	private List<Integer> companyIds;
	
	public RunCompanyTemplate() {
		super();
	}

	public RunCompanyTemplate(int id, String name, String description,
			Date created, String createdBy, List<Company> companies,
			List<Integer> companyIds) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.createdBy = createdBy;
		this.companies = companies;
		this.companyIds = companyIds;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Integer> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Integer> companyIds) {
		this.companyIds = companyIds;
	}
	
}
