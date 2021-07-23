package com.art.services;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

	public boolean checkIfFavouriteImage(String userName, Long id);

	public boolean addImToFavourites(String userName, Long id);

	public boolean removeImFromFavourites(String userName, Long id);

}
