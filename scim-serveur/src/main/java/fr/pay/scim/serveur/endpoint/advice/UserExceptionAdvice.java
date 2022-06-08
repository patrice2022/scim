package fr.pay.scim.serveur.endpoint.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ScimError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		 
        ScimError error = new ScimError();
		error.setStatus("400");
		error.setDetail(ex.getMessage());
		
		return new ResponseEntity<ScimError>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ScimError> methodArgumentNotValidExceptionHandler(Exception ex) {
		 
        ScimError error = new ScimError();
		error.setStatus("500");
		error.setDetail(ex.getMessage());
		
		return new ResponseEntity<ScimError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
