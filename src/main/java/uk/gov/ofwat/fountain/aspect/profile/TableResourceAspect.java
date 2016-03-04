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

package uk.gov.ofwat.fountain.aspect.profile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(300)
public class TableResourceAspect {
	

	private static Logger log = Logger.getLogger(TableResourceAspect.class);
	private static Map<String, Integer> callCounts = Collections.synchronizedMap(new HashMap<String, Integer>());
	
	@Around("execution(public * uk.gov.ofwat.fountain.rest.TableResource.get*(..))")
	public Object profileRestCall(ProceedingJoinPoint pjp)
			throws Throwable {

		callCounts = Collections.synchronizedMap(new HashMap<String, Integer>());
		
		Long startTime = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		Long elapsedTime = System.currentTimeMillis() - startTime;

		StringBuffer elapsedTimeMessage = new StringBuffer("Profiling time in ms : " + elapsedTime + " for method " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + "()");
		log.debug(elapsedTimeMessage);

		synchronized(callCounts) {
			for (String method : callCounts.keySet()) {
				int count = callCounts.get(method);
				StringBuffer methodCallCountMessage = new StringBuffer("Profiling no. of interval calls : " + count + " for method " + method);
				log.debug(methodCallCountMessage);
			}
		}

		return retVal;
	}

	@After("execution(public * uk.gov.ofwat.fountain.dao.*.*(..))")
	public void countDAOCalls(JoinPoint jp)
			throws Throwable {
		String method = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName() + "()";
		if (!callCounts.containsKey(method)) {
			callCounts.put(method, 0);
		}
		int count = callCounts.get(method);
		count++;
		callCounts.put(method, count);
	}

}