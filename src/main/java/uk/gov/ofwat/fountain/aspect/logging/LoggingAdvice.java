/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.aspect.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;


/**
 * log a call prior to the method being invoked
 * 
 */
@Aspect
@Order(100)
public class LoggingAdvice {
	

	private static org.apache.log4j.Logger log = Logger.getLogger(LoggingAdvice.class);

	
	@Before("execution(* uk.gov.ofwat.fountain.api.*.*(..))")
	public void before(JoinPoint jp)
			throws Throwable {
		
		StringBuffer message = new StringBuffer("LOGGING ASPECT: method ");
		Object[] args = jp.getArgs();
		message.append(jp.getSignature().getName());
		message.append(" invoked with args: ");
		for(Object o: args){
			message.append(o.getClass().getName() + " : " + o);
		}
		log.debug(message);
	}
	
//	@AfterThrowing(
//		    pointcut="uk.gov.ofwat.fountain.api.*.*(..)",
//		    throwing="ex")
//    public void afterThrowing(JoinPoint jp, Throwable ex){
//		
//		log.error("exception thrown by " + jp.getSignature().getName() +  " : " + ex.getMessage());
//	}


}