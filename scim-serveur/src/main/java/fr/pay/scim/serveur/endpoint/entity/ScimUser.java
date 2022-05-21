package fr.pay.scim.serveur.endpoint.entity;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
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
	private String externalId;
}
