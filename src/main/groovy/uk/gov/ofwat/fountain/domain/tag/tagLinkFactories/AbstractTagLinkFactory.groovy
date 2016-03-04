package uk.gov.ofwat.fountain.domain.tag.tagLinkFactories

import uk.gov.ofwat.fountain.domain.Company
import uk.gov.ofwat.fountain.domain.Data
import uk.gov.ofwat.fountain.domain.Value
import uk.gov.ofwat.fountain.domain.run.Run

class AbstractTagLinkFactory {
	
	private DataTagLinkFactory dataTagLinkFactory
	private RunTagLinkFactory runTagLinkFactory
	private CompanyTagLinkFactory companyTagLinkFactory
	private ValueTagLinkFactory valueTagLinkFactory

	public void setDataTagLinkFactory(DataTagLinkFactory dataTagLinkFactory) {
		this.dataTagLinkFactory = dataTagLinkFactory
	}
	
	public void setRunTagLinkFactory(RunTagLinkFactory runTagLinkFactory) {
		this.runTagLinkFactory = runTagLinkFactory
	}
	
	public void setCompanyTagLinkFactory(CompanyTagLinkFactory companyTagLinkFactory) {
		this.companyTagLinkFactory = companyTagLinkFactory
	}
	
	public void setValueTagLinkFactory(ValueTagLinkFactory valueTagLinkFactory) {
		this.valueTagLinkFactory = valueTagLinkFactory
	}
	
	def getTagLinkFactory(Class clazz){
		switch(clazz){
			case Data:
				return dataTagLinkFactory
			case Run:
				return runTagLinkFactory
			case Company:
				return companyTagLinkFactory
			case Value:
				return valueTagLinkFactory
			default: 
				return null
		}
	}
}
