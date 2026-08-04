package com.cyber_employee_portal.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cyber_employee_portal.dto.DepartmentSummaryResponse;
import com.cyber_employee_portal.dto.EmployeeDetailResponse;
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
    public List<DepartmentSummaryResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDetailResponse> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment_Id(departmentId)
                .stream()
                .map(this::mapToEmployeeDetail)
                .collect(Collectors.toList());
    }

    private DepartmentSummaryResponse mapToSummary(Department department) {
        return new DepartmentSummaryResponse(department.getId(), department.getDepartmentName());
    }

    private EmployeeDetailResponse mapToEmployeeDetail(Employee employee) {
        return new EmployeeDetailResponse(
                employee.getId(),
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getProfileImage(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAddress(),
                employee.getCity(),
                employee.getState(),
                employee.getCountry(),
                employee.getPincode(),
                (employee.getDepartment() != null) ? employee.getDepartment().getDepartmentName() : "Unassigned",
                employee.getDesignation(),
                employee.getEmploymentType(),
                employee.getJoiningDate(),
                employee.getSalary(),
                employee.getEmergencyContactName(),
                employee.getEmergencyContactNumber(),
                (employee.getRole() != null) ? employee.getRole().getName() : null,
                employee.getActive()
        );
    }
}