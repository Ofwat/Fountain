package uk.gov.ofwat.fountain.api.report;

import java.io.File;

public interface ReportWriter{
	public void generateReportFile(File reportTemplate, File outputDir, String localDir );
}