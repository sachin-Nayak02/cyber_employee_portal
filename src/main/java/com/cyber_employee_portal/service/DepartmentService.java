package com.cyber_employee_portal.service;

import java.util.List;

import com.cyber_employee_portal.dto.DepartmentResponse;
import com.cyber_employee_portal.dto.DepartmentSummaryResponse;
import com.cyber_employee_portal.dto.EmployeeDetailResponse;
import com.cyber_employee_portal.dto.DepartmentSummaryResponse;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Employee;

public interface DepartmentService {

	 List<DepartmentSummaryResponse> getAllDepartments();
	    List<EmployeeDetailResponse> getEmployeesByDepartment(Long departmentId);
//    RegisterResponse mapToEmployeeResponse(Employee employee);
}