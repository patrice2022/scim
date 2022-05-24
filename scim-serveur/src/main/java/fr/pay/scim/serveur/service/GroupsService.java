package fr.pay.scim.serveur.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.pay.scim.serveur.endpoint.entity.group.ScimGroup;
import fr.pay.scim.serveur.service.entity.Group;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupsService {

	private Map<String, Group> groups = new HashMap<>();

	public GroupsService() {
		super();
		
		Group group = new Group();
		group.setId("0000");
		group.setDisplayName("Bla Bla Bla");
		
		groups.put("0000", group);
	}

	
	public ScimGroup read(String id) {
		
		Group group = groups.get(id);
		
		if (group == null) {
			return null;
		}
		
		ScimGroup scimGroup = new ScimGroup();
		scimGroup.setId(group.getId());
		scimGroup.setDisplayName(group.getDisplayName());
		
		return scimGroup;
	}
	
}
