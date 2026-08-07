package com.cyber_employee_portal.dto;

import java.util.List;

public class ProfileResponse {
    private EmployeeProfileDto employeeInfo;
    private List<SkillDto> skills;
    private List<ConnectionDto> connections;
    private List<ProjectTimelineDto> projectTimelines;
    private RatingDto rating;

    // getters and setters
    public EmployeeProfileDto getEmployeeInfo() { return employeeInfo; }
    public void setEmployeeInfo(EmployeeProfileDto employeeInfo) { this.employeeInfo = employeeInfo; }
    public List<SkillDto> getSkills() { return skills; }
    public void setSkills(List<SkillDto> skills) { this.skills = skills; }
    public List<ConnectionDto> getConnections() { return connections; }
    public void setConnections(List<ConnectionDto> connections) { this.connections = connections; }
    public List<ProjectTimelineDto> getProjectTimelines() { return projectTimelines; }
    public void setProjectTimelines(List<ProjectTimelineDto> projectTimelines) { this.projectTimelines = projectTimelines; }
    public RatingDto getRating() { return rating; }
    public void setRating(RatingDto rating) { this.rating = rating; }
}
