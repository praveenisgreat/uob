package com.ms.core.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.core.auth.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsernameOrEmail(String username, String email);

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
