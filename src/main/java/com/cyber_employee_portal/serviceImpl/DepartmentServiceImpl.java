package com.cyber_employee_portal.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cyber_employee_portal.dto.DepartmentResponse;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.repository.DepartmentRepository;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegisterResponse> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment_Id(departmentId)
                .stream()
                .map(this::mapToEmployeeResponse)
                .collect(Collectors.toList());
    }

    private DepartmentResponse mapToResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getDepartmentName());
    }

    private RegisterResponse mapToEmployeeResponse(Employee employee) {
        return new RegisterResponse(
                employee.getId(),
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmail(),
                employee.getRole().getName(),
                null
        );
    }
}