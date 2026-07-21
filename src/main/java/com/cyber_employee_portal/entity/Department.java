package com.cyber_employee_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "department")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String departmentName; 
    
    
}