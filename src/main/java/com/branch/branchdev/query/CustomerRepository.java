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
    
//    public List<Customer> findByItem(int item);
     
    @Query("SELECT c FROM Customer c")
    public List<Customer> listAllItems();
    
//    @Param("amount") float amount
}
