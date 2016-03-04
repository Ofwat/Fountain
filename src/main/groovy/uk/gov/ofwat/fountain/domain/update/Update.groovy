package uk.gov.ofwat.fountain.domain.update
import uk.gov.ofwat.fountain.domain.Entity;

class Update implements Entity{
	Long id
	Long sortOrder
	String title
	String description
	String externalLink

	public int getId(){
		return this.id
	}
		
}
