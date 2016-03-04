package uk.gov.ofwat.fountain.rest.dto

class RunTemplateDto {
	int id
	String name
	String Description
	String createdAt
	String createdBy
	List<ModelDto> models = []		
}
