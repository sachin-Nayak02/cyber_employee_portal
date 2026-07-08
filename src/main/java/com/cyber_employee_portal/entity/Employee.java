package com.cyber_employee_portal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String employeeId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;
 
    @Column
    private LocalDate dateOfBirth;
    
    @Column
    private String gender;

    @Column
    private String bloodGroup; 

    @Column
    private String maritalStatus;

    @Column
    private String nationality;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String pincode;

    @Column
    private String department;

    @Column
    private String designation;

    @Column
    private String employmentType; // Full-Time, Intern, Contract

    @Column
    private LocalDate joiningDate;

    @Column
    private Double salary;

    @Column
    private String managerId;

    @Column
    private String emergencyContactName;

    @Column
    private String emergencyContactNumber;

    @Column
    private String profileImage;

    @Column
    private Boolean active = true;

    @Column
    private Boolean emailVerified = false;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt; 

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
