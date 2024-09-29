package com.spyder.socially.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.spyder.socially.dto.UserDTO;
import com.spyder.socially.handler.UserNotFoundException;
import com.spyder.socially.model.User;
import com.spyder.socially.repository.UserRepository;
import com.spyder.socially.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
    // Utility method to convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
	
    @Override
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("Email already exists");
        }

        user.setId(UUID.randomUUID());  // Ensure unique UUID
        return userRepository.save(user);
    }
    
    @Override
    public Optional<UserDTO> getUserById(UUID id) {
    	return userRepository.findById(id).map(this::convertToDTO);
    }
    
    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
    	return userRepository.findByEmail(email).map(this::convertToDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
    	return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public User updateUser(UUID id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields
            if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
            if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
            if (user.getPassword() != null) existingUser.setPassword(user.getPassword());

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
	
}
