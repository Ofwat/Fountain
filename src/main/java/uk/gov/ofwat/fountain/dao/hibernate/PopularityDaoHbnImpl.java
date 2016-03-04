package uk.gov.ofwat.fountain.dao.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;


import uk.gov.ofwat.fountain.api.PopularityPeriod;
import uk.gov.ofwat.fountain.dao.PopularityDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.popularity.View;
import uk.gov.ofwat.fountain.domain.tag.Tag;

public class PopularityDaoHbnImpl extends AbstractHbnDao<View> implements PopularityDao {

	@Override
	public List<HashMap<Long, Float>> getItemsByPopularity(String type,
			PopularityPeriod period, int count) {
		
		/*
		 Periods are 1 year, 1 month, 1 week, 1day, all time?
		*/
		/*
		select entityId, count(1)/7 as popularity 
		from tbl_popularity 
		where entityType = 'test' and date < '2014-01-20'
		group by entityId
		order by popularity desc
		limit 10
		*/
		
		int limit = 10;
		
		//This query will get entities that are the most popular over the period using an average. 
		String queryString = "select entityId, count(1)/:days as popularity from tbl_popularity where entityType = :entityType" + 
				" and date > :date group by entityId order by popularity desc limit :limit";
		
		int daysFromNow = PopularityPeriod.calculateDaysInPastFromNow(period);
		
		Date calculatedDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, (daysFromNow*-1));		
		
		Query query = getSession().createSQLQuery(queryString)
				.setParameter("days", daysFromNow+1)
				.setParameter("entityType", type)
				.setParameter("date", calendar.get(Calendar.YEAR) + "-" + new Integer(calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH))//"2014-10-20"
				.setParameter("limit", limit);
		
		ArrayList<ArrayList<Object>> results = (ArrayList<ArrayList<Object>>) query.list();
		
		//Build the result. 
		//TreeMap<Long, Float> popularities = new TreeMap<Long, Float>();
		ArrayList<HashMap<Long, Float>> popularities = new ArrayList<HashMap<Long, Float>>();
		
		for (Object r : results) {
			Object[] a = (Object[]) r;
			BigInteger id = (BigInteger)a[0];
			BigDecimal val = (BigDecimal)a[1];
			HashMap<Long, Float> result = new HashMap<Long, Float>();
			result.put(id.longValue(), val.floatValue());
			popularities.add(result);
		}
		return popularities;
	}
	
	@Override
	public void addPopularity(String type, Long id) {
		View view = new View();
		view.setDate(new Date());
		view.setEntityId(id);
		view.setEntityType(type);
		//Save the view. 
		create(view);		
	}	

	@Override
	public void addPopularity(Entity entity) {
		View view = new View();
		view.setDate(new Date());
		view.setEntityId(new Long(entity.getId()));
		view.setEntityType(entity.getClass().toString());
		//Save the view. 
		create(view);		
	}
	
	@Override
	/**
	 * This will remove the earliest popularity record for an entity. 
	 */
	public void removePopularity(Entity entity) {
		//Find the view by entityId
		View view = findEarliestViewByEntityId(new Long(entity.getId()));
		delete(view);
	}

	@Override
	/**
	 * Find the latest instance of view from the associated entityID
	 */
	public View findLatestViewByEntityId(Long entityId) {
		Session s = getSession();
		Query q = s.getNamedQuery("findLatestByEntityId");
		q.setCacheable(true);
		q.setLong("entityId", entityId);
		View view = (View)q.uniqueResult();
		return view;		
	}
	
	@Override
	/**
	 * Find the earliest instance of view from the associated entityID
	 */
	public View findEarliestViewByEntityId(Long entityId) {
		Session s = getSession();
		Query q = s.getNamedQuery("findEarliestByEntityId");
		q.setCacheable(true);
		q.setLong("entityId", entityId);
		View view = (View)q.uniqueResult();
		return view;
	}

	@Override
	/**
	 * Remove all entries that are over a year old. 
	 * Run as a scheduled task.
	 */
	public void cleanUp(PopularityPeriod period) {
		//Delete all records that existed after the popularity period we are interested in. 
		
		//Date we want to remove records before. 
		int daysAgo = PopularityPeriod.calculateDaysInPastFromNow(period);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, (daysAgo*-1));
		
		//HQL query.
		String hql = "delete from View v where v.date > :date";
		getSession().createQuery(hql).setParameter("date", calendar.getTime()).executeUpdate();
		
	}	
	
	
	
}
