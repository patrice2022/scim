package fr.pay.scim.serveur.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimUserGroup {

	/**
	 * The identifier of the User's group.
	 */
	private String value;

	/**
	 * The URI of the corresponding 'Group' resource to which the user belongs.
	 */
	@JsonProperty(value = "$ref")
	private String ref;

	/**
	 * A human-readable name, primarily used for display purposes. READ-ONLY.
	 */
	private String display;

	/**
	 * A label indicating the attribute's function, e.g., 'direct' or 'indirect'.
	 */
	private String type;

}
