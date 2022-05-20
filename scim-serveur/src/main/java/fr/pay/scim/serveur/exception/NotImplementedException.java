package fr.pay.scim.serveur.exception;

import org.springframework.http.HttpStatus;

public class NotImplementedException extends ScimException {

	public NotImplementedException() {
		super(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public NotImplementedException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

}
