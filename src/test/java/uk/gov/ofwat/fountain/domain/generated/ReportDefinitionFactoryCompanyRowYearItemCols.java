package uk.gov.ofwat.fountain.domain.generated;

import uk.gov.ofwat.fountain.domain.ReportDefinition;
import com.thoughtworks.xstream.XStream;
import java.io.InputStream;

public class ReportDefinitionFactoryCompanyRowYearItemCols
{

	public ReportDefinition buildReportDefinition()
	{
		XStream xstream = new XStream();
		InputStream in = getClass().getResourceAsStream( "/uk/gov/ofwat/fountain/domain/generated/ReportDefinitionFactoryCompanyRowYearItemCols.xml" );
		return (ReportDefinition)xstream.fromXML( in );
	}

}
