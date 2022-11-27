package com.example.app.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Bean
	public JdbcUserDetailsManager getAuthenticationDetails(PasswordEncoder passwordEncoder) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(datasource);
		
		// create users
		addAdmin();
		manager.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
		manager.setAuthoritiesByUsernameQuery("select username, role from users where username=?");
		
		return manager;
	}
	
	private void addAdmin() {
		System.out.println("Adding admin details : ");
		String encodedPassword = getPasswordEncoder().encode("sahilpal");
		this.jdbcTemplate.update("delete from users");
		String sql = "insert into users values(?,?,?,?,?)";
		Object[] args = {1,"sahil",encodedPassword,"ROLE_ADMIN",1};
		this.jdbcTemplate.update(sql, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
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
