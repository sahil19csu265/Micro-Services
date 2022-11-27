package com.example.app.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.app.DAO.UserDAO;
import com.example.app.entities.AuthDetails;
import com.example.app.entities.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// here the users can be hardcoded userDetails also
		Optional<User> user = userDAO.findByusername(username);
		user.orElseThrow(()-> new UsernameNotFoundException("This username does not exists !"));
		return user.map(AuthDetails::new).get();
	}
	
	public boolean createAdmin() {
		if(userDAO.findByusername("sahil").isEmpty()) {
			String encodedPassword = passwordEncoder.encode("pal");
			userDAO.save(new User("sahil",encodedPassword,true,"ROLE_ADMIN"));
			return true;
		}
		return false;
	}

}
