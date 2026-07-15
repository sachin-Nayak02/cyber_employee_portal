package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Leave;
import com.cyber_employee_portal.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveRepository leaveRepository;

    @GetMapping("/my")
    public Page<Leave> getMyLeaves(
            @AuthenticationPrincipal Employee employee,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fromDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return leaveRepository.findByEmployee_Id(employee.getId(), pageable);
    }


@PostMapping("/add")
public Leave addLeave(@RequestBody Leave leave, @AuthenticationPrincipal Employee employee) {
    leave.setEmployee(employee);
    return leaveRepository.save(leave);
}
}