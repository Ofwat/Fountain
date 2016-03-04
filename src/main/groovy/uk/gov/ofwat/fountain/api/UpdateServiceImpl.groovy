package uk.gov.ofwat.fountain.api

import java.util.List;
import uk.gov.ofwat.fountain.api.UpdateService
import uk.gov.ofwat.fountain.dao.UpdateDao;
import uk.gov.ofwat.fountain.domain.update.Release;
import uk.gov.ofwat.fountain.domain.update.Update;

class UpdateServiceImpl implements UpdateService {

	UpdateDao updateDao
	
	public List<Release> getAllReleases() {
		return updateDao.getAllReleases()
	}

	@Override
	public Release getRelease(Long releaseId) {
		return updateDao.getRelease(releaseId)
	}

	@Override
	public Release getLatestRelease() {
		List<Release> releases = updateDao.getAllReleases()
		Release latestRelease
		for(Release release: releases){
			if(release.published){
				return release
			}
		}
	}

	@Override
	public List<Update> getUpdates(Long releaseId) {
		return updateDao.getRelease(releaseId).updates
	}

	@Override
	public Release setPublished(Long releaseId, Boolean published) {
		Release release = updateDao.getRelease(releaseId)
		release.published = published
		updateDao.updateRelease(release)
		return release;
	}

	@Override
	public Release createRelease(Release release) {
		return updateDao.createRelease(release);
	}
	
	@Override
	public Release updateRelease(Release release) {
		return updateDao.updateRelease(release)
	}
	
	@Override
	public Update getUpdate(Long updateId) {
		Update update = updateDao.getUpdate(updateId);
		return update;
	}

	@Override
	public Update addUpdate(Long releaseId, Update update) {
		Release release = updateDao.getRelease(releaseId)
		return updateDao.createUpdate(update, release)
	}
	
	@Override
	public Boolean deleteUpdate(Long updateId) {
		Update update = updateDao.getUpdate(updateId)
		return updateDao.deleteUpdate(update)
	}
	
}
