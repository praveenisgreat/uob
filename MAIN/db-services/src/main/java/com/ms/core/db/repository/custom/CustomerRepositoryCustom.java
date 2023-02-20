package com.ms.core.db.repository.custom;

import java.util.Optional;

import com.ms.core.db.model.Customer;

public interface CustomerRepositoryCustom {

	Optional<Customer> findCustomerByNameDob(String name, String dob);
}
