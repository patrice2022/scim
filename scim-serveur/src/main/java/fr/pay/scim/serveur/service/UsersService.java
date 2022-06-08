package fr.pay.scim.serveur.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.repository.UserRepository;
import fr.pay.scim.serveur.repository.entity.UserEntity;
import fr.pay.scim.serveur.service.entity.user.User;
import fr.pay.scim.serveur.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {

	@Autowired
	private UserRepository repository;

	private UserMapper userMapper = new UserMapper();
	
	
	
	public User read(String id) {
		UserEntity userEntity = repository
				.findById(id).orElse(null);
		return userMapper.mapper(userEntity);
	}
	
	
	public User readByUsername(String username) {
		UserEntity userEntity = repository
				.findByUsername(username);
		if (userEntity == null) {
			return null;
		} else {
			return userMapper.mapper(userEntity);
		}
		
	}


	public User create(User user) {
		return userMapper.mapper(repository.save(userMapper.mapper(user)));
	}
	
	
	public User update(String id, User user) {
		user.setId(id);
		return userMapper.mapper(repository.save(userMapper.mapper(user)));
	}
	
	
	public void delete(String id) {
		repository.deleteById(id);
	}


	public List<User> all() {
		List<User> retour = repository
				.findAll()
				.stream()
				.map(u -> userMapper.mapper(u)).collect(Collectors.toList());
		return retour;
	}
	
}
