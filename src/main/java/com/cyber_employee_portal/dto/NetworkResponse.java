package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NetworkResponse {

    private Long id;
    private String employeeId;
    private String name;
    private String email;
    private String designation;
    private String departmentName;
    private String profileImage;
}