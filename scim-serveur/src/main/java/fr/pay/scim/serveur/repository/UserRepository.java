package fr.pay.scim.serveur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.pay.scim.serveur.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	@Query("SELECT u FROM UserEntity u WHERE u.userName = :username")
	UserEntity findByUsername(String username);
}
