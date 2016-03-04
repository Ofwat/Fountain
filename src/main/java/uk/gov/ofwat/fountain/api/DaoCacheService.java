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
import java.util.List;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.domain.CacheLevel;

public interface DaoCacheService {

	public abstract void addCache(DaoCache cache);


	/**
	 * enable caching (has no effect if caches are enabled).
	 */
	public void enableCache();	
	
	/**
	 * purge caches
	 */
	public void clearCaches();
	
	/** 
	 * disable and then purge caches
	 */
	public void disableCache();

	/** 
	 * Write current cache levels to the log
	 */
	public List<CacheLevel> reportCacheLevels();


	public abstract void writeToDisk() throws IOException;


	public abstract void readFromDisk();

}