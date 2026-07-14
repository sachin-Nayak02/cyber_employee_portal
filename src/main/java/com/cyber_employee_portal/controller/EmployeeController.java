package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.AdminUserRequest;
import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.exception.EmailAlreadyExistsException;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee", description = "Employee registration and management endpoints")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Register a new employee")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = employeeService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "Register a new employee by Admin")
    @PostMapping("/adminregister")
    public ResponseEntity<AdminUserResponse> adminregister(@Valid @RequestBody AdminUserRequest request) {
    	AdminUserResponse response = employeeService.generateEmpId(request);
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "Update an employee (only the fields you send get changed)")
    @PatchMapping("/update/{id}")
    public ResponseEntity<RegisterResponse> updateEmployee(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateEmployeeRequest request) {
        RegisterResponse response = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(response);
    }
 
    @Operation(summary = "Delete an employee by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EmployeeNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailExists(EmailAlreadyExistsException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}