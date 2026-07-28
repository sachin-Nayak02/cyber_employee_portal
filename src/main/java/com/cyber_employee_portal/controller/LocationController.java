package com.cyber_employee_portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber_employee_portal.dto.LocationResponse;
import com.cyber_employee_portal.entity.Employee;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @GetMapping("/my-location")
    public ResponseEntity<LocationResponse> getMyLocation(@AuthenticationPrincipal Employee employee) {

        LocationResponse location = new LocationResponse(
                employee.getCity(),
                employee.getState(),
                employee.getCountry()
        );

        return ResponseEntity.ok(location);
    }
}