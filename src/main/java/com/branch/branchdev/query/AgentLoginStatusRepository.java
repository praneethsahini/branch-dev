package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.branch.branchdev.model.AgentLoginStatus;

@Repository
public interface AgentLoginStatusRepository extends CrudRepository<AgentLoginStatus, Long> {
    

    @Query("SELECT a FROM AgentLoginStatus a")
    public List<AgentLoginStatus> listAllAgents();
    
    @Query("SELECT a FROM AgentLoginStatus a where aid = :agentId")
    public List<AgentLoginStatus> listAgentById(@PathVariable long agentId);

}
