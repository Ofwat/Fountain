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

import java.util.List;

import uk.gov.ofwat.fountain.dataformat.v1.AuditDocument.Audit;

public class AuditFactory {

	public Audit createAudit(List<Audit> listOfAudits, Water2000record w2Record) {
		Audit audit = Audit.Factory.newInstance();
		listOfAudits.add(audit);
		audit.setTimestamp(w2Record.getDateStamp().getTimeInMillis());
		audit.setUser(w2Record.getUser());
		audit.setComment(w2Record.getComments());
		audit.addNewData();
		return audit;
	}

	public Audit createInitialAudit() {
		Audit audit = Audit.Factory.newInstance();
		audit.setTimestamp(0);
		audit.setUser("System");
		audit.setComment("Initial Audit");
		audit.addNewData();
		return audit;
	}

}
