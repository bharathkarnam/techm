package com.digital.tech.techm.service;


import java.net.URISyntaxException;

import com.digital.tech.service.model.User;

public interface UserEntityService {

	public User insertUser(User user);
	public User viewUser(String id) throws URISyntaxException;
}
