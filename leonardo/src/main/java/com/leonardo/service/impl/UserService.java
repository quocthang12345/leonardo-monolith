package com.leonardo.service.impl;

import com.leonardo.entity.User;
import com.leonardo.repository.UserRepository;
import com.leonardo.service.IUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public boolean RegisterUser(User user) {
		if(user.getUsername() != null) {
			User checkUser = userRepo.findOneByUsernameAndStatus(user.getUsername(), user.getStatus());
			if(checkUser == null) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepo.save(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User findByUsernameAndStatus(String username, int status) {
		return userRepo.findOneByUsernameAndStatus(username, status);
	}

	@Override
	public User findById(String id) {
		return userRepo.findById(id).get();
	}
	
	@Override
	public User findOneByVerifyCode(String verifyCode) {
		return userRepo.findOneByVerifyCode(verifyCode);
	}
	
	@Override
	public void updateUser(User user) {
		Optional.ofNullable(user).map(item -> userRepo.save(item)).orElseThrow();
	}
}
