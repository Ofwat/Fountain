package uk.gov.ofwat.fountain.domain.updateimport uk.gov.ofwat.fountain.domain.Entity;import clover.gnu.cajo.utils.extra.Implements;


class Release implements Entity {	long id
	String releaseName
	Boolean published
	Date releaseDate
	String releaseVersion
	ArrayList updates = []	@Override	public int getId() {		return this.id	}
}
