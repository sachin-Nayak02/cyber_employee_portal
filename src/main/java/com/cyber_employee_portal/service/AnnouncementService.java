package com.cyber_employee_portal.service;

import com.cyber_employee_portal.dto.AnnouncementRequest;
import com.cyber_employee_portal.dto.AnnouncementResponse;

import java.util.List;

public interface AnnouncementService {
    AnnouncementResponse createAnnouncement(AnnouncementRequest request, String postedByEmail);
    List<AnnouncementResponse> getAllAnnouncements();
}