package uk.gov.ofwat.fountain.api

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ReportDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.search.FountainSearchIndex;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;
import uk.gov.ofwat.fountain.exception.InvalidSearchTypeException;
import uk.gov.ofwat.fountain.exception.SearchException;
import uk.gov.ofwat.fountain.api.IndexService;


class IndexServiceImpl implements IndexService{

	private static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	SearchService searchService
	ItemDao itemDao
	TableDao tableDao
	ReportService reportService
	
	@Override
	public Boolean index(Class clazz, String ids) {
		logger.info("Indexing Class ${clazz} with String ids ${ids}")
		def intIds
		intIds = ids.tokenize(',')*.toInteger()
		return index(clazz, intIds);
	}
	
	@Override
	public Boolean index(Class clazz, ArrayList ids) {
		ids.each{
			index(clazz, it)
		}
	}

	@Override
	public Boolean indexAll(Class clazz) {
		logger.info("Indexing all of Class ${clazz}")
		def count = 0
		def totalCount = 0
		def success = false
		switch(clazz){
			case Item:
				def items = itemDao.findAll()
				logger.info("Found ${items.size()} of ${clazz}")
				items.each{ item ->
					if(count%100 == 0)
						logger.info("Put ${count} of ${clazz}")
					searchService.put(item, clazz)
					count++
				}
			break
			case ReportSearchWrapper:
			
				//Swap this for a single call that does all of the reports similar to the Groovy script. 
			
				reportService.indexAllReports();
				
				
				/*
				def reportWrappers = reportService.getAllReportSearchWrappers();
				logger.info("Found ${reportWrappers.size()} of ${clazz}")
				reportWrappers.each{ rw ->
					if(count%100 == 0)
						logger.info("Put ${count} of ${clazz}")
					searchService.put(rw, clazz)
					count++
				}
				*/
			break;
			/*
			case ReportStructure:
				def reportStructures = reportService.getAllReportStructures()
				logger.info("Found ${reportStructures.size()} of ${clazz}")
				reportStructures.each{ rs ->
					if(count%100 == 0)
						logger.info("Put ${count} of ${clazz}")
					elasticSearchService.put(rs, clazz)
					count++
				}
			break;
			*/
			case Table:
				def tables = tableDao.findAll()
				logger.info("Found ${tables.size()} of ${clazz}")
				tables.each{ table ->
					if(count%100 == 0)
						logger.info("Put ${count} of ${clazz}")
					searchService.put(table, clazz)
					count++
				}
			break;
			default:
				logger.error("Unable to find a searchable class.")
				throw new InvalidSearchTypeException(clazz)
			break
		}
		return success
	}

	@Override
	public boolean index(Class clazz, Integer id) {
		def success = false
		logger.info("Indexing Class ${clazz} with id ${id}")
		try{
			switch(clazz){
				case Item:
					def item = itemDao.findById(id)
					searchService.put(item, clazz)
				break
				case ReportSearchWrapper:
					def reportSearchWrapper = reportService.getReportSearchWrapper(id);
					searchService.put(reportSearchWrapper, clazz)
				break;
				/*
				case ReportStructure:
					def reportStructure = reportService.getReportStructure(id)
					elasticSearchService.put(reportStructure, clazz)
				break;
				*/
				case Table:
					def table = tableDao.findById(id)
					searchService.put(table, clazz)
				break;
				default:
					logger.error("Unable to find a searchable class.")
					throw new InvalidSearchTypeException(clazz)
				break
			}
		}
		catch(SearchException){
			success = false;
		}
		return success
	}	
}