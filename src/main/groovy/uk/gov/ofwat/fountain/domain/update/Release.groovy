package uk.gov.ofwat.fountain.domain.update


class Release implements Entity {
	String releaseName
	Boolean published
	Date releaseDate
	String releaseVersion
	ArrayList updates = []
}