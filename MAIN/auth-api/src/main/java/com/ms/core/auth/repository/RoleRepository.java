package com.ms.core.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.core.auth.model.RoleModel;
import com.ms.core.auth.model.RoleName;

public interface RoleRepository extends JpaRepository<RoleModel, Long>{
	Optional<RoleModel> findByName(RoleName roleName);
}
