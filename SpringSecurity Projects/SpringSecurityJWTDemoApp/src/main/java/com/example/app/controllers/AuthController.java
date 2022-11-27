package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.models.AuthRequest;
import com.example.app.models.AuthResponse;
import com.example.app.services.UserDetailServiceImpl;
import com.example.app.utils.JWTUtil;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping(path="/authenticate")
	public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(BadCredentialsException bce) {
			throw new Exception("Incorrect Username and Password");
		}
		final UserDetails user = userDetailService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateJwtToken(user);
		return ResponseEntity.ok(new AuthResponse(jwt));
	}
}
