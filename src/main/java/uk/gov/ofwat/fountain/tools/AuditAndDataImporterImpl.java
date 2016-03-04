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
package uk.gov.ofwat.fountain.tools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.dao.AuditDao;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.CompanyDao;
import uk.gov.ofwat.fountain.dao.ConfidenceGradeDao;
import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.User;

public class AuditAndDataImporterImpl implements AuditAndDataImporter {

	protected AuditDao auditDao;
	protected DataDao dataDao;
	protected ItemDao itemDao;
	protected IntervalDao intervalDao;
	protected CompanyDao companyDao;
	protected ConfidenceGradeDao confidenceGradeDao;
	protected GroupDao groupDao;
	protected UserService userService;
	protected BranchDao branchDao;
	protected String bulkUploadDir;
	protected ReferenceService referenceService;
	
	private static final Log log = LogFactory.getLog(AuditAndDataImporterImpl.class);
//	private static final String ORDINAL_REGEX = "[]"

	public ReferenceService getReferenceService() {
		return referenceService;
	}
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	public BranchDao getBranchDao() {
		return branchDao;
	}
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	public ItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public IntervalDao getIntervalDao() {
		return intervalDao;
	}
	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	public ConfidenceGradeDao getConfidenceGradeDao() {
		return confidenceGradeDao;
	}
	public void setConfidenceGradeDao(ConfidenceGradeDao confidenceGradeDao) {
		this.confidenceGradeDao = confidenceGradeDao;
	}
	public AuditDao getAuditDao() {
		return auditDao;
	}
	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}
	public DataDao getDataDao() {
		return dataDao;
	}
	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	public GroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setBulkUploadDir(String bulkUploadDir) {
		this.bulkUploadDir = bulkUploadDir;
	}

	@Transactional
	public void importFile(File xmlFile) {
		try {
			XmlObject xmlObjExpected = XmlObject.Factory.parse(xmlFile);

			if (xmlObjExpected instanceof AuditsDocument){
				AuditsDocument ad = (AuditsDocument)xmlObjExpected;
				importData(ad);

			} else {
				throw new RuntimeException("File is not an AuditsDocument: " + xmlFile);
			}

		} catch (IOException ex) {
			throw new RuntimeException("IOException reading " + xmlFile, ex);
		} catch (XmlException ex){
			throw new RuntimeException("Bad XML in " + xmlFile, ex);
		} catch (InvalidIntervalException ex){
			throw new RuntimeException("Bad Interval in " + xmlFile, ex);
		} catch (InvalidCompanyException ex) {
			throw new RuntimeException("Bad company in " + xmlFile, ex);
		}		
	}

	@Transactional
	public void importData(AuditsDocument ad) throws InvalidCompanyException, InvalidIntervalException {
		log.info("Importing data");
		Set<String> missedItems = new HashSet<String>();
		Set<String> addedItems = new HashSet<String>();
		MyLookups lookups = new MyLookups();
		
		int total = 0;
		int aTot = ad.getAudits().getAuditArray().length;
		int aCnt = 1;
		for (uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit audit : ad.getAudits().getAuditArray()) {
			log.info("Importing audit " + aCnt + " of " + aTot + " {" + digest(audit.getComment()) + "}");
			aCnt++;
			
			Audit auditObject = new Audit();
			auditObject.setComment(audit.getComment());
			if (audit.getTimestamp() < 10000000000L) {
				throw new RuntimeException("Timestamp must be in milliseconds so it should be at least 11 digits long (not counting leading zero's). " +
						"The timestamp supplied will give a date before Sun, 26 Apr 1970 17:46:40 UTC. " +
						"Use a site like this http://www.onlineconversion.com/unix_time.htm to work out the timestamp AND THEN ADD three trailing zero's.");
			}
			auditObject.setDate(new Date(audit.getTimestamp()));
			User user = null;
			try {
				user = userService.getNamedUser(audit.getUser());
			}
			catch (Exception e){
				// user does not exist so create;
				log.info("Creating user " + audit.getUser());
				user = userService.createUser(audit.getUser());
			}
			
			auditObject.setUser(user);
			uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value firstValue = audit.getData().getValueArray()[0];
			Company company = referenceService.getCompany(firstValue.getCompany());
			auditObject.setCompany(company);			
			 
			// see if there is an existing audit identical to this one?
			List<Audit> existing = auditDao.findByUserCompanyAndTimestamp(user, company, audit.getTimestamp());
			for(Audit tAudit: existing){
				if(tAudit.getComment().equals(audit.getComment())){
					// this is an identical audit to one in the system - delete the old one
					auditDao.delete(tAudit.getId());
				}
			}

			// Some logging information 
			int vTot = audit.getData().getValueArray().length;
			int vCnt = 0;
			int v = 0;
			total = total + vTot;
			
			// now create the audit (if we did this earlier it would have been deleted in the previous line)
			auditObject.setId(auditDao.create(auditObject));
			List<Data> updates = new Vector<Data>();
			for (uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value value : audit.getData().getValueArray()){
				// Just info to the logs
				vCnt++;
				v++;
				if (v>=1000 || vCnt==1) {
					log.info(" > Preparing value " + vCnt + " of " + vTot + " {" + value.getItem() + "}");
					if (vCnt>1) v = 0;
				}
				
				// Error if mixed company
				if (!value.getCompany().equalsIgnoreCase(company.getCode())) {
					throw new InvalidCompanyException("The company for the current value does not match the company of the first value in the audit. All values in an audit must share the same company.");
				}

				// Get the item - report anything that is not in dictionary
				Item item = lookups.getItem(value.getItem());
				if(null == item){
					missedItems.add(value.getItem());
					continue;
				}
				addedItems.add(value.getItem());
				
				// Get the interval
				Interval interval = lookups.getInterval(value.getYear());
				if (interval==null) throw new InvalidIntervalException("Data contains invalid interval: " + value.getYear());
				
				ConfidenceGrade cg = lookups.getConfidenceGrade(value.getCg());

				
				// Get the branch if specified
				Branch bt = null;
				if (audit.isSetBranchTag()) {
					bt = lookups.getBranch(audit.getBranchTag());
				}

				// Get the group entry
				GroupEntry ge = lookups.getGroupEntry(company, value);
				
				// Add the update to the list
				Data data = new Data();
				data.setValue(value.getStringValue());
				data.setCompany(company);
				data.setConfidenceGrade(cg);
				data.setInterval(interval);
				data.setItem(item);
				data.setGroupEntry(ge);
				data.setBranch(bt);
				updates.add(data);
			}
			
			// Perform a bulk upload of all the data
			dataDao.bulkUpdate(updates, auditObject);
		}
		log.info("Imported " + total + " value(s)");
		
		// Report any missing
		if (missedItems!=null && missedItems.size()>0) {
			log.info("** " + addedItems.size() + " item(s) added");
			log.info("** " + missedItems.size() + " item(s) missed:");
			StringBuffer b = new StringBuffer();
			for(String itemCode: missedItems){
				if (b.length()>0) b.append(",");
				b.append(itemCode);
				if (b.length()>200) {
					log.info("** " + b.toString());
					b = new StringBuffer();
				}
			}
			if (b.length()>0) log.info("** " + b.toString());
		}
	}
	
	public List<String> bulkImport() {
		log.info("Started bulk import");
		
		File uploadDir = new File(bulkUploadDir);
		File finishedDir = new File(bulkUploadDir, "done");
		
		List<String> reports = new ArrayList<String>();
		if (!createDirOrFail(reports, uploadDir)) return reports;
		if (!createDirOrFail(reports, finishedDir)) return reports;
		
		reports.add("Reading all files in " + uploadDir);
		String[] fileNames = uploadDir.list();
		log.info("Found " + fileNames.length + " file(s) in " + uploadDir);
		int processed = 0;
		for(String fileName: fileNames){
			if(fileName.endsWith(".xml")){
				File dataFile = new File(uploadDir.getAbsolutePath(), fileName);
				String destString = finishedDir.getAbsolutePath() + "\\" + fileName;

				reports.add("about to process " + fileName);
				try{
					log.info("About to processing " + fileName);
					XmlObject xmlObjExpected = XmlObject.Factory.parse(dataFile);
					if(xmlObjExpected instanceof AuditsDocument){
						AuditsDocument ad = (AuditsDocument)xmlObjExpected;
						importData(ad);
						reports.add("finished processing " + fileName);
						destString += "." + getTimeStamp() + ".done";
						log.info("Finished processing " + fileName);
						processed++;
					}
					else{
						log.info("Invalid file: " + fileName );
						reports.add("file " + fileName + " was not a valid audit data file");
						destString += ".err";
					}

				}
				catch(Exception e){
					log.error("Couldn't import " + fileName, e);
					reports.add("error processing " + dataFile.getName() + ": " + e.getMessage());
					destString += ".err";
				}
				File destFile = new File(destString);
				if (!dataFile.renameTo(destFile)) {
					reports.add("Couldn't rename file to " + destString);
				}
				log.info("Moved finished file to " + destFile);
			}
		}
		
		reports.add ("Successfully processed " + processed + " file(s)");
		return reports;
	}
	
	private boolean createDirOrFail(List<String> reports, File file) {
		try {
			if (!file.exists()) {
				// Try to create it
				file.mkdirs();
			}
		}
		catch (Exception ex) {
			log.error("Could't create folder " + file, ex);
			reports.add("Couldn't create " + file + ": " + ex.getMessage());
			return false;
		}
		return true;
	}

	private String digest(String text) {
		if (text==null) return "";
		if (text.length()==0) return "";
		String t = text.trim();
		if (t.length()<50) return t;
		return t.substring(0,47) + "...";
	}
	
	

	/**
	 * Short term cache for the import operation only
	 */
	private class MyLookups {
		private Map<String, Interval> intervalMap = new HashMap<String, Interval>();
		private Map<String, Item> itemMap = new HashMap<String, Item>();
		private Map<String, ConfidenceGrade> cgMap = new HashMap<String, ConfidenceGrade>();
		private Map<String, Branch> branchMap = new HashMap<String, Branch>();
		private Map<String, GroupEntry> ungroupedEntryMap = new HashMap<String, GroupEntry>();
		private Map<String, GroupEntry> groupEntryMap = new HashMap<String, GroupEntry>();
		private Map<String, Group> groupMap = new HashMap<String, Group>();

		/**
		 * Return (and cache) the interval
		 */
		public Interval getInterval(String code) {
			String k = code.toUpperCase();
			if (intervalMap.containsKey(k)) {
				return intervalMap.get(k);
			}
			else {
				Interval interval = intervalDao.findByName(k);
				intervalMap.put(k, interval);
				return interval;
			}
		}

		/**
		 * Return (and cache) the item
		 */
		public Item getItem(String code) {
			String k = code.toUpperCase();
			if (itemMap.containsKey(k)) {
				return itemMap.get(k);
			}
			else {
				Item item = itemDao.findByCode(k);
				itemMap.put(k, item);
				return item;
			}
		}

		/**
		 * Return (and cache) the confidence grade
		 */
		public ConfidenceGrade getConfidenceGrade(String code) {
			if (!StringUtils.hasText(code)) return null;
			String k = code.toUpperCase();
			if (cgMap.containsKey(k)) {
				return cgMap.get(k);
			}
			else {
				ConfidenceGrade cg = confidenceGradeDao.findByCode(k);
				cgMap.put(k, cg);
				return cg;
			}
		}

		public Branch getBranch(String code) {
			String k = code.toUpperCase();
			if (branchMap.containsKey(k)) {
				return branchMap.get(k);
			}
			else {
				Branch bt = branchDao.findByName(k);
				if (null == bt) {
					bt = branchDao.create(k);
				}
				branchMap.put(k, bt);
				return bt;
			}
		}
		
		/**
		 * Return (creating if necessary) the group entry for a value
		 */
		public GroupEntry getGroupEntry(Company company, uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value value) {
			if (value.getGrouptype()==null) {
				// No group so get the ungrouped entry
				String k = company.getCode().toUpperCase();
				if (ungroupedEntryMap.containsKey(k)) {
					return ungroupedEntryMap.get(k);
				}
				else {
					GroupEntry ge = groupDao.findUngroupedEntry(company);
					ungroupedEntryMap.put(k, ge);
					return ge;
				}
			}

			// Must be grouped - so read the group
			Group group = null;
			String k = value.getGrouptype().toUpperCase();
			if (groupMap.containsKey(k)) {
				group = groupMap.get(k);
			}
			else {
				try {
					group = groupDao.findByName(value.getGrouptype());
				}
				catch(EmptyResultDataAccessException ex){
					// create a new one
					log.info("Added group " + k);
					group = new Group();
					group.setName(k);
					group.setId(groupDao.create(group));
				}
				groupMap.put(k, group);
			}

			// look for the group entry
			String dataGroup = value.getGroup(); // the generic group entry shared by all companies
			k = company.getCode().toUpperCase() + "_" + dataGroup.toUpperCase();
			if (groupEntryMap.containsKey(k)) {
				return groupEntryMap.get(k);
			}
			else {
				GroupEntry ge = groupDao.findEntryCompanyAndName(company.getId(), k);
				if (ge==null) {
					// create a new group entry  
					ge = new GroupEntry();
					ge.setCompany(company);
					ge.setGroup(group);
					ge.setDescription(k);
					Pattern p = Pattern.compile("(\\d+)");
					Matcher m = p.matcher(dataGroup);
					int ord = 0;
					try{
						m.find();
						ord = Integer.parseInt(m.group(0));
					}
					catch(Exception e){
						log.debug("unable to read ordinal for group " + dataGroup);
					}

					ge.setOrdinal(ord);
					ge.setId(groupDao.createGroupEntry(ge));
				}
				groupEntryMap.put(dataGroup, ge);
				return ge;
			}
		}
	}

	private String getTimeStamp() {
		String fmt = "yyyyMMdd-kkmmss";
	    SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	    Calendar n = Calendar.getInstance();
	    
	    return sdf.format(n.getTimeInMillis());
	}

}
