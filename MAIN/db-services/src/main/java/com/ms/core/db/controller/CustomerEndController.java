package com.ms.core.db.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.core.db.exceptions.CustomerNotFoundException;
import com.ms.core.db.model.Customer;
import com.ms.core.db.repository.CustomerRepository;

/**
 * Customer controller.
 *
 * @author praveen 
 */
@RestController
@RequestMapping("/customer")
public class CustomerEndController {

	Logger logger = Logger.getLogger("CustomerEndController");
	
	@Autowired
	private CustomerRepository customerRepository;
	
    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id", required = true) Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
    
    @GetMapping("/{name}/{dob}")
    public Customer getEmployeeByNameDob(@PathVariable(value = "name", required = true) String name,
    									 @PathVariable(value = "dob", required = true) String dob) {
        return customerRepository.findCustomerByNameDob(name, dob)
                .orElseThrow(() -> new CustomerNotFoundException(name));
    }
}

