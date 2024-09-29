package com.spyder.socially.controller;

import com.spyder.socially.dto.UserDTO;
import com.spyder.socially.model.User;
import com.spyder.socially.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations pertaining to User in Socially App")
public class UserController {

    @Autowired
    private UserService userService;

    // Get user by ID
    @GetMapping("/{id}")
    @Operation(summary = "Find user by ID", description = "Returns a single user by UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get user by Email
    @GetMapping("/email/{email}")
    @Operation(summary = "Find user by Email", description = "Returns a single user by their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponse(responseCode = "200", description = "List of users", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Create new user
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	   try {
    	        User createdUser = userService.createUser(user);
    	        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    	    } catch (RuntimeException e) {
    	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Conflict if username or email already exists
    	    }
    }

    // Update an existing user
    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an existing user", description = "Updates specific fields of a user by their UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<User> patchUser(@PathVariable UUID id, @RequestBody User patchDetails) {
        User updatedUser = userService.updateUser(id, patchDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user by UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
