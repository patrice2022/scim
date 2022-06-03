package fr.pay.scim.serveur.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.pay.scim.serveur.endpoint.entity.ScimError;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUsers;
import fr.pay.scim.serveur.endpoint.mapper.ScimUserMapper;
import fr.pay.scim.serveur.endpoint.patch.PatchOp;
import fr.pay.scim.serveur.endpoint.patch.PatchProcess;
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
import lombok.extern.slf4j.Slf4j;

/**
 * https://datatracker.ietf.org/doc/html/rfc7644
 * 
 * @author PAUBRY
 */
@RestController
@Slf4j
@RequestMapping("/Users")
public class UsersEndPoint {
	
	private UsersService usersService;
	
	private ScimUserMapper mapper = new ScimUserMapper();
	
	private PatchProcess patchProcess = new PatchProcess();
	
	public UsersEndPoint(UsersService usersService) {
		this.usersService = usersService;
	}
	
	
	// Search: GET https://example.com/{v}/{resource}?filter={attribute}{op}{value}&sortBy={attributeName}&sortOrder={ascending|descending}
	@Operation(summary = "Search for users")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "users trouvés", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))})
			})
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public ScimUsers all(
			@Parameter(description = "startIndex") @RequestParam(defaultValue = "1", required = false) int startIndex,
			@Parameter(description = "count") @RequestParam(defaultValue = "5", required = false) int count
			) {
		
		List<User> users = usersService.all();
		
		List<ScimUser> scimUser = users.stream()
				.skip(startIndex-1)
				.limit(count)
				.map(p -> mapper.mapper(p, null))
				.collect(Collectors.toList());
		
		ScimUsers scimUsers = new ScimUsers();
		scimUsers.setTotalResults(users.size());
		scimUsers.setItemsPerPage(scimUser.size());
		scimUsers.setStartIndex(startIndex);
		scimUsers.setResources(scimUser);
		
		return scimUsers;
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

		log.info("Demande de création de compte : {}", scimUser);
		
		User user = mapper.mapper(scimUser);
		
		user = usersService.create(user);
		
		String location = request.getRequestURL()+ "/" + user.getId();
		scimUser = mapper.mapper(user, location);
		
		log.info("Création du compte effectuée : {}", scimUser);
		
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

		User user = usersService.read(id);
		
		if (user == null) {
			throw new NotFoundException("User not found.");			
		}
		
		user = mapper.mapper(scimUser);
		user = usersService.update(id, user);
				
		String location = request.getRequestURL()+ "/" + user.getId();
		scimUser = mapper.mapper(user, location);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimUser);
	}

		
	// Update: PATCH https://example.com/{v}/{resource}/{id}	
	@Operation(summary = "Patch a user.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "The user's has been updated.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimUser.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))}),
			@ApiResponse(responseCode = "404", description = "User not found.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ScimError.class))})
			})
	@PatchMapping("/{id}")
	public ResponseEntity<ScimUser> patch(
			@Parameter(description = "Id of user to be patched.") @PathVariable String id,
			@RequestBody PatchOp patchOp,
			HttpServletRequest request
			) throws ScimException {

		// On recherche le user
		User user = usersService.read(id);
		
		if (user == null) {
			throw new NotFoundException("User not found.");			
		}
		
		// Conversion de l'utilisateur dans son format Scim
		ScimUser scimUser = mapper.mapper(user, null);

		// On récupere le scim de l'utilisateur qui es paché
		ScimUser scimUserPatched = patchProcess.patch(scimUser, patchOp);
			
		// On effectue la sauvegarde
		user = usersService.update(id, mapper.mapper(scimUserPatched));
			
		String location = request.getRequestURL()+ "/" + user.getId();
		ScimUser scimUserUpdated = mapper.mapper(user, location);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Content-Type", "application/scim+json")
				.header("Location", location)
				.body(scimUserUpdated);
	}
	
		
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
		
		User user = usersService.read(id);
		
		if (user == null) {
			throw new NotFoundException("User not found.");			
		}
		
		usersService.delete(id);
		
		return ResponseEntity
				.noContent()
				.build();
	}
	
	

////  Bulk: POST https://example.com/{v}/Bulk
	
}
