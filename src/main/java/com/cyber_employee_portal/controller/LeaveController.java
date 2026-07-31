package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.LeaveRequest;
import com.cyber_employee_portal.dto.LeaveResponse;
import com.cyber_employee_portal.dto.LeaveStatusUpdate;
import com.cyber_employee_portal.entity.Leave;
import com.cyber_employee_portal.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // ==========================================
    // EMPLOYEE PANEL APIs
    // ==========================================

    // Employee applies for a new leave
    @PostMapping("/apply")
    public ResponseEntity<Leave> applyLeave(@RequestBody LeaveRequest request) {
        Leave newLeave = leaveService.applyForLeave(request);
        return ResponseEntity.ok(newLeave);
    }

    // Employee checks their own leave history
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Leave>> getEmployeeLeaves(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getEmployeeLeaves(employeeId));
    }

    // ==========================================
    // ADMIN PANEL APIs
    // ==========================================

    // Admin fetches all pending leaves to review
    @GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getPendingLeaves());
    }

    // Admin approves or rejects a leave
    @PutMapping("/{leaveId}/status")
    public ResponseEntity<Leave> updateLeaveStatus(
            @PathVariable Long leaveId, 
            @RequestBody LeaveStatusUpdate updateRequest) {
        
        Leave updatedLeave = leaveService.updateLeaveStatus(leaveId, updateRequest.getStatus());
        return ResponseEntity.ok(updatedLeave);
    }

    // ==========================================
    // PROFILE DISPLAY API
    // ==========================================

    // API to get active leaves to display on profiles
    @GetMapping("/today")
    public ResponseEntity<List<LeaveResponse>> getActiveLeaves() {
        LocalDate today = LocalDate.now();
        List<LeaveResponse> activeLeaves = leaveService.getActiveLeaves(today);
        return ResponseEntity.ok(activeLeaves);
    } 
}