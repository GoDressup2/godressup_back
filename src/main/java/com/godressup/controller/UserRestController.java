package com.godressup.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.godressup.domain.UserVO;
import com.godressup.service.UserService;
import com.godressup.util.JwtUtil;

@RestController
@RequestMapping(value="/api/user")
public class UserRestController {
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired(required=true)
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	// @PostMapping("/register")
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserVO userVO) throws Exception {
		userService.addUser(userVO);
		logger.info(userVO.toString());
		logger.info("/api/user/register REST-API POST method called. then method executed.");
		
		return ResponseEntity.ok("회원가입 성공");
	}
	
	// @PostMapping("/login")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody UserVO userVO) throws Exception {
		logger.info("로그인 요청: {}", userVO.getUsername());
		
		String username = userVO.getUsername();
		String password = userVO.getPassword();
		
		// 1. 로그인 인증
		boolean authenticated = userService.login(username, password);
		
		if(!authenticated) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호 오류");
		}
	
		// 2. JWT 토큰 생성
		String token = jwtUtil.generateToken(username);
			
		// 3. 토큰을 JSON 형태로 응답
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
			
		return ResponseEntity.ok(response);
	}
}
