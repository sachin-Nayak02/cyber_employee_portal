package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.dto.AnnouncementRequest;
import com.cyber_employee_portal.dto.AnnouncementResponse;
import com.cyber_employee_portal.entity.Announcement;
import com.cyber_employee_portal.repository.AnnouncementRepository;
import com.cyber_employee_portal.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementResponse createAnnouncement(AnnouncementRequest request, String postedByEmail) {
        Announcement announcement = new Announcement();
        announcement.setSubject(request.getSubject());
        announcement.setDetails(request.getDetails());
        announcement.setPostedBy(postedByEmail);

        Announcement saved = announcementRepository.save(announcement);
        return mapToResponse(saved);
    }

    public List<AnnouncementResponse> getAllAnnouncements() {
        return announcementRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AnnouncementResponse mapToResponse(Announcement a) {
        return new AnnouncementResponse(a.getId(), a.getSubject(), a.getDetails(), a.getPostedBy(), a.getCreatedAt());
    }
}