package com.cyber_employee_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEmployee_Id(Long employeeId);
    List<Project> findByDepartment_Id(Long departmentId);
}