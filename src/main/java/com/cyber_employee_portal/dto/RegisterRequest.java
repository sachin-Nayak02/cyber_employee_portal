package com.cyber_employee_portal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String gender;
    
    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    private String department;

    private String designation;

    private String employmentType; // Full-Time, Intern, Contract

    private LocalDate joiningDate;

    private Double salary;
    private String managerId;

    private String emergencyContactName;

    private String emergencyContactNumber;

    private String profileImage;
    
    //------------
    private String bloodGroup;
    private String maritalStatus;
    private String nationality;

    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;


    
    //----------------

    // If not provided, defaults to "EMPLOYEE" in the service layer
    private String roleName;



//	public String getEmployeeId() { 
//		// TODO Auto-generated method stub
//		return null;
//	}



	
	
}