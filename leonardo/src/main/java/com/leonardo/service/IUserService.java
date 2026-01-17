package com.leonardo.service;

import com.leonardo.entity.User;

public interface IUserService {
	boolean RegisterUser(User user);
	
	User findByUsernameAndStatus(String username, int status);
	
	User findById(String id);
	
	User findOneByVerifyCode(String verifyCode);
	
	void updateUser(User user);
}
