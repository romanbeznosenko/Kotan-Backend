package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubGetService {
    private final ClubManager clubManager;
    private final StorageService storageService;

    public ClubResponse getClub(UUID clubId) {
        log.info("Getting club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);

        return ClubBuilders.buildResponse(clubDAO, storageService);
    }
}
