package uk.gov.ofwat.fountain

import java.util.concurrent.Executors.DelegatedExecutorService;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue


class HibernateTransactionalSpringContextTests extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected SessionFactory sessionFactory
	
	public HibernateTransactionalSpringContextTests(){
		super()
 	}

	//def invokeMethod(String name, Object args){
		//System.out.println "Called method: $name"
		/*
		if(name.startsWith('test')){
			System.out.println("TEST: ${delegate.getClass().getCanonicalName()}:${name}")
		}
		def calledMethod = metaClass.getMetaMethod(name, args)
		calledMethod ? calledMethod.invoke(this, args) : inTestTx(args)
		*/
	//}
	
	@Before
	public void setUp() throws Exception {
		sessionFactory = applicationContext.getBean("sessionFactory")
	}
	
	/**
	 * Closure to run a part of a test and explicitly flush the Hibernate session - 
	 * persisting to the DB for a roll back when the transactional unit test ends. 
	 */
	def inTestTx = { 
		def session = sessionFactory.getCurrentSession()
		it()
		session.flush()
	}
}
