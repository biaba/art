package com.art.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.art.entities.User;
import com.art.entities.learning.Item;
import com.art.entities.learning.SearchResponse;
import com.art.repositories.UserRepository;

// Used for learning purposes only.
// @Controller can be used both for getting jsp and json (REST)
// whereas @UserController is used for REST communication only

@Controller
public class LearningController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserRepository userRepo;

// returns view	+ makes REST request
	@RequestMapping("/jsp")
	public String index(Map<String, Object> model) {
		SearchResponse results = restTemplate.getForObject(
				"https://collectionapi.metmuseum.org/public/collection/v1/search?q=gogh", SearchResponse.class);

		int id[] = results.getObjectIDs();

		Item item = restTemplate.getForObject("https://collectionapi.metmuseum.org/public/collection/v1/objects/{id}",
				Item.class, String.valueOf(id[0]));

		model.put("message", item.getPrimaryImage());
		return "main";
	}

// returns string
	@RequestMapping("/string")
	@ResponseBody
	public String foo() {
		return "Response!";
	}

// responds to REST request
	@RequestMapping(value = "/useri", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User[] getAll(Principal pi) {

		List<User> users = userRepo.findAll();
		return users.toArray(new User[users.size()]);
	}

}