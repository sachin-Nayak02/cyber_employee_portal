package com.cyber_employee_portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}