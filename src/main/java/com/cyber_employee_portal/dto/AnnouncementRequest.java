package com.cyber_employee_portal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Details are required")
    private String details;
}