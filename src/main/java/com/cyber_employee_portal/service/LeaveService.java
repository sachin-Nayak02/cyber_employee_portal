package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.LeaveRequest;
import com.cyber_employee_portal.entity.Leave;
import java.util.List;

public interface LeaveService {
    Leave applyForLeave(LeaveRequest request);
    List<Leave> getPendingLeaves();
    List<Leave> getEmployeeLeaves(Long employeeId);
    Leave updateLeaveStatus(Long leaveId, String status);
}