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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.generic.GetAllAspect;
import uk.gov.ofwat.fountain.dao.DataDaoImpl;
import uk.gov.ofwat.fountain.domain.CacheLevel;

public class DaoCacheServiceImpl implements DaoCacheService {

	private static org.apache.log4j.Logger log = Logger.getLogger(DaoCacheServiceImpl.class);
	private List<DaoCache> caches = Collections.synchronizedList(new ArrayList<DaoCache>());
	private String cacheStoreDir;
	

	public String getCacheStoreDir() {
		return cacheStoreDir;
	}

	public void setCacheStoreDir(String cacheStoreDir) {
		this.cacheStoreDir = cacheStoreDir;
	}

	/**
	 * Persist the caches to allow loading when container starts. 
	 */
	public void persistCaches(){
		
	}
	
	/**
	 * Load the caches from disk 
	 */
	public void loadCaches(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.DaoCacheService#addCache(uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache)
	 */
	public void addCache(DaoCache cache){
		caches.add(cache);
		log.info("Cache added " + cache.getClass().getName());
	}
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.DaoCacheService#clearCaches()
	 */
	public void clearCaches() {
		for (DaoCache cache: caches) {
			cache.clear();
		}
		log.info("Caches cleared.");
	}

	public void enableCache() {
		log.info("Caching enabled");
		DaoCache.setEnabled(true);
	}
	
	public void disableCache(){
		log.info("Caching disabled");
		DaoCache.setEnabled(false);
		clearCaches();
	}
	
	public List<CacheLevel> reportCacheLevels() {
		List<CacheLevel> cacheLevels = new ArrayList<CacheLevel>();
		for (DaoCache cache: caches) {
			if (null == cache.getTarget()) {
				log.info("Cache " + cache.getClass().getSimpleName() + " Not yet in use " + cache.getPercentageFull() + "% of cache capacity has been used.");
				cacheLevels.add(new CacheLevel(cache.getClass().getSimpleName(), "Not yet in use", cache.getPercentageFull()));
			}
			else {
				if (cache instanceof GetAllAspect) {
					log.info("Cache " + cache.getClass().getSimpleName() + " for target " + cache.getTarget().getSimpleName() + " is in use." + "100% of cache capacity has been used.");
					cacheLevels.add(new CacheLevel(cache.getClass().getSimpleName(), cache.getTarget().getSimpleName(), 100));
				}
				else {
					log.info("Cache " + cache.getClass().getSimpleName() + " for target " + cache.getTarget().getSimpleName() + " is " + cache.getPercentageFull() + "% of cache capacity has been used. Items cached: " + cache.getNoOfObjectsCached());
					cacheLevels.add(new CacheLevel(cache.getClass().getSimpleName(), cache.getTarget().getSimpleName(), cache.getPercentageFull()));
				}
			}
		}
		return cacheLevels;
	}

	@Override
	public void writeToDisk() throws IOException {
		FileUtils.cleanDirectory(new File(cacheStoreDir));
//		persist("FindByModelAndItemAspect");
//		persist("FindByItemAndModelAspect");
//		persist("FindByTableItemAspect");
		persist("LatestDataAspect");
		persist("GetBranchDataAspect");
		persist("TaggedDataAspect");
//		persist("FindByTableIdAspect");
//		persist("FindByRunAndModelAndCompanyAspect");
//		persist("FindByCompanyAndRunAspect");
	}

	@Override
	public void readFromDisk() {
		clearCaches();
//		hydrate("FindByModelAndItemAspect");
//		hydrate("FindByItemAndModelAspect");
//		hydrate("FindByTableItemAspect");
		hydrate("LatestDataAspect");
		hydrate("GetBranchDataAspect");
		hydrate("TaggedDataAspect");
//		hydrate("FindByTableIdAspect");
//		hydrate("FindByRunAndModelAndCompanyAspect");
//		hydrate("FindByCompanyAndRunAspect");
	}
	
	private void persist(String cacheName) {
		for (DaoCache cache: caches) {
			if (!cache.getClass().getSimpleName().equals(cacheName)) {
				continue;
			}
			if (null == cache.getPersistantFileName()) {
				// cache not yet in use
				log.info("Unable to persist " + cacheName + ". Cache not yet in use.");
				return;
			}

			Long startTime = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.putAll((Map<String, Object>)cache.getCache());
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
		        fos = new FileOutputStream(cacheStoreDir + cache.getPersistantFileName());
		        oos = new ObjectOutputStream(fos);
				oos.writeObject(map);
				oos.flush();
				fos.flush();
			} catch (FileNotFoundException fnfe) {
				log.info(fnfe.getMessage());
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				try {
					oos.close();
					fos.close();
				} catch (IOException closeE) {
					closeE.printStackTrace();
				}
			}
			Long elapsedTime = System.currentTimeMillis() - startTime;
			log.info("Persisted " + cache.getNoOfObjectsCached() + " items in " + elapsedTime + "ms for " + cache.getClass().getSimpleName() + "-" + cache.getTarget().getSimpleName() + " cache to " + cacheStoreDir + cache.getPersistantFileName());
		}
	}

	@SuppressWarnings("unchecked")
	private void hydrate(String cacheName) {
		for (DaoCache cache: caches) {
			if (!cache.getClass().getSimpleName().equals(cacheName)) {
				continue;
			}
			cache.clear();
			if (null == cache.getPersistantFileName()) {
				// cache not yet in use
				cache.initialiseCache(DataDaoImpl.class);
			}
			
			Long startTime = System.currentTimeMillis();
			FileInputStream fis = null;
			ObjectInputStream ois = null;
	        try {
				fis = new FileInputStream(cacheStoreDir + cache.getPersistantFileName());
			} catch (FileNotFoundException fnfe) {
				log.error(fnfe.getMessage());
				return;
			}
	        try {
		        ois = new ObjectInputStream(fis);
		        Map<String, Object> cacheFromDisk = (Map<String, Object>)ois.readObject();
		        if (null != cache.getCache()) {
		        	cache.getCache().putAll((Map<String, Object>)cacheFromDisk);
		        }
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				return;
			} finally {
				try {
					ois.close();
					fis.close();
				} catch (IOException closeE) {
					closeE.printStackTrace();
				}
			}
			Long elapsedTime = System.currentTimeMillis() - startTime;
			log.info("Hydrated " + cache.getNoOfObjectsCached() + " items in " + elapsedTime + "ms for " + cache.getClass().getSimpleName() + "-" + cache.getTarget().getSimpleName() + " cache from " + cacheStoreDir + cache.getPersistantFileName());
		}
	}
	
}
