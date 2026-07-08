package com.cyber_employee_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Employee;

@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
