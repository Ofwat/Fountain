package uk.gov.ofwat.fountain.domain.generated;

import com.thoughtworks.xstream.XStream;
import java.io.InputStream;
import uk.gov.ofwat.fountain.domain.ReportDefinition;

public class ReportDefinitionFactoryYearCompanyRowsItemCol2
{

	public ReportDefinition buildReportDefinition()
	{
		XStream xstream = new XStream();
		InputStream in = getClass().getResourceAsStream( "/uk/gov/ofwat/fountain/domain/generated/ReportDefinitionFactoryYearCompanyRowsItemCol2.xml" );
		return (ReportDefinition)xstream.fromXML( in );
	}

}
