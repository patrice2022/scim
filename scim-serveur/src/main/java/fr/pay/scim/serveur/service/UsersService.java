package fr.pay.scim.serveur.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.endpoint.entity.ScimUser;
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
		
		return scimUser;
	}

}
