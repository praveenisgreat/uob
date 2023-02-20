package com.ms.core.db.repository.custom;

import java.util.Optional;

import com.ms.core.db.model.Address;


public interface AddressRepositoryCustom {
	Optional<Address> findAddressByCustomerId(Long id);
}
