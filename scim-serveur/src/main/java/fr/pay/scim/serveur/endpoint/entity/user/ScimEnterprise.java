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
public class ScimEnterprise {

	/**
	 * A string identifier, typically numeric or alphanumeric, assigned to a person,
	 * typically based on order of hire or association with an organization.
	 */
	private String employeeNumber;

	/**
	 * Identifies the name of a cost center.
	 */
	private String costCenter;

	/**
	 * Identifies the name of an organization.
	 */
	private String organization;

	/**
	 * Identifies the name of a department.
	 */
	private String department;

	/**
	 * The user's manager. A complex type that optionally allows service providers
	 * to represent organizational hierarchy by referencing the "id" attribute of
	 * another User.
	 */
	private ScimManager manager;
}
