package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.DocumentResponse;
import com.cyber_employee_portal.dto.DocumentStatsResponse;
import com.cyber_employee_portal.entity.Document;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Documents", description = "Employee document upload, view, and download")
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @Operation(summary = "Upload a new document")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponse> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "fileName", required = false) String fileName,
            @RequestParam("category") String category,
            @RequestParam(value = "description", required = false) String description,
            @AuthenticationPrincipal Employee employee) {

        DocumentResponse response = documentService.uploadDocument(
                file, fileName, category, description, employee.getId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all documents for the logged-in employee")
    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAllDocuments(@AuthenticationPrincipal Employee employee) {
        return ResponseEntity.ok(documentService.getAllDocuments(employee.getId()));
    }

    @Operation(summary = "Get document statistics (counts, storage used)")
    @GetMapping("/stats")
    public ResponseEntity<DocumentStatsResponse> getStats(@AuthenticationPrincipal Employee employee) {
        return ResponseEntity.ok(documentService.getStats(employee.getId()));
    }

    @Operation(summary = "View a document inline in the browser")
    @GetMapping("/{id}/view")
    public ResponseEntity<Resource> viewDocument(@PathVariable Long id,
                                                  @AuthenticationPrincipal Employee employee) {
        Document document = documentService.getDocumentForDownload(id, employee.getId());
        Resource resource = documentService.loadFileAsResource(document);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resolveContentType(document.getFileType())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }

    @Operation(summary = "Download a document")
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id,
                                                       @AuthenticationPrincipal Employee employee) {
        Document document = documentService.getDocumentForDownload(id, employee.getId());
        Resource resource = documentService.loadFileAsResource(document);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }

    @Operation(summary = "Delete a document")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id,
                                                @AuthenticationPrincipal Employee employee) {
        documentService.deleteDocument(id, employee.getId());
        return ResponseEntity.noContent().build();
    }

    private String resolveContentType(String extension) {
        return switch (extension.toLowerCase()) {
            case "pdf" -> "application/pdf";
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "doc" -> "application/msword";
            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default -> "application/octet-stream";
        };
    }
    
    @Operation(summary = "Admin: get all documents for a specific employee")
    @GetMapping("/admin/employee/{employeeId}")
    public ResponseEntity<List<DocumentResponse>> getDocumentsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(documentService.getDocumentsByEmployeeId(employeeId));
    }

    @Operation(summary = "Admin: view any employee's document inline")
    @GetMapping("/admin/{id}/view")
    public ResponseEntity<Resource> adminViewDocument(@PathVariable Long id) {
        Document document = documentService.getDocumentEntityById(id);
        Resource resource = documentService.loadFileAsResourceAdmin(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(getContentType(document.getFileType())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }

    private String getContentType(String fileType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Operation(summary = "Admin: download any employee's document")
    @GetMapping("/admin/{id}/download")
    public ResponseEntity<Resource> adminDownloadDocument(@PathVariable Long id) {
        Document document = documentService.getDocumentEntityById(id);
        Resource resource = documentService.loadFileAsResourceAdmin(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }

    @Operation(summary = "Admin: delete any employee's document")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> adminDeleteDocument(@PathVariable Long id) {
        documentService.deleteDocumentAsAdmin(id);
        return ResponseEntity.noContent().build();
    }
}