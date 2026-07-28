package com.cyber_employee_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyber_employee_portal.entity.HolidayList;

public interface HolidayListRepository extends JpaRepository<HolidayList, Long> {

}