package com.cyber_employee_portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber_employee_portal.dto.DepartmentResponse;
import com.cyber_employee_portal.dto.DepartmentSummaryResponse;
import com.cyber_employee_portal.dto.EmployeeDetailResponse;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Department", description = "Department management endpoints")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Get all departments")
    @GetMapping
    public ResponseEntity<List<DepartmentSummaryResponse>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments()); 
    }

    @Operation(summary = "Get all employees belonging to a department")
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDetailResponse>> getEmployeesByDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getEmployeesByDepartment(id));
    }
}