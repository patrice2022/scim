package fr.pay.scim.serveur.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.pay.scim.serveur.endpoint.entity.ScimGroup;
import fr.pay.scim.serveur.exception.NotFoundException;
import fr.pay.scim.serveur.service.ResourceService;

/**
 * https://datatracker.ietf.org/doc/html/rfc7644
 * 
 * @author PAUBRY
 */
public abstract class ResourceEndPoint<T, S extends ResourceService<T>> {

	private S resourceService;
	
	public ResourceEndPoint(S resourceService) {
		super();
		this.resourceService = resourceService;
	}

	
	public ResponseEntity<T> readResource(String id, HttpServletRequest request) throws NotFoundException {
		
		T result = resourceService.read(id);
		
		if (result == null) {
			throw new NotFoundException("Not found.");			
		}
		
		String location = request.getRequestURL().toString();
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(result);

	}
	
}