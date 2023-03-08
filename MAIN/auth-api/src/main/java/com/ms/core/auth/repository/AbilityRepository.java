package com.ms.core.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.core.auth.model.AbilityAction;
import com.ms.core.auth.model.AbilityModel;


@Repository
public interface AbilityRepository extends JpaRepository<AbilityModel, Long>{
	Optional<AbilityModel> findByAction(AbilityAction actionName);
}
