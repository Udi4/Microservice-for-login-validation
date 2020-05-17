package com.myservice.loginMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myservice.loginMicroservice.entity.User;
import com.myservice.loginMicroservice.repository.UserRepository;

@RestController
@RequestMapping(value = "/")
public class UserController{
	
	@Autowired
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;
		
	}


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		System.out.println("Saving user.");
		return userRepository.save(user);
	}

	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		System.out.println("Getting user with ID: {}."+userId);
		return userRepository.findByName(userId);
	}
	
	@RequestMapping("/postdata")
    public String customerInformation() {

        /* You can write your DAO logic here.
         * For time being I am printing the customer data just to show the POST call is working.
         */

        return "Hi I will show User data" ;
    }

	@PostMapping("/user/userdata")
	public User addPerson(@RequestBody User user){
		//Just has a Sysout stmt, a real world application would save this record to the database
		System.out.println("User name:"+user.getUsername());
		System.out.println("User Password:"+user.getPassword());
		userRepository.save(user);
		
		
		// fetch all customers
	    System.out.println("Users found with findAll():");
	    System.out.println("-------------------------------");
	    for (User user_temp : userRepository.findAll()) {
	      System.out.println(user_temp);
	    }
		
		return user;
		
	}
}
