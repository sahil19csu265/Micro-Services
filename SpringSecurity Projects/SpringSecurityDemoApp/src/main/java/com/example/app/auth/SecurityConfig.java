package com.example.app.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	// Authentication
	@Bean
	public InMemoryUserDetailsManager getAuthenticationDetails(PasswordEncoder passwordEncoder) {
		UserDetails admin1 = User.withUsername("admin").password(passwordEncoder.encode("pal")).roles("ADMIN").build();
		UserDetails user1 = User.withUsername("user").password(passwordEncoder.encode("pal")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin1,user1);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		PasswordEncoder passwordEncoder =  PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
	
	// Authorization
	@Bean
	public SecurityFilterChain getAuthorizationDetails(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
			.authorizeHttpRequests()	
			.requestMatchers("/admin").hasRole("ADMIN")
			.requestMatchers("/user").hasAnyRole("ADMIN","USER")
			.anyRequest().permitAll()
			.and()
			.formLogin().and()
			.logout();
		return httpSecurity.build();
	}
	
}
