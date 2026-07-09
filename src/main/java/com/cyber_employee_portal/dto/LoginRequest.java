package com.cyber_employee_portal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class LoginRequest {

	@NotBlank @Email
    private String email;

    @NotBlank
    private String password;
}
