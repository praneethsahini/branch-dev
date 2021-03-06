package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.branch.branchdev.model.AgentDetails;
import com.branch.branchdev.model.AgentStats;

@Repository
public interface AgentStatsRepository extends CrudRepository<AgentStats, Long> {
    

    @Query("SELECT a FROM AgentStats a")
    public List<AgentStats> listAllItems();
    
    @Query("SELECT a FROM AgentStats a where aid = :agentId")
    public List<AgentStats> listItemsById(@PathVariable long agentId);

}
