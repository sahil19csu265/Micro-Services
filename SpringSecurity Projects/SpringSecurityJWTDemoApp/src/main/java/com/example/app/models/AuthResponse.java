package com.example.app.models;

public class AuthResponse {

	private final String jwtString;

	public AuthResponse(String jwtString) {
		this.jwtString = jwtString;
	}

	public String getJwtString() {
		return jwtString;
	}

	@Override
	public String toString() {
		return "AuthResponse [jwtString=" + jwtString + "]";
	}
	
	
}
