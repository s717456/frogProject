package com.tim.document.web1.dao.impl;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.tim.document.web1.dao.UserDao;
import com.tim.document.web1.mapper.UserMyBatisMapper;
import com.tim.document.web1.model.User;

@Repository
public class UserDaoImpl implements UserDao{			//實作
	private final UserMyBatisMapper userMyBatisMapper;	//組合/依賴

	public UserDaoImpl(UserMyBatisMapper userMyBatisMapper) {
		super();
		this.userMyBatisMapper = userMyBatisMapper;
		//Spring 會把 MyBatis 建立好的 Mapper 物件交給 UserDaoImpl
	}
	
	@Override
	public Optional<User>findByUsername(String username){
		User user=userMyBatisMapper.findByUsername(username);
		return Optional.ofNullable(user);	//ofNullable()允許傳進來的值是 null
	}
}
