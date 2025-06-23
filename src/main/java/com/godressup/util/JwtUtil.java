package com.godressup.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secret = "ggeunit0very0strong0jwt0secret0key0123456789"; // JWT 서명 시 사용하는 비밀키
	private long expirationMs = 3600000; // 1시간(토큰 유효 시간)
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username) // 토큰의 주어(subject) 설정 - 주로 사용자 ID나 이름이 들어감
				.setIssuedAt(new Date()) // 토큰 발급 시각 설정(현재 시간)
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // 토큰 만료 시각 설정(현재 시간 + 1시간)
				.signWith(SignatureAlgorithm.HS256, secret) // HS256 알고리즘 + 비밀키로 서명함
				.compact(); // 토큰을 최종 문자열로 만들어 리턴
	}
}
