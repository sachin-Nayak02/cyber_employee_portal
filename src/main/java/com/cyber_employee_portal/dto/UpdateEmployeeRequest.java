package com.cyber_employee_portal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateEmployeeRequest {

    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

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

    // Admin-only fields — ignored/rejected for non-admin callers in the service layer.
    private String roleName;
    private Boolean active;
}