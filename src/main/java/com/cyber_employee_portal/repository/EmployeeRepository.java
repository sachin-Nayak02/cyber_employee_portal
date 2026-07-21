package com.cyber_employee_portal.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;


@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(String employeeId);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
    List<Employee> findByDepartment_Id(Long departmentId);
    
    List<Employee> findByDepartment_IdAndNameContainingIgnoreCase(Long departmentId, String name);
}