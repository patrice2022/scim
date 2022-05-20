package fr.pay.scim.serveur.endpoint.entity;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScimError {

	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:Error");
	
	private String status;
	
	private String detail;
	
}
