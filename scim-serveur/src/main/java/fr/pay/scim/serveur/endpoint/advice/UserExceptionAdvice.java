package fr.pay.scim.serveur.endpoint.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.pay.scim.serveur.endpoint.entity.ScimError;
import fr.pay.scim.serveur.exception.ScimException;

@ControllerAdvice
public class UserExceptionAdvice {

	
	@ExceptionHandler(ScimException.class)
	public ResponseEntity<ScimError> methodArgumentNotValidExceptionHandler(ScimException ex) {
		 
        ScimError error = new ScimError();
		error.setStatus(String.valueOf(ex.getStatus().value()));
		error.setDetail(ex.getMessage());
		
		return new ResponseEntity<ScimError>(error, ex.getStatus());
	}

	
}
