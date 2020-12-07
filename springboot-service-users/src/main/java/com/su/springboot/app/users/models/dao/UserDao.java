package com.su.springboot.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.su.springboot.app.commons.models.entity.User;

@RepositoryRestResource(path="users")
public interface UserDao extends PagingAndSortingRepository<User, Long> {

	@RestResource(path="find-username")
	public User findByUsername(@Param("username") String username);
	
	@Query("select u from User u where u.username=?1")
	public User gettingUserByUsername(String username);
	
}
