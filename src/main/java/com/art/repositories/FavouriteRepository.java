package com.art.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.art.entities.Favourite;
import com.art.entities.Image;
import com.art.entities.User;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
	
	public List<Favourite> findByUserAndFavImage(User user, Image image);
	
	@Transactional
	public Long deleteByUserAndFavImage(User user, Image image);
	
	public Favourite saveAndFlush(Favourite favourite);

}
