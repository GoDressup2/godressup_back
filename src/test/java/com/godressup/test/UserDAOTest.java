package com.godressup.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.godressup.config.RootConfig;
import com.godressup.domain.UserVO;
import com.godressup.persistence.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class UserDAOTest {

	@Autowired
	private UserMapper userMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	
	//@Test
	public void testUserMapperInsert() throws Exception {
		UserVO user = new UserVO();
		user.setUsername("test");
		user.setPassword("1234");
		user.setEmail("test@tukorea.ac.kr");
		user.setMobile("010-1234-5678");
		try {
			userMapper.insert(user);
			logger.info("user.toString(): " + user.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUserMapperSelectById() throws Exception {
		try {
			UserVO user = userMapper.selectById("test");
			logger.info("user.toString(): " + user.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUserMapperSelectAll() throws Exception {
		try {
			List<UserVO> userList = userMapper.selectAll();
			logger.info("userList.toString(): " + userList.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUserMapperUpdate() throws Exception {
		UserVO user = new UserVO();
		user.setUsername("test");
		user.setPassword("12345678");
		user.setEmail("test@tukorea.ac.kr");
		user.setMobile("010-1234-5678");
		try {
			userMapper.update(user);
			logger.info("user.toString(): " + user.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUserMapperDelete() throws Exception {
		try {
			userMapper.delete("test");
			logger.info("userMapper.delete(\"test\") executed!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
