package com.cyber_employee_portal.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String employeeId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String maritalStatus;
    private String nationality;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String department;
    private String designation;
    private String employmentType;
    private LocalDate joiningDate;
    private Double salary;
    private String managerId;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String profileImage;
    private String roleName;
    private boolean active;
    private boolean emailVerified;
}