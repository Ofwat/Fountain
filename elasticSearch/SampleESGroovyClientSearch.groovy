@GrabConfig(systemClassLoader=true)
@Grapes([ 
    @Grab(group='org.elasticsearch', module='elasticsearch-lang-groovy', version='2.2.0'),
    @Grab(group='mysql', module='mysql-connector-java', version='5.1.6'),
    @Grab(group='com.google.code.gson', module='gson', version='2.2.4'),
    @Grab(group='com.vividsolutions', module='jts', version='1.13') 
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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import groovy.sql.Sql
import com.google.gson.Gson
import groovy.json.JsonSlurper


gson = new Gson()
sql = Sql.newInstance(
    'jdbc:mysql://localhost/fountain', 
    'fountain',
    'fountain', 
    'com.mysql.jdbc.Driver'
);

// on startup
Settings settings = ImmutableSettings.settingsBuilder()
        .put("cluster.name", "fountain").build();
client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300))        

println client.class

QueryBuilder qb = QueryBuilders.multiMatchQuery("test","_all");

//QueryBuilder qb = QueryBuilders.idsQuery().ids("102"); //Works

SearchResponse response = client.prepareSearch("fountain").setTypes("report").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(qb).setFrom(10).setSize(100).execute().actionGet();

println response.class

def slurper = new JsonSlurper()
def result = slurper.parseText(response.toString())
println result.hits.total
 
client.close() 
 