package com.zw.admin.server.service;

import com.zw.admin.server.dto.UserDto;
import com.zw.admin.server.model.User;

public interface UserService {

	User saveUser(UserDto userDto);

	void forgetPassword(String username, String newPassword);

	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
