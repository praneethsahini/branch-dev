package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.branch.branchdev.model.Status;

@Repository
public interface MessageStatusRepository extends CrudRepository<Status, Long> {
    

    @Query("SELECT m FROM Status m")
    public List<Status> listAllItems();

}