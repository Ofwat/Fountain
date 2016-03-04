package uk.gov.ofwat.fountain.aspect.audit

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.Response

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import uk.gov.ofwat.fountain.api.SearchService
import uk.gov.ofwat.fountain.api.MongoService
import static org.mockito.Mockito.*

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners([])
@ContextConfiguration(locations = ["classpath:test_beans.xml"])
class AuditAspectTest extends AbstractTransactionalJUnit4SpringContextTests {
	//need to mock http servlet request and http servlet response as wellas fire up a test instance of mongo and elastic search...
	
	@Autowired
	private SearchService elasticSearchService;
	@Autowired
	private MongoService mongoService
	
	private AuditAdvice auditAdvice
	private ProceedingJoinPoint pjp
	private HttpServletRequest req
	private Response res
	
	@Before
	public void initialize() {
		//Need to mock ProceedingJoinPoint
		pjp = mock(ProceedingJoinPoint.class)
		//Need to mock HttpServletRequest
		req = mock(HttpServletRequest.class)
		//Need to mock HttpServletResponse 
		res = mock(Response.class)
	}
	
	@Test
	public void sampleTest(){
		//Set up mocks
		when(res.getStatus()).thenReturn(200)//OR whatever we like 400 etc....
		//when(res.getEntity()).thenReturn() //This will be the table DTO potentially, look up from file?
		
		when(pjp.getTarget().getClass().getName()).thenReturn("MOCK TEST_CLASS")
		when(pjp.getSignature().getName()).thenReturn("MOCK TEST METHOD")
		when(pjp.proceed()).thenReturn(res) //Mock the object too.
		when(pjp.getArgs()).thenReturn(new Object[10]) //Should we add some args.
		//Need to make sure the getContent() call in the Advice returns the 'content' we expect!
		//Method method = mock(Method.class)
		
		MethodSignature methodSignature = mock(MethodSignature.class)
		when(pjp.getStaticPart().getSignature()).thenReturn(methodSignature)
		 
				
		when(req.getMethod()).thenReturn("GET")//Could be GET, POST, PUT, DELETE etc
		when(req.getRequestURI()).thenReturn("MOCK TEST URI")
		when(req.getUserPrincipal().getName()).thenReturn("MOCK USER NAME")
		when(req.isUserInRole()).thenReturn(true)
		
		AuditAdvice auditAdvice = buildAudit()
	}
	
	private AuditAdvice buildAudit(){
		auditAdvice = new AuditAdvice()
		auditAdvice.setHttpServeletRequest(req)
		auditAdvice.setHttpServeletResponse(res)
		auditAdvice.setMongoService(mongoService)
	}
	
}
