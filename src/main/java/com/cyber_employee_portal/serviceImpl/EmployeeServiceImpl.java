package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Role;
import com.cyber_employee_portal.exception.EmailAlreadyExistsException;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.repository.RoleRepository;
import com.cyber_employee_portal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        String roleName = (request.getRoleName() == null || request.getRoleName().isBlank())
                ? "EMPLOYEE"
                : request.getRoleName().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

        String employeeId = generateEmployeeId();

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
        return String.format("EMP%04d", count);
    }

    @Override
    @Transactional
    public RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request, boolean isPartial) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        Employee currentUser = getCurrentAuthenticatedEmployee();
        boolean isAdmin = "ADMIN".equalsIgnoreCase(currentUser.getRole().getName());
        boolean isSelf = currentUser.getId().equals(id);

        if (!isAdmin && !isSelf) {
            throw new AccessDeniedException("You are not allowed to update this employee record");
        }

        if (!isAdmin && (request.getRoleName() != null || request.getActive() != null)) {
            throw new AccessDeniedException("Only an administrator can change role or active status");
        }

        if (request.getEmail() != null && !request.getEmail().equalsIgnoreCase(employee.getEmail())) {
            if (employeeRepository.existsByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
            }
            employee.setEmail(request.getEmail());
        } else if (!isPartial && request.getEmail() == null) {
            throw new IllegalArgumentException("Email is required for a full update (PUT)");
        }

        if (!isPartial && (request.getName() == null || request.getName().isBlank())) {
            throw new IllegalArgumentException("Name is required for a full update (PUT)");
        }

        if (isPartial) {
            applyPartial(employee, request);
        } else {
            applyFull(employee, request);
        }

        if (request.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (isAdmin && request.getRoleName() != null) {
            Role role = roleRepository.findByName(request.getRoleName().toUpperCase())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + request.getRoleName()));
            employee.setRole(role);
        }

        if (isAdmin && request.getActive() != null) {
            employee.setActive(request.getActive());
        }

        Employee saved = employeeRepository.save(employee);

        return new RegisterResponse(
                saved.getId(),
                saved.getEmployeeId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole().getName(),
                isPartial ? "Employee updated successfully" : "Employee replaced successfully"
        );
    } 
    
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    private void applyPartial(Employee employee, UpdateEmployeeRequest r) {
        if (r.getName() != null) employee.setName(r.getName());
        if (r.getPhoneNumber() != null) employee.setPhoneNumber(r.getPhoneNumber());
        if (r.getDateOfBirth() != null) employee.setDateOfBirth(r.getDateOfBirth());
        if (r.getGender() != null) employee.setGender(r.getGender());
        if (r.getBloodGroup() != null) employee.setBloodGroup(r.getBloodGroup());
        if (r.getMaritalStatus() != null) employee.setMaritalStatus(r.getMaritalStatus());
        if (r.getNationality() != null) employee.setNationality(r.getNationality());
        if (r.getAddress() != null) employee.setAddress(r.getAddress());
        if (r.getCity() != null) employee.setCity(r.getCity());
        if (r.getState() != null) employee.setState(r.getState());
        if (r.getCountry() != null) employee.setCountry(r.getCountry());
        if (r.getPincode() != null) employee.setPincode(r.getPincode());
        if (r.getDepartment() != null) employee.setDepartment(r.getDepartment());
        if (r.getDesignation() != null) employee.setDesignation(r.getDesignation());
        if (r.getEmploymentType() != null) employee.setEmploymentType(r.getEmploymentType());
        if (r.getJoiningDate() != null) employee.setJoiningDate(r.getJoiningDate());
        if (r.getSalary() != null) employee.setSalary(r.getSalary());
        if (r.getManagerId() != null) employee.setManagerId(r.getManagerId());
        if (r.getEmergencyContactName() != null) employee.setEmergencyContactName(r.getEmergencyContactName());
        if (r.getEmergencyContactNumber() != null) employee.setEmergencyContactNumber(r.getEmergencyContactNumber());
        if (r.getProfileImage() != null) employee.setProfileImage(r.getProfileImage());
    }

    private void applyFull(Employee employee, UpdateEmployeeRequest r) {
        employee.setName(r.getName());
        employee.setPhoneNumber(r.getPhoneNumber());
        employee.setDateOfBirth(r.getDateOfBirth());
        employee.setGender(r.getGender());
        employee.setBloodGroup(r.getBloodGroup());
        employee.setMaritalStatus(r.getMaritalStatus());
        employee.setNationality(r.getNationality());
        employee.setAddress(r.getAddress());
        employee.setCity(r.getCity());
        employee.setState(r.getState());
        employee.setCountry(r.getCountry());
        employee.setPincode(r.getPincode());
        employee.setDepartment(r.getDepartment());
        employee.setDesignation(r.getDesignation());
        employee.setEmploymentType(r.getEmploymentType());
        employee.setJoiningDate(r.getJoiningDate());
        employee.setSalary(r.getSalary());
        employee.setManagerId(r.getManagerId());
        employee.setEmergencyContactName(r.getEmergencyContactName());
        employee.setEmergencyContactNumber(r.getEmergencyContactNumber());
        employee.setProfileImage(r.getProfileImage());
    }

    private Employee getCurrentAuthenticatedEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Employee)) {
            throw new AccessDeniedException("No authenticated user found");
        }
        return (Employee) authentication.getPrincipal();
    }
}