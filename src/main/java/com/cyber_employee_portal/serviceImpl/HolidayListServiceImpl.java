package com.cyber_employee_portal.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyber_employee_portal.entity.HolidayList;
import com.cyber_employee_portal.repository.HolidayListRepository;
import com.cyber_employee_portal.service.HolidayListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayListServiceImpl implements HolidayListService {

    private final HolidayListRepository holidayListRepository;

    @Override
    public HolidayList saveHoliday(HolidayList holiday) {
        return holidayListRepository.save(holiday);
    }

    @Override
    public List<HolidayList> getAllHolidays() {
        return holidayListRepository.findAll();
    }

}