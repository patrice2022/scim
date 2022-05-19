package fr.pay.scim.serveur.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pay.scim.serveur.exception.NotImplementedException;
import fr.pay.scim.serveur.exception.ScimException;

/**
 * https://datatracker.ietf.org/doc/html/rfc7644
 * 
 * @author PAUBRY
 */
@RestController
@RequestMapping("/Users")
public class UsersEndPoint {

	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(
			@PathVariable String id,
			HttpServletRequest request
			) throws ScimException {
		
		throw new NotImplementedException();
	}
	

	
}
