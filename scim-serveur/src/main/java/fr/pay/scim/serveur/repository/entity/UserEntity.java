package fr.pay.scim.serveur.repository.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Column(unique = true, nullable = true)
	private String externalId;
	
	@Column(unique = true, nullable = false)
	private String userName;
	
	@Column
	private String displayName;
	
	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;
	
	@Column
	private String title;
	
	
	
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_group")
//	private Set<GroupEntity> groups = new HashSet<>(); 
//	
	
	@Basic(optional = false)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP Default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Basic(optional = false)
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP Default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified = new Date();
	

}
