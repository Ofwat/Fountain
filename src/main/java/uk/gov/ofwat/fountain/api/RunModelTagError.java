package uk.gov.ofwat.fountain.api;

public class RunModelTagError extends Error {

	public RunModelTagError() {
	}

	public RunModelTagError(String message) {
		super(message);
	}

	public RunModelTagError(Throwable cause) {
		super(cause);
	}

	public RunModelTagError(String message, Throwable cause) {
		super(message, cause);
	}

	public RunModelTagError(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
