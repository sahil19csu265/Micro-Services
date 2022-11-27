package com.example.app.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.app.services.UserDetailServiceImpl;
import com.example.app.utils.JWTUtil;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String AuthorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if(AuthorizationHeader != null && AuthorizationHeader.startsWith("Bearer ")) {
			jwt = AuthorizationHeader.substring(7);
			username = jwtUtil.getUsernameFromToken(jwt);
		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userDetailService.loadUserByUsername(username);
			try {
				if(jwtUtil.validateJwtToken(jwt, user)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
							(user,jwt,user.getAuthorities());
					authToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request)
					);
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			catch(Exception e) {
				System.out.println("Wrong JWT");
			}
		}
		
		doFilter(request, response, filterChain);
	}

	
}
