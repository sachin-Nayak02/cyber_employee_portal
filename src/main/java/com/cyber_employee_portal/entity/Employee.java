package com.cyber_employee_portal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "employees")
public class Employee implements UserDetails{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String employeeId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;
 
    @Column
    private LocalDate dateOfBirth;
    
    @Column
    private String gender;

    @Column
    private String bloodGroup; 

    @Column
    private String maritalStatus;

    @Column
    private String nationality;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column
    private String designation; 

    @Column
    private String employmentType; // Full-Time, Intern, Contract

    @Column
    private LocalDate joiningDate;

    @Column
    private Double salary;

    @Column
    private String managerId;

    @Column
    private String emergencyContactName;

    @Column
    private String emergencyContactNumber;

    @Column
    private String profileImage;

    @Column
    private Boolean active = true;

    @Column
    private Boolean emailVerified = false;

    @Column
    private LocalDateTime createdAt;

    @Column 
    private LocalDateTime updatedAt; 
    

    private LocalDateTime sessionExpiry;


    @Column
    private String otp;

    @Column
    private LocalDateTime otpExpiry;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    

    // ===== UserDetails methods (required by Spring Security) =====

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    }
 
    @Override
    public String getUsername() {
        return email; // we log in using email, not a separate username field
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
