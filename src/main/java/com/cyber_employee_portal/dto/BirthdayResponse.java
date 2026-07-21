package com.cyber_employee_portal.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BirthdayResponse {

    private Long id;
    private String name;
    private String employeeId;
    private String department;
    private String designation;
    private LocalDate dateOfBirth;
    private String profileImage;
    private String message;

 
}