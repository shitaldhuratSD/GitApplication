package com.dev.gitclone.GitApplication.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gitclone.GitApplication.Repository.UserRepository;
import com.dev.gitclone.GitApplication.dto.Status;
import com.dev.gitclone.GitApplication.dto.User;
import com.dev.gitclone.GitApplication.service.UserService;

@RestController
@RequestMapping(path="/gitapplication")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	    UserRepository userRepository;
	 
	    @PostMapping("/users/register")
	    public Status registerUser(@Valid @RequestBody User newUser) {
	        List<User> users = userRepository.findAll();
	        System.out.println("New user: " + newUser.toString());
	        for (User user : users) {
	            System.out.println("Registered user: " + newUser.toString());
	            if (user.equals(newUser)) {
	                System.out.println("User Already exists!");
	                return Status.USER_ALREADY_EXISTS;
	            }
	        }
	        userRepository.save(newUser);
	        return Status.SUCCESS;
	    }
	    
	    @PostMapping("/users/login")
	    public Status loginUser(@Valid @RequestBody User user) {
	        List<User> users = userRepository.findAll();
	        for (User other : users) {
	            if (other.equals(user)) {
	                user.setLoggedIn(true);
//	                userRepository.save(user);
	                return Status.SUCCESS;
	            }
	        }
	        return Status.FAILURE;
	    }
	    
	    
	    @PostMapping("/users/logout")
	    public Status logUserOut(@Valid @RequestBody User user) {
	        List<User> users = userRepository.findAll();
	        for (User other : users) {
	            if (other.equals(user)) {
	                user.setLoggedIn(false);
//	                userRepository.save(user);
	                return Status.SUCCESS;
	            }
	        }
	        return Status.FAILURE;
	    }
	    @DeleteMapping("/users/deleteAll")
	    public Status deleteUsers() {
	        userRepository.deleteAll();
	        return Status.SUCCESS;
	    }
	    
	    
	    @DeleteMapping("/users/delete/{id}")  
	    private Status deleteUserById(@PathVariable("id") int id)   
	    {  
	    	userRepository.deleteById(id);  
	    return Status.SUCCESS;
	    } 
	   
	    
	    @GetMapping(path="/users/all")
		 public Iterable<User> getAllUsers() {
			 return userRepository.findAll();
	    }
	    
	    @GetMapping("/users/getbyid/{id}")  
	    private Optional<User> getByUserId(@PathVariable("id") int id)   
	    {  
	    return userRepository.findById(id); 
	    }
	    
	    @PostMapping("/users/forgot-password")
		public String forgotPassword(@RequestParam String email) {

			String response = userService.forgotPassword(email);

			if (!response.startsWith("Invalid")) {
				response = "http://localhost:8080/gitapplication/users/reset-password?token=" + response;
			}
			return response;
		}

		@PutMapping("/users/reset-password")
		public String resetPassword(@RequestParam String token,
				@RequestParam String password) {

			return userService.resetPassword(token, password);
		}
	   

}
