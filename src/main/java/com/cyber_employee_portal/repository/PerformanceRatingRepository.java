// PerformanceRatingRepository.java
package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.PerformanceRating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerformanceRatingRepository extends JpaRepository<PerformanceRating, Long> {
    Optional<PerformanceRating> findByEmployeeId(Long employeeId);
}