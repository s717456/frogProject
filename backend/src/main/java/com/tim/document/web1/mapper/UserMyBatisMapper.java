package com.tim.document.web1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tim.document.web1.model.User;

//程式啟動時自動產生Mapper實作類別
// interface 是 MyBatis Mapper，會建立可以執行 SQL 的物件

@Mapper				
public interface UserMyBatisMapper {
	User findByUsername(@Param("username")String username);
}
