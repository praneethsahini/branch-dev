package com.branch.branchdev.routes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.branch.branchdev.model.AgentLoginStatus;
import com.branch.branchdev.query.AgentLoginStatusRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AgentLoginRoutes {

	@Autowired
	private AgentLoginStatusRepository agentService;

	
	@GetMapping("/agentlogin")
	public List<AgentLoginStatus> retrieveAllAgent() {
		return agentService.listAllAgents();
	}
	
	@GetMapping("/agentlogin/{agentId}")
	public List<AgentLoginStatus> retrieveAgentById(@PathVariable long agentId) {
		return agentService.listAgentById(agentId);
	}
	
	@PostMapping("/agentlogin")
	AgentLoginStatus newAgentLogin(@RequestBody Map<String, Object> payload) {

		AgentLoginStatus newAgentDetails = new AgentLoginStatus(
				Long.parseLong(payload.get("aid").toString()),
				(boolean)payload.get("loginStatus"),
				new Date(System.currentTimeMillis()));
		System.out.println(newAgentDetails);
		
	    return agentService.save(newAgentDetails);
	}
	
	
	@DeleteMapping("/agentlogin/{id}")
	void deleteAgentLogin(@PathVariable Long id) {
		agentService.deleteById(id);
	  }
	
	
	@PutMapping("/agentlogin")
	AgentLoginStatus replaceAgentLogin(@RequestBody Map<String, Object> payload) {
		AgentLoginStatus newAgentDetails = new AgentLoginStatus(
				Long.parseLong(payload.get("aid").toString()), 
				(boolean)payload.get("loginStatus"),
				new Date(System.currentTimeMillis()));
		System.out.println(newAgentDetails);
		return agentService.save(newAgentDetails);
	  }
	
}