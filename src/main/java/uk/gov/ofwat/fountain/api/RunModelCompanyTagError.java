package uk.gov.ofwat.fountain.api;

public class RunModelCompanyTagError extends Error {

	public RunModelCompanyTagError() {
	}

	public RunModelCompanyTagError(String message) {
		super(message);
	}

	public RunModelCompanyTagError(Throwable cause) {
		super(cause);
	}

	public RunModelCompanyTagError(String message, Throwable cause) {
		super(message, cause);
	}

	public RunModelCompanyTagError(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
