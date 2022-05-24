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
public class ScimManager {

	/**
	 * value The "id" of the SCIM resource representing the user's manager.
	 * RECOMMENDED.
	 */
	private String id;

	/**
	 * The URI of the SCIM resource representing the User's manager. RECOMMENDED.
	 */
	@JsonProperty(value = "$ref")
	private String ref;

	/**
	 * The displayName of the user's manager. This attribute is OPTIONAL, and
	 * mutability is "readOnly".
	 */
	private String displayName;
}
