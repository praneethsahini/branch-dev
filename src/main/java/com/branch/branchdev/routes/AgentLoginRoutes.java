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

import java.util.List;

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
	AgentLoginStatus newEmployee(@RequestBody AgentLoginStatus newAgentDetails) {
	    return agentService.save(newAgentDetails);
	}
	
	
	@DeleteMapping("/agentlogin/{id}")
	void deleteEmployee(@PathVariable Long id) {
		agentService.deleteById(id);
	  }
	
	
	@PutMapping("/agentlogin/{id}")
	AgentLoginStatus replaceEmployee(@RequestBody AgentLoginStatus agent, @PathVariable long id) {
		return agentService.save(agent);
	  }
	
}