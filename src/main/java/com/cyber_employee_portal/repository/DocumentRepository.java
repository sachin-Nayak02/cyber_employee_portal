package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByEmployeeIdOrderByUploadedOnDesc(Long employeeId);
    Optional<Document> findByIdAndEmployeeId(Long id, Long employeeId);
    long countByEmployeeIdAndFileTypeIgnoreCase(Long employeeId, String fileType);
}