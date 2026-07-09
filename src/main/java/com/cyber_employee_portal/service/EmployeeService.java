package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Employee;

import java.util.List;


public interface EmployeeService {
	
//	Employee postEmployee(Employee employee);
//    List<Employee> getAllEmployee(); 
//    void deleteEmployee(Long id); 
//    Employee updateEmployee(Long id, Employee employee); 
    RegisterResponse register(RegisterRequest request); 


}
