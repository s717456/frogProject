package com.tim.document.web1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.document.web1.model.User;
import com.tim.document.web1.service.UserService;
import com.tim.document.web1.utility.JwtUtility;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	private final JwtUtility jwtUtility;

	public UserController(UserService userService,JwtUtility jwtUtility) {
		super();
		this.userService = userService;
		this.jwtUtility=jwtUtility;
	}
	
	@PostMapping("/login")
	public Map<String,Object>login(@RequestBody User user){
		boolean success=userService.login(
				user.getUsername(),user.getPassword());
		Map<String, Object>response=new HashMap<>();
		if (success) {
			String token=jwtUtility.generateToken(user.getUsername());
			response.put("success",true);
			response.put("token",token);
		}
		else {
			response.put("success",false);
			response.put("token", "帳號密碼錯誤");
		}
		return response;
	}
	@GetMapping("/me")
	public ResponseEntity<String>getCurrentUser(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username!=null) {
			return ResponseEntity.ok(username);
		}
		return ResponseEntity.status(401).body("未登入");
	}
	@PostMapping("/logout")
	public ResponseEntity<String>logout(HttpSession session){
		session.invalidate();//讓session失效
		return ResponseEntity.ok("登出成功");
	}
}
