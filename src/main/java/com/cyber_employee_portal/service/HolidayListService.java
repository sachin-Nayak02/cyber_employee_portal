package com.cyber_employee_portal.service;

import java.util.List;
import com.cyber_employee_portal.entity.HolidayList;

public interface HolidayListService {

    HolidayList saveHoliday(HolidayList holiday);

    List<HolidayList> getAllHolidays();

}