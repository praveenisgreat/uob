package com.ms.core.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ms.core.db.model.Address;
import com.ms.core.db.model.Customer;
import com.ms.core.db.repository.custom.AddressRepositoryCustom;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom{
	List<Address> findByCustomer(Customer customer);
}

