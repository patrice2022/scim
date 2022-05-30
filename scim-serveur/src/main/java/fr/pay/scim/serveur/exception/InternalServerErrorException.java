package fr.pay.scim.serveur.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ScimException {

	public InternalServerErrorException() {
		super(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

}
