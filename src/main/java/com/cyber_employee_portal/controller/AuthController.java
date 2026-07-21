package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.LoginRequest;
import com.cyber_employee_portal.dto.LoginResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.security.JwtUtil;
import com.cyber_employee_portal.security.TokenBlacklistService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

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
    
    @Operation(summary = "Logout and invalidate the current JWT token")
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            long expiryMillis = jwtUtil.getExpirationMillis(token);
            tokenBlacklistService.blacklistToken(token, expiryMillis);
        }

        Map<String, String> response = new HashMap<>(); 
        response.put("message", "Logged out successfully");
        return ResponseEntity.ok(response);
    }
    
  
}