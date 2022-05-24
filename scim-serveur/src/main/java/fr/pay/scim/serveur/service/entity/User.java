package fr.pay.scim.serveur.service.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private String id;

	private String externalId;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String displayName;
	
	private String title;
	
	private String email;
	
	private Date createTimeStamp = new Date();
	
	private Date lastModified = new Date();
		
}
