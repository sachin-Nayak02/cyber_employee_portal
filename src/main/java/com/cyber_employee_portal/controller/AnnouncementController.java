package com.cyber_employee_portal.controller;

import com.cyber_employee_portal.dto.AnnouncementRequest;
import com.cyber_employee_portal.dto.AnnouncementResponse;
import com.cyber_employee_portal.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Announcements", description = "Admin announcements broadcast to all employees")
@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(summary = "Create a new announcement (admin only)")
    @PostMapping
    public ResponseEntity<AnnouncementResponse> createAnnouncement(
            @Valid @RequestBody AnnouncementRequest request,
            Authentication authentication) {

        String postedByEmail = authentication.getName();
        AnnouncementResponse response = announcementService.createAnnouncement(request, postedByEmail);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all announcements (visible to every logged-in employee)")
    @GetMapping
    public ResponseEntity<List<AnnouncementResponse>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }
}