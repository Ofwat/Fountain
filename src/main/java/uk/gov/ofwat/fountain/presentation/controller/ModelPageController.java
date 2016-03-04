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

package uk.gov.ofwat.fountain.presentation.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import sun.awt.CharsetString;
import uk.gov.ofwat.fountain.api.CompanyService;
import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Table;



@Controller
@RequestMapping("*")
public class ModelPageController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(ModelPageController.class);

	@RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
							        HttpServletResponse response) throws Exception {
		setCacheSeconds(10);
        StringBuffer url = request.getRequestURL();
        
        String destination = url.substring(url.indexOf("/jsp"));
        destination = destination.substring(0, destination.indexOf("."));
        
        ModelAndView mav = null;
        
        if (destination.equals("/jsp/protected/reportDisplay")) {
        	String reportId = request.getParameter("reportId");
        	String reportPath = "/jsp/protected/report/report-" + reportId;
			Resource resource = getWebApplicationContext().getResource(reportPath + ".jsp");
			if(!resource.exists()){ //Try and get the HTML representation
				resource = getWebApplicationContext().getResource(reportPath + ".html");
			}
			//mav = new ModelAndView(reportPath + '.' + FilenameUtils.getExtension(resource.getFilename()));
			mav = new ModelAndView(reportPath);
			if (resource == null || !resource.exists()){
				getWebApplicationContext().getBean(ReportService.class).buildReport(Integer.parseInt(reportId));
				int count = 0;
				while (!resource.exists() && count < 100) {
					Thread.currentThread().sleep(100);
					count++;
				}
				if (!resource.exists()) {
					mav = new ModelAndView("/jsp/error");
					mav.addObject("error", "Failed to generate report.");
					logger.error("Failed to generate report " + reportPath + ".");
				}
			}
        }
        else {
        	mav = new ModelAndView(destination);
            int companyId = Integer.parseInt(request.getParameter("companyId"));
            String sub1 = destination.substring(destination.indexOf("model/") + 6);
            String modelCode =  sub1.substring(0, sub1.indexOf("/"));
            String tableName = sub1.substring(sub1.indexOf("/") + 1);
            //URL decode tableName - Just in case!
            tableName = URLDecoder.decode(tableName, CharEncoding.UTF_8).toUpperCase();

// All the following will need to be replaced by JS/AJAX/REST            
//            List<Table> tablesForCompany = getWebApplicationContext().getBean(ModelService.class).getTablesForModelCodeAndCompany(modelCode, companyId);
//            
//            int tableId = 0;
//            for(Table table: tablesForCompany){
//            	if(tableName.endsWith(table.getName().toUpperCase())){
//            		tableId = table.getId();
//            		break;
//            	}
//            }
//            // a list of all the groups on this table
//            List<Group> groupsForThisTable = getWebApplicationContext().getBean(ModelService.class).getGroupsForTable(tableId);
//            
//            
//            // for every group entry for these groups, put a request object containing GROUP + ordinal as the key
//            // and group entry id as the value as these will be substituted in the page
//            for(Group group: groupsForThisTable){
//            	System.out.println("found group " + group.getName());
//            	List<GroupEntry> entries = getWebApplicationContext().getBean(GroupService.class).getGroupEntriesForCompany(companyId, group.getId());
//            	for(GroupEntry ge: entries){
//            		System.out.println("found group entry " + ge.getDescription() + " id: " + ge.getId() + " ord: "+ ge.getOrdinal());
//            		String entryName = ge.getDescription();
//            		if( -1 < entryName.indexOf("_")){
//            			entryName = entryName.substring(entryName.indexOf("_")+ 1);
//            		}
//            		mav.addObject(entryName , ge.getId());
//            	}
//            }
//            
//            mav.addObject("companyTables", tablesForCompany);
//            mav.addObject("company", getWebApplicationContext().getBean(CompanyService.class).getCompany(companyId));
//            mav.addObject("modelId", getWebApplicationContext().getBean(ModelService.class).getModel(modelCode).getId());
        }
        return mav;
    }

}
