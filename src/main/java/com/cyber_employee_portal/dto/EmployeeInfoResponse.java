package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoResponse {
	private Long id;   
    private String name;
    private String employeeId;
    private String roleName;
    private String departmentName;
    private String designation;
    private String profileImage;
}