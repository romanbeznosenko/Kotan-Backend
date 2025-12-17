package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamDeleteService {
    private final TeamManager teamManager;

    public void deleteTeam(UUID teamId){
        log.info("Delete team with id {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        teamManager.deleteTeam(teamId);
    }
}
