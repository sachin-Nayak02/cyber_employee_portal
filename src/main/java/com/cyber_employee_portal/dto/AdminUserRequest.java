package com.cyber_employee_portal.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
 @Setter
 @Data
public class AdminUserRequest {

	@NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
	
	@NotNull(message="salary is required")
	private Double salary;  
}
