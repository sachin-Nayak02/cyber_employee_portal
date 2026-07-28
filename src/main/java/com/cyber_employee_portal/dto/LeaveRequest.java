package com.cyber_employee_portal.dto;

import java.time.LocalDate;

public class LeaveRequest {
    
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    // --- GETTERS AND SETTERS ---

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
   
        // Add this new variable
        private String leaveType;

        // ... existing getters and setters ...

        // Add these new getters and setters at the bottom:
        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }
    }
