package fr.pay.scim.serveur.endpoint.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimAddress {

	/**
	 * The full mailing address, formatted for display or use with a mailing label.
	 * This attribute MAY contain newlines.
	 * 
	 * <pre>
	 * 		"name" : "formatted",
   	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The full mailing address, formatted for
	 * 			display or use with a mailing label.  This attribute MAY contain
	 * 			newlines.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String formatted;

	/**
	 * alse, "description" : "The full street address component, which may include
	 * house number, street name, P.O. box, and multi-line extended street address
	 * information. This attribute MAY contain newlines.
	 * 
	 * <pre>
	 * 		"name" : "streetAddress",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The full street address component,
	 * 			which may include house number, street name, P.O. box, and multi-line
	 * 			extended street address information.  This attribute MAY contain
	 * 			newlines.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String streetAddress;

	/**
	 * The city or locality component.
	 * 
	 * <pre>
	 * 		"name" : "locality",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The city or locality component.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String locality;

	/**
	 * The state or region component.
	 * 
	 * <pre>
	 * "name" : "region",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The state or region component.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String region;

	/**
	 * The zip code or postal code component.
	 * 
	 * <pre>
	 * 		"name" : "postalCode",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The zip code or postal code component.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String postalCode;

	/**
	 * The country name component.
	 * 
	 * <pre>
	 * 		"name" : "country",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The country name component.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String country;

	/**
	 * A label indicating the attribute's function, e.g., 'work' or 'home'.
	 * 
	 * <pre>
	 * 		"name" : "type",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "A label indicating the attribute's
	 * 			function, e.g., 'work' or 'home'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"canonicalValues" : [
	 * 			"work",
	 * 			"home",
	 * 			"other"
	 * </pre>
	 */
	private String type;
	
	
	/**
	 *   @Attribute(description = "A Boolean value indicating the 'primary' " +
      "or preferred attribute value for this attribute, e.g., the " +
      "preferred address. The primary attribute value 'true' MUST appear " +
      "no more than once.",
      isRequired = false,
      mutability = AttributeDefinition.Mutability.READ_WRITE,
      returned = AttributeDefinition.Returned.DEFAULT)
	 */
//	private Boolean primary;
}
