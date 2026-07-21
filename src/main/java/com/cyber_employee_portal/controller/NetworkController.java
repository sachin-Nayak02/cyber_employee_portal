package com.cyber_employee_portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber_employee_portal.dto.NetworkResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/network")
@RequiredArgsConstructor
public class NetworkController {

    private final EmployeeService employeeService;

    @GetMapping("/my-network")
    public ResponseEntity<List<NetworkResponse>> getMyNetwork(@AuthenticationPrincipal Employee employee) {
        return ResponseEntity.ok(employeeService.getMyNetwork(employee.getEmail()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<NetworkResponse>> findPeopleByName(
            @AuthenticationPrincipal Employee employee,
            @RequestParam String name) {
        return ResponseEntity.ok(employeeService.findPeopleByName(employee.getEmail(), name));
    }
}