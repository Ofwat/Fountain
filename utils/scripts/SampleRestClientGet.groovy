@Grapes(
    @Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
)
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT

String endpoint = "http://localhost:8090"
          
def http = new HTTPBuilder(endpoint)
//http.setProxy('localhost', 8888, 'http')


http.get( path: '/chunks/26815984-cbfa-403e-a7ff-def9601aca39/b643da3f-8f79-46bd-ad8c-26b627ee8320.json', requestContentType: JSON) { resp, json ->         
    println "GET Success: ${resp.statusLine}"
    println json.toString()
    //assert resp.statusLine.statusCode == 201
}