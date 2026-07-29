package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.DashboardResponse;
import com.cyber_employee_portal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(dashboardService.getDashboardData(email));
    }
}