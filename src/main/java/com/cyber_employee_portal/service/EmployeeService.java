package com.cyber_employee_portal.service;


import com.cyber_employee_portal.dto.AdminUserRequest;
import com.cyber_employee_portal.dto.AdminUserResponse;
import com.cyber_employee_portal.dto.EmployeeResponse;
import com.cyber_employee_portal.dto.BirthdayResponse;
import com.cyber_employee_portal.dto.CalendarResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.AnniversaryResponse;
import com.cyber_employee_portal.dto.CurrentDateTimeResponse;
import com.cyber_employee_portal.dto.EmployeeLookupResponse;
import com.cyber_employee_portal.dto.ForgotPasswordRequest;
import com.cyber_employee_portal.dto.NetworkResponse;
import com.cyber_employee_portal.dto.RegisterRequest;
import com.cyber_employee_portal.dto.RegisterResponse;
import com.cyber_employee_portal.dto.ResetPasswordRequest;
import com.cyber_employee_portal.dto.UpdateEmployeeRequest;
import com.cyber_employee_portal.entity.Employee;

import jakarta.validation.Valid;


import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface EmployeeService {



    List<EmployeeResponse> getAllEmployee(); 


	 
 
    RegisterResponse register(RegisterRequest request); 
	RegisterResponse updateEmployee(Long id, UpdateEmployeeRequest request);
	public void deleteEmployee (Long id);
	EmployeeLookupResponse lookupByEmployeeId(String employeeId);

	AdminUserResponse generateEmpId(AdminUserRequest request); 
	List<AdminUserResponse> getAllAdminUsers();
	 String uploadProfileImage(MultipartFile file, Employee employee);
	    Resource getProfileImage(Long employeeId);
	List<BirthdayResponse> getTodayBirthdays(); 
	 List<AnniversaryResponse> getTodayAnniversaries();
	 List<BirthdayResponse> getUpcomingBirthdays();
	 List<BirthdayResponse> getBirthdayList();
	 List<RegisterResponse> getEmployeesByGender(String gender);
	 List<CalendarResponse> getCalendarEvents();
	 
	 
	 CurrentDateTimeResponse getCurrentDateTime();
	 

	String forgotPassword(ForgotPasswordRequest request);
    String resetPassword(ResetPasswordRequest request); 
   
    List<NetworkResponse> getMyNetwork(String email);
    List<NetworkResponse> findPeopleByName(String email, String name);
    List<AnniversaryResponse> getUpcomingAnniversaries();
    RegisterResponse getEmployeeById(Long id);
   

}

