package uk.gov.ofwat.fountain.api.report;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.run.Run;

// used to keep track of the horizontal entities
class Triplet{
	ReportItem ri;
	Interval interval;
	Company company;
	RunTag runTag;
	
	@Override
	protected Triplet clone() {
		Triplet clone = new Triplet();
		clone.ri = this.ri;
		clone.interval = this.interval;
		clone.company = this.company;
		clone.runTag = this.runTag;
		return clone;
	}
	
}

