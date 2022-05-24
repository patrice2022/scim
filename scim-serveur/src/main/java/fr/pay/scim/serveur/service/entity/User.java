package fr.pay.scim.serveur.service.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private String id;	
	
	private String externalId;
	
	private Date created = new Date();		
	
	private Date lastModified = new Date();
	
	private String userName;	
	
	private String familyName;
	
	private String givenName;
	
	private String displayName;
	
	private String title;
	
	private Boolean active;
	
	private String email;
	

}
