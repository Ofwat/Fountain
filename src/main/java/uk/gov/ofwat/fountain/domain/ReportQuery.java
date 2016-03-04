package uk.gov.ofwat.fountain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.api.RunTagService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;


public class ReportQuery {

	private int id;
	private int suppliedCompanyId;
	private int suppliedRunId;
	private RunModelTag suppliedTag;
	private ReportDefinition reportDef;
	private ReportService reportService;
	private RunService runService;
	private GroupService groupService;
	private ReferenceService referenceService;
	private ItemService itemService;
	private TagMap tagMap;
	private List<Integer> itemIds;
	private List<Integer> intervalIds;
	private List<Integer> runIds;
	private List<Integer> companyIds;
	private List<RunTag> runTags;
	private Map<Integer, List<Integer>> groupEntryIds;
	private List<String> tagNames;
	private RunTagService runTagService;
	
	
	public ReportQuery(ReportService reportService, int id, int suppliedCompanyId, int suppliedRunId, RunModelTag suppliedTag) {
		super();
		this.reportService = reportService;
		this.runService= reportService.getRunService();
		this.runTagService= reportService.getRunTagService();
		this.groupService = reportService.getGroupService();
		this.referenceService = reportService.getReferenceService();
		this.id = id;
		this.suppliedCompanyId = suppliedCompanyId;
		this.suppliedRunId = suppliedRunId;
		this.suppliedTag = suppliedTag;
		this.reportDef = reportService.getReportDefinition(id);
		this.itemService = reportService.getItemService();
		this.populateItemIds();
		this.populateIntervalIds();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSuppliedCompanyId() {
		return suppliedCompanyId;
	}


	public void setSuppliedCompanyId(int suppliedCompanyId) {
		this.suppliedCompanyId = suppliedCompanyId;
	}


	public int getSuppliedRunId() {
		return suppliedRunId;
	}


	public void setSuppliedRunId(int suppliedRunId) {
		this.suppliedRunId = suppliedRunId;
	}


	public RunModelTag getSuppliedTag() {
		return suppliedTag;
	}


	public void setSuppliedTag(RunModelTag suppliedTag) {
		this.suppliedTag = suppliedTag;
	}

	public ReportDefinition getReportDef() {
		return reportDef;
	}


	public List<RunTag> getRunTags() {
		if (null == runTags) {
			populateRunTags();
			populateRunIds();
		}
		return runTags;
	}
	
	public List<Integer> getRunIds() {
		if (null == runIds) {
			populateRunTags();
			populateRunIds();
		}
		return runIds;
	}
	
	private List<Integer> populateRunIds() {
		Set<Integer> runIdsSet = new HashSet<Integer>();
		for (RunTag runTag : runTags) {
			if (0 == runTag.getRun().getId()) {
				runIdsSet.add(suppliedRunId);
			}
			else {
				runIdsSet.add(runTag.getRun().getId());
			}
		}
		runIds = new ArrayList<Integer>(runIdsSet);
		return runIds;
	}

	private void populateRunTags() {
		runTags = new ArrayList<RunTag>();
		
		if (0 == suppliedRunId) {
			// tags are in the report
			if (0 == suppliedCompanyId) {
				// companies are in the report
				for (RunTag runTag: reportDef.getRunTags()) {
					if (RunModelTag.LATEST.equals(runTag.getRunModelTag())) {
						// latest
						RunTag latestRunTag = runTagService.getLatestRunTag(runTag.getRun().getId());
						runTags.add(latestRunTag);
					}
					else {
						// real tag
						runTags.add(runTag);
					}
				}
			}
			else {
				// company is supplied
				for(RunTag runTag : reportDef.getRunTags()){
					if (RunModelTag.LATEST.equals(runTag.getRunModelTag())) {
						// latest
						RunTag latestRunTag = runTagService.getLatestRunTag(runTag.getRun().getId());
						runTags.add(latestRunTag);
					}
					else {
						// real tag
						runTags.add(runTag);
					}
				}
			}
		}
		else{
			// tag is supplied
			if (RunModelTag.LATEST.equals(suppliedTag)) {
				// latest
				RunTag runTag = runTagService.getLatestRunTag(suppliedRunId);
				runTags.add(runTag);
			}
			else {
				// real tag
				RunTag runTag = runTagService.getRunTag(suppliedRunId, suppliedTag.getId());
				runTags.add(runTag);
			}
		}
	}

	public List<Integer> getCompanyIds() {
		if (null == companyIds) {
			companyIds = populateCompanyIds();
		}
		return companyIds;
	}
	
	private List<Integer> populateCompanyIds() {
		companyIds = new ArrayList<Integer>();
		for (Integer companyId : reportDef.getCompanyIds()) {
			if (0 == companyId) {
				companyIds.add(suppliedCompanyId);
			} else {
				companyIds.add(companyId);
			}
		}
		return companyIds;
	}

	public List<Integer> getItemIds() {
		if (null == itemIds) {
			itemIds = populateItemIds();
		}
		return itemIds;
	}
	
	private List<Integer> populateItemIds() {
		itemIds = new ArrayList<Integer>();
		for (ModelItem modelItem : reportDef.getModelItems()) {
			itemIds.add(modelItem.getItemId());
		}
		return itemIds;
	}


	public List<Integer> getIntervalIds() {
		if (null == intervalIds) {
			intervalIds = populateIntervalIds();
		}
		return intervalIds;
	}
	
	private List<Integer> populateIntervalIds() {
		intervalIds = new ArrayList<Integer>();
		intervalIds.addAll(reportDef.getIntervalIds());
		return intervalIds;
	}

	public List<Integer> getGroupEntries(int companyId) {
		if (null == groupEntryIds) {
			groupEntryIds = new HashMap<Integer, List<Integer>>();
		}
		if (!groupEntryIds.containsKey(companyId)) {
			groupEntryIds.put(companyId, populateGroupEntries(companyId));
		}
		return groupEntryIds.get(companyId);
		
	}
	
	private List<Integer> populateGroupEntries(int companyId) {
		Group group = reportDef.getGroup();
		if (null == group) {
			group = groupService.getGroupById(1);
		}
		List<Integer> groupEntryIds = new ArrayList<Integer>();
		List<GroupEntry> groupEntries = groupService.getGroupEntriesForCompany(companyId, group.getId());
		for (GroupEntry groupEntry : groupEntries) {
			groupEntryIds.add(groupEntry.getId());
		}
		return groupEntryIds;
	}

	public TagMap getTagMap() {
		if (null == tagMap) {
			tagMap = populateTagMap();
		}
		return tagMap;
	}
		
	private TagMap populateTagMap() {
		tagMap = new TagMap();
		
		if (0 == suppliedCompanyId &&
			null != suppliedTag) {
			// Tag supplied. Company not supplied.
			// So create lookup varying on company 
			Map<Integer, Integer> companyTagMap = new HashMap<Integer, Integer>();
			if (RunModelTag.LATEST.equals(suppliedTag)) {
				for (int companyId : reportDef.getCompanyIds()) {
					companyTagMap.put(companyId, RunModelCompanyTag.LATEST.getId());
				}
			}
			else {
				// Proper tag.
				for (int companyId : reportDef.getCompanyIds()) {
					RunModelCompanyTag companySpecificTag = runTagService.findRunModelCompanyTagByRunModelCompany(suppliedTag.getRunId(), suppliedTag.getModelId(), companyId);
					if (null != companySpecificTag) {
						companyTagMap.put(companyId, companySpecificTag.getId());
					}
				}
			}
			tagMap.setTagMapType("companyTagMap");
			tagMap.setTags(companyTagMap);
		}
		
		if ((null == suppliedTag || RunModelTag.LATEST.equals(suppliedTag)) &&
			0 != suppliedCompanyId) {
			// Company supplied. Tag not supplied.
			// So create lookup varying on tag 
			Map<Integer, Integer> tagTagMap = new HashMap<Integer, Integer>();
			for (RunTag runTag : reportDef.getRunTags()) {
				if (!RunModelTag.LATEST.equals(runTag.getRunModelTag()) &&
					!RunModelTag.PLACE_HOLDER.equals(runTag.getRunModelTag())) {
					RunModelCompanyTag runModelCompanyTag = runTagService.findRunModelCompanyTagByRunModelCompany(runTag.getRun().getId(), runTag.getRunModelTag().getModelId(), suppliedCompanyId);
					if (null != runModelCompanyTag) {
						tagTagMap.put(runTag.getRunModelTag().getId(), runModelCompanyTag.getId());
					}
				}
			}
			tagTagMap.put(0, 0);
			tagMap.setTagMapType("tagTagMap");
			tagMap.setTags(tagTagMap);
		}
		return tagMap;
	}

	public Integer getGroupId() {
		Integer groupId = null;
		if (null != getReportDef().getGroup()) {
			groupId = getReportDef().getGroup().getId();
		}
		return groupId;
	}

	public Company getSuppliedCompany() {
		Company suppliedCompany = null;
		if (getSuppliedCompanyId() != 0) {
			suppliedCompany = referenceService.getCompany(getSuppliedCompanyId());
		}
		return suppliedCompany;
	}

	public Run getSuppliedRun() {
		Run suppliedRun = null;
		if (getSuppliedRunId() != 0) {
			suppliedRun = runService.getRun(getSuppliedRunId());
		}
		return suppliedRun;
	}

	public void addEquationItems(List<ItemInEquation> itemsInEquation) {
		Set<Integer> itemIdsSet = new HashSet<Integer>(itemIds);
		for (ItemInEquation itemInEquation: itemsInEquation) {
			Item item = itemService.getItem(itemInEquation.getItem());
			if (null == item) {
				continue;
			}
			itemIdsSet.add(item.getId());
		}
		itemIds = new ArrayList<Integer>(itemIdsSet);

		Set<Integer> intervalIdsSet = new HashSet<Integer>(intervalIds);
		for (ItemInEquation itemInEquation: itemsInEquation) {
			intervalIdsSet.add(itemInEquation.getInterval().getId());
		}
		intervalIds = new ArrayList<Integer>(intervalIdsSet);
	}

}
