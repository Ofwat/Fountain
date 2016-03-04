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

package uk.gov.ofwat.fountain.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportResponseDto {
	
	private boolean success;
	private List<String> notices = new ArrayList<String>();
	private String logFileId;
	private String uploadFileName;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<String> getNotices() {
		return notices;
	}
	public void addNotice(String notice) {
		this.notices.add(notice);
	}
	public void addNotices(List<String> notices) {
		this.notices.addAll(notices);
	}
	public void setNotices(List<String> notices) {
		this.notices = notices;
	}
	public void setLogFileId(String logFileId) {
		this.logFileId = logFileId;
	}
	public String getLogFileId() {
		return logFileId;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
}
