package uk.gov.ofwat.fountain.domain.tag.tagLinkFactories

import uk.gov.ofwat.fountain.dao.CompanyDao
import uk.gov.ofwat.fountain.domain.Company
import uk.gov.ofwat.fountain.domain.Data

class CompanyTagLinkFactory {
	
	private CompanyDao companyDao

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao
	}

	List<Company> getEntities(tagLinks){
		List<Company> result = new ArrayList<Company>()
		tagLinks.each{
			result.add(companyDao.findById((int)it.entityId))
		}
		return result		
	}
}
