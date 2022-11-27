package com.example.app.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.app.entities.AuthDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String encodedPassword = passwordEncoder.encode("sahilpal");
		return new AuthDetails("admin",encodedPassword,true,Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
	}


}
