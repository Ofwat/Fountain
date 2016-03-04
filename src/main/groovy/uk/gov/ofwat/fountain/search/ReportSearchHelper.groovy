package uk.gov.ofwat.fountain.search

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Classes;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.node.Node;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.*;
import org.slf4j.Logger;

import groovy.sql.Sql
import com.google.gson.GsonBuilder

class ReportSearchHelper {
	
	def gson
	def sql
	def client 
	static ReportSearchHelper instance
	Properties appProperties
	Properties esProperties
	
	private static Logger logger = LoggerFactory.getLogger(ReportSearchHelper.class)
	
	private ReportSearchHelper(){
		gson = new GsonBuilder().setPrettyPrinting().create()
		appProperties = getPropertiesFromClasspath("app.properties")
		esProperties = getPropertiesFromClasspath("elasticsearch.properties")
	}	
	
	public static ReportSearchHelper getInstance(){
		if(instance == null){
			instance = new ReportSearchHelper()
		}else{
			return instance
		}
	}
	
	public void indexReports(){
		sql = Sql.newInstance(
			"${appProperties.getProperty('db.url')}",
			"${appProperties.getProperty('db.user')}",
			"${appProperties.getProperty('db.password')}",
			'com.mysql.jdbc.Driver'
		);
		// on startup
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "${esProperties.getProperty('elasticsearch.search.cluster')}").build();
		client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("${esProperties.getProperty('elasticsearch.search.host')}", new Integer(esProperties.getProperty('elasticsearch.search.port'))))
		
		def reportCount = 0
		
		sql.eachRow( 'select * from tbl_report' ) {
			def reportJson = convertReportToElasticSearchDto(it.id)
		   //send to ES
		   try{
			   reportCount++
			   //GetResponse response = client.prepareGet("fountain", "report", "1").execute().actionGet();
			   //println response.getSourceAsString()
			   IndexResponse response = client.prepareIndex("fountain", "report", it.id.toString()).setSource(reportJson).execute().actionGet();
			   logger.debug("Indexed report with id ${it.id.toString()}")
		   	   if(reportCount%100 == 0){
					  logger.info("Indexed ${reportCount} reports.")
			   }
			   //println "id:$it.id -- name:${it.name} -- version:${response.version}"
		   }catch(Exception e){
			   println e
			   client.close() //remove if we want it to continue even if there is an error!
		   }
	   }
       client.close()
	   sql.close()
	}
	
	def convertReportToElasticSearchDto(reportId){
		//Look up the report and all the items + item properties.
		def rd = sql.firstRow("select id, name, user, lastModified, deleted, public, groupId, iplRun, description from tbl_report where id = ${reportId}")
		//println reportData
		def date = new Date(rd.lastmodified.getTime())
		def dateStr = date.format("yyyy-MM-dd'T'HH:mm:ss")
		def tags = ["tag1", "tag2"];
		def report = [id:rd.id, name:rd.name, tags:tags, user:rd.user, lastModified:dateStr, deleted:rd.deleted, public:rd.public, groupId:rd.groupId, iplRun:rd.iplRun, readOnly:true, description:rd.description]
		//get all the items and item properties.
		def items = []
		sql.eachRow("select distinct i.id, i.code, i.unit, ip.description, ip.version, ip.definition, tri.modelId from tbl_item i, tbl_report_items tri, tbl_itemproperties ip where tri.itemId = i.id and ip.itemId = i.id and tri.reportId = ${reportId} and ip.version = (SELECT max(version) FROM tbl_itemproperties WHERE itemid = i.id)"){
			//println it
			def item = [id:it.id, code:it.code, unit:it.unit, description:it.description, definition:it.definition, model_id:it.modelId, version:it.version]
			items.add(item)
		}
		report.put("items", items)
		//Convert these to a DTO to store in ES or the correct JSON to store in ES.
		def json = gson.toJson(report)
		return json
	}
	
	private Properties getPropertiesFromClasspath(String propFileName) throws IOException {
		// loading xmlProfileGen.properties from the classpath
		Properties props = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
			.getResourceAsStream(propFileName);
	
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
				+ "' not found in the classpath");
		}
	
		props.load(inputStream);
		return props;
	}
	
	
}
