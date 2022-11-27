package com.example.app.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.app.filters.JwtRequestFilter;
import com.example.app.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	
	@Autowired
	@Lazy
	private UserDetailServiceImpl userServices;
	
	@Autowired
	@Lazy
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationDetails(PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userServices);
		provider.setPasswordEncoder(getPasswordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManagerFactoryBean getAuthenticationManager() {
		return new AuthenticationManagerFactoryBean();
	}
	
	// Authorization
	@Bean
	public SecurityFilterChain getAuthorizationDetails(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/authenticate").permitAll()
			.requestMatchers("/admin").hasRole("ADMIN")
			.requestMatchers("/user").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.logout();
		httpSecurity.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
}
