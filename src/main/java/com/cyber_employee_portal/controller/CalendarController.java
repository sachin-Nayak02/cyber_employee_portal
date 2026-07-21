package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.CalendarResponse;
import com.cyber_employee_portal.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public ResponseEntity<CalendarResponse> getCalendar(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return ResponseEntity.ok(calendarService.getCalendar(month, year));
    }
}