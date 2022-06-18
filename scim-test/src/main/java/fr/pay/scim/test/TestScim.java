package fr.pay.scim.test;

import static fr.pay.scim.test.scim.method.AddUser.addUser;
import static fr.pay.scim.test.scim.method.DeleteUser.deleteUserById;
import static fr.pay.scim.test.scim.method.FindUser.findUserById;
import static fr.pay.scim.test.scim.method.FindUser.findUsers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.github.javafaker.Faker;

import fr.pay.scim.test.scim.user.ScimEmail;
import fr.pay.scim.test.scim.user.ScimName;
import fr.pay.scim.test.scim.user.ScimUser;
import fr.pay.scim.test.scim.user.ScimUsers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestScim {

	String scimEndPoint = "http://localhost:8080";
	

	private List<ScimUser> scimUsers = new ArrayList<>();

	
	public void go() throws Exception {
		
		
		if ((Math.random() * 100) > 60) {
			Faker faker = new Faker();
		
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String userName = StringUtils.stripAccents(StringUtils.lowerCase(StringUtils.deleteWhitespace(StringUtils.join(Arrays.asList(firstName .split(" ")).stream().map(n -> "a").collect(Collectors.toList()).toArray(), "") + lastName)));
			String title = faker.company().profession();
			String email =  (firstName + "." + lastName + "@" + faker.company().name() + ".com").replaceAll(" ", "");
			
			// Création d'un utilisateur
			ScimName scimName = new ScimName();
			scimName.setFamilyName(lastName);
			scimName.setGivenName(firstName);
			
			ScimEmail scimEmail = new ScimEmail();
			scimEmail.setType("work");
			scimEmail.setValue(email);
			
			ScimUser scimUser = new ScimUser();
			scimUser.setUserName(userName);
			scimUser.setExternalId(UUID.randomUUID().toString());
			scimUser.setName(scimName);
			scimUser.setDisplayName(firstName + " " + lastName);
			scimUser.setTitle(title);
			scimUser.setEmails(Arrays.asList(scimEmail));
			
			System.out.println("----- ajout d'un utilisateur " + scimUser.getUserName() + " -----");
			System.out.println("demande : " + scimUser);
			scimUser = addUser(scimEndPoint, scimUser);
			System.out.println("reponse : " + scimUser);
			
			scimUsers.add(scimUser);
			
		} else 	if ((Math.random() * 100) > 50 && !scimUsers.isEmpty()) {
			System.out.println("----- suppression d'un utilisateur par l'Id -----");
			deleteUserById(scimEndPoint, scimUsers.remove(0).getId());
			
		}
		
		
		

		
//		System.out.println("----- recherche d'un utilisateur par l'Id -----");
//		System.out.println(findUserById(scimEndPoint, scimUser.getId()));
		

		
		System.out.println("----- liste des utilisateurs -----");
		ScimUsers scimUsers = findUsers(scimEndPoint);
		scimUsers.getResources().forEach(s -> {
			String famillyName ="";
			String givenName = "";
			String email = "";
			
			if (s.getName() != null) {
				famillyName = s.getName().getFamilyName();
				givenName = s.getName().getGivenName();
			}
			if (s.getEmails() != null) {
				email = s.getEmails().get(0).getValue();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String createTime = sdf.format(s.getMeta().getCreated());
			String lastModified = sdf.format(s.getMeta().getLastModified());
			
			System.out.printf("%36s", s.getId())
	          		  .printf("%1s", "|")
			          .printf("%12s", s.getUserName())
			          .printf("%1s", "|")
			          .printf("%36s", s.getExternalId())
			          .printf("%1s", "|")
			          .printf("%15s", famillyName)
			          .printf("%1s", "|")
			          .printf("%15s", givenName)
			          .printf("%1s", "|")
			          .printf("%30s", s.getDisplayName())
			          .printf("%1s", "|")
			          .printf("%20s", s.getTitle())
			          .printf("%1s", "|")
			          .printf("%50s", email)			          
			          .printf("%1s", "|")
			          .printf("%20s", createTime)			          
			          .printf("%1s", "|")
			          .printf("%20s", lastModified)
					  .printf("%n", "|");
			});
	}

	
	
	public static void main(String[] args) throws Exception {
		TestScim test = new TestScim();
		
		while (true) {
			try {				
				
				Thread.sleep(2000);
				
				test.go();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
