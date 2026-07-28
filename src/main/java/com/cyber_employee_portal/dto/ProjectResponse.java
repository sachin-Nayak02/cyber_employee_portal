package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectResponse {

    private Long projectId;
    private String employeeId;
    private String employeeName;
    private String departmentName;
    private String projectName;
    private String projectDuration;
    private String projectStatus;
}