package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.LoginRequest;
import com.cyber_employee_portal.dto.LoginResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            Employee employee = (Employee) authentication.getPrincipal();
            String token = jwtUtil.generateToken(employee);

            LoginResponse response = new LoginResponse(
                    token,
                    employee.getEmail(),
                    employee.getRole().getName()
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}