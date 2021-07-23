package com.art.services;

import java.util.List;

import com.art.entities.Image;
import com.art.entities.ImageSearchCriteria;

public interface ImageService {

	public List<Image> findAllByCriteria(ImageSearchCriteria criteria);

	public boolean findByName(String artname);

}
