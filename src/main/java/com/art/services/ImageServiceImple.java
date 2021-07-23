package com.art.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.art.entities.CategoryType;
import com.art.entities.Image;
import com.art.entities.ImageSearchCriteria;
import com.art.entities.User;
import com.art.repositories.ImageRepository;
import com.art.repositories.UserRepository;

@Service
public class ImageServiceImple implements ImageService {

	@Autowired
	ImageRepository imageRepo;
	
	@Autowired
	UserRepository userRepo;

	public Image createOrUpdateImage(Image theImage) {

		if (theImage.getId() != null) {
			Optional<Image> image = imageRepo.findById(theImage.getId());
			Image newImage = image.get();
			if (theImage.getName() != null) {
				newImage.setName(theImage.getName());
			}
			if (theImage.getFileUrl() != null) {
				newImage.setFileUrl(theImage.getFileUrl());
			}
			if (theImage.getCreator() != null) {
				newImage.setCreator(theImage.getCreator());
			}
			if (theImage.getCategory() != null) {
				newImage.setCategory(theImage.getCategory());
			}
			if (theImage.isForSale() != newImage.isForSale()) {
				newImage.setForSale(theImage.isForSale());
			}
			if (theImage.isSold() != newImage.isSold()) {
				newImage.setSold(theImage.isSold());
			}
			if (theImage.getBuyer() != null) {
				newImage.setBuyer(theImage.getBuyer());
			}

			return imageRepo.save(newImage);

		} else {
			return imageRepo.save(theImage);
		}
	}
	
	public boolean findByName(String artname) {
		return imageRepo.findByName(artname)!=null;
	}

	public Image getImage(long id) {
		return imageRepo.getOne(id);
	}

	@Override
	public List<Image> findAllByCriteria(ImageSearchCriteria criteria) {
		Image i = new Image();
		User u = userRepo.findByUserName(criteria.getArtist());
		i.setCreator(u);
		i.setName(criteria.getArtName());
		i.setCategory(criteria.getCategory());
		//if(criteria.getCategory()!=null) {i.setCategory(criteria.getCategory());}
		//if(criteria.isForSale()!=null) {i.setForSale(criteria.isForSale());}
		i.setForSale(criteria.isForSale());
		System.out.println("LOOKING FOR IMAGE: "+i.toString());
		return imageRepo.findAll(Example.of(i));
	}	

}
