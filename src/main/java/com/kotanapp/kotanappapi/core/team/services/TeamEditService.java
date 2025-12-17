package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.core.team.models.TeamRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamEditService {
    private final TeamManager teamManager;

    public void editTeam(UUID teamId, TeamRequest teamRequest) {
        log.info("Editing team with id: {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        teamDAO.setName(teamRequest.name());
        teamDAO.setTeamType(teamRequest.teamType());

        teamManager.saveToDatabase(teamDAO);
    }
}
