package fr.pay.scim.test.scim.method;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pay.scim.test.scim.user.ScimUser;
import fr.pay.scim.test.scim.user.ScimUsers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindUser {


	public static ScimUser findUserById(String scimEndPoint, String id) throws Exception {
		
		Client client = ClientBuilder.newClient();
		
	    client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
	    client.property(ClientProperties.READ_TIMEOUT,    300000);
	    	
		WebTarget webTarget = client.target(scimEndPoint);
		
		Map<String, Object> values = new HashMap<>();
		values.put("id", id);
		
		WebTarget usersWebTarget = webTarget
				.path("/Users/{id}")
				.resolveTemplates(values);
				
		Response response = usersWebTarget
				.request()
				.header("Content-Type", MediaType.APPLICATION_JSON)
//				.header(apiKeyName, apiKey)
				.get();
		
		String data = response.readEntity(String.class);
		
		if (response.getStatus() == 200) {

			try {
				
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(data, ScimUser.class);
								
			} catch (JsonProcessingException e) {
				
				log.error("addUserInternal {}", response);

				throw new Exception(
						"Erreur lors de la lecture de la réponse " + data + " : " + e.getMessage(), 
						e);
			}
			
		} else if (response.getStatus() == 404) {
			System.out.println("404 : " + data);
			
			return null;
		} else {

			System.out.println("autre : " + data);
		  
			throw new Exception("Erreur lors de la lecture de la réponse " + data);
		}

	}

	
	
	public static ScimUsers findUsers(String scimEndPoint) throws Exception {
		
		Client client = ClientBuilder.newClient();
		
	    client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
	    client.property(ClientProperties.READ_TIMEOUT,    300000);
	    	
		WebTarget webTarget = client.target(scimEndPoint);
		
		WebTarget usersWebTarget = webTarget
				.path("/Users");
				
		Response response = usersWebTarget
				.request()
				.header("Content-Type", MediaType.APPLICATION_JSON)
//				.header(apiKeyName, apiKey)
				.get();
		
		String data = response.readEntity(String.class);
		
		if (response.getStatus() == 200) {

			try {
				
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(data, ScimUsers.class);
								
			} catch (JsonProcessingException e) {
				
				log.error("addUserInternal {}", response);

				throw new Exception(
						"Erreur lors de la lecture de la réponse " + data + " : " + e.getMessage(), 
						e);
			}
			
		} else if (response.getStatus() == 404) {
			System.out.println("404 : " + data);
			
			return null;
		} else {

			System.out.println("autre : " + data);
		  
			throw new Exception("Erreur lors de la lecture de la réponse " + data);
		}

	}

}
