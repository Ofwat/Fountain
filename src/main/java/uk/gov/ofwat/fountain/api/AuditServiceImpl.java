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

package uk.gov.ofwat.fountain.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.gov.ofwat.fountain.dao.AuditDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class AuditServiceImpl implements AuditService{

	private AuditDao auditDao;
	private RunService runService;
	private RunTagService runTagService;
	
	public void setAuditDao(AuditDao auditDao){
		this.auditDao = auditDao;
	}
	
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
	
	public RunService getRunService(){
		return this.runService;
	}

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public Audit createAudit(String comment, User user, Company company) {
		Audit audit = new Audit();
		audit.setComment(comment);
		audit.setUser(user);
		audit.setDate(new Date());
		audit.setCompany(company);
		audit.setId(auditDao.create(audit));
		return audit;
	}
	
	public List<AuditedValue> getAuditedValues(Integer itemId, Integer intervalId, Integer companyId, Integer groupEntryId) {
		return auditDao.getAuditedValues(itemId, intervalId, companyId, groupEntryId);
	}

	public List<AuditedValue> getAuditedValues(Integer itemId, Integer intervalId, Integer companyId, Integer groupEntryId, int runId, int tagId) {
		List<AuditedValue> auditedValues = new ArrayList<AuditedValue>();
		
		Run currentRun = runService.getRun(runId);
		RunModelCompanyTag sourceRunModelCompanyTag = runTagService.findRunModelCompanyTagById(tagId);
		
		do {
			auditedValues.addAll(auditDao.getAuditedValues(itemId, intervalId, companyId, groupEntryId, currentRun.getId(), sourceRunModelCompanyTag.getId()));
			currentRun = runService.getRun(currentRun.getDataSourceId());
			RunModelTag sourceRunModelTag = runTagService.findRunModelTagById(currentRun.getBaseTagId());
			sourceRunModelCompanyTag = sourceRunModelTag.getRunModelCompanyTag(companyId);
		} while (currentRun.getId() != RunPlaceHolder.RUN_PLACE_HOLDER.getId());
		
		return auditedValues;
	}
	
	

}
