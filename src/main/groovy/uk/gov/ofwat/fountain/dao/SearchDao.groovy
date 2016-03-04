package uk.gov.ofwat.fountain.dao

import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;

interface SearchDao {
	
	SaveSearchDto getSearchDto(Long id)
		
	def saveSearchHistory(SaveSearchDto dto)
	
	def saveSearchFavorite(SaveSearchDto dto)
	
	def getSearchHistoryDtos(int maxToReturn, String user)
	
	def getSearchHistoryDtos(int maxToReturn)
	
	def getSearchFavoriteDtos(int maxToReturn, String user)
	
	def deleteSearch(Long searchId)
	
}
