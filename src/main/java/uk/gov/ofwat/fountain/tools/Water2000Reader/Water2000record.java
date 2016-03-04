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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Water2000record {

	private String dataset;
	private String company;
	private String item;
	private String year;       
	private String action;
	private String fromData;      
	private String toData;        
	private String user;     
	private String date;      
	private String time;
	private String comments;
	private GregorianCalendar datestamp;
	
	public Water2000record(String line, String dataset, String company, String item) {
 		setDataset(dataset);
		setCompany(company);
		setItem(item);
		setYear(line.substring(15, 25));
		setAction(line.substring(26, 44));
		setFromData(line.substring(45, 59));
		setToData(line.substring(60, 74));
		setUser(line.substring(75, 83));
		setDate(line.substring(84, 93));
		setTime(line.substring(94, 102));
		setDateStamp();
		if (line.length() > 103) {
			setComments(line.substring(103));
		}
		else {
			setComments("");
		}
	}

	@Override
	public String toString() {
		return "Water2000record [action=" + action + ", comments=" + comments
				+ ", company=" + company + ", dataset=" + dataset + ", date="
				+ date + ", fromData=" + fromData + ", item=" + item
				+ ", time=" + time + ", toData=" + toData + ", user=" + user
				+ ", year=" + year + "]";
	}

	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset.trim();
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item.trim();
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year.trim();
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action.trim();
	}
	public String getFromData() {
		return fromData;
	}
	public void setFromData(String fromData) {
		if (fromData.trim().equalsIgnoreCase("NA")) {
			this.fromData = "";
		}
		else {
			this.fromData = fromData.trim();
		}
	}
	public String getToData() {
		return toData;
	}
	public void setToData(String toData) {
		this.toData = toData.trim();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user.trim();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date.trim();
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time.trim();
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments.trim();
	}
	public void setDateStamp() {
		Integer dayOfMonth = Integer.parseInt(getDate().substring(0, 2));
		Integer month = convertMonth(getDate().substring(2, 5));
		Integer year = convertYear(getDate().substring(5, 7));
		
		Integer hourOfDay = Integer.parseInt(getTime().substring(0, 2));
		Integer minute = Integer.parseInt(getTime().substring(3, 5));
		Integer second = Integer.parseInt(getTime().substring(6, 8));
		
		datestamp = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
	}

	public GregorianCalendar getDateStamp() {
		return datestamp;
	}

	private Integer convertYear(String yr) {
		Integer year = Integer.parseInt(yr);
		if (year > 90) {
			year = year + 1900;
		}
		else {
			year = year + 2000;
		}
		return year;
	}

	private Integer convertMonth(String mth) {
		Integer month = null;
		if (mth.equals("JAN")) {
			month = Calendar.JANUARY;
		}
		else if (mth.equals("FEB")) {
			month = Calendar.FEBRUARY;
		} 
		else if (mth.equals("MAR")) {
			month = Calendar.MARCH;
		} 
		else if (mth.equals("APR")) {
			month = Calendar.APRIL;
		} 
		else if (mth.equals("MAY")) {
			month = Calendar.MAY;
		} 
		else if (mth.equals("JUN")) {
			month = Calendar.JUNE;
		} 
		else if (mth.equals("JUL")) {
			month = Calendar.JULY;
		} 
		else if (mth.equals("AUG")) {
			month = Calendar.AUGUST;
		} 
		else if (mth.equals("SEP")) {
			month = Calendar.SEPTEMBER;
		} 
		else if (mth.equals("OCT")) {
			month = Calendar.OCTOBER;
		} 
		else if (mth.equals("NOV")) {
			month = Calendar.NOVEMBER;
		} 
		else if (mth.equals("DEC")) {
			month = Calendar.DECEMBER;
		} 
		return month;
	}

	public String getActionType() {
		if (getAction().equalsIgnoreCase("CGrade")) {
			return "CGrade";
		}
		else {
			return "Value";
		}
	}
}
