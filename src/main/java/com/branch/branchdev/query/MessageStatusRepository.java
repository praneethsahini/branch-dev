package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.branch.branchdev.model.MessageDetails;
import com.branch.branchdev.model.MessageStatus;

@Repository
public interface MessageStatusRepository extends CrudRepository<MessageStatus, Long> {
    

    @Query("SELECT m FROM MessageStatus m")
    public List<MessageStatus> listAllItems();
    
    @Query("SELECT m FROM MessageStatus m where mid = :messageId")
    public List<MessageStatus> listMessageById(@PathVariable long messageId);

}
