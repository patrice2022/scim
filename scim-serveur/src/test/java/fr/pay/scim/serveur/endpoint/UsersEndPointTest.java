package fr.pay.scim.serveur.endpoint;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pay.scim.serveur.endpoint.advice.UserExceptionAdvice;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.patch.Operation;
import fr.pay.scim.serveur.endpoint.patch.PatchOp;
import fr.pay.scim.serveur.service.UsersService;
import fr.pay.scim.serveur.service.entity.user.User;

@SpringBootTest
@DisplayName("Test de compatibilité avec la RFC 7644")
class UsersEndPointTest {

	private MockMvc mockMvc;
	
	@Mock
	UsersService usersService;
	
	@InjectMocks
	UsersEndPoint userEndPoint;
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	User user1 = null;
	
	ScimUser scimUser1 = null;
			
	@BeforeEach
	public void setUp() {
	        
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(userEndPoint)
				.setControllerAdvice(new UserExceptionAdvice())
				.build();		
		
		// User in base
		user1 = new User();
		user1.setId("a510f190-aa6d-46b3-924b-4bd3ad7a50e6");
		user1.setExternalId("9999");
		user1.setUserName("johndo");
		user1.setFamilyName("Do");
		user1.setGivenName("John");
		user1.setDisplayName("John Do");
		user1.setTitle("Boulanger");
		user1.setEmail("do.john@johndo.local");
		user1.setCreated(new Date(1653569543794L));
		user1.setLastModified(new Date(1653569543794L + 500L));
		user1.setActive(true);
		
		
		Map<String, User> users = new HashMap<>();
		users.put(user1.getId(), user1);
		
		Mockito.when(usersService.read(user1.getId())).thenAnswer(invocation -> { 
																String id = (String) invocation.getArguments()[0];  
																return users.get(id); } );	
		
		Mockito.when(usersService.readByUsername("johndo")).thenReturn(user1);	

		
		Mockito.when(usersService.create(any(User.class))).thenAnswer(invocation -> { 
																User u = (User) invocation.getArguments()[0];  
																u.setId(UUID.randomUUID().toString());
																return u; } );
		
		Mockito.when(usersService.all()).thenReturn(Arrays.asList(new User(), user1, new User(), new User(), new User()));
		
		// User in base
		scimUser1 = new ScimUser();
		scimUser1.setId("a510f190-aa6d-46b3-924b-4bd3ad7a50e6");
		scimUser1.setUserName("johndo");
		
		Mockito.when(usersService.update(eq(scimUser1.getId()), any())).thenAnswer(invocation -> invocation.getArguments()[1]);
		
		
	}
	
	
	
	//-----------------------------------------------------------
	//  GET
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - Retrouver la liste des utilisateurs (Ok)")
	void testGet() throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalResults", is(5)))
				.andExpect(jsonPath("$.itemsPerPage", is(5)))
				.andExpect(jsonPath("$.startIndex", is(1)))
				.andExpect(jsonPath("$.Resources", hasSize(5)))
				.andExpect(jsonPath("$.Resources[1].id", is("a510f190-aa6d-46b3-924b-4bd3ad7a50e6")));
	}
	
	
	@Test
	@DisplayName("RFC7644 - Retrouver la liste des utilisateurs avec startIndex et count (Ok)")
	void testGetWithStartIndex() throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users")
					.param("startIndex", "2")
					.param("count", "2")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalResults", is(5)))
				.andExpect(jsonPath("$.itemsPerPage", is(2)))
				.andExpect(jsonPath("$.startIndex", is(2)))
				.andExpect(jsonPath("$.Resources", hasSize(2)))
				.andExpect(jsonPath("$.Resources[0].id", is("a510f190-aa6d-46b3-924b-4bd3ad7a50e6")));
	}
	
	
	//-----------------------------------------------------------
	//  GET {id}
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - Retrouver un utilisateur connu (Ok)")
	void testGetWithId() throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users/" + user1.getId())
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	
	@Test
	@DisplayName("RFC7644 - Retrouver un utilisateur connu (NotFound)")
	void testGetWithId404() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users/a510f190-aa6d-47b3-924b-4bd3ad6a50e6")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	
	
	//-----------------------------------------------------------
	//  POST
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - Création d'un compte utilisateur (Created)")
	void testPost() throws Exception {
		
		ScimUser scimUser = new ScimUser();
		scimUser.setUserName("bjensen");
		
		mockMvc.perform(MockMvcRequestBuilders
							.post("/Users")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(scimUser)))
						.andExpect(status().isCreated());
	}
	
	
	@Test
	@DisplayName("RFC7644 - Création d'un compte utilisateur (Conflict)")
	void testPostDoublon() throws Exception {
		
		ScimUser scimUser = new ScimUser();
		scimUser.setUserName("johndo");
		
		mockMvc.perform(MockMvcRequestBuilders
							.post("/Users")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(scimUser)))
						.andExpect(status().isConflict());
	}
	
	//-----------------------------------------------------------
	//  Put
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - Modification d'un compte utilisateur (Ok)")
	void testPut() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
							.put("/Users/" + scimUser1.getId())
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(scimUser1)))
						.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("RFC7644 - Modification d'un compte utilisateur (NotFound)")
	void testPutNotFound() throws Exception {

		ScimUser scimUser = new ScimUser();
		scimUser.setId("a510f190-aa6d-47b3-924b-4bd3ad6a50e6");
		scimUser.setUserName("johndo2");
		
		mockMvc.perform(MockMvcRequestBuilders
							.put("/Users/" + scimUser.getId())
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(scimUser)))
						.andExpect(status().isNotFound());
	}
	
	
	//-----------------------------------------------------------
	//  Patch
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - patch displayName (OK)")
	void testPatch() throws Exception {
		
		Operation op = new Operation("replace", "/displayName", "John DO");
		
		PatchOp patchOp = new PatchOp();
		patchOp.setOperations(Arrays.asList(op));
		
		mockMvc.perform(MockMvcRequestBuilders
							.patch("/Users/" + scimUser1.getId())
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(patchOp)))
						.andExpect(status().isOk());
	}
	
	
	@Test
	@DisplayName("RFC7644 - patch displayName (notFound)")
	void testPatchNotFound() throws Exception {
		
		Operation op = new Operation("replace", "/displayName", "John DO");
		
		PatchOp patchOp = new PatchOp();
		patchOp.setOperations(Arrays.asList(op));
		
		mockMvc.perform(MockMvcRequestBuilders
							.patch("/Users/a510f190-aa6d-47b3-924b-4bd3ad6a50e6")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(patchOp)))
						.andExpect(status().isNotFound());
	}
	

	//-----------------------------------------------------------
	//  Delete
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("RFC7644 - Suppression d'un compte utilisateur (Ok)")
	void testDelete() throws Exception {
        
		mockMvc.perform(MockMvcRequestBuilders
							.delete("/Users/" + user1.getId())
							.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isNoContent());
	}

	
	@Test
	@DisplayName("RFC7644 - Suppression d'un compte utilisateur (NotFound)")
	void testDeleteNotFound() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
							.delete("/Users/a510f190-aa6d-47b3-924b-4bd3ad6a50e6")
							.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isNotFound());
	}
	
}
