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
import org.springframework.security.access.AccessDeniedException;
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
    
    @Operation(summary = "Partially update an employee (only supplied fields change)")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> patchUpdate(@PathVariable Long id,
                                          @Valid @RequestBody UpdateEmployeeRequest request) {
        return handleUpdate(id, request, true);
    }
 
    @Operation(summary = "Delete an employee by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
//    @Operation(summary = "Fully replace an employee's editable fields")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> putUpdate(@PathVariable Long id,
//                                        @Valid @RequestBody UpdateEmployeeRequest request) {
//        return handleUpdate(id, request, false);
//    }

    private ResponseEntity<?> handleUpdate(Long id, UpdateEmployeeRequest request, boolean isPartial) {
        try {
            RegisterResponse response = employeeService.updateEmployee(id, request, isPartial);
            return ResponseEntity.ok(response);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (AccessDeniedException e) { 
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (EmailAlreadyExistsException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}