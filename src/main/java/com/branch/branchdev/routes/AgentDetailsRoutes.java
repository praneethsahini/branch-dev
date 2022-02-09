package com.branch.branchdev.routes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.branch.branchdev.model.AgentDetails;
import com.branch.branchdev.query.AgentDetailsRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AgentDetailsRoutes {

	@Autowired
	private AgentDetailsRepository agentService;

	
	@GetMapping("/agent")
	public List<AgentDetails> retrieveAllAgent() {
		return agentService.listAllItems();
	}
	
	@GetMapping("/agent/{agentId}")
	public List<AgentDetails> retrieveAgentById(@PathVariable long agentId) {
		return agentService.listItemsById(agentId);
	}
	
	@PostMapping("/agent")
	AgentDetails newEmployee(@RequestBody Map<String, String> payload) {
		AgentDetails newAgentDetails = new AgentDetails(
				payload.getOrDefault("firstName", ""), 
				payload.getOrDefault("lastName", ""),
				payload.getOrDefault("address", ""));
		
		newAgentDetails.setTimestamp(new Date(System.currentTimeMillis()));
	    return agentService.save(newAgentDetails);
	}
	
	
	@DeleteMapping("/agent/{id}")
	void deleteEmployee(@PathVariable long id) {
		agentService.deleteById(id);
	  }
	
	
	@PutMapping("/agent/{id}")
	AgentDetails replaceEmployee(@RequestBody Map<String, String> payload, @PathVariable long id) {
		
		AgentDetails agent = new AgentDetails(
				payload.getOrDefault("firstName", ""), 
				payload.getOrDefault("lastName", ""),
				payload.getOrDefault("address", ""));
		
		
		for(AgentDetails oldAgent: agentService.listItemsById(id)) {
			
			String address = agent.getAddress();
			if(address != null && address.length()>0) {
				agent.setAddress(address);
			}
			
			String firstName = agent.getFirstName();
			if(firstName != null && firstName.length()>0) {
				agent.setFirstName(firstName);
			}
			
			String lastName = agent.getLastName();
			if(lastName != null && lastName.length()>0) {
				agent.setLastName(lastName);
			}

			agent.setTimestamp(new Date(System.currentTimeMillis()));
			return agentService.save(agent);
		}
		return agent;
	  }
	
}