package com.cyber_employee_portal.dto;

import java.time.LocalDate;
import java.time.Period;

public class AnniversaryResponse {

    private Long id;
    private String name;
    private String employeeId;
    private String department;
    private String designation;
    private LocalDate joiningDate;
    private int yearsCompleted;
    private String message;

    public AnniversaryResponse(Long id, String name, String employeeId, String department,
                                String designation, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.yearsCompleted = Period.between(joiningDate, LocalDate.now()).getYears();
        this.message = "🎊 Happy Work Anniversary, " + name + "! (" + yearsCompleted + " year"
                + (yearsCompleted == 1 ? "" : "s") + ")";
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public int getYearsCompleted() { return yearsCompleted; }
    public String getMessage() { return message; }
}