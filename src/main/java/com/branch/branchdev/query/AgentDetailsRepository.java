package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.branch.branchdev.model.AgentDetails;

@Repository
public interface AgentDetailsRepository extends CrudRepository<AgentDetails, Long> {
     
    @Query("SELECT a FROM AgentDetails a")
    public List<AgentDetails> listAllItems();

}
