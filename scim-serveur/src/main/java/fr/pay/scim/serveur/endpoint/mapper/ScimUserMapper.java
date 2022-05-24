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

	public ScimUser mapper(User user, String location) {
		
		ScimUser scimUser = new ScimUser();
		
		scimUser.setId(user.getId());
		
		scimUser.setExternalId(user.getExternalId());
		
		ScimUserMeta scimMeta = new ScimUserMeta();
		scimMeta.setCreated(user.getCreateTimeStamp());
		scimMeta.setLastModified(user.getLastModified());
		scimMeta.setLocation(location);
		scimMeta.setVersion(null);                               // -> a completer
		scimUser.setMeta(scimMeta);

		scimUser.setSchemas(Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:User"));
		
		scimUser.setUserName(user.getUsername());
		
		if (user.getLastName() != null || user.getFirstName() != null) {
			ScimName scimName = new ScimName();
			scimName.setFormatted(null);                               // -> a completer
			scimName.setFamilyName(user.getLastName());
			scimName.setMiddleName(null);                              // -> a completer
			scimName.setGivenName(user.getFirstName());
			scimName.setHonorificPrefix(null);                         // -> a completer
			scimName.setHonorificSuffix(null);                         // -> a completer
			scimUser.setName(scimName);
		}
		scimUser.setDisplayName(user.getDisplayName());
		
//		private String nickName;
//		private URI profileUrl;
		
		scimUser.setTitle(user.getTitle());
		
//		private String userType;
//		private String preferredLanguage;
//		private String locale;
//		private String timezone;
		
		scimUser.setActive(true);
		
//		private String password;

		if (user.getEmail() != null || user.getEmail() != null) {
			ScimEmail scimEmail = new ScimEmail();
			scimEmail.setValue(user.getEmail());
			scimEmail.setPrimary(true);
			scimEmail.setType("work");
			scimEmail.setDisplay(null);                                // -> a completer
			scimUser.setEmails(Arrays.asList(scimEmail));
		}
		
//		private List<ScimPhoneNumber> phoneNumbers;
//		private List<ScimIms> ims;
//		private List<ScimPhoto> photos;
//		private List<ScimAddress> addresses;
//		private List<ScimUserGroup> groups;
//		private List<ScimEntitlement> entitlements;
//		private List<ScimRole> roles;
//		private List<ScimX509Certificate> x509Certificates;

		return scimUser;
	}
	
	
	
	
	
	public User mapper(ScimUser scimUser) {
		
		User user = new User();
		
		// id;
		user.setId(scimUser.getId());
			
		// externalId;
		user.setExternalId(scimUser.getExternalId());	
		
		// userName;
		user.setUsername(scimUser.getUserName());
		
		//	displayName;
		user.setDisplayName(scimUser.getDisplayName());

		// firstName, lastName
		ScimName scimName = scimUser.getName();
		if (scimName != null) {
			user.setFirstName(scimName.getGivenName());
			user.setLastName(scimName.getFamilyName());
		}

		// email
		List<ScimEmail> scimEmails = scimUser.getEmails();
		if (scimEmails != null) {
			ScimEmail scimEmail = scimEmails.stream()
								.filter(e -> "work".equalsIgnoreCase(e.getType()))
								.findFirst()
								.orElse(null);
			if (scimEmail != null) {
				user.setEmail(scimEmail.getValue());
			}				
		}
		
		// title;
		user.setTitle(scimUser.getTitle());
		
		// created;
		
		// lastModified;

		
		return user;
	}

}
