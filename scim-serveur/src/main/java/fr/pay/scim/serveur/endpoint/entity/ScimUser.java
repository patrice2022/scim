package fr.pay.scim.serveur.endpoint.entity;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class ScimUser {

	/**
	 * A unique identifier for a SCIM resource as defined by the service provider.
	 * Each representation of the resource MUST include a non-empty "id" value. This
	 * identifier MUST be unique across the SCIM service provider's entire set of
	 * resources. It MUST be a stable, non-reassignable identifier that does not
	 * change when the same resource is returned in subsequent requests. The value
	 * of the "id" attribute is always issued by the service provider and MUST NOT
	 * be specified by the client. The string "bulkId" is a reserved keyword and
	 * MUST NOT be used within any unique identifier value. 
	 * 
	 * The attribute characteristics are "caseExact" as "true", a mutability of "readOnly", and a
	 * "returned" characteristic of "always".
	 */
	@Pattern(regexp="^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$", message="id Invalide")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String id;
	
	/**
	 * A String that is an identifier for the resource as defined by the
	 * provisioning client. The "externalId" may simplify identification of a
	 * resource between the provisioning client and the service provider by allowing
	 * the client to use a filter to locate the resource with an identifier from the
	 * provisioning domain, obviating the need to store a local mapping between the
	 * provisioning domain's identifier of the resource and the identifier used by
	 * the service provider. Each resource MAY include a non-empty "externalId"
	 * value. The value of the "externalId" attribute is always issued by the
	 * provisioning client and MUST NOT be specified by the service provider. The
	 * service provider MUST always interpret the externalId as scoped to the
	 * provisioning domain. While the server does not enforce uniqueness, it is
	 * assumed that the value's uniqueness is controlled by the client setting the
	 * value.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String externalId;
	
	/**
	 * A complex attribute containing resource metadata. All "meta" sub-attributes
	 * are assigned by the service provider (have a "mutability" of "readOnly"), and
	 * all of these sub-attributes have a "returned" characteristic of "default".
	 * 
	 * This attribute SHALL be ignored when provided by clients.
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private ScimUserMeta meta;

	/**
	 * SCIM provides a resource type for "User" resources. The core schema for
	 * "User" is identified using the following schema URI:
	 * "urn:ietf:params:scim:schemas:core:2.0:User".
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:schemas:core:2.0:User");

	/**
	 * A service provider's unique identifier for the user, typically used by the
	 * user to directly authenticate to the service provider. Often displayed to the
	 * user as their unique identifier within the system (as opposed to "id" or
	 * "externalId", which are generally opaque and not user-friendly identifiers).
	 * Each User MUST include a non-empty userName value. This identifier MUST be
	 * unique across the service provider's entire set of Users. This attribute is
	 * REQUIRED and is case insensitive.
	 * 
	 * <pre>
	 * 		"name" : "userName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Unique identifier for the User, typically
	 * 			used by the user to directly authenticate to the service provider.
	 * 			Each User MUST include a non-empty userName value.  This identifier
	 * 			MUST be unique across the service provider's entire set of Users.
	 * 			REQUIRED.",
	 * 		"required" : true,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "server"
	 * </pre>
	 */
	@NotEmpty
	@Pattern(regexp="^[0-9a-z]{8}$", message="username Invalide")
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private String userName;
	
	/**
	 * The components of the user's name. Service providers MAY return just the full
	 * name as a single string in the formatted sub-attribute, or they MAY return
	 * just the individual component attributes using the other sub-attributes, or
	 * they MAY return both. If both variants are returned, they SHOULD be
	 * describing the same name, with the formatted name indicating how the
	 * component attributes should be combined.
	 * 
	 * <pre>
	 * 		"name" : "name",
	 * 		"type" : "complex",
	 * 		"multiValued" : false,
	 * 		"description" : "The components of the user's real name.
	 * 			Providers MAY return just the full name as a single string in the
	 * 			formatted sub-attribute, or they MAY return just the individual
	 * 			component attributes using the other sub-attributes, or they MAY
	 * 			return both.  If both variants are returned, they SHOULD be
	 * 			describing the same name, with the formatted name indicating how the
	 * 			component attributes should be combined.",
	 * 		"required" : false
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	@Schema(accessMode = Schema.AccessMode.READ_WRITE)
	private ScimName name;
}
