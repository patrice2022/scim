package fr.pay.scim.serveur.endpoint.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import fr.pay.scim.serveur.endpoint.entity.user.ScimEmail;
import fr.pay.scim.serveur.endpoint.entity.user.ScimName;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUserMeta;
import fr.pay.scim.serveur.service.entity.user.User;

class ScimUserMapperTest {

	
	private ScimUserMapper mapper = new ScimUserMapper();
	
	
	
	@Test
	void scimuUserToUser() {
		
		ScimName scimName = new ScimName();
		scimName.setFamilyName("lastName");
		scimName.setGivenName("firstName");
		
		ScimEmail scimEmail = new ScimEmail();
		scimEmail.setType("work");
		scimEmail.setValue("value@societe.com");
		
		ScimUserMeta scimUserMeta = new ScimUserMeta();
		scimUserMeta.setCreated(new Date());
		scimUserMeta.setLastModified(new Date());
		
		ScimUser scimUser = new ScimUser();
		scimUser.setId(UUID.randomUUID().toString());
		scimUser.setExternalId(UUID.randomUUID().toString());
		scimUser.setMeta(scimUserMeta);
		scimUser.setUserName("username");
		scimUser.setName(scimName);
		scimUser.setDisplayName("displayName");
		scimUser.setTitle("title");
		scimUser.setActive(true);
		scimUser.setEmails(Arrays.asList(scimEmail));
	
		User user = mapper.mapper(scimUser);
		
		assertNotNull(user);
		assertEquals(scimUser.getId(), user.getId());
		assertEquals(scimUser.getExternalId(), user.getExternalId());
		assertEquals(scimUser.getUserName(), user.getUserName());
		assertEquals(scimName.getFamilyName(), user.getFamilyName());
		assertEquals(scimName.getGivenName(), user.getGivenName());
		assertEquals(scimUser.getDisplayName(), user.getDisplayName());
		assertEquals(scimUser.getTitle(), user.getTitle());
		assertEquals(scimUser.getActive(), user.getActive());
		assertEquals(scimEmail.getValue(), user.getEmail());
		
		assertNotEquals(scimUserMeta.getCreated(), user.getCreated());
		assertNotEquals(scimUserMeta.getLastModified(), user.getLastModified());
	}

	
	@Test
	void userToscimuUser() {
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setExternalId(UUID.randomUUID().toString());
		user.setCreated(new Date());
		user.setLastModified(new Date());
		user.setUserName("username");
		user.setFamilyName("lastName");
		user.setGivenName("firstName");
		user.setDisplayName("displayName");
		user.setTitle("title");
		user.setActive(true);
		user.setEmail("value@societe.com");
		
		ScimUser scimUser = mapper.mapper(user, "location");
		
		assertNotNull(scimUser);
		
		assertEquals(user.getId(), scimUser.getId());
		assertEquals(user.getExternalId(), scimUser.getExternalId());
		assertEquals(user.getUserName(), scimUser.getUserName());
		assertEquals(user.getDisplayName(), scimUser.getDisplayName());
		assertEquals(user.getTitle(), scimUser.getTitle());
		assertEquals(user.getActive(), scimUser.getActive());
		
		assertNotNull(scimUser.getMeta());
		assertEquals(user.getCreated(), scimUser.getMeta().getCreated());
		assertEquals(user.getLastModified(), scimUser.getMeta().getLastModified());
		assertEquals("location", scimUser.getMeta().getLocation());
		
		assertEquals(Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:User"), scimUser.getSchemas());
		
		assertNotNull(scimUser.getName());
		assertEquals(user.getFamilyName(), scimUser.getName().getFamilyName());
		assertEquals(user.getGivenName(), scimUser.getName().getGivenName());
		
		assertNotNull(scimUser.getEmails());
		assertEquals(1, scimUser.getEmails().size());
		assertEquals(user.getEmail(), scimUser.getEmails().get(0).getValue());
		assertEquals("work", scimUser.getEmails().get(0).getType());
		
	}

}
