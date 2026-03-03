package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubDeleteService {
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;

    public void deleteClub(UUID clubId) {
        log.info("Deleting club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        Club club = clubMapper.mapToDomain(clubDAO, new CycleAvoidingMappingContext());

        club.setIsArchived(true);
        club.setArchivedAt(Instant.now());

        clubDAO = clubMapper.mapToEntity(club, new CycleAvoidingMappingContext());
        clubManager.saveToDatabase(clubDAO);
    }
}
