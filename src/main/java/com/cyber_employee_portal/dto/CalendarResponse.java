package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {

    private String month;
    private Integer year;
    private List<CalendarDayResponse> days;
}