package uk.gov.ofwat.fountain.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

public interface Dao <T extends Object>{
	
	void create(T t);
	
	T get(Serializable id);
	
	T load(Serializable id);
	
	List<T> getAll();
		
	void update(T t);
	
	void delete(T t);
	
	void deleteById(Serializable id);
	
	void deleteAll();
	
	long count();
	
	boolean exists(Serializable id);
	
}
