package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.AdminUserRequest;
import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.ForgotPasswordRequest;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.ResetPasswordRequest;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.entity.Employee;

import jakarta.validation.Valid;

import java.util.List;



public interface EmployeeService {
	 
//	Employee postEmployee(Employee employee);
//    List<Employee> getAllEmployee(); 
//    void deleteEmployee(Long id); 
//    Employee updateEmployee(Long id, Employee employee); 
    RegisterResponse register(RegisterRequest request); 
	RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request);
	public void deleteEmployee (Long id);
	AdminUserResponse generateEmpId(AdminUserRequest request);
	String forgotPassword(ForgotPasswordRequest request);
    String resetPassword(ResetPasswordRequest request);

 
}