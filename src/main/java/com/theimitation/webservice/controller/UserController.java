package com.theimitation.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.theimitation.webservice.dto.Login;
import com.theimitation.webservice.dto.UserDto;
import com.theimitation.webservice.model.User;
import com.theimitation.webservice.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserDto userDto;

	@RequestMapping(method = RequestMethod.POST, value = "/users/authenticate")
	public UserDto login(@RequestBody Login login) {
		userDto = userService.authenticateUser(login.getUsername(), login.getPassword());
		return userDto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/register")
	public String registerUser(@RequestBody User user) {
		return userService.userRegistration(user);
	}
}
