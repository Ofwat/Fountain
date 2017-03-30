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
package uk.gov.ofwat.fountain.rest;

import uk.gov.ofwat.fountain.rest.interceptors.ContentTypeSetterPreProcessorInterceptor;
import uk.gov.ofwat.fountain.rest.interceptors.LoggingInterceptor;
import uk.gov.ofwat.fountain.util.SpringApplicationContext;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class FountainApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();
   private Set<Class<?>> empty = new HashSet<Class<?>>();
   
   
   public FountainApplication() {
	  
	  // Spring managed resources
	  singletons.add(SpringApplicationContext.getBean("auditAdvice"));
	  singletons.add(SpringApplicationContext.getBean("tableResource"));
	  singletons.add(SpringApplicationContext.getBean("companyResource"));
	  singletons.add(SpringApplicationContext.getBean("auditResource"));
	  singletons.add(SpringApplicationContext.getBean("basketResource"));
	  singletons.add(SpringApplicationContext.getBean("itemResource"));
	  singletons.add(SpringApplicationContext.getBean("intervalResource"));
	  singletons.add(SpringApplicationContext.getBean("checkoutResource"));
	  singletons.add(SpringApplicationContext.getBean("reportResource"));
	  singletons.add(SpringApplicationContext.getBean("dataResource"));
	  singletons.add(SpringApplicationContext.getBean("modelResource"));
	  singletons.add(SpringApplicationContext.getBean("fileResource"));
	  singletons.add(SpringApplicationContext.getBean("confidenceGradeResource"));
	  singletons.add(SpringApplicationContext.getBean("teamResource"));
	  singletons.add(SpringApplicationContext.getBean("groupResource"));
      singletons.add(SpringApplicationContext.getBean("lockResource"));
      singletons.add(SpringApplicationContext.getBean("cacheResource"));
      singletons.add(SpringApplicationContext.getBean("wikiAddressResource"));
      singletons.add(SpringApplicationContext.getBean("runResource"));
      singletons.add(SpringApplicationContext.getBean("bannerResource"));
      singletons.add(SpringApplicationContext.getBean("tagResource"));
      singletons.add(SpringApplicationContext.getBean("agendaResource"));
      singletons.add(SpringApplicationContext.getBean("excelDataTableMarshaller"));
      singletons.add(SpringApplicationContext.getBean("excelReportMarshaller"));
      singletons.add(SpringApplicationContext.getBean("searchResource"));
      singletons.add(SpringApplicationContext.getBean("updateResource"));
      singletons.add(SpringApplicationContext.getBean("tableWrapperResource"));
      //singletons.add(SpringApplicationContext.getBean("auditInterceptor"));
      //singletons.add(SpringApplicationContext.getBean("postAuditInterceptor"));
      //simple resources or resources with their own initialisation
      //singletons.add(new ExcelMarshaller());
      singletons.add(new ContentTypeSetterPreProcessorInterceptor());
      singletons.add(new LoggingInterceptor());
   }

   @Override
   public Set<Class<?>> getClasses() {
      return empty;
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
