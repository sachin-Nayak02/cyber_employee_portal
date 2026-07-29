package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private EmployeeInfoResponse employeeInfo;
    private ProjectResponse assignedProject;
    private List<NetworkResponse> teamMembers;
    private List<HolidayDTO> holidays;
    private List<BirthdayResponse> todayBirthdays;
    private List<BirthdayResponse> upcomingBirthdays;
    private List<AnniversaryResponse> todayAnniversaries;
    private List<AnniversaryResponse> upcomingAnniversaries;
}