package com.su.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.su.springboot.app.commons.models.entity.User;
import com.su.springboot.app.oauth.clients.UserFeignClient;

import feign.FeignException;

@Service
public class UserService implements IUserService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient client;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			User user = client.findByUsername(username);

			List<GrantedAuthority> authorities = 
					user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.peek(authority -> log.info("Role: " + authority.getAuthority()))
					.collect(Collectors.toList());

			log.info("User authenticated: " + username);

			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true, true, authorities);

		} catch (FeignException e) {
			String error = "Error en el login, no existe el usuario '" + username + "' en el sistema";
			log.error(error);

			throw new UsernameNotFoundException(error);
		}
	}
	
	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public User update(User user, Long id) {
		return client.update(user, id);
	}

}
