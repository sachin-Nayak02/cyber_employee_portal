package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.DashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboardData(String email);
}