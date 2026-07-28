package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.DocumentResponse;
import com.cyber_employee_portal.dto.DocumentStatsResponse;
import com.cyber_employee_portal.entity.Document;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.exception.DocumentNotFoundException;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.exception.FileStorageException;
import com.cyber_employee_portal.repository.DocumentRepository;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public DocumentResponse uploadDocument(MultipartFile file, String customFileName,
                                            String category, String description, Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        if (file.isEmpty()) {
            throw new FileStorageException("Cannot upload an empty file", null);
        }

        String originalFileName = file.getOriginalFilename();
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        }

        String storedFileName = UUID.randomUUID().toString() + "." + extension;

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            Path targetPath = uploadPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Document document = new Document();
            document.setFileName((customFileName == null || customFileName.isBlank())
                    ? originalFileName : customFileName);
            document.setOriginalFileName(originalFileName);
            document.setStoredFileName(storedFileName);
            document.setCategory(category);
            document.setFileType(extension.toLowerCase());
            document.setFileSize(file.getSize());
            document.setDescription(description);
            document.setFilePath(targetPath.toString());
            document.setEmployee(employee);

            Document saved = documentRepository.save(document);

            return toResponse(saved);

        } catch (IOException e) {
            throw new FileStorageException("Failed to store file: " + originalFileName, e);
        }
    }

    @Override
    public List<DocumentResponse> getAllDocuments(Long employeeId) {
        return documentRepository.findByEmployeeIdOrderByUploadedOnDesc(employeeId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Document getDocumentForDownload(Long documentId, Long employeeId) {
        return documentRepository.findByIdAndEmployeeId(documentId, employeeId)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found or access denied"));
    }

    @Override
    public Resource loadFileAsResource(Document document) {
        try {
            Path filePath = Paths.get(document.getFilePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new DocumentNotFoundException("File not found on server: " + document.getFileName());
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException("Error loading file", e);
        }
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId, Long employeeId) {
        Document document = documentRepository.findByIdAndEmployeeId(documentId, employeeId)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found or access denied"));

        try {
            Files.deleteIfExists(Paths.get(document.getFilePath()));
        } catch (IOException e) {
            throw new FileStorageException("Failed to delete file from disk", e);
        }

        documentRepository.delete(document);
    }

    @Override
    public DocumentStatsResponse getStats(Long employeeId) {
        List<Document> allDocs = documentRepository.findByEmployeeIdOrderByUploadedOnDesc(employeeId);

        long pdfCount = allDocs.stream().filter(d -> d.getFileType().equalsIgnoreCase("pdf")).count();
        long wordCount = allDocs.stream().filter(d ->
                d.getFileType().equalsIgnoreCase("doc") || d.getFileType().equalsIgnoreCase("docx")).count();
        long imageCount = allDocs.stream().filter(d ->
                List.of("jpg", "jpeg", "png").contains(d.getFileType().toLowerCase())).count();
        long othersCount = allDocs.size() - pdfCount - wordCount - imageCount;

        double documentsSizeMB = allDocs.stream()
                .filter(d -> d.getFileType().equalsIgnoreCase("pdf")
                        || d.getFileType().equalsIgnoreCase("doc")
                        || d.getFileType().equalsIgnoreCase("docx"))
                .mapToLong(Document::getFileSize).sum() / (1024.0 * 1024);

        double imagesSizeMB = allDocs.stream()
                .filter(d -> List.of("jpg", "jpeg", "png").contains(d.getFileType().toLowerCase()))
                .mapToLong(Document::getFileSize).sum() / (1024.0 * 1024);

        double totalSizeMB = allDocs.stream().mapToLong(Document::getFileSize).sum() / (1024.0 * 1024);
        double othersSizeMB = totalSizeMB - documentsSizeMB - imagesSizeMB;

        return new DocumentStatsResponse(
                allDocs.size(), pdfCount, wordCount, imageCount, othersCount,
                round(totalSizeMB), round(documentsSizeMB), round(imagesSizeMB), round(othersSizeMB)
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private DocumentResponse toResponse(Document doc) {
        return new DocumentResponse(
                doc.getId(),
                doc.getFileName(),
                doc.getCategory(),
                doc.getFileType(),
                formatFileSize(doc.getFileSize()),
                doc.getDescription(),
                doc.getUploadedOn()
        );
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.0f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024));
    }
}