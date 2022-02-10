package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.branch.branchdev.model.AgentLoginStatus;
import com.branch.branchdev.model.MessageBase;

@Repository
public interface MessageBaseRepository extends CrudRepository<MessageBase, Long> {
    

    @Query("SELECT m FROM MessageBase m")
    public List<MessageBase> listAllItems();
    
    @Query("SELECT m FROM MessageBase m where mid = :messageId")
    public List<MessageBase> listMessageById(@PathVariable long messageId);
    
    @Query(value = "SELECT * from message_base where aid = :aid and cid = :cid ORDER BY mid DESC LIMIT 1", nativeQuery = true)
    public List<MessageBase> listMessageByCidAidLatest(@PathVariable long aid, @PathVariable long cid);
    

}
