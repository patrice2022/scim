package fr.pay.scim.test.scim.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimPhoneNumber {

	/**
	 * Phone number of the User.
	 * 
	 * <pre>
	 * 		"name" : "value",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Phone number of the User.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String value;

	/**
	 * A human-readable name, primarily used for display purposes.
	 * 
	 * READ-ONLY.
	 * 
	 * <pre>
	 * 		"name" : "display",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A human-readable name, primarily used
	 * 			for display purposes.  READ-ONLY.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String display;

	/**
	 * A label indicating the attribute's function, e.g., 'work', 'home', 'mobile'.
	 * 
	 * <pre>
	 * 		"name" : "type",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A label indicating the attribute's
	 * 			function, e.g., 'work', 'home', 'mobile'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"canonicalValues" : [
	 * 			"work",
	 * 			"home",
	 * 			"mobile",
	 * 			"fax",
	 * 			"pager",
	 * 			"other"	
	 * 		],
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String type;

	/**
	 * A Boolean value indicating the 'primary' or preferred attribute value for
	 * this attribute, e.g., the preferred phone number or primary phone number. The
	 * primary attribute value 'true' MUST appear no more than once.
	 * 
	 * <pre>
	 * 
	 * 		"name" : "primary",
	 * 		"type" : "boolean",
	 * 		"multiValued" : false,
	 * 		"description" : "A Boolean value indicating the 'primary'
	 * 			or preferred attribute value for this attribute, e.g., the preferred
	 * 			phone number or primary phone number.  The primary attribute value
	 * 			'true' MUST appear no more than once.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default"
	 * </pre>
	 */
	private Boolean primary;
}
