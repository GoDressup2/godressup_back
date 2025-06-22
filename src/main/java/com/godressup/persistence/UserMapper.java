package com.godressup.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.godressup.domain.UserVO;

@Mapper
public interface UserMapper {
	void insert(UserVO user);
	UserVO selectById(String username);
	List<UserVO> selectAll();
	void update(UserVO user);
	void delete(String username);
}
