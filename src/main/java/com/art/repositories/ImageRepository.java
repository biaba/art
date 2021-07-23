package com.art.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.art.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, QueryByExampleExecutor<Image> {
	
	public Image findByName(String artname);
	
	public Optional<Image> findById(Long id);
}
