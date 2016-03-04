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
public class DataTagKeyFactory {
	
	private ModelService modelService;

	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public DataTagKey createDataTagKey(int runId, int companyId) {
		DataTagKey dataTagKey = new DataTagKey();
		dataTagKey.setRunId(""+runId);
		dataTagKey.setCompanyId(""+companyId);
		dataTagKey.setModelId(""+0);
		return dataTagKey;
	}

	public DataTagKey createDataTagKey(String displayName, int runId, int companyId) {
		DataTagKey dataTagKey = new DataTagKey();
		if (null == displayName ||
			displayName.equals("0")) {
			return dataTagKey;
		}
		dataTagKey.setRunId(""+runId);
		dataTagKey.setCompanyId(""+companyId);
		
		
		if (displayName.equalsIgnoreCase("Origin")) {
			Model model = modelService.getModel("ORIGIN");
			dataTagKey.setModelId(""+model.getId());
		}
		else {
			dataTagKey.setModelId(""+0);
		}
		return dataTagKey;
	}


	public DataTagKey createDataTagKey(String displayName, int runId, int companyId, int reportId) {
		DataTagKey dataTagKey = new DataTagKey();
		if (null == displayName ||
			displayName.equals("0")) {
			return dataTagKey;
		}
		dataTagKey.setRunId(""+runId);
		dataTagKey.setCompanyId(""+companyId);
		
		
		if (displayName.equalsIgnoreCase("Origin")) {
			Model model = modelService.getModel("ORIGIN");
			dataTagKey.setModelId(""+model.getId());
		}
		else if (displayName.equalsIgnoreCase("Completed")) {
			Model model = modelService.getModel("COMPLETED");
			dataTagKey.setModelId(""+model.getId());
		}
		else {
			int modelId = getMCRModelId(reportId);
			dataTagKey.setModelId(""+modelId);
		}
		return dataTagKey;
	}

	public DataTagKey createDataTagKey(String key) { 
		DataTagKey dataTagKey = new DataTagKey();
		StringTokenizer st = new StringTokenizer(key, "-");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			switch(token.charAt(0)) {
				case 'r': dataTagKey.setRunId(token.substring(1)); 
				break;
				case 'c': dataTagKey.setCompanyId(token.substring(1));
				break;
				case 'm': dataTagKey.setModelId(token.substring(1));
				break;
			}
		}
		return dataTagKey;
	}
	

	private int getMCRModelId(int id) {
		ModelCompanyReport mcr = modelService.getModelCompanyReport(id);
		int modelId = (null==mcr) ? 0 : mcr.getModelId();
		return modelId;
	}
}
