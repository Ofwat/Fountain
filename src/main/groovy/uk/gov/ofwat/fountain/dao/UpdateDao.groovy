package uk.gov.ofwat.fountain.dao

import uk.gov.ofwat.fountain.domain.update.*

interface UpdateDao {
	
	public Release getRelease(Long releaseId)
	
	public List<Release> getAllReleases()
	
	public Release createRelease(Release release)
	
	public Release updateRelease(Release release)
	
	public Update getUpdate(Long updateId)
	
	public Update createUpdate(Update update, Release release)
	
	public Boolean deleteUpdate(Update update)
	
}

