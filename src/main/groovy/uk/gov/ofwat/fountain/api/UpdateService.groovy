package uk.gov.ofwat.fountain.api

import uk.gov.ofwat.fountain.domain.update.Update;
import uk.gov.ofwat.fountain.domain.update.Release;

public interface UpdateService {
	public List<Release> getAllReleases()
	public Release getRelease(Long releaseId)
	public Release getLatestRelease()
	public List<Update> getUpdates(Long releaseId)
	public Release setPublished(Long releaseId, Boolean published)
	public Release createRelease(Release release)
	public Release updateRelease(Release release)
	public Update addUpdate(Long releaseId, Update update)
	public Boolean deleteUpdate(Long updateId)
	public Update getUpdate(Long updateId);
}
