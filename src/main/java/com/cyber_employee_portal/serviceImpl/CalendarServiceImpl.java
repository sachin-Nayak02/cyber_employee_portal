package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.CalendarDayResponse;
import com.cyber_employee_portal.dto.CalendarResponse;
import com.cyber_employee_portal.service.CalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Override
    public CalendarResponse getCalendar(Integer month, Integer year) {

        YearMonth yearMonth = YearMonth.of(year, month);

        List<CalendarDayResponse> days = new ArrayList<>();

        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {

            LocalDate date = LocalDate.of(year, month, i);

            CalendarDayResponse day = new CalendarDayResponse();

            day.setDay(i);
            day.setDate(date.toString());

            // Default values
            day.setHoliday(false);
            day.setBirthday(false);
            day.setAnniversary(false);
            day.setHolidayName(null);

            days.add(day);
        }

        CalendarResponse response = new CalendarResponse();
        response.setMonth(Month.of(month).name());
        response.setYear(year);
        response.setDays(days);

        return response;
    }
}