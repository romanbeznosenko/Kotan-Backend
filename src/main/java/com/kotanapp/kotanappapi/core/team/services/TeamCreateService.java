package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamMapper;
import com.kotanapp.kotanappapi.core.team.models.Team;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.core.team.models.TeamRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamCreateService {
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;

    public void create(TeamRequest teamRequest) {
        log.info("Creating team with name: {}", teamRequest.name());

        Team team = TeamBuilders.buildFromRequest(teamRequest);
        TeamDAO teamDAO = teamMapper.mapToEntity(team, new CycleAvoidingMappingContext());

        teamManager.saveToDatabase(teamDAO);
    }
}
