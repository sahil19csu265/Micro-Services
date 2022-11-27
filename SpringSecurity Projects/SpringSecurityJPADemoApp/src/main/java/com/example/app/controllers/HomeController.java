package com.example.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.services.UserDetailServiceImpl;

@RestController
public class HomeController {
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@RequestMapping("/")
	public String welcome() {
		return "<h1> Welcome to Home Page </h1>";
	}
	
	@RequestMapping("/user")
	public String userMessage(Principal principal) {
		return "<h1> Welcome <i>"+principal.getName()+"</i> to User Page  </h1>";
	}
	
	@RequestMapping("/admin")
	public String adminMessage(Principal principal) {
		return "<h1> Welcome <i>"+principal.getName()+"<i/> to Admin Page </h1>";
	}
	
	@RequestMapping("/add")
	public String addAdmin() {
		if(userDetailService.createAdmin()) {
			return "Sahil Admin Added Successfully!";
		}
		else {
			return "Admin Already existed";
		}
	}
}
