package com.cyber_employee_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adminUsers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email; // e.g. ADMIN, EMPLOYEE
    
    @Column(unique = true, nullable = false)
    private String employeeId;

	

}