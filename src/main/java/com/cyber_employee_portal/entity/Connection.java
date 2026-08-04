package com.cyber_employee_portal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // owner of the network

    @ManyToOne
    @JoinColumn(name = "connected_employee_id", nullable = false)
    private Employee connectedEmployee; // the person they're connected to

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public Employee getConnectedEmployee() { return connectedEmployee; }
    public void setConnectedEmployee(Employee connectedEmployee) { this.connectedEmployee = connectedEmployee; }
}