package uk.gov.ofwat.fountain.exception;

import org.springframework.jdbc.UncategorizedSQLException;

public class ReportTooLargeException extends Exception {
	
	public ReportTooLargeException(UncategorizedSQLException e){
		super(e.getSQLException());
	}
	
}
