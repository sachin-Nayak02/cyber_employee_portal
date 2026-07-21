package com.cyber_employee_portal.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HolidayList")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HolidayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String holidayName; 
    
    @Column( nullable = false)
    private LocalDate holidayDate ;
    
    @Column(nullable = false)
    private String day; 
    

}