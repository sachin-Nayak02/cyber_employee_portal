package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.AdminUserRequest;
import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.entity.AdminUsers;
import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Role;
import com.cyber_employee_portal.exception.EmailAlreadyExistsException;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.exception.InvalidEmployeeIdException;
import com.cyber_employee_portal.repository.AdminUserRepository;
import com.cyber_employee_portal.repository.DepartmentRepository;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.repository.RoleRepository;
import com.cyber_employee_portal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cyber_employee_portal.dto.ForgotPasswordRequest;
import com.cyber_employee_portal.dto.ResetPasswordRequest;
import com.cyber_employee_portal.exception.InvalidOtpException;
import com.cyber_employee_portal.service.EmailService;

import java.security.SecureRandom;
import java.time.LocalDateTime;



import java.util.Arrays;
 
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private  final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final DepartmentRepository  departmentRepository;

    private final EmailService emailService;


    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // 1. Validate employeeId exists in AdminUsers table (pre-issued by admin)
        AdminUsers adminUser = adminUserRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new InvalidEmployeeIdException(
                        "Invalid Employee ID. This ID was not issued by admin: " + request.getEmployeeId()));

        // 2. Prevent reusing an employeeId that's already been registered
        if (employeeRepository.existsByEmployeeId(request.getEmployeeId())) {
            throw new InvalidEmployeeIdException(
                    "This Employee ID has already been used to register: " + request.getEmployeeId());
        }

        // 3. Prevent duplicate email registration
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // 4. Resolve role — default to EMPLOYEE if not provided
        String roleName = (request.getRoleName() == null || request.getRoleName().isBlank())
                ? "EMPLOYEE"
                : request.getRoleName().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
        
        
        
        Department department = departmentRepository.findByDepartmentName(request.getDepartment())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid department: " + request.getDepartment()));

        // 5. Build employee entity
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setEmployeeId(request.getEmployeeId());
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
        employee.setDepartment(department);
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

    @Override
    @Transactional
    public AdminUserResponse generateEmpId(AdminUserRequest request) {
    	if (adminUserRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered: " + request.getEmail());
        }
    	
    	String employeeId = generateEmployeeId();
    	
    	AdminUsers adminUsers=new AdminUsers(); 
    	adminUsers.setEmail(request.getEmail());
    	adminUsers.setEmployeeId(employeeId);
    	
    	AdminUsers saved = adminUserRepository.save(adminUsers);
    	
    	return new AdminUserResponse(
    			saved.getEmail(),
                saved.getEmployeeId()
        );
    }

    private String generateEmployeeId() {
        long count = adminUserRepository.count() + 1;
        return String.format("EMPl%04d", count);
    }

    @Override
    @Transactional
    public RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        BeanUtils.copyProperties(request, employee, getNullPropertyNames(request));

        if (request.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Employee saved = employeeRepository.save(employee);

        return new RegisterResponse(
                saved.getId(),
                saved.getEmployeeId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole().getName(),
                "Employee updated successfully"
        );
    }

  
    private String[] getNullPropertyNames(Object source) {
        BeanWrapper wrapper = new BeanWrapperImpl(source);
        return Arrays.stream(wrapper.getPropertyDescriptors())
                .map(java.beans.PropertyDescriptor::getName)
                .filter(name -> wrapper.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
    
//    ---------------------------forgot password service logic-----------------------------------------------------
    
    @Override
    @Transactional
    public String forgotPassword(ForgotPasswordRequest request) {

        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "No employee found with email: " + request.getEmail()));

        String otp = generateOtp();

        employee.setOtp(otp);
        employee.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        employeeRepository.save(employee);

        emailService.sendOtpEmail(employee.getEmail(), otp);

        return "OTP sent successfully to " + request.getEmail();
    }

    @Override
    @Transactional
    public String resetPassword(ResetPasswordRequest request) {

        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "No employee found with email: " + request.getEmail()));

        // Check OTP exists
        if (employee.getOtp() == null) {
            throw new InvalidOtpException("No OTP request found. Please request a new OTP.");
        }

        // Check OTP matches
        if (!employee.getOtp().equals(request.getOtp())) {
            throw new InvalidOtpException("Invalid OTP");
        }

        // Check OTP hasn't expired
        if (employee.getOtpExpiry() == null || employee.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new InvalidOtpException("OTP has expired. Please request a new one.");
        }

        // All checks passed — update password (encrypted)
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Clear OTP so it can't be reused
        employee.setOtp(null);
        employee.setOtpExpiry(null);

        employeeRepository.save(employee);

        return "Password reset successfully";
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // always 6 digits
        return String.valueOf(otp);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}