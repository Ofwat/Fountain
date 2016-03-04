package uk.gov.ofwat.fountain.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 
 * @author james.toddington
 * Abstract class to allow the generic CRUD operations for Fountain domain entities. 
 *
 * @param <T> The type of the domain class to be returned. 
 */
@Transactional
public class AbstractHbnDao<T extends Object> implements Dao<T> {
	
	private SessionFactory sessionFactory; //TODO - do we want to use annotations?! Just use the standard spring context?

	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	private Class<T> domainClass;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();		
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass(){
		if(domainClass == null){
			ParameterizedType thisType = 
					(ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass = 
					(Class<T>) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
	private String getDomainClassName(){
		return getDomainClass().getName();
	}
	
	public void create(T t){
		Method method = ReflectionUtils.findMethod(
				getDomainClass(), "setDateCreated", 
				new Class[] {Date.class});
		if(method != null){
			try{
				method.invoke(t, new Date());
			}catch(Exception e){
				//Maybe the only case where it's ok not to do anything with an exception?
			}
		}
		getSession().save(t);				
	}
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id){
		return (T) getSession().get(getDomainClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public T load(Serializable id){
		return (T) getSession().load(getDomainClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		return getSession()
				.createQuery("from " + getDomainClassName())
				.setCacheable(true)
				.list();
	}
	
	public void update(T t){
		getSession().update(t);
		
	}
	
	public void delete(T t){getSession().delete(t);}
	
	public void deleteById(Serializable id){
		Session session = getSession();
		delete(load(id));
	}
	
	public void deleteAll(){
		getSession()
				.createQuery("delete " + getDomainClassName())
				.setCacheable(true)
				.uniqueResult();
	}
	
	public long count(){
		return (Long) getSession()
				.createQuery("select count(*) from " + getDomainClassName())
				.setCacheable(true)
				.uniqueResult();
	}
	
	public boolean exists(Serializable id){return (get(id) != null);}
}
