package com.ms.core.db.repository;

import org.springframework.stereotype.Repository;
import com.ms.core.db.model.Customer;
import com.ms.core.db.repository.custom.CustomerRepositoryCustom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom{
	Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsernameOrEmail(String username, String email);

    Optional<Customer> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email); 
}
