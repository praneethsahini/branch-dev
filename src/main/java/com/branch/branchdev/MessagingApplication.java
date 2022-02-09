package com.branch.branchdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


import com.branch.branchdev.query.CustomerRepository;


@SpringBootApplication
public class MessagingApplication implements CommandLineRunner{
	
	
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    CustomerRepository repository;
     
    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }
 
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO branch.status (sid, status_message) VALUES (?, ?)";
//        sql = "SELECT * FROM branch.status";
        System.out.println(sql);
        
//        int result = jdbcTemplate.update(sql, 6, "from code");
         
//        System.out.println(result);
//        if (result > 0) {
//            System.out.println("A new row has been inserted.");
//        }
        
        Iterable it = repository.listAllItems();
        it.forEach(a->System.out.println(a));
         
    }

}


