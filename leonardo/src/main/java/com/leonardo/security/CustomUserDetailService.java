package com.leonardo.security;

import com.leonardo.entity.User;
import com.leonardo.repository.UserRepository;
import com.leonardo.service.impl.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userRepository.findOneByUsernameAndStatus(username, 1);
			return new CustomUserDetail(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
	
	
	public UserDetails loadUserById(String id) throws UsernameNotFoundException {
		User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
        return new CustomUserDetail(user);
    }
}
