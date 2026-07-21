package com.cyber_employee_portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cyber_employee_portal.entity.HolidayList;
import com.cyber_employee_portal.service.HolidayListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/holidayList")
@RequiredArgsConstructor
@Tag(name = "Holiday List")
public class HolidayListController {

    private final HolidayListService holidayListService;

    @Operation(summary = "Add Holiday")
    @PostMapping
    public ResponseEntity<HolidayList> addHoliday(@RequestBody HolidayList holiday) {

        return ResponseEntity.ok(holidayListService.saveHoliday(holiday));
    }

    @Operation(summary = "Get All Holidays")
    @GetMapping
    public ResponseEntity<List<HolidayList>> getAllHolidays() {

        return ResponseEntity.ok(holidayListService.getAllHolidays());
    }

}