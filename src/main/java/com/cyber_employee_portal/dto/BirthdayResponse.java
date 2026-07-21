package com.cyber_employee_portal.dto;

import java.time.LocalDate;

public class BirthdayResponse {

    private Long id;
    private String name;
    private String employeeId;
    private String department;
    private String designation;
    private LocalDate dateOfBirth;
    private String profileImage;
    private String message;

    public BirthdayResponse(Long id, String name, String employeeId, String department,
                             String designation, LocalDate dateOfBirth, String profileImage,String message) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
        this.dateOfBirth = dateOfBirth;
        this.profileImage = profileImage;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getProfileImage() { return profileImage; }
    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }
}