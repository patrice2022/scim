package fr.pay.scim.serveur.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(insertable = true, updatable = false)
	private String id;

//	private String externalId;
	
	@Column(unique = true, nullable = false)
	private String userName;
	
//	private String displayName;
//	
//	private String firstName;
//
//	private String lastName;
//
//	private String email;
//	
//	private String title;
	
	
	
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_group")
//	private Set<GroupEntity> groups = new HashSet<>(); 
//	
//	@Basic(optional = false)
//	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP Default CURRENT_TIMESTAMP")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date created;
//	
//	@Basic(optional = false)
//	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP Default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date lastModified = new Date();
	

}
