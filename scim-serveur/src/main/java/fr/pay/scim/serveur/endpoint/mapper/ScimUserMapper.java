package fr.pay.scim.serveur.endpoint.mapper;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.endpoint.entity.user.ScimEmail;
import fr.pay.scim.serveur.endpoint.entity.user.ScimName;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.endpoint.entity.user.ScimUserMeta;
import fr.pay.scim.serveur.service.entity.User;

@Service
public class ScimUserMapper {


	private ScimUserMeta mapperMeta(User user, String location) {
		ScimUserMeta scimMeta = new ScimUserMeta();
		scimMeta.setCreated(user.getCreated());
		scimMeta.setLastModified(user.getLastModified());
		scimMeta.setLocation(location);
		scimMeta.setVersion(null);
		return scimMeta;
	}
	
	private ScimName mapperName(User user) {
		ScimName scimName = null;
		if (user.getFamilyName() != null || user.getGivenName() != null) {
			scimName = new ScimName();
			scimName.setFormatted(null);
			scimName.setFamilyName(user.getFamilyName());
			scimName.setMiddleName(null);
			scimName.setGivenName(user.getGivenName());
			scimName.setHonorificPrefix(null);
			scimName.setHonorificSuffix(null);
		}
		return scimName;
	}
	
	private List<ScimEmail> mapperEmail(User user) {
		List<ScimEmail> emails = null;
		if (user.getEmail() != null || user.getEmail() != null) {
			ScimEmail scimEmail = new ScimEmail();
			scimEmail.setValue(user.getEmail());
			scimEmail.setPrimary(true);
			scimEmail.setType("work");
			scimEmail.setDisplay(null);
			emails = Arrays.asList(scimEmail);
		}
		return emails;
	}
	
	
	public ScimUser mapper(User user, String location) {
		ScimUser scimUser = new ScimUser();
		scimUser.setId(user.getId());
		scimUser.setExternalId(user.getExternalId());
		scimUser.setMeta(mapperMeta(user, location));
 		scimUser.setSchemas(Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:User"));
		scimUser.setUserName(user.getUserName());
		scimUser.setName(mapperName(user));
		scimUser.setDisplayName(user.getDisplayName());
		scimUser.setTitle(user.getTitle());
		scimUser.setActive(true);
		scimUser.setEmails(mapperEmail(user));
		return scimUser;
	}
	
	
	

	public User mapper(ScimUser scimUser) {
		User user = new User();
		user.setId(scimUser.getId());
		user.setExternalId(scimUser.getExternalId());	
		user.setUserName(scimUser.getUserName());
		if (scimUser.getName() != null) {
			user.setGivenName(scimUser.getName().getGivenName());
			user.setFamilyName(scimUser.getName().getFamilyName());
		}
		user.setDisplayName(scimUser.getDisplayName());
		user.setTitle(scimUser.getTitle());
		user.setActive(scimUser.getActive());
		if (scimUser.getEmails() != null) {
			ScimEmail scimEmail = scimUser.getEmails().stream()
								.filter(e -> "work".equalsIgnoreCase(e.getType()))
								.findFirst()
								.orElse(null);
			if (scimEmail != null) {
				user.setEmail(scimEmail.getValue());
			}				
		}
		return user;
	}

}
