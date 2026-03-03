package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubListResponse;
import com.kotanapp.kotanappapi.modules.club.models.ClubPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubPageService {
    private final ClubManager clubManager;
    private final StorageService storageService;

    public ClubPageResponse listAllClubs() {
        log.info("Listing all clubs");

        Page<ClubDAO> clubDAOPage = clubManager.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        List<ClubListResponse> data = clubDAOPage.get()
                .map(item -> ClubBuilders.buildListResponse(item, storageService))
                .toList();

        return ClubPageResponse.builder()
                .count(clubDAOPage.getTotalElements())
                .data(data)
                .build();
    }
}
