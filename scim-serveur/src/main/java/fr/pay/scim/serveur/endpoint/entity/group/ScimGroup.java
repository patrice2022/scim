package fr.pay.scim.serveur.endpoint.entity.group;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimGroup {

	private String id;
	
	/**
	 * A human-readable name for the Group. REQUIRED.
	 */
	private String displayName;
	
	/**
	 * A list of members of the Group.
	 */
	private List<ScimMember> members;
	
	
	private ScimGroupMeta meta;
	
	
	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:Group");
	
}
