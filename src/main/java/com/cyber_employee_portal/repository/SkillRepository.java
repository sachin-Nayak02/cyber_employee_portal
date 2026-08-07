// SkillRepository.java
package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByEmployeeId(Long employeeId);
}
