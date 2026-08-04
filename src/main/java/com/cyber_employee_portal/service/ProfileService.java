package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.*;
import com.cyber_employee_portal.entity.*;
import com.cyber_employee_portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ProjectTimelineRepository projectTimelineRepository;

    @Autowired
    private PerformanceRatingRepository performanceRatingRepository;

    public ProfileResponse getProfile(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        ProfileResponse response = new ProfileResponse();

        // employee info
        EmployeeProfileDto empDto = new EmployeeProfileDto();
        empDto.setId(employee.getId());
        empDto.setName(employee.getName());
        empDto.setEmployeeId(employee.getEmployeeId());
        empDto.setRoleName(employee.getRole() != null ? employee.getRole().getName() : null);
        empDto.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : null);
        empDto.setDesignation(employee.getDesignation());
        empDto.setProfileImage(employee.getProfileImage());
        empDto.setPhone(employee.getPhoneNumber());
        empDto.setEmail(employee.getEmail());

        // combine city + state into one "location" string for the UI
        String location = buildLocation(employee);
        empDto.setLocation(location);

        response.setEmployeeInfo(empDto);

        // skills
        List<SkillDto> skills = skillRepository.findByEmployeeId(employeeId).stream()
                .map(s -> {
                    SkillDto dto = new SkillDto();
                    dto.setId(s.getId());
                    dto.setSkillName(s.getSkillName());
                    dto.setLevel(s.getLevel());
                    return dto;
                }).collect(Collectors.toList());
        response.setSkills(skills);

        // connections
        List<ConnectionDto> connections = connectionRepository.findByEmployeeId(employeeId).stream()
                .map(c -> {
                    ConnectionDto dto = new ConnectionDto();
                    Employee connected = c.getConnectedEmployee();
                    dto.setId(connected.getId());
                    dto.setName(connected.getName());
                    dto.setDesignation(connected.getDesignation());
                    dto.setProfileImage(connected.getProfileImage());
                    return dto;
                }).collect(Collectors.toList());
        response.setConnections(connections);

        // project timelines
        List<ProjectTimelineDto> timelines = projectTimelineRepository.findByEmployeeId(employeeId).stream()
                .map(t -> {
                    ProjectTimelineDto dto = new ProjectTimelineDto();
                    dto.setId(t.getId());
                    dto.setProjectName(t.getProjectName());
                    dto.setProgressPercent(t.getProgressPercent());
                    dto.setStatus(t.getStatus());
                    return dto;
                }).collect(Collectors.toList());
        response.setProjectTimelines(timelines);

        // rating
        performanceRatingRepository.findByEmployeeId(employeeId).ifPresent(r -> {
            RatingDto ratingDto = new RatingDto();
            ratingDto.setRating(r.getRating());
            ratingDto.setFeedbackNote(r.getFeedbackNote());
            response.setRating(ratingDto);
        });

        return response;
    }

    public EmployeeProfileDto updateProfile(Long employeeId, EmployeeProfileDto updated) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(updated.getName());
        employee.setPhoneNumber(updated.getPhone());
        employee.setDesignation(updated.getDesignation());
        // email intentionally not editable here (it's your login username)

        employeeRepository.save(employee);

        return updated;
    }

    private String buildLocation(Employee employee) {
        StringBuilder sb = new StringBuilder();
        if (employee.getCity() != null && !employee.getCity().isBlank()) {
            sb.append(employee.getCity());
        }
        if (employee.getState() != null && !employee.getState().isBlank()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(employee.getState());
        }
        return sb.length() > 0 ? sb.toString() : "Not specified";
    }
}