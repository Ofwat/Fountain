package uk.gov.ofwat.fountain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.report.ReportCompanyGroups;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class ReportCollector {
	
	private User user; 
	private RoleChecker roleChecker; 
	private Basket basket; 
	private ReportDefinition reportDef; 
	private List<RunTag> runTags;
	private Map<String, DataDto> dataList;
	private TransientDataCache transientDataCache;
	private List<Lock> locks;
	private List<CodeList> codeLists;
	private Map<Integer, ReportCompanyGroups> mapOfReportCompanyGroups;
	private List<Pot> pots;


	public ReportCollector(User user, RoleChecker roleChecker, Basket basket, ReportDefinition reportDef, List<RunTag> runTags) {
		super();
		this.user = user; 
		this.roleChecker = roleChecker; 
		this.basket = basket; 
		this.reportDef = reportDef; 
		this.runTags = runTags;
		this.dataList = new HashMap<String, DataDto>();
		this.transientDataCache = new TransientDataCache();
		this.locks = new ArrayList<Lock>();
		this.codeLists = new ArrayList<CodeList>();
		this.mapOfReportCompanyGroups = new HashMap<Integer, ReportCompanyGroups>();
		this.pots = new ArrayList<Pot>();
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setRoleChecker(RoleChecker roleChecker) {
		this.roleChecker = roleChecker;
	}


	public void setBasket(Basket basket) {
		this.basket = basket;
	}


	public void setReportDef(ReportDefinition reportDef) {
		this.reportDef = reportDef;
	}


	public void setRunTags(List<RunTag> runTags) {
		this.runTags = runTags;
	}


	public void setDataList(Map<String, DataDto> dataList) {
		this.dataList = dataList;
	}


	public void setTransientDataCache(TransientDataCache transientDataCache) {
		this.transientDataCache = transientDataCache;
	}


	public void setLocks(List<Lock> locks) {
		this.locks = locks;
	}


	public void setCodeLists(List<CodeList> codeLists) {
		this.codeLists = codeLists;
	}


	public void setMapOfReportCompanyGroups(
			Map<Integer, ReportCompanyGroups> mapOfReportCompanyGroups) {
		this.mapOfReportCompanyGroups = mapOfReportCompanyGroups;
	}


	public void setPots(List<Pot> pots) {
		this.pots = pots;
	}


	public User getUser() {
		return user;
	}


	public RoleChecker getRoleChecker() {
		return roleChecker;
	}


	public Basket getBasket() {
		return basket;
	}


	public ReportDefinition getReportDef() {
		return reportDef;
	}


	public List<RunTag> getRunTags() {
		return runTags;
	}


	public Map<String, DataDto> getDataList() {
		return dataList;
	}


	public TransientDataCache getTransientDataCache() {
		return transientDataCache;
	}


	public List<Lock> getLocks() {
		return locks;
	}


	public List<CodeList> getCodeLists() {
		return codeLists;
	}


	public Map<Integer, ReportCompanyGroups> getMapOfReportCompanyGroups() {
		return mapOfReportCompanyGroups;
	}


	public List<Pot> getPots() {
		return pots;
	}

	
}
