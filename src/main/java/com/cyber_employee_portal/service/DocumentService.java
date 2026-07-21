package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.DocumentResponse;
import com.cyber_employee_portal.dto.DocumentStatsResponse;
import com.cyber_employee_portal.entity.Document;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    DocumentResponse uploadDocument(MultipartFile file, String customFileName,
                                     String category, String description, Long employeeId);

    List<DocumentResponse> getAllDocuments(Long employeeId);

    Document getDocumentForDownload(Long documentId, Long employeeId);

    Resource loadFileAsResource(Document document);

    void deleteDocument(Long documentId, Long employeeId);

    DocumentStatsResponse getStats(Long employeeId);
}