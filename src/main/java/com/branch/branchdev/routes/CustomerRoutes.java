package com.branch.branchdev.routes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.branch.branchdev.model.Customer;
import com.branch.branchdev.query.CustomerRepository;
import java.util.List;

@RestController
public class CustomerRoutes {

	@Autowired
	private CustomerRepository customerService;

	@GetMapping("/customer")
	public List<Customer> retrieveAllCustomers(@PathVariable int customerId) {
		return customerService.listAllItems();
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Customer> retrieveCoustomerById(@PathVariable int customerId) {
		return customerService.listItemsById(customerId);
	}
}