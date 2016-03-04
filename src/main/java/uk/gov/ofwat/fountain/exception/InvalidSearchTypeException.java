package uk.gov.ofwat.fountain.exception;

import org.springframework.jdbc.UncategorizedSQLException;

public class InvalidSearchTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSearchTypeException(Class clazz){
		super("Sorry I don't know what to do with objects of type: " + clazz.toString());
	}
}
