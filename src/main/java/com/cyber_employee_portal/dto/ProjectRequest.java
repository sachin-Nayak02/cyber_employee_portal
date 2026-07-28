package com.cyber_employee_portal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    private String employeeId;      // business employee code, e.g. "EMPl0003"
    private String departmentName;  // e.g. "IT"
    private String projectName;
    private String projectDuration;
    private String projectStatus;   // e.g. NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD
}