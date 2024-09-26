package com.spyder.socially.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spyder.socially.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	
	Optional<User> findById(UUID id);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	
}
