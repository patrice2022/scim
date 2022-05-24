package fr.pay.scim.serveur.endpoint.entity.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ScimUserMeta {

	/**
	 * The name of the resource type of the resource. This attribute has a
	 * mutability of "readOnly" and "caseExact" as "true".
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String resourceType = "User";

	/**
	 * The "DateTime" that the resource was added to the service provider. This
	 * attribute MUST be a DateTime.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Date created;

	/**
	 * The most recent DateTime that the details of this resource were updated at
	 * the service provider. If this resource has never been modified since its
	 * initial creation, the value MUST be the same as the value of "created".
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Date lastModified;

	/**
	 * The URI of the resource being returned. This value MUST be the same as the
	 * "Content-Location" HTTP response header
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String location;

	/**
	 * The version of the resource being returned. This value must be the same as
	 * the entity-tag (ETag) HTTP response header (see Sections 2.1 and 2.3 of
	 * [RFC7232]). This attribute has "caseExact" as "true". Service provider
	 * support for this attribute is optional and subject to the service provider's
	 * support for versioning (see Section 3.14 of [RFC7644]). If a service provider
	 * provides "version" (entity-tag) for a representation and the generation of
	 * that entity-tag does not satisfy all of the characteristics of a strong
	 * validator (see Section 2.1 of [RFC7232]), then the origin server MUST mark
	 * the "version" (entity-tag) as weak by prefixing its opaque value with "W/"
	 * (case sensitive).
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String version;

}
