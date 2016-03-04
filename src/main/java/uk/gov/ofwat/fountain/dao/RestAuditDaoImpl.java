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

package uk.gov.ofwat.fountain.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import uk.gov.ofwat.fountain.api.MongoService;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.RestAudit;
import uk.gov.ofwat.fountain.domain.User;

public class RestAuditDaoImpl implements RestAuditDao {
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.dao.AuditDao#create(uk.gov.ofwat.fountain.domain.Audit)
	 */
	
	private MongoService mongoService;
	
	
	public MongoService getMongoService() {
		return mongoService;
	}

	public void setMongoService(MongoService mongoService) {
		this.mongoService = mongoService;
	}


	@Override
	public Key<RestAudit> createUpdate(RestAudit restAudit) {
		@SuppressWarnings("unchecked")
		Key<RestAudit> key = mongoService.<RestAudit>createUpdate(restAudit);
		return key;
	}
}
