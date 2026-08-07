package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AnnouncementResponse {
    private Long id;
    private String subject;
    private String details;
    private String postedBy;
    private LocalDateTime createdAt;
}