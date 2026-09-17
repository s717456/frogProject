package com.tim.document.web1;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.tim.document.web1.mapper")
public class FrogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrogProjectApplication.class, args);
	}

}
