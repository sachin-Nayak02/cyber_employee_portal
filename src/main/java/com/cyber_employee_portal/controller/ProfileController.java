package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.EmployeeProfileDto;
import com.cyber_employee_portal.dto.ProfileResponse;
import com.cyber_employee_portal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{employeeId}")
    public ProfileResponse getProfile(@PathVariable Long employeeId) {
        return profileService.getProfile(employeeId);
    }

    @PutMapping("/{employeeId}")
    public EmployeeProfileDto updateProfile(@PathVariable Long employeeId,
                                             @RequestBody EmployeeProfileDto updated) {
        return profileService.updateProfile(employeeId, updated);
    }
}