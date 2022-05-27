package fr.pay.scim.serveur.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pay.scim.serveur.endpoint.entity.ScimError;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.mapper.ScimUserMapper;
import fr.pay.scim.serveur.exception.NotFoundException;
import fr.pay.scim.serveur.exception.ScimException;
import fr.pay.scim.serveur.service.UsersService;
import fr.pay.scim.serveur.service.entity.user.User;
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
@RequestMapping("/Users")
public class UsersEndPoint {
	
	private UsersService usersService;
	
	private ScimUserMapper mapper = new ScimUserMapper();
	
	public UsersEndPoint(UsersService usersService) {
		this.usersService = usersService;
	}
	
	
	//  Read: GET https://example.com/{v}/{resource}/{id}
	//  RFC : 	200 OK
	//			Content-Type: application/scim+json
	//			Location: https://example.com/v2/Users/2819c223-7f76-453a-919d-413861904646
	//          ETag: W/"e180ee84f0671b1"
	@Operation(summary = "search for a user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user is found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
	})
	@GetMapping("/{id}")
	public ResponseEntity<ScimUser> read(
			@Parameter(description = "Id of user to be searched.") @PathVariable String id,
			HttpServletRequest request
			) throws ScimException {
		
		User user = usersService.read(id);
		
		if (user == null) {
			throw new NotFoundException("User not found.");			
		}
		
		String location = request.getRequestURL().toString();
		ScimUser scimUser = mapper.mapper(user, location);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimUser);
	}

	
	
	
	
	//  Create: POST https://example.com/{v}/{resource}
	//  RFC : 	201 Created
	//			Content-Type: application/scim+json
	//			Location: https://example.com/v2/Users/2819c223-7f76-453a-919d-413861904646
	//          ETag: W/"e180ee84f0671b1"
	@Operation(summary = "Creation of a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "The user is created.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "409", description = "User already exists.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PostMapping("")
	public ResponseEntity<ScimUser> create(
			@RequestBody @Validated ScimUser scimUser,
			HttpServletRequest request
			) throws ScimException {

		User user = mapper.mapper(scimUser);
		
		user = usersService.create(user);
		
		String location = request.getRequestURL()+ "/" + scimUser.getId();
		scimUser = mapper.mapper(user, location);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimUser);
	}

	
	
	//  Replace: PUT https://example.com/{v}/{resource}/{id}
	//	RFC : 	200 OK
	//	   		Content-Type: application/scim+json
	//	   		Location: "https://example.com/v2/Users/2819c223-7f76-453a-919d-413861904646"
	//	   		ETag: W/"b431af54f0671a2"
	@Operation(summary = "Replacing a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user's has been updated.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PutMapping("/{id}")
	public ResponseEntity<ScimUser> replace(
			@Parameter(description = "Id of user to be searched.") @PathVariable String id,
			@RequestBody @Validated ScimUser scimUser,
			HttpServletRequest request
			) throws ScimException {

		User user = mapper.mapper(scimUser);
		
		user = usersService.update(id, user);
				
		String location = request.getRequestURL()+ "/" + scimUser.getId();
		scimUser = mapper.mapper(user, location);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimUser);
	}

	
	
//// Update: PATCH https://example.com/{v}/{resource}/{id}	
//	
	
	// Delete: DELETE https://example.com/{v}/{resource}/{id}
	// RFC :	204 No Content
	@Operation(summary = "Deleting a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "User deleted.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@DeleteMapping("/{id}")
	public ResponseEntity<ScimUser> delete(
			@Parameter(description = "User ID") @PathVariable String id
			) throws ScimException {
		
		usersService.delete(id);
		
		return ResponseEntity
				.noContent()
				.build();
	}
	
	
//	
//	
//// Search: GET https://example.com/{v}/{resource}?ﬁlter={attribute}{op}{value}&sortBy={attributeName}&sortOrder={ascending|descending}
////  Bulk: POST https://example.com/{v}/Bulk
	
}
