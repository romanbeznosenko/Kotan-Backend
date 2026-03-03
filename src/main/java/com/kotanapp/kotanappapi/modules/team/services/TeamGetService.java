package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamGetService {
    private final TeamManager teamManager;
    private final ClubManager clubManager;
    private final StorageService storageService;

    public TeamResponse getTeam(UUID clubId, UUID teamId) {
        log.info("Getting team with id: {} from club with id: {}", teamId, clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        TeamDAO teamDAO = teamManager.findByIdAndClub(teamId, clubDAO)
                .orElseThrow(TeamNotFoundException::new);

        return TeamBuilders.buildResponse(teamDAO, storageService);
    }
}
