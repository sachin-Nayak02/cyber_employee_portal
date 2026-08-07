// ProjectTimelineRepository.java
package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.ProjectTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectTimelineRepository extends JpaRepository<ProjectTimeline, Long> {
    List<ProjectTimeline> findByEmployeeId(Long employeeId);
}