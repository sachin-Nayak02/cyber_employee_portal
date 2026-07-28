package com.cyber_employee_portal.service;

import java.util.List;

import com.cyber_employee_portal.dto.ProjectRequest;
import com.cyber_employee_portal.dto.ProjectResponse;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);
    List<ProjectResponse> getAllProjects();
    ProjectResponse getProjectById(Long projectId);
    List<ProjectResponse> getProjectsByEmployee(String employeeId);
    List<ProjectResponse> getProjectsByDepartment(String departmentName);
    ProjectResponse updateProject(Long projectId, ProjectRequest request);
    void deleteProject(Long projectId);
}