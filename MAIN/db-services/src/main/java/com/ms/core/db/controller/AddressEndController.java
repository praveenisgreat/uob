package com.ms.core.db.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.core.db.model.Address;
import com.ms.core.db.model.Customer;
import com.ms.core.db.repository.AddressRepository;

/**
 * Address controller.
 *
 * @author praveen 
 */
@RestController
@RequestMapping("/address")
public class AddressEndController {
	Logger logger = Logger.getLogger("AddressEndController");
	
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/")
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public Customer getAddressByCustomer(@PathVariable(value = "id", required = true) Long id) {
        return null;
    }

}
