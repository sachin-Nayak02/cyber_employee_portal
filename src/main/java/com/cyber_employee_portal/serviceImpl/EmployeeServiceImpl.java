package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Role;
import com.cyber_employee_portal.exception.EmailAlreadyExistsException;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.repository.RoleRepository;
import com.cyber_employee_portal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // 1. Prevent duplicate email registration
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // 2. Resolve role — default to EMPLOYEE if not provided
        String roleName = (request.getRoleName() == null || request.getRoleName().isBlank())
                ? "EMPLOYEE"
                : request.getRoleName().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

        // 3. Generate a unique employeeId (e.g. EMP0001, EMP0002...)
        String employeeId = generateEmployeeId();

        // 4. Build entity
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setEmployeeId(employeeId);
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setGender(request.getGender());
        employee.setBloodGroup(request.getBloodGroup());
        employee.setMaritalStatus(request.getMaritalStatus());
        employee.setNationality(request.getNationality());
        employee.setAddress(request.getAddress());
        employee.setCity(request.getCity());
        employee.setState(request.getState());
        employee.setCountry(request.getCountry());
        employee.setPincode(request.getPincode());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setEmploymentType(request.getEmploymentType());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setSalary(request.getSalary());
        employee.setManagerId(request.getManagerId());
        employee.setEmergencyContactName(request.getEmergencyContactName());
        employee.setEmergencyContactNumber(request.getEmergencyContactNumber());
        employee.setProfileImage(request.getProfileImage());
        employee.setRole(role);
        employee.setActive(true);
        employee.setEmailVerified(false);

        Employee saved = employeeRepository.save(employee);

        // 5. Return safe response (no password)
        return new RegisterResponse(
                saved.getId(),
                saved.getEmployeeId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole().getName(),
                "Employee registered successfully"
        );
    }

    private String generateEmployeeId() {
        long count = employeeRepository.count() + 1;
        return String.format("EMP%04d", count); // EMP0001, EMP0002, ...
    }
}