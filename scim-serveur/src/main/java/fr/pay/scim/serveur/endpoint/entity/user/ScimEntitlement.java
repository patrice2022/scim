package fr.pay.scim.serveur.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimEntitlement {

	/**
	 * A list of entitlements for the User that represent a thing the User has.
	 */
	private String value;

	/**
	 * A human-readable name, primarily used for display purposes. READ-ONLY.
	 */
	private String display;

	/**
	 * A label indicating the attribute's function.
	 */
	private String type;

	/**
	 * A Boolean value indicating the 'primary' or preferred attribute value for
	 * this attribute. The primary attribute value 'true' MUST appear no more than
	 * once.
	 */
	private Boolean primary;
}
