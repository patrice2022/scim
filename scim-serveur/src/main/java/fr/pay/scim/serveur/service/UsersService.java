package fr.pay.scim.serveur.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.service.entity.user.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {

	private Map<String, User> users = new HashMap<>();

	
	public User read(String id) {
		return users.get(id);
	}

	public User create(User user) {
		user.setId(UUID.randomUUID().toString());
		users.put(user.getId(), user);
		return read(user.getId());
	}
	
	
	public User update(String id, User user) {
		users.put(id, user);
		return user;
	}
	
	
	public void delete(String id) {
		users.remove(id);
	}
	
}
