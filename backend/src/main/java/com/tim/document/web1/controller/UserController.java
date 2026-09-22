package com.tim.document.web1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tim.document.web1.model.User;
import com.tim.document.web1.service.UserService;
import com.tim.document.web1.utility.JwtUtility;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtility jwtUtility;

    public UserController(
            UserService userService,
            JwtUtility jwtUtility) {

        this.userService = userService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/login")//驗證帳密，成功後產生 JWT
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody User user) {

        boolean success = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        Map<String, Object> response = new HashMap<>();

        if (!success) {
            response.put("success", false);
            response.put("message", "帳號密碼錯誤");

            return ResponseEntity.status(401).body(response);
        }

        String token = jwtUtility.generateToken(
                user.getUsername()
        );

        response.put("success", true);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(
            HttpServletRequest request) {

        String username =
                (String) request.getAttribute("username");//取得 JWT Filter 事先放進 request 的 username

        return ResponseEntity.ok(username);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok("請於前端清除 JWT");
    }
}