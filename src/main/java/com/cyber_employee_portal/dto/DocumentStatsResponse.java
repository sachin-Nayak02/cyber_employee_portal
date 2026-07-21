package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocumentStatsResponse {
    private long totalDocuments;
    private long pdfCount;
    private long wordCount;
    private long imageCount;
    private long othersCount;
    private double totalSizeMB;
    private double documentsSizeMB; // pdf + docx grouped as "documents"
    private double imagesSizeMB;
    private double othersSizeMB;
}