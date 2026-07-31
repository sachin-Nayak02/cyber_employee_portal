package com.cyber_employee_portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "leaves")
@Data
public class Leave {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // Mapping to your database's 'from_date' column
    @Column(name = "from_date", nullable = false)
    private LocalDate startDate;

    // Mapping to your database's 'to_date' column
    @Column(name = "to_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String reason;

    // Mapping to your database's 'leave_type' column
    @Column(name = "leave_type")
    private String leaveType;

    @Column(nullable = false)
    private Long days;

    // Status can be: PENDING, APPROVED, REJECTED
    @Column(nullable = false)
    private String status = "PENDING";

	
}