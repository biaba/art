package com.art.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.art.entities.Image;
import com.art.entities.ImageSearchCriteria;
import com.art.entities.User;
import com.art.services.ImageServiceImple;

@RestController
public class ImageSearchController {

	@Autowired
	ImageServiceImple imageService;

	@RequestMapping(value = "/advancedSearch", method = RequestMethod.POST, produces = "application/json")
	public List<Image> advancedSearch(@RequestBody ImageSearchCriteria criteria) {

		System.out.println("INCOMING CRITERIA: " + criteria.toString());

		List<Image> l = imageService.findAllByCriteria(criteria);
		for (Image i : l) {
			System.out.println(i.getId() + " art name: " + i.getName());
		}

		return l;
	}

	// Checking if artname exists
	@RequestMapping(value = "/check/artname", method = RequestMethod.GET, produces = "application/json")
	public boolean checkArtName(@RequestParam String artname) {

		return imageService.findByName(artname);
	}

}
