package fr.pay.scim.serveur.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pay.scim.serveur.endpoint.entity.ScimGroup;
import fr.pay.scim.serveur.exception.NotFoundException;
import fr.pay.scim.serveur.exception.ScimException;
import fr.pay.scim.serveur.service.GroupsService;
import fr.pay.scim.serveur.service.entity.Group;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * https://datatracker.ietf.org/doc/html/rfc7644
 * 
 * @author PAUBRY
 */
@RestController
@RequestMapping("/Groups")
public class GroupsEndPoint extends ResourceEndPoint<ScimGroup, GroupsService> {

	private GroupsService groupsService;
	
	public GroupsEndPoint(GroupsService resourceService) {
		super(resourceService);
		this.groupsService = resourceService;
	}

	
	
	//  Read: GET https://example.com/{v}/{resource}/{id}
	//  RFC : 	200 OK
	//			Content-Type: application/scim+json
	//			Location: https://example.com/v2/{resource}/2819c223-7f76-453a-919d-413861904646
	//          ETag: W/"e180ee84f0671b1"
	@Operation(summary = "search for a group")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The group is found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimGroup.class))}),
			@ApiResponse(responseCode = "404", description = "Group not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimException.class))})
	})
	@GetMapping("/{id}")
	public ResponseEntity<ScimGroup> read(
			@Parameter(description = "Id of group to be searched.") @PathVariable String id,
			HttpServletRequest request
			) throws ScimException {
		
		return readResource(id, request);
	}
	
}
