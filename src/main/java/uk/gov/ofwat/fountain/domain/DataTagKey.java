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

package uk.gov.ofwat.fountain.domain;

import java.util.StringTokenizer;

import uk.gov.ofwat.fountain.api.ModelService;

/**
 * key structure for jsp pages.
 */
public class DataTagKey {
	
	private String runId;
	private String companyId;
	private String modelId;

	
	public DataTagKey() {
	}

	public String getModelId() {
		return modelId;
	}
	public Integer getModelIdInteger() {
		return Integer.parseInt(getModelId());
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = ""+modelId;
	}

	public String getRunId() {
		return runId;
	}
	public Integer getRunIdInteger() {
		return Integer.parseInt(getRunId());
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public Integer getCompanyIdInteger() {
		return Integer.parseInt(getCompanyId());
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setCompanyId(int companyId) {
		setCompanyId("" + companyId);
	}

	public String getKey(){
		if (null == runId || runId.isEmpty() || runId.equals("0") ||
			null == companyId || companyId.isEmpty() || companyId.equals("0") ||
			null == modelId || modelId.isEmpty() || modelId.equals("0")) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("r");
		builder.append(runId);
		builder.append("-c");
		builder.append(companyId);
		builder.append("-m");
		builder.append(modelId);
		return builder.toString();
	}

}
