package com.ms.core.db.repository.custom.impl;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ms.core.db.model.Customer;
import com.ms.core.db.repository.custom.CustomerRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

	@PersistenceContext
    EntityManager entityManager;
	
	@Value("${spring.datasource.name}")
	private String dataSourceName;
	
	@Override
	public Optional<Customer> findCustomerByNameDob(String name, String dob) {
		Query query = entityManager.createNativeQuery("SELECT em.* FROM"+ dataSourceName+".customer as em " +
                "WHERE em.firstname LIKE ? AND em.dateOfBirth LIKE ?", Customer.class);
        query.setParameter(1, name + "%");
        query.setParameter(2, dob + "%");
		return (Optional<Customer>) query.getSingleResult();
	}

}
