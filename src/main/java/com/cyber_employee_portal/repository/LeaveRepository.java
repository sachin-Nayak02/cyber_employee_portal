package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    
    // For Admin: View all pending leaves
    List<Leave> findByStatus(String status);
    
    // For Employee: View their own leave history
    List<Leave> findByEmployeeId(Long employeeId);
    
    List<Leave> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date1, LocalDate date2);
    
  }
