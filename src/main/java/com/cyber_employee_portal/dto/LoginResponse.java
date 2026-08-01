package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class LoginResponse {
	
	// LoginResponse.java
	private Long id;
	private String token;
	private String tokenType = "Bearer";
    private String email;
    private String role;

    public LoginResponse(Long id, String token, String email, String role) {
    	this.id = id;
        this.token = token;
        this.email = email;
        this.role = role;
    }
}
 