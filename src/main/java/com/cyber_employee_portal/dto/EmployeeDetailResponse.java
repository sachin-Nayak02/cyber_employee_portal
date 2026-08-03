package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDetailResponse {
    private Long id;
    private String employeeId;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String departmentName;
    private String designation;
    private String employmentType;
    private LocalDate joiningDate;
    private Double salary;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String roleName;
    private Boolean active;
}