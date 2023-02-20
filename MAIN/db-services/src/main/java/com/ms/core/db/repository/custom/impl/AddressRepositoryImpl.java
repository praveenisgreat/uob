package com.ms.core.db.repository.custom.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;

import com.ms.core.db.model.Address;
import com.ms.core.db.repository.custom.AddressRepositoryCustom;

public class AddressRepositoryImpl implements AddressRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Value("${spring.datasource.name}")
	private String dataSourceName;
	
	@Override
	public Optional<Address> findAddressByCustomerId(Long id) {
		
		return Optional.empty();
	}

}
