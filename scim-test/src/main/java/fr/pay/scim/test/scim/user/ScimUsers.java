package fr.pay.scim.test.scim.user;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ScimUsers {

	private int totalResults;
	
	private int itemsPerPage;
	
	private int startIndex;
	
	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:ListResponse");
	
	@JsonProperty(value = "Resources")
	private List<ScimUser> resources;
	
}
