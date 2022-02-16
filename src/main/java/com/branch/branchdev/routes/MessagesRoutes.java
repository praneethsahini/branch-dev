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
import com.branch.branchdev.model.AgentLoginStatus;
import com.branch.branchdev.model.AgentStats;
import com.branch.branchdev.model.MessageBase;
import com.branch.branchdev.model.MessageDetails;
import com.branch.branchdev.model.MessageStatus;
import com.branch.branchdev.query.AgentDetailsRepository;
import com.branch.branchdev.query.AgentLoginStatusRepository;
import com.branch.branchdev.query.AgentStatsRepository;
import com.branch.branchdev.query.MessageBaseRepository;
import com.branch.branchdev.query.MessageDetailsRepository;
import com.branch.branchdev.query.MessageStatusRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

@RestController
public class MessagesRoutes {
	
	private final static Logger logger = 
            Logger.getLogger(MessagesRoutes.class.getName());

	@Autowired
	private MessageBaseRepository messageBaseService;
	
	@Autowired
	private MessageDetailsRepository messageDetailsService;
	
	@Autowired
	private MessageStatusRepository messageStatusService;
	
	@Autowired
	private AgentLoginStatusRepository agentLoginService;
	
	@Autowired
	private AgentDetailsRepository agentDetailsService;
	
	@Autowired
	private AgentStatsRepository agentStatsService;
	

	// Map of agent ID to load
	Map<Long, Long> aidLoad = new HashMap<>();
	
	// map of customer ID to agent ID if any
	Map<Long, Long> cidAidMap = new HashMap<>();
	
	// map of customer ID to message status
	Map<Long, Integer> cidAssignedStatus = new HashMap<>();
	
	public final int CREATED = 1;
	public final int ACTIVE = 2;
	public final int ROTTEN = 3;
	public final int DEAD = 4;
	
	// MESSAGE BASE
	@GetMapping("/message")
	public List<MessageBase> retrieveAllMessage() {
		return messageBaseService.listAllItems();
	}
	
	@GetMapping("/message/{messageId}")
	public List<MessageBase> retrieveMessageById(@PathVariable long messageId) {
		return messageBaseService.listMessageById(messageId);
	}
	
	@DeleteMapping("/message/{id}")
	void deleteAgentLogin(@PathVariable Long id) {
		messageBaseService.deleteById(id);
	  }
	
	
	// MESSAGE STATUS
	@GetMapping("/messagestatus")
	public List<MessageStatus> retrieveAllMessageStatus() {
		return messageStatusService.listAllItems();
	}
	
	@GetMapping("/messagestatus/{messageId}")
	public List<MessageStatus> retrieveMessageStatusById(@PathVariable long messageId) {
		return messageStatusService.listMessageById(messageId);
	}
	
	@DeleteMapping("/messagestatus/{id}")
	void deleteMessageStatus(@PathVariable Long id) {
		messageStatusService.deleteById(id);
	  }
	
	
	
	// MESSAGE DETAILS
	@GetMapping("/messagedetails")
	public List<MessageDetails> retrieveAllMessageDetails() {
		return messageDetailsService.listAllItems();
	}
	
	@GetMapping("/messagedetails/{messageId}")
	public List<MessageDetails> retrieveMessageDetaiilsById(@PathVariable long messageId) {
		return messageDetailsService.listMessageById(messageId);
	}
	
	@DeleteMapping("/messagedetails/{id}")
	void deleteMessageDetails(@PathVariable Long id) {
		messageDetailsService.deleteById(id);
	  }
	
	
	
	// MESSAGING ENTRY UPDATES TO DB FOLLOW
	@PostMapping("/message")
	MessageBase newMessage(@RequestBody Map<String, Object> payload) {
		
		long cid = Long.parseLong(payload.get("cid").toString());
		long aid = (long) payload.getOrDefault("aid", assignAgent(cid));
		String messageText = payload.getOrDefault("message", "").toString();
		
		// update status' locally in cache, here in memory
		updateLocalStores(cid, aid);
		
		// insert message base to give a unique ID for this message
		MessageBase messageBase = new MessageBase(aid, cid, new Date(System.currentTimeMillis()));
		
		System.out.println("aidLoad : "+aidLoad);
		System.out.println("cidAssignedStatus : "+cidAssignedStatus);
		System.out.println("cidAidMap : "+cidAidMap);
		
		
		MessageBase messageBaseSaved = messageBaseService.save(messageBase);
		long mid=messageBaseSaved.getMid();
		
		List<MessageBase> m = messageBaseService.listMessageByCidAidLatest(aid, cid);
		if(m.size()>0) {
			mid = m.get(0).getMid();
		}
		
		// Save message status as ACTIVE or CREATED
		MessageStatus messageStatus = new MessageStatus(mid, 
				cidAssignedStatus.get(cid), new Date(System.currentTimeMillis()));
		messageStatusService.save(messageStatus);
		
		
		// Insert actual message
		MessageDetails messageDetails = new MessageDetails(mid, false,
				messageText, new Date(System.currentTimeMillis()));
		messageDetailsService.save(messageDetails);
		
	    return m.get(0);
	}
	
	
	@PutMapping("/message")
	MessageDetails followupMessage(@RequestBody Map<String, Object> payload) {
		
		long mid = Long.parseLong(payload.get("mid").toString());
		long cid = -1;
		long aid = -1;
		
		List<MessageBase> m = messageBaseService.listMessageById(mid);
		if(m.size()>0) {
			cid = m.get(0).getCid();
			aid = m.get(0).getAid();
		}
		
		
		updateLocalStores(cid, aid);
		String messageText = payload.getOrDefault("message", "").toString();
		boolean agentCust = (boolean)payload.get("agentCust");
		
		MessageDetails messageDetails = new MessageDetails(mid, agentCust,
				messageText, new Date(System.currentTimeMillis()));
		
		return messageDetailsService.save(messageDetails);
		
	  }
	
	
	private void updateLocalStores(long cid, long aid) {
		if(!cidAssignedStatus.containsKey(cid)) {
			cidAssignedStatus.put(cid, CREATED);
			cidAidMap.put(cid, aid);
			aidLoad.put(aid, aidLoad.getOrDefault(aid, 0l) + 1);
		}
		else {
			cidAssignedStatus.put(cid, ACTIVE);
			cidAidMap.put(cid, aid); // not necessary
		}
	}
	
	private long assignAgent(long cid) {
		
		
		// proportional to the rating
		// inversely proportional to (current time - last message handled time)
		// inversely proportional to the load
		
		if(cidAssignedStatus.containsKey(cid)) {
			if(cidAssignedStatus.get(cid) == CREATED || cidAssignedStatus.get(cid) == DEAD) {
				return assignNewAgent();
			}
			else {
				return cidAidMap.getOrDefault(cid, assignNewAgent());
			}
		}
		else 
			return assignNewAgent();

	}
	
	private long assignNewAgent() {
		
		List<AgentLoginStatus> allAgents = agentLoginService.listAllAgents();
		long aid = -1, lowestLoad = Integer.MAX_VALUE;
		
		for(AgentLoginStatus agent: agentLoginService.listAllAgents()) {
			if(!agent.isLoginStatus()) {
				continue;
			}
			
			if(aidLoad.containsKey(agent.getAid())) {
				if(lowestLoad>aidLoad.get(agent.getAid())){
					lowestLoad = aidLoad.get(agent.getAid());
					aid = agent.getAid();
				}
			}
			else {
				lowestLoad = 0;
				aid = agent.getAid();
				aidLoad.put(aid, lowestLoad);
			}
		}
		
		List<AgentStats> agentstatsDetails = agentStatsService.listItemsById(aid);
		if(agentstatsDetails.size()>0) {
			int maxCust = agentstatsDetails.get(0).getMaxNumCustomers();
			logger.info(aid+" -> "+maxCust+" : "+aidLoad);
			System.out.println(aid+" -> "+maxCust+" : "+aidLoad);
			if(aidLoad.get(aid) > maxCust) {
				System.out.println("AGENT "+aid +" is overloaded. Reduce load to "+ maxCust +" from "+aidLoad.get(aid));
				logger.info("AGENT "+aid +" is overloaded. Reduce load to "+ maxCust +" from "+aidLoad.get(aid));
			}
		}
		
		return aid;
	}
	
	
	
	
}