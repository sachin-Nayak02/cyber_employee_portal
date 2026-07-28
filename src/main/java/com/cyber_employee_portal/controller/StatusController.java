package com.cyber_employee_portal.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber_employee_portal.dto.StatusResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class StatusController {

    private final EmployeeRepository employeeRepository;

    // Check your own status
    @GetMapping("/my-status")
    public ResponseEntity<StatusResponse> getMyStatus(@AuthenticationPrincipal Employee employee) {
        return ResponseEntity.ok(buildStatus(employee));
    }

    // Check any employee's status by their employeeId (e.g. for "who's online" views)
    @GetMapping("/{employeeId}")
    public ResponseEntity<StatusResponse> getStatusByEmployeeId(@PathVariable String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("No employee found with employeeId: " + employeeId));
        return ResponseEntity.ok(buildStatus(employee));
    }

    private StatusResponse buildStatus(Employee employee) {
        boolean sessionActive = employee.getSessionExpiry() != null
                && employee.getSessionExpiry().isAfter(LocalDateTime.now());

        String status = sessionActive ? "ONLINE" : "OFFLINE";

        return new StatusResponse(employee.getEmployeeId(), employee.getName(), status);
    }
}