package fr.pay.scim.test.scim.method;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pay.scim.test.scim.user.ScimUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddUser {



	public static ScimUser addUser(String scimEndPoint, ScimUser payload) throws Exception {
		
		Client client = ClientBuilder.newClient();
		
	    client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
	    client.property(ClientProperties.READ_TIMEOUT,    5000);
	    
		WebTarget webTarget = client.target(scimEndPoint);
		
		WebTarget usersWebTarget = webTarget
				.path("/Users");
		
		Response response = usersWebTarget
				.request()
				.header("Content-Type", MediaType.APPLICATION_JSON)
//				.header(apiKeyName, apiKey)
				.post(Entity.entity(payload, MediaType.APPLICATION_JSON));
		
		String data = response.readEntity(String.class);
		
		if (response.getStatus() == 201) {
			
			try {

				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(data, ScimUser.class);
								
			} catch (JsonProcessingException e) {
				
				log.error("addUser {}", response);

				throw new Exception(
						"Erreur lors de la lecture de la réponse " + data + " : " + e.getMessage(), 
						e);
			}
			
		} else {

			log.error("addUserInternal : {}, payload : {}, retour : {}", response, payload, data);
			
			throw new Exception("Echec addUserInternal : " + response);
		}
	}	

	
}
