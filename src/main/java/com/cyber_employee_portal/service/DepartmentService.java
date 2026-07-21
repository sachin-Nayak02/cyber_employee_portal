package com.cyber_employee_portal.service;

import java.util.List;

import com.cyber_employee_portal.dto.DepartmentResponse;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Employee;

public interface DepartmentService {

    List<DepartmentResponse> getAllDepartments();
    List<RegisterResponse> getEmployeesByDepartment(Long departmentId);
    RegisterResponse mapToEmployeeResponse(Employee employee);
}