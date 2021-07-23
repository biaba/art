package com.art.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.art.entities.User;
import com.art.repositories.UserRepository;
import com.art.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

// Authentication
	@RequestMapping(value = "/users/authenticate", method = RequestMethod.POST, produces = "application/json")
	public User login(@RequestBody User user) throws InterruptedException {
		
		return userRepo.findByUserName(user.getUserName());
	}

// Getting all users	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public User[] getAll(Principal pi) {

		List<User> users = userRepo.findAll();
		return users.toArray(new User[users.size()]);
	}

// Getting logged in user or another user if looking at profile
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public User getUserDetails(@RequestParam Optional<String> artist, Principal principal) {
		User u = new User();
		if (artist.isPresent()) {
			u = userRepo.findByUserName(artist.get());

		} else {
			u = userRepo.findByUserName(principal.getName());

		}
		return u;
	}

// Checking if exists
	@RequestMapping(value = "/check/username", method = RequestMethod.GET, produces = "application/json")
	public boolean checkUserName(@RequestParam String username) {

		return userRepo.findByUserName(username) != null;
	}

// Checking if image is favourite
	@RequestMapping(value = "/check/image/favourite", method = RequestMethod.GET, produces = "application/json")
	public boolean checkIfImageFavourite(@RequestParam String username, Long id) {

		return userService.checkIfFavouriteImage(username, id);
	}
	
// Adding Image to Favourites
	@RequestMapping(value = "/add/image/favourite", method = RequestMethod.GET, produces = "application/json")
	public boolean addImageToFavourites(@RequestParam String username, Long id) {
		System.out.println(" RECEIVED IMAGE TO BE ADDED "+ username + " "+ id);

		return userService.addImToFavourites(username, id);
	}
	
	
// Removing Image from Favourites
	@RequestMapping(value = "/remove/image/favourite", method = RequestMethod.GET, produces = "application/json")
	public boolean removeImageFromFavourites(@RequestParam String username, Long id) {

		return userService.removeImFromFavourites(username, id);
	}
	

}
