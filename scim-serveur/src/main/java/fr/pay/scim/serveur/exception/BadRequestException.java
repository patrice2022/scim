package fr.pay.scim.serveur.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ScimException {

	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

}
