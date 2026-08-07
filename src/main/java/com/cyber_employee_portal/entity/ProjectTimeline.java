package com.cyber_employee_portal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_timelines")
public class ProjectTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "progress_percent", nullable = false)
    private Integer progressPercent; // 0-100

    @Column(name = "status", nullable = false)
    private String status; // "in progress", "completed"

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public Integer getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Integer progressPercent) { this.progressPercent = progressPercent; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}