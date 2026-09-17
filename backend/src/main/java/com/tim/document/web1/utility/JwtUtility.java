package com.tim.document.web1.utility;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//產生token,解析token,驗證token
@Component
public class JwtUtility {
	
	private final String secret;
	private final long expiration;//設定token有效時間(毫秒)
	
	public JwtUtility(
			@Value("${jwt.secret}")String secret, 
			@Value("${jwt.expiration}") long expiration) {
		this.secret = secret;
		this.expiration = expiration;
	}
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getKey())
				.compact();
	}
	public String extractUsername(String token) {

	    return Jwts.parser()
	            .verifyWith(getKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
}
