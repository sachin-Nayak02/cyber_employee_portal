package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusResponse {

    private String employeeId;
    private String name;
    private String status; // "ONLINE" or "OFFLINE"
}