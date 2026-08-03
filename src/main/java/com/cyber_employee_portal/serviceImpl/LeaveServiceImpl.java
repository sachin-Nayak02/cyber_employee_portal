package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.LeaveRequest;
import com.cyber_employee_portal.dto.LeaveResponse;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Leave;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.repository.LeaveRepository;
import com.cyber_employee_portal.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Leave applyForLeave(LeaveRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());
        leave.setLeaveType(request.getLeaveType());
        leave.setStatus("PENDING");

        // Automatically calculate the number of days inclusive of start and end dates
        if (request.getStartDate() != null && request.getEndDate() != null) {
            long calculatedDays = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;
            leave.setDays(calculatedDays);
        }

        return leaveRepository.save(leave);
    }

   

    @Override
    public List<Leave> getEmployeeLeaves(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Leave updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave record not found"));
        
        leave.setStatus(status.toUpperCase());
        return leaveRepository.save(leave);
    }
    
    @Override
    public List<LeaveResponse> getPendingLeaves() {
        return leaveRepository.findByStatus("PENDING")
                .stream()
                .map(this::mapToLeaveResponse)
                .collect(Collectors.toList()); 
    }

    private LeaveResponse mapToLeaveResponse(Leave leave) {
        return new LeaveResponse(
                leave.getId(),
                leave.getEmployee().getId(),                          // numeric id — new
                leave.getEmployee().getName(),
                leave.getEmployee().getEmployeeId(),
                (leave.getEmployee().getDepartment() != null) ? leave.getEmployee().getDepartment().getDepartmentName() : "Unassigned",
                leave.getEmployee().getDesignation(),
                leave.getLeaveType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getDays(),
                leave.getReason(),
                leave.getStatus()
        );
    }


    @Override
    public List<LeaveResponse> getActiveLeaves(LocalDate today) {
        return leaveRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today)
                .stream()
                .map(this::mapToLeaveResponse)
                .collect(Collectors.toList());
    }
    }
     
	
