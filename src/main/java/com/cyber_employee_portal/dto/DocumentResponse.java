package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DocumentResponse {
    private Long id;
    private String fileName;
    private String category;
    private String fileType;
    private String fileSizeDisplay; // e.g. "1.2 MB"
    private String description;
    private LocalDateTime uploadedOn;
}