package fr.pay.scim.test.scim.user;

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
	private ScimName name;
	
	/**
	 * The name of the user, suitable for display to end-users. Each user returned
	 * MAY include a non-empty displayName value. The name SHOULD be the full name
	 * of the User being described, if known (e.g., "Babs Jensen" or "Ms. Barbara J
	 * Jensen, III") but MAY be a username or handle, if that is all that is
	 * available (e.g., "bjensen"). The value provided SHOULD be the primary textual
	 * label by which this User is normally displayed by the service provider when
	 * presenting it to end-users.
	 * 
	 * <pre>
	 * 		"name" : "displayName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The name of the User, suitable for display
	 * 			to end-users.  The name SHOULD be the full name of the User being
	 * 			described, if known.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String displayName;

	
	/**
	 * The casual way to address the user in real life, e.g., "Bob" or "Bobby"
	 * instead of "Robert". This attribute SHOULD NOT be used to represent a User's
	 * username (e.g., bjensen or mpepperidge).
	 * 
	 * <pre>
	 * 		"name" : "nickName",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The casual way to address the user in real
	 * 			life, e.g., 'Bob' or 'Bobby' instead of 'Robert'.  This attribute
	 * 			SHOULD NOT be used to represent a User's username (e.g., 'bjensen' or
	 * 			'mpepperidge').",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private String nickName;

	/**
	 * A URI that is a uniform resource locator (as defined in Section 1.1.3 of
	 * [RFC3986]) and that points to a location representing the user's online
	 * profile (e.g., a web page). URIs are canonicalized per Section 6.2 of
	 * [RFC3986].
	 * 
	 * <pre>
	 * 		"name" : "profileUrl",
	 * 		"type" : "reference",
	 * 		"referenceTypes" : ["external"],
	 * 		"multiValued" : false,
	 * 		"description" : "A fully qualified URL pointing to a page
	 * 			representing the User's online profile.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private URI profileUrl;

	/**
	 * The user's title, such as "Vice President".
	 * 
	 * <pre>
	 * 		"name" : "title",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The user's title, such as
	 * 			"Vice President.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private String title;

	/**
	 * Used to identify the relationship between the organization and the user.
	 * Typical values used might be "Contractor", "Employee", "Intern", "Temp",
	 * "External", and "Unknown", but any value may be used.
	 * 
	 * <pre>
	 * 		"name" : "userType",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Used to identify the relationship between
	 * 				the organization and the user.  Typical values used might be
	 * 				'Contractor', 'Employee', 'Intern', 'Temp', 'External', and
	 * 				'Unknown', but any value may be used.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private String userType;

	/**
	 * Indicates the user's preferred written or spoken languages and is generally
	 * used for selecting a localized user interface. The value indicates the set of
	 * natural languages that are preferred. The format of the value is the same as
	 * the HTTP Accept-Language header field (not including "Accept-Language:") and
	 * is specified in Section 5.3.5 of [RFC7231]. The intent of this value is to
	 * enable cloud applications to perform matching of language tags [RFC4647] to
	 * the user's language preferences, regardless of what may be indicated by a
	 * user agent (which might be shared), or in an interaction that does not
	 * involve a user (such as in a delegated OAuth 2.0 [RFC6749] style interaction)
	 * where normal HTTP Accept-Language header negotiation cannot take place.
	 * 
	 * <pre>
	 * 		"name" : "preferredLanguage",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Indicates the User's preferred written or
	 * 			spoken language.  Generally used for selecting a localized user
	 * 			interface; e.g., 'en_US' specifies the language English and country
	 * 			US.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private String preferredLanguage;

	/**
	 * Used to indicate the User's default location for purposes of localizing such
	 * items as currency, date time format, or numerical representations. A valid
	 * value is a language tag as defined in [RFC5646]. Computer languages are
	 * explicitly excluded.
	 * 
	 * A language tag is a sequence of one or more case-insensitive sub-tags, each
	 * separated by a hyphen character ("-", %x2D). For backward compatibility,
	 * servers MAY accept tags separated by an underscore character ("_", %x5F). In
	 * most cases, a language tag consists of a primary language sub-tag that
	 * identifies a broad family of related languages (e.g., "en" = English) and
	 * that is optionally followed by a series of sub-tags that refine or narrow
	 * that language's range (e.g., "en-CA" = the variety of English as communicated
	 * in Canada). Whitespace is not allowed within a language tag. Example tags
	 * include:
	 * 
	 * fr, en-US, es-419, az-Arab, x-pig-latin, man-Nkoo-GN
	 * 
	 * See [RFC5646] for further information.
	 * 
	 * <pre>
	 * 		"name" : "locale",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "Used to indicate the User's default location
	 * 			for purposes of localizing items such as currency, date time format, or
	 * 			numerical representations.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private String locale;

	/**
	 * The User's time zone, in IANA Time Zone database format [RFC6557], also known
	 * as the "Olson" time zone database format [Olson-TZ] (e.g.,
	 * "America/Los_Angeles").
	 * 
	 * <pre>
	 * 		"name" : "timezone",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The User's time zone in the 'Olson' time zone
	 * 			database format, e.g., 'America/Los_Angeles'.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private String timezone;

	/**
	 * A Boolean value indicating the user's administrative status. The definitive
	 * meaning of this attribute is determined by the service provider. As a typical
	 * example, a value of true implies that the user is able to log in, while a
	 * value of false implies that the user's account has been suspended.
	 * 
	 * <pre>
	 * 		"name" : "active",
	 * 		"type" : "boolean",
	 * 		"multiValued" : false,
	 * 		"description" : "A Boolean value indicating the User's
	 * 			administrative status.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default
	 * </pre>
	 */
	private Boolean active;

	/**
	 * This attribute is intended to be used as a means to set, replace, or compare
	 * (i.e., filter for equality) a password. The cleartext value or the hashed
	 * value of a password SHALL NOT be returnable by a service provider. If a
	 * service provider holds the value locally, the value SHOULD be hashed. When a
	 * password is set or changed by the client, the cleartext password SHOULD be
	 * processed by the service provider as follows:
	 * 
	 * * Prepare the cleartext value for international language comparison. See
	 * Section 7.8 of [RFC7644].
	 * 
	 * * Validate the value against server password policy. Note: The definition and
	 * enforcement of password policy are beyond the scope of this document.
	 * 
	 * * Ensure that the value is encrypted (e.g., hashed). See Section 9.2 for
	 * acceptable hashing and encryption handling when storing or persisting for
	 * provisioning workflow reasons.
	 * 
	 * A service provider that immediately passes the cleartext value on to another
	 * system or programming interface MUST pass the value directly over a secured
	 * connection (e.g., Transport Layer Security (TLS)). If the value needs to be
	 * temporarily persisted for a period of time (e.g., because of a workflow)
	 * before provisioning, then the value MUST be protected by some method, such as
	 * encryption.
	 * 
	 * Testing for an equality match MAY be supported if there is an existing stored
	 * hashed value. When testing for equality, the service provider:
	 * 
	 * * Prepares the filter value for international language comparison. See
	 * Section 7.8 of [RFC7644].
	 * 
	 * * Generates the salted hash of the filter value and tests for a match with
	 * the locally held value.
	 * 
	 * The mutability of the password attribute is "writeOnly", indicating that the
	 * value MUST NOT be returned by a service provider in any form (the attribute
	 * characteristic "returned" is "never").
	 * 
	 * <pre>
	 * 		"name" : "password",
	 * 		"type" : "string",
	 * 		"multiValued" : false,
	 * 		"description" : "The User's cleartext password.  This
	 * 			attribute is intended to be used as a means to specify an initial
	 * 			password when creating a new User or to reset an existing User's
	 * 			password.",
	 * 		"required" : false,
	 * 		"caseExact" : false,
	 * 		"mutability" : "writeOnly",
	 * 		"returned" : "never",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
//	private String password;

	/**
	 * ScimEmail addresses for the User. The value SHOULD be specified according to
	 * [RFC5321]. Service providers SHOULD canonicalize the value according to
	 * [RFC5321], e.g., "bjensen@example.com" instead of "bjensen@EXAMPLE.COM".
	 * 
	 * <pre>
	 * 		"name" : "emails",
	 * 		"type" : "complex",
	 * 		"multiValued" : true,
	 * 		"description" : "Email addresses for the user.  The value
	 * 			SHOULD be canonicalized by the service provider, e.g.,
	 * 			'bjensen@example.com' instead of 'bjensen@EXAMPLE.COM'.
	 * 			Canonical type values of 'work', 'home', and 'other'.",
	 * 		"required" : false
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
	private List<ScimEmail> emails;

	/**
	 * Phone numbers for the user. The value SHOULD be specified according to the
	 * format defined in [RFC3966], e.g., 'tel:+1-201-555-0123'. Service providers
	 * SHOULD canonicalize the value according to [RFC3966] format, when
	 * appropriate.
	 * 
	 * <pre>
	 * 		"name" : "phoneNumbers",
	 * 		"type" : "complex",
	 * 		"multiValued" : true,
	 * 		"description" : "Phone numbers for the User.  The value
	 * 			SHOULD be canonicalized by the service provider according to the
	 * 			format specified in RFC 3966, e.g., 'tel:+1-201-555-0123'.
	 * 			Canonical type values of 'work', 'home', 'mobile', 'fax', 'pager',
	 * 			and 'other'.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default"
	 * </pre>
	 */
//	private List<ScimPhoneNumber> phoneNumbers;

	/**
	 * Instant messaging address for the user. No official canonicalization rules
	 * exist for all instant messaging addresses, but service providers SHOULD, when
	 * appropriate, remove all whitespace and convert the address to lowercase.
	 * 
	 * <pre>
	 * 		"name" : "ims",
	 * 		"type" : "complex",
	 * 		"multiValued" : true,
	 * 		"description" : "Instant messaging addresses for the User.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default"
	 * </pre>
	 */
//	private List<ScimIms> ims;

	/**
	 * A URI that is a uniform resource locator (as defined in Section 1.1.3 of
	 * [RFC3986]) that points to a resource location representing the user's image.
	 * The resource MUST be a file (e.g., a GIF, JPEG, or PNG image file) rather
	 * than a web page containing an image. Service providers MAY return the same
	 * image in different sizes, although it is recognized that no standard for
	 * describing images of various sizes currently exists. Note that this attribute
	 * SHOULD NOT be used to send down arbitrary photos taken by this user; instead,
	 * profile photos of the user that are suitable for display when describing the
	 * user should be sent. Instead of the standard canonical values for type, this
	 * attribute defines the following canonical values to represent popular photo
	 * sizes: "photo" and "thumbnail".
	 * 
	 * <pre>
	 * 		"name" : "photos",
	 * 		"type" : "complex",
	 * 		"multiValued" : true,
	 * 		"description" : "URLs of photos of the User.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default"
	 * </pre>
	 */
//	private List<ScimPhoto> photos;

	/**
	 * A physical mailing address for this user. Canonical type values of "work",
	 * "home", and "other". This attribute is a complex type with the following
	 * sub-attributes. All sub-attributes are OPTIONAL.
	 * 
	 * <pre>
	 * 		"name" : "addresses",
	 * 		"type" : "complex",
	 * 		"multiValued" : true,
	 * 		"description" : "A physical mailing address for this User.
	 * 			Canonical type values of 'work', 'home', and 'other'.  This attribute
	 * 			is a complex type with the following sub-attributes.",
	 * 		"required" : false,
	 * 		"mutability" : "readWrite",
	 * 		"returned" : "default",
	 * 		"uniqueness" : "none"
	 * </pre>
	 */
//	private List<ScimAddress> addresses;

	/**
	 * A list of groups to which the user belongs, either through direct membership,
	 * through nested groups, or dynamically calculated. The values are meant to
	 * enable expression of common group-based or role-based access control models,
	 * although no explicit authorization model is defined. It is intended that the
	 * semantics of group membership and any behavior or authorization granted as a
	 * result of membership are defined by the service provider. The canonical types
	 * "direct" and "indirect" are defined to describe how the group membership was
	 * derived. Direct group membership indicates that the user is directly
	 * associated with the group and SHOULD indicate that clients may modify
	 * membership through the "Group" resource. Indirect membership indicates that
	 * user membership is transitive or dynamic and implies that clients cannot
	 * modify indirect group membership through the "Group" resource but MAY modify
	 * direct group membership through the "Group" resource, which may influence
	 * indirect memberships. If the SCIM service provider exposes a "Group"
	 * resource, the "value" sub-attribute MUST be the "id", and the "$ref"
	 * sub-attribute must be the URI of the corresponding "Group" resources to which
	 * the user belongs. Since this attribute has a mutability of "readOnly", group
	 * membership changes MUST be applied via the "Group" Resource (Section 4.2).
	 * 
	 * This attribute has a mutability of "readOnly".
	 */
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private List<ScimUserGroup> groups;

	/**
	 * A list of entitlements for the user that represent a thing the user has. An
	 * entitlement may be an additional right to a thing, object, or service. No
	 * vocabulary or syntax is specified; service providers and clients are expected
	 * to encode sufficient information in the value so as to accurately and without
	 * ambiguity determine what the user has access to. This value has no canonical
	 * types, although a type may be useful as a means to scope entitlements.
	 */
//	private List<ScimEntitlement> entitlements;

	/**
	 * A list of roles for the user that collectively represent who the user is,
	 * e.g., "Student", "Faculty". No vocabulary or syntax is specified, although it
	 * is expected that a role value is a String or label representing a collection
	 * of entitlements. This value has no canonical types.
	 */
//	private List<ScimRole> roles;

	/**
	 * A list of certificates associated with the resource (e.g., a User). Each
	 * value contains exactly one DER-encoded X.509 certificate (see Section 4 of
	 * [RFC5280]), which MUST be base64 encoded per Section 4 of [RFC4648]. A single
	 * value MUST NOT contain multiple certificates and so does not contain the
	 * encoding "SEQUENCE OF Certificate" in any guise.
	 */
//	private List<ScimX509Certificate> x509Certificates;


}
