package com.tim.document.web1.service;

import java.util.Optional;

import com.tim.document.web1.model.User;

public interface UserService {
	boolean login(String username,String password);
	Optional<User>findByUsername(String username);
}
