package com.godressup.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/ping")
	public String ping() {
		return "OK";
	}
	
	// @PostMapping("/login")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) throws Exception {
		String username = loginData.get("username");
		String inputPassword = loginData.get("password");
		
		logger.info("로그인 요청: {}", username);
		
		// 1. DB에서 사용자 조회
		UserVO user = userService.readUser(username);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 사용자 없음");
		}
		
		// 2. 비밀번호 비교
		if(!user.getPassword().equals(inputPassword)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 비밀번호 불일치");
		}
	
		// 3. JWT 토큰 생성
		String token = jwtUtil.generateToken(username);
			
		// 4. 토큰을 JSON 형태로 응답
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
			
		return ResponseEntity.ok(response);
	}
}
