package fr.pay.scim.serveur.endpoint.patch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;

import fr.pay.scim.serveur.endpoint.entity.user.ScimUser;
import fr.pay.scim.serveur.exception.BadRequestException;
import fr.pay.scim.serveur.exception.InternalServerErrorException;
import fr.pay.scim.serveur.exception.ScimException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatchProcess {

	private ObjectMapper mapper = new ObjectMapper();
	
	public ScimUser patch(ScimUser scimUser, PatchOp patchOp) throws ScimException {
		

			// Conversion du payload en JsonPatch
			try {
				String payload = mapper.writeValueAsString(patchOp.getOperations());

				JsonNode jsonNodeServer = mapper.readTree(payload);
				JsonPatch patch = JsonPatch.fromJson(jsonNodeServer);
				
				// Application du patch sur la representation json de l'utilisateur scim
				JsonNode patchedScimUser = patch.apply(mapper.convertValue(scimUser, JsonNode.class));
				
				// On récupere le scim de l'utilisateur qui es paché
				ScimUser scimUserPatched = mapper.treeToValue(patchedScimUser, ScimUser.class);
				
				return scimUserPatched;			
			
			} catch (JsonProcessingException e) {
				throw new BadRequestException();
				
			} catch (Exception e) {
				throw new InternalServerErrorException();
			}
			

	}
}
