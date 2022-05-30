package fr.pay.scim.serveur.endpoint.patch;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchOp {

	@Schema(defaultValue = "urn:ietf:params:scim:api:messages:2.0:PatchOp")
	private List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:PatchOp");
	
	@JsonProperty(value = "Operations")
	private List<Operation> operations;
	
}
