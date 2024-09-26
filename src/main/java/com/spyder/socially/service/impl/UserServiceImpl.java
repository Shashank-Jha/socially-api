package com.spyder.socially.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.spyder.socially.dto.UserDTO;
import com.spyder.socially.model.User;
import com.spyder.socially.repository.UserRepository;
import com.spyder.socially.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
    // Utility method to convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
	
    @Override
    public User createUser(User user) {
    	
    }
    
    public Optional<UserDTO> getUserById(UUID id){
    	return userRepository.findById(id).map(this::convertToDTO);
    }
    
    public Optional<UserDTO> getUserByEmail(String email){
    	
    }
    
    public List<UserDTO> getAllUsers(){
    	
    }
    
    public User updateUser(UUID id, User user) {
    	
    }
    
    public void deleteUser(UUID id) {
    	
    }
	
}
