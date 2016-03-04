package uk.gov.ofwat.fountain.domain.generated;

import com.thoughtworks.xstream.XStream;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import java.io.InputStream;

public class ReportDefinitionFactoryItemCompanyRowsYearCol
{

	public ReportDefinition buildReportDefinition()
	{
		XStream xstream = new XStream();
		InputStream in = getClass().getResourceAsStream( "/uk/gov/ofwat/fountain/domain/generated/ReportDefinitionFactoryItemCompanyRowsYearCol.xml" );
		return (ReportDefinition)xstream.fromXML( in );
	}

}
