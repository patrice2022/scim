package fr.pay.scim.serveur.exception;

public class ScimException extends Exception {

	public ScimException() {
		super();
	}

	public ScimException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ScimException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScimException(String message) {
		super(message);
	}

	public ScimException(Throwable cause) {
		super(cause);
	}

}
