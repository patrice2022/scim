package fr.pay.scim.test;

import java.util.HashMap;
import java.util.Map;

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
public class TestScim {

	String scimEndPoint = "http://localhost:8080";
	
	private ScimUser findUserById(String id) throws Exception {
		
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

	
	private ScimUser addUser(ScimUser payload) throws Exception {
		
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

	
	
	private void deleteUserById(String id) throws Exception {
		
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

	
	

	public static void main(String[] args) throws Exception {
		TestScim test = new TestScim();
		
		// Création d'un utilisateur
		ScimUser scimUser = new ScimUser();
		scimUser.setUserName("paubry");
		scimUser = test.addUser(scimUser);
		System.out.println(scimUser);
		
		
		// Recherche d'un utilisateur
		scimUser = test.findUserById(scimUser.getId());
		System.out.println(scimUser);
		
		
		// Suppression d'un utilisateur
		test.deleteUserById(scimUser.getId());
	}
}
