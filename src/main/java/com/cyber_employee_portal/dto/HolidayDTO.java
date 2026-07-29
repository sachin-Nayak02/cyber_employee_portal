package com.cyber_employee_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDTO {
    private String holidayName;
    private LocalDate holidayDate;
    private String day;
}