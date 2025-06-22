package com.godressup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.godressup.domain.UserVO;
import com.godressup.persistence.UserMapper;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void addUser(UserVO user) throws Exception {
		userMapper.insert(user);
	}

	@Override
	public UserVO readUser(String username) throws Exception {
		return userMapper.selectById(username);
	}

	@Override
	public List<UserVO> readUserList() throws Exception {
		return userMapper.selectAll();
	}

	@Override
	public void updateUser(UserVO user) throws Exception {
		userMapper.update(user);
	}

	@Override
	public void deleteUser(String username) throws Exception {
		userMapper.delete(username);
	}

}
