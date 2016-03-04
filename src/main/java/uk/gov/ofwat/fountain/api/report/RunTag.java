package uk.gov.ofwat.fountain.api.report;

import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class RunTag {
	private Run run;
	private RunModelTag runModelTag;
	
	public RunTag(Run run, RunModelTag runModelTag) {
		super();
		this.run = run;
		this.runModelTag = runModelTag;
	}
	public Run getRun() {
		return run;
	}
	public void setRun(Run run) {
		this.run = run;
	}
	public RunModelTag getRunModelTag() {
		return runModelTag;
	}
	public void setRunModelTag(RunModelTag runModelTag) {
		this.runModelTag = runModelTag;
	}

}
