package fr.pay.scim.serveur.endpoint;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.pay.scim.serveur.endpoint.advice.UserExceptionAdvice;
import fr.pay.scim.serveur.service.UsersService;
import fr.pay.scim.serveur.service.entity.user.User;

@SpringBootTest
public class UsersEndPointTest {

	private MockMvc mockMvc;
	
	@Mock
	UsersService usersService;
	
	@InjectMocks
	UsersEndPoint userEndPoint;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(userEndPoint)
				.setControllerAdvice(new UserExceptionAdvice())
				.build();
	}
	
	
	@Test
	void testGet200() throws Exception {
		
		User user = new User();
		user.setId("0000");
		user.setExternalId("9999");
		user.setUserName("johndo");
		user.setFamilyName("Do");
		user.setGivenName("John");
		user.setDisplayName("John Do");
		user.setTitle("Boulanger");
		user.setEmail("do.john@johndo.local");
		user.setCreated(new Date(1653569543794L));
		user.setLastModified(new Date(1653569543794L + 500L));
		user.setActive(false);

		Mockito.when(usersService.read(user.getId())).thenReturn(user);
				
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users/0000")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("id", is(scimUser.getId())))
//				.andExpect(jsonPath("meta.resourceType", is("User")))
//				.andExpect(jsonPath("meta.created", is("2022-05-26T12:52:23.794Z")))
//				.andExpect(jsonPath("meta.lastModified", is("2022-05-26T12:52:24.294Z")))
//				.andExpect(jsonPath("schemas[0]", is("urn:ietf:params:scim:schemas:core:2.0:User")))
//				.andExpect(jsonPath("userName", is(scimUser.getUserName())))
//				.andExpect(jsonPath("name.familyName", is(scimName.getFamilyName())))
//				.andExpect(jsonPath("name.givenName", is(scimName.getGivenName())))
//				.andExpect(jsonPath("displayName", is(scimUser.getDisplayName())))
//				.andExpect(jsonPath("active", is(false)))
//				.andExpect(jsonPath("emails[0].primary", is(scimEmail.getPrimary())))
//				.andExpect(jsonPath("emails[0].value", is(scimEmail.getValue())))
//				.andExpect(jsonPath("emails[0].type", is(scimEmail.getType())))
//				.andExpect(jsonPath("emails[0].display", is(scimEmail.getDisplay())));
	}

	@Test
	void testGet404() throws Exception {
		
		Mockito.when(usersService.read("0000")).thenReturn(null);
				
		// {"schemas":["urn:ietf:params:scim:api:messages:2.0:Error"],"status":"404","detail":"User not found."}
		mockMvc.perform(MockMvcRequestBuilders
					.get("/Users/0000")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
}
