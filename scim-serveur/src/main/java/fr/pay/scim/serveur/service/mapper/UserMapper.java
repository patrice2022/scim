package fr.pay.scim.serveur.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.repository.entity.UserEntity;
import fr.pay.scim.serveur.service.entity.user.User;

public class UserMapper {

	
	public User mapper(UserEntity userEntity) {
		ModelMapper modelMapper  = new ModelMapper();
		return modelMapper.map(userEntity, User.class);
	}
	
	
	public UserEntity mapper(User user) {
		ModelMapper modelMapper  = new ModelMapper();
		UserEntity entity = modelMapper.map(user, UserEntity.class);
		
//		entity.setCreated(new Date());
		return entity;
	}
	
}
