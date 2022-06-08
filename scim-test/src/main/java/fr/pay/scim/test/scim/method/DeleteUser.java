package fr.pay.scim.test.scim.method;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteUser {



	public static void deleteUserById(String scimEndPoint, String id) throws Exception {
		
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
				.delete();
		
		String data = response.readEntity(String.class);
		
		if (response.getStatus() == 204) {

			
		} else if (response.getStatus() == 404) {
			System.out.println("404 : " + data);
			
			throw new Exception("Erreur lors de la lecture de la réponse " + data);
		} else {

			System.out.println("autre : " + data);
		  
			throw new Exception("Erreur lors de la lecture de la réponse " + data);
		}

	}
	
}
