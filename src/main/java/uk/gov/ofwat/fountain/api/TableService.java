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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.User;

public interface TableService {

	public DataTable getTableForCompany(int tableId, int companyId, User user, RoleChecker roleChecker);

	public TableUploadMetaData  uploadTable(TableUploadMetaData metaData, InputStream inputStream, User user, RoleChecker roleChecker) throws IOException;
	
	public List<Table> addTableDescriptions(List<Table> tables);
}
