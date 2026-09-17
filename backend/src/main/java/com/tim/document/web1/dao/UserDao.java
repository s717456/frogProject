package com.tim.document.web1.dao;

import java.util.Optional;

import com.tim.document.web1.model.User;

public interface UserDao {
	Optional<User>findByUsername(String username);
}
