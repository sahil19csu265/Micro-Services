package com.example.app.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.User;

public interface UserDAO extends JpaRepository<User,Integer>{
	
	Optional<User> findByusername(String username);
}
