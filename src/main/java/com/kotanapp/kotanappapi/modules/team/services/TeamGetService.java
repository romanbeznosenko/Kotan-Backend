package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
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
    private final StorageService storageService;

    public TeamResponse getTeamById(UUID id) {
        log.info("Fetching team with id: {}", id);

        TeamDAO teamDAO = teamManager.findById(id)
                .orElseThrow(TeamNotFoundException::new);

        return TeamBuilders.buildResponse(teamDAO, storageService);
    }
}
