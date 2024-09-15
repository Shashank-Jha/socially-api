package com.spyder.socially.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spyder.socially.model.User;

@RestController
public class UserController {
	@GetMapping("/get-users")
	public List<User> getUsers(){
		User userOne = new User("shash","Shash","Jha","shash@google,com","shashword");
		List<User> users = new ArrayList<User>();
		users.add(userOne);
		return users;
	}
}
