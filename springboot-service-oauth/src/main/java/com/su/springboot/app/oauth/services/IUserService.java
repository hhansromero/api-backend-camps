package com.su.springboot.app.oauth.services;

import com.su.springboot.app.commons.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);

	public User update(User user, Long id);

}
