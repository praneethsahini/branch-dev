package com.branch.branchdev.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.branch.branchdev.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
     
    @Query("SELECT c FROM Customer c")
    public List<Customer> listAllItems();
    
    @Query("SELECT c FROM Customer c where c.cid = :customerId")
    public List<Customer> listItemsById(int customerId);
    

}
