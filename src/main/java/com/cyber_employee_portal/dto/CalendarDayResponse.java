package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDayResponse {

    private Integer day;
    private String date;
    private boolean holiday;
    private boolean birthday;
    private boolean anniversary;
    private String holidayName;
}