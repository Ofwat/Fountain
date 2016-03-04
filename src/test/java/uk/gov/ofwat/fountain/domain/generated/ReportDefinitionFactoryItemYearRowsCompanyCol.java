package uk.gov.ofwat.fountain.domain.generated;

import uk.gov.ofwat.fountain.domain.ReportDefinition;
import java.io.InputStream;
import com.thoughtworks.xstream.XStream;

public class ReportDefinitionFactoryItemYearRowsCompanyCol
{

	public ReportDefinition buildReportDefinition()
	{
		XStream xstream = new XStream();
		InputStream in = getClass().getResourceAsStream( "/uk/gov/ofwat/fountain/domain/generated/ReportDefinitionFactoryItemYearRowsCompanyCol.xml" );
		return (ReportDefinition)xstream.fromXML( in );
	}

}
