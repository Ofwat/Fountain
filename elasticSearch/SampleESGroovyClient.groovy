@GrabConfig(systemClassLoader=true)
@Grapes([ 
    @Grab(group='org.elasticsearch', module='elasticsearch-lang-groovy', version='2.2.0'),
    @Grab(group='mysql', module='mysql-connector-java', version='5.1.6'),
    @Grab(group='com.google.code.gson', module='gson', version='2.2.4') 
])

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
import groovy.sql.Sql
import com.google.gson.GsonBuilder


gson = new GsonBuilder().setPrettyPrinting().create()
sql = Sql.newInstance(
    //'jdbc:mysql://fnttest201:4406/fountain', 
    'jdbc:mysql://localhost:3306/fountain', 
    'fountain',
    'fountain', 
    'com.mysql.jdbc.Driver'
);

// on startup
Settings settings = ImmutableSettings.settingsBuilder()
        .put("cluster.name", "fountain").build();
client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300))        

println client.class

//indexItems()
//indexReports()
indexTables()

def indexTables(){
    def attemptCount = 0;
    def actualCount = 0;
    sql.eachRow( 'select t.id, t.name as tableName, m.name as modelName, m.code as modelCode, mt.name as modelType from tbl_table t, tbl_model m, tbl_modelType mt where t.modelId = m.id and m.modelTypeId = mt.id' ) {
        //if(count <= 1){
            attemptCount++
            def tableJson = convertTableToElasticSearchDto(it.id, it.tableName, it.modelName, it.modelCode, it.modelType)   
            //println tableJson  
            
        
            //send to ES
            try{
                //GetResponse response = client.prepareGet("fountain", "report", "1").execute().actionGet();
                //println response.getSourceAsString()
                IndexResponse response = client.prepareIndex("fountain", "table", it.id.toString()).setSource(tableJson).execute().actionGet();      
                actualCount++
            }catch(Exception e){
                println e
                client.close() //remove if we want it to continue even if there is an error!
            }
        //}
    }
    println "Loaded ${actualCount} of ${attemptCount} table records"
}

def convertTableToElasticSearchDto(tableId, tableName, modelName, modelCode, modelType){
    
    def table = ["id":tableId, "model_name":modelName, "model_code":modelCode, "name":tableName]
    def items = []

    sql.eachRow("""select distinct t.name, p.itemId, i.code, i.id, ip.description, 
        ip.definition, ip.version, i.unit, null as modelId
        from tbl_table t, tbl_table_pots tp, tbl_pot p,
        tbl_item i, tbl_itemproperties ip
        where tp.tableId = t.id 
        and p.id = tp.potId
        and p.itemId = i.id
        and ip.itemId = i.id
        and t.id = ${tableId}"""){
        //println it
        def item = [id:it.id, code:it.code, unit:it.unit, description:it.description, definition:it.definition, model_id:it.modelId, version:it.version]
        items.add(item)
    }
    
    //Convert these to a DTO to store in ES or the correct JSON to store in ES. 
    table.put("items", items)

    def json = gson.toJson(table); 
    return json
}


def indexReports(){
    sql.eachRow( 'select * from tbl_report' ) { 
         def reportJson = convertReportToElasticSearchDto(it.id)
        //send to ES
        try{
            //GetResponse response = client.prepareGet("fountain", "report", "1").execute().actionGet();
            //println response.getSourceAsString()
            IndexResponse response = client.prepareIndex("fountain", "report", it.id.toString()).setSource(reportJson).execute().actionGet();
            //println "id:$it.id -- name:${it.name} -- version:${response.version}"        
        }catch(Exception e){
            println e
            client.close() //remove if we want it to continue even if there is an error!
        }
    }
}

def indexItems(){
    def attemptCount = 0;
    def actualCount = 0;
    try{
        sql.eachRow( "SELECT distinct i.id, i.code, i.unit, i.name, ip.description, ip.definition, ip.version FROM tbl_item i, tbl_itemproperties ip where ip.itemId = i.id" ) { 
            attemptCount++
            def item = [id:it.id, code:it.code, unit:it.unit, name:it.name, description:it.description, definition:it.definition]       
            def reports = []
            def tables = []
            
            sql.eachRow("select distinct r.id, r.name  from tbl_report_items ri, tbl_report r where ri.itemId = ${it.id} and r.id = ri.reportId"){ rd ->
                 def report = [id:rd.id, name:rd.name]
                 reports.add(report)                         
            }
            item.put("reports", reports)
            
            sql.eachRow("""select distinct t.id as id, m.name as modelName, m.code as modelCode, t.name as name 
from tbl_pot p, tbl_table_pots tp, tbl_table t, tbl_model m
where p.itemid = ${it.id} and p.id = tp.potId and t.id = tp.tableId
and m.id = t.modelId"""){ td ->
                 def table = [name:td.name, model_name:td.modelName, model_code:td.modelCode]
                 tables.add(table)                         
            }                        
            item.put("tables", tables)
            
            def itemJson = gson.toJson(item)
            //println itemJson
            IndexResponse response = client.prepareIndex("fountain", "item", it.id.toString()).setSource(itemJson).execute().actionGet()
            actualCount++
            if(attemptCount % 100 == 0){
                println "Added ${actualCount} of ${attemptCount} items."
            }
        }
    }
    catch(Exception e){
        println e
        client.close()
    }    
    println "Added ${actualCount} items."
}

client.close()

def convertReportToElasticSearchDto(reportId){
    //Look up the report and all the items + item properties.
    def rd = sql.firstRow("select id, name, user, lastModified, deleted, public, groupId, iplRun, description from tbl_report where id = ${reportId}")
    //println reportData
    def date = new Date(rd.lastmodified.getTime())
    def dateStr = date.format("yyyy-MM-dd'T'HH:mm:ss")
    def report = [id:rd.id, name:rd.name, user:rd.user, lastModified:dateStr, deleted:rd.deleted, public:rd.public, groupId:rd.groupId, iplRun:rd.iplRun, readOnly:true, description:rd.description]
    //get all the items and item proerties.
    def items = []
    sql.eachRow("select distinct i.id, i.code, i.unit, ip.description, ip.version, ip.definition, tri.modelId from tbl_item i, tbl_report_items tri, tbl_itemproperties ip where tri.itemId = i.id and ip.itemId = i.id and tri.reportId = ${reportId}"){
//        println it
        def item = [id:it.id, code:it.code, unit:it.unit, description:it.description, definition:it.definition, model_id:it.modelId, version:it.version]
        items.add(item)
    }
    report.put("items", items)
    //Convert these to a DTO to store in ES or the correct JSON to store in ES. 
    def json = gson.toJson(report); 
    return json
}
 