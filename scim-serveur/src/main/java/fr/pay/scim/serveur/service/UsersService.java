package fr.pay.scim.serveur.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.endpoint.entity.ScimUser;
import fr.pay.scim.serveur.endpoint.entity.ScimUserMeta;
import fr.pay.scim.serveur.service.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {

	private Map<String, User> users = new HashMap<>();
	

	public UsersService() {
		super();
		
		User user = new User();
		user.setId("0000");
		user.setExternalId("123456");
		user.setUsername("jbtoto");
		
		users.put("0000", user);
	}


	public ScimUser read(String id) {
		
		User user = users.get(id);
		
		if (user == null) {
			return null;
		}
		
		ScimUser scimUser = new ScimUser();
		scimUser.setId(user.getId());
		scimUser.setExternalId(user.getExternalId());
		scimUser.setUserName(user.getUsername());
		
		ScimUserMeta scimMeta = new ScimUserMeta();
		scimMeta.setCreated(user.getCreateTimeStamp());
		scimMeta.setLastModified(user.getLastModified());
//		scimMeta.setLocation(location + "/" + id);
//		scimMeta.setVersion(null);                               // -> a completer
		scimUser.setMeta(scimMeta);

		
		return scimUser;
	}

	
	public ScimUser create(ScimUser scimUser) {
		
		String id = UUID.randomUUID().toString();
		
		User user = new User();
		user.setId(id);
		user.setExternalId(scimUser.getExternalId());
		user.setUsername(scimUser.getUserName());
		users.put(id, user);
		
		scimUser.setId(id);
		
		ScimUserMeta scimMeta = new ScimUserMeta();
		scimMeta.setCreated(user.getCreateTimeStamp());
		scimMeta.setLastModified(user.getLastModified());
//		scimMeta.setLocation(location + "/" + id);
//		scimMeta.setVersion(null);                               // -> a completer
		scimUser.setMeta(scimMeta);
		
		return scimUser;
	}
}
