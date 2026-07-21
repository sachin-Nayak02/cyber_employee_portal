package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.CalendarResponse;

public interface CalendarService {

    CalendarResponse getCalendar(Integer month, Integer year);

}