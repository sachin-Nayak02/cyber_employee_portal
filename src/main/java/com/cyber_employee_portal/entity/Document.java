package com.cyber_employee_portal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName; // user-given display name (editable)

    @Column(nullable = false)
    private String originalFileName; // actual uploaded file name

    @Column(nullable = false)
    private String storedFileName; // unique name on disk (UUID-based, avoids collisions)

    @Column(nullable = false)
    private String category; // Personal, Professional, Finance, etc.

    @Column(nullable = false)
    private String fileType; // pdf, docx, jpg, png...

    @Column(nullable = false)
    private Long fileSize; // in bytes

    @Column
    private String description;

    @Column(nullable = false)
    private String filePath; // full path on disk

    @Column(nullable = false)
    private LocalDateTime uploadedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @PrePersist
    public void onCreate() {
        uploadedOn = LocalDateTime.now(); 
    }
}