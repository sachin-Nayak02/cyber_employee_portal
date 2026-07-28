package com.cyber_employee_portal.dto;

import java.time.LocalDateTime;

public class CurrentDateTimeResponse {

    private LocalDateTime currentDateTime;

    public CurrentDateTimeResponse() {
    }

    public CurrentDateTimeResponse(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}