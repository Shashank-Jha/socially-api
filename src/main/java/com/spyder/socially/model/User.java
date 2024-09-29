package com.spyder.socially.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private UUID id;
	@Column(name = "username",unique = true, nullable = false)
	private String username;
	@Column(name = "firstname",nullable = false)
	private String firstName;
	@Column(name = "lastname",nullable = false)
	private String lastName;
	@Column(name = "email",unique = true, nullable = false)
	private String email;
	@Column(name = "password",nullable = false)
	private String password;

	public User() {
		this.setId(UUID.randomUUID());
	}

	public User(String username, String firstName, String lastName, String email, String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
