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
package uk.gov.ofwat.fountain.tools.Water2000Reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument;
import uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit;
import uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument.Audits;
import uk.gov.ofwat.fountain.dataformat.v1.ValueDocument.Value;

public class AuditsFactory {

	AuditFactory auditFactory;
	List<Audit> listOfAudits;
	Map<String, Water2000record> w2AllRecsMap;
	
	public AuditsFactory() {
		this.auditFactory = new AuditFactory();
		this.listOfAudits = new ArrayList<Audit>();
		this.w2AllRecsMap = new HashMap<String, Water2000record>();
	}

	public AuditsDocument createFountainAudits(List<Water2000record> records) {
		
		Audit audit = null;

		for (Water2000record w2Record: records) {
			audit = getAuditForRecord(w2Record);
			String key = String.format("%014d", w2Record.getDateStamp().getTimeInMillis()) + "-" + w2Record.getUser() + "-" + w2Record.getComments() + "-" + w2Record.getItem() + "-" + w2Record.getYear() + "-" + w2Record.getActionType();
			if (w2AllRecsMap.containsKey(key)) {
				System.err.println("Duplicate records found.");
				System.err.println("Record      : " + w2AllRecsMap.get(key).toString());
				System.err.println("Replaced by : " + w2Record.toString());
			}
			w2AllRecsMap.put(key, w2Record);
			addRecordToAudit(audit, w2Record);
		}
		
		sortListOfAudits();
		listOfAudits.add(createInitialAudit());
		
		AuditsDocument auditsDoc = AuditsDocument.Factory.newInstance();
		Audits audits = auditsDoc.addNewAudits();
		sortListOfAudits();
		audits.setAuditArray(listOfAudits.toArray(new Audit[0]));
		return auditsDoc;
	}

	private Audit getAuditForRecord(Water2000record w2Rec) {
		for (Audit audit: listOfAudits) {
			GregorianCalendar auditCalendar = new GregorianCalendar();
			auditCalendar.setTime(new Date(audit.getTimestamp()));
			int auditYear = auditCalendar.get(GregorianCalendar.YEAR); 
			int auditMonth = auditCalendar.get(GregorianCalendar.MONTH); 
			int auditDay = auditCalendar.get(GregorianCalendar.DAY_OF_MONTH); 
			
			if (w2Rec.getUser().equals(audit.getUser()) &&
				w2Rec.getComments().equals(audit.getComment()) &&
				w2Rec.getDateStamp().get(GregorianCalendar.YEAR) == auditYear &&
				w2Rec.getDateStamp().get(GregorianCalendar.MONTH) == auditMonth &&
				w2Rec.getDateStamp().get(GregorianCalendar.DAY_OF_MONTH) == auditDay) {
				return audit;
			}
		}

		return auditFactory.createAudit(listOfAudits, w2Rec);
	}

	public Audit createInitialAudit() {
		Audit initialAudit = auditFactory.createInitialAudit();
		Map<String, Water2000record> initalRecsMap = new HashMap<String, Water2000record>();
		for (Audit audit: listOfAudits) {
			for (Value value: audit.getData().getValueArray()) {
				processValueIntoAudit(initialAudit, initalRecsMap, audit, value, "Value");
				processValueIntoAudit(initialAudit, initalRecsMap, audit, value, "CGrade");
			}
		}
		return initialAudit;
	}

	private void processValueIntoAudit(Audit initialAudit, Map<String, Water2000record> initalRecsMap, Audit audit, Value value, String dataType) {
		String recordKey = String.format("%014d", value.getTimestamp()) + "-" + audit.getUser() + "-" + audit.getComment() + "-" + value.getItem() + "-" + value.getYear() + "-" + dataType;
		String itemYearTypeKey =  value.getItem() + "-" + value.getYear() + "-" + dataType;
		if (!initalRecsMap.containsKey(itemYearTypeKey)) {
			// not in initial audit so add it. 
			Water2000record rec = w2AllRecsMap.get(recordKey);
			if (null != rec
				&& rec.getActionType().equalsIgnoreCase(dataType)) {
				if (rec.getFromData().length() != 0) {
					addRecordToInitialAudit(initialAudit, rec);
				}
				initalRecsMap.put(itemYearTypeKey, rec);
			}
		}
	}

	private void sortListOfAudits() {
//		Map<String, Audit> auditMap = new TreeMap<String, Audit>();
//		for (Audit audit: listOfAudits) {
//			sortValues(audit);
//			auditMap.put(String.format("%014d", audit.getTimestamp()) + "-" + audit.getUser() + "-" + audit.getComment(), audit);
//		}
//		listOfAudits =  new ArrayList<Audit>(auditMap.values());
		Map<String, Audit> auditMap = new TreeMap<String, Audit>();
		for (Audit audit: listOfAudits) {
			sortValues(audit);
			auditMap.put(String.format("%014d", audit.getTimestamp()) + "-" + audit.getUser() + "-" + audit.getComment(), audit);
		}
		listOfAudits =  new ArrayList<Audit>(auditMap.values());
	}

	private void sortValues(Audit audit) {
		Map<String, Value> valueMap = new TreeMap<String, Value>();
		for (Value value: audit.getData().getValueArray()) {
			if (!value.getStringValue().isEmpty()) {
				String key = String.format("%014d", value.getTimestamp()) + "-" + audit.getUser() + "-" + audit.getComment() + "-" + value.getItem() + "-" + value.getYear() + "-" + "Value";
				valueMap.put(key, value);
			}
			else if (value.isSetCg()) {
				String key = String.format("%014d", value.getTimestamp()) + "-" + audit.getUser() + "-" + audit.getComment() + "-" + value.getItem() + "-" + value.getYear() + "-" + "CGrade";
				valueMap.put(key, value);
			}
		}
		audit.getData().setValueArray(valueMap.values().toArray(new Value[0]));
	}

	private Audit addRecordToAudit(Audit audit, Water2000record w2Record) {
		
		Value value = getValue(audit, w2Record);
		
		if (w2Record.getAction().equals("CGrade")) {
			value.setCg(w2Record.getToData());
		}
		else {
			value.setStringValue(w2Record.getToData());
		}

		return audit;
	}

	private Audit addRecordToInitialAudit(Audit audit, Water2000record w2Record) {
		
		Value value = getValue(audit, w2Record);
		
		if (w2Record.getAction().equals("CGrade")) {
			if (!value.isSetCg()) {
				// first cg for this item/year
				value.setCg(w2Record.getFromData());
			}
		}
		else {
			if (value.getStringValue().isEmpty()) {
				// first value for this item/year
				value.setStringValue(w2Record.getFromData());
			}
		}
		return audit;
	}
	
	private Value getValue(Audit audit, Water2000record w2Record) {
		for (Value val: audit.getData().getValueArray()) {
			if (val.getYear().equals(w2Record.getYear()) &&
				val.getItem().equals(w2Record.getItem())
				) {
				if (!val.isSetCg() && w2Record.getAction().equals("CGrade") 
					|| 
					val.getStringValue().isEmpty() && w2Record.getAction().equals("Value")) { 
					// already have an existing value for this item/year
					return val;
				}
			}
		}
		
		// new value required for this item/year 
		Value value = audit.getData().addNewValue();
		value.setItem(w2Record.getItem());
		value.setYear(w2Record.getYear());
		value.setTimestamp(w2Record.getDateStamp().getTimeInMillis());
		
		return value;
	}

}
