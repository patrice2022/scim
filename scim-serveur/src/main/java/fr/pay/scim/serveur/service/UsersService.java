package fr.pay.scim.serveur.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.endpoint.entity.user.ScimEmail;
import fr.pay.scim.serveur.endpoint.entity.user.ScimName;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUserMeta;
import fr.pay.scim.serveur.service.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {

	private Map<String, User> users = new HashMap<>();
	

	public UsersService() {
		super();
		
		User user = new User();
		user.setId("0d3d948d-a671-49af-a5b5-f740f08bc8f3");
		user.setExternalId("0000012345");
		user.setUserName("greenzebra547");
		user.setGivenName("Amanda");
		user.setFamilyName("SMITH");
		user.setDisplayName("Amanda SMITH");
		user.setTitle("Architecte technique");
		user.setActive(true);
		user.setEmail("amanda.smith@example.com");
		
		users.put(user.getId(), user);
	}


	public User read(String id) {
		return users.get(id);
	}

	
	public User create(User user) {
		
		String id = UUID.randomUUID().toString();
		

//		user.setId(id);
//		user.setExternalId(scimUser.getExternalId());
//		user.setUsername(scimUser.getUserName());
//		if (scimUser.getName() != null) {
//			user.setFirstName(scimUser.getName().getGivenName());
//			user.setLastName(scimUser.getName().getFamilyName());
//		}
//		user.setDisplayName(scimUser.getDisplayName());
		users.put(id, user);
		
//		scimUser.setId(id);
//		
//		ScimUserMeta scimMeta = new ScimUserMeta();
//		scimMeta.setCreated(user.getCreateTimeStamp());
//		scimMeta.setLastModified(user.getLastModified());
////		scimMeta.setLocation(location + "/" + id);
////		scimMeta.setVersion(null);                               // -> a completer
//		scimUser.setMeta(scimMeta);
//		
//		ScimName scimName = new ScimName();
//		scimName.setFamilyName(user.getLastName());
//		scimName.setGivenName(user.getFirstName());
////		scimName.setFormatted(null);
////		scimName.setHonorificPrefix(null);
////		scimName.setMiddleName(null);
////		scimName.setHonorificSuffix(null);
//		scimUser.setName(scimName);
		
		return user;
	}
}
