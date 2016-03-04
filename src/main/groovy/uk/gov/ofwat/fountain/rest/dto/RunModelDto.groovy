package uk.gov.ofwat.fountain.rest.dto

class RunModelDto {
	int id
	ModelDto model
	Boolean completed
	String completedDate
	String completedBy
	ArrayList<RunModelCompanyDto> runModelCompanies
	int runOrder
	Boolean tagged
}
