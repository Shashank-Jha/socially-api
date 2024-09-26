package com.spyder.socially.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.spyder.socially.dto.UserDTO;
import com.spyder.socially.model.User;

public interface UserService {
	
    User createUser(User user);
    
    Optional<UserDTO> getUserById(UUID id);
    
    Optional<UserDTO> getUserByEmail(String email);
    
    List<UserDTO> getAllUsers();
    
    User updateUser(UUID id, User user);
    
    void deleteUser(UUID id);
	
}
