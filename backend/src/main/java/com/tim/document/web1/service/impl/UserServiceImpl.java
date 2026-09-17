package com.tim.document.web1.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim.document.web1.dao.UserDao;
import com.tim.document.web1.model.User;
import com.tim.document.web1.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	private final UserDao userDao;
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao=userDao;
	}
	@Override
	public boolean login(String username,String password) {
		Optional<User>user=userDao.findByUsername(username);
		if(user.isPresent()) {
			return user.get().getPassword().equals(password);
		}
		return false;
	}
	@Override
	public Optional<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
}
