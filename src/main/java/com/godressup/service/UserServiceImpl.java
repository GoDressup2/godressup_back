package com.godressup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godressup.domain.UserVO;
import com.godressup.persistence.UserMapper;

@Service
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

	@Override
	public boolean login(String username, String password) throws Exception {
		UserVO user = userMapper.selectById(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
