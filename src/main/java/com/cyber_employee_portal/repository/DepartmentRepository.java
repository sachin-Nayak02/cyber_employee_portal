package com.cyber_employee_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Role;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByDepartmentName(String departmentName);

}