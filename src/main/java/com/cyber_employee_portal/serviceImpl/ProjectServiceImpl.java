package com.cyber_employee_portal.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cyber_employee_portal.dto.ProjectRequest;
import com.cyber_employee_portal.dto.ProjectResponse;
import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;
import com.cyber_employee_portal.entity.Project;
import com.cyber_employee_portal.exception.EmployeeNotFoundException;
import com.cyber_employee_portal.exception.ProjectNotFoundException;
import com.cyber_employee_portal.repository.DepartmentRepository;
import com.cyber_employee_portal.repository.EmployeeRepository;
import com.cyber_employee_portal.repository.ProjectRepository;
import com.cyber_employee_portal.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + request.getEmployeeId()));

        Department department = departmentRepository.findByDepartmentName(request.getDepartmentName())
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + request.getDepartmentName()));

        Project project = new Project();
        project.setEmployee(employee);
        project.setDepartment(department);
        project.setProjectName(request.getProjectName());
        project.setProjectDuration(request.getProjectDuration());
        project.setProjectStatus(request.getProjectStatus());

        return toResponse(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
        return toResponse(project);
    }

    @Override
    public List<ProjectResponse> getProjectsByEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        return projectRepository.findByEmployee_Id(employee.getId()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponse> getProjectsByDepartment(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentName));

        return projectRepository.findByDepartment_Id(department.getId()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));

        project.setProjectName(request.getProjectName());
        project.setProjectDuration(request.getProjectDuration());
        project.setProjectStatus(request.getProjectStatus());

        return toResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getProjectId(),
                project.getEmployee().getEmployeeId(),
                project.getEmployee().getName(),
                project.getDepartment().getDepartmentName(),
                project.getProjectName(),
                project.getProjectDuration(),
                project.getProjectStatus()
        );
    }
}