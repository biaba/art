package com.art.services;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.art.entities.Favourite;
import com.art.entities.Image;
import com.art.entities.Role;
import com.art.entities.User;
import com.art.repositories.FavouriteRepository;
import com.art.repositories.ImageRepository;
import com.art.repositories.UserRepository;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	FavouriteRepository favRepo;

	@Autowired
	ImageRepository imageRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	public boolean checkIfFavouriteImage(String userName, Long id) {

		User u = userRepo.findByUserName(userName);
		Optional<Image> i = imageRepo.findById(id);

		return favRepo.findByUserAndFavImage(u, i.get()).size() > 0;
	}

	public boolean addImToFavourites(String userName, Long id) {

		User u = userRepo.findByUserName(userName);
		Optional<Image> i = imageRepo.findById(id);
		Favourite fav = new Favourite(u, null, i.get(), null);	
		
		System.out.println(" SERVICE ADDING NEW FAV "+ fav.toString());

		return favRepo.saveAndFlush(fav)!=null;
	}

	public boolean removeImFromFavourites(String userName, Long id) {

		User u = userRepo.findByUserName(userName);
		Optional<Image> i = imageRepo.findById(id);

		return favRepo.deleteByUserAndFavImage(u, i.get())!=null;
	}

}
