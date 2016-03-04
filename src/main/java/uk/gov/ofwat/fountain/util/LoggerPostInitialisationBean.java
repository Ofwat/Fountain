package uk.gov.ofwat.fountain.util;

import javax.annotation.PostConstruct;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.logging.slf4j.SLF4JLoggerImplFactory;

/**
 * Class to perform Logger post initialisation - Currently only switches Morphia to use Slf4j implementation. 
 * @author james.toddington
 *
 */
public class LoggerPostInitialisationBean {
	@PostConstruct
	public void disableLogging(){
		MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);
	}
}
