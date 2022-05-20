package fr.pay.scim.serveur.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ScimException {

	public ConflictException() {
		super(HttpStatus.CONFLICT);
	}

	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}

}
