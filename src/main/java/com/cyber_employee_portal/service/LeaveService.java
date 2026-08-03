package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.LeaveRequest;
import com.cyber_employee_portal.dto.LeaveResponse;
import com.cyber_employee_portal.entity.Leave;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {
    Leave applyForLeave(LeaveRequest request);
    List<Leave> getEmployeeLeaves(Long employeeId);
    Leave updateLeaveStatus(Long leaveId, String status);
    List<LeaveResponse> getActiveLeaves(LocalDate today);
    List<LeaveResponse> getPendingLeaves();
}