package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.*;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.HolidayList;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.service.EmployeeService;
import com.cyber_employee_portal.service.HolidayListService;
import com.cyber_employee_portal.service.ProjectService;
import com.cyber_employee_portal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final HolidayListService holidayListService;

    @Override 
    public DashboardResponse getDashboardData(String email) {

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("No employee found with email: " + email));

        EmployeeInfoResponse employeeInfo = new EmployeeInfoResponse(
        		employee.getId(),
                employee.getName(),
                employee.getEmployeeId(),
                employee.getRole() != null ? employee.getRole().getName() : "N/A",
                employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : "Not Assigned",
                employee.getDesignation(),
                employee.getProfileImage()
        );

        List<ProjectResponse> projects = projectService.getProjectsByEmployee(employee.getEmployeeId());
        ProjectResponse assignedProject = projects.isEmpty() ? null : projects.get(0);

        List<NetworkResponse> teamMembers = employeeService.getMyNetwork(email);

        List<HolidayDTO> holidays = holidayListService.getAllHolidays().stream()
                .filter(h -> h.getHolidayDate() != null && !h.getHolidayDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(HolidayList::getHolidayDate))
                .map(h -> new HolidayDTO(h.getHolidayName(), h.getHolidayDate(), h.getDay()))
                .collect(Collectors.toList());

        List<BirthdayResponse> todayBirthdays = employeeService.getTodayBirthdays();
        List<BirthdayResponse> upcomingBirthdays = employeeService.getUpcomingBirthdays();
        List<AnniversaryResponse> todayAnniversaries = employeeService.getTodayAnniversaries();
        List<AnniversaryResponse> upcomingAnniversaries = employeeService.getUpcomingAnniversaries();

        return new DashboardResponse(
                employeeInfo, assignedProject, teamMembers, holidays,
                todayBirthdays, upcomingBirthdays, todayAnniversaries, upcomingAnniversaries
        );
    }
}