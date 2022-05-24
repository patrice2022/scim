package fr.pay.scim.serveur.endpoint.entity.group;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScimMember {

	/**
	 * Identifier of the member of this Group.
	 */
	private String value;

	/**
	 * The URI corresponding to a SCIM resource that is a member of this Group.
	 */
	@JsonProperty(value = "$ref")
	private String ref;

	/**
	 * A label indicating the type of resource, e.g., 'User' or 'Group'.
	 */
	private String type;
}
