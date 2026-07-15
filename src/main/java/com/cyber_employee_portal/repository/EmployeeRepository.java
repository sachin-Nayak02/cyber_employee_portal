package com.cyber_employee_portal.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;

@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
    List<Employee> findByDepartment_Id(Long departmentId);
}
 