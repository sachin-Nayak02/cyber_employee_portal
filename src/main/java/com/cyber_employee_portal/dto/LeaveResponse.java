package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LeaveResponse {
    private Long id;
    private Long employeeDbId;      // numeric id, for fetching profile image
    private String employeeName;
    private String employeeId;      // string code like EMP0005
    private String department;
    private String designation;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long days;
    private String reason;
    private String status;
}