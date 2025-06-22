package com.godressup.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
	private String id;
	private String username;
	private String password;
	private String email;
	private String mobile;
}
