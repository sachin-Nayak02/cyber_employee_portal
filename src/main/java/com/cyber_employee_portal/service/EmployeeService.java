package com.cyber_employee_portal.service;

import java.util.List;

import com.cyber_employee_portal.dto.AdminUserRequest;
import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.EmployeeResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.entity.Employee;

public interface EmployeeService {

    RegisterResponse register(RegisterRequest request);
    //RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request, boolean isPartial);
    public void deleteEmployee(Long id);
    AdminUserResponse generateEmpId(AdminUserRequest request);

    List<EmployeeResponse> getAllEmployee();
	RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request);
}
