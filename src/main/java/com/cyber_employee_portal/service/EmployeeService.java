package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;


public interface EmployeeService {
	
//	Employee postEmployee(Employee employee);
//    List<Employee> getAllEmployee(); 
//    void deleteEmployee(Long id); 
//    Employee updateEmployee(Long id, Employee employee); 
    RegisterResponse register(RegisterRequest request); 


}
